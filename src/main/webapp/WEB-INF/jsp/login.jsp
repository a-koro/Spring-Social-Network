<%-- 
    Document   : login
    Created on : Aug 18, 2020, 11:06:37 AM
    Author     : korov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>My Login Page</title>
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
</head>
<body>

<%--<c:set var="income" scope="session" value="${4000*4}"/>--%>
<%--<c:if test="${empty param.error}">--%>
<%--<p>My income is: <c:out value="${param.error}"/><p>--%>
<%--    </c:if>--%>
<div class="container-fluid">
    <div class="row">
        <div class="col-lg-5 pl-0 d-none d-lg-inline">
            <img class="img-fluid" src="/img/back.jpg"/>
        </div>
    <div class="col-lg-4 col-xl-3 col-sm-6 offset-sm-3 offset-lg-1 col-xs-12 mt-4">
        <div class="text-center">
            <img class="img-fluid" src="/img/logo.jpg" width="300px" alt="Connector Logo" class="mx-auto d-block mt-5">
        </div>
        <div class="card">
            <img
                    src="//ssl.gstatic.com/accounts/ui/avatar_2x.png"
                    alt="profile-img"
                    class="profile-img-card"
            />
            <form action="/login" method="post">
                <c:if test="${param.logout}">
                <div class="form-group">
                    <div class="alert alert-success" style="text-align: center">
                        Logout Successful
                    </div>
                    </c:if>

                    <div class="form-group">
                        <label for="username">Username</label>
                        <input type="text" class="form-control" name="username" id="username">
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" class="form-control" name="password" id="password">
                    </div>
                    <c:if test="${param.error}">
                    <div class="form-group">
                        <div class="alert alert-danger" style="text-align: center">
                            Invalid Credentials
                        </div>
                    </div>
                    </c:if>
                    <div class="form-group">
                        <input type="submit" class="btn btn-primary btn-block" value="Log In">
                    </div>
            </form>
            <form action="/register">
                <button type="submit" class="btn btn-success btn-block">Create New Account</button>
            </form>
        </div>
        <div class="fixed-bottom w-100 bg-light text-center">
            <small>Connector &#169; 2020</small>
        </div>
    </div>
    </div>
</div>
</body>
</html>
