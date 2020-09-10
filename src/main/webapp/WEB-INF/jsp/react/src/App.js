import React from 'react';
import { Route, Switch, BrowserRouter, Redirect } from 'react-router-dom';
import Navbar, { ResultsProvider } from './components/Navbar';
import NewsFeed from './components/NewsFeed';
import Search from './components/Search';
import Profile from "./pages/Profile";
import Chat from "./pages/Chat";

const SearchContext = React.createContext({});

function App(props) {

  return (
      <div className="container-fluid p-0">
          {/* <Navbar /> */}

          {/* <div>{items.map((item) => (
      <Contact username={item.first_name+" "+item.last_name} avatar={item.avatar}/>
    ))}</div> */}
          {/* <Post username="Rick Sanchez" post="After having been missing for nearly 20 years, Rick Sanchez suddenly arrives at daughter Beth's doorstep to move in with her and her family. Although Beth welcomes Rick into her home, her husband, Jerry, isn't as happy about the family reunion." />
      <Comment username="Alex Koro" post="After having been missing for nearly 20 years, Rick Sanchez suddenly arrives at daughter Beth's doorstep to move in with her and her family." /> */}
          {/* <NewsFeed/> */}
          <BrowserRouter>
              <Navbar />
              <div className="row m-0">
              <Switch>
                  <ResultsProvider>
                      <Redirect from="/index.html" to="/" exact />
                      <Route path="/results" component={Search}/>
                      <Route exact path="/" render={(props)=><NewsFeed {...props}/>} />
                      <Route path="/profile" component={Profile}/>
                      <Route exact path="/chat" render={(props) => <Chat {...props} />} />
                  </ResultsProvider>
              </Switch>
              </div>
          </BrowserRouter>
      </div>
  );
}

export default App;
