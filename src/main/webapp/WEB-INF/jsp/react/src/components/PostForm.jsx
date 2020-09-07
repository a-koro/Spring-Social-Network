import React from 'react';

function PostForm() {
    return (
        <div className="my-2">
            <form>
                <div className="form-group">
                    <input type="text" className="form-control" id="exampleInputEmail1" aria-describedby="freeText"
                           placeholder="What are you thinking?"/>
                        <small id="freeText" className="form-text text-muted">Only visible to your connections.</small>
                </div>
                <button type="submit" className="btn btn-primary">Post</button>
                <button className="btn btn-secondary"><i className="far fa-file-image"></i></button>
            </form>
        </div>
    );
}

export default PostForm;