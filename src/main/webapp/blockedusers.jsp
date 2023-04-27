<%@ page import="java.util.List" %>
<%@ page import="com.example.javaeefinalpoject.entity.News" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="com.example.javaeefinalpoject.entity.BlockedUser" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
            crossorigin="anonymous"></script>
</head>
<body style="background-color: #f7f7f7">
<%@include file="vendor/navbar.jsp" %>

<div class="container" style="min-height: 80vh;">
    <div class="row mt-3">
        <div class="col-sm-8 mx-auto">
            <%
                List<BlockedUser> users = (List<BlockedUser>) request.getAttribute("blockedusers");
                if (users != null) {
                    for (BlockedUser blockedUser : users) {
            %>
            <div class="list-group">
                <a href="${pageContext.request.contextPath}/blocked-user-details?id=<%=blockedUser.getId()%>" class="list-group-item list-group-item-action">
                    <div class="d-flex w-100 justify-content-between">
                        <h5 class="mb-1" style="color: #b73f3f"><%=blockedUser.getUser().getFullName()%>
                        </h5>
                        <small class="text-body-secondary"><%=blockedUser.getUser().getEmail()%>
                        </small>
                    </div>
                    <p><strong>Reason:</strong> <%=blockedUser.getReason()%></p>
                </a>
            </div>
            <%
                    }
                }
            %>
        </div>
    </div>
</div>

<%@include file="vendor/footer.jsp" %>
</body>
</html>
