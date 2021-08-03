<div class="header">
    <div class="header_bg"></div>
    <div class="header_nav">
        <ul class="nav-link">
            <c:if test="${empty QUIZPROCESSING}">
                <li><a href="home">Home</a></li>
                    <c:if test="${not empty USERMODEL}">
                    <li><a href="takequiz">Take Quiz</a></li>

                    <c:if test="${USERMODEL.roleId == 1}">
                        <li><a href="makequiz">Make Quiz</a></li>
                        <li><a href="managequiz">Manage Quiz</a></li>
                        </c:if>

                    <li><a href="logout">Logout</a></li>
                    </c:if>
                </c:if>
        </ul>
    </div>
    <div class="header-nav-dropdown">
        <button class="btn-menu" onclick="myDropDown()"><i class="fa fa-bars" style="font-size:20px"></i></button>
        <ul class="nav-link">
            <c:if test="${empty QUIZPROCESSING}">
                <li><a href="home">Home</a></li>
                    <c:if test="${not empty USERMODEL}">
                    <li><a href="takequiz">Take Quiz</a></li>

                    <c:if test="${USERMODEL.roleId == 1}">
                        <li><a href="makequiz">Make Quiz</a></li>
                        <li><a href="managequiz">Manage Quiz</a></li>
                        </c:if>

                    <li><a href="logout">Logout</a></li>
                    </c:if>
                </c:if>
        </ul>
    </div>
</div>