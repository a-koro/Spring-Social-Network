import axios from 'axios';


const URL_ALL_USERS='http://localhost:8080/api/profile/users';
const URL_CURRENT_USER='http://localhost:8080/api/profile/user';


function TrainerDataService(){


    this.getAllUsers = getAllUsers;
    this.getCurrentUser=getCurrentUser;

}



function getAllUsers(){

    return axios.get(URL_ALL_USERS);


}

function getCurrentUser(){

    return axios.get(URL_CURRENT_USER);

}

export default (new TrainerDataService());