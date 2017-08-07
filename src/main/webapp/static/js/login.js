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
                url: "/admin/login",
                type: "POST",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(person),
                success: function (data) {
                    if (data.code === 2001) {
                        if(data.data.type === "a")
                            window.location.href = basePath + "/admin/welcomeAdmin";
                        else if(data.data.type === "s")
                            window.location.href = basePath + "/student/welcomeStudent";
                        else if(data.data.type === "f")
                            window.location.href = basePath + "/faculty/welcomeFaculty";

                    } else {
                        Showbo.Msg.alert("用户名或密码不正确!", function () {});
                    }
                }
            })
        }
    })
});