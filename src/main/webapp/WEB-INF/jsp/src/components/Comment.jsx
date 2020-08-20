import React from 'react';
import { faGlassCheers } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

// function fetchData() {
//     fetch('http://localhost:8080/getcityfromcountry/7', {mode: "cors"})
//         .then(response => response.json())
//         .then(data => console.log(data));
// }

const style = {
    objectFit: 'cover',
    borderRadius: '50%',
    width: '40px',
    height: '40px'
};

function Comment(props){
    return (
        <>
        <div className="card col-md-6 col-xs-12 col-sm-8 p-0 my-2">
            <div className="card-body d-flex flex-row pt-2 pb-0 px-1">
                {/* "https://cdn.vox-cdn.com/thumbor/G8A4RF-QWQl7jItQw93r402os_0=/1400x1050/filters:format(jpeg)/cdn.vox-cdn.com/uploads/chorus_asset/file/10816041/rick_and_morty_s02_still.jpg" */}
                <img style={style} src={props.avatar} 
                    className="avatar rounded-circle mx-2" 
                    alt="Cinque Terre"/>
                <div>
                <h6 className="card-text mb-0">{props.username}</h6>
                <blockquote className="card-text p-0 m-0">
                    <p className="mb-0">{props.post}</p>
                </blockquote>
                

            </div>
            </div>
            <div className="card-body p-1">
                <div className="row">
                    <div className="col-4 m-2">
                        <FontAwesomeIcon className="mx-2" icon={faGlassCheers} />{8}
                    </div>
                    <div className="col-7 m-2 text-right">
                        <p className="card-text text-secondary"><small><i className="far fa-clock pr-2"></i>September 14, 2016</small></p>
                    </div>
                </div>
            </div>
        </div>
    </>
    );
}

export default Comment;