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
import DataServices from "./services/DataServices";
import Stomp from "stompjs";
import SockJS from "sockjs-client";

const SearchContext = React.createContext({});

function App(props) {

    let SockJSr = new SockJS("/ws");
    const [authenticatedUser, setAuthenticatedUser] = React.useState({});
    const [activeChat, setActiveChat] = React.useState({});
    const [chatMessages, setChatMessages] = React.useState([]);
    const [connections, setConnections] = React.useState([]);
    const [stompClient, setStompClient] = React.useState(Stomp.over(SockJSr));

    const connect = () => {
        stompClient.connect({}, onConnected, onError);
    };

    const onError = (err) => {
        console.log(err);
    };

    const onConnected = () => {
        console.log("connected");
        stompClient.subscribe(
            "/user/" + authenticatedUser.userId + "/queue/messages",
            onMessageReceived
        );
    };

    const onMessageReceived = (msg) => {
        const notification = JSON.parse(msg.body);
        console.log(msg.body);

        if (activeChat.userId === notification.userId) {
            DataServices.findChatMessage(notification.chatNotificationId).then((message) => {
                const newMessages = chatMessages;
                newMessages.push(message.data);
                setChatMessages(newMessages);
            }).catch(error => { console.log(error.response) });
        } else {
            console.log("Received a new message from " + notification.firstName + " " + notification.lastName);
        }
    };

    React.useEffect(() => {
        connect();
    },[authenticatedUser]);

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
                    setConnections,
                    stompClient
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
