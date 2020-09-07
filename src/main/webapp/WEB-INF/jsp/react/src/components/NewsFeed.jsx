import React from 'react';
import Post from './Post';
import Comment from './Comment';
import Contact from './Contact';
import PostForm from './PostForm';

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

    React.useEffect(() => {
            fetch('http://localhost:8080/api/user')
                .then(response => response.json())
                .then(data => setItems(data));
        }, []
    );

    return (
        <>
            <div className="col-md-3 col-12 d-none d-md-block">
                <div>{items.map((item) => (
                    <Contact username={item.userSecondId + " " + item.firstName + " " + item.lastName}/>
                ))}
                </div>
            </div>
            <div className="col-md-6 col-12">

                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
                    New Post
                </button>

                <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">New Post</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <PostForm/>
                            </div>
                        </div>
                    </div>
                </div>
                <Post username="Rick Sanchez"
                      post="After having been missing for nearly 20 years, Rick Sanchez suddenly arrives at daughter Beth's doorstep to move in with her and her family. Although Beth welcomes Rick into her home, her husband, Jerry, isn't as happy about the family reunion."/>
                <Comment username="Alex Koro"
                         post="After having been missing for nearly 20 years, Rick Sanchez suddenly arrives at daughter Beth's doorstep to move in with her and her family."/>
            </div>
        </>
    );
}

export default NewsFeed;