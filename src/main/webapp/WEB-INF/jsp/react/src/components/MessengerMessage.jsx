import React from 'react';

const style = {
    borderRadius: '10px'
};

export default function MessengerMessage(props) {

    console.log(props.message.created);
    return (
            <>
                <div className="bg-info float-right" style={style}>
                    <p>{props.message.content}</p>
                </div>
                <p>{props.message.timestamp}</p>
            </>
    );
}