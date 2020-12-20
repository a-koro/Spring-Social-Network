import React from 'react';
import { CurrentUserContext } from "./Navbar";
import {useHistory} from "react-router-dom";
import Axios from "axios";
import TimeAgo from 'react-timeago';

// function fetchData() {
//     fetch('/getcityfromcountry/7', {mode: "cors"})
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
    const [editable, setEditable] = React.useState(false);
    const [text, setText] = React.useState(props.comment.text);
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

    async function updatePost() {
        if(editable) {
            try {
                await Axios.post(
                    '/post/updateComment',
                    {
                        'text': text
                    },
                    {
                        withCredentials: true,
                        headers: {
                            'Content-Type': 'application/json',
                            commentId: props.comment.commentId
                        }
                    }
                );
            } catch (err) {
                console.log(err);
                setText(props.post.text);
            }
        }
        setEditable(!editable);
    }

    function viewProfile() {
        if(props.comment.user.userId === currentUser.userId) {
            history.push("/profile");
        }
        else {
            history.push({
                pathname: '/ProfileAll',
                state: {detail: props.comment.user.userId}
            });
        }
    }

    React.useEffect(() => {
        let tempDate = new Date(props.comment.created);
        tempDate.setHours(tempDate.getHours()+3,tempDate.getMinutes(),tempDate.getSeconds(),tempDate.getMilliseconds());
        setDateTime(tempDate);

        // Delete post render
        // if (props.comment.user.userId === currentUser.userId) {
        //     document.getElementById("deleteButton" + props.comment.commentId).style.display = "block";
        // }
    },[]);

    return (
        <>
        <div className="card p-0 my-1 mx-2">
            <div className="card-body d-flex flex-row pt-2 pb-0 px-1 mb-0">
                {/* "https://cdn.vox-cdn.com/thumbor/G8A4RF-QWQl7jItQw93r402os_0=/1400x1050/filters:format(jpeg)/cdn.vox-cdn.com/uploads/chorus_asset/file/10816041/rick_and_morty_s02_still.jpg" */}
                <img style={style} src={"/api/profile/searchUsers/" + props.comment.user.userId}
                    className="avatar rounded-circle mx-2" 
                    alt="Cinque Terre"
                    onClick={viewProfile}/>
                <div className="w-100">
                    <h6 className="card-text mb-0" onClick={viewProfile} style={{cursor: "pointer"}}>{props.comment.user.firstName + " " + props.comment.user.lastName}</h6>
                    <blockquote className="card-text p-0 m-0">
                        { !editable &&
                            <p className="mb-0">{text}</p>
                        }
                        { editable &&
                            <textarea value={text} rows="3" minLength="1" maxLength="250" className="form-control" onChange={(evt) => {setText(evt.target.value)}}></textarea>
                        }
                    </blockquote>

                </div>
                {(props.comment.user.userId === currentUser.userId) &&
                    <>
                        <div onClick={updatePost} id={"updateButton" + props.comment.commentId} style={{cursor: "pointer"}}><i className="far fa-edit mr-2"></i></div>
                        <div onClick={deleteComment} id={"deleteButton" + props.comment.commentId} style={{cursor: "pointer"}} className="pr-2"><i className="far fa-trash-alt"></i></div>
                    </>
                }
            </div>
            <div className="card-body p-1">
                <div className="row">
                    {/*<div className="col-4 m-2">*/}
                    {/*    <FontAwesomeIcon className="mx-2" icon={faGlassCheers} />{8}*/}
                    {/*</div>*/}
                    <div className="col-11 text-right">
                        <p className="card-text text-secondary"><small><i className="far fa-clock pr-2"></i><TimeAgo date={props.comment.created}/></small></p>
                        {/*<p className="card-text text-secondary"><small><i className="far fa-clock pr-2"></i>{dateTime.toLocaleString("en-GB",{timeZone: "UTC"})}</small></p>*/}
                    </div>
                </div>
            </div>
        </div>
    </>
    );
}

export default Comment;