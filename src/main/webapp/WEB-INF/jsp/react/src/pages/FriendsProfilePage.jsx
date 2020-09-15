import React from 'react';
import ProfileAll from "./ProfileAll";
import {CurrentUserProvider} from "../components/Navbar";

function FriendsProfilePage() {
    return (
        <>
            <CurrentUserProvider>
                <ProfileAll/>
            </CurrentUserProvider>
        </>
    );
}
export default FriendsProfilePage;