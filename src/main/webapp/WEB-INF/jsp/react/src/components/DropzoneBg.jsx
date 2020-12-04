import React, {useCallback} from 'react'
import {useDropzone} from 'react-dropzone';
import { useHistory, Link } from "react-router-dom";

import axios from 'axios';

function Dropzone() {

    const history = useHistory();

    const style = {
        objectFit: 'cover',
        borderRadius: '50%',
        width: '140px',
        height: '140px'
    };

    const onDrop = useCallback(acceptedFiles => {
      const file = acceptedFiles[0];
      console.log(file);


      const formData= new FormData();
      formData.append("file",file);

      axios.post(
        `/api/profile/image-background/upload`,
        formData,
        {
            headers:{
                "Content-Type":"multipart/form-data"
            }
        }
      ).then(()=>{
          console.log("file upload success");
          history.push('/');
          history.push('/profile');
      }).catch(err=>{
          console.log(err);
      });
        

    }, [])
    const {getRootProps, getInputProps, isDragActive} = useDropzone({onDrop});

    return (
        <>
                <div {...getRootProps()}>
            <input {...getInputProps()} />
            {
              isDragActive ?
                <p>Drop the image here ...</p> :
                <p>Drag 'n' drop background image, or click to select background image</p>
            }
          </div>
        </>
    )
  }

export default Dropzone;
