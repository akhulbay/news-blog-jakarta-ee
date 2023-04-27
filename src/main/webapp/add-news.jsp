<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add News</title>
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
            <form action="${pageContext.request.contextPath}/add-news" method="post">
                <div class="mb-3">
                    <label for="exampleFormControlInput1" class="form-label" style="font-weight: bold">TITLE :</label>
                    <input type="text" class="form-control" id="exampleFormControlInput1" placeholder="Insert title" name="title" required>
                </div>
                <div class="mb-3">
                    <label for="exampleFormControlTextarea2" class="form-label" style="font-weight: bold">CONTENT :</label>
                    <textarea class="form-control" id="exampleFormControlTextarea2" rows="5" name="content" required></textarea>
                </div>
                <button type="submit" class="btn btn-primary">ADD POST</button>
            </form>

        </div>
    </div>
</div>

<%@include file="vendor/footer.jsp"%>
</body>
</html>
