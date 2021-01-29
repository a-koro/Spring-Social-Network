import React from 'react';

const style = {
    borderRadius: '10px'
};

export default function MessengerMessage(props) {

    let createdProp = new Date(props.message.timestamp);

    const [created, setCreated] = React.useState(createdProp.toString());

    function isToday(someDate) {
        const today = new Date();
        return someDate.getDate() == today.getDate() &&
            someDate.getMonth() == today.getMonth() &&
            someDate.getFullYear() == today.getFullYear();
    }

    function isSameYear(someDate) {
        const today = new Date();
        return someDate.getFullYear() == today.getFullYear();
    }

    function formatCreated() {
        if(isToday(createdProp)) {
            setCreated(createdProp.getHours() + ":" + createdProp.getMinutes());
        }
        else if(isSameYear(createdProp)) {
            setCreated(createdProp.toDateString().slice(0,-5));
        }
        else {
            setCreated(createdProp.toDateString());
        }
    }

    React.useEffect(() => {
        formatCreated();
    },[]);

    return (
            <>
                <div className={props.message.senderId == props.authUser.userId ? "bg-light float-right" : "bg-secondary float-left text-light"} style={style}>
                    <p className="m-1">{props.message.content}</p>
                </div>
                <br/>
                <div className={props.message.senderId == props.authUser.userId ? "text-right" : "text-left"}>
                    <small className="text-secondary">{created}</small>
                </div>
            </>
    );
}