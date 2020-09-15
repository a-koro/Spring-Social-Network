import React from 'react';

import {useHistory, Link} from "react-router-dom";

let results = ["Test DATA 001"];
let currentUser = {};
let userId = 0;

export const ResultsContext = React.createContext({});
export const CurrentUserContext = React.createContext({});

export const ResultsProvider = (props) => {

    const [stateResults, setStateResults] = React.useState([]);

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



// export const UserIdContext = React.createContext(0);
//
// export const UserIdProvider = (props) => {
//
//     return (
//         <UserIdContext.Provider value={0}>
//             {props.children}
//             {/* this indicates that the global store is accessible to all the child tags with MyProvider as Parent */}
//         </UserIdContext.Provider>
//     );
// };

const style = {
    objectFit: 'cover',
    borderRadius: '50%',
    width: '40px',
    height: '40px'
};

// function logout(evt) {
//     evt.preventDefault();
//
//     fetch("/logout",
//         {
//             method: 'GET',
//             credentials: "include"
//         }).then(response => {
//         window.location.href = "http://localhost:8080/";
//     });
//
//     console.log("You have been logged out.");
// }

function Navbar() {

    const [username, setUsername] = React.useState(" ");
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
            });
    }

    React.useEffect(() => {
        getCurrentUser();
        // saveCurrentUserId()
    }, []);


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
                console.log("data from search", data);
                console.log(results[0].email);
                console.log(results[0].image);
            });
        evt.target.search.value = "";
    }

    const isInitialMount = React.useRef(true);
    React.useEffect(() => {
        if (isInitialMount.current) {
            isInitialMount.current = false;
        } else {
            history.push("/results");
        }
    }, [searchResults]);

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