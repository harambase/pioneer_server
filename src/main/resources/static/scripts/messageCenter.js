$(function(){

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
        "pagingType":   "full_numbers",
        "lengthMenu": [
            [5, 10, 25, 50],
            [5, 10, 25, 50]
        ],
        processing: true,
        serverSide: true,
        searching: false,
        lengthChange: false,

        ajax: {
            url: basePath + "/message/list"
        },
        columns: [
            {"data": "null", "title":"选择", "createdCell": function(nTd, rowData){
                var htmlStr =
                   '<div class="check-td mail-checkbox">'+
                   '     <label class="checkbox-inline custom-checkbox nowrap">' +
                   '       <input type="checkbox">' +
                   '       <span></span>' +
                   '     </label>' +
                   '</div>';
                $(nTd).html(htmlStr);
            }},
            {"data": null, "title": "发件人", "createdCell": function(nTd, rowData){
                var htmlStr = '<div style="float: left">' +
                    '<img style="border-radius: 23px; margin: 7px 0 7px 7px;" class="photo-td little-human little-human-picture" ' +
                    '   src="'+ rowData.pic + '">' +
                    '</div>';
                htmlStr +=
                    '<div class="name-container" style="float: right">' +
                    '   <div>' +
                    '       <span class="name">'+ rowData.sender +'</span>' +
                    '   </div>' +
                    '   <div>' +
                    '       <span class="tag label label-primary family">' + rowData.tag +'</span>' +
                    '   </div>' +
                    '</div>';
                $(nTd).html(htmlStr);
            }},
            {"data": "title", "title": "标题"},
            {"data": "body", "title": "内容"},
            {"data": "date", "title": "发送时间"}
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
