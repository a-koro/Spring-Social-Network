import React,{useState,useEffect} from 'react';

import Dropzone from '../components/Dropzone';
import DataServices from "../services/DataServices";



export default function ImageProfile() {
    

    return (
        <>
        
             <img alt="image-profile" src="http://lorempixel.com/100/100/people/9" />
            <Dropzone userid={1}/>
        </>
    )
}
