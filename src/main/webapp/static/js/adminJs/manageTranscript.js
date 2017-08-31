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
        $("#h1").html(studentView.lastname+", "+studentView.firstname + " Transcripts");
        var $studentTable = $("#student-table");
        var detail = [];
        $("#h2").html("General Transcript Detail for:" + studentView.lastname+", "+studentView.firstname+"/"+studentView.studentid);
        detail.push(setRow("", "Credit Limit: ",
            '<input style="width: 34px;height: 27px;margin-top: 10px;float: left" id = "max" disabled minlength="1" maxlength="2" value=' +
            studentView.max_credits +'>' +
            '<button class="btn btn-edit" style="display: block; margin-top: 10px;"id="enable">Edit</button>' +
            '<button class="btn btn-edit" style="display: none;margin-top: 10px;" id="update">update</button>'));
        detail.push(setRow("", "Incomplete Credits:    ", studentView.incomplete));
        detail.push(setRow("", "In Progress Credits:   ", studentView.progress));
        detail.push(setRow("", "Complete Credits:      ", studentView.complete));

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
            "emptyTable": "No Data Founded！",
            "info": "SHOW FROM _START_ TO _END_ ，TOTAL OF _TOTAL_ RECORDS",
            "infoEmpty": "NO RECORDS FOUND！",
            "infoFiltered": "(SEARCH FROM _MAX_ RECORDS)",
            "lengthMenu": "SHOW: _MENU_",
            "search": "SEARCH:",
            "zeroRecords": "No Record Found！",
            "paginate": {
                "previous": "Previous",
                "next": "Next",
                "last": "Last",
                "first": "First"
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
            {"data": "id", "title": "serial"},
            {"data": "userid", "title": "userid"},
            {"data": "username", "title": "username"},
            {"data": "firstname", "title": "firstname"},
            {"data": "lastname", "title": "lastname"},
            {"data": "status", "title": "status"},
            {"data": "updatetime", "title": "updateTime"},
            {
                "data": null, "title": "Tool", "createdCell": function (nTd) {
                $(nTd).html('<button class="btn btn-info">Choose</button>');
            }, "width": "100px"
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
    var classTable = $("#classTable").DataTable({

        "language": {
            "aria": {
                "sortAscending": ": activate to sort column ascending",
                "sortDescending": ": activate to sort column descending"
            },
            "emptyTable": "No Data Founded！",
            "info": "SHOW FROM _START_ TO _END_ ，TOTAL OF _TOTAL_ RECORDS",
            "infoEmpty": "NO RECORDS FOUND！",
            "infoFiltered": "(SEARCH FROM _MAX_ RECORDS)",
            "lengthMenu": "SHOW: _MENU_",
            "search": "SEARCH:",
            "zeroRecords": "No Record Found！",
            "paginate": {
                "previous": "Previous",
                "next": "Next",
                "last": "Last",
                "first": "First"
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
            {"data": "id", "title": "serial"},
            {"data": "crn", "title": "crn"},
            {"data": "name", "title": "name"},
            {"data": "coulev", "title": "coulev"},
            {"data": "cousec", "title": "cousec"},
            {"data": "capa", "title": "capa"},
            {"data": "status", "title": "status"},
            {"data": "facultyid", "title": "faculty"},
            {"data": "updatetime", "title": "updateTime"},
            {
                "data": null, "title": "Tool", "createdCell": function (nTd) {
                $(nTd).html('<button class="btn btn-info">Choose</button>');
            }, "width": "100px"
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
    var transTable = $("#transTable").DataTable({

        "language": {
            "aria": {
                "sortAscending": ": activate to sort column ascending",
                "sortDescending": ": activate to sort column descending"
            },
            "emptyTable": "No Data Founded！",
            "info": "SHOW FROM _START_ TO _END_ ，TOTAL OF _TOTAL_ RECORDS",
            "infoEmpty": "NO RECORDS FOUND！",
            "infoFiltered": "(SEARCH FROM _MAX_ RECORDS)",
            "lengthMenu": "SHOW: _MENU_",
            "search": "SEARCH:",
            "zeroRecords": "No Record Found！",
            "paginate": {
                "previous": "Previous",
                "next": "Next",
                "last": "Last",
                "first": "First"
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
            {"data": "id", "title": "serial", "width": "15px"},
            {"data": "studentid", "title": "studentid"},
            {"data": "slast", "title": "lastName"},
            {"data": "sfirst", "title": "firstName"},
            {"data": "crn", "title": "crn"},
            {"data": "coursename", "title": "coursename"},
            {"data": "grade", "title": "grade"},
            {"data": "complete", "title": "complete"},
            {"data": "facultyid", "title": "facultyid"},
            {"data": "credits", "title": "credits"},
            {"data": "assigntime", "title": "assignTime", "width": "100px"},
            {
                "data": null, "title": "Tool", "createdCell": function (nTd) {
                $(nTd).html('<button class="btn btn-edit">Edit</button>');
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

