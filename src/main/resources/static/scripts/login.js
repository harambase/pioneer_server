$(function (){
    var loginForm = $("#loginForm").validate({});
    $("#btnLogin").click(function(){
        if(loginForm.form()) {
            var userid = $("#userid").val();
            var password = hex_md5($("#password").val());
            var person = {
                userid: userid,
                password: password
            };
            $.ajax({
                url: basePath + "/admin/login",
                type: "POST",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(person),
                success: function (data) {
                    if (data.code === 2001)
                        window.location.href = basePath + "/welcome";
                    else
                        Showbo.Msg.alert("登录失败", function () {});
                }
            });
        }
    });
});

