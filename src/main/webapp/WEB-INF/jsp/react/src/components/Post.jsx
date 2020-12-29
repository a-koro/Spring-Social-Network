import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faComments } from '@fortawesome/free-solid-svg-icons';
import { faGlassCheers } from '@fortawesome/free-solid-svg-icons';
import { CurrentUserContext } from "./Navbar";
import Comment from './Comment';
import {useHistory} from "react-router-dom";
import Axios from 'axios';
import TimeAgo from 'react-timeago';

const style = {
    objectFit: 'cover',
    borderRadius: '50%',
    width: '50px',
    height: '50px'
};

function Post(props) {

    const [postImageUrl, setPostImageUrl] = React.useState("");
    const [dateTime, setDateTime] = React.useState(new Date());
    const [cheers, setCheers] = React.useState(0);
    const [editable, setEditable] = React.useState(false);
    const [text, setText] = React.useState(props.post.text);
    const history = useHistory();

    const currentUser = React.useContext(CurrentUserContext);

    function insertComment(evt) {
        evt.preventDefault();

        fetch("/post/insertComment",{
            method: 'POST',
            credentials: "include",
            headers: {
                'input': evt.target.input.value,
                'postId': props.post.postId
            }
        })
            .then(response => response.json())
            .then(data => props.value.setValue(!props.value.value));

        evt.target.input.value = "";
    }

    async function updatePost(evt) {
        if(editable) {
            try {
                await Axios.post(
                    '/post/updatePost',
                    {
                        'text': text
                    },
                    {
                        withCredentials: true,
                        headers: {
                            'Content-Type': 'application/json',
                            postId: props.post.postId
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

    function cheer() {
        fetch("/post/cheers",{
            method: "POST",
            credentials: "include",
            headers: {
                postId: props.post.postId
            }
        })
            .then(response => response.json())
            .then(data => {
                setCheers(data.filter(cheer => cheer.active === true).length);
            })
    }

    function deletePost() {
        fetch("/post/removePost",{
            method: "POST",
            credentials: "include",
            headers: {
                postId: props.post.postId
            }
        })
            .then((data) => {
                history.push("/profile");
                history.push("/");
            });
    }

    function viewProfile() {
        if(props.post.user.userId === currentUser.userId) {
            history.push("/profile");
        }
        else {
            history.push({
                pathname: '/ProfileAll',
                state: {detail: props.post.user.userId}
            });
        }
    }

    React.useEffect(() => {
        // Date render
        let tempDate = new Date(props.post.created);
        tempDate.setHours(tempDate.getHours()+3,tempDate.getMinutes(),tempDate.getSeconds(),tempDate.getMilliseconds());
        setDateTime(tempDate);

        // Cheers render
        setCheers(props.post.cheers.filter(cheer => cheer.active === true).length);

        // Delete post render
        // if (props.post.user.userId === currentUser.userId) {
        //     console.log(props.post);
        //     document.getElementById("deleteButton" + props.post.postId).style.display = "block";
        // }

        // Image render
        if (props.post.imageUrl === "BLOB") {
            document.getElementById("postImage"+props.post.postId).style.display = "block";
            setPostImageUrl("/post/downloadPostImage/" + props.post.postId);
        }
        else if (props.post.imageUrl !== "" && props.post.imageUrl !== null) {
            document.getElementById("postImage"+props.post.postId).style.display = "block";
            setPostImageUrl(props.post.imageUrl);
        }
        // else if (!(props.post.postImage === null || props.post.postImage === undefined)) {
        //     document.getElementById("postImage"+props.post.postId).style.display = "block";
        //     setPostImageUrl("/post/downloadPostImage/" + props.post.postId);
        // }
    },[]);

    return (
        <>
            <div className="card p-0 my-3"> {/*col-md-6 col-xs-12 col-sm-8*/}
                <div className="card-body d-flex flex-row p-3">
                    <img style={style} src={"/api/profile/searchUsers/" + props.post.user.userId}
                        className="avatar rounded-circle mx-3"
                        alt="Profile picture"
                        onClick={viewProfile}/>
                    <div className="w-100" onClick={viewProfile}>
                        <h5 className="card-title mb-0" style={{cursor: "pointer"}}>{props.post.user.firstName + " " + props.post.user.lastName}</h5>
                        <p className="card-text text-secondary"><small><i className="far fa-clock pr-2"></i><TimeAgo date={props.post.created}/></small></p>
                        {/*<p className="card-text text-secondary"><small><i className="far fa-clock pr-2"></i>{dateTime.toLocaleString("en-GB",{timeZone: "UTC"})}</small></p>*/}
                    </div>
                    {(props.post.user.userId === currentUser.userId) &&
                        <>
                            <div onClick={updatePost} id={"updateButton" + props.post.postId} style={{cursor: "pointer"}}><i className="far fa-edit mr-2"></i></div>
                            <div onClick={deletePost} id={"deleteButton" + props.post.postId} style={{cursor: "pointer"}}><i className="far fa-trash-alt"></i></div>
                        </>
                    }
                </div>
                <div className="card-body p-1">
                    <img src={postImageUrl} alt="Couldn't load image from URL" className="img-fluid rounded" id={"postImage" + props.post.postId} style={{display: "none", width: "100%"}}/>
                    <blockquote className="card-text p-3 m-0">
                        { !editable &&
                            <p className="mb-0">{text}</p>
                        }
                        { editable &&
                            <textarea value={text} rows="6" minLength="1" maxLength="250" className="form-control" onChange={(evt) => {setText(evt.target.value)}}></textarea>
                        }
                    </blockquote>
                    <div className="row">
                        <div className="col-xs-12 col-sm-4 col-md-4 m-2">
                            <FontAwesomeIcon className="mx-2" icon={faGlassCheers} onClick={cheer} style={{cursor: "pointer"}}/>{cheers}
                            {/*{props.post.cheers.length}*/}
                            <FontAwesomeIcon className="mx-2" icon={faComments} data-toggle="collapse" data-target={"#collapseExample" + props.post.postId}
                                             aria-expanded="false" aria-controls="collapseExample" style={{cursor: "pointer"}}/>{props.post.comments.length}
                        </div>
                    </div>
                </div>
                <form className="mx-2" onSubmit={insertComment}>
                    <div className="form-group">
                        <input type="text" className="form-control"
                               placeholder="Enter a comment..." name="input" minLength="1"  maxLength="250"/>
                    </div>
                </form>
                <small className="ml-2 mt-0 mb-1" data-toggle="collapse" data-target={"#collapseExample" + props.post.postId}
                        aria-expanded="false" aria-controls="collapseExample" style={{cursor: "pointer"}}>
                    Show/Hide Comments
                </small>
                <div className="collapse" id={"collapseExample" + props.post.postId}>
                    {props.post.comments.sort((a,b)=>{return new Date(a.created) - new Date(b.created)}).map((comment) => (
                        <Comment comment={comment} value={props.value} key={comment.commentId}/>
                    ))}
                </div>
            </div>
        </>
    );
}

export default Post;