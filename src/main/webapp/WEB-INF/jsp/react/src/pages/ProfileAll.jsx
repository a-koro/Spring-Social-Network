import React, {useEffect} from 'react'
import {useLocation} from 'react-router-dom'
import DataServices from "../services/DataServices";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faUserPlus} from "@fortawesome/free-solid-svg-icons";
import {faUserFriends} from "@fortawesome/free-solid-svg-icons";
import { CurrentUserContext } from "../components/Navbar";
import { PostsContext } from "../components/NewsFeed";
import Post from '../components/Post';


function ProfileAll(props) {


    const style = {
        objectFit: 'cover',
        borderRadius: '50%',
        width: '250px',
        height: '250px'
    };

    const location = useLocation();
    const url = "/api/profile/searchUsers/" + location.state.detail
    const backgroundUrl = '/api/profile/image-background/download/' + location.state.detail

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
    const currentUser = React.useContext(CurrentUserContext);
    const friendsPosts = React.useContext(PostsContext);

    useEffect(() => {
        getCurrentRelationship()
        getCurrentUser()
        console.log(friendsPosts);
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
        DataServices.getCurrentRelationship(currentUser.userId, location.state.detail).then(
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
        DataServices.deleteRelationship(currentUser.userId, location.state.detail).then(
            () => {
                console.log("Relationship deleted")
                setUpdate(1);
            }
        )
    }

    const acceptRel = () => {
        DataServices.acceptRelationship(currentUser.userId, location.state.detail).then (
            () => {
                console.log("Friend request Accepted")
                setUpdate(3);
            }
        )
    }

    const createRel = () => {
        DataServices.createRelationship(currentUser.userId, location.state.detail).then(
            () => {
                console.log("Friend request sent")
                setUpdate(2);
            }
        )
    }

    function ifStatements() {

        if (userRel) {
            if (userRel.id.userFirstId === -1) {
                return "noRelationship"
            } else if (userRel.friends === true) {
                return "friends";
            } else if (currentUser.userId < location.state.detail) {
                if (userRel.pendingFirstSecond) {
                    return "pendingFriendRequest";
                } else {
                    return "respondFriendRequest"
                }
            } else if (userRel.pendingSecondFirst) {
                return "pendingFriendRequest"
            } else {
                return "respondFriendRequest"
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
                                    <button onClick={createRel}>
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
                                    <button title="Click to cancel Friend Request">
                                        <FontAwesomeIcon id="AddFriendIcon" icon={faUserFriends}/>
                                        <span className="InnerSpan" onClick={deleteRel}>Request Pending</span>
                                    </button>
                                </div>}
                                {ifStatements() === "respondFriendRequest" &&
                                <div className="StartingDiv">
                                    <button>
                                        <FontAwesomeIcon id="AddFriendIcon" icon={faUserFriends}/>
                                        <span className="InnerSpan">Respond to Request</span>
                                    </button>
                                    <div>
                                        <button id="acceptB" onClick={acceptRel}>Accept</button>
                                        <button id="declineB" onClick={deleteRel}>Decline</button>
                                    </div>
                                </div>}
                                <h3>{user.firstName} {user.lastName}</h3>
                            </div>
                            <div className="desc">Birthday {new Date(user.birthday).toLocaleDateString("en-GB")}</div>
                        </div>
                    </div>
                </div>
            </div>
                <div className="col-md-6 col-12 offset-md-3">
                    <PostsContext.Consumer>
                        {(context) => (
                            context.map((post) => (
                                (post.user.userId === user.userId) &&
                                    <Post post={post}/>
                            ))
                        )}
                    </PostsContext.Consumer>
                </div>
        </>
    )

}


export default ProfileAll;

