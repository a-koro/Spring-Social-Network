import React from 'react'

import '../components/profileBg.css';


import DataServices from '../services/DataServices';
import Dropzone from "./Dropzone";


export default function ProfileBg() {

    const [user, setUser] = React.useState({});

    function getCurrentUser() {
        DataServices.getCurrentUser().then(
            response => {
                console.log("user in profile : ", response.data);
                setUser(response.data);
            }
        ).catch(error => { console.log(error.response) });
    }

    React.useEffect(() => { getCurrentUser() }, []);

    return (

        <div className="card hovercard">
            <div className="cardheader">

            </div>
            <div className="avatar">
                <img alt="image-profile" src="http://localhost:8080/api/profile/image/download" />
                <Dropzone/>
            </div>
            <div className="info">
                <div className="title">
                    <p>Script Eden</p>
                </div>
                <div className="desc">{user.userId}</div>
                <div className="desc">Curious developer</div>
                <div className="desc">Tech geek</div>
            </div>

        </div>
    )
}
