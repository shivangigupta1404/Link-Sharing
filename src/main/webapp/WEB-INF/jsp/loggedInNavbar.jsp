<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/css/font-awesome.min.css">
    <link rel="stylesheet" href="/resources/css/style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
    <div class="container-fluid border-black-1 round-10 padding-10 font-lg" id="navbar">
    <div class="row">
        <div class="col-xs-12 text-center col-sm-offset-0 col-sm-3 font-lg navbar-options" id="brand">
            <a href="/"><u>Link Sharing</u></a>
        </div>
        <div class="col-xs-offset-1 col-xs-10 col-sm-offset-2 col-sm-3">
            <form style="margin-bottom:0px;">
                <div class="form-group" style="margin-bottom:0px;">
                    <input type="text" name="search" placeholder="Search..">
                </div>
            </form>
        </div>
        <div class="col-xs-6 col-sm-2 text-center navbar-options">
            <i type="button" data-toggle="modal" data-target="#create_topic_popup" class="fa fa-comment" aria-hidden="true"></i>
            <!-- Create Topic Pop Up -->
            <div class="modal fade" id="create_topic_popup" role="dialog">
                <div class="modal-dialog">
                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Create Topic</h4>
                        </div>
                        <div class="modal-body font-md">
                            <form action="/createTopic" method="POST">
                                <div class="form-group">
                                    <div class="container-fluid">
                                        <div class="row">
                                            <label class="col-xs-6 col-sm-4 text-right" for="create_topic_name">Name * :</label>
                                            <input type="text" name="name" class="col-xs-6 col-sm-8" class="form-control" id="create_topic_name">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="container-fluid">
                                        <div class="row">
                                            <label class="col-xs-6 col-sm-4 text-right" for="create_topic_visibility">Visibility * :</label>
                                            <select name="visibility" id="create_topic_visibility" class="col-xs-6 col-sm-8">
                                                <option value="">--Select Privacy--</option>
                                                <option value="Private">private</option>
                                                <option value="Public">public</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <button type="submit" class="btn btn-default">Create</button>
                                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                            </form>

                        </div>

                    </div>

                </div>
            </div>
            <span type="button" data-toggle="modal" data-target="#send_invitation_popup" class="glyphicon glyphicon-envelope"></span>
            <!-- Send Invitation Pop Up -->
            <div class="modal fade" id="send_invitation_popup" role="dialog">
                <div class="modal-dialog">
                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Send Invitation</h4>
                        </div>
                        <div class="modal-body font-md">
                            <form>
                                <div class="form-group">
                                    <div class="container-fluid">
                                        <div class="row">
                                            <label class="col-xs-6 col-sm-4 text-right" for="create_topic_name">Email * :</label>
                                            <input class="col-xs-6 col-sm-8" type="email" class="form-control" id="send_invitation_email">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="container-fluid">
                                        <div class="row">
                                            <label class="col-xs-6 col-sm-4 text-right" for="send_invitation_topics">Topic * :</label>
                                            <select id="send_invitation_topics" class="topicList col-xs-6 col-sm-8">
                                                ${topicOption}
                                            </select>
                                        </div>
                                    </div>

                                </div>
                                <button type="submit" class="btn btn-default">Invite</button>
                                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                            </form>

                        </div>

                    </div>

                </div>
            </div>
            <span type="button" data-toggle="modal" data-target="#share_link_popup"  class="glyphicon glyphicon-link"></span>
            <!-- Share Link Pop Up -->
            <div class="modal fade" id="share_link_popup" role="dialog">
                <div class="modal-dialog">
                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Share Link</h4>
                        </div>
                        <div class="modal-body font-md">
                            <form action="/createlink" method="POST">
                                <div class="form-group">
                                    <div class="container-fluid">
                                        <div class="row">
                                            <label class="col-xs-6 col-sm-4 text-right" for="share_link_link">Link * :</label>
                                            <input class="col-xs-6 col-sm-8" name="link" type="text" class="form-control" id="share_link_link">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-6 col-sm-4 text-right" for="share_link_description">Description * :</label>
                                    <textarea class="col-xs-6 col-sm-8" name="description" rows="5" id="share_link_description"></textarea>
                                </div>
                                <div class="form-group">
                                    <div class="container-fluid">
                                        <div class="row">
                                            <label class="col-xs-6 col-sm-4 text-right" for="share_link_topics">Topic * :</label>
                                            <select name="topic" id="share_link_topics" class="col-xs-6 col-sm-8 topicList">
                                                ${topicOption}
                                            </select>
                                        </div>
                                    </div>

                                </div>
                                <button type="submit" class="btn btn-default">Share</button>
                                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                            </form>

                        </div>

                    </div>

                </div>
            </div>
            <span type="button" data-toggle="modal" data-target="#share_document_popup"  class="glyphicon glyphicon-file"></span>
            <!-- Share Document Pop Up -->
            <div class="modal fade" id="share_document_popup" role="dialog">
                <div class="modal-dialog">
                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Share Document</h4>
                        </div>
                        <div class="modal-body font-md">
                            <form action="/createdocument" method="post" enctype="multipart/form-data">
                                <div class="form-group">
                                    <div class="container-fluid">
                                        <div class="row">
                                            <label class="col-xs-6 col-sm-4 text-right" for="share_document_document">Document * :</label>
                                            <input class="col-xs-3 col-sm-4" name="file" type="file" class="form-control" style="display: none;" id="share_document_document">
                                            <input type="button" value="Browse" class="btn btn-default" onclick="document.getElementById('share_document_document').click();" />
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-6 col-sm-4 text-right" for="share_document_description">Description * :</label>
                                    <textarea class="col-xs-6 col-sm-8" rows="5" name="description" id="share_document_description"></textarea>
                                </div>
                                <div class="form-group">
                                    <div class="container-fluid">
                                        <div class="row">
                                            <label class="col-xs-6 col-sm-4 text-right" for="share_document_topics">Topics:</label>
                                            <select name="topic" id="share_document_topics" class="topicList col-xs-6 col-sm-8">
                                                ${topicOption}
                                            </select>
                                        </div>
                                    </div>

                                </div>
                                <button type="submit" class="btn btn-default">Share</button>
                                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-xs-6 col-sm-2 navbar-options">
            <span class="glyphicon glyphicon-user"></span>
            <span class="dropdown">
                <button class="btn btn-lg dropdown-toggle user-dropdown" type="button" data-toggle="dropdown">
                    <%=session.getAttribute("username")%>
                    <span class="caret"></span></button>
                    <ul class="dropdown-menu">
                        <li><a href="/editprofile">Profile</a></li>
                        <% String admin=(String)session.getAttribute("admin");
                            if(admin.equals("yes")){
                        %>
                            <li><a href="/users/">Users</a></li>
                        <%}%>
                        <li><a href="#">Topics</a></li>
                        <li><a href="#">Posts</a></li>
                        <li><a href="/logout">Logout</a></li>
                    </ul>
            </span>
        </div>
    </div>
</div>
</body>
</html>

