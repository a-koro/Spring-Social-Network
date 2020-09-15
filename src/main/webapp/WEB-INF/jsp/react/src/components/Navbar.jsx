import React from 'react';
import Requests from './Requests'
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faSmileBeam, faUserFriends} from "@fortawesome/free-solid-svg-icons";


import {useHistory, Link} from "react-router-dom";
import DataServices from "../services/DataServices";

let results = ["Test DATA 001"];
let currentUser = {};
let userId = 0;

export const ResultsContext = React.createContext({});
export const CurrentUserContext = React.createContext({});

export const ResultsProvider = (props) => {

    return (
        <ResultsContext.Provider value={results}>
            {props.children}
            {/* this indicates that the global store is accessible to all the child tags with MyProvider as Parent */}
        </ResultsContext.Provider>
    );
};

export const CurrentUserProvider = (props) => {
    return (
        <CurrentUserContext.Provider value={currentUser}>
            {props.children}
        </CurrentUserContext.Provider>
    );
};

const style = {
    objectFit: 'cover',
    borderRadius: '50%',
    width: '40px',
    height: '40px'
};

function Navbar() {

    const [username, setUsername] = React.useState(" ");
    const [userId, setUserId] = React.useState(0);
    const [friendReq, setFriendReq] = React.useState([]);
    const [updateValue, setUpdateValue] = React.useState(0);
    const [searchResults, setSearchResults] = React.useState([]);
    const history = useHistory();

    function getCurrentUser() {

        fetch("/userDetails", {
            method: 'GET',
            credentials: "include"
        })
            .then(response => response.json())
            .then(data => {
                setUsername(data.firstName + " " + data.lastName);
                currentUser = data;
                // userId = data.userId
                // setUserId(data.userId)
                // console.log("Inside promise" + data.userId);
                // history.push({
                //     path: "/profileAll",
                //     currentUserId: {id: data.userId}
                // });
                setUserId(data.userId);
            });
    }

    React.useEffect(() => {
        getCurrentUser();
        getAllRequests();
        // saveCurrentUserId()
    }, [userId, updateValue]);


    // const saveCurrentUserId = () => {
    //     console.log(userId)
    //     history.push({
    //         path: "/profileAll",
    //         currentUserId: {id: 3}
    //     });
    // }

    function fetchUsers(evt) {
        evt.preventDefault();

        let searchBar = document.getElementById("searchBar");

        fetch("/searchUsers",
            {
                method: 'GET',
                credentials: "include",
                headers: {
                    'input': evt.target.search.value
                },
            })
            .then(response => response.json())
            .then(data => {
                setSearchResults(data);
                results = data;
                console.log(data);
                console.log(currentUser);
            });
        evt.target.search.value = "";
    }

    function getAllRequests() {
        console.log(userId)
        if (userId) {
            DataServices.getPendingRequests(userId).then(
                response => {
                    console.log("All friend requests", response.data);
                    setFriendReq(response.data);
                }
            ).catch(error => {
                console.log("No friend requests" + error.response)
            });
        }
    }

    const isInitialMount = React.useRef(true);
    React.useEffect(() => {
        if (isInitialMount.current) {
            isInitialMount.current = false;
        } else {
            history.push("/results");
        }
    }, [searchResults]);

    const stopDefault = e => {
        e.preventDefault();
        e.stopPropagation();
    }

    return (
        <>
            {/*{saveCurrentUserId()}*/}
            <nav className="navbar navbar-expand-lg navbar-light bg-light">
                <a className="navbar-brand" href="/">
                    {/*<img src="/img/logo.jpg" height="54px" alt="Connector Logo" className="m-0"/>*/}
                    Connector
                </a>
                <button className="navbar-toggler" type="button" data-toggle="collapse"
                        data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                        aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>

                <div className="collapse navbar-collapse" id="navbarSupportedContent">
                    <form className="form-inline my-2 my-lg-0 mr-auto" onSubmit={fetchUsers}>
                        <input id="searchBar" className="form-control autocomplete mr-sm-2" name="search" type="search"
                               placeholder="Search" aria-label="Search"/>
                    </form>
                    {/*<div style={{width: "400px"}}></div>*/}
                    <ul className="navbar-nav">
                        <li className="nav-item active">
                            <Link to="/" className="nav-link">Feed</Link>
                        </li>
                        <li className="nav-item dropdown">
                            <a className="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Notifications
                            </a>
                            <div className="dropdown-menu" onClick={stopDefault} aria-labelledby="navbarDropdown">
                                {friendReq.length !==0 ?
                                    friendReq.map((item) => (
                                        <div onClick={stopDefault} className="dropdown-item">
                                            <Requests requesterInfo={{
                                                myId: userId,
                                                id: item.userId,
                                                username: item.firstName + " " + item.lastName
                                            }} handleUpdate={setUpdateValue}/>
                                        </div>
                                    )) :
                                    <div className="card my-2 border-0">
                                        <div className="card-body d-flex flex-row pt-2 pb-0 px-1">
                                            <h6 className="card-text align-bottom text-center">
                                                Everything's up to date
                                                <FontAwesomeIcon className="ml-2" icon={faSmileBeam}/>
                                            </h6>
                                        </div>
                                    </div>
                                }
                            </div>
                        </li>
                        <li className="nav-item active">
                            <a className="nav-link" href="#">Messages</a>
                        </li>
                        <li className="nav-item active">
                            <a className="nav-link" href="#">Conections</a>
                        </li>
                        <li>
                            <img style={style} src="http://localhost:8080/api/profile/image/download"
                                 className="avatar rounded-circle ml-3"
                                 alt="Cinque Terre"/>
                        </li>
                        <li className="nav-item">
                            <Link to="/profile" className="nav-link">{username}</Link>
                        </li>
                        <li className="nav-item">
                            <form action="https://www.paypal.com/cgi-bin/webscr" method="post" target="_top">
                                <input type="hidden" name="cmd" value="_s-xclick" />
                                <input type="hidden" name="hosted_button_id" value="B6MCPHFEUW3FN" />
                                <input id="donatePaypal" type="image" src="https://www.paypalobjects.com/en_US/i/btn/btn_donate_LG.gif" border="0" name="submit" title="PayPal - The safer, easier way to pay online!" alt="Donate with PayPal button" />
                                <img alt="" border="0" src="https://www.paypal.com/en_GR/i/scr/pixel.gif" width="1" height="1" />
                            </form>
                        </li>
                        <li>
                            <a className="nav-link" href="/logout">Logout</a>
                        </li>
                    </ul>
                </div>
            </nav>
        </>
    );
}

export default Navbar;