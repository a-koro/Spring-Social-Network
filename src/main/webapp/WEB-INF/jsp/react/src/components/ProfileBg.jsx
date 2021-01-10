import React from 'react'
import Axios from 'axios';
import './profileBg.css';
import '../css/fixedNavBar.css';

import DataServices from '../services/DataServices';
import Dropzone from "./Dropzone";
import DropzoneBg from './DropzoneBg';
import Post from "./Post";
import {CurrentUserContext} from "./Navbar";


export default function ProfileBg(props) {

    const style = {
        objectFit: 'cover',
        borderRadius: '50%',
        width: '250px',
        height: '250px'
    };


    const background = {
        background: `url("/api/profile/image-background/download")`,
        backgroundSize: "cover",
        height: "300px"
    };

    const bgImageStyle = {
        height: '340px'
    };

    const [user, setUser] = React.useState({});
    const [posts, setPosts] = React.useState([]);

    function getCurrentUser() {
        DataServices.getCurrentUser().then(
            response => {
                console.log("user in profile : ", response.data);
                setUser(response.data);
                getProfilePosts(response.data.userId);
            }
        ).catch(error => {
            console.log(error.response)
        });
    }

    function getProfilePosts(userId) {
        Axios.get(`/post/getUsersPosts/${userId}`)
            .then(response => {
                setPosts(response.data);
            }).catch(err => {
                console.log(err);
        });
    }

    React.useEffect(() => {
        getCurrentUser();
    }, []);

    return (
        <>
            <div className="col-md-6 col-12 offset-md-3 offset-0 marginFromTopForFixedNavbar">
                <div id="cssSelector">
                    <div className="card hovercard">
                        <div style={background}>
                            <DropzoneBg/>
                        </div>
                        <div className="avatar">
                            <img style={style} alt="image-profile" src="/api/profile/image/download"/>
                            <Dropzone/>
                        </div>
                        <div className="info">
                            <div className="title">
                                <h3>{user.firstName} {user.lastName}</h3>
                            </div>
                            <div className="desc">Birthday {new Date(user.birthday).toLocaleDateString("en-GB")}</div>
                        </div>
                    </div>
                </div>
            </div>
            <div className="col-md-6 col-12 offset-md-3">
                {posts.map((post) => (
                    <Post post={post}/>
                ))}
            </div>
        </>
    )
}
