
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/css/font-awesome.min.css">
    <link rel="stylesheet" href="/resources/css/style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
    <jsp:include page="loggedInNavbar.jsp" />
    <div class="text-center error font-md">${error}</div>
    <div class="text-center success font-md">${success}</div>
    <div class="container-fluid" id="main-body">
        <div class="row">
            <div class="col-xs-12 col-sm-offset-1 col-sm-4 font-md">
                <jsp:include page="UserSummary.jsp" />
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-xs-6 col-sm-6">Subscriptions</div>
                                <div class="col-xs-6 col-sm-6 text-right"><a href="#">View All</a></div>
                            </div>
                        </div>

                    </div>
                    <div class="panel-body">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-xs-3 col-sm-3">
                                    <img src="/resources/images/user.jpg" alt="user" class="img-responsive center-block" class="user-img">
                                </div>
                                <div class="col-xs-9 col-sm-9">
                                    <div class="row">
                                            <div class="col-xs-12 col-sm-12">
                                                {{Topic}}
                                            </div>
                                            <div class="font-sm">
                                                <div class="col-xs-12 col-sm-4">
                                                    <div>@Uday</div>
                                                    <div><a href="#">Unsubscribe</a></div>
                                                </div>
                                                <div class="col-xs-6 col-sm-5">
                                                    <div>Subscriptions</div>
                                                    <div>{{count}}</div>
                                                </div>
                                                <div class="col-xs-6 col-sm-3">
                                                    <div>Posts</div>
                                                    <div>{{Count}}</div>
                                                </div>
                                            </div>
                                        </div>
                                </div>
                            </div>
                            <div class="row option-list text-center">
                                <div>
                                    <select>
                                        <option value="">Serious</option>
                                        <option value="Private">Very Serious</option>
                                        <option value="Public">Casual</option>
                                    </select>
                                    <select>
                                        <option value="">Private</option>
                                        <option value="Private">Public</option>
                                    </select>
                                    <span class="option">
                                        <span  class="glyphicon glyphicon-envelope"></span>
                                        <span  class="glyphicon glyphicon-file"></span>
                                        <span  class="glyphicon glyphicon-trash"></span>
                                    </span>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xs-12 col-sm-6 font-md">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-xs-4 col-sm-4">Inbox</div>
                                <div class="col-xs-8 col-sm-offset-3 col-sm-5" id="search">
                                    <span class="glyphicon glyphicon-search"></span>
                                    &nbsp;&nbsp;Search
                                    <span id="small_search_close" class="glyphicon glyphicon-remove-sign pull-right"></span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel-body ">

                        <div class="msg">
                            <div class="col-xs-3 col-sm-2">
                                <img src="/resources/images/user.jpg" alt="user" class="img-responsive center-block" class="user-img">
                            </div>
                            <div class="col-xs-9 col-sm-10">
                                <a href="#">Grails</a>
                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam consequat tin elit, eu posuere arcu sollicitudin quis. Fusce porta ipsum lacus, eget finibus nisi accumsan non.</p>
                                <div class="row">
                                    <div class="col-xs-12 col-sm-3 pull-left">
                                        <i class="fa fa-facebook-official" aria-hidden="true"></i>
                                        <i class="fa fa-tumblr" aria-hidden="true"></i>
                                        <i class="fa fa-google-plus" aria-hidden="true"></i>
                                    </div>
                                    <div class="post_options col-xs-12 col-sm-9 text-right font-sm ">
                                        <a href="#"><u>Download</u></a>
                                        <a href="#"><u>View full site</u></a>
                                        <a href="#"><u>Mark as read</u></a>
                                        <a href="#"><u>View Post</u></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <hr>
                        <div class="msg">
                            <div class="col-xs-3 col-sm-2">
                                <img src="/resources/images/user.jpg" alt="user" class="img-responsive center-block" class="user-img">
                            </div>
                            <div class="col-xs-9 col-sm-10">
                                <a href="#">Grails</a>
                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam consequat tin elit, eu posuere arcu sollicitudin quis. Fusce porta ipsum lacus, eget finibus nisi accumsan non.</p>
                                <div class="row">
                                    <div class="col-xs-12 col-sm-3 pull-left">
                                        <i class="fa fa-facebook-official" aria-hidden="true"></i>
                                        <i class="fa fa-tumblr" aria-hidden="true"></i>
                                        <i class="fa fa-google-plus" aria-hidden="true"></i>
                                    </div>
                                    <div class="post_options col-xs-12 col-sm-9 text-right font-sm ">
                                        <a href="#"><u>Download</u></a>
                                        <a href="#"><u>View full site</u></a>
                                        <a href="#"><u>Mark as read</u></a>
                                        <a href="#"><u>View Post</u></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="/resources/js/jquery.min.js"></script>
    <script src="/resources/js/bootstrap.min.js"></script>
</body>
</html>
