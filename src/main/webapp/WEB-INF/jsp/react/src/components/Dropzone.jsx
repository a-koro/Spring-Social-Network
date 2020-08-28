import React, {useCallback} from 'react'
import {useDropzone} from 'react-dropzone';

import axios from 'axios';

function Dropzone() {
    const onDrop = useCallback(acceptedFiles => {
      const file = acceptedFiles[0];
      console.log(file);


      const formData= new FormData();
      formData.append("file",file);

      axios.post(
        `http://localhost:8080/api/profile/image/upload`,
        formData,
        {
            headers:{
                "Content-Type":"multipart/form-data"
            }
        }
      ).then(()=>{
          console.log("file upload success")
      }).catch(err=>{
          console.log(err);
      });
        

    }, [])
    const {getRootProps, getInputProps, isDragActive} = useDropzone({onDrop})
  
    return (
      <div {...getRootProps()}>
        <input {...getInputProps()} />
        {
          isDragActive ?
            <p>Drop the image here ...</p> :
            <p>Drag 'n' drop profile image, or click to select profile image</p>
        }
      </div>
    )
  }

export default Dropzone;
