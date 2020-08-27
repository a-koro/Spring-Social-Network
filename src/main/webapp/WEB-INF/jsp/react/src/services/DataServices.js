import axios from 'axios';


const URL_ALL_USERS='http://localhost:8080/api/profile/users';
const URL_CURRENT_USER='http://localhost:8080/api/profile/user';
const URL_UPLOAD_IMAGE_PROFILE='http://localhost:8080/api/profile/';


function TrainerDataService(){


    this.getAllUsers = getAllUsers;
    this.getCurrentUser=getCurrentUser;
    this.imageUpload=imageUpload;

}



function getAllUsers(){

    return axios.get(URL_ALL_USERS);


}

function getCurrentUser(){

    return axios.get(URL_CURRENT_USER);

}

function imageUpload(userid){
    console.log("userid",userid);
    return axios.post(URL_UPLOAD_IMAGE_PROFILE+userid+"image/upload");

}

export default (new TrainerDataService());