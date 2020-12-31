import React from "react";
import Axios from "axios";
import {useHistory} from 'react-router-dom';
import TimeAgo from 'react-timeago';
import '../css/commentNotification.css';

export default function NewCommentNotification(props) {

    const history = useHistory();

    function selectNotificationComment() {
        Axios.get(`/post/getSpecificPost/${props.comment.commentId}`)
            .then((response) =>{
                history.push({
                    pathname: "/post",
                    state: {
                        post: response.data
                    }
                });
                props.updateNotifications();
            });
    }

    return (
        <>
            <div className="card my-2 border-0" onClick={selectNotificationComment}>
                <div className="card-body justify-content-start my-0 py-0 px-1">
                    <div className="commentNotification">
                        <p className="card-text"><b className="card-title mt-2">{props.comment.user.firstName + " " + props.comment.user.lastName}</b> commented on your post</p>
                    </div>
                    <div className="text-right">
                        <small><TimeAgo date={props.comment.created}/></small>
                    </div>
                </div>
            </div>

        </>
    );
}