<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Profile</title>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/resources/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="/resources/css/style.css">

    <meta name="viewport" content="width=device-width, initial-scale=1">

    <style>
        .panel-body{border-top:none;}
    </style>
</head>
<body>

    <% if(session.getAttribute("username")!=null){ %>
    <jsp:include page="loggedInNavbar.jsp" />
    <% }else{ %>
    <jsp:include page="loggedOutNavbar.jsp" />
    <% } %>
    <div class="text-center error font-md">${error}</div>
    <div class="text-center success font-md">${success}</div>
    <div id="main-body" class="container-fluid">
        <div class="row">
            <div class="col-xs-12 col-sm-offset-1 col-sm-5 font-md">
                <div class="panel panel-default border-black-1">
                    <div class="panel-body">
                        <div class="container-fluid">
                            <div class="col-xs-3 col-sm-3 about">
                                <img src="/resources/images/user.jpg" alt="user" class="img-responsive center-block" class="user-img">
                            </div>
                            <div class="col-xs-9 col-sm-9">
                                <div class="row">
                                    <div class="col-xs-12">
                                        <strong>
                                            ${user.firstname} &nbsp; ${user.lastname}
                                        </strong>
                                        <p class="grey">@${user.username}</p>
                                    </div>
                                    <div class="col-xs-8">
                                        <div>Subscriptions</div>
                                        <div>${user.subscriptionCount}</div>
                                    </div>
                                    <div class="col-xs-4">
                                        <div>Topics</div>
                                        <div>${user.topicCount}</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                <div class="panel-heading">Topics</div>
                <div class="panel-body">

                    <div class="col-sm-4">
                        <div><a href="#">{{topic}}</a></div>
                        <span class="dropdown">
                                <button class="btn dropdown-toggle" type="button" data-toggle="dropdown">
                                    Seriousness
                                    <span class="caret"></span></button>
                                    <ul class="dropdown-menu">
                                        <li>Casual</li>
                                        <li>Serious</li>
                                        <li>Very Serious</li>
                                    </ul>
                            </span>
                    </div>
                    <div class="col-sm-4">
                        <div>Subscriptions</div>
                        <div>50</div>
                    </div>
                    <div class="col-sm-4">
                        <div>Post</div>
                        <div>30</div>
                    </div>

                </div>
            </div>
                <div class="panel panel-default">
                    <div class="panel-heading">Subscriptions</div>
                    <div class="panel-body">

                        <div class="col-sm-4">
                            <div><a href="#">{{topic}}</a></div>
                            <span class="dropdown">
                                <button class="btn dropdown-toggle" type="button" data-toggle="dropdown">
                                    Seriousness
                                    <span class="caret"></span></button>
                                    <ul class="dropdown-menu">
                                        <li>Casual</li>
                                        <li>Serious</li>
                                        <li>Very Serious</li>
                                    </ul>
                            </span>
                        </div>
                        <div class="col-sm-4">
                            <div>Subscriptions</div>
                            <div>50</div>
                        </div>
                        <div class="col-sm-4">
                            <div>Post</div>
                            <div>30</div>
                        </div>

                    </div>
                </div>
            </div>
            <div class="col-xs-12 col-sm-6 font-md">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-xs-4 col-sm-4">Posts</div>
                                <div class="col-xs-8 col-sm-offset-3 col-sm-5" id="search">
                                    <span class="glyphicon glyphicon-search"></span>
                                    &nbsp;&nbsp;Search
                                    <span id="small_search_close" class="glyphicon glyphicon-remove-sign pull-right"></span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel-body ">
                        <c:set var="userPost" value="${userPost}" />
                        <c:if test="${userPost ne null}">
                            <c:forEach var="post" items="${userPost}">
                                <div class="post">
                                    <div>
                                        <a href="/showtopic/${post.topic.id}"><c:out value="${post.topic.name}"/></a>
                                        <p>${post.description}</p>
                                        <div class="row">
                                            <div class="col-xs-12 col-sm-3 pull-left">
                                                <i class="fa fa-facebook-official" aria-hidden="true"></i>
                                                <i class="fa fa-tumblr" aria-hidden="true"></i>
                                                <i class="fa fa-google-plus" aria-hidden="true"></i>
                                            </div>
                                            <div class="post_options col-xs-12 col-sm-9 pull-right">
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
                                                <a href="#"><u>Mark as read</u></a>
                                                <a href="#"><u>View Post</u></a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <hr>
                            </c:forEach>
                        </c:if>
                        <c:if test="${userPost eq null}">
                            <p>No Posts Yet</p>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
