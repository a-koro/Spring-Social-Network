import React from 'react';

const style = {
    objectFit: 'cover',
    borderRadius: '50%',
    width: '70px',
    height: '70px'
};

function Contact(props) {

    //TODO add a callback function to send the request to retrieve the contact profile

    return (
        <>
            <div className="card my-2 border-0">
                <div className="card-body d-flex flex-row pt-2 pb-0 px-1">
                    <img style={style} src={props.avatar}
                         className="avatar rounded-circle mx-2"
                         alt="Cinque Terre" />
                    <h5 className="card-text align-bottom mt-4 ml-3">{props.username}</h5>
                </div>
                <hr/>
            </div>
        </>
    );
}

export default Contact;