$(function(){
    //详情弹窗
    $("#userReg").on("click", ".btn.btn-edit", function () {

        var id  = $(this).parents("tr").find("td").eq(0).html();
        var crn = $(this).parents("tr").find("td").eq(1).html();
        var userJson = $(this).parents("tr").find("td").eq(0).find("button").data("json");
        console.log(userJson);
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
            {
                "data": "userJson", "title": "操作", "createdCell": function (nTd, rowData) {
                    var htmlStr = '<button data-json="'+rowData+'" class="btn btn-edit btn-warning">详情</button>';
                    htmlStr += '<button   class="btn btn-approve">批准</button>';
                    htmlStr += '<button  class="btn btn-decline">拒绝</button>';
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
