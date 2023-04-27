<%@ page import="java.util.List" %>
<%@ page import="com.example.javaeefinalpoject.entity.News" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
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
    <form action="${pageContext.request.contextPath}/news-page" method="get">
        <div class="row mt-3">
            <div class="col-sm-4" style="margin-left: 30%">
                <input type="text" class="form-control" name="key" placeholder="Search">
            </div>
            <div class="col-sm-3">
                <button class="btn btn-success">SEARCH</button>
            </div>
        </div>
    </form>
    <div class="row mt-3">
        <div class="col-sm-8 mx-auto">
            <%
                List<News> newsList = (List<News>) request.getAttribute("newsList");
                if (newsList != null) {
                    for (News news : newsList) {
            %>
            <div class="p-5 mb-3" style="background-color: #9090d0; border-radius: 5px">
                <a href="/news-details?id=<%=news.getId()%>" style="color: white; text-decoration: none">
                    <h3>
                        <%=news.getTitle()%>
                    </h3>
                </a>
                <p><%=news.getContent()%>
                </p>
                <p>
                    Posted by <strong><%=news.getUser().getFullName()%>
                </strong>
                    At <strong><%=news.getPostDate().format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))%>
                </strong>
                </p>

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

