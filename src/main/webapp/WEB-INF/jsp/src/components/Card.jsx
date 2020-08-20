import React from 'react';
import './Card.css';

function Card() {
    return (
        <div className="card promoting-card col-md-6">
            <div className="card-body d-flex flex-row">
                <img src="https://mdbootstrap.com/img/Photos/Avatars/avatar-8.jpg" className="rounded-circle mr-3" height="50px" width="50px" alt="avatar" />
                <div>
                    <h4 className="card-title font-weight-bold mb-2">New spicy meals</h4>
                    <p className="card-text"><i className="far fa-clock pr-2"></i>07/24/2018</p>

                </div>

            </div>
            <div className="view overlay">
                <img className="card-img-top rounded-0" src="https://mdbootstrap.com/img/Photos/Horizontal/Food/full page/2.jpg" alt="Card image cap" />
                <a href="#!">
                    <div className="mask rgba-white-slight"></div>
                </a>
            </div>
            <div className="card-body">
                <div className="collapse-content">
                    <p className="card-text collapse" id="collapseContent">Recently, we added several exotic new dishes to our restaurant menu. They come from countries such as Mexico, Argentina, and Spain. Come to us, have some delicious wine and enjoy our juicy meals from around the world.</p>
                    <a className="btn btn-flat red-text p-1 my-1 mr-0 mml-1 collapsed" data-toggle="collapse" href="#collapseContent" aria-expanded="false" aria-controls="collapseContent"></a>
                    <i className="fas fa-share-alt text-muted float-right p-1 my-1" data-toggle="tooltip" data-placement="top" title="Share this post"></i>
                    <i className="fas fa-heart text-muted float-right p-1 my-1 mr-3" data-toggle="tooltip" data-placement="top" title="I like it"></i>
                </div>
            </div>
        </div>
    );
}

export default Card;