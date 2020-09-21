import React from 'react';
import Post from './Post';
import Contact from './Contact';
import PostForm from './PostForm';
import {CurrentUserProvider} from "./Navbar";
import { loggedInUser } from "../atom/globalState";
import DataServices from '../services/DataServices';
import { useRecoilState } from "recoil";

let postsForContext = [];
let connections = [];

export const ConnectionsContext = React.createContext([]);

export const ConnectionsProvider = (props) => {
    return (
        <ConnectionsContext.Provider value={connections}>
            {props.children}
        </ConnectionsContext.Provider>
    );
};

export const PostsContext = React.createContext([]);

export const PostsProvider = (props) => {
    return (
        <PostsContext.Provider value={postsForContext}>
            {props.children}
        </PostsContext.Provider>
    );
};


const style = {
    height: "100vh",
    position: "fixed",
    overflow: "scroll"
};

const styleBar = {
    height: "100%",
    overflowY: "scroll"
};

function NewsFeed(props) {

    const [items, setItems] = React.useState([]);
    const [posts, setPosts] = React.useState([]);
    const [value, setValue] = React.useState(true);

    const [user, setUser] = useRecoilState(loggedInUser);
    React.useEffect(() => {

        loadCurrentUser();
      }, []);

      function loadCurrentUser() {
        DataServices.getCurrentUser().then(
            response => {
                setUser(response.data);
            }
        ).catch(error => { console.log(error.response) });
    }

    React.useEffect(() => {
            fetch('http://localhost:8080/api/newsFeed')
                .then(response => response.json())
                .then(data => {
                    setItems(data.friends);
                    setPosts(data.posts);
                    postsForContext = data.posts;
                    connections = data.friends;
                });
        }, [value]
    );

    function closeModal() {
        document.getElementById("closeButton").click();
    }

    return (
        <>
            <div className="col-md-3 col-12 d-none d-md-block">
                <div>{items.map((item) => (
                    <Contact  userFriendId = {item.userSecondId} username={ item.firstName + " " + item.lastName}/>
                ))}
                </div>
            </div>
            <div className="col-md-6 col-12">

                <button type="button" class="btn btn-primary mt-2" data-toggle="modal" data-target="#exampleModal">
                    New Post
                </button>

                <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">New Post</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close" id="closeButton">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <PostForm closeModal={closeModal} value={value} setValue={setValue}/>
                            </div>
                        </div>
                    </div>
                </div>
                <CurrentUserProvider>
                {posts.map((post) => (
                    <Post post={post} value={{value:value,setValue:setValue}}/>
                ))}
                </CurrentUserProvider>
            {/*    <Post username="Rick Sanchez"*/}
            {/*          post="After having been missing for nearly 20 years, Rick Sanchez suddenly arrives at daughter Beth's doorstep to move in with her and her family. Although Beth welcomes Rick into her home, her husband, Jerry, isn't as happy about the family reunion."/>*/}
            {/*    <Comment username="Alex Koro"*/}
            {/*             post="After having been missing for nearly 20 years, Rick Sanchez suddenly arrives at daughter Beth's doorstep to move in with her and her family."/>*/}
            </div>
        </>
    );
}

export default NewsFeed;