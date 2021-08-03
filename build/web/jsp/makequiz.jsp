<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Make Quiz Page</title>
        <link rel="stylesheet" href="<c:url value="css/style.css"></c:url>">
            <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
        </head>
        <body>
            <div class="container">
            <%@include file="../components/header.jsp" %>
            <div class="row">
                <b id="validInputServer" class="success">${result == 1 ? "Add new question successfully!":""}</b>
                <b id="invalidInputServer" class="danger">${result == 0 ? "Add new question failed!":""}</b>
                <b id="invalidInput" class="danger">${message}</b>
                <b id="validInput" class="sucess"></b>
                <form action="makequiz" method="post">
                    <table>
                        <tbody>
                            <tr>
                                <td>Question: </td>
                                <td><textarea id="question"  name="question" cols="70" rows="6">${q}</textarea></td>
                            </tr>
                            <tr>
                                <td>Option1: </td>
                                <td><textarea id="option1" name="option1" cols="10" rows="3">${o1}</textarea></td>
                            </tr>
                            <tr>
                                <td>Option2: </td>
                                <td><textarea id="option2" name="option2" cols="3" rows="3">${o2}</textarea></td>
                            </tr>
                            <tr>
                                <td>Option3: </td>
                                <td><textarea id="option3" name="option3" cols="3" rows="3">${o3}</textarea></td>
                            </tr>
                            <tr>
                                <td>Option4: </td>
                                <td><textarea id="option4" name="option4" cols="3" rows="3">${o4}</textarea></td>
                            </tr>
                            <tr>
                                <td>Answer(s): </td>
                                <td><input id="answer1" type="checkbox" name="answers" value="1" ${answerString.contains("1") ? "checked" : ""}>Option 1
                                    <input id="answer2" type="checkbox" name="answers" value="2" ${answerString.contains("2") ? "checked" : ""}>Option 2
                                    <input id="answer3" type="checkbox" name="answers" value="3" ${answerString.contains("3") ? "checked" : ""}>Option 3
                                    <input id="answer4" type="checkbox" name="answers" value="4" ${answerString.contains("4") ? "checked" : ""}>Option 4
                                </td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>
                                    <input class="input_" type="submit" name="submit" id="submit" value="Save">
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>  
        <script src="js/header.js"></script>
    </body>
</html>
