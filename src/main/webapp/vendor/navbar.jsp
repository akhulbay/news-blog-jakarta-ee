<%@ page import="com.example.javaeefinalpoject.entity.User" %>
<%
    User currentUser = (User) request.getSession().getAttribute("CURRENT_USER");
%>
<header>
    <div class="container">
        <div class="row">
            <div class="col-sm-12 mx-auto">
                <nav class="navbar" style="border-bottom: 1px solid gray">
                    <a class="navbar-brand" href="/home" style="font-weight: 700">MY BLOG</a>

                    <ul class="nav justify-content-end">
                        <%
                            if (currentUser != null) {
                        %>
                        <%
                            if (currentUser.getRole() == 1) {
                        %>

                        <li class="nav-item dropdown">

                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown1" role="button"

                               data-bs-toggle="dropdown" aria-expanded="false" style="color: black">

                                Users

                            </a>

                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown1">

                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/users">All Users</a></li>

                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/blocked-users">Blocked Users</a></li>

                            </ul>

                        </li>
                        <%
                            }
                        %>
                        <li class="nav-item">
                            <a class="nav-link" href="/news-page" style="color: black">All News</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/add-news-page" style="color: black">Add News</a>
                        </li>
                        <li class="nav-item dropdown">

                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"

                               data-bs-toggle="dropdown" aria-expanded="false" style="color: black">

                                <%=currentUser.getFullName()%>

                            </a>

                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">

                                <li><a class="dropdown-item" href="/profile">Profile</a></li>

                                <li><a class="dropdown-item" href="/settings">Settings</a></li>

                                <li>
                                    <hr class="dropdown-divider">
                                </li>

                                <li><a class="dropdown-item" href="/logout">Logout</a></li>

                            </ul>

                        </li>
                        <%
                        } else {
                        %>
                        <li class="nav-item">
                            <a class="nav-link" href="/news-page" style="color: black">All News</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/home" style="color: black">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/login" style="color: black">Sign In</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/register" style="color: black">Register</a>
                        </li>
                        <%
                            }
                        %>
                    </ul>
                </nav>

            </div>
        </div>
    </div>
</header>
