$(function (){
    var loginForm = $("#fm1").validate({});

    $("#btnLogin").click(function(){
        if(loginForm.form()){
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
                        var user = data.data;
                        if(user.type === "a")
                            window.location.href = basePath + "/welcomeAdmin";
                        else if(user.type === "s")
                            window.location.href = basePath + "/welcomeStudent";
                        else if(user.type === "f")
                            window.location.href = basePath + "/welcomeFaculty";
                    }
                    else if(data.code === 2005)
                        Showbo.Msg.alert("系统异常!", function () {});
                    else
                        Showbo.Msg.alert("用户名或密码不正确!", function () {});
                    }
            })
        }
    });
});

