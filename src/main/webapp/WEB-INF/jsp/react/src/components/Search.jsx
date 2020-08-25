import React from 'react';
import { ResultsContext } from './Navbar';
import context from 'react-bootstrap/esm/AccordionContext';

function Search(props) {

    return (
        <div>
            <ResultsContext.Consumer>
                {(context) => (
                    <p>{context}</p>
                )}
            </ResultsContext.Consumer>
            <h1>
                Search Results
            </h1>
        </div>
    );
}

export default Search;