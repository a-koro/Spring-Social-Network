import React from 'react';

const style = {
    objectFit: 'cover',
    borderRadius: '50%',
    width: '40px',
    height: '40px'
};

function Contact(props) {
    let url = "http://localhost:8080/api/profile/searchUsers/" + props.userId;

    return (
        <>
            <div className="card my-2 border-0">
                <div className="card-body d-flex flex-row pt-2 pb-0 px-1">
                    <img style={style} src={url}
                        className="avatar rounded-circle mx-2"
                        alt="Cinque Terre" />
                    <h6 className="card-text align-bottom mt-2">{props.username}</h6>
                </div>
            </div>
        </>
    );
}

export default Contact;