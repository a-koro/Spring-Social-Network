import React from 'react'


import ProfileBg from '../components/ProfileBg';

import '../components/profile.css';



export default function Profile() {
    return (
        <div>

            <div className="container-fluid">
                <div className="row">
                    <div className="col-2"></div>
                    <div className="col-8">

                        <ProfileBg />
                    </div>
                    <div className="col-2"></div>
                </div>
            </div>
        </div>
    )
}
