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
        <div class="col-sm-6 mx-auto">
            <%
                String emailError = request.getParameter("emailerror");
                if (emailError!=null) {
            %>
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                The user for this <strong>email</strong> already exists!
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
            <%
                }
            %>
            <%
                String passwordError = request.getParameter("passworderror");
                if (passwordError!=null) {
            %>
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                <strong>Passwords</strong> are not same!
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
            <%
                }
            %>
            <form method="post" action="${pageContext.request.contextPath}/register">
                <div class="card col mt-3">
                    <h5 class="pt-3 pb-3 px-3" style="background-color: #f7f7f7">Register Page
                    </h5>
                    <div class="card-body mb-3">

                        <table class="mb-3" style="border-collapse: separate; border-spacing: 10px; width: 100%">
                            <tbody>
                            <tr>
                                <td>
                                    <span>First Name</span>
                                </td>
                                <td>
                                    <input type="text" class="form-control" aria-label="First name"
                                           aria-describedby="basic-addon1" name="first-name" required>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <span>Last Name</span>
                                </td>
                                <td>
                                    <input type="text" class="form-control" aria-label="Last name"
                                           aria-describedby="basic-addon1" name="last-name" required>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <span>Email</span>
                                </td>
                                <td>
                                    <input type="email" class="form-control" aria-label="Username"
                                           aria-describedby="basic-addon1" name="email" required>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <span>Password</span>
                                </td>
                                <td>
                                    <input type="password" class="form-control" aria-label="Username"
                                           aria-describedby="basic-addon1" name="password" required>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <span>Repeat Password</span>
                                </td>
                                <td>
                                    <input type="password" class="form-control" aria-label="Username"
                                           aria-describedby="basic-addon1" name="re-password" required>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <span>Birth Date</span>
                                </td>
                                <td>
                                    <input type="date" class="form-control" aria-label="Birth date"
                                           aria-describedby="basic-addon1" name="birth-date" required>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                        <button type="submit" class="btn btn-success btn-sm">Register</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<%@include file="vendor/footer.jsp"%>
</body>
</html>