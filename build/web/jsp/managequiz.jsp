<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Management Page</title>
        <link rel="stylesheet" href="<c:url value="css/style.css"></c:url>">
            <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
        </head>
        <body>
            <div class="container">
            <%@include file="../components/header.jsp" %>
            <div class="row">
                <h3 class="title mt-1 mb-1"><p>Number of questions: </p> ${totalItems}</h3>
                <table>
                    <thead>
                    <th style="text-align: left; color: blue;">Question</th>
                    <th style="text-align: left; color: blue;">Created Date</th>
                    <th style="text-align: left; color: blue;"></th>
                    </thead>
                    <tbody>
                        <c:forEach items="${listQuestion}" var="question">
                            <tr>
                                <td style="width: 440px;padding-right: 50px;">${question.question}</td>
                                <td>${question.convertDateToString()}</td>
                                <td></td>
                                <td><a href="<c:url value="/update?quizid=${question.id}"></c:url>">Update</a>
                                </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <center> 
                    <c:if test="${totalPage != 1}">

                        <c:forEach begin="1" end="${totalPage}" var="i">
                            <div class="paging">
                                <a  href="managequiz?index=${i}">${i}</a>
                            </div>

                        </c:forEach>

                    </c:if>

                </center>
            </div>
        </div>
        <script src="js/header.js"></script>
    </body>
</html>
