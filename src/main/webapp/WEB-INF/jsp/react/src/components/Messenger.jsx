import React from 'react';
import GlobalContext from "../contexts/GlobalContext";
import Contact from "./Contact";
import Axios from 'axios';
import '../css/messenger.css';
import MessengerContact from "./MessengerContact";
import MessengerMessage from "./MessengerMessage";

export default function Messenger(props) {

    const {authenticatedUser} = React.useContext(GlobalContext);
    const {activeChat, setActiveChat} = React.useContext(GlobalContext);
    const {chatMessages, setChatMessages} = React.useContext(GlobalContext);
    const {connections} = React.useContext(GlobalContext);
    const [chats, setChats] = React.useState([]);

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
                <div className="col-8 border border-light px-0 tallDiv">
                    { activeChat.userId &&
                        <Contact  userFriendId = {activeChat.userId} username={ activeChat.firstName + " " + activeChat.lastName}/>
                    }
                    <div className="tallOverFlow">
                        <ul className="px-1" style={{'listStyle': 'none'}}>
                            { chatMessages.map((msg) => (
                                <li>
                                    <MessengerMessage message={msg} authUser={authenticatedUser}/>
                                    <br/>
                                </li>
                            ))}
                        </ul>
                    </div>
                    <div className="m-3">
                        <i className="far fa-paper-plane fa-2x"></i>
                    </div>
                </div>
            </div>
        </div>
    );
}