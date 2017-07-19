$(function () {
    $('#confirmPassword').on('input', function () {
        if ($('#password').val() == $('#confirmPassword').val()) {
            $('#passwordMatchMessage').html('Password Match').css('color', 'green');
        } else
            $('#passwordMatchMessage').html('Password does not match').css('color', 'red');
    });

    $('form textarea[minlength]').on('input', function(){
        e_len = $(this).val().trim().length
        e_min_len = Number($(this).attr('minlength'))
        message = e_min_len <= e_len ? '' : e_min_len + ' characters minimum'
        this.setCustomValidity(message)
    });

    $('#register_email').on('input',function(){
        email=$('#register_email').val();
        var url="/checkEmailAvailability/"+email+" ";
        try{
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                    if(this.responseText=="taken") {
                        $('#register_email_message').html("email address exists").css('color','red');
                        return false;
                    }
                    else{
                        $('#register_email_message').html("success").css('color','green');
                        return true;
                    }
                }
            };
            xhttp.open("GET",url, true);
            xhttp.send();
        }catch(e){
            console.log("unable to connect");
        }
    });

    $('#register_username').on('input',function(){
        username=$('#register_username').val();
        var url="/checkUsernameAvailability/"+username;
        try{
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                    if(this.responseText=="taken") {
                        $('#register_username_message').html("username not available").css('color','red');
                        return false;
                    }
                    else{
                        $('#register_username_message').html("available").css('color','green');
                        return true;
                    }
                }
            };
            xhttp.open("GET",url, true);
            xhttp.send();
        }catch(e){
            console.log("unable to connect");
        }
    });

});
