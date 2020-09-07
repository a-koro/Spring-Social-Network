import React from 'react';
import { ResultsContext } from './Navbar';
import ResultUser from './ResultUser';
import context from 'react-bootstrap/esm/AccordionContext';
import Contact from "./Contact";

function Search(props) {

    return (
        <div>
            <div className="col-sm-6 offset-sm-3 col-xs-12 mt-3">
                <h4>
                    Search results
                </h4>
                <ResultsContext.Consumer>
                    {(context) => (
                        context.map((item) => (
                            <ResultUser username={item.firstName + " " + item.lastName} avatar={item.url} />
                        ))
                    )}
                </ResultsContext.Consumer>
            </div>
        </div>
    );
}

export default Search;