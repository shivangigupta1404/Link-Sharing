<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<html>
<head>
    <title>Topic Page</title>

    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/css/font-awesome.min.css">
    <link rel="stylesheet" href="/resources/css/style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
    <% Boolean isCreator;
        if(session.getAttribute("username")==null){
            isCreator=false;
        }
        else
        if(session.getAttribute("username").equals(request.getAttribute("topic.createdBy.username"))){
            isCreator=true;
        }
        else {
            isCreator=false;
        }
    %>

    <% if(session.getAttribute("username")!=null){ %>
        <jsp:include page="loggedInNavbar.jsp" />
    <% }else{ %>
        <jsp:include page="loggedOutNavbar.jsp" />
    <% } %>
    <div class="text-center error font-md">${error}</div>
    <div class="text-center success font-md">${success}</div>
    <div class="container-fluid" id="main-body">
        <div class="row">
            <div class="col-xs-12 col-sm-offset-1 col-sm-4 font-md">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Topic : ${topic.name}
                    </div>
                    <div class="panel-body">
                        <div class="container-fluid">
                            <div class="col-xs-3 col-sm-3 about">
                                <img src="/resources/images/user.jpg" alt="user" class="img-responsive center-block" class="user-img">
                            </div>
                            <div class="col-xs-9 col-sm-9">
                                <div class="row">
                                        <div class="col-xs-12">
                                            <strong>${topic.name}</strong>
                                            ${topic.visibility}
                                        </div>
                                        <div class="col-xs-12">
                                            @${topic.createdBy.username}
                                        </div>
                                        </br>
                                        <div class="col-xs-8">
                                            <div>Subscriptions</div>
                                            <div>${subscriptions}</div>
                                        </div>
                                        <div class="col-xs-4">
                                            <div>Posts</div>
                                            <div>${posts}</div>
                                        </div>
                                </div>
                            </div>
                            <div class="col-xs-12">
                                <div class="row">
                                    <div class="col-xs-9 col-sm-9">
                                        <c:if test = "!isCreator">
                                            <form id="subscribe_topic" action="/subscribe/${topic.id}" method="POST">
                                                <div class="form-group">
                                                    <select name="seriousness" >
                                                        <option value="" selected>--Select Seriousness--</option>
                                                        <option value="Casual">Casual</option>
                                                        <option value="Serious">Serious</option>
                                                        <option value="VerySerious">Very Serious</option>
                                                    </select>
                                                    <a onclick="document.getElementById('subscribe_topic').submit();"><u>Subscribe</u></a>
                                                </div>
                                            </form>
                                        </c:if>
                                    </div>
                                    <div class="col-xs-2 col-sm-2">
                                        <span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Users : ${topic.name}
                    </div>
                    <div class="panel-body">
                        <div class="container-fluid">
                            <c:forEach var="user" items="${subscribersList}">
                                <div class="row">
                                    <div class="col-xs-3 col-sm-3 about">
                                        <img src="/resources/images/user.jpg" alt="user" class="img-responsive center-block" class="user-img">
                                    </div>
                                    <div class="col-xs-9 col-sm-9">
                                        <div class="row">
                                            <div class="col-xs-12">
                                                <strong><c:out value="${user.firstname}"/> &nbsp;
                                                    <c:out value="${user.lastname}"/></strong></br>
                                                @<c:out value="${user.username}"/>
                                                </br>
                                                </br>
                                            </div>
                                            <div class="col-xs-8">
                                                <div>Subscriptions</div>
                                                <div>50</div>
                                            </div>
                                            <div class="col-xs-4">
                                                <div>Topics</div>
                                                <div>30</div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <hr>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xs-12 col-sm-6 font-md">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-xs-6 col-sm-6">Posts : ${topic.name}</div>
                                <div class="col-xs-6 col-sm-6" id="search">
                                    <span class="glyphicon glyphicon-search"></span>
                                    &nbsp;&nbsp;Search
                                    <span id="small_search_close" class="glyphicon glyphicon-remove-sign pull-right"></span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel-body ">
                        <c:forEach var="post" items="${postsList}">
                            <div class="msg">
                                <div class="col-xs-3 col-sm-2">
                                    <img src="/resources/images/user.jpg" alt="user" class="img-responsive center-block" class="user-img">
                                </div>
                                <div class="col-xs-9 col-sm-10">
                                    <p><c:out value="${post.description}"/></p>
                                    <div class="row">
                                        <div class="col-xs-12 col-sm-3 pull-left">
                                            <i class="fa fa-facebook-official" aria-hidden="true"></i>
                                            <i class="fa fa-tumblr" aria-hidden="true"></i>
                                            <i class="fa fa-google-plus" aria-hidden="true"></i>
                                        </div>
                                        <div class="post_options col-xs-12 col-sm-9 text-right font-sm ">
                                            <c:choose>
                                                <c:when test="${post.url!=null}">
                                                    <a href="${post.url}"><u>View full site</u></a>
                                                </c:when>
                                                <c:otherwise>
                                                    <a href="${post.url}"><u>Download</u></a>
                                                </c:otherwise>
                                            </c:choose>
                                            <a href="#"><u>Mark as read</u></a>
                                            <a href="#"><u>View Post</u></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <hr>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="/resources/js/jquery.min.js"></script>
    <script src="/resources/js/bootstrap.min.js"></script>
</body>
</html>



