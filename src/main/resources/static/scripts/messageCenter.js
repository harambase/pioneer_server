$(function(){
    $(".message-pop").css({display: "none"});

    $("#messageTable").on("click", ".btn.btn-info", function () {
        $(".message-pop").css({display: "block"});

    });

    $(".w_close").click(function () {
        $(".message-pop").css({display: "none"});
    });

    var messageTable = $("#messageTable").DataTable({

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
            url: basePath + "/message/list",

            data: function (d) {
                d.receiverid = "9000000000";
            }

        },
        columns: [
            {"data": "id", "title": "序列号", "width" : "45px"},
            {"data": "semail", "title": "发件人邮件"},
            {"data": "slast", "title": "发件人姓"},
            {"data": "sfirst", "title": "发件人名"},
            {"data": "subject", "title": "主题"},
            {"data": "title", "title": "标题"},
            {"data": "body", "title": "内容"},
            {"data": "status", "title": "状态"},
            {"data": "date", "title": "发送时间"},
            {
                "data": null, "title": "操作", "createdCell": function (nTd, rowData) {
                var htmlStr = '<button  class="btn btn-edit btn-warning">删除消息</button>';
                htmlStr += '<button  class="btn btn-edit">标记已读</button>';
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
