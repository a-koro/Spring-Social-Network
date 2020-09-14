import React from 'react';
import { CurrentUserContext } from "./Navbar";
import {useHistory} from "react-router-dom";

// function fetchData() {
//     fetch('http://localhost:8080/getcityfromcountry/7', {mode: "cors"})
//         .then(response => response.json())
//         .then(data => console.log(data));
// }

const style = {
    objectFit: 'cover',
    borderRadius: '50%',
    width: '40px',
    height: '40px'
};

function Comment(props){

    const [dateTime, setDateTime] = React.useState(new Date());
    const currentUser = React.useContext(CurrentUserContext);
    const history = useHistory();

    function deleteComment() {
        fetch("/post/removeComment",{
            method: "POST",
            credentials: "include",
            headers: {
                commentId: props.comment.commentId
            }
        })
            .then((data) => {
                // history.push("/profile");
                // history.push("/");
                props.value.setValue(!props.value.value);
            });
    }

    React.useEffect(() => {
        let tempDate = new Date(props.comment.created);
        tempDate.setHours(tempDate.getHours()+3,tempDate.getMinutes(),tempDate.getSeconds(),tempDate.getMilliseconds());
        setDateTime(tempDate);

        // Delete post render
        if (props.comment.user.userId === currentUser.userId) {
            document.getElementById("deleteButton" + props.comment.commentId).style.display = "block";
        }
    },[]);

    return (
        <>
        <div className="card p-0 my-1 mx-2">
            <div className="card-body d-flex flex-row pt-2 pb-0 px-1 mb-0">
                {/* "https://cdn.vox-cdn.com/thumbor/G8A4RF-QWQl7jItQw93r402os_0=/1400x1050/filters:format(jpeg)/cdn.vox-cdn.com/uploads/chorus_asset/file/10816041/rick_and_morty_s02_still.jpg" */}
                <img style={style} src={"http://localhost:8080/api/profile/searchUsers/" + props.comment.user.userId}
                    className="avatar rounded-circle mx-2" 
                    alt="Cinque Terre"/>
                <div className="w-100">
                    <h6 className="card-text mb-0">{props.comment.user.firstName + " " + props.comment.user.lastName}</h6>
                    <blockquote className="card-text p-0 m-0">
                        <p className="mb-0">{props.comment.text}</p>
                    </blockquote>

                </div>
                <div onClick={deleteComment} id={"deleteButton" + props.comment.commentId} style={{display: "none", cursor: "pointer"}} className="pr-2"><i className="far fa-trash-alt"></i></div>
            </div>
            <div className="card-body p-1">
                <div className="row">
                    {/*<div className="col-4 m-2">*/}
                    {/*    <FontAwesomeIcon className="mx-2" icon={faGlassCheers} />{8}*/}
                    {/*</div>*/}
                    <div className="col-11 text-right">
                        <p className="card-text text-secondary"><small><i className="far fa-clock pr-2"></i>{dateTime.toLocaleString("en-GB",{timeZone: "UTC"})}</small></p>
                    </div>
                </div>
            </div>
        </div>
    </>
    );
}

export default Comment;