<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<html>
<head>
    <title>Post Details</title>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/css/font-awesome.min.css">
    <link rel="stylesheet" href="/resources/css/style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

    <% if(session.getAttribute("username")!=null){ %>
    <jsp:include page="loggedInNavbar.jsp" />
    <% }else{ %>
    <jsp:include page="loggedOutNavbar.jsp" />
    <% } %>

    <div class="text-center error font-md">${error}</div>
    <div class="text-center success font-md">${success}</div>
    <div class="container-fluid" id="main-body">
        <div class="row">
            <div class="col-xs-12 col-sm-offset-1 col-sm-5 font-md">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-xs-3 col-sm-3 about">
                                    <img src="/resources/images/user.jpg" alt="user" class="img-responsive center-block" class="user-img">
                                </div>
                                <div class="col-xs-9">
                                    <p style="margin-top: 20px;">
                                        <span>${post.createdBy.firstname} &nbsp; ${post.createdBy.lastname}</span>
                                        <span class="pull-right"><a href="/showtopic/${post.topic.id}"><u>${post.topic.name}</u></a></span>
                                    </p>
                                    <p>
                                        <span>@${post.createdBy.username}</span>
                                        <span class="pull-right">${post.dateCreated}</span>
                                    </p>
                                </div>
                            </div>
                            <p></p>
                            <p></p>
                            <div class="row">
                                <p>${post.description}</p>
                                <p>
                                    <span>
                                <i class="fa fa-facebook-official" aria-hidden="true"></i>
                                <i class="fa fa-tumblr" aria-hidden="true"></i>
                                <i class="fa fa-google-plus" aria-hidden="true"></i>
                            </span>
                                    <span class="post_options font-sm pull-right">
                                        <a href="#"><u>Edit</u></a>
                                        <a href="#"><u>Delete</u></a>
                                        <c:catch var="exception">
                                            <c:set var="url" value="${post.url}" />
                                            <c:if test="${url ne null}">
                                                <a href="${post.url}"><u>View full site</u></a>
                                            </c:if>
                                        </c:catch>
                                        <c:catch var="exception">
                                            <c:set var="filepath" value="${post.filepath}" />
                                            <c:if test="${filepath ne null}">
                                                <a href="${post.filepath}"><u>Download</u></a>
                                            </c:if>
                                        </c:catch>
                                    </span>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xs-12 col-sm-6 font-md">
            </div>
        </div>
    </div>

    <script src="/resources/js/jquery.min.js"></script>
    <script src="/resources/js/bootstrap.min.js"></script>

</body>
</html>
