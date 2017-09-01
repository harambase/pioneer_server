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
        $(".class-pop").css({display: "none"});
        $(".user-pop").css({display: "none"});
        $(".student-only").css({display:"none"});
        $("#h1").html("All Transcripts");
    });
    $("#class").click(function () {
        $(".class-pop").css({display: "block"});
        $(".user-pop").css({display: "none"});
        $(".student-only").css({display:"none"});
        $(".all").css({display:"none"});
    });
    $("#user").click(function () {
        $(".class-pop").css({display: "none"});
        $(".user-pop").css({display: "block"});
        $(".student-only").css({display:"block"});
        $(".all").css({display:"none"});
    });
    $("#student").click(function () {
        $("#user-title").html("Lists of Students in System");
        $(".user-table").css({display: "block"});
        userType = "s";
        userTable.draw();
    });
    $(".w_close").click(function () {
        $(".user-pop").css({display: "none"});
        $(".class-pop").css({display: "none"});
        $(".w_wrapper").css({display: "none"});
    });

    $("#userTable").on("click", ".btn.btn-info", function () {
        $(".user-view").css({display: "none"});
        $(".user-pop").css({display: "none"});
        $(".class-pop").css({display: "none"});
        $(".view").css({display: "none"});
        crn = null;

        $(".view").css({display: "block"});

        studentid = $(this).parents("tr").find("td").eq(1).html();

        writingProperty(studentid);
        transTable.draw();

    });
    function writingProperty(studentid){
        $.ajax({
            url: basePath + "/student/transcript/info?studentid="+studentid,
            type: "GET",
            success: function (data) {
                if (data.code === 2001)
                    writingTranscriptProperty(data.data);
                else if (data.code === 2005)
                    Showbo.Msg.alert("系统异常!", function () {});
                else
                    Showbo.Msg.alert("获取失败!", function () {});
            }
        })
    }
    function writingTranscriptProperty(studentView){
        $("#h1").html(studentView.lastname+", "+studentView.firstname + " 成绩单");
        var $studentTable = $("#student-table");
        var detail = [];
        $("#h2").html(studentView.lastname+", "+studentView.firstname+"/"+studentView.studentid+" 的成绩单");
        detail.push(setRow("", "学分上限: ",
            '<input style="width: 34px;height: 27px;margin-top: 10px;float: left" id = "max" disabled minlength="1" maxlength="2" value=' +
            studentView.max_credits +'>' +
            '<button class="btn btn-edit" style="display: block; margin-top: 10px;"id="enable">修改</button>' +
            '<button class="btn btn-edit" style="display: none;margin-top: 10px;" id="update">确认更新</button>'));
        detail.push(setRow("", "未完成学分数:    ", studentView.incomplete));
        detail.push(setRow("", "正在进行的学分数:   ", studentView.progress));
        detail.push(setRow("", "已完成学分数:      ", studentView.complete));

        writeTableResourceDetail($studentTable,detail);
    }
    function writeTableResourceDetail(tableId, list) {
        var str = '<tr>';
        for (var i = 0; i < list.length; i++) {
            str += '<td style="padding: 0 10px 0 0;"><b>' + list[i].text + '</b></td>' +
                '<td style="padding: 0 30px 0 0;">' + list[i].value + '</td>';
        }
        str += '</tr>';
        tableId.html(str);
    }
    function setRow(name, text, value) {
        return {
            name: name,
            text: text,
            value: value
        }
    }

    $("#classTable").on("click", ".btn.btn-info", function () {
        $(".user-view").css({display: "none"});
        $(".user-pop").css({display: "none"});
        $(".class-pop").css({display: "none"});
        $(".view").css({display: "none"});
        studentid = null;

        $(".view").css({display: "block"});

        crn = $(this).parents("tr").find("td").eq(1).html();
        var cname = $(this).parents("tr").find("td").eq(2).html();
        $("#h1").html("Student Transcripts in "+ cname +" Course");

        transTable.draw();
    });
    //INPUT RADIO 选择控制
    $("#complete").click(function () {
        $("#complete").prop("checked", true);
        $("#process").prop("checked", false);
        $("#nComplete").prop("checked", false);
        complete = "Complete";
    });
    $("#process").click(function () {
        $("#process").prop("checked", true);
        $("#complete").prop("checked", false);
        $("#nComplete").prop("checked", false);
        complete = "In Progress";
    });
    $("#nComplete").click(function () {
        $("#nComplete").prop("checked", true);
        $("#process").prop("checked", false);
        $("#complete").prop("checked", false);
        complete = "Not Complete";
    });

    $("#cancel").click(function(){
        $(".w_wrapper").css({display: "none"});
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
                else if (data.code === 2005)
                    Showbo.Msg.alert("系统异常!", function () {});
                else
                    Showbo.Msg.alert("更新失败!", function () {});
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
                else if (data.code === 2005)
                    Showbo.Msg.alert("系统异常!", function () {});
                else
                    Showbo.Msg.alert("更新失败!", function () {});
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
            {"data": "id", "title": "序列号", "width":"45px"},
            {"data": "userid", "title": "用户ID"},
            {"data": "username", "title": "用户名"},
            {"data": "firstname", "title": "名"},
            {"data": "lastname", "title": "姓"},
            {
                "data": null, "title": "Tool", "createdCell": function (nTd) {
                $(nTd).html('<button class="btn btn-info">选择</button>');
            }, "width": "100px"
            }
        ],
        "columnDefs": [{
            orderable: false,
            targets: [5]
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
            {"data": "id", "title": "序列号","width":"45px"},
            {"data": "crn", "title": "课程编号"},
            {"data": "name", "title": "课程名"},
            {"data": "couLev", "title": "课程等级"},
            {"data": "couSec", "title": "课程部分"},
            {"data": "capa", "title": "课程容量"},
            {"data": "status", "title": "课程状态"},
            {"data": "faculty", "title": "授课教师"},
            {
                "data": null, "title": "Tool", "createdCell": function (nTd) {
                $(nTd).html('<button class="btn btn-info">选择</button>');
            }, "width": "100px"
            }
        ],
        "columnDefs": [{
            orderable: false,
            targets: [8]
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
                d.crn = crn;
            }
        },
        columns: [
            {"data": "id", "title": "序列号", "width": "45px"},
            {"data": "studentid", "title": "学生ID"},
            {"data": "slast", "title": "学生姓"},
            {"data": "sfirst", "title": "学生名"},
            {"data": "crn", "title": "课程编码"},
            {"data": "coursename", "title": "课程编号"},
            {"data": "grade", "title": "成绩"},
            {"data": "complete", "title": "完成情况"},
            {"data": "facultyid", "title": "授课人ID"},
            {"data": "credits", "title": "课程学分"},
            {"data": "assigntime", "title": "成绩提交时间", "width": "200px"},
            {
                "data": null, "title": "Tool", "createdCell": function (nTd) {
                $(nTd).html('<button class="btn btn-edit">修改</button>');
                }, "width": "100px"
            }
        ],
        "columnDefs": [{
            orderable: false,
            targets: [11]
        }, {
            "defaultContent": "",
            "targets": "_all"
        }]
    });
});

