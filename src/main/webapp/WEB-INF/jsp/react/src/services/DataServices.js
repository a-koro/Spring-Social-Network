import axios from 'axios';


const URL_ALL_USERS='http://localhost:8080/api/profile/users';
const URL_CURRENT_USER='http://localhost:8080/api/profile/user';
const URL_UPLOAD_IMAGE_PROFILE='http://localhost:8080/api/profile/';
const URL_CHECK_RELATIONSHIP = 'http://localhost:8080/api/relationship'
const URL_DELETE_RELATIONSHIP = 'http://localhost:8080/api/deleteRelationship'


function TrainerDataService(){


    this.getAllUsers = getAllUsers;
    this.getCurrentUser=getCurrentUser;
    this.imageUpload=imageUpload;
    this.getCurrentProfile = getCurrentProfile
    this.getCurrentRelationship = getCurrentRelationship
    this.deleteRelationship = deleteRelationship
}



function getAllUsers(){

    return axios.get(URL_ALL_USERS);


}

function getCurrentUser(){

    return axios.get(URL_CURRENT_USER);

}

function getCurrentProfile(userId) {
    return axios.get(URL_CURRENT_USER + "/" + userId )
}

 function getCurrentRelationship(currentUserId, profilePageId) {
    return axios.post(URL_CHECK_RELATIONSHIP, {
        currentUserId,
        profilePageId
    })
 }

 function deleteRelationship(currentUserId, profilePageId) {
    return axios.post(URL_DELETE_RELATIONSHIP, {
        currentUserId,
        profilePageId
    })
 }

function imageUpload(userid){
    console.log("userid",userid);
    return axios.post(URL_UPLOAD_IMAGE_PROFILE+userid+"image/upload");

}

export default (new TrainerDataService());