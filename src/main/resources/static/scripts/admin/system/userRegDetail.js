var student = false;
var faculty = false;
var admin = false;
var yes = false;
var reset = false;
var password = "";
var id = location.search.split("&")[0].split("=")[1];
var userid = location.search.split("&")[1].split("=")[1];

var editUserForm = $("#editUserForm").validate({});

// //拒绝
// $("#userReg").on("click", ".btn.btn-decline", function () {
//     id = $(this).parents("tr").find("td").eq(0).html();
//     createtime = $(this).parents("tr").find("td").eq(2).html();
//     userid = $(this).parents("tr").find("td").eq(1).html();
//     status = "-1";
//     //先拿到点击的行号
//     var rowIndex = $(this).parents("tr").index();
//     //此处拿到隐藏列的id
//     userJson = $("#userReg").DataTable().row(rowIndex).data().userJson;
//     updateTempUser(userid);
// });
//
// $("#decline").click(function () {
//     status = "-1";
//     updateTempUser(userid);
// });


// function updateTempUser(userid){
//     var tempUser = {
//         id: id,
//         userJson: userJson,
//         userid: userid,
//         status: status,
//         createtime: createtime
//     };
//     $.ajax({
//         url: basePath + "/request/update/user",
//         type: "POST",
//         contentType: "application/json; charset=utf-8",
//         data: JSON.stringify(tempUser),
//         success: function (data) {
//             if(data.code === 2001 && status === "-1"){
//                 Showbo.Msg.alert(data.msg, function () {});
//             }else if (data.code !== 2001)
//                 Showbo.Msg.alert(data.msg, function () {});
//         }
//     });
// }

var registerForm = $("#registerUserForm").validate({});

//性别
$("#male-div").click(function (){
    $("#female").prop("checked", false);
    $("#male").prop("checked", true);
});
$("#female-div").click(function (){
    $("#male").prop("checked", false);
    $("#female").prop("checked", true);
});

//角色
$("#student-div").click(function(){
    $("#student1").prop("checked", !student);
    student = !student;
});
$("#faculty-div").click(function(){
    $("#faculty1").prop("checked", !faculty);
    faculty = !faculty;
});
$("#admin-div").click(function(){
    $("#admin1").prop("checked", !admin);
    admin = !admin;
});

//确认
$("#yes-div").click(function(){
    $("#yes").prop("checked", !yes);
    yes = !yes;
});


$(function(){
    var user = JSON.parse(localStorage.getItem("user"));
    localStorage.clear();

    $("#userid").val(userid);
    $("#year-semester").val(user.info);
    $("#username").val(user.username);
    $("#firstname").val(user.firstname);
    $("#lastname").val(user.lastname);
    $("#birthday").val(user.birthday);
    $("#email").val(user.email);
    $("#qq").val(user.qq);
    $("#weChat").val(user.wechat);
    $("#tel").val(user.tel);
    $("#dorm").val(user.dorm);
    $("#comments").val(user.comment);

    password = user.password;

    if(user.gender === "male")
        $("#male").prop("checked", true);
    else
        $("#female").prop("checked", true);

});

//批准
$("#approve").click(function (){
    if(registerForm.form()) {
        var gender = "";
        var type = "";
        var status = "";

        $('input[name="gender"]:checked').each(function () {
            gender = $(this).val();
        });

        $('input[name="type"]:checked').each(function () {
            type += $(this).val() + "/";
        });

        $('input[name="status"]:checked').each(function () {
            status = $(this).val();
        });

        if (reset)
            password = hex_md5("pioneer" + userid);

        var formdata = {
            userid: $("#userid").val(),
            username: $("#username").val(),
            firstname: $("#firstname").val(),
            lastname: $("#lastname").val(),
            birthday: $("#birthday").val(),
            email: $("#email").val(),
            wechat: $("#weChat").val(),
            tel: $("#tel").val(),
            dorm: $("#dorm").val(),
            qq: $("#qq").val(),
            comment: $("#comments").val(),
            password: password,
            gender: gender,
            type: type,
            status: "1"
        };

        $.ajax({
            url: basePath + "/admin/user/add",
            type: "POST",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(formdata),
            success: function (data) {
                if (data.code === 2001) {
                    //updateTempUser(userid);
                    Showbo.Msg.alert("添加成功!", function () {});
                }
                else
                    Showbo.Msg.alert(data.msg, function () {});
            }
        });
    }
});
