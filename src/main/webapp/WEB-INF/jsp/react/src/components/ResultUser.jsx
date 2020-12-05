import React from 'react';
import {useHistory} from 'react-router-dom'

const style = {
    objectFit: 'cover',
    borderRadius: '50%',
    width: '70px',
    height: '70px'
};

function Contact(props) {

    //TODO add a callback function to send the request to retrieve the contact profile

    let history = useHistory();



    const getUserId = (props) => {
        const initialString = props.avatar;
        if (initialString.length === 47) {
            return initialString.substr(initialString.length -1)
        }
        else if (initialString.length === 48) {
            // console.log("for double digit id: " + initialString.substr(initialString.length -2) )
            return initialString.substr(initialString.length -2)
        }
        // Triple digit user Id
        else if ( initialString.length ===49) {
            return initialString.substr(initialString.length -3)
        }
    }

    const onClick = e => {
        e.preventDefault();
        console.log(props.avatar)
        history.push( {
            pathname: '/ProfileAll',
            state: {detail: props.avatar}
        })

    }


    return (
        <>
            <div className="card my-2 border-0">
                <div className="card-body d-flex flex-row pt-2 pb-0 px-1">
                    <img style={style} src={"/api/profile/searchUsers/" + props.avatar}
                         className="avatar rounded-circle mx-2"
                         alt="Profile Picture"/>
                    {/*{console.log(getUserId(props))}*/}
                    <h5 className="card-text align-bottom mt-4 ml-3">{props.username}</h5>
                    <a href="/ProfileAll" className="stretched-link" onClick={onClick}></a>
                </div>
                <hr/>
            </div>
        </>
    );
}

export default Contact;