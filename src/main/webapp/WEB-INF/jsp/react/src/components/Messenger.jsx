import React from 'react';
import GlobalContext from "../contexts/GlobalContext";
import Contact from "./Contact";
import Axios from 'axios';
import '../css/messenger.css';
import MessengerContact from "./MessengerContact";
import MessengerMessage from "./MessengerMessage";
import DataServices from "../services/DataServices";
import {message} from "antd";

export default function Messenger(props) {

    const {authenticatedUser} = React.useContext(GlobalContext);
    const {activeChat, setActiveChat} = React.useContext(GlobalContext);
    const {chatMessages, setChatMessages} = React.useContext(GlobalContext);
    const {connections} = React.useContext(GlobalContext);
    const {stompClient} = React.useContext(GlobalContext);
    const [chats, setChats] = React.useState([]);
    const [message, setMessage] = React.useState("");

    function getChatMessages(userId) {
        if (userId) {
            Axios.get(`/messages/${userId}/${authenticatedUser.userId}`).then((response) => {
                setChatMessages(response.data);
            });
        }
    }

    function getChats() {
        Axios.get('/messages/getChats').then((response) => {
            setChats(response.data);
            setActiveChat(response.data[0]);
            //getChatMessages(response.data[0].userId);
        });
    }

    const sendMessage = (msg) => {
        if (msg.trim() !== "") {
            const message = {
                senderId: authenticatedUser.userId,
                recipientId: activeChat.userId,
                content: msg,
                timestamp: new Date(),
            };
            stompClient.send("/app/chat", {}, JSON.stringify(message));
            const newMessages = [...chatMessages];
            newMessages.push(message);
            setChatMessages(newMessages);
        }
    };

    function submitForm(evt) {
        evt.preventDefault();
        sendMessage(message);
    }

    React.useEffect(() => {
        getChats();
    },[]);

    React.useEffect(() => {
        getChatMessages(activeChat.userId);
    },[activeChat]);

    return (
        <div className="col-lg-8 col-12 offset-lg-2 mt-5 pt-3">
            {/*Button trigger modal*/}
            <button type="button" className="btn btn-primary my-3" data-toggle="modal" data-target="#exampleModal">
                New Chat
            </button>

            {/*Modal*/}
            <div className="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div className="modal-dialog" role="document">
                    <div className="modal-content">
                        <div className="modal-header">
                            <h5 className="modal-title" id="exampleModalLabel">Modal title</h5>
                            <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div className="modal-body">
                            ...
                        </div>
                        <div className="modal-footer">
                            <button type="button" className="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button type="button" className="btn btn-primary">Save changes</button>
                        </div>
                    </div>
                </div>
            </div>
            <div className="row">
                <div className="col-4 pr-0 tallDiv">
                    <div className="tallDivOfChats hideScrollBar">
                        {
                            chats.map((chat) => (
                                <div>
                                    <MessengerContact  contact={chat}/>
                                </div>
                            ))
                        }
                    </div>
                </div>
                <div className="col-8 border border-light px-0 tallDiv pb-2">
                    { activeChat.userId &&
                        <Contact  userFriendId = {activeChat.userId} username={ activeChat.firstName + " " + activeChat.lastName}/>
                    }
                    <div className="tallOverFlow">
                        <ul className="px-3" style={{'listStyle': 'none'}}>
                            { chatMessages.map((msg) => (
                                <li>
                                    <MessengerMessage message={msg} authUser={authenticatedUser}/>
                                    <br/>
                                </li>
                            ))}
                        </ul>
                    </div>
                    <div className="m-3">
                        <form onSubmit={submitForm}>
                            <div className="form-row">
                                <div className="col-11">
                                    <input
                                        className="form-control w-80"
                                        type="text" value={message}
                                        onChange={(evt) => {setMessage(evt.target.value);}}
                                    />
                                </div>
                                <div className="col-1">
                                    <button type="submit" className="btn"><i className="far fa-paper-plane fa-2x"></i></button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
}