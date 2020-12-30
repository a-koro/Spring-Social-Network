import React from "react";
import TimeAgo from 'react-timeago';

export default function NewCommentNotification(props) {
    return (
        <>
            <div className="card my-2 border-0">
                <div className="card-body justify-content-start my-0 py-0 px-1">
                    <div>
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