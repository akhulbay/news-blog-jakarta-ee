<%@ page import="java.util.List" %>
<%@ page import="com.example.javaeefinalpoject.entity.News" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="com.example.javaeefinalpoject.entity.Comment" %>
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
                User user = (User) request.getAttribute("user");
                if (user != null) {
            %>
            <div class="p-5 mb-3" style="background-color: #9090d0; border-radius: 5px">
                <table>
                    <tr>
                        <th scope="row">ID:</th>
                        <td><%=user.getId()%></td>
                    </tr>
                    <tr>
                        <th scope="row">Full Name:</th>
                        <td><%=user.getFullName()%></td>
                    </tr>
                    <tr>
                        <th scope="row">Email:</th>
                        <td><%=user.getEmail()%></td>
                    </tr>
                    <tr>
                        <th scope="row">Password:</th>
                        <td><%=user.getPassword()%></td>
                    </tr>
                    <tr>
                        <th scope="row">Birth Date:</th>
                        <td><%=user.getBirthDate()%></td>
                    </tr>
                    <tr>
                        <th scope="row">Role:</th>
                        <td><%=user.getRole() == 1 ? "ADMIN" : "CUSTOMER"%></td>
                    </tr>
                </table>
            </div>
            <div>
                <!-- Button trigger modal -->
                <button type="button" class="btn btn-danger btn-sm" data-bs-toggle="modal"
                        data-bs-target="#blockUser">
                    BLOCK
                </button>

                <!-- Modal -->
                <div class="modal fade" id="blockUser" data-bs-backdrop="static" data-bs-keyboard="false"
                     tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <form action="${pageContext.request.contextPath}/block-user" method="post">
                                <input type="hidden" value="<%=user.getId()%>" name="user-id">
                                <div class="modal-header">
                                    <h1 class="modal-title fs-5" id="staticBackdropLabel">Block User</h1>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <div class="mb-3">
                                        <label for="exampleFormControlTextarea2" class="form-label"
                                               style="font-weight: bold">REASON :</label>
                                        <textarea class="form-control" id="exampleFormControlTextarea2" rows="5"
                                                  name="reason" required></textarea>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel
                                    </button>
                                    <button class="btn btn-danger">Block</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <%
                }
            %>


        </div>
    </div>
</div>

<%@include file="vendor/footer.jsp" %>
</body>
</html>

