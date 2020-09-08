import React from 'react';

function PostForm() {

    const [imgUrl, setImgUrl] = React.useState("");
    const [buttonDisplay, setButtonDisplay] = React.useState("none");
    const regex = new RegExp("(http(s?):)([/|.|\\w|\\s|-])*\\.(?:jpg|gif|png)");

    let removeButtonStyle = {
        display: buttonDisplay
    }

    function findUrl(evt) {
        if (regex.test(evt.target.value)) {
            setImgUrl(evt.target.value);
            evt.target.value = "";
            setButtonDisplay("inline");
        }
    }

    function removeImage() {
        setImgUrl("");
        setButtonDisplay("none");
    }

    return (
        <div className="my-2">
            <img src={imgUrl} alt="" height="120px" className="m-1"/>
            <button onClick={removeImage} style={removeButtonStyle} className="btn btn-white">x</button>
            <form>
                <div className="form-group">
                    <input type="text" className="form-control" id="exampleInputEmail1" aria-describedby="freeText"
                           placeholder="What are you thinking?" onChange={findUrl}/>
                        <small id="freeText" className="form-text text-muted">Only visible to your connections.</small>
                    <input type="hidden" id="url"/>
                </div>
                <button type="submit" className="btn btn-primary">Post</button>
                <button className="btn btn-white">Upload image <i className="far fa-file-image"></i> <small>(Drag 'n' drop)</small></button>
            </form>
        </div>
    );
}

export default PostForm;