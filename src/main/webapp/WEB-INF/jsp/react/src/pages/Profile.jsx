import React from 'react'
import {CurrentUserProvider} from "../components/Navbar";
import {PostsProvider} from "../components/NewsFeed";

import ProfileBg from '../components/ProfileBg';

import '../components/profile.css';


export default function Profile() {
    return (
        <>
            <CurrentUserProvider>
                <PostsProvider>
                    <ProfileBg/>
                </PostsProvider>
            </CurrentUserProvider>
        </>
    )
}
