$(function(){
    var regForm = $("#regForm").validate({});
    $("#submit").click(function () {
        if(regForm){
            var formdata = {
                info: $("#info").val(),
                name : $("#inputName3").val(),
                email : $("#inputEmail3").val(),
                password : $("#inputPassword3").val(),
                qq : $("#qq").val(),
                tel : $("#tel").val(),
                birthday : $("#birthday").val()
            };
            $.ajax({
                url: basePath + "/admin/register",
                type: "POST",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(formdata),
                success: function (data) {
                    if (data.code === 2001)
                        Showbo.Msg.alert("注册成功!", function () {});
                    else
                        Showbo.Msg.alert(data.msg, function () {});
                }

            })
        }
    });
})

