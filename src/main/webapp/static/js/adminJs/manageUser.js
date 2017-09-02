$(function () {
    //变量定义
    var status, gender, type, userid;
    var newStatus, newGender, newType;
    var registerForm = $("#createUserForm").validate({});

    //添加用户
    $("#registerBtn").click(function () {
        if(registerForm.form()){
            var firstname = $("#firstname").val();
            var lastname = $("#lastname").val();
            var email = $("#email").val();
            var qq = $("#qq").val();
            var weChat = $("#weChat").val();
            var dorm = $("#dorm").val();
            var gender = "";
            var type="";
            var birthday = $("#birthday").val();
            var info = $("#year-semester").val();
            var comment = $("#comment").val();

            $('input[name="gender"]:checked').each(function(){
                gender = $(this).val();
            });

            $('input[name="type"]:checked').each(function () {
                type += $(this).val() + "/";
            });

            var person = {
                firstname: firstname,
                lastname: lastname,
                email: email,
                qq: qq,
                wechat: weChat,
                dorm: dorm,
                gender: gender,
                info: info,
                type: type,
                birthday:birthday,
                comment :comment
            };
            alert(JSON.stringify(person));
            $.ajax({
                url: basePath + "/admin/user/add",
                type: "POST",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(person),
                success: function (data) {
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
    });

    //更新用户信息
    $("#confirm").click(function (){
        var formdata = {
            userid    : $("#userid2").val(),
            username  : $("#username2").val(),
            firstname : $("#firstname2").val(),
            lastname  : $("#lastname2").val(),
            birthday  : $("#birthday2").val(),
            email     : $("#email2").val(),
            wechat    : $("#weChat2").val(),
            tel       : $("#tel2").val(),
            dorm      : $("#dorm2").val(),
            qq        : $("#qq2").val(),
            password  : $("#pwd").val(),
            comment   : $("#comment2").val()
        };
        $.ajax({
            url:basePath+"/admin/user/update",
            type: "POST",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(formdata),
            success: function (data) {
                if (data.code === 2001)
                    Showbo.Msg.alert("更新成功!", function () {
                        logTable.draw();
                    });
                else if(data.code === 2005)
                    Showbo.Msg.alert("系统异常!", function () {});
                else
                    Showbo.Msg.alert("更新失败!", function () {});
            }
        })
    });
    //更新用户状态
    $("#apply").click(function (){
        newType = "";
        $('input[name="type2"]:checked').each(function () {
            newType += $(this).val() + "/";
        });
        if(newStatus !== status || newType !== type || newGender !== gender) {
            var formdata = {
                userid: $("#userid2").val(),
                gender: newGender,
                status: newStatus,
                type: newType
            };
            $.ajax({
                url: basePath + "/admin/user/update",
                type: "POST",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(formdata),
                success: function (data) {
                    if (data.code === 2001)
                        Showbo.Msg.alert("更新成功!", function () {
                            logTable.draw();
                            writeSettings(newStatus, newType, newGender);
                        });
                    else if (data.code === 2005)
                        Showbo.Msg.alert("系统异常!", function () {});
                    else
                        Showbo.Msg.alert("更新失败!", function () {});
                }
            })
        }
        else{
            Showbo.Msg.alert("无任何更改!", function () {
                logTable.draw();
                writeSettings(newStatus, newType, newGender);
            });
        }
    });
    //重置用户密码
    $("#resetPassword").click(function () {
        var userid = $("#userid2").val();
        var formdata = {
            userid: userid,
            password: "pioneer" + userid
        };
        $.ajax({
            url: basePath + "/admin/user/update",
            type: "POST",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(formdata),
            success: function (data) {
                if (data.code === 2001)
                    Showbo.Msg.alert("更新成功!", function () {
                        logTable.draw();
                    });
                else if (data.code === 2005)
                    Showbo.Msg.alert("系统异常!", function () {});
                else
                    Showbo.Msg.alert("更新失败!", function () {});
            }
        })

    });
    //复选框控制
    $(".enable").click(function () {
        $(".w_manage input").eq(1).prop("checked", false);
        $(".w_manage input").eq(0).prop("checked", true);
        newStatus = $(".enable").val();
    });
    $(".disable").click(function () {
        $(".w_manage input").eq(0).prop("checked", false);
        $(".w_manage input").eq(1).prop("checked", true);
        newStatus = $(".disable").val();
    });
    $(".male").click(function () {
        $(".w_manage input").eq(6).prop("checked", false);
        $(".w_manage input").eq(5).prop("checked", true);
        newGender = $(".male").val();
    });
    $(".female").click(function () {
        $(".w_manage input").eq(5).prop("checked", false);
        $(".w_manage input").eq(6).prop("checked", true);
        newGender = $(".female").val();
    });
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

    function closePop(ele) {
        $(ele).click(function () {
            $(".w_wrapper").css({display: "none"});
            $("[type='checkbox']").removeAttr("checked");//取消全选
            $(".account").siblings("li").removeClass("active");
            $(".advise").siblings("li").removeClass("active");
            $(".base-info").addClass("active");
            $(".w_pop").css({display: "none"});
            $(".w_pop")[0].style.display = "block";
        })
    }

    $(".base-info").click(function () {
        $(this).siblings("li").removeClass("active");
        $(this).addClass("active");
        $(".w_pop").css({display: "none"});
        $(".w_pop")[0].style.display = "block";
    });

    $(".account").click(function () {
        $(this).siblings("li").removeClass("active");
        $(this).addClass("active");
        $(".w_pop").css({display: "none"});
        $(".w_pop")[1].style.display = "block";
    });

    $(".advise").click(function () {

        $(this).siblings("li").removeClass("active");
        $(this).addClass("active");
        $("#assignSDiv").css({display: "none"});
        $("#assignFDiv").css({display: "none"});
        if(type === "f") {
            $("#assignSDiv").css({display: "block"});
            studentTable.draw();
        }
        else {
            $("#assignFDiv").css({display: "block"});
            facultyTable.draw();
        }

        $(".w_pop").css({display: "none"});
        $(".w_pop")[2].style.display = "block";

        if(type === "a"){
            $(".w_pop").css({display: "none"});
            $(".w_pop")[2].style.display = "none";
        }
    });

    $(".w_manage span").click(function () {
        $(this).siblings("span").removeClass("active");
        $(this).addClass("active");
    });

    $(".status span").click(function () {
        $(this).siblings("span").removeClass("active");
        $(this).addClass("active");
    });


    //添加Advise
    $("#studentTable").on("click", ".btn.btn-info", function () {
        var facultyid = userid;
        var studentid = $(this).parents("tr").find("td").eq(1).html();
        assginAdvise(studentid, facultyid)
    });
    $("#facultyTable").on("click", ".btn.btn-info", function () {
        var studentid= userid;
        var facultyid = $(this).parents("tr").find("td").eq(1).html();
        assginAdvise(studentid, facultyid)
    });

    function assginAdvise(studentid, facultyid){
        var formdata = {
            facultyid : facultyid,
            studentid : studentid
        };
        $.ajax({
            url:basePath+"/admin/advise/assign",
            type: "POST",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(formdata),
            success: function (data) {
                if (data.code === 2001)
                    Showbo.Msg.alert("添加成功!", function () {});
                else if(data.code === 2005)
                    Showbo.Msg.alert("系统异常!", function () {});
                else
                    Showbo.Msg.alert(data.msg, function () {});
            }
        })
    }

    var logTable = $("#userTable").DataTable({

        "language": {
            "aria": {
                "sortAscending": ": activate to sort column ascending",
                "sortDescending": ": activate to sort column descending"
            },
            "emptyTable": "没有数据！",
            "info": "显示 _START_ 至 _END_ 条 ，总共_TOTAL_ 条数据",
            "infoEmpty": "没有发现记录！",
            "infoFiltered": "(从_MAX_条记录中搜索)",
            "lengthMenu": "显示: _MENU_",
            "search": "搜索:",
            "zeroRecords": "没有找到匹配的记录！",
            "paginate": {
                "previous": "上一页",
                "next": "下一页",
                "last": "尾页",
                "first": "首页"
            }
        },
        "lengthMenu": [
            [5,10],
            [5,10]
        ],
        pageLength: 10,
        processing: true,
        serverSide: true,

        ajax: {
            url: basePath + "/admin/user/list",

            data: function (d) {
                d.startTime = $("#inpstart").val();
                d.endTime = $("#inpend").val();
            }
        },
        columns: [
            {"data": "id", "title": "序列号", "width" : "45px"},
            {"data": "userid", "title": "用户ID"},
            {"data": "username", "title": "用户名"},
            {"data": "firstname", "title": "名"},
            {"data": "lastname", "title": "姓"},
            {"data": "password", "title": "密码"},
            {"data": "type", "title": "账户类型"},
            {"data": "status", "title": "状态"},
            {"data": "updatetime", "title": "更新时间"},
            {
                "data": null, "title": "操作", "createdCell": function (nTd) {
                $(nTd).html('<button class="btn btn-info">删除</button><button class="btn btn-edit">Edit</button>');
                }, "width": "200px"
            }
        ],
        "columnDefs": [{
            orderable: false,
            targets: [9]
        }, {
            "defaultContent": "",
            "targets": "_all"
        }]
    });
    var studentTable = $("#studentTable").DataTable({
        "language": {
            "aria": {
                "sortAscending": ": activate to sort column ascending",
                "sortDescending": ": activate to sort column descending"
            },
            "emptyTable": "没有数据！",
            "info": "显示 _START_ 至 _END_ 条 ，总共_TOTAL_ 条数据",
            "infoEmpty": "没有发现记录！",
            "infoFiltered": "(从_MAX_条记录中搜索)",
            "lengthMenu": "显示: _MENU_",
            "search": "搜索:",
            "zeroRecords": "没有找到匹配的记录！",
            "paginate": {
                "previous": "上一页",
                "next": "下一页",
                "last": "尾页",
                "first": "首页"
            }
        },
        "lengthMenu": [
            [5],
            [5]
        ],
        pageLength: 5,
        processing: true,
        serverSide: true,

        ajax: {
            url: basePath + "/admin/user/list",
            data: function (d) {
                d.type = "s";
                d.status = "1";
            }
        },
        columns: [
            {"data": "id", "title": "序列号","width":"45px"},
            {"data": "userid", "title": "用户ID"},
            {"data": "firstname", "title": "名"},
            {"data": "lastname", "title": "姓"},
            {
                "data": null, "title": "操作", "createdCell": function (nTd) {
                $(nTd).html('<button class="btn btn-info">选择</button>');
            }, "width": "100px"
            }
        ],
        "columnDefs": [{
            orderable: false,
            targets: [4]
        }, {
            "defaultContent": "",
            "targets": "_all"
        }]
    });
    var facultyTable = $("#facultyTable").DataTable({

        "language": {
            "aria": {
                "sortAscending": ": activate to sort column ascending",
                "sortDescending": ": activate to sort column descending"
            },
            "emptyTable": "没有数据！",
            "info": "显示 _START_ 至 _END_ 条 ，总共_TOTAL_ 条数据",
            "infoEmpty": "没有发现记录！",
            "infoFiltered": "(从_MAX_条记录中搜索)",
            "lengthMenu": "显示: _MENU_",
            "search": "搜索:",
            "zeroRecords": "没有找到匹配的记录！",
            "paginate": {
                "previous": "上一页",
                "next": "下一页",
                "last": "尾页",
                "first": "首页"
            }
        },
        "lengthMenu": [
            [5],
            [5]
        ],
        pageLength: 5,
        processing: true,
        serverSide: true,

        ajax: {
            url: basePath + "/admin/user/list",
            data: function (d) {
                d.type = "f";
                d.status = "1";
            }
        },
        columns: [
            {"data": "id", "title": "序列号","width":"45px"},
            {"data": "userid", "title": "用户ID"},
            {"data": "firstname", "title": "名"},
            {"data": "lastname", "title": "姓"},
            {
                "data": null, "title": "操作", "createdCell": function (nTd) {
                $(nTd).html('<button class="btn btn-info">选择</button>');
            }, "width": "100px"
            }
        ],
        "columnDefs": [{
            orderable: false,
            targets: [4]
        }, {
            "defaultContent": "",
            "targets": "_all"
        }]
    });

});
//获取用户
function getUser(userid){
    var user = null;
    $.ajax({
        url : basePath+"/admin/get?userid="+userid,
        type : "GET",
        async: false,
        success: function (result) {
            if (result.code === 2001) {
                user = result.data;
            } else {
                Showbo.Msg.alert("用户获取失败", function () {});
            }
        }
    });
    return user;
}
