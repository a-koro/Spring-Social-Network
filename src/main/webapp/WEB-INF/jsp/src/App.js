import React from 'react';
import Navbar from './components/Navbar';
import Post from './components/Post';
import Comment from './components/Comment';
import Contact from './components/Contact';
import NewsFeed from './components/NewsFeed';

function App() {

  return (
    <div className="container-fluid p-0">
      <Navbar />

    {/* <div>{items.map((item) => (
      <Contact username={item.first_name+" "+item.last_name} avatar={item.avatar}/>
    ))}</div> */}
      {/* <Post username="Rick Sanchez" post="After having been missing for nearly 20 years, Rick Sanchez suddenly arrives at daughter Beth's doorstep to move in with her and her family. Although Beth welcomes Rick into her home, her husband, Jerry, isn't as happy about the family reunion." />
      <Comment username="Alex Koro" post="After having been missing for nearly 20 years, Rick Sanchez suddenly arrives at daughter Beth's doorstep to move in with her and her family." /> */}
      <NewsFeed/>
    </div>
  );
}

export default App;
