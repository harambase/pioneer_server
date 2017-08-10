$(function () {
    //变量定义
    var facultyids = "";
    var newStatus, newGender, newType;
    var createCourseForm = $("#createCourseForm").validate({});

    //教师列表
    $("#searchFValue").bind("input propertychange", function () {
        var search = $("#searchFValue").val();
        $(".w_selected").css({display: "block"});
        $(".w_selected li").remove();
        $.ajax({
            url : basePath+"/admin/list/faculty?search="+search,
            type : "GET",
            success: function (result) {
                if (result.code === 2001) {
                    for (var i = 0; i < result.data.length; i++) {
                        var userid = result.data[i].userid;
                        var temp = "<li data-id=" + userid + ">" + result.data[i].firstname
                            + result.data[i].lastname+ "</li>";
                        $(".w_selected").append(temp);
                        $(".w_selected").css({height: "180px", overflow: "auto"});
                        $(".w_selected").find("li").css({
                            lineHeight: "30px",
                            paddingLeft: "15px",
                            backgroundColor: "#fff",
                            cursor: "pointer"
                        });
                        $(".w_selected").find("li").hover(function () {
                            $(this).css({background: "#498BD5", color: "#fff"});
                        }, function () {
                            $(this).css({background: "#fff", color: "#333"});
                        });
                        $("#searchFValue").blur(function () {
                            setTimeout(function () {
                                $(".w_selected").hide();
                            }, 200)

                        });
                        $(".w_selected").find("li").click(function () {
                            $("#searchFValue").val(this.innerHTML);
                        })
                    }

                } else {
                    Showbo.Msg.alert("获取失败", function () {});
                }
            }
        });
    });
    $(".w_selected").on("click", "li", function () {
        $("#searchFValue").val($(this).data("id"));
        $("#searchFValue").data("userid", $(this).data("id"));
        $(".w_selected").css({display: "none"});
    });
    $("#addf-button").click(function () {
        facultyids = $("#searchFValue").data("userid");
        if(facultyids === ""){
            var faculty = $("#searchFValue").val();
            getFaulcty(faculty);
        }
        else
            Showbo.Msg.alert("认证成功", function () {});

    });
    //搜索教师
    function getFaulcty(faculty){
        var isSucc = false;
        $.ajax({
            url : basePath+"/admin/list/faculty?search="+faculty,
            type : "GET",
            async: false,
            success: function (result) {
                if (result.code === 2001) {
                    if(result.data.length !== 1){
                        Showbo.Msg.alert("教师获取失败", function () {});
                    }
                    else if(result.data.length === 1){
                        facultyids = result.data[i].userid;
                        isSucc = true;
                        Showbo.Msg.alert("教师获取成功", function () {});
                    }
                } else {
                    Showbo.Msg.alert("教师获取失败", function () {});
                }
            }
        });
        return isSucc;
    }

    //课程列表

    //搜索课程

    //添加课程
    $("#registerBtn").click(function () {
        if(createCourseForm.form()) {
            var name = $("#name").val();
            var credits = $("#credits").val();
            var coulev = $("#coulev").val();
            var cousec = $("#cousec").val();
            var startdate = $("#startdate").val();
            var enddate = $("#enddate").val();
            var starttime = $("#starttime").val();
            var endtime = $("#endtime").val();
            var capa = $("#capa").val();
            var day = "";
            var info = $("#year-semester").val();

            if (facultyids !== "") {

                $('input[name="day"]:checked').each(function () {
                    day += $(this).val() + "/";
                });

                console.log(facultyids);

                var course = {
                    credits: credits,
                    coulev: coulev,
                    name: name,
                    cousec: cousec,
                    startdate: startdate,
                    enddate: enddate,
                    starttime: starttime,
                    endtime: endtime,
                    capa: capa,
                    facultyid: facultyids,
                    day: day,
                    info: info
                };

                $.ajax({
                    url: basePath + "/course/add",
                    type: "POST",
                    contentType: "application/json; charset=utf-8",
                    data: JSON.stringify(course),
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
            else
                Showbo.Msg.alert("教师信息获取失败", function () {});

        }
    });
    //获取用户
    function getUser(userid){
        var user = new Object();
        $.ajax({
            url: basePath + "/admin/get?userid="+userid,
            type: "GET",
            contentType: "application/json; charset=utf-8",
            async : false,
            success: function (data) {
                if (data.code === 2001)
                    user = data.data;
                else if(data.code === 2005)
                    Showbo.Msg.alert("系统异常!", function () {});
                else
                    Showbo.Msg.alert("获取失败!", function () {});
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
        $(".w_manage input").eq(1).attr("checked", false);
        $(".w_manage input").eq(0).attr("checked", true);
        newStatus = $(".enable").val();
    });
    $(".disable").click(function () {
        $(".w_manage input").eq(0).attr("checked", false);
        $(".w_manage input").eq(1).attr("checked", true);
        newStatus = $(".disable").val();
    });
    $(".student").click(function () {
        $(".w_manage input").eq(3).attr("checked", false);
        $(".w_manage input").eq(4).attr("checked", false);
        $(".w_manage input").eq(2).attr("checked", true);
        newType = $(".student").val();
    });
    $(".teacher").click(function () {
        $(".w_manage input").eq(2).attr("checked", false);
        $(".w_manage input").eq(4).attr("checked", false);
        $(".w_manage input").eq(3).attr("checked", true);
        newType = $(".teacher").val();
    });
    $(".admin").click(function () {
        $(".w_manage input").eq(2).attr("checked", false);
        $(".w_manage input").eq(3).attr("checked", false);
        $(".w_manage input").eq(4).attr("checked", true);
        newType = $(".admin").val();
    });
    $(".male").click(function () {
        $(".w_manage input").eq(6).attr("checked", false);
        $(".w_manage input").eq(5).attr("checked", true);
        newGender = $(".male").val();
    });
    $(".female").click(function () {
        $(".w_manage input").eq(5).attr("checked", false);
        $(".w_manage input").eq(6).attr("checked", true);
        newGender = $(".female").val();
    });
    //写入账户属性
    function writeSettings(status, type, gender){
        if (status === "1") {
            $(".w_manage h4").eq(0).html("Account Status: ACTIVE");
            $(".w_manage input").eq(0).attr("checked", true);
        }
        else {
            $(".w_manage h4").eq(0).html("Account Status: DEACTIVE");
            $(".w_manage input").eq(1).attr("checked", true);
        }

        if(type === "a"){
            $(".w_manage h4").eq(1).html("Account Type: Administrator");
            $(".w_manage input").eq(4).attr("checked", true);
        }
        else if(type === "f"){
            $(".w_manage h4").eq(1).html("Account Type: Faculty");
            $(".w_manage input").eq(3).attr("checked", true);
        }
        else{
            $(".w_manage h4").eq(1).html("Account Type: Student");
            $(".w_manage input").eq(2).attr("checked", true);
        }

        if(gender === "male"){
            $(".w_manage h4").eq(2).html("User Gender: Male");
            $(".w_manage input").eq(5).attr("checked", true);
        }
        else{
            $(".w_manage h4").eq(2).html("User Gender: Female");
            $(".w_manage input").eq(6).attr("checked", true);
        }
    }
    //编辑弹窗
    $("#userTable").on("click", ".btn.btn-edit", function () {

        var userid = $(this).parents("tr").find("td").eq(0).html();
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
    });

    function closePop(ele) {
        $(ele).click(function () {
            $(".w_wrapper").css({display: "none"});
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

    $(".w_close").click(function () {
        $(".w_wrapper").css({display: "none"});

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
            {"data": "createtime", "title": "createTime"},
            {"data": "updatetime", "title": "updateTime"},
            {
                "data": null, "title": "Tool", "createdCell": function (nTd) {
                $(nTd).html('<button class="btn btn-info">Delete</button><button class="btn btn-edit">Edit</button>');
            }, "width": "100px"
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

});
