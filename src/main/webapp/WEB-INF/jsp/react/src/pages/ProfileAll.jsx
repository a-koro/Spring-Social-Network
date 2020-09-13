import React, {useEffect} from 'react'
import {useLocation} from 'react-router-dom'
import DataServices from "../services/DataServices";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faUserPlus} from "@fortawesome/free-solid-svg-icons";
import {faUserFriends} from "@fortawesome/free-solid-svg-icons";


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
    const [userRel, setUserRel] = React.useState(null);
    const [update, setUpdate] = React.useState(0);

    useEffect(() => {
        getCurrentRelationship()
        getCurrentUser()
        // console.log(props.myUserId)
        // console.log(location.state.detail);
    }, [update]);

    function getCurrentUser() {
        DataServices.getCurrentProfile(location.state.detail).then(
            response => {
                console.log("user in profile : ", response.data);
                setUser(response.data);
            }
        ).catch(error => {
            console.log(error.response)
        });
    }

    function getCurrentRelationship() {
        DataServices.getCurrentRelationship(props.myUserId, location.state.detail).then(
            response => {
                console.log("friendship status : ", response.data)
                console.log(response.data.id.userFirstId)
                setUserRel(response.data)
                // setUserIds(response.data.id)
            }
        ).catch(error => {
            console.log(error.response)
        });
    }

    const deleteRel = () => {
        DataServices.deleteRelationship(props.myUserId, location.state.detail).then(
            () => {
                console.log("Relationship deleted")
                setUpdate(1);
            }
        )
    }

    function ifStatements() {

        // if (props.myUserId > location.state.detail) {
        //     const temp = props.myUserId;
        //     props.myUserId = location.state.detail;
        //     location.state.detail = temp;
        // }

        if (userRel) {
            if (userRel.id.userFirstId === -1) {
                return "noRelationship"
            } else if (userRel.friends === true) {
                return "friends";
            } else if (props.myUserId < location.state.detail) {
                if (userRel.pendingFirstSecond) {
                    return "pendingFriendRequest";
                }
            } else if (userRel.pendingSecondFirst) {
                return "pendingFriendRequest"
            }
        }
        return false;
    }


    return (
        <>
            {console.log(ifStatements())}
            <div className="col-md-6 col-12 offset-md-3 offset-0">
                <div id="cssSelector">
                    <div className="card hovercard">
                        <div style={background}>
                        </div>
                        <div className="avatar">
                            <img style={style} alt="image-profile" src={url}/>
                        </div>
                        <div className="info">
                            <div className="title">
                                {ifStatements() === "noRelationship" &&
                                <div className="StartingDiv">
                                    <button>
                                        <FontAwesomeIcon id="AddFriendIcon" icon={faUserPlus}/>
                                        <span className="InnerSpan">Add Friend</span>
                                    </button>
                                </div>}
                                {ifStatements() === "friends" &&
                                <div className="StartingDiv">
                                    <button>
                                        <FontAwesomeIcon id="AddFriendIcon" icon={faUserFriends}/>
                                        <span className="InnerSpan">&#10004; Friends</span>
                                    </button>
                                    <button id="removeB" onClick={deleteRel}>&#x2717; Remove Friend</button>
                                </div>}
                                {ifStatements() === "pendingFriendRequest" &&
                                <div className="StartingDiv">
                                    <button>
                                        <FontAwesomeIcon id="AddFriendIcon" icon={faUserFriends}/>
                                        <span className="InnerSpan">&#10004; Request Pending</span>
                                    </button>
                                </div>}


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

