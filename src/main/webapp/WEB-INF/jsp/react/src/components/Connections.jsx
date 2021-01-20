import React from 'react';
// import { ConnectionsContext } from './NewsFeed';
import ConnectionCard from './ConnectionCard';
import '../css/fixedNavBar.css';
import GlobalContext from "../contexts/GlobalContext";

function Connections() {

    // const friends = React.useContext(ConnectionsContext);
    const {connections} = React.useContext(GlobalContext);

    return (
        <>
            <div className="col-sm-6 offset-sm-3 col-xs-12 marginFromTopForFixedNavbar">
                <h4 className="my-4">
                    Connections
                </h4>
                {/*<ConnectionsContext.Consumer>*/}
                {/*    {(context) => (*/}
                {/*        context.map((item) => (*/}
                {/*            <ConnectionCard username={item.firstName + " " + item.lastName} userId={item.userId} />*/}
                {/*        ))*/}
                {/*    )}*/}
                {/*</ConnectionsContext.Consumer>*/}
                {connections.map((item) => (
                    <ConnectionCard username={item.firstName + " " + item.lastName} userId={item.userId}/>
                ))}
            </div>
        </>
    );
}

export default Connections;