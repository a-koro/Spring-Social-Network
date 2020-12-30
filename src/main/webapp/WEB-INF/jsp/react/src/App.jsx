import React from 'react';
import { Route, Switch, BrowserRouter, Redirect } from 'react-router-dom';
import Navbar, {ResultsProvider, UserIdProvider} from './components/Navbar';
import NewsFeed from './components/NewsFeed';
import Search from './components/Search';
import Profile from "./pages/Profile";
import Connections from "./components/Connections";
import FriendsProfilePage from "./pages/FriendsProfilePage";
import Chat from "./pages/Chat";
import ConnectionsPage from './pages/ConnectionsPage';
import SinglePost from "./components/SinglePost";

const SearchContext = React.createContext({});

function App(props) {

  return (

      <div className="container-fluid p-0">
          <BrowserRouter>
              <Navbar />
              <div className="row m-0">
              <Switch>
                  <ResultsProvider>
                      <Redirect from="/index.html" to="/" exact />
                      <Route path="/results" component={Search}/>
                      <Route exact path="/" render={(props)=><NewsFeed {...props}/>} />
                      <Route path="/profile" component={Profile}/>
                      <Route path="/connections" component={ConnectionsPage}/>
                      <Route path="/profileAll" component={ () => <FriendsProfilePage/>}/>
                      <Route path="/post" component={SinglePost}/>
                      <Route exact path="/chat" render={(props) => <Chat {...props} />} />
                  </ResultsProvider>
              </Switch>
              </div>
          </BrowserRouter>
      </div>
  );
}

export default App;
