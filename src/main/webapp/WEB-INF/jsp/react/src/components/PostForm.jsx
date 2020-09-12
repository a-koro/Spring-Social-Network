import React, {useCallback} from 'react'
import {useDropzone} from 'react-dropzone';
import axios from 'axios';

function PostForm(props) {

    const [imgUrl, setImgUrl] = React.useState("");
    const [buttonDisplay, setButtonDisplay] = React.useState("none");
    const regex = new RegExp("(http(s?):)([/|.|\\w|\\s|-])*\\.(?:jpg|gif|png)");
    const [fileName, setFileName] = React.useState("Drag 'n' drop image, or click to browse ");
    const [imageFile, setImageFile] = React.useState(new FormData());

    const onDrop = useCallback(acceptedFiles => {
        const file = acceptedFiles[0];
        let formData = new FormData();
        setFileName(file.path + " ");
        formData.append("file",file);
        setImageFile(formData);
        removeImage();
    }, []);

    function onSubmit(evt) {
        evt.preventDefault();

        console.log(imageFile);
        if (imageFile === null && (evt.target.text.value !== "" || evt.target.url.value !== "")) {
            fetch("http://localhost:8080/post/insertPost",{
                method: 'POST',
                credentials: "include",
                headers: {
                    'text': evt.target.text.value,
                    'imageUrl': evt.target.url.value
                }
            })
                .then(response => response.json())
                .then((data) => {

                })
                .catch((error) => {
                    console.error('Error:', error);
            });
        }
        else if (imageFile !== null) {
            axios.post(
                "http://localhost:8080/post/insertPostWithFile",
                imageFile,
                {
                    headers:{
                        "Content-Type":"multipart/form-data",
                        'text': evt.target.text.value,
                        'imageUrl': ""
                    }
                }
            ).catch(err=>{
                console.log(err);
            });
        }
        else {
            alert("Post cannot be empty");
        }

        document.getElementById("resetButton").click();
        props.closeModal();
    }

    const {getRootProps, getInputProps, isDragActive} = useDropzone({onDrop});

    let removeButtonStyle = {
        display: buttonDisplay
    }

    function findUrl(evt) {
        if (regex.test(evt.target.value)) {
            setImgUrl(evt.target.value);
            document.getElementById("url").value = evt.target.value;
            evt.target.value = "";
            setButtonDisplay("inline");
            resetForm();
        }
    }

    function removeImage() {
        setImgUrl("");
        setButtonDisplay("none");
        document.getElementById("url").value = "";
    }

    function resetForm() {
        setFileName("Drag 'n' drop image, or click to browse ");
        // imageFile= null;
        setImageFile(null);
    }

    return (
        <div className="my-2">
            <img src={imgUrl} alt="" height="120px" className="m-1"/>
            <button onClick={removeImage} style={removeButtonStyle} className="btn btn-white">x</button>
            <form onSubmit={onSubmit}>
                <div className="form-group">
                    <input type="text" className="form-control" id="exampleInputEmail1" aria-describedby="freeText"
                           placeholder="What are you thinking?" onChange={findUrl} name="text"/>
                        <small id="freeText" className="form-text text-muted">Only visible to your connections.</small>
                    <input type="hidden" id="url" name="url"/>
                </div>
                <div {...getRootProps()}>
                    <input {...getInputProps()} />
                    {
                        isDragActive ?
                            <p>Drop the image here ...</p> :
                            <p>{fileName}<i className="far fa-file-image"></i></p>
                    }
                </div>
                <input type="reset" className="btn btn-light mr-2" value="Reset Form" onClick={resetForm} id="resetButton"/>
                <button type="submit" className="btn btn-primary">Post</button>
            </form>
        </div>
    );
}

export default PostForm;