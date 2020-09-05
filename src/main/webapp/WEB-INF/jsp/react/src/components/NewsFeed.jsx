import React from 'react';
import Post from './Post';
import Comment from './Comment';
import Contact from './Contact';

const style = {
    height: "100vh",
    position: "fixed",
    overflow: "scroll"
};

const styleBar = {
    height: "100%",
    overflowY: "scroll"
};

let alekos = "alekos";

function NewsFeed(props) {

    const [items, setItems] = React.useState([]);

    React.useEffect(() => {
            fetch('http://localhost:8080/api/user')
                .then(response => response.json())
                .then(data => setItems(data));
        }, []
    );

    return (
        <div className="row" style={style}>
            <div className="col-2" style={styleBar}>
                <div>{items.map((item) => (
                    <Contact username={item.userSecondId + " " + item.firstName + " " + item.lastName}/>
                ))}
                </div>
            </div>
            <div className="col-9">
                <Post username="Rick Sanchez"
                      post="After having been missing for nearly 20 years, Rick Sanchez suddenly arrives at daughter Beth's doorstep to move in with her and her family. Although Beth welcomes Rick into her home, her husband, Jerry, isn't as happy about the family reunion."/>
                <Comment username="Alex Koro"
                         post="After having been missing for nearly 20 years, Rick Sanchez suddenly arrives at daughter Beth's doorstep to move in with her and her family."/>
            </div>
        </div>
    );
}

export default NewsFeed;