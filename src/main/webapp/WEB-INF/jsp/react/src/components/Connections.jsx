import React from 'react';
import { ConnectionsContext } from './NewsFeed';
import ConnectionCard from './ConnectionCard';
import '../css/fixedNavBar.css';

function Connections() {

    const friends = React.useContext(ConnectionsContext);

    return (
        <>
            {console.log(friends)}
            <div className="col-sm-6 offset-sm-3 col-xs-12 marginFromTopForFixedNavbar">
                <h4>
                    Connections
                </h4>
                <ConnectionsContext.Consumer>
                    {(context) => (
                        context.map((item) => (
                            <ConnectionCard username={item.firstName + " " + item.lastName} userId={item.userId} />
                        ))
                    )}
                </ConnectionsContext.Consumer>
            </div>
        </>
    );
}

export default Connections;