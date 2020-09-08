import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faComments } from '@fortawesome/free-solid-svg-icons';
import { faGlassCheers } from '@fortawesome/free-solid-svg-icons';
import Comment from './Comment';

const style = {
    objectFit: 'cover',
    borderRadius: '50%',
    width: '50px',
    height: '50px'
};

function Post(props) {
    return (
        <>
            <div className="card p-0 my-2"> {/*col-md-6 col-xs-12 col-sm-8*/}
                <div className="card-body d-flex flex-row p-3">
                    <img style={style} src="https://cdn.vox-cdn.com/thumbor/G8A4RF-QWQl7jItQw93r402os_0=/1400x1050/filters:format(jpeg)/cdn.vox-cdn.com/uploads/chorus_asset/file/10816041/rick_and_morty_s02_still.jpg" 
                        className="avatar rounded-circle mx-3" 
                        alt="Cinque Terre"/>
                    <div>
                    <h5 className="card-title mb-0">{props.username}</h5>
                    <p className="card-text text-secondary"><small><i className="far fa-clock pr-2"></i>September 14, 2016</small></p>

                </div>
                </div>
                <div className="card-body p-1">
                    <img src="https://www.neolaia.gr/wp-content/uploads/2019/10/Rick-and-Morty.jpg" alt="rick" className="img-fluid rounded"/>
                    <blockquote className="card-text p-3 m-0">
                        <p className="mb-0">{props.post}</p>
                    </blockquote>
                    <div className="row">
                        <div className="col-xs-12 col-sm-4 col-md-4 m-2">
                            <FontAwesomeIcon className="mx-2" icon={faGlassCheers} />{8}
                            <FontAwesomeIcon className="mx-2" icon={faComments} />{10}
                        </div>
                    </div>
                </div>
                <Comment username="Alex Koro" post="After having been missing for nearly 20 years, Rick Sanchez suddenly arrives at daughter Beth's doorstep to move in with her and her family." />
                <Comment username="Alex Koro" post="After having been missing for nearly 20 years, Rick Sanchez suddenly arrives at daughter Beth's doorstep to move in with her and her family." />
            </div>
        </>
    );
}

export default Post;