$(function(){
    var user = JSON.parse($.session.get("user"));
    var baseInfo = $("#editUserForm2");

    baseInfo.find("#userid").val(user.userid);
    baseInfo.find("#username").val(user.username);
    baseInfo.find("#firstname").val(user.firstname);
    baseInfo.find("#lastname").val(user.lastname);
    baseInfo.find("#birthday").val(user.birthday);
    baseInfo.find("#email").val(user.email);
    baseInfo.find("#qq").val(user.qq);
    baseInfo.find("#weChat").val(user.weChat);
    baseInfo.find("#tel").val(user.tel);
    baseInfo.find("#dorm").val(user.dorm);
    baseInfo.find("#pwd").val(user.password);

    $("#confirm").click(function (){
        var formdata = {
            userid    : $("#userid").val(),
            username  : $("#username").val(),
            firstname : $("#firstname").val(),
            lastname  : $("#lastname").val(),
            birthday  : $("#birthday").val(),
            email     : $("#email").val(),
            weChat    : $("#weChat").val(),
            tel       : $("#tel").val(),
            dorm      : $("#dorm").val(),
            qq        : $("#qq").val(),
            password  : $("#pwd").val()
        };
        $.ajax({
            url:basePath+"/admin/user/update",
            type: "POST",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(formdata),
            success: function (data) {
                if (data.code === 2001)
                    Showbo.Msg.alert("更新成功!", function () {});
                else
                    Showbo.Msg.alert(data.msg, function () {});
            }
        })
    });
});
function removeStuFromCourse(studentid, crn){
    $.ajax({
        url: basePath + "/course/student/remove?studentid="+studentid+"&crn="+crn,
        type: "DELETE",
        async:false,
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            Showbo.Msg.alert(data.msg, function () {});
            return data;
        }
    });
}
