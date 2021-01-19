import React from 'react';
import {Route, Switch, BrowserRouter, Redirect} from 'react-router-dom';
import Navbar, {ResultsProvider, UserIdProvider} from './components/Navbar';
import NewsFeed from './components/NewsFeed';
import Search from './components/Search';
import Profile from "./pages/Profile";
import Connections from "./components/Connections";
import FriendsProfilePage from "./pages/FriendsProfilePage";
import Chat from "./pages/Chat";
import ConnectionsPage from './pages/ConnectionsPage';
import SinglePost from "./components/SinglePost";
import MessengerContext from "./contexts/MessengerContext";

const SearchContext = React.createContext({});

function App(props) {

    const [koroUser, setKoroUser] = React.useState({});
    const [activeChat, setActiveChat] = React.useState({});
    const [chatMessages, setChatMessages] = React.useState([]);

    return (
        <div className="container-fluid p-0">
            <BrowserRouter>
                <MessengerContext.Provider value={{
                    koroUser,
                    setKoroUser,
                    activeChat,
                    setActiveChat,
                    chatMessages,
                    setChatMessages
                }}>
                    <Navbar/>
                    <div className="row m-0">
                        <Switch>
                            <ResultsProvider>
                                <Redirect from="/index.html" to="/" exact/>
                                <Route path="/results" component={Search}/>
                                <Route exact path="/" render={(props) => <NewsFeed {...props}/>}/>
                                <Route path="/profile" component={Profile}/>
                                <Route path="/connections" component={ConnectionsPage}/>
                                <Route path="/profileAll" component={() => <FriendsProfilePage/>}/>
                                <Route path="/post" component={SinglePost}/>
                                <Route exact path="/chat" render={(props) => <Chat {...props} />}/>
                            </ResultsProvider>
                        </Switch>
                    </div>
                </MessengerContext.Provider>
            </BrowserRouter>
        </div>
    );
}

export default App;
