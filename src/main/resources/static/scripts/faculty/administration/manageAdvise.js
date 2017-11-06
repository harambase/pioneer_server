$(function () {

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
        "pagingType":   "full_numbers",
        "lengthMenu": [
            [10],
            [10]
        ],
        pageLength: 10,
        processing: true,
        serverSide: true,

        ajax: {
            url: basePath + "/admin/advise/list",
            data: function (d) {
                d.mode = "faculty";
            }
        },
        columns: [
            {"data": "id", "title": "序列号", "width": "45px"},
            {"data": "studentid", "title": "学生ID"},
            {"data": "slast", "title": "学生姓"},
            {"data": "sfirst", "title": "学生名"},
            {"data": "facultyid", "title": "教师ID"},
            {"data": "flast", "title": "教师姓"},
            {"data": "ffirst", "title": "教师名"},
            {
                "data": null, "title": "操作", "createdCell": function (nTd) {
                $(nTd).html('<button style="width:100%" class="btn btn-info">浏览学生信息</button>');
                }, "width": "200px"
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

