<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>

  <title>Welcome</title>

  <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
  <link rel="stylesheet" href="/resources/css/font-awesome.min.css">
  <link rel="stylesheet" href="/resources/css/style.css">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <style>
    .panel-body{border-top:1px solid black;}
  </style>

</head>
<body>

  <div class="container-fluid" id="main-wrapper">
    <jsp:include page="loggedOutNavbar.jsp" />
    <div class="text-center error font-md">${error}</div>
    <div class="text-center success font-md">${success}</div>
    <div id="main-body" class="container-fluid">
      <div class="row">
        <div class="col-xs-12 col-sm-8 font-md">
          <div class="panel panel-default border-black-1">
            <div class="panel-heading">Recent Shares</div>
            <div class="panel-body">
              <div class="container-fluid">
                <c:forEach var="resource" items="${recentshares}">
                  <div class="row share">
                    <div class="col-xs-12 col-sm-3">
                      <img src="/resources/images/user.jpg" alt="user" class="img-responsive center-block" class="user-img">
                    </div>
                    <div class="col-xs-12 col-sm-9">
                      <div class="row">
                        <div class="col-xs-8">
                          <c:out value="${resource.createdBy.firstname}"/>
                          &nbsp;
                          <c:out value="${resource.createdBy.lastname}"/>
                          &nbsp;
                          <span class="grey">@<c:out value="${resource.createdBy.username}"/> 10min</span>
                        </div>
                        <div class="col-xs-4 pull-right text-right">
                          <a href="/showtopic/${resource.topic.id}"><c:out value="${resource.topic.name}"/></a>
                        </div>
                      </div>
                      <p><c:out value="${resource.description}"/></p>
                      <div class="row">
                        <div class="col-xs-9 pull-left">
                          <i class="fa fa-facebook-official fa-2x" aria-hidden="true"></i>
                          <i class="fa fa-tumblr fa-2x" aria-hidden="true"></i>
                          <i class="fa fa-google-plus fa-2x" aria-hidden="true"></i>
                        </div>
                        <div class="col-xs-3 pull-right text-right">
                          <a href="<c:out value='${resource.id}'/>"><u>View Post</u></a>
                        </div>
                      </div>
                    </div>
                  </div>
                </c:forEach>
              </div>
            </div>
          </div>
          <div class="panel panel-default">
            <div class="panel-heading">
              <div class="container-fluid">
                <div class="row">
                  <div class="col-xs-6 col-sm-8">
                    Top Posts
                  </div>
                  <div class="dropdown col-xs-6 col-sm-4 pull-right text-right">
                    <button class="btn btn-default dropdown-toggle" type="button" id="menu1" data-toggle="dropdown">Today<span class="caret"></span></button>
                    <ul class="dropdown-menu" role="menu" aria-labelledby="menu1">
                      <li>Today</li>
                      <li>1 Week</li>
                      <li>1 Month</li>
                      <li>1 Year</li>
                    </ul>
                  </div>
                </div>
              </div>

            </div>
            <div class="panel-body">
              <div class="row share">
                <div class="col-xs-12 col-sm-3">
                  <img src="/resources/images/user.jpg" alt="user" class="img-responsive center-block" class="user-img">
                </div>
                <div class="col-xs-12 col-sm-9">
                  <div class="row">
                    <div class="col-xs-8">
                      Uday Pratap Singh &nbsp;&nbsp; <span class="grey">@uday 10min</span>
                    </div>
                    <div class="col-xs-4 pull-right text-right">
                      <a hre="#"> Grails</a>
                    </div>
                  </div>
                  <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam consequat tin elit, eu posuere arcu sollicitudin quis. Fusce porta ipsum lacus, eget finibus nisi accumsan non.</p>
                  <div class="row">
                    <div class="col-xs-9 pull-left">
                      <i class="fa fa-facebook-official fa-2x" aria-hidden="true"></i>
                      <i class="fa fa-tumblr fa-2x" aria-hidden="true"></i>
                      <i class="fa fa-google-plus fa-2x" aria-hidden="true"></i>
                    </div>
                    <div class="col-xs-3 pull-right text-right">
                      <a href="#"><u>View Post</u></a>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="col-xs-12 col-sm-4">
          <div class="panel panel-default">
            <div class="panel-heading font-md">Login</div>
            <div class="panel-body">
              <div class="container-fluid">
                <form class="form-horizontal" id="login_form" action="/login" method="POST">
                  <div class="form-group">
                    <div class="row">
                      <label class="control-label col-xs-12 col-sm-3" for="login_user">Email:</label>
                      <div class="col-xs-12 col-sm-9">
                        <input type="text" class="form-control" id="login_user" name="login_user">
                      </div>
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="row">
                      <label class="control-label col-xs-12 col-sm-3" for="login_password">Password:</label>
                      <div class="col-xs-12 col-sm-9">
                        <input type="password" class="form-control" id="login_password" name="login_password">
                      </div>
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="col-xs-12 col-sm-6 text-center font-md">
                      <a href="#"> <u>Forgot Password</u> </a>
                    </div>
                    <div class="col-xs-offset-3 col-xs-4  col-sm-offset-1 col-sm-5">
                      <button class="btn btn-default">Login</button>
                    </div>
                  </div>
                </form>
              </div>
            </div>
          </div>
          <div class="panel panel-default">
            <div class="panel-heading font-md">Register</div>
            <div class="panel-body">
              <div class="container-fluid">
                <form class="form-horizontal" id="register_form" action="/register" method="POST" enctype="multipart/form-data">
                  <div class="form-group">
                    <div class="row">
                      <label class="control-label col-xs-12 col-sm-3" for="register_firstname">First Name * :</label>
                      <div class="col-xs-12 col-sm-9">
                        <input type="text" class="form-control" id="register_firstname" name="firstname">
                      </div>
                      <span class="col-xs-12 col-sm-offset-3 col-sm-9" id='register_firstname_message'></span>
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="row">
                      <label class="control-label col-xs-12 col-sm-3" for="register_lastname">Last Name * :</label>
                      <div class="col-xs-12 col-sm-9">
                        <input type="text" class="form-control" id="register_lastname" name="lastname">
                      </div>
                      <span class="col-xs-12 col-sm-offset-3 col-sm-9" id='register_lastname_message'></span>
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="row">
                      <label class="control-label col-xs-12 col-sm-3" for="register_email">Email * :</label>
                      <div class="col-xs-12 col-sm-9">
                        <input type="email" class="form-control" id="register_email" name="email">
                      </div>
                      <span class="col-xs-12 col-sm-offset-3 col-sm-9" id='register_email_message'></span>
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="row">
                      <label class="control-label col-xs-12 col-sm-3" for="register_username">Username * :</label>
                      <div class="col-xs-12 col-sm-9">
                        <input type="text" class="form-control" id="register_username" name="username">
                      </div>
                      <span class="col-xs-12 col-sm-offset-3 col-sm-9" id='register_username_message'></span>
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="row">
                      <label class="control-label col-xs-12 col-sm-3" for="register_password">Password * :</label>
                      <div class="col-xs-12 col-sm-9">
                        <input type="password" class="form-control" id="register_password" name="password">
                      </div>
                      <span class="col-xs-12 col-sm-offset-3 col-sm-9" id='register_password_message'></span>
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="row">
                      <label class="control-label col-xs-12 col-sm-3" for="register_confirm_password">Confirm Password * :</label>
                      <div class="col-xs-12 col-sm-9">
                        <input type="password" class="form-control" id="register_confirm_password" name="confirmPassword">
                      </div>
                      <span class="col-xs-12 col-sm-offset-3 col-sm-9" id='register_confirm_password_message'></span>
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="row">
                      <label class="control-label col-xs-12 col-sm-3" for="photo">Photo</label>
                      <div class="col-xs-6 col-xs-offset-3 col-sm-offset-0 col-sm-8">
                        <input type="file" id="photo" name="photo"/>
                        <input type="button" value="Upload image" class="btn btn-default" onclick="document.getElementById('photo').click();" />
                      </div>
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="row">
                      <div class="col-xs-4 col-xs-offset-3 col-sm-5">
                        <button type="submit" class="btn btn-default">Register</button>
                      </div>
                      <span class="col-xs-12 col-sm-offset-3 col-sm-9" id='register_validity'></span>
                    </div>
                  </div>
                </form>
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
  <script src="/resources/js/custom.js"></script>

</body>
</html>