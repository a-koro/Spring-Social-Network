import React, {useCallback} from 'react'
import {useDropzone} from 'react-dropzone';
import { useHistory } from "react-router-dom";
import axios from 'axios';

function PostForm(props) {

    const [imgUrl, setImgUrl] = React.useState("");
    const [buttonDisplay, setButtonDisplay] = React.useState("none");
    const regex = new RegExp("(http(s?):)([/|.|\\w|\\s|-])*\\.(?:jpg|gif|png)");
    const base64regex = new RegExp(/(data:image\/[^;]+;base64[^"]+)/g);
    const [fileName, setFileName] = React.useState("Drag 'n' drop image, or click to browse ");
    const [imageFile, setImageFile] = React.useState(null);
    const history = useHistory();

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

        if (imageFile === null && (evt.target.text.value !== "" || evt.target.url.value !== "")) {
            fetch("/post/insertPost",{
                method: 'POST',
                credentials: "include",
                headers: {
                    'text': evt.target.text.value,
                    'imageUrl': evt.target.url.value
                }
            })
                .then(response => response.json())
                .then((data) => {
                    document.getElementById("resetButton").click();
                    props.closeModal();
                    history.push("/profile");
                })
                .then(() => {
                    history.push("/");
                })
                .catch((error) => {
                    console.error('Error:', error);
            });
        }
        else if (imageFile !== null) {
            axios.post(
                "/post/insertPostWithFile",
                imageFile,
                {
                    headers:{
                        "Content-Type":"multipart/form-data",
                        'text': evt.target.text.value
                    }
                }
            )
                .then(() => {
                    document.getElementById("resetButton").click();
                    props.closeModal();
                    history.push("/profile");
                })
                .then(() => {
                    history.push("/");
                })
                .catch(err=>{
                console.log(err);
            });
        }
        else {
            alert("Post cannot be empty");
        }
    }

    const {getRootProps, getInputProps, isDragActive} = useDropzone({onDrop});

    let removeButtonStyle = {
        display: buttonDisplay
    }

    function checkImage(imageSrc) {
        let img = new Image();
        img.onload = function () {
            console.log("Exists");
        };
        img.onerror = function () {
            console.log("Does not Exist");
        };
        img.src = imageSrc;
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
        setImageFile(null);
    }

    return (
        <div className="my-2">
            <img src={imgUrl} alt="" height="120px" className="m-1"/>
            <button onClick={removeImage} style={removeButtonStyle} className="btn btn-white">x</button>
            <form onSubmit={onSubmit}>
                <div className="form-group">
                    <textarea className="form-control" id="exampleInputEmail1" aria-describedby="freeText"
                              placeholder="What are you thinking?" onChange={findUrl} name="text" maxLength="250" rows="5"></textarea>
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