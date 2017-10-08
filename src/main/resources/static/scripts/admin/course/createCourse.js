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

$('.js-example-basic-single').select2();
$('.js-example-basic-multiple').select2();

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
        var precrn = $("#searchCValue").val();

        $('input[name="day"]:checked').each(function () {
            day += $(this).val() + "/";
        });

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

                    });
                }
                else
                    Showbo.Msg.alert(data.msg, function () {});
            }
        })
    }
});

