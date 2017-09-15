$(function (){
    var loginForm = $("#loginForm").validate({});

    $("#btnLogin").click(function(){
        if(loginForm.form()) {
            var username = $("#username").val();
            var password = $("#password").val();
            var person = {
                username: username,
                password: password
            };
            $.ajax({
                url: basePath + "/admin/login",
                type: "POST",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(person),
                success: function (data) {
                    if (data.code === 2001) {
                        window.location.href = "http://localhost:3000/#/dashboard/index";
                    }
                }
            });
        }
    });
});

