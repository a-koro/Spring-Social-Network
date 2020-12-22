import React from 'react';
import {useHistory} from "react-router-dom";
import DataServices from "../services/DataServices";

const style = {
    objectFit: 'cover',
    borderRadius: '50%',
    width: '40px',
    height: '40px'
};

function Requests(props) {

    let url = "/api/profile/searchUsers/" + props.requesterInfo.id;
    let history = useHistory();


    const onClick = e => {
        e.preventDefault();




        }

    const acceptRel = () => {
        DataServices.acceptRelationship(props.requesterInfo.myId, props.requesterInfo.id).then (
            () => {
                console.log("Friend request from User " + props.requesterInfo.id + " Accepted")
                props.handleUpdate(props.requesterInfo.id);
            }
        )
    }

    const deleteRel = () => {
        DataServices.deleteRelationship(props.requesterInfo.myId, props.requesterInfo.id).then(
            () => {
                console.log("Friend request from user " + props.requesterInfo.id + " Declined" )
                props.handleUpdate(props.requesterInfo.id);
            }
        )
    }

    return (
        <>
            <div className="card my-2 border-0">
                <div className="card-body d-flex flex-row justify-content-between m-0 p-0">
                    <div className="d-flex flex-row">
                        <img style={style} src={url}
                             className="avatar rounded-circle mx-2"
                             alt="Profile Picture"/>
                        <h6 className="card-text align-bottom mt-2">{props.requesterInfo.username}</h6>
                    </div>
                    <div>
                        <button className="btn btn-success ml-2" onClick={acceptRel}><i className="fas fa-user-check"></i></button>
                        <button className="btn btn-danger ml-2" onClick={deleteRel}><i className="fas fa-user-times"></i></button>
                    </div>
                </div>
            </div>

        </>
    )


}


export default Requests;