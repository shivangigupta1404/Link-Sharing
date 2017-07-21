<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container-fluid border-black-1 round-10 padding-10" id="navbar">
    <div class="row font-lg">
        <div class="col-xs-offset-2 col-xs-10 col-sm-offset-0 col-sm-8" id="brand">
            <a href="/"><u>Link Sharing</u></a>
        </div>
        <div class="col-xs-offset-1 col-xs-10 col-sm-offset-0 col-sm-3">
            <form style="margin-bottom:0px;">
                <div class="form-group" style="margin-bottom:0px;">
                    <input type="text" name="search" placeholder="Search.." id="search_offline">
                </div>
            </form>
        </div>
    </div>
</div>

<script src="/resources/js/jquery.min.js"></script>

<%--
<script>
    $('#search_offline').on('input',function(){
        jQuery.ajax({
            url:"/search/offline/",
            type: 'POST',
            dataType: 'json',
            data:{'text':$('#search_offline').val()},
            success:function (response) {
                var returnedData = JSON.parse(response);
                console.log(returnedData);
                $('#main_body .row').hide();
                var myTable="";
                $.each(response, function(i, obj) {
                    myTable += "<tr><td>"+obj.id+"</td></tr>";
                });
                $('#search_result').html(myTable);
            }
        });
    })
</script>--%>
