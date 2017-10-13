//添加课程
var createCourseForm = $("#createCourseForm").validate({});

var m = false; var t = false; var w = false;
var tr = false; var f = false; var sa = false;
var s = false; var yes = false;

$('#yes-div').click(function(){
  $("#yes").prop("checked", !yes);
  yes = !yes;
});

$("#mon").click(function(){
    $("#m").prop("checked", !m);
    m = !m;
});
$("#tue").click(function(){
    $("#t").prop("checked", !t);
    t = !t;
});
$("#wed").click(function(){
    $("#w").prop("checked", !w);
    w = !w;
});
$("#thr").click(function(){
    $("#tr").prop("checked", !tr);
    tr = !tr;
});
$("#fri").click(function(){
    $("#f").prop("checked", !f);
    f = !f;
});
$("#sat").click(function(){
    $("#sa").prop("checked", !sa);
    sa = !sa;
});
$("#sun").click(function(){
    $("#s").prop("checked", !s);
    s = !s;
});

var uri = location.search.split("&");
var crn = uri[0].split("=")[1];
$(function(){
    getCourse(crn);
    var curStuTable = $("#studentTable").DataTable({

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
                d.type = "s";
                d.status = "1";
            }
        },
        columns: [
            {"data": "userid", "title": "学生ID"},
            {"data": "lastname", "title": "姓"},
            {"data": "firstname", "title": "名"},

            {
                "data": null, "title": "操作", "createdCell": function (nTd) {
                $(nTd).html('<button class="btn btn-info">添加</button>');
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
    var stuListTable = $("#studentList").DataTable({

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
            url: basePath + "/course/student/list",
            data: function (d) {
                d.crn = crn;
            }
        },
        columns: [
            {"data": "studentid", "title": "学生ID"},
            {"data": "sname", "title": "学生名"},
            {"data": "grade", "title": "学生成绩"},
            {"data": "complete", "title": "完成情况", "createdCell": function (nTd, rowData) {
                if(rowData === "1")
                    $(nTd).html('<p style="line-height: 1.42857143; padding-top: 0; color:green; ">完成</p>');
                else if(rowData === "0")
                    $(nTd).html('<p style="line-height: 1.42857143; padding-top: 0; color:blue; ">进行中</p>');
                else if(rowData === "-1")
                    $(nTd).html('<p style="line-height: 1.42857143; padding-top: 0; color:red; ">挂科</p>');
            }
            },
            {
                "data": null, "title": "操作", "createdCell": function (nTd) {
                $(nTd).html('<button style="width: 50%" class="btn btn-success">修改成绩</button><button style="width: 50%" class="btn btn-danger">移除</button>');
            }, "width": "10px"
            }
        ],
        "columnDefs": [{
            orderable: false,
            targets: [4]
        }, {
            "defaultContent": "",
            "targets": "_all"
        }]
    });

    //移除学生
    $("#studentList").on("click", ".btn.btn-info", function () {
        var studentid = $(this).parents("tr").find("td").eq(1).html();
        Showbo.Msg.confirm("确认删除该学生？",function(){
            if($(".btnfocus").val() !== "取消"){
                var data = removeStuFromCourse(studentid, crn);
                $("#student").css({display: "block"});
                stuListTable.draw();
            }
        });
    });

    //添加学生
    $("#studentTable").on("click", ".btn.btn-info", function () {
        var studentid = $(this).parents("tr").find("td").eq(1).html();
        var pre = false;
        var time = false;
        var capacity = false;
        $('input[name="pre"]:checked').each(function () {
            pre = true;
        });
        $('input[name="time"]:checked').each(function () {
            time = true;
        });
        $('input[name="capa"]:checked').each(function () {
            capacity = true;
        });

        var formdata = {
            prereq  :pre,
            time : time,
            capacity :capacity,
            studentid :studentid,
            crn: crn
        };
        $.ajax({
            url:basePath+"/course/add/student",
            type: "POST",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(formdata),
            success: function (data) {
                if (data.code === 2001)
                    Showbo.Msg.alert("添加成功!", function () {
                        logTable.draw();
                    });
                else
                    Showbo.Msg.alert(data.msg, function () {});
            }
        })
    });
    function getCourse(crn){
        $.ajax({
            url : basePath+"/course/list/search?search="+crn,
            type : "GET",
            success: function (result) {
                var course = result.data[0];
                $("#crn").val(course.crn);
                $("#name").val(course.name);
                $("#credits").val(course.credits);
                $("#coulev").val(course.coulev);
                $("#cousec").val(course.cousec);
                $("#startdate").val(course.startdate);
                $("#enddate").val(course.starttime);
                $("#starttime").val(course.starttime);
                $("#endtime").val(course.endtime);
                $("#capa").val(course.capa);
                $("#year-semester").val(course.info);
                $("#classroom").val(course.classroom);
                $("#comment").val(course.comment);
                $("#searchFValue").val(course.facultyid);
                var precrn = course.precrn.split("/");
                $("#searchCValue").val(precrn);
            }
        });
    }
});



//教师列表
$(".js-example-basic-single").select2({
    ajax: {
        url: basePath + "/admin/list/faculty",
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
    placeholder: '请分配所属集群',//默认文字提示
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
//课程列表
$('.js-example-basic-multiple').select2({
    ajax: {
        url: basePath + "/course/list/search",
        type: "GET",
        delay: 250,
        data: function (params) {
            return {
                search: params.term, // search term 请求参数 ， 请求框中输入的参数
                page: params.page
            };
        },
        processResults: function (data, params) {
            var itemList = [];
            var item;
            for (var i = 0; i < data.data.length; i++) {
                item = {
                    id: data.data[i].crn,
                    text: data.data[i].name
                };
                itemList.push(item);
            }
            return {
                results: itemList
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

$("#registerBtn").click(function (){
    if(createCourseForm.form()) {
        var name = $("#name").val();
        var credits = $("#credits").val();
        var coulev = $("#coulev").val();
        var cousec = $("#cousec").val();
        var startdate = $("#startdate").val();
        var enddate = $("#enddate").val();
        var starttime = $("#starttime").val();
        var endtime = $("#endtime").val();
        var capa = $("#capa").val();
        var day = "";
        var info = $("#year-semester").val();
        var classroom  = $("#classroom").val();
        var comment  = $("#comment").val();
        var facultyid = $("#searchFValue").val();
        var precrnArray = $("#searchCValue").val();
        var precrn = "/";

        $('input[name="day"]:checked').each(function () {
            day += $(this).val() + "/";
        });

        for(var i = 0; i<precrnArray.length; i++){
            precrn += precrnArray[i] + "/";
        }

        var course = {
            credits: credits,
            coulev: coulev,
            name: name,
            cousec: cousec,
            startdate: startdate,
            enddate: enddate,
            starttime: starttime,
            endtime: endtime,
            capa: capa,
            facultyid: facultyid,
            day: day,
            info: info,
            precrn: precrn,
            classroom: classroom,
            comment : comment
        };


        $.ajax({
            url: basePath + "/course/add",
            type: "POST",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(course),
            success: function (data) {
                if (data.code === 2001) {
                    Showbo.Msg.alert("添加成功!", function () {
                        window.location.href = basePath + "/manage/course/view";
                    });
                }
                else
                    Showbo.Msg.alert(data.msg, function () {});
            }
        })
    }
});

function formatRepoSelection(repo) {
    return repo.id
}

function formatRepo(repo) {
    return repo.text
}

