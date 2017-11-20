$("#detail").css({display:"none"});
$("#writeMail").css({display:"none"});
$("#refresh").click(function(){
    messageTable.draw();
});

var messageTable = $("#messageTable").DataTable({

    "language": {
        "aria": {
            "sortAscending": ": activate to sort column ascending",
            "sortDescending": ": activate to sort column descending"
        },
        "emptyTable": "没有数据！",
        "info": "",
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
                '<div class="name-container" style="margin-top: 5%;float: right">' +
                '   <div>' +
                '       <span class="name">'+ rowData.sender +'</span>' +
                '   </div>' +
                '   <div>' +
                '       <span class="tag label label-primary friend">' + rowData.tag +'</span>' +
                '   </div>' +
                '</div>';
            $(nTd).html(htmlStr);
        }, "width":"250px"},
        {"data": "subject", "title": "主题"},
        {"data": "title", "title": "标题"},
        {"data": "body", "title": "内容"},
        {"data": "date", "title": "发送时间"},
        {
            "data": null, "title": "操作", "createdCell": function (nTd) {
            $(nTd).html('' +
                '<button class="btn btn-primary btn-info" style="width: 50%">查看消息</button>' +
                '<button class="btn btn-primary btn-edit" style="width: 50%">标记已读</button>');
        }, "width": "300px"}
    ],
    "columnDefs": [{
        orderable: false
    }, {
        "defaultContent": "",
        "targets": "_all"
    }]
});

$(function(){

});
