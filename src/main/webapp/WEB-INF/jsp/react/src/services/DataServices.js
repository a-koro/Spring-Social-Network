import axios from 'axios';


const URL_ALL_USERS='http://localhost:8080/api/profile/users';
const URL_CURRENT_USER='http://localhost:8080/api/profile/user';
const URL_UPLOAD_IMAGE_PROFILE='http://localhost:8080/api/profile/';

const URL_GET_CONTACTS='http://localhost:8080/api/user/';

const URL_FIND_MESSAGES='http://localhost:8080/messages/';

function TrainerDataService(){


    this.getAllUsers = getAllUsers;
    this.getCurrentUser=getCurrentUser;
    this.imageUpload=imageUpload;
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