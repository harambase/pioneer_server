var label = "";
var box = "inbox";

$("#detail").css({display:"none"});
$("#writeMail").css({display:"none"});
$("#refresh").click(function(){
    messageTable.draw();
});
$("#back").click(function(){
    $("#detail").css({display:"none"});
    $("#table").css({display:"block"});
    messageTable.draw();
});

$("#inbox").click(function(){
    label = "inbox";
    box = "inbox";
    messageTable.draw();
});

$("#sent").click(function(){
    label = "inbox";
    box = "sent";
    messageTable.draw();
});

$("#draft").click(function(){
    label = "draft";
    box = "inbox";
    messageTable.draw();
});

$("#important").click(function(){
    label = "important";
    box = "inbox";
    messageTable.draw();
});

$("#trash").click(function(){
    label = "trash";
    box = "inbox";
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
        url: basePath + "/message/list",
        data:{
            label: label,
            box: box
        }
    },
    columns: [
        {"data": null, "title":"选择", "createdCell": function(nTd){
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
        {"data": "id", "title": "操作", "createdCell": function (nTd, rowData) {
            $(nTd).html('' +
                '<button class="btn btn-primary btn-info" style="width: 50%" onclick="viewDetail(\'' + rowData + '\')">查看消息</button>' +
                '<button class="btn btn-primary btn-edit" style="width: 50%" onclick="markAsRead(\'' + rowData + '\')">标记已读</button>');
            }, "width": "300px"
        }
    ],
    "columnDefs": [{
        orderable: false
    }, {
        "defaultContent": "",
        "targets": "_all"
    }]
});

function viewDetail(id){
    $.ajax({
        url: basePath + "/message/view?id=" + id,
        type: "GET",
        success: function (data) {
            if (data.code === 2001){
                var message = data.data;
                $("#detail").css({display:"block"});
                $("#table").css({display:"none"});
                var senderInfo =
                    '<img class="human-picture" src="'+ message.pic +'">'+
                    '   <div class="name"><h2 class="name-h ng-binding">' + message.sender + '</h2>' +
                    '       <div>' +
                    '           <span class="mail-tag tag label family">'+message.tag+'</span>' +
                    '       </div>' +
                    '   </div>';
                $("#senderInfo").html(senderInfo);

                var contactInfo =
                    '<div class="contact-info phone-email">' +
                    '    <div>' +
                    '       <i class="fa fa-phone-square fa-2x"></i> ' +
                    '       <span class="phone"> '+ message.tel +'</span>' +
                    '    </div>' +
                    '    <div>' +
                    '       <i class="fa fa-envelope-square fa-2x"></i> ' +
                    '       <span class="email"> '+ message.email +'</span>' +
                    '    </div>' +
                    '</div>';
                $("#contactInfo").html(contactInfo);

                var roleInfo =
                    '<div class="contact-info position-address">' +
                    '   <div>' +
                    '       <i class="fa fa-user-circle fa-2x"></i>' +
                    '       <span class="position">Technical Chef</span>' +
                    '   </div>' +
                    '   <div>' +
                    '       <i class="fa fa-address-card fa-2x"></i>' +
                    '       <span class="address">12 Nezavisimosti st. Vilnius, Lithuania</span>' +
                    '   </div>' +
                    '</div>';
                $("#roleInfo").html(roleInfo);
                
                var subject = 
                    '<span class="subject ng-binding">'+ message.subject +'</span>' +
                    '<span class="date ng-binding">• '+ message.date +' </span>';
                $("#subject").html(subject);

                var body = '<p>'+ message.body + '</p>';
                $("#body").html(body);
            }
            else
                Showbo.Msg.alert("消息获取失败", function () {});
        }
    });
}

function markAsRead(id){

}

$(function(){
  init();
});

function init(){
    // initUnread();
    // initSent();
}

function initUnread(){
    $.ajax({
        url: basePath + "/message/count?status=unread&box=inbox",
        type: "GET",
        success: function (data) {
            if (data.code === 2001){

            }
            else
                Showbo.Msg.alert("消息获取失败", function () {});
        }
    });
}

function initSent(){
    $.ajax({
        url: basePath + "/message/count?status=unread",
        type: "GET",
        success: function (data) {
            if (data.code === 2001){

            }
            else
                Showbo.Msg.alert("消息获取失败", function () {});
        }
    });
}


