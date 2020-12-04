import axios from 'axios';


const URL_ALL_USERS='/api/profile/users';
const URL_CURRENT_USER='/api/profile/user';
const URL_UPLOAD_IMAGE_PROFILE='/api/profile/';
const URL_CHECK_RELATIONSHIP = '/api/relationship'
const URL_DELETE_RELATIONSHIP = '/api/deleteRelationship'
const URL_CREATE_RELATIONSHIP = '/api/createRelationship'
const URL_ACCEPT_RELATIONSHIP = '/api/acceptRelationship'
const URL_FRIEND_REQUESTS = '/api/relationshipPending/'

const URL_GET_CONTACTS='/api/user/';

const URL_FIND_MESSAGES='/messages/';

function TrainerDataService(){


    this.getAllUsers = getAllUsers;
    this.getCurrentUser=getCurrentUser;
    this.imageUpload=imageUpload;
    this.getCurrentProfile = getCurrentProfile
    this.getCurrentRelationship = getCurrentRelationship
    this.deleteRelationship = deleteRelationship
    this.createRelationship = createRelationship
    this.acceptRelationship = acceptRelationship
    this.getPendingRequests = getPendingRequests
    this.getContacts=getContacts;
    this.findChatMessages=findChatMessages;
    this.findChatMessage=findChatMessage;
    this.countNewMessages=countNewMessages;

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

 function createRelationship(currentUserId, profilePageId) {
     return axios.post(URL_CREATE_RELATIONSHIP, {
         currentUserId,
         profilePageId
     })
 }

 function acceptRelationship(currentUserId, profilePageId) {
     return axios.post(URL_ACCEPT_RELATIONSHIP, {
         currentUserId,
         profilePageId
     })
 }

 function getPendingRequests(currentUserId) {
    return axios.get(URL_FRIEND_REQUESTS + currentUserId)
 }

function imageUpload(userid){
    console.log("userid",userid);
    return axios.post(URL_UPLOAD_IMAGE_PROFILE+userid+"/image/upload");

}

function getContacts(){

    return axios.get(URL_GET_CONTACTS);
}

function findChatMessages(senderId,recipientId){
    return axios.get(URL_FIND_MESSAGES + senderId + "/" + recipientId);
}

function findChatMessage(id){
    return axios.get(URL_FIND_MESSAGES+id);
}

function countNewMessages(senderId,recipientId){
    return axios.get(URL_FIND_MESSAGES+senderId+"/"+recipientId+"/count");
}

export default (new TrainerDataService());