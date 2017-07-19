$(function(){
    $('#register_firstname').on('input',function(){
        $('#register_firstname_message').html("");
    });

    $('#register_lastname').on('input',function(){
        $('#register_lastname_message').html("");
    });

    $('#register_email').on('input',function(){
        $('#register_email_message').html("");
    });

    $('#register_username').on('input',function(){
        $('#register_username_message').html("");
    });

    $('#register_password').on('input',function(){
        $('#register_password_message').html("");
    });

    $('#register_confirm_password').on('input',function(){
        $('#register_confirm_password_message').html("");
    });

    $('#register_confirm_password').on('input', function () {
        if ($('#register_password').val() == $('#register_confirm_password').val()) {
            $('#register_confirm_password_message').html('Password Match').css('color', 'green');
        } else
            $('#register_confirm_password_message').html('Password does not match').css('color', 'red');
    });

    $('form textarea[minlength]').on('input', function(){
        e_len = $(this).val().trim().length
        e_min_len = Number($(this).attr('minlength'))
        message = e_min_len <= e_len ? '' : e_min_len + ' characters minimum'
        this.setCustomValidity(message)
    });

    $('#register_email').on('input',function(){
        email=$('#register_email').val();
        var url="/checkEmailAvailability";
        console.log(email);

        jQuery.ajax({
            url:url,
            data:{email:email},
            success:function (data) {
                console.log(data);
                if(data=="taken"){
                    $('#register_email_message').html('email address already registered').css('color', 'red');
                }
                else{
                    $('#register_email_message').html('').css('color', 'green');
                }
            }
        });
    });

    $('#register_username').on('input',function(){
        username=$('#register_username').val();
        var url="/checkUsernameAvailability/"+username;
        jQuery.ajax({
            url:url,
            success:function(data){
                if(data=="taken"){
                    $('#register_username_message').html('username not available').css('color', 'red');
                }
            }
        });
    });

    $('#register_form').submit(function (e) {
        var invalid=false;
        if(!$('#register_firstname').val()){
            $('#register_firstname_message').html('Please enter firstname').css('color', 'red');
            e.preventDefault();
            invalid=true;
        }
        if(!$('#register_lastname').val()){
            $('#register_lastname_message').html('Please enter lastname').css('color', 'red');
            e.preventDefault();
            invalid=true;
        }
        if(!$('#register_email').val()){
            $('#register_email_message').html("Please enter email").css('color','red');
            e.preventDefault();
            invalid=true;
        }
        if($('#register_username').val().length<6){
            $('#register_username_message').html('Minimum 6 characters').css('color','red');
            e.preventDefault();
            invalid=true;
        }
        if($('#register_password').val().length<6){
            $('#register_password_message').html('Minimum 6 characters').css('color','red');
            e.preventDefault();
            invalid=true;
        }
        if($('#register_password').val() != $('#register_confirm_password').val()) {
            $('#register_confirm_password_message').html('password does not match').css('color', 'red');
            e.preventDefault();
            invalid=true;
        }

        email=$('#register_email').val();
        var url="/checkEmailAvailability";
        console.log(url);
        jQuery.ajax({
            url:url,
            data:{email:email},
            success:function (data) {
                console.log(data);
                if(data=="taken"){
                    $('#register_email_message').html('email address already registered').css('color', 'red');
                    e.preventDefault();
                    invalid=true;
                }
                else
                {
                    username=$('#register_username').val();
                    var url="/checkUsernameAvailability/"+username;
                    jQuery.ajax({
                        url:url,
                        success:function(data){
                            if(data=="taken"){
                                $('#register_username_message').html('username not available').css('color', 'red');
                                e.preventDefault();
                                invalid=true;
                            }else{
                                if(invalid==false){
                                    $('#register_form').submit();
                                }

                            }
                        }
                    });
                }
            }
        });

    });

/*      jQuery.ajax({
            url: "/usertopicslist",
            async: false,
            success: function(data) {
                console.log(data);
                $('.topicList').html('<option value="">--Select Topic--</option>');
                $('.topicList').append(data);
            },
            error:function() {
                console.log("error");
            }

        });*/
});