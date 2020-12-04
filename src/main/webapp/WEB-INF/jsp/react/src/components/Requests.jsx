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
                <div className="card-body d-flex flex-row pt-2 pb-0 px-1">
                    <img style={style} src={url}
                         className="avatar rounded-circle mx-2"
                         alt="Profile Picture"/>
                    <h6 className="card-text align-bottom mt-2">{props.requesterInfo.username} <span id="wannaConnect">would like to connect</span></h6>
                    <button className="btn btn-success ml-2" onClick={acceptRel}>Accept</button>
                    <button className="btn btn-danger ml-2" onClick={deleteRel}>Decline</button>
                </div>
            </div>

        </>
    )


}


export default Requests;