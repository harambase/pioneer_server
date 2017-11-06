$(function () {
    var userType = "s";
    var studentid = null;
    var crn = null;
    var complete = null;

    $(".view").css({display: "block"});
    $(".user-pop").css({display: "none"});
    $(".class-pop").css({display: "none"});
    $(".student-only").css({display:"none"});


    $("#overview").click(function () {
        studentid = null;
        crn = null;
        transTable.draw();
    });


    $("#userTable").on("click", ".btn.btn-info", function () {
        crn = null;
        studentid = $(this).parents("tr").find("td").eq(0).html();
        transTable.draw();
    });

    $("#overall").click(function(){
        studentid = null;
        crn = null;
        transTable.draw();
    });

    $("#classTable").on("click", ".btn.btn-info", function () {
        studentid = null;
        crn = $(this).parents("tr").find("td").eq(0).html();
        transTable.draw();
    });

    $("#transTable").on("click", ".btn.btn-edit", function () {
        $(".w_wrapper").css({display: "block"});
        var baseInfo = $(".w_basicInfo");

        studentid = $(this).parents("tr").find("td").eq(1).html();
        crn = $(this).parents("tr").find("td").eq(4).html();
        var credits =  $(this).parents("tr").find("td").eq(9).html();
        var grade = $(this).parents("tr").find("td").eq(6).html();
        complete =  $(this).parents("tr").find("td").eq(7).html();

        if(complete === "Complete"){
            $("#complete").prop("checked", true);
            $("#process").prop("checked", false);
            $("#nComplete").prop("checked", false);
            complete = "Complete";
        }
        else if(complete === "In Progress"){
            $("#process").prop("checked", true);
            $("#complete").prop("checked", false);
            $("#nComplete").prop("checked", false);
            complete = "In Progress";
        }
        else{
            $("#nComplete").prop("checked", true);
            $("#process").prop("checked", false);
            $("#complete").prop("checked", false);
            complete = "Not Complete";
        }

        baseInfo.find("#sid").val(studentid);
        baseInfo.find("#crn").val(crn);
        baseInfo.find("#credits").val(credits);
        baseInfo.find("#grade").val(grade);
        baseInfo.find("#complete").val(complete);
    });

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
    //更新成绩
    $("#confirm").click(function(){
        var formdata = {
            studentid: studentid,
            crn: crn,
            grade: $("#grade").val(),
            complete: complete
        };
        $.ajax({
            url: basePath + "/course/transcript/update",
            type: "POST",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(formdata),
            success: function (data) {
                if (data.code === 2001)
                    Showbo.Msg.alert("更新成功!", function () {
                        studentid = "";
                        crn = "";
                        transTable.draw();
                    });
                else
                    Showbo.Msg.alert(data.msg, function () {});
            }
        })
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
    var classTable = $("#classTable").DataTable({

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
            url: basePath + "/course/list"
        },

        columns: [
            {"data": "crn", "title": "编号"},
            {"data": "name", "title": "课名"},
            {"data": "coulev", "title": "等级"},
            {"data": "cousec", "title": "班级"},
            {"data": "faculty", "title": "教师"},
            {"data": "status", "title": "状态", "createdCell": function (nTd, rowData) {
                if(rowData === "1")
                    $(nTd).html('<p style="line-height: 1.42857143; padding-top: 0; color:black; ">未开始</p>');
                else if(rowData === "0")
                    $(nTd).html('<p style="line-height: 1.42857143; padding-top: 0; color:green; ">进行中</p>');
                else if(rowData === "-1")
                    $(nTd).html('<p style="line-height: 1.42857143; padding-top: 0; color:red; ">已结课</p>');

            }},
            {
                "data": null, "title": "操作", "createdCell": function (nTd) {
                $(nTd).html('<button class="btn btn-info layui-layer-btn" style="width:100%">选择</button>');
            }, "width": "60px"
            }
        ],
        "columnDefs": [{
            orderable: false,
            targets: [6]
        }, {
            "defaultContent": "",
            "targets": "_all"
        }]
    });
    var transTable = $("#transTable").DataTable({

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
            [10],
            [10]
        ],
        pageLength: 10,
        processing: true,
        serverSide: true,

        ajax: {
            url: basePath + "/course/transcript/list",
            data: function (d) {
                d.studentid = studentid;
                d.crn = crn;            }
        },
        columns: [
            {"data": "id", "title": "序号", "bVisible":false},
            {"data": "studentid", "title": "学生ID"},
            {"data": "sname", "title": "姓名"},
            {"data": "crn", "title": "课码"},
            {"data": "coursename", "title": "课名"},
            {"data": "fname", "title": "授课人"},
            {"data": "grade", "title": "成绩"},
            {"data": "complete", "title": "完成情况", "createdCell": function (nTd, rowData) {
                if(rowData === "1")
                    $(nTd).html('<p style="line-height: 1.42857143; padding-top: 0; color:green; ">完成</p>');
                else if(rowData === "0")
                    $(nTd).html('<p style="line-height: 1.42857143; padding-top: 0; color:blue; ">进行中</p>');
                else if(rowData === "-1")
                    $(nTd).html('<p style="line-height: 1.42857143; padding-top: 0; color:red; ">挂科</p>');
                }
            },
            {"data": "assigntime", "title": "提交时间"},
            {
                "data": null, "title": "操作", "createdCell": function (nTd) {
                $(nTd).html('<button class="btn btn-edit" style="width: 100%">修改</button>');
                }, "width": "100px"
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

    //移除记录
    $("#transTable").on("click", ".btn.btn-info", function() {
        var studentid = $(this).parents("tr").find("td").eq(1).html();
        var crn       = $(this).parents("tr").find("td").eq(4).html();
        Showbo.Msg.confirm("确认删除该记录？",function(){
            if($(".btnfocus").val() !== "取消"){
                /*删除操作*/
                removeStuFromCourse(studentid, crn);
                transTable.draw();
            }
        });
    });

});

$("#transTable").on("click", ".btn.btn-edit", function(){
    $("#editTranscript").modal('show');
    $("#formBody").html(content);
});

var content =
    '      <div style="padding: 30px; line-height: 22px;">' +
    '<form id="editTranscript" class="form-group" method="post" onsubmit="return false">' +
    '         <div class="form-group">' +
    '             <div class="col-sm-3">' +
    '                <label for="sid" class="col-sm-12 control-label">学生ID:</label>' +
    '              </div>' +
    '              <div class="col-sm-9">' +
    '                 <input id="sid" value="" class="form-control" disabled >' +
    '              </div>' +
    '              <div class="col-sm-3" style="margin-top:10px;">' +
    '                 <label for="crn" class="col-sm-12 control-label">*课程CRN:</label>' +
    '              </div>' +
    '              <div class="col-sm-9" style="margin-top:10px;">' +
    '                  <input id="crn2" class="form-control" required disabled/>' +
    '              </div>' +
    '              <div class="col-sm-3" style="margin-top:10px;">' +
    '                 <label for="crn" class="col-sm-12 control-label">*课程学分:</label>' +
    '              </div>' +
    '              <div class="col-sm-9" style="margin-top:10px;">' +
    '                 <input id="credits2" class="form-control" required disabled/>' +
    '              </div>' +
    '              <div class="col-sm-3" style="margin-top:10px;">' +
    '                  <label for="crn" class="col-sm-12 control-label">*成绩:</label>' +
    '              </div>' +
    '              <div class="col-sm-9" style="margin-top:10px;">' +
    '                  <input placeholder="可使用Letter Grade或百分制" id="grade" name="grade" value="" class="form-control" minlength="1" required >' +
    '              </div>' +
    '              <div class="col-sm-3" style="margin-top:10px;">' +
    '                  <label for="crn" class="col-sm-12 control-label">*完成情况:</label>' +
    '              </div>' +
    '              <div class="col-sm-9" style="margin-top:10px;">' +
    '                        <input id="complete"  style="width: 33%" type="radio" name="complete" value="Complete">完成课程' +
    '                        <input id="process"   style="width: 33%" type="radio" name="complete" value="In Progress">正在进行' +
    '                        <input id="nComplete" style="width: 33%" type="radio" name="complete" value="Not Complete">未完成（挂科）' +
    '              </div>' +
    '              <div class="col-sm-3" style="margin-top:10px;">' +
    '                  <label for="tPassword" class="col-sm-12 control-label">*操作密码:</label>' +
    '              </div>' +
    '              <div class="col-sm-9" style="margin-top:10px;">' +
    '                  <input type="password" id="tPassword" class="form-control" minlength="6" maxlength="16" checkOpPwd="true" required/>' +
    '              </div>' +
    '          </div>' +
    '     </div>' +
    '</form>'+
    '</div>';

