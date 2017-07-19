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

<div class="container-fluid">
    <jsp:include page="loggedInNavbar.jsp" />
    <div id="main-body" class="container-fluid">
        <div class="row">
            <div class="col-xs-12 col-sm-4 font-md">
                <jsp:include page="UserSummary.jsp" />
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
            </div>
            <div class="col-xs-12 col-sm-offset-1 col-sm-7 font-md">
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

                        <div class="post">
                            <div>
                                <a href="#">Grails</a>
                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam consequat tin elit, eu posuere arcu sollicitudin quis. Fusce porta ipsum lacus, eget finibus nisi accumsan non.</p>
                                <div class="row">
                                    <div class="col-xs-12 col-sm-3 pull-left">
                                        <i class="fa fa-facebook-official" aria-hidden="true"></i>
                                        <i class="fa fa-tumblr" aria-hidden="true"></i>
                                        <i class="fa fa-google-plus" aria-hidden="true"></i>
                                    </div>
                                    <div class="post_options col-xs-12 col-sm-9 pull-right text-right">
                                        <a href="#"><u>Download</u></a>
                                        <a href="#"><u>View full site</u></a>
                                        <a href="#"><u>Mark as read</u></a>
                                        <a href="#"><u>View Post</u></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <hr>
                        <div class="post">
                            <div>
                                <a href="#">Grails</a>
                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam consequat tin elit, eu posuere arcu sollicitudin quis. Fusce porta ipsum lacus, eget finibus nisi accumsan non.</p>
                                <div class="row">
                                    <div class="col-xs-12 col-sm-3 pull-left">
                                        <i class="fa fa-facebook-official" aria-hidden="true"></i>
                                        <i class="fa fa-tumblr" aria-hidden="true"></i>
                                        <i class="fa fa-google-plus" aria-hidden="true"></i>
                                    </div>
                                    <div class="post_options col-xs-12 col-sm-9 pull-right text-right">
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
</div>

</body>
</html>
