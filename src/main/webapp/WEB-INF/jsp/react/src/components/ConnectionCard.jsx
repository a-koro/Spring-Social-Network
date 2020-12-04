import React from 'react';
import {useHistory} from 'react-router-dom'

const style = {
    objectFit: 'cover',
    borderRadius: '50%',
    width: '70px',
    height: '70px'
};

function ConnectionCard(props) {

    let history = useHistory();

    const onClick = e => {
        e.preventDefault();
        console.log(props.avatar)
        history.push( {
            pathname: '/ProfileAll',
            state: {detail: props.userId}
        })

    }

    return (
        <>
            {console.log(props)}
            <div className="card my-2 border-0">
                <div className="card-body d-flex flex-row pt-2 pb-0 px-1">
                    <img style={style} src={"/api/profile/searchUsers/" + props.userId}
                         className="avatar rounded-circle mx-2"
                         alt="Profile Picture"/>
                    <h5 className="card-text align-bottom mt-4 ml-3">{props.username}</h5>
                    <a href="/ProfileAll" className="stretched-link" onClick={onClick}></a>
                </div>
                <hr/>
            </div>
        </>
    );
}

export default ConnectionCard;