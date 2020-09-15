<%--
  Created by IntelliJ IDEA.
  User: korov
  Date: 9/15/2020
  Time: 1:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous" />
    <title>Admin console</title>
</head>
<body>
    <div class="container-fluid">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="#">Connector</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarText">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="#">Home of Admin</a>
                    </li>
                </ul>
                <span class="navbar-text">
                    <a class="nav-link" href="/logout">Logout</a>
                </span>
            </div>
        </nav>
        <div class="row mt-5">
            <div class="card-deck text-center mx-auto">
                <div class="card border-info mb-3" style="max-width: 18rem;">
                    <div class="card-header">Registered Users</div>
                    <div class="card-body text-info">
                        <h1 class="card-title display-1">${users}</h1>
                    </div>
                </div>
                <div class="card border-info mb-3" style="max-width: 18rem;">
                    <div class="card-header">Posts submitted</div>
                    <div class="card-body text-info">
                        <h1 class="card-title display-1">${posts}</h1>
                    </div>
                </div>
                <div class="card border-info mb-3" style="max-width: 18rem;">
                    <div class="card-header">Comments submitted</div>
                    <div class="card-body text-info">
                        <h1 class="card-title display-1">${comments}</h1>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
