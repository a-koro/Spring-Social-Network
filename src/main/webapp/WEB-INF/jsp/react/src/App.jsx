import React from 'react';
import { Route, Switch, BrowserRouter, Redirect } from 'react-router-dom';
import Navbar, {ResultsProvider, UserIdProvider} from './components/Navbar';
import NewsFeed from './components/NewsFeed';
import Search from './components/Search';
import Profile from "./pages/Profile";
import Connections from "./components/Connections";
import FriendsProfilePage from "./pages/FriendsProfilePage";
import ConnectionsPage from './pages/ConnectionsPage';

const SearchContext = React.createContext({});

function App() {

  return (

      <div className="container-fluid p-0">
          <BrowserRouter>
              <Navbar />
              <div className="row m-0">
              <Switch>
                  <ResultsProvider>
                          <Redirect from="/index.html" to="/" exact />
                          <Route path="/results" component={Search}/>
                          <Route path="/" exact component={NewsFeed}/>
                          <Route path="/profile" component={Profile}/>
                          <Route path="/connections" component={ConnectionsPage}/>
                          <Route path="/profileAll" component={ () => <FriendsProfilePage/>}/>
                  </ResultsProvider>
              </Switch>
              </div>
          </BrowserRouter>
      </div>
  );
}

export default App;
