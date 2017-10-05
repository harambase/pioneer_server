$(function () {
    //变量定义
    var status, gender, type, userid;
    var newStatus, newGender, newType;

    $("#userList").css({display:"block"});
    $("#addUser").css({display:"none"});

    $("#add").click(function () {
        $("#addUser").css({display:"block"});
        $("#userList").css({display:"none"});
    });
    $("#list").click(function () {
        $("#addUser").css({display:"none"});
        $("#userList").css({display:"block"});
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
            password  : hex_md5($("#pwd").val()),
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
                        resetPop();
                    });
                else
                    Showbo.Msg.alert(data.msg, function () {});
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
                            resetPop();
                        });
                    else
                        Showbo.Msg.alert(data.msg, function () {});
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
            password: hex_md5("pioneer" + userid)
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
                        resetPop();
                    });
                else
                    Showbo.Msg.alert(data.msg, function () {});
            }
        })

    });


    function closePop(ele) {
        $(ele).click(function () {
           resetPop();
        })
    }
    function resetPop(){
        $(".w_wrapper").css({display: "none"});
        $("[type='checkbox']").removeAttr("checked");//取消全选
        $(".account").siblings("li").removeClass("active");
        $(".advise").siblings("li").removeClass("active");
        $(".base-info").addClass("active");
        $(".w_pop").css({display: "none"});
        $(".w_pop")[0].style.display = "block";
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
        if(type.indexOf("f") !== -1){
            $("#assignSDiv").css({display: "block"});
            studentTable.draw();
        }
        else {
            $("#assignFDiv").css({display: "block"});
            facultyTable.draw();
        }

        $(".w_pop").css({display: "none"});
        $(".w_pop")[2].style.display = "block";

        if(type.indexOf("a")!==-1 && type.indexOf("f") === -1){
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
            {"data": "id", "title": "序列号", "width" : "100px"},
            {"data": "userid", "title": "用户ID"},
            {"data": "username", "title": "用户名"},
            {"data": "firstname", "title": "名"},
            {"data": "lastname", "title": "姓"},
            {"data": "type", "title": "账户类型"},
            {"data": "status", "title": "状态"},
            {"data": "updatetime", "title": "更新时间", "width" : "180px"},
            {
                "data": null, "title": "操作", "createdCell": function (nTd) {
                $(nTd).html('<button class="btn btn-info">删除用户</button><button class="btn btn-edit">编辑用户</button>');
                }, "width": "180px"
            }
        ],
        "columnDefs": [{
            orderable: false,
            targets: [8]
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

    //移除用户
    $("#userTable").on("click", ".btn.btn-info", function() {
        userid = $(this).parents("tr").find("td").eq(1).html();
        Showbo.Msg.confirm("确认删除该用户？",function(){
            if($(".btnfocus").val() !== "取消"){
                /*删除操作*/
                $.ajax({
                    url: basePath + "/admin/remove/user?userid="+userid,
                    type: "DELETE",
                    contentType: "application/json; charset=utf-8",
                    success: function (data) {
                        if (data.code === 2001)
                            Showbo.Msg.alert("删除成功!", function () {
                                logTable.draw();
                            });
                        else
                            Showbo.Msg.alert(data.msg, function () {});
                    }
                });
            }
        });
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
