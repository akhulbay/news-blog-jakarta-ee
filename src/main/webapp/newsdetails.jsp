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
                News news = (News) request.getAttribute("news");
                if (news != null) {
            %>
            <div class="p-5 mb-3" style="background-color: #9090d0; border-radius: 5px">
                <h3>
                    <%=news.getTitle()%>
                </h3>
                <p><%=news.getContent()%>
                </p>
                <p>
                    Posted by <strong><%=news.getUser().getFullName()%>
                </strong>
                    At <strong><%=news.getPostDate().format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))%>
                </strong>
                </p>

                <%
                    }
                %>
                <%
                    if (currentUser != null && currentUser.getRole() == 1) {
                %>
                <button type="button" class="btn btn-danger btn-sm mb-3" data-bs-toggle="modal"
                        data-bs-target="#deleteNews">
                    DELETE
                </button>
                <!-- Modal -->
                <div class="modal fade" id="deleteNews" data-bs-backdrop="static" data-bs-keyboard="false"
                     tabindex="-1" aria-labelledby="staticBackdropLabel1" aria-hidden="true">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <form action="${pageContext.request.contextPath}/delete-news" method="post">
                                <input type="hidden" value="<%=news.getId()%>" name="news-id">
                                <div class="modal-header">
                                    <h1 class="modal-title fs-5" id="staticBackdropLabel1">Edit News</h1>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <span>Are you sure that you want to delete this news?</span>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel
                                    </button>
                                    <button class="btn btn-danger">Delete</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <%
                    }
                %>
                <%
                    if (currentUser != null && currentUser.getId().equals(news.getUser().getId())) {
                %>
                <div>
                    <!-- Button trigger modal -->
                    <button type="button" class="btn btn-primary btn-sm" data-bs-toggle="modal"
                            data-bs-target="#editNews">
                        EDIT
                    </button>
                    <!-- Modal -->
                    <div class="modal fade" id="editNews" data-bs-backdrop="static" data-bs-keyboard="false"
                         tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                        <div class="modal-dialog modal-lg">
                            <div class="modal-content">
                                <form action="/update-news" method="post">
                                    <input type="hidden" value="<%=news.getId()%>" name="news-id">
                                    <div class="modal-header">
                                        <h1 class="modal-title fs-5" id="staticBackdropLabel">Edit News</h1>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <div class="mb-3">
                                            <label for="exampleFormControlInput1" class="form-label"
                                                   style="font-weight: bold">TITLE :</label>
                                            <input type="text" class="form-control" id="exampleFormControlInput1"
                                                   placeholder="Insert title" name="title" required
                                                   value="<%=news.getTitle()%>">
                                        </div>
                                        <div class="mb-3">
                                            <label for="exampleFormControlTextarea2" class="form-label"
                                                   style="font-weight: bold">CONTENT :</label>
                                            <textarea class="form-control" id="exampleFormControlTextarea2" rows="5"
                                                      name="content" required><%=news.getContent()%></textarea>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel
                                        </button>
                                        <button class="btn btn-success">Update</button>
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
            <%
                if (currentUser != null) {
            %>
            <div class="mb-3">
                <form action="/add-comment" method="post">
                    <input type="hidden" value="<%=news.getId()%>" name="news-id">
                    <div class="mb-3">
                        <textarea class="form-control" id="exampleFormControlTextarea1" rows="2"
                                  name="comment" required></textarea>
                    </div>
                    <div>
                        <button class="btn btn-success btn-sm">ADD COMMENT</button>
                    </div>
                </form>
            </div>
            <%
                }
            %>
            <div class="row">
                <div class="col-12">
                    <%
                        List<Comment> comments = (List<Comment>) request.getAttribute("comments");
                        if (comments != null) {
                            for (Comment comment : comments) {
                    %>
                    <div class="list-group">
                        <a href="#" class="list-group-item list-group-item-action">
                            <div class="d-flex w-100 justify-content-between">
                                <h5 class="mb-1"><%=comment.getUser().getFullName()%>
                                </h5>
                                <small class="text-body-secondary"><%=comment.getPostDate()
                                        .format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))%>
                                </small>
                            </div>
                            <p class="mb-1"><%=comment.getComment()%>
                            </p>
                        </a>
                        <%
                            if (currentUser != null && currentUser.getRole() == 1) {
                        %>
                        <div class="mt-1">
                            <button type="button" class="btn btn-danger btn-sm mb-3" data-bs-toggle="modal"
                                    data-bs-target="#deleteComment">
                                DELETE
                            </button>
                            <!-- Modal -->
                            <div class="modal fade" id="deleteComment" data-bs-backdrop="static"
                                 data-bs-keyboard="false"
                                 tabindex="-1" aria-labelledby="staticBackdropLabel2" aria-hidden="true">
                                <div class="modal-dialog modal-lg">
                                    <div class="modal-content">
                                        <form action="${pageContext.request.contextPath}/delete-comment" method="post">
                                            <input type="hidden" value="<%=comment.getId()%>" name="comment-id">
                                            <input type="hidden" value="<%=news.getId()%>" name="news-id">
                                            <div class="modal-header">
                                                <h1 class="modal-title fs-5" id="staticBackdropLabel2">Edit News</h1>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                        aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                <span>Are you sure that you want to delete this comment?</span>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                                                    Cancel
                                                </button>
                                                <button class="btn btn-danger">Delete</button>
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
                    <%
                            }
                        }
                    %>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="vendor/footer.jsp" %>
</body>
</html>

