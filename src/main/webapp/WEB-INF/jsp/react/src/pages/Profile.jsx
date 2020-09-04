import React from 'react'


import ProfileBg from '../components/ProfileBg';

import '../components/profile.css';



export default function Profile() {
    return (
        <div>
            <div className="row">
                <div className="col-md-8 col-12 offset-md-2 offset-0">
                    <ProfileBg />
                </div>
            </div>
        </div>
    )
}
