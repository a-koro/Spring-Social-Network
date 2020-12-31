import React from "react";
import Post from "./Post";
import {CurrentUserProvider} from "./Navbar";

export default function SinglePost(props) {

    return (
        <>
            <div className="col-md-6 offset-md-3 col-12">
                <CurrentUserProvider>
                    <Post post={props.location.state.post}/>
                </CurrentUserProvider>
            </div>
        </>
    );
}