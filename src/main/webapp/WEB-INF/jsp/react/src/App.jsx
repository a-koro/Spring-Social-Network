import React from 'react';
import {Route, Switch, BrowserRouter, Redirect} from 'react-router-dom';
import Navbar, {ResultsProvider, UserIdProvider} from './components/Navbar';
import NewsFeed from './components/NewsFeed';
import Search from './components/Search';
import Profile from "./pages/Profile";
import Connections from "./components/Connections";
import FriendsProfilePage from "./pages/FriendsProfilePage";
import Chat from "./pages/Chat";
//import ConnectionsPage from './pages/ConnectionsPage';
import SinglePost from "./components/SinglePost";
import GlobalContext from "./contexts/GlobalContext";
import Messenger from "./components/Messenger";

const SearchContext = React.createContext({});

function App(props) {

    const [authenticatedUser, setAuthenticatedUser] = React.useState({});
    const [activeChat, setActiveChat] = React.useState({});
    const [chatMessages, setChatMessages] = React.useState([]);
    const [connections, setConnections] = React.useState([]);

    return (
        <div className="container-fluid p-0">
            <BrowserRouter>
                <GlobalContext.Provider value={{
                    authenticatedUser,
                    setAuthenticatedUser,
                    activeChat,
                    setActiveChat,
                    chatMessages,
                    setChatMessages,
                    connections,
                    setConnections
                }}>
                    <Navbar/>
                    <div className="row m-0">
                        <Switch>
                            <ResultsProvider>
                                <Redirect from="/index.html" to="/" exact/>
                                <Route path="/results" component={Search}/>
                                <Route exact path="/" render={(props) => <NewsFeed {...props}/>}/>
                                <Route path="/profile" component={Profile}/>
                                <Route path="/connections" component={Connections}/>
                                <Route path="/profileAll" component={() => <FriendsProfilePage/>}/>
                                <Route path="/post" component={SinglePost}/>
                                {/*<Route exact path="/chat" render={(props) => <Chat {...props} />}/>*/}
                                <Route path="/chat" component={Messenger}/>
                            </ResultsProvider>
                        </Switch>
                    </div>
                </GlobalContext.Provider>
            </BrowserRouter>
        </div>
    );
}

export default App;
