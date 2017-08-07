$(function (){
    var loginForm = $("#fm1").validate({});
    $("#btnLogin").click(function(){
        if(loginForm.form()){
            var username = $("#username").val();
            var password = $("#password").val();
            var person = {
                name: username,
                password: password
            };
            $.ajax({
                url: basePath + "/admin/login",
                type: "POST",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(person),
                success: function (data) {
                    if (data.code == 2100) {
                        window.location.href = basePath + "/welcomeAdmin";
                    } else {
                        Showbo.Msg.alert("用户名或密码不正确!", function () {});
                    }
                }
            })
        }
    })
});