
$(function(){
    var gender, id, password, info, userJson, status = "0", createtime, userid, viewStatus = "";

    var editUserForm = $("#editUserForm").validate({});

    $("#general").click(function(){
        viewStatus = "";
        userReg.draw();
    });
    $("#active").click(function(){
        viewStatus = "0";
        userReg.draw();
    });
    $("#declined").click(function(){
        viewStatus = "-1";
        userReg.draw();
    });
    $("#approved").click(function(){
        viewStatus = "1";
        userReg.draw();
    });

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
    //拒绝
    $("#userReg").on("click", ".btn.btn-decline", function () {
        id = $(this).parents("tr").find("td").eq(0).html();
        createtime = $(this).parents("tr").find("td").eq(2).html();
        userid = $(this).parents("tr").find("td").eq(1).html();
        status = "-1";
        //先拿到点击的行号
        var rowIndex = $(this).parents("tr").index();
        //此处拿到隐藏列的id
        userJson = $("#userReg").DataTable().row(rowIndex).data().userJson;

        updateTempUser(userid);
    });
    $("#decline").click(function () {
        status = "-1";
        updateTempUser(userid);
    });

    //批准
    $("#apply").click(function () {
        status = "1";
        if(editUserForm.form()) {
            var firstname = $("#firstname2").val();
            var lastname = $("#lastname2").val();
            var email = $("#email2").val();
            var qq = $("#qq2").val();
            var birthday = $("#birthday2").val();
            var comment = $("#comment2").val();
            var type = "";

            $('input[name="type2"]:checked').each(function () {
                type += $(this).val() + "/";
            });

            var person = {
                userid: userid,
                firstname: firstname,
                lastname: lastname,
                email: email,
                qq: qq,
                gender: gender,
                type: type,
                info: info,
                birthday: birthday,
                comment: comment,
                password: password
            };
            $.ajax({
                url: basePath + "/admin/user/add",
                type: "POST",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(person),
                success: function (data) {
                    if (data.code === 2001) {
                        updateTempUser(userid);
                        Showbo.Msg.alert("添加成功!", function () {
                            userReg.draw();
                        });
                    }
                    else
                        Showbo.Msg.alert(data.msg, function () {});
                }
            });
        }
    });

    function updateTempUser(userid){
        var tempUser = {
            id: id,
            userJson: userJson,
            userid: userid,
            status: status,
            createtime: createtime
        };
        $.ajax({
            url: basePath + "/request/update/user",
            type: "POST",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(tempUser),
            success: function (data) {
                if(data.code === 2001 && status === "-1"){
                    resetPop();
                    userReg.draw();
                    Showbo.Msg.alert(data.msg, function () {});
                }else if (data.code !== 2001)
                    Showbo.Msg.alert(data.msg, function () {});
            }
        });
    }
    //详情弹窗
    $("#userReg").on("click", ".btn.btn-edit", function () {

        id = $(this).parents("tr").find("td").eq(0).html();
        createtime = $(this).parents("tr").find("td").eq(2).html();
        userid = $(this).parents("tr").find("td").eq(1).html();

        //先拿到点击的行号
        var rowIndex = $(this).parents("tr").index();
        //此处拿到隐藏列的id
        userJson = $("#userReg").DataTable().row(rowIndex).data().userJson;
        var user = JSON.parse(userJson);

        $("#userid2").val(userid);
        $("#username2").val(user.username);
        $("#firstname2").val(user.firstname);
        $("#lastname2").val(user.lastname);
        $("#birthday2").val(user.birthday);
        $("#email2").val(user.email);
        $("#qq2").val(user.qq);
        $("#tel2").val(user.tel);
        password = user.password;
        info = user.info;
        gender = user.gender;

        if(user.gender === "male"){
            $(".w_manage h4").eq(1).html("用户性别：男");
            $(".w_manage input").eq(3).prop("checked", true);
        }
        else{
            $(".w_manage h4").eq(1).html("用户性别：女");
            $(".w_manage input").eq(4).prop("checked", true);
        }
        $(".w_wrapper").css({display: "block"});
        $(".w_ul li").remove();

        closePop("#cancel");
        closePop("#cancel2");
        closePop(".w_close");
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
    //复选框控制
    $(".enable").click(function () {
        $(".w_manage input").eq(1).prop("checked", false);
        $(".w_manage input").eq(0).prop("checked", true);
        status = $(".enable").val();
    });
    $(".disable").click(function () {
        $(".w_manage input").eq(0).prop("checked", false);
        $(".w_manage input").eq(1).prop("checked", true);
        status = $(".disable").val();
    });
    $(".male").click(function () {
        $(".w_manage input").eq(6).prop("checked", false);
        $(".w_manage input").eq(5).prop("checked", true);
        gender = $(".male").val();
    });
    $(".female").click(function () {
        $(".w_manage input").eq(5).prop("checked", false);
        $(".w_manage input").eq(6).prop("checked", true);
        gender = $(".female").val();
    });

    var userReg = $("#userReg").DataTable({

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
            url: basePath + "/request/user/list",

            data: function (d) {
                d.viewStatus = viewStatus;
            }

        },
        columns: [
            {"data": "id", "title": "序列号", "width" : "100px"},
            {"data": "userid", "title": "用户ID"},
            {"data": "createtime", "title": "创建时间"},
            {"data": "status", "title": "申请状态", "createdCell": function (nTd, rowData) {
                    if(rowData === "0") $(nTd).html("申请中");
                    else if(rowData === "1") $(nTd).html("已批准");
                    else if(rowData === "-1") $(nTd).html("已拒绝");
                }
            },
            {"data": "userJson", "title": "用户JSON","bVisible": false},
            {"data": "status", "title": "操作", "createdCell": function (nTd, rowData) {
                    if(rowData === "0") {
                        var htmlStr = '<button class="btn btn-edit btn-warning">查看详情</button>';
                        htmlStr += '<button  class="btn btn-decline">拒绝申请</button>';
                        $(nTd).html(htmlStr);
                    } else
                        $(nTd).html("无可用操作");

                }, "width": "200px"
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
