<%--
  Created by IntelliJ IDEA.
  User: arisp
  Date: 8/22/2020
  Time: 9:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Registration successful</title>
    <style>
        .card {
            background-color: #f7f7f7;
            padding: 20px 25px 30px;
            margin: 0 auto 25px;
            margin-top: 50px;
            -moz-border-radius: 2px;
            -webkit-border-radius: 2px;
            border-radius: 2px;
            -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
            -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
            box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
        }

        .card-container.card {
            max-width: 350px !important;
            padding: 40px 40px;
        }
    </style>
</head>
<body>
<div class="col-md-12">
    <div class="card card-container">
        <div class="form-group">
            <div class="alert alert-success">
                Registration successful!
            </div>
        </div>
        <a class="btn btn-primary" role="button" href="/login">Click Here to Login</a>
    </div>
</div>
</body>
</html>
