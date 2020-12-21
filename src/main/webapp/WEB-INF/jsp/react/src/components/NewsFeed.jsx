import React from 'react';
import Post from './Post';
import Contact from './Contact';
import PostForm from './PostForm';
import {CurrentUserProvider} from "./Navbar";
import { loggedInUser } from "../atom/globalState";
import DataServices from '../services/DataServices';
import { useRecoilState } from "recoil";
import Axios from 'axios';
import ArticlePrev from "./ArticlePrev";

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
    const [loadingSpinner, setLoadingSpinner] = React.useState(true);
    const [loadingArticleSpinner, setLoadingArticleSpinner] = React.useState(true);

    const [trending, setTrending] = React.useState([]);

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

    async function fetchTrendingArticles() {
        try {
            await Axios.get('https://mern-articlomaric-app.herokuapp.com/api/getTrendingArticles')
                .then((response) => {
                    setTrending(response.data);
                    setLoadingArticleSpinner(false);
                });

        } catch(err) {
            console.log(err);
        }
    }

    React.useEffect(() => {
            fetch('/api/newsFeed')
                .then(response => response.json())
                .then(data => {
                    setItems(data.friends);
                    setPosts(data.posts);
                    postsForContext = data.posts;
                    connections = data.friends;
                    setLoadingSpinner(false);
                });
        }, [value]
    );

      React.useEffect(() => {
        fetchTrendingArticles();
      },[]);

    function closeModal() {
        document.getElementById("closeButton").click();
    }

    return (
        <>
            <div className="col-md-3 col-12 d-none d-md-block">
                <div className="sticky-top">{items.map((item) => (
                    <Contact  userFriendId = {item.userId} username={ item.firstName + " " + item.lastName}/>
                ))}
                </div>
            </div>
            <div className="col-md-6 col-12">

                <button type="button" className="btn btn-primary mt-2" data-toggle="modal" data-target="#exampleModal">
                    New Post
                </button>

                { loadingSpinner &&
                    <div className="text-center mt-5">
                        <div className="spinner-border" role="status">
                            <span className="sr-only">Loading...</span>
                        </div>
                    </div>
                }

                <div className="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div className="modal-dialog" role="document">
                        <div className="modal-content">
                            <div className="modal-header">
                                <h5 className="modal-title" id="exampleModalLabel">New Post</h5>
                                <button type="button" className="close" data-dismiss="modal" aria-label="Close" id="closeButton">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div className="modal-body">
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
            <div className="col-md-3 d-lg-block d-none">
                <div className="sticky-top">
                    <h5 className="text-right mt-5">Trending on Atricl-O-matic</h5>
                    { loadingArticleSpinner &&
                    <div className="text-center mt-5">
                        <div className="spinner-border" role="status">
                            <span className="sr-only">Loading...</span>
                        </div>
                    </div>
                    }
                    {trending.map((trend) => {
                        return <><hr/><ArticlePrev key={trend._id} article={trend}/></>
                    })}
                </div>
            </div>
        </>
    );
}

export default NewsFeed;