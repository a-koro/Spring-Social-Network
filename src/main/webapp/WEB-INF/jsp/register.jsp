<%-- 
    Document   : welcome
    Created on : Aug 1, 2020, 2:13:50 PM
    Author     : korov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="springform" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <%--    Can't "see" .css file due to Security/jsp --%>
    <%--    <link rel="stylesheet" href="../../../resources/static/forms.css">--%>
    <style>
        label {
            display: block;
            margin-top: 10px;
        }

        .card-container.card {
            max-width: 350px !important;
            padding: 40px 40px;
        }

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

        .profile-img-card {
            width: 96px;
            height: 96px;
            margin: 0 auto 10px;
            display: block;
            -moz-border-radius: 50%;
            -webkit-border-radius: 50%;
            border-radius: 50%;
        }
        .alert {
            margin: 2px;
        }

    </style>

    <title>Register User</title>
</head>
<body>
<div class="col-md-12">
    <a href="/">
        <img src="/img/logo.jpg" width="300px" alt="Connector Logo" class="mx-auto d-block mt-5">
    </a>
    <div class="card card-container">
        <img
                src="//ssl.gstatic.com/accounts/ui/avatar_2x.png"
                alt="profile-img"
                class="profile-img-card"
        />
        <springform:form action="doregister" method="post" modelAttribute="newUser">
            <div class="form-group">
                <label for="email">Email</label>
                <springform:input type="email" path="email" cssClass="form-control"/>
                <springform:errors path="email" cssClass="alert alert-danger" element="div"/>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <springform:password path="password" cssClass="form-control"/>
                <springform:errors path="password" cssClass="alert alert-danger" element="div"/>
            </div>
            <div class="form-group">
                <label for="firstName">First Name</label>
                <springform:input path="firstName" cssClass="form-control" />
                <springform:errors path="firstName" cssClass="alert alert-danger" element="div"/>
            </div>
            <div class="form-group">
                <label for="lastName">Last Name</label>
                <springform:input path="lastName" cssClass="form-control"/>
                <springform:errors path="lastName" cssClass="alert alert-danger" element="div"/>
            </div>
            <div class="form-group">
                <label for="birthday">Date Of Birth</label>
                <springform:input path="birthday" type="date" cssClass="form-control"/>
                <springform:errors path="birthday" cssClass="alert alert-danger" element="div"/>
            </div>
            <div class="form-group">
                <input type="submit" value="Sign up" class="btn btn-primary btn-block"/>
            </div>
        </springform:form>
    </div>
</div>
</body>
</html>
