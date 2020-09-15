import React from 'react';
import NewsFeed, {ConnectionsProvider} from '../components/NewsFeed';
import Connections from "../components/Connections";

function ConnectionsPage() {
    return (
        <>
            <ConnectionsProvider>
                <Connections/>
            </ConnectionsProvider>
        </>
    );
}

export default ConnectionsPage;