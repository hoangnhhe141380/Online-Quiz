<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form action="takequiz" method="post">
    <table>
        <tbody>
            <tr>
                <td></td>
                <td class="wth-170">
                    Time remaining <p id="time"></p>
                </td>
                <td></td>
            </tr>
            <tr>
                <td>${QUIZRUN.question} </td>
                <td></td>
            </tr>
            <c:set var = "value" scope = "session" value = "0"/>
            <c:forEach items="${QUIZRUN.option}" var="i">
                <c:set var = "value" scope = "session" value = "${value+1}"/>
                <tr>
                    <td class="wth-400"><input id="option${value}" type="checkbox" name="answers" value="${value}" id="checkbox"> <label for="option${value}"> ${i}</label></td>
                    <td></td>
                    <td></td>

                </tr>
            </c:forEach>
            <tr>
                <td></td>
                <td> </td>
                <td><input class="input_" type="submit" name="nextQuiz" value="Next"></td>
            </tr>
        </tbody>
    </table>
</form>
<script>

    //All time getted is milis
    //Ex: 10AM -> 10*60*60*1000 
    //
    // Set the time ending
    var countDownDate = ${QUIZMODEL.endTime};
    // Update the count down every 1 second
    var x = setInterval(function () {
        // Get today's date and time
        var now = new Date().getTime();

        // Find the distance between now and the count down date
        var timeRemaining = countDownDate - now;
        console.log(timeRemaining);

        // Time calculations
        var minutes = Math.floor((timeRemaining % (1000 * 60 * 60)) / (1000 * 60));

        var seconds = Math.floor((timeRemaining % (1000 * 60)) / 1000);

        // Output the result in an element with id="time"
        document.getElementById("time").innerHTML = minutes + "m " + seconds + "s ";
        // If the count down is over, display message and clear inteval
        if (timeRemaining < 0) {
            clearInterval(x);
            // redirect page if time up
            window.location = "takequiz";
        }
    }, 1000);
    
</script>