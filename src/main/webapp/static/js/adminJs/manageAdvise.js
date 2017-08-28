$(function () {
    var userType = "s";
    var studentid = null;
    var facultyid = null;
    var id = null;

    $(".view").css({display: "block"});
    $(".user-pop").css({display: "none"});
    $(".class-pop").css({display: "none"});
    $(".student-only").css({display:"none"});
    $("#assignFDiv").css({display: "none"});
    $("#assignCDiv").css({display: "none"});

    $("#overview").click(function () {
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
        studentTable.draw();
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
        $(".view").css({display: "block"});

        facultyid = null;
        studentid = $(this).parents("tr").find("td").eq(1).html();

        writingProperty(studentid);
        adviseTable.draw();

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
        $("#h1").html("Advisor for:" + studentView.lastname+", "+studentView.firstname+"/"+studentView.studentid);
    }

    $("#assignF").click(function(){
        $("#assignFDiv").css({display: "block"});
        $("#assignF").css({display: "none"});
    });
    $("#assignC").click(function(){
        $("#assignCDiv").css({display: "block"});
        $("#assignC").css({display: "none"});
    });
    $("#cancelF").click(function(){
        $("#assignFDiv").css({display: "none"});
        $("#assignF").css({display: "block"});
    });
    $("#cancelC").click(function(){
        $("#assignCDiv").css({display: "none"});
        $("#assignC").css({display: "block"});
    });

    $("#classTable").on("click", ".btn.btn-info", function () {
        $(".user-view").css({display: "none"});
        $(".user-pop").css({display: "none"});
        $(".class-pop").css({display: "none"});
        $(".view").css({display: "block"});

        facultyid = $(this).parents("tr").find("td").eq(1).html();
        studentid = null;

        var fname = $(this).parents("tr").find("td").eq(3).html()+
                    $(this).parents("tr").find("td").eq(4).html();
        $("#h1").html("Advisee List for "+ fname);

        adviseTable.draw();
    });

    $("#cancel").click(function(){
        $(".w_wrapper").css({display: "none"});
    });
    $("#cancel2").click(function(){
        $(".w_wrapper").css({display: "none"});
    });

    $("#transTable").on("click", ".btn.btn-edit", function () {
        $(".w_wrapper").css({display: "block"});
        $("#aF").css({display: "none"});
        $("#aS").css({display: "none"});
        if(studentid === null)
            $("#aS").css({display: "block"});
        if(facultyid === null)
            $("#aF").css({display: "block"});

        id = $(this).parents("tr").find("td").eq(0).html();
    });


    //教师列表
    function facultyList(searchFValue, w_select, w_select_li){
        var search = searchFValue.val();
        w_select.css({display: "block"});
        w_select_li.remove();
        $.ajax({
            url : basePath+"/admin/list/faculty?search="+search,
            type : "GET",
            success: function (result) {
                if (result.code === 2001) {
                    for (var i = 0; i < result.data.length; i++) {
                        var userid = result.data[i].userid;
                        newFaculty = result.data[i].lastname + result.data[i].firstname;
                        var temp = "<li data-name="+ newFaculty +" data-id=" + userid + ">" + newFaculty + "</li>";
                        w_select.append(temp);
                        w_select.css({
                            overflow: "auto",
                            position: "absolute",
                            top: "25px",
                            left: "0",
                            width: "100%",
                            margin: "0",
                            "z-index": "5"
                        });
                        w_select.find("li").css({
                            lineHeight: "30px",
                            paddingLeft: "15px",
                            backgroundColor: "#fff",
                            cursor: "pointer"
                        });
                        w_select.find("li").hover(function () {
                            $(this).css({background: "#03ced0", color: "#fff"});
                        }, function () {
                            $(this).css({background: "#fff", color: "#333"});
                        });
                        searchFValue.blur(function () {
                            setTimeout(function () {
                                w_select.hide();
                            }, 200)

                        });
                        w_select.find("li").click(function () {
                            searchFValue.val(this.innerHTML);
                        })
                    }

                } else {
                    Showbo.Msg.alert("获取失败", function () {});
                }
            }
        });
    }

    $("#searchFValue2").bind("input propertychange", function () {
        facultyList($("#searchFValue2"), $(".w_selected3"),$(".w_selected3 li"));
    });
    $(".w_selected3").on("click", "li", function () {
        $("#searchFValue2").val($(this).data("id"));
        $("#searchFValue2").data("userid", $(this).data("id"));
        $("#searchFValue2").data("name", $(this).data("name"));
        $(".w_selected3").css({display: "none"});
    });
    $("#addf-button2").click(function () {
        facultyid = $("#searchFValue2").data("userid");
        var formdata = {
            id :  id,
            facultyid : facultyid
        };
        $.ajax({
            url:basePath+"/admin/advise/update",
            type: "POST",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(formdata),
            success: function (data) {
                if (data.code === 2001)
                    Showbo.Msg.alert("更新成功!", function () {
                        adviseTable.draw();
                    });
                else if(data.code === 2005)
                    Showbo.Msg.alert("系统异常!", function () {});
                else
                    Showbo.Msg.alert("更新失败!", function () {});
            }
        })
    });

    //学生列表
    function studentList(searchFValue, w_select, w_select_li){
        var search = searchFValue.val();
        w_select.css({display: "block"});
        w_select_li.remove();
        $.ajax({
            url : basePath+"/admin/list/student?search="+search,
            type : "GET",
            success: function (result) {
                if (result.code !== 2005) {
                    for (var i = 0; i < result.data.length; i++) {
                        var userid = result.data[i].userid;
                        newFaculty = result.data[i].lastname + result.data[i].firstname;
                        var temp = "<li data-name="+ newFaculty +" data-id=" + userid + ">" + newFaculty + "</li>";
                        w_select.append(temp);
                        w_select.css({
                            overflow: "auto",
                            position: "absolute",
                            top: "25px",
                            left: "0",
                            width: "100%",
                            margin: "0",
                            "z-index": "5"
                        });
                        w_select.find("li").css({
                            lineHeight: "30px",
                            paddingLeft: "15px",
                            backgroundColor: "#fff",
                            cursor: "pointer"
                        });
                        w_select.find("li").hover(function () {
                            $(this).css({background: "#03ced0", color: "#fff"});
                        }, function () {
                            $(this).css({background: "#fff", color: "#333"});
                        });
                        searchFValue.blur(function () {
                            setTimeout(function () {
                                w_select.hide();
                            }, 200)

                        });
                        w_select.find("li").click(function () {
                            searchFValue.val(this.innerHTML);
                        })
                    }

                } else {
                    Showbo.Msg.alert("获取失败", function () {});
                }
            }
        });
    }

    $("#searchCValue2").bind("input propertychange", function () {
        studentList($("#searchCValue2"), $(".w_selected4"),$(".w_selected4 li"));
    });
    $(".w_selected4").on("click", "li", function () {
        $("#searchCValue2").val($(this).data("id"));
        $("#searchCValue2").data("userid", $(this).data("id"));
        $("#searchCValue2").data("name", $(this).data("name"));
        $(".w_selected3").css({display: "none"});
    });
    $("#addc-button2").click(function () {
        studentid = $("#searchCValue2").data("userid");
        var formdata = {
            id :  id,
            studentid : studentid
        };
        $.ajax({
            url:basePath+"/admin/advise/update",
            type: "POST",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(formdata),
            success: function (data) {
                if (data.code === 2001)
                    Showbo.Msg.alert("更新成功!", function () {
                        adviseTable.draw();
                    });
                else if(data.code === 2005)
                    Showbo.Msg.alert("系统异常!", function () {});
                else
                    Showbo.Msg.alert("更新失败!", function () {});
            }
        })
    });


    var studentTable = $("#userTable").DataTable({

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
                d.type = 's';
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
    var facultyTable = $("#classTable").DataTable({

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
            url: basePath + "/admin/user/list",
            data: function (d) {
                d.type = 'f';
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
            targets: [7]
        }, {
            "defaultContent": "",
            "targets": "_all"
        }]
    });
    var adviseTable = $("#transTable").DataTable({

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
            url: basePath + "/admin/advise/list",
            data: function (d) {
                d.studentid = studentid;
                d.facultyid = facultyid;
            }
        },
        columns: [
            {"data": "id", "title": "serial", "width": "15px"},
            {"data": "studentid", "title": "studentid"},
            {"data": "slast", "title": "lastName"},
            {"data": "sfirst", "title": "firstName"},
            {"data": "facultyid", "title": "facultyid"},
            {"data": "flast", "title": "flast"},
            {"data": "ffirst", "title": "ffirst"},
            {
                "data": null, "title": "Tool", "createdCell": function (nTd) {
                $(nTd).html('<button class="btn btn-info">remove</button>'+
                '<button class="btn btn-edit">Change</button>');
            }, "width": "300px"
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
