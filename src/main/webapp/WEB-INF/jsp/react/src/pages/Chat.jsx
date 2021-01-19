import React, { useEffect, useState } from "react";

import DataServices from '../services/DataServices';

import { Button, message } from "antd";
import ScrollToBottom from "react-scroll-to-bottom";
import './Chat.css';
import { useRecoilValue, useRecoilState } from "recoil";
import '../css/fixedNavBar.css';

import {
    loggedInUser,
    chatActiveContact,
    chatMessages,
} from "../atom/globalState";

import { getUsers } from "../ApiUtil";

var stompClient = null;
const style = {
    objectFit: 'cover',
    borderRadius: '50%',
    width: '40px',
    height: '40px'
};

function Chat(props) {


    const user = useRecoilValue(loggedInUser);
    const [contacts, setContacts] = useState([]);
    const [messages, setMessages] = useRecoilState(chatMessages);
    const [activeContact, setActiveContact] = useRecoilState(chatActiveContact);
    const [text, setText] = useState("");

    useEffect(() => {
        connect();
        loadContacts();
    }, []);



    useEffect(() => {
        if (activeContact === undefined) return;
        console.log("useEffect", activeContact);
        DataServices.findChatMessages(activeContact.userSecondId, user.userId).then((msgs) =>
            setMessages(msgs.data)
        ).catch(error => { console.log(error.response) });
        loadContacts();
    }, [activeContact]);




    const connect = () => {
        const Stomp = require("stompjs");
        var SockJS = require("sockjs-client");
        SockJS = new SockJS("/ws");
        stompClient = Stomp.over(SockJS);
        stompClient.connect({}, onConnected, onError);
    };

    const onError = (err) => {
        console.log(err);
    };

    const onConnected = () => {
        console.log("connected");
        console.log("connected user id ", user.userId);
        stompClient.subscribe(
            "/user/" + user.userId + "/queue/messages",
            onMessageReceived
        );
    };

    const sendMessage = (msg) => {
        if (msg.trim() !== "") {
            const message = {
                senderId: user.userId,
                recipientId: activeContact.userSecondId,
                // senderEmail: user.email,
                // recipientEmail: activeContact.lastName,
                content: msg,
                timestamp: new Date(),
            };
            stompClient.send("/app/chat", {}, JSON.stringify(message));

            const newMessages = [...messages];
            newMessages.push(message);
            setMessages(newMessages);

        }
    };

    const onMessageReceived = (msg) => {

        const notification = JSON.parse(msg.body);
        const active = JSON.parse(sessionStorage.getItem("recoil-persist"))
            .chatActiveContact;

        console.log("notification ", notification);
        console.log("active ", active);

        if (active.userSecondId == notification.userId) {
            DataServices.findChatMessage(notification.chatNotificationId).then((message) => {
                const newMessages = JSON.parse(sessionStorage.getItem("recoil-persist"))
                    .chatMessages;

                newMessages.push(message.data);
                setMessages(newMessages);

                console.log("chat active contact from recoil state ", activeContact);
                console.log("chat active contact from sessionStorage ", JSON.parse(sessionStorage.getItem("recoil-persist")).chatActiveContact);

                console.log("newMessages ", newMessages);
                console.log("message ", message.data);
            }).catch(error => { console.log(error.response) });
        } else {
            message.info("Received a new message from " + notification.firstName + " " + notification.lastName);
        }
        loadContacts();
    };

    function loadContacts() {
        DataServices.getContacts().then(response => {
            setContacts(response.data);
            if (JSON.parse(sessionStorage.getItem("recoil-persist"))
                .chatActiveContact === undefined && response.data.length > 0) {

                // if(JSON.parse(sessionStorage.getItem("recoil-persist"))
                // .chatActiveContact!== undefined){ setActiveContact(JSON.parse(sessionStorage.getItem("recoil-persist"))
                // .chatActiveContact)}

                setActiveContact(response.data[0]);
                console.log("set user0 active in if loop ", activeContact);
                console.log("user 0 ", response.data[0]);
            }
        }
        );
    }




    return (
        <div id="chat" className="container">
            <div className="row marginFromTopForFixedNavbar">
                <div className="col-md-4 friends">
                    <div id="contacts">
                        <ul>
                            {contacts.map((contact) => (
                                <li
                                    onClick={() => setActiveContact(contact)}
                                    class={
                                        activeContact && contact.userSecondId === activeContact.userSecondId
                                            ? "contact-active"
                                            : "contact"
                                    }
                                >
                                    <div class="wrap">
                                        <span class="contact-status online"></span>
                                        <img style={style}  src={"/api/profile/searchUsers/"+contact.userSecondId} alt="" />
                                        <div class="meta">
                                            <p class="name">{contact.firstName + " " + contact.lastName}</p>
                                            {contact.newMessages !== undefined &&
                                                contact.newMessages > 0 && (
                                                    <p class="preview">
                                                        {contact.newMessages} new messages
                                                    </p>
                                                )}
                                        </div>
                                    </div>
                                </li>
                            ))}
                        </ul>
                    </div>
                </div>
                <div class="col-md-8 chat-room" >
                    <div class="contact-profile">
                        <img style={style} src={activeContact && "/api/profile/searchUsers/"+activeContact.userSecondId} alt="" />
                        <p>{activeContact && (activeContact.firstName + " " + activeContact.lastName)}</p>
                    </div>
                    <div >
                    <ScrollToBottom className="messages">
                        <ul>
                            {messages.map((msg) => (
                                <li class={msg.senderId === user.userId ? "sent" : "replies"}>
                                    {msg.senderId !== user.userId && (
                                         <img style={style} className="chat-image" src={activeContact && "/api/profile/searchUsers/"+activeContact.userSecondId} alt="" />
                                    )}
                                    <p>{msg.content}</p>
                                </li>
                            ))}

                        </ul>

                    </ScrollToBottom>
                    </div>
                    <div class="message-input">
                        <div class="wrap">
                            <input
                                name="user_input"
                                size="large"
                                placeholder="Write your message..."
                                value={text}
                                onChange={(event) => setText(event.target.value)}
                                onKeyPress={(event) => {
                                    if (event.key === "Enter") {
                                        sendMessage(text);
                                        setText("");
                                    }
                                }}
                            />
                            <Button
                                icon={<i class="fa fa-paper-plane" aria-hidden="true"></i>}
                                onClick={() => {
                                    sendMessage(text);
                                    setText("");
                                }}
                            />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Chat;
