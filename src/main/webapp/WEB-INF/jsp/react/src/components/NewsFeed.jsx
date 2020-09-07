import React from 'react';
import Post from './Post';
import Comment from './Comment';
import Contact from './Contact';
import PostForm from './PostForm';

const style = {
    height: "100vh",
    position: "relative",
    overflowY: "scroll"
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
                <PostForm/>
                <Post username="Rick Sanchez"
                      post="After having been missing for nearly 20 years, Rick Sanchez suddenly arrives at daughter Beth's doorstep to move in with her and her family. Although Beth welcomes Rick into her home, her husband, Jerry, isn't as happy about the family reunion."/>
                <Comment username="Alex Koro"
                         post="After having been missing for nearly 20 years, Rick Sanchez suddenly arrives at daughter Beth's doorstep to move in with her and her family."/>
            </div>
        </>
    );
}

export default NewsFeed;