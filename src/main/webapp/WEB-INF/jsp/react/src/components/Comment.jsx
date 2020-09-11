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

    const [dateTime, setDateTime] = React.useState(new Date());

    React.useEffect(() => {
        let tempDate = new Date(props.comment.created);
        tempDate.setHours(tempDate.getHours()+3,tempDate.getMinutes(),tempDate.getSeconds(),tempDate.getMilliseconds());
        setDateTime(tempDate);
    },[]);

    return (
        <>
        <div className="card p-0 my-1 mx-2">
            <div className="card-body d-flex flex-row pt-2 pb-0 px-1 mb-0">
                {/* "https://cdn.vox-cdn.com/thumbor/G8A4RF-QWQl7jItQw93r402os_0=/1400x1050/filters:format(jpeg)/cdn.vox-cdn.com/uploads/chorus_asset/file/10816041/rick_and_morty_s02_still.jpg" */}
                <img style={style} src={"http://localhost:8080/api/profile/searchUsers/" + props.comment.user.userId}
                    className="avatar rounded-circle mx-2" 
                    alt="Cinque Terre"/>
                <div>
                    <h6 className="card-text mb-0">{props.comment.user.firstName + " " + props.comment.user.lastName}</h6>
                    <blockquote className="card-text p-0 m-0">
                        <p className="mb-0">{props.comment.text}</p>
                    </blockquote>
                </div>

            </div>
            <div className="card-body p-1">
                <div className="row">
                    {/*<div className="col-4 m-2">*/}
                    {/*    <FontAwesomeIcon className="mx-2" icon={faGlassCheers} />{8}*/}
                    {/*</div>*/}
                    <div className="col-11 text-right">
                        <p className="card-text text-secondary"><small><i className="far fa-clock pr-2"></i>{dateTime.toLocaleString("en-GB",{timeZone: "UTC"})}</small></p>
                    </div>
                </div>
            </div>
        </div>
    </>
    );
}

export default Comment;