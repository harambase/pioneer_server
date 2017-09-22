
$(function(){
    var status, gender;
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

    //详情弹窗
    $("#userReg").on("click", ".btn.btn-edit", function () {

        var id = $(this).parents("tr").find("td").eq(0).html();
        var userid = $(this).parents("tr").find("td").eq(1).html();
        //先拿到点击的行号
        var rowIndex = $(this).parents("tr").index();
        //此处拿到隐藏列的id
        var userJson = $("#userReg").DataTable().row(rowIndex).data().userJson;
        var user = JSON.parse(userJson);

        console.log(user);
        $("#userid2").val(userid);
        $("#username2").val(user.username);
        $("#firstname2").val(user.firstname);
        $("#lastname2").val(user.lastname);
        $("#birthday2").val(user.birthday);
        $("#email2").val(user.email);
        $("#qq2").val(user.qq);
        $("#tel2").val(user.tel);

        if(user.gender === "male"){
            $(".w_manage h4").eq(2).html("用户性别：男");
            $(".w_manage input").eq(5).prop("checked", true);
        }
        else{
            $(".w_manage h4").eq(2).html("用户性别：女");
            $(".w_manage input").eq(6).prop("checked", true);
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
                d.receiverid = "9000000000";
            }

        },
        columns: [
            {"data": "id", "title": "序列号", "width" : "45px"},
            {"data": "userid", "title": "用户ID"},
            {"data": "userJson", "title": "用户JSON","bVisible": false},
            {
                "data": null, "title": "操作", "createdCell": function (nTd, rowData) {
                    var htmlStr = '<button class="btn btn-edit btn-warning">查看详情</button>';
                    htmlStr += '<button  class="btn btn-decline">拒绝申请</button>';
                    $(nTd).html(htmlStr);

                }, "width": "200px"
            }
        ],
        "columnDefs": [{
            orderable: false,
            targets: [2]
        }, {
            "defaultContent": "",
            "targets": "_all"
        }]
    });
    var courReg = $("#courReg").DataTable({

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
                d.receiverid = "9000000000";
            }

        },
        columns: [
            {"data": "id", "title": "序列号", "width" : "45px"},
            {"data": "semail", "title": "课程CRN"},
            {
                "data": null, "title": "操作", "createdCell": function (nTd, rowData) {
                var htmlStr = '<button  class="btn btn-edit btn-warning">详情</button>';
                htmlStr += '<button  class="btn btn-edit">批准</button>';
                htmlStr += '<button  class="btn btn-edit">拒绝</button>';
                $(nTd).html(htmlStr);

            }, "width": "300px"
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
});
