import React from 'react';
import ProfileAll from "./ProfileAll";
import {CurrentUserProvider} from "../components/Navbar";
import {PostsProvider} from "../components/NewsFeed";

function FriendsProfilePage() {
    return (
        <>
            <CurrentUserProvider>
                <PostsProvider>
                    <ProfileAll/>
                </PostsProvider>
            </CurrentUserProvider>
        </>
    );
}
export default FriendsProfilePage;