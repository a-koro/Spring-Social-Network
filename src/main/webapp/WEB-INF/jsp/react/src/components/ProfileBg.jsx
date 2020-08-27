import React from 'react'

import '../components/profileBg.css';
import ImageProfile from "../components/ImageProfile";

export default function ProfileBg() {
    return (

        <div className="card hovercard">
            <div className="cardheader">

            </div>
            <div className="avatar">
               <ImageProfile/>
               </div>
            <div className="info">
                <div className="title">
                    <p>Script Eden</p>
                </div>
                <div className="desc">Passionate designer</div>
                <div className="desc">Curious developer</div>
                <div className="desc">Tech geek</div>
            </div>

        </div>
    )
}
