$(function (){
    var loginForm = $("#loginForm").validate({});

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
                        $.session.set("user",JSON.stringify(user));
                        if(user.type.length === 2) {
                            if (user.type.indexOf("a")!== -1)
                                window.location.href = basePath + "/welcomeAdmin";
                            else if (user.type.indexOf("s")!== -1)
                                window.location.href = basePath + "/welcomeStudent";
                            else if (user.type.indexOf("f")!== -1)
                                window.location.href = basePath + "/welcomeFaculty";
                        }
                        else{
                            window.location.href = basePath + "/welcome";
                        }
                    }
                    else if(data.code === 2005)
                        Showbo.Msg.alert("系统异常!", function () {});
                    else
                        Showbo.Msg.alert("用户名或密码不正确!", function () {});
                    }
            })
        }
    });

    var user = JSON.parse($.session.get("user"));
    $("#adSys").css({display: "none"});
    $("#faSys").css({display: "none"});
    $("#stSys").css({display: "none"});
    if(user.type.indexOf("a") !== -1){
        $("#adSys").css({display: "block"});
    }
    if(user.type.indexOf("f") !== -1){
        $("#faSys").css({display: "block"});
    }
    if(user.type.indexOf("s") !== -1){
        $("#stSys").css({display: "block"});
    }

    $("#admin").click(function(){
        window.location.href = basePath + "/welcomeAdmin";
    });
    $("#faculty").click(function(){
        window.location.href = basePath + "/welcomeFaculty";
    });
    $("#student").click(function(){
        window.location.href = basePath + "/welcomeStudent";
    });
});

