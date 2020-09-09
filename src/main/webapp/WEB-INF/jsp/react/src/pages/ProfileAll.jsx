import React, {useEffect} from 'react'
import {useLocation} from 'react-router-dom'

function ProfileAll(props) {


    const location = useLocation();

    useEffect(() => {
        console.log(location.pathname); // result: '/secondpage'
        console.log(location.state.detail); // result: 'some_value'
    }, [location]);


    return (
        <>
            <div>Hi I am ProfileAll</div>
        </>
    )

}


export default ProfileAll;

