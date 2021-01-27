import React from 'react';
import {useHistory} from 'react-router-dom';

const style = {
    objectFit: 'cover',
    borderRadius: '50%',
    width: '40px',
    height: '40px'
};

function Contact(props) {

    let url = "/api/profile/searchUsers/" + props.userFriendId;
    let history = useHistory();

    const onClick = e => {
        e.preventDefault();
        console.log(props.userFriendId)
        history.push({
            pathname: '/ProfileAll',
            state: {detail: props.userFriendId},
            // fuckingSendThem: {data: "data"}
        });
    }

    return (
        <>
            <div className="card my-2 border-0">
                {/*<button onClick={onClick}>Click</button>*/}
                <div className="card-body d-flex flex-row pt-2 pb-0 px-1">
                    <img style={style} src={url}
                         className="avatar rounded-circle mx-2"
                         alt="Profile Picture"/>
                    <h6 className="card-text align-bottom mt-2">{props.username}</h6>
                    <a href="/ProfileAll" className="stretched-link" onClick={onClick}></a>
                </div>
            </div>

        </>
    )


}


export default Contact;

