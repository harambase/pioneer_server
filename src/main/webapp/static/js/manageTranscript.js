$(function () {
    var userType = "s";
    var studentid = null;
    var crn = null;
    var complete = null;

    $(".view").css({display: "none"});
    $(".user-pop").css({display: "none"});
    $(".class-pop").css({display: "none"});

    $("#class").click(function () {
        $(".class-pop").css({display: "block"});
        $(".user-pop").css({display: "none"});

    });
    $("#user").click(function () {
        $(".class-pop").css({display: "none"});
        $(".user-pop").css({display: "block"});

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
        var sname = $(this).parents("tr").find("td").eq(2).html() +" "
                    + $(this).parents("tr").find("td").eq(3).html();
        $("#h1").html("Transcripts of Student: "+ sname +" ");

        transTable.draw();
    });

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
        complete = "In Process";
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
        else if(complete === "In Process"){
            $("#process").prop("checked", true);
            $("#complete").prop("checked", false);
            $("#nComplete").prop("checked", false);
            complete = "In Process";
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
            [5],
            [5]
        ],
        pageLength: 5,
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
            {"data": "id", "title": "serial"},
            {"data": "studentid", "title": "studentid"},
            {"data": "slast", "title": "lastName"},
            {"data": "sfirst", "title": "firstName"},
            {"data": "crn", "title": "crn"},
            {"data": "coursename", "title": "coursename"},
            {"data": "grade", "title": "grade"},
            {"data": "complete", "title": "complete"},
            {"data": "facultyid", "title": "facultyid"},
            {"data": "credits", "title": "credits"},
            {"data": "assigntime", "title": "assignTime"},
            {
                "data": null, "title": "Tool", "createdCell": function (nTd) {
                $(nTd).html('<button class="btn btn-edit">Edit</button>');
            }, "width": "100px"
            }
        ],
        "columnDefs": [{
            orderable: false,
            targets: [10]
        }, {
            "defaultContent": "",
            "targets": "_all"
        }]
    });
});

