$(function () {
    //变量定义
    var status, gender, type;
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
            var gender = $("#gender").val();
            var info = $("#year-semester").val();
            var type = $("#type").val();
            var birthday = $("#birthday").val();

            var person = {
                firstname: firstname,
                lastname: lastname,
                email: email,
                qq: qq,
                weChat: weChat,
                dorm: dorm,
                gender: gender,
                info: info,
                type: type,
                birthday:birthday
            };
            $.ajax({
                url: basePath + "/admin/user/add",
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
                    Showbo.Msg.alert("教师获取失败", function () {});
                }
            }
        });
        return user;
    }
    //更新用户信息
    $("#confirm").click(function (){
        var formdata = {
            userid :  $("#userid2").val(),
            username  : $("#username2").val(),
            firstname : $("#firstname2").val(),
            lastname : $("#lastname2").val(),
            birthday : $("#birthday2").val(),
            email : $("#email2").val(),
            weChat : $("#weChat2").val(),
            tel : $("#tel2").val(),
            dorm : $("#dorm2").val(),
            qq : $("#firstname2").val(),
            password : $("#pwd").val()
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
    $(".student").click(function () {
        $(".w_manage input").eq(3).prop("checked", false);
        $(".w_manage input").eq(4).prop("checked", false);
        $(".w_manage input").eq(2).prop("checked", true);
        newType = $(".student").val();
    });
    $(".teacher").click(function () {
        $(".w_manage input").eq(2).prop("checked", false);
        $(".w_manage input").eq(4).prop("checked", false);
        $(".w_manage input").eq(3).prop("checked", true);
        newType = $(".teacher").val();
    });
    $(".admin").click(function () {
        $(".w_manage input").eq(2).prop("checked", false);
        $(".w_manage input").eq(3).prop("checked", false);
        $(".w_manage input").eq(4).prop("checked", true);
        newType = $(".admin").val();
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
            $(".w_manage h4").eq(0).html("Account Status: ACTIVE");
            $(".w_manage input").eq(0).prop("checked", true);
        }
        else {
            $(".w_manage h4").eq(0).html("Account Status: DEACTIVE");
            $(".w_manage input").eq(1).prop("checked", true);
        }

        if(type === "a"){
            $(".w_manage h4").eq(1).html("Account Type: Administrator");
            $(".w_manage input").eq(4).prop("checked", true);
        }
        else if(type === "f"){
            $(".w_manage h4").eq(1).html("Account Type: Faculty");
            $(".w_manage input").eq(3).prop("checked", true);
        }
        else{
            $(".w_manage h4").eq(1).html("Account Type: Student");
            $(".w_manage input").eq(2).prop("checked", true);
        }

        if(gender === "male"){
            $(".w_manage h4").eq(2).html("User Gender: Male");
            $(".w_manage input").eq(5).prop("checked", true);
        }
        else{
            $(".w_manage h4").eq(2).html("User Gender: Female");
            $(".w_manage input").eq(6).prop("checked", true);
        }
    }
    //编辑弹窗
    $("#userTable").on("click", ".btn.btn-edit", function () {

        var userid = $(this).parents("tr").find("td").eq(1).html();
        var user = getUser(userid);
        var baseInfo = $(".w_basicInfo");

        baseInfo.find("#userid2").val(userid);
        baseInfo.find("#username2").val(user.username);
        baseInfo.find("#firstname2").val(user.firstname);
        baseInfo.find("#lastname2").val(user.lastname);
        baseInfo.find("#birthday2").val(user.birthday);
        baseInfo.find("#email2").val(user.email);
        baseInfo.find("#qq2").val(user.qq);
        baseInfo.find("#weChat2").val(user.weChat);
        baseInfo.find("#tel2").val(user.tel);
        baseInfo.find("#dorm2").val(user.dorm);
        baseInfo.find("#pwd").val(user.password);
        newStatus = status = user.status;
        newType = type = user.type;
        newGender = gender = user.gender;

        writeSettings(status, type, gender);

        $(".w_wrapper").css({display: "block"});
        $(".w_ul li").remove();

        closePop("#cancel");
        closePop("#cancel2");
        closePop(".w_close");
    });

    function closePop(ele) {
        $(ele).click(function () {
            $(".w_wrapper").css({display: "none"});
            $("[type='checkbox']").removeAttr("checked");//取消全选
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

    $(".join-in").click(function () {
        $(this).siblings("li").removeClass("active");
        $(this).addClass("active");
        $(".w_pop").css({display: "none"});
        $(".w_pop")[2].style.display = "block";
    });

    $(".w_manage span").click(function () {
        $(this).siblings("span").removeClass("active");
        $(this).addClass("active");
    });

    $(".status span").click(function () {
        $(this).siblings("span").removeClass("active");
        $(this).addClass("active");
    });

    var logTable = $("#userTable").DataTable({

        "language": {
            "aria": {
                "sortAscending": ": activate to sort column ascending",
                "sortDescending": ": activate to sort column descending"
            },
            "emptyTable": "No Data Founded！",
            "info": "SHOW FROM _START_ TO _END_ ，TOTAL OF _TOTAL_ RECORDS",
            "infoEmpty": "NO RECORDS FOUND！",
            "infoFiltered": "(SEARCH FROM _MAX_ RECORDS)",
            "lengthMenu": "SHOW: _MENU_",
            "search": "SEARCH:",
            "zeroRecords": "No Record Found！",
            "paginate": {
                "previous": "Previous",
                "next": "Next",
                "last": "Last",
                "first": "First"
            }
        },
        "lengthMenu": [
            [5, 10, 25, 50],
            [5, 10, 25, 50]
        ],
        pageLength: 5,
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
            {"data": "id", "title": "serial"},
            {"data": "userid", "title": "userid"},
            {"data": "username", "title": "username"},
            {"data": "firstname", "title": "firstname"},
            {"data": "lastname", "title": "lastname"},
            {"data": "password", "title": "password"},
            {"data": "type", "title": "type"},
            {"data": "status", "title": "status"},
            {"data": "updatetime", "title": "updateTime"},
            {
                "data": null, "title": "Tool", "createdCell": function (nTd) {
                $(nTd).html('<button class="btn btn-info">Delete</button><button class="btn btn-edit">Edit</button>');
            }, "width": "100px"
            }
        ],
        "columnDefs": [{
            orderable: false,
            targets: [7]
        }, {
            "defaultContent": "",
            "targets": "_all"
        }]
    });

});
