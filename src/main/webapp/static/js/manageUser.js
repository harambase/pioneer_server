$(function () {
    var registerForm = $("#createUserForm").validate({});
    $("#registerBtn").click(function () {
        if(registerForm.form()){
            var firstname = $("#firstname").val();
            var lastname = $("#lastname").val();
            var email = $("#email").val();
            var qq = $("#qq").val();
            var weChat = $("#weChat").val();
            var dorm = $("#dorm").val();
            var gender = $("#gender").val();
            var info = $("#year-semester").val();
            var type = $("#type").val();
            var birthday = $("#birthday").val();

            var person = {
                firstname: firstname,
                lastname: lastname,
                email: email,
                qq: qq,
                weChat: weChat,
                dorm: dorm,
                gender: gender,
                info: info,
                type: type,
                birthday:birthday
            };
            $.ajax({
                url: basePath + "/admin/adduser",
                type: "POST",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(person),
                success: function (data) {
                    console.log(data);
                    if (data.code === 2001) {
                        Showbo.Msg.alert("添加成功!", function () {
                            window.location.reload();
                        });
                    }
                    else if (data.code === 2005) {
                        Showbo.Msg.alert("系统异常!", function () {} );
                    }
                    else
                        Showbo.Msg.alert(data.msg, function () {});
                }
            })
        }
    })


    $(function () {
        var logTable = $("#userTable").DataTable({

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
                [5, 10, 25, 50],
                [5, 10, 25, 50]
            ],
            pageLength: 5,
            processing: true,
            serverSide: true,
            order: [[8, "desc"]],
            // paging: false,
            ajax: {
                url: basePath + "/admin/user/list",

                data: function (d) {
                    d.startTime = $("#inpstart").val();
                    d.endTime = $("#inpend").val();
                }
            },
            columns: [
                {
                    "data": null,
                    "title": "<input id='allCheck' type='checkbox'/>全选",
                    "createdCell": function (nTd) {
                        $(nTd).html('<input type="checkbox"/>');
                    },
                    "width": "70px"
                },
                {"data": "userid", "title": "userid"},
                {"data": "username", "title": "username"},
                {"data": "firstname", "title": "firstname"},
                {"data": "lastname", "title": "lastname"},
                {"data": "password", "title": "password"},
                {"data": "type", "title": "type"},
                {"data": "status", "title": "status"},
                {"data": "createtime", "title": "createTime"},
                {"data": "updatetime", "title": "updateTime"},
                {
                    "data": null, "title": "Tool", "createdCell": function (nTd) {
                    $(nTd).html('<button class="btn btn-info">Delete</button><button class="btn btn-edit">Edit</button>');
                }, "width": "100px"
                }
            ],
            "columnDefs": [{
                orderable: false,
                targets: [0, 9]
            }, {
                "defaultContent": "",
                "targets": "_all"
            }]
        });

        var start = {
            format: 'YYYY-MM-DD',
            minDate: '2014-06-16 23:59:59', //设定最小日期为当前日期
            maxDate: $.nowDate({DD: 0}), //最大日期
            choosefun: function (elem, datas) {
                end.minDate = datas; //开始日选好后，重置结束日的最小日期
                endDates();
                logTable.draw();
            }
        };
        var end = {
            format: 'YYYY-MM-DD',
            minDate: $.nowDate({DD: 0}), //设定最小日期为当前日期
            maxDate: '2099-06-16 23:59:59', //最大日期
            choosefun: function (elem, datas) {
                start.maxDate = datas; //将结束日的初始值设定为开始日的最大日期
                logTable.draw();
            }
        };

        function endDates() {
            end.trigger = false;
            $("#inpend").jeDate(end);
        }

        $("#inpstart").jeDate(start);
        $("#inpend").jeDate(end);
    })
});