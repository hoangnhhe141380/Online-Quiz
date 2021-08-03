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
                <b id="validInputServer" class="success">${result == 1 ? "Update new question successfully!":""}</b>
                <b id="invalidInputServer" class="danger">${result == 0 ? "Update new question failed!":""}</b>
                <b id="invalidInput" class="danger"></b>
                <b id="validInput" class="sucess"></b>
                <form onsubmit="return onSubmitQuiz(event)" action=""  method="post">
                    <input type="hidden"  name="quizid" value="${QUIZUPDATE.id}">
                    <table>
                        <thead>
                        <th></th>
                        <th></th>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Question: </td>
                                <td><textarea id="question"  name="question" cols="70" rows="6">${QUIZUPDATE.question}</textarea></td>
                            </tr>
                            <tr>
                                <td>Option1: </td>
                                <td><textarea id="option1" name="option1" cols="10" rows="3">${QUIZUPDATE.option[0]}</textarea></td>
                            </tr>
                            <tr>
                                <td>Option2: </td>
                                <td><textarea id="option2" name="option2" cols="3" rows="3">${QUIZUPDATE.option[1]}</textarea></td>
                            </tr>
                            <tr>
                                <td>Option3: </td>
                                <td><textarea id="option3" name="option3" cols="3" rows="3">${QUIZUPDATE.option[2]}</textarea></td>
                            </tr>
                            <tr>
                                <td>Option4: </td>
                                <td><textarea id="option4" name="option4" cols="3" rows="3">${QUIZUPDATE.option[3]}</textarea></td>
                            </tr>
                            <tr>
                                <td>Answer(s): </td>
                                <td><input id="answer1" type="checkbox" name="answers" value="1" ${ANSWER1}>Option 1
                                    <input id="answer2" type="checkbox" name="answers" value="2" ${ANSWER2}>Option 2
                                    <input id="answer3" type="checkbox" name="answers" value="3" ${ANSWER3}>Option 3
                                    <input id="answer4" type="checkbox" name="answers" value="4" ${ANSWER4}>Option 4
                                </td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>
                                    <input class="input_" type="submit" name="submit" id="submit" value="Update">
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
