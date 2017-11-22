var label = "inbox";
$("#write").click(function(){
   $("#writeMail").css({display:"block"});

});
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
    $(this).addClass("active").siblings().removeClass("active");
    messageTable.draw();
});

$("#sent").click(function(){
    label = "sent";
    $(this).addClass("active").siblings().removeClass("active");
    messageTable.draw();
});

$("#draft").click(function(){
    label = "draft";
    $(this).addClass("active").siblings().removeClass("active");
    messageTable.draw();
});

$("#important").click(function(){
    label = "important";
    $(this).addClass("active").siblings().removeClass("active");
    messageTable.draw();
});

$("#trash").click(function(){
    label = "trash";
    $(this).addClass("active").siblings().removeClass("active");
    messageTable.draw();
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
        data: function (d) {
            d.label = label;
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
        {"data": "body", "title": "内容"},
        {"data": "date", "title": "发送时间"},
        {"data": null, "title": "操作", "createdCell": function (nTd, rowData) {
            if(rowData.status.toLowerCase() === "unread"){
                $(nTd).html('' +
                    '<button class="btn btn-primary btn-info" style="width: 50%" onclick="viewDetail(\'' + rowData.id + '\')">查看消息</button>' +
                    '<button class="btn btn-primary btn-danger" style="width: 50%" onclick="markAsRead(\'' + rowData.id + '\')">标为已读</button>');
            }
            if(rowData.status.toLowerCase() === "read"){
                $(nTd).html('' +
                    '<button class="btn btn-primary btn-info" style="width: 50%" onclick="viewDetail(\'' + rowData.id + '\')">查看消息</button>' +
                    '<button class="btn btn-primary btn-edit" style="width: 50%" onclick="markAsUnread(\'' + rowData.id + '\')">标为未读</button>');
            }
            if(rowData.status.toLowerCase() === "saved"){
                $(nTd).html('' +
                    '<button class="btn btn-primary btn-info" style="width: 50%" onclick="viewDraft(\'' + rowData.id + '\')">查看消息</button>');
            }
            if(rowData.status.toLowerCase() === "trashed"){
                $(nTd).html('' +
                    '<button class="btn btn-primary btn-info" style="width: 50%" onclick="viewDetail(\'' + rowData.id + '\')">查看消息</button>' +
                    '<button class="btn btn-primary btn-danger" style="width: 50%" onclick="markAsRead(\'' + rowData.id + '\')">永久删除</button>');
            }
            if(rowData.status.toLowerCase() === "sent"){
                $(nTd).html('' +
                    '<button class="btn btn-primary btn-info" style="width: 50%" onclick="viewDetail(\'' + rowData.id + '\')">查看消息</button>');
            }

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
$("#searchUser").select2({
    ajax: {
        url: basePath + "/admin/list/active/user",
        type: "GET",
        delay: 250,
        data: function (params) {
            return {
                search: params.term, // search term 请求参数 ， 请求框中输入的参数
                page: params.page
            };
        },
        processResults: function (data, params) {
            //var data = [{ id: 0, text: 'enhancement' }, { id: 1, text: 'bug' }, { id: 2, text: 'duplicate' }, { id: 3, text: 'invalid' }, { id: 4, text: 'wontfix' }];
            var itemList = [];
            var item;
            for (var i = 0; i < data.data.length; i++) {
                item = {
                    id: data.data[i].userid,
                    text: data.data[i].lastname +", "+ data.data[i].firstname
                };
                itemList.push(item);
            }
            // console.log(itemList);
            return {
                results: itemList//itemList
            };
        },
        cache: true
    },
    language: "zh-CN",
    tags: false,//允许手动添加
    allowClear: true,//允许清空
    escapeMarkup: function (markup) {
        return markup;
    }, // 自定义格式化防止xss注入
    minimumInputLength: 1,//最少输入多少个字符后开始查询
    templateResult: formatRepo,
    templateSelection: formatRepoSelection
});

function viewDraft(id){

}

function viewDetail(id){
    markAsRead(id);
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
                    '   <div class="name"><h2 class="name-h ng-binding">发件人：' + message.sender + '</h2>' +
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

function markAsUnread(id){
    var status = "unread";
    sendStatusUpdateAjax(id, status);
}
function markAsRead(id){
    var status = "read";
    sendStatusUpdateAjax(id, status);
}
function sendStatusUpdateAjax(id, status){
    $.ajax({
        url: basePath + "/message/update/status?id="+id+"&status="+status,
        type: "PUT",
        success: function (data) {
            if (data.code === 2001){
                init();
                messageTable.draw();            }
            else
                Showbo.Msg.alert("消息更新失败!", function () {});
        }
    });
}

function init(){
    initUnread();
    initDraft();
    initTrash();
    initImportant();
}

$(function(){
    init();
});
function initImportant(){
    $.ajax({
        url: basePath + "/message/count?status=unread&label=important",
        type: "GET",
        async: false,
        success: function (data) {
            if (data.code === 2001){
                $("#importantCount").text(data.data);
            }
            else
                Showbo.Msg.alert("消息获取失败", function () {});
        }
    });
}
function initUnread(){
    $.ajax({
        url: basePath + "/message/count?status=unread&label=inbox",
        type: "GET",
        async: false,
        success: function (data) {
            if (data.code === 2001){
                $("#inboxCount").text(data.data);
            }
            else
                Showbo.Msg.alert("消息获取失败", function () {});
        }
    });
}
function initDraft(){
    $.ajax({
        url: basePath + "/message/count?status=saved&label=draft",
        type: "GET",
        async: false,
        success: function (data) {
            if (data.code === 2001){
                $("#draftCount").text(data.data);
            }
            else
                Showbo.Msg.alert("消息获取失败", function () {});
        }
    });
}
function initTrash(){
    $.ajax({
        url: basePath + "/message/count?status=trashed&label=trash",
        type: "GET",
        async: false,
        success: function (data) {
            if (data.code === 2001){
                $("#trashCount").text(data.data);
            }
            else
                Showbo.Msg.alert("消息获取失败", function () {});
        }
    });
}


