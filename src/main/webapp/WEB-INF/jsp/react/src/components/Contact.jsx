import React from 'react';
import {useHistory} from 'react-router-dom'

const style = {
    objectFit: 'cover',
    borderRadius: '50%',
    width: '40px',
    height: '40px'
};

// export const ContactPage = props => {
//     let history = useHistory();
//
//     const onClick = e => {
//         history.push({
//             pathname: '/ProfileAll',
//             ourUserId: props.userFriendId
//         });
//     }
// }




function Contact(props) {

    let history = useHistory();


    const onClick = e => {
        console.log(props.userFriendId)
        history.push({
            pathname: '/ProfileAll',
            state: {detail: props.userFriendId}
        });
    }

    return (
        <>
            <div className="card my-2 border-0">
                {/*<button onClick={onClick}>Click</button>*/}
                <div onClick={onClick} className="card-body d-flex flex-row pt-2 pb-0 px-1">
                    <img style={style} src={props.avatar}
                         className="avatar rounded-circle mx-2"
                         alt="Cinque Terre"/>
                    <h6 className="card-text align-bottom mt-2">{props.username}</h6>
                </div>
            </div>
        </>
    );
}

export default Contact;