import React from 'react';
import TimeAgo from 'react-timeago';
import "../css/article.css";

export default function ArticlePrev(props) {

    return (
        <div className="card border-0 mb-4 prevArticle" onClick={()=> window.open("https://articlomatic.herokuapp.com", "_blank")}>
            <div className="row no-gutters d-flex flex-row justify-content-between">
                <div className="col-7 mr-2 d-flex flex-column justify-content-between">
                    <small className="text-muted">{props.article.category.name}</small>
                    <h6 className="card-title">{props.article.title}</h6>
                    <div>
                        <span className="card-text float-left"><small className="text-muted">{"@" + props.article.user.firstName + " " + props.article.user.lastName}</small></span>
                        <span className="card-text float-right"><small className="text-muted"><TimeAgo date={props.article.createdAt}/></small></span>
                    </div>

                </div>
                <div className="col-4">
                    <img src={"https://mern-articlomaric-app.herokuapp.com/api/articleImage?articleId=" + props.article._id} className="img-fluid imageScalingSmall" alt="Article Image" />
                </div>
            </div>

        </div>
    );
}