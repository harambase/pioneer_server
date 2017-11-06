//更新学分
$("#student-table").on("click", "#enable", function(){
    $("#max").removeAttr("disabled");
    $("#enable").css({display: "none"});
    $("#update").css({display: "block"});
});
$("#student-table").on("click", "#update", function(){
    var newMax = $("#max").val();
    var formdata = {
        studentid: studentid,
        maxCredits: newMax
    };
    $.ajax({
        url: basePath + "/student/update",
        type: "POST",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(formdata),
        success: function (data) {
            if (data.code === 2001)
                Showbo.Msg.alert("更新成功!", function () {});
            else
                Showbo.Msg.alert(data.msg, function () {});
        }
    });
    $("#max").attr("disabled","disabled");
    $("#update").css({display: "none"});
    $("#enable").css({display: "block"});
});

var userTable = $("#userTable").DataTable({

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
            d.type = userType;
        }
    },
    columns: [
        {"data": "userid", "title": "学生ID"},
        {"data": "lastname", "title": "姓"},
        {"data": "firstname", "title": "名"},
        {
            "data": null, "title": "操作", "createdCell": function (nTd) {
            $(nTd).html('<button class="btn btn-info" style="width:100%">选择</button>');
        }, "width": "100px"
        }
    ],
    "columnDefs": [{
        orderable: false,
        targets: [3]
    }, {
        "defaultContent": "",
        "targets": "_all"
    }]
});