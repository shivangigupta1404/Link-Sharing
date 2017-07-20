<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<html>
<head>
    <title>All Users</title>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/css/font-awesome.min.css">
    <link rel="stylesheet" href="/resources/css/style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<body>

    <div class="container-fluid" id="main-wrapper">
        <jsp:include page="loggedInNavbar.jsp" />
        <div class="text-center error font-md">${error}</div>
        <div class="text-center success font-md">${success}</div>
        <div id="main-body" class="container-fluid">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Users
                    <select id="user_type">
                        <option selected="selected" value="all">All Users</option>
                        <option value="active">Active users</option>
                        <option value="inactive">Inactive users</option>
                    </select>
                    <form id="admin_search_user" method="post" action="#" class="pull-right">
                        <input type="text" placeholder="Search">
                        <input type="submit" value="Search">
                    </form>
                </div>
                <div class="panel-body" style="padding:0px;">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>UserName</th>
                            <th>Email</th>
                            <th>FirstName</th>
                            <th>LastName</th>
                            <th>Active</th>
                            <th>Manage</th>
                        </tr>
                        </thead>
                        <tbody id="user_table">
                        </tbody>
                    </table>
                    <div class="text-center">
                        <ul class="pagination pagination-lg" id="user_pages">
                        </ul>
                    </div>

                </div>
            </div>
        </div>
    </div>
    
    <script src="/resources/js/jquery.min.js"></script>
    <script src="/resources/js/bootstrap.min.js"></script>
    <script src="/resources/js/custom.js"></script>

    <script>
        $(function(){
            getPagination();
            getData(0);

            $("#user_type").on('change',function(){
                getPagination();
                getData(0);
            });

            $("#user_pages").on('click',"li a",(function() {
                var id= $(this).attr('id');
                var offset=(id-1)*2;
                getData(offset);
            }));
        });

        function getData(offset) {
            jQuery.ajax({
                url:"/getLimitedUser/",
                async:false,
                data:{"offset":offset,"limit":2,"type":$('#user_type').val()},
                success:function(data) {
                    $('#user_table').html(data);
                }
            });
        }

        function getPagination(){
            jQuery.ajax({
                url:"/getUserCount/",
                data:{"type":$('#user_type').val()},
                async:false,
                success:function(data) {
                    var pages = Math.ceil(parseInt(data) / 2);
                    var text="";
                    for (var i = 1; i <= pages; i++) {
                        text=text+'<li><a id='+i+'>'+i+'</a></li>';
                    }
                    $('#user_pages').html(text);
                }
            });
        }

    </script>
</body>
</html>
