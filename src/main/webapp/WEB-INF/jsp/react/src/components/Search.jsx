import React from 'react';
import { ResultsContext } from './Navbar';
import ResultUser from './ResultUser';
import context from 'react-bootstrap/esm/AccordionContext';
import Contact from "./Contact";
import '../css/fixedNavBar.css';

function Search(props) {

    return (
        <>
            <div className="col-sm-6 offset-sm-3 col-xs-12 marginFromTopForFixedNavbar">
                <h4 className="my-4">
                    Search results
                </h4>
                <ResultsContext.Consumer>
                    {(context) => (
                        context.map((item) => (
                            <ResultUser username={item.firstName + " " + item.lastName} avatar={item.userId} onClick={() => {console.log(item)}}/>
                        ))
                    )}
                </ResultsContext.Consumer>
            </div>
        </>
    );
}

export default Search;