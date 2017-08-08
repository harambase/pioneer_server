$(function () {
    var registerForm = $("#createUserForm").validate({});
    $("#registerBtn").click(function () {
        if(registerForm.form()){
            var firstname = $("#firstname").val();
            var lastname = $("#lastname").val();
            var email = $("#email").val();
            var qq = $("#qq").val();
            var weChat = $("#weChat").val();
            var dorm = $("#dorm").val();
            var gender = $("#gender").val();
            var info = $("#year-semester").val();
            var type = $("#type").val();

            var person = {
                firstname: firstname,
                lastname: lastname,
                email: email,
                qq: qq,
                weChat: weChat,
                dorm: dorm,
                gender: gender,
                info: info,
                type: type
            };
            $.ajax({
                url: basePath + "/admin/adduser",
                type: "POST",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(person),
                success: function (data) {
                    console.log(data);
                    if (data.code === 2001) {
                        Showbo.Msg.alert("添加成功!", function () {
                            window.location.reload();
                        });
                    }
                    else if (data.code === 2005) {
                        Showbo.Msg.alert("系统异常!", function () {} );
                    }
                    else
                        Showbo.Msg.alert(data.msg, function () {});
                }
            })
        }
    })
});