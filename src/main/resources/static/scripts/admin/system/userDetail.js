//写入账户属性
function writeSettings(status, type, gender){

    if (status === "1") {
        $(".w_manage h4").eq(0).html("账户状态：启用");
        $(".w_manage input").eq(0).prop("checked", true);
    }
    else {
        $(".w_manage h4").eq(0).html("账户状态：禁用");
        $(".w_manage input").eq(1).prop("checked", true);
    }
    var text = "账户类型：";
    if(type.indexOf("a") !== -1){
        text += "系统管理员/";
        $(".w_manage input").eq(4).prop("checked", true);
    }
    if(type.indexOf("f") !== -1){
        text += "教师/";
        $(".w_manage input").eq(3).prop("checked", true);
    }
    if(type.indexOf("s") !== -1){
        text += "学生/";
        $(".w_manage input").eq(2).prop("checked", true);
    }
    $(".w_manage h4").eq(1).html(text);
    if(gender === "male"){
        $(".w_manage h4").eq(2).html("用户性别：男");
        $(".w_manage input").eq(5).prop("checked", true);
    }
    else{
        $(".w_manage h4").eq(2).html("用户性别：女");
        $(".w_manage input").eq(6).prop("checked", true);
    }
}
//编辑弹窗
$("#userTable").on("click", ".btn.btn-edit", function () {

    userid = $(this).parents("tr").find("td").eq(1).html();
    var user = getUser(userid);
    var baseInfo = $(".w_basicInfo");

    baseInfo.find("#userid2").val(userid);
    baseInfo.find("#username2").val(user.username);
    baseInfo.find("#firstname2").val(user.firstname);
    baseInfo.find("#lastname2").val(user.lastname);
    baseInfo.find("#birthday2").val(user.birthday);
    baseInfo.find("#email2").val(user.email);
    baseInfo.find("#qq2").val(user.qq);
    baseInfo.find("#weChat2").val(user.wechat);
    baseInfo.find("#tel2").val(user.tel);
    baseInfo.find("#dorm2").val(user.dorm);
    baseInfo.find("#pwd").val(user.password);
    baseInfo.find("#comment2").val(user.comment);
    newStatus = status = user.status;
    newType = type = user.type;
    newGender = gender = user.gender;

    writeSettings(status, type, gender);

    $(".w_wrapper").css({display: "block"});
    $(".w_ul li").remove();

    closePop("#cancel");
    closePop("#cancel2");
    closePop("#cancel3");
    closePop(".w_close");
});