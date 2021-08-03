<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Home Page</title>
        <link rel="stylesheet" href="<c:url value="css/style.css"></c:url>">
        </head>
        <body>
            <div class="container">
                <div class="header">
                    <div class="header_bg"></div>
                    <div class="header_nav">
                        <ul class="nav-link">
                            <li>
                                <a href="home">Home</a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="row">
                    <h3 class="title mt-1 mb-1">Login Form</h3>
                    <form action="login" method="post">
                        <table>
                            <tbody>
                                <tr>
                                    <td>User Name: </td>
                                    <td><input id="username"  class="input_" type="text" name="username" required></td>
                                </tr>
                                <tr>
                                    <td>Password: </td>
                                    <td><input id="password" class="input_" type="password" name="password" required ></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td>
                                        <input class="input_" type="submit" name="submit" id="submit" value="Sign in">
                                        <a href="<c:url value="/register"></c:url>">Register</a>
                                    </td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td> 
                                        <b id="invalidInputServer" class="danger">${messageFail}</b>
                                    <b id="invalidInput" class="danger"></b>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
    </body>
</html>
