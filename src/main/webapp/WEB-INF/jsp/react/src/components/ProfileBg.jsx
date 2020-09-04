import React from 'react'

import '../components/profileBg.css';


import DataServices from '../services/DataServices';
import Dropzone from "./Dropzone";


export default function ProfileBg() {

    const style = {
        objectFit: 'cover',
        borderRadius: '50%',
        width: '160px',
        height: '160px'
    };

    const bgImageStyle = {
        height: '340px'
    };

    const [user, setUser] = React.useState({});

    function getCurrentUser() {
        DataServices.getCurrentUser().then(
            response => {
                console.log("user in profile : ", response.data);
                setUser(response.data);
            }
        ).catch(error => { console.log(error.response) });
    }

    React.useEffect(() => { getCurrentUser() }, []);

    return (

        // <div className="card hovercard">
        //     <div className="cardheader">
        //
        //     </div>
        //     <div className="avatar">
        //         <img style={style} alt="image-profile" src="http://localhost:8080/api/profile/image/download" />
        //         <Dropzone/>
        //     </div>
        //     <div className="info">
        //         <div className="title">
        //             <p>Script Eden</p>
        //         </div>
        //         <div className="desc">{user.userId}</div>
        //         <div className="desc">Curious developer</div>
        //         <div className="desc">Tech geek</div>
        //     </div>

            <div className="card text-white">
                <img style={bgImageStyle} className="card-img" src="https://scontent.fath4-2.fna.fbcdn.net/v/t1.0-9/110182717_102990614832624_7303548008920258688_n.jpg?_nc_cat=110&_nc_sid=dd9801&_nc_ohc=4uN-mIsaNjUAX8gyEFk&_nc_ht=scontent.fath4-2.fna&oh=b510cce8ab6d058f76f6585a68be56f1&oe=5F661D65" alt="Card image"/>
                    <div className="card-img-overlay card-inverse">
                        <p className="card-text text-center mt-4">
                            <img style={style} alt="image-profile" src="http://localhost:8080/api/profile/image/download"/>
                            <Dropzone/>
                        </p>
                        <h2 className="card-title text-center">{user.firstName} {user.lastName}</h2>
                    </div>
            </div>
        // </div>
    )
}
