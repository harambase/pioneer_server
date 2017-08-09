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

    var that, userid, user;

    $("#userTable").on("click", ".btn.btn-edit", function () {

        that = $(this);
        userid = $(this).parents("tr").find("td").eq(0).html();

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


        $(".w_basicInfo").find("#userid2").val(userid);
        $(".w_basicInfo").find("#userName2").val(user.username);
        $(".w_basicInfo").find("#firstname2").val(user.firstname);
        $(".w_basicInfo").find("#lastname2").val(user.lastname);
        $(".w_basicInfo").find("#birthday2").val(user.birthday);
        $(".w_basicInfo").find("#email2").val(user.email);
        $(".w_basicInfo").find("#qq2").val(user.qq);
        $(".w_basicInfo").find("#weChat2").val(user.weChat);
        $(".w_basicInfo").find("#tel2").val(user.tel);
        $(".w_basicInfo").find("#dorm2").val(user.dorm);

        if (user.status === "1") {
            $(".w_manage h4").eq(0).html("Account Status: ACTIVE");
            $(".w_manage input").eq(0).attr("checked", true);
            // $("input:radio[value='1']").attr('checked','true');
        }
        else {
            $(".w_manage h4").eq(0).html("Account Status: DEACTIVE");
            $(".w_manage input").eq(1).attr("checked", true);
        }

        if(user.type === "a"){
            $(".w_manage h4").eq(1).html("Account Type: Administrator");
            $(".w_manage input").eq(4).attr("checked", true);
        }
        else if(user.type === "f"){
            $(".w_manage h4").eq(1).html("Account Type: Faculty");
            $(".w_manage input").eq(3).attr("checked", true);
        }
        else{
            $(".w_manage h4").eq(1).html("Account Type: Student");
            $(".w_manage input").eq(2).attr("checked", true);
        }

        if(user.gender === "male"){
            $(".w_manage h4").eq(2).html("User Gender: Male");
            $(".w_manage input").eq(5).attr("checked", true);
        }
        else{
            $(".w_manage h4").eq(2).html("User Gender: Female");
            $(".w_manage input").eq(6).attr("checked", true);
        }

        $(".w_wrapper").css({display: "block"});
        $(".w_ul li").remove();

        closePop(".system-control-btn button");
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

    $(function () {
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
            order: [[8, "desc"]],
            // paging: false,
            ajax: {
                url: basePath + "/admin/user/list",

                data: function (d) {
                    d.startTime = $("#inpstart").val();
                    d.endTime = $("#inpend").val();
                }
            },
            columns: [

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
                targets: [0, 9]
            }, {
                "defaultContent": "",
                "targets": "_all"
            }]
        });

        var start = {
            format: 'YYYY-MM-DD',
            minDate: '2014-06-16 23:59:59', //设定最小日期为当前日期
            maxDate: $.nowDate({DD: 0}), //最大日期
            choosefun: function (elem, datas) {
                end.minDate = datas; //开始日选好后，重置结束日的最小日期
                endDates();
                logTable.draw();
            }
        };
        var end = {
            format: 'YYYY-MM-DD',
            minDate: $.nowDate({DD: 0}), //设定最小日期为当前日期
            maxDate: '2099-06-16 23:59:59', //最大日期
            choosefun: function (elem, datas) {
                start.maxDate = datas; //将结束日的初始值设定为开始日的最大日期
                logTable.draw();
            }
        };

        function endDates() {
            end.trigger = false;
            $("#inpend").jeDate(end);
        }

        $("#inpstart").jeDate(start);
        $("#inpend").jeDate(end);
    });

});
