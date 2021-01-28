import React from 'react';
import GlobalContext from "../contexts/GlobalContext";

const style = {
    objectFit: 'cover',
    borderRadius: '50%',
    width: '40px',
    height: '40px'
};

const cursorStyle = {
    cursor: 'pointer'
};

function MessengerContact(props) {

    const {activeChat, setActiveChat} = React.useContext(GlobalContext);
    let url = "/api/profile/searchUsers/" + props.contact.userId;

    function selectChat() {
        setActiveChat(props.contact);
    }

    return (
        <>
            <div className="card my-2 border-0" onClick={selectChat} style={cursorStyle}>
                <div className={`card-body d-flex flex-row py-2 pb-0 px-1 ${(activeChat.userId === props.contact.userId) ? "bg-light" : ""}`}>
                    <img style={style} src={url}
                         className="avatar rounded-circle mx-2"
                         alt="Profile Picture"/>
                    <h6 className="card-text align-bottom mt-2 d-sm-block d-none">{props.contact.firstName + " " + props.contact.lastName}</h6>
                </div>
            </div>
        </>
    )


}


export default MessengerContact;