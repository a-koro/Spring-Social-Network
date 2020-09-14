import React, {useEffect} from 'react'
import {useLocation} from 'react-router-dom'
import ProfileBg from "../components/ProfileBg";
import DropzoneBg from "../components/DropzoneBg";
import Dropzone from "../components/Dropzone";
import DataServices from "../services/DataServices";
import { ResultsContext } from "../components/Navbar"

function ProfileAll(props) {

    const style = {
        objectFit: 'cover',
        borderRadius: '50%',
        width: '250px',
        height: '250px'
    };

    const location = useLocation();
    const url = "http://localhost:8080/api/profile/searchUsers/" + location.state.detail
    const backgroundUrl = 'http://localhost:8080/api/profile/image-background/download/' + location.state.detail

    const background = {
        background: `url(${backgroundUrl})`,
        backgroundSize: "cover",
        height: "300px"
    };

    const bgImageStyle = {
        height: '340px'
    };

    const [user, setUser] = React.useState({});
    const [userRel, setUserRel] = React.useState({});
    const [loggedInUser, setLoggedInUser] = React.useState([]);

    useEffect(() => {
        getLoggedInUser()
        getCurrentRelationship()
        getCurrentUser()
        // getCurrentRelationship()
        // console.log(location.pathname); // result: '/secondpage'
        // console.log(props.myUserId)
        console.log(location.state.detail); // result: 'some_value'

        // console.log(location.currentUserId.id)
    }, []);

    function getCurrentUser() {
        DataServices.getCurrentProfile(location.state.detail).then(
            response => {
                console.log("user in profile : ", response.data);
                setUser(response.data);
            }
        ).catch(error => { console.log(error.response) });
    }

    function getCurrentRelationship () {
        DataServices.getCurrentRelationship(loggedInUser.userId, location.state.detail).then(
            response => {
                console.log("friendship status : ", response.data)
            }
        ).catch(error => { console.log(error.response) });
    }

    function getLoggedInUser() {
        fetch("/userDetails", {
            method: 'GET',
            credentials: "include"
        })
            .then(response => response.json())
            .then(data => {
                setLoggedInUser(data);
            });
    }

    return (
        <>
            <div className="col-md-6 col-12 offset-md-3 offset-0">
                <div id="cssSelector">
                    <div className="card hovercard">
                        <div style={background}>
                            {/*<DropzoneBg/>*/}
                        </div>
                        <div className="avatar">
                            <img style={style} alt="image-profile" src={url} />
                            {/*<Dropzone />*/}
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
        </>
    )

}


export default ProfileAll;

