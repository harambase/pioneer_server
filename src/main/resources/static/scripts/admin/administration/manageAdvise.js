var userType = "s";
var studentid = null;
var facultyid = null;
var id = null;

$(function () {

    $("#overview").click(function () {
        $(".class-pop").css({display: "none"});
        $(".user-pop").css({display: "none"});
        $(".student-only").css({display:"none"});
        studentid = null;
        facultyid = null;
        adviseTable.draw();
        $("#h1").html("辅导关系总览表");
    });
    $("#class").click(function () {
        $(".class-pop").css({display: "block"});
        $(".user-pop").css({display: "none"});
        $(".student-only").css({display:"none"});
        $(".all").css({display:"none"});
    });
    $("#user").click(function () {
        $(".class-pop").css({display: "none"});
        $(".user-pop").css({display: "block"});
        $(".student-only").css({display:"block"});
        $(".all").css({display:"none"});
    });
    $("#student").click(function () {
        $("#user-title").html("Lists of Students in System");
        $(".user-table").css({display: "block"});
        userType = "s";
        studentTable.draw();
    });
    $(".w_close").click(function () {
        $(".user-pop").css({display: "none"});
        $(".class-pop").css({display: "none"});
        $(".w_wrapper").css({display: "none"});
    });

    $("#userTable").on("click", ".btn.btn-info", function () {
        $(".user-view").css({display: "none"});
        $(".user-pop").css({display: "none"});
        $(".class-pop").css({display: "none"});
        $(".view").css({display: "block"});

        facultyid = null;
        studentid = $(this).parents("tr").find("td").eq(1).html();

        writingProperty(studentid);
        adviseTable.draw();

    });
    function writingProperty(studentid){
        $.ajax({
            url: basePath + "/student/transcript/info?studentid="+studentid,
            type: "GET",
            success: function (data) {
                if (data.code === 2001)
                    writingTranscriptProperty(data.data);
                else
                    Showbo.Msg.alert(data.msg, function () {});
            }
        })
    }
    function writingTranscriptProperty(studentView){
        $("#h1").html("Advisor for:" + studentView.lastname+", "+studentView.firstname+"/"+studentView.studentid);
    }

    $("#assignF").click(function(){
        $("#assignFDiv").css({display: "block"});
        $("#assignF").css({display: "none"});
    });
    $("#assignC").click(function(){
        $("#assignCDiv").css({display: "block"});
        $("#assignC").css({display: "none"});
    });
    $("#cancelF").click(function(){
        $("#assignFDiv").css({display: "none"});
        $("#assignF").css({display: "block"});
    });
    $("#cancelC").click(function(){
        $("#assignCDiv").css({display: "none"});
        $("#assignC").css({display: "block"});
    });

    $("#classTable").on("click", ".btn.btn-info", function () {
        $(".user-view").css({display: "none"});
        $(".user-pop").css({display: "none"});
        $(".class-pop").css({display: "none"});
        $(".view").css({display: "block"});

        facultyid = $(this).parents("tr").find("td").eq(1).html();
        studentid = null;

        var fname = $(this).parents("tr").find("td").eq(3).html()+
                    $(this).parents("tr").find("td").eq(4).html();
        $("#h1").html(fname+"的辅导学生列表");

        adviseTable.draw();
    });

    $("#cancel").click(function(){
        $(".w_wrapper").css({display: "none"});
    });
    $("#cancel2").click(function(){
        $(".w_wrapper").css({display: "none"});
    });

    $("#transTable").on("click", ".btn.btn-edit", function () {
        $(".w_wrapper").css({display: "block"});
        $("#aF").css({display: "none"});
        $("#aS").css({display: "none"});
        if(studentid === null)
            $("#aS").css({display: "block"});
        if(facultyid === null)
            $("#aF").css({display: "block"});

        id = $(this).parents("tr").find("td").eq(0).html();
    });

    $("#searchFValue2").bind("input propertychange", function () {
        facultyList($("#searchFValue2"), $(".w_selected3"),$(".w_selected3 li"));
    });
    $(".w_selected3").on("click", "li", function () {
        $("#searchFValue2").val($(this).data("id"));
        $("#searchFValue2").data("userid", $(this).data("id"));
        $("#searchFValue2").data("name", $(this).data("name"));
        $(".w_selected3").css({display: "none"});
    });


});

var studentTable = $("#userTable").DataTable({

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
    "pagingType":   "full_numbers",
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
            d.type = 's';
        }
    },
    columns: [
        {"data": "id", "title": "序列号", "width": "45px"},
        {"data": "userid", "title": "用户ID"},
        {"data": "username", "title": "用户名"},
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
        targets: [5]
    }, {
        "defaultContent": "",
        "targets": "_all"
    }]
});
var facultyTable = $("#classTable").DataTable({

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
        [5], [5]
    ],
    pageLength: 5,
    processing: true,
    serverSide: true,

    ajax: {
        url: basePath + "/admin/user/list",
        data: function (d) {
            d.type = 'f';
        }
    },
    columns: [
        {"data": "id", "title": "序列号", "width": "45px"},
        {"data": "userid", "title": "用户ID"},
        {"data": "username", "title": "用户名"},
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
        targets: [5]
    }, {
        "defaultContent": "",
        "targets": "_all"
    }]
});
var adviseTable = $("#transTable").DataTable({

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
        [10,20,50],
        [10,20,50]
    ],
    pageLength: 10,
    processing: true,
    serverSide: true,

    ajax: {
        url: basePath + "/admin/advise/list",
        data: function (d) {
            d.studentid = studentid;
            d.facultyid = facultyid;
        }
    },
    columns: [
        {"data": "sname", "title": "学生姓名"},
        {"data": "fname", "title": "教师姓名"},
        {"data": "status", "title": "状态", "createdCell": function (nTd, rowData) {
            if (rowData === "1")
                $(nTd).html('<p style="line-height: 1.42857143; padding-top: 0; color:green; ">正常</p>');
            else if (rowData === "0")
                $(nTd).html('<p style="line-height: 1.42857143; padding-top: 0; color:red; ">已停止</p>');
        }
        },
        {"data": "updateTime", "title": "更新时间"},
        {"data": "oname", "title": "操作人"},
        {
            "data": null, "title": "操作", "createdCell": function (nTd, rowData) {
            var htmlStr =  '<button class="btn btn-info" style="width:50%" onclick="deleteAdvise(\'' +rowData.id+ '\')">删除辅导关系</button>';
            if(rowData.status === "0")
                htmlStr += '<button class="btn btn-success" style="width:50%" onclick="updateStatus(\'' +rowData.id+'\',\'' + "1" + '\')">启用辅导关系</button>';
            else
                htmlStr += '<button class="btn btn-danger " style="width:50%" onclick="updateStatus(\'' +rowData.id+'\',\'' + "0" + '\')">停止辅导关系</button>';
            $(nTd).html(htmlStr);

        }, "width": "200px"
        }
    ],
    "columnDefs": [{
        orderable: false,
        targets: [5]
    }, {
        "defaultContent": "",
        "targets": "_all"
    }]
});
//移除关系
function deleteAdvise(id) {
    Showbo.Msg.confirm("确认删除该关系？",function(){
        if($(".btnfocus").val() !== "取消"){
            /*删除操作*/
            $.ajax({
                url: basePath + "/admin/advise/remove?id="+id,
                type: "DELETE",
                contentType: "application/json; charset=utf-8",
                success: function (data) {
                    if (data.code === 2001)
                        Showbo.Msg.alert("删除成功!", function () {
                            adviseTable.draw();
                        });
                    else
                        Showbo.Msg.alert(data.msg, function () {});
                }
            });
        }
    });
}
function updateStatus(id, status){
    var formdata = {
        id :  id,
        status : status
    };
    $.ajax({
        url:basePath+"/admin/advise/update",
        type: "POST",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(formdata),
        success: function (data) {
            if (data.code === 2001)
                Showbo.Msg.alert("更新成功!", function () {
                    adviseTable.draw();
                });
            else
                Showbo.Msg.alert(data.msg, function () {});
        }
    });
}
