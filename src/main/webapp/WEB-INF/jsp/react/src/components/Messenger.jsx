import React from 'react';
import GlobalContext from "../contexts/GlobalContext";
import Contact from "./Contact";

export default function Messenger(props) {

    const {authenticatedUser} = React.useContext(GlobalContext);
    const {activeChat, setActiveChat} = React.useContext(GlobalContext);
    const {chatMessages, setChatMessages} = React.useContext(GlobalContext);
    const {connections} = React.useContext(GlobalContext);

    return (
        <div className="col-lg-8 col-12 offset-lg-2 mt-5 pt-3">
            <div className="col-4">
                {
                    connections.map((connection) => (
                        <Contact  userFriendId = {connection.userId} username={ connection.firstName + " " + connection.lastName}/>
                    ))
                }
            </div>
            <div className="col-8">

            </div>
        </div>
    );
}