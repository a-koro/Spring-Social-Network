import React from "react";
import Post from "./Post";
import {CurrentUserProvider} from "./Navbar";
import '../css/fixedNavBar.css';

export default function SinglePost(props) {

    return (
        <>
            <div className="col-md-6 offset-md-3 col-12 marginFromTopForFixedNavbar">
                <CurrentUserProvider>
                    <Post post={props.location.state.post}/>
                </CurrentUserProvider>
            </div>
        </>
    );
}