$(function(){

    var dataBeast, xAxisData;
    /*调用echarts*/
    $.ajax({
        type: "GET",
        url: basePath + "/admin/student/count",
        success: function (result) {
            dataBeast = result.dataBeast;
            xAxisData = result.xAxisData;
            exampleUserTop("exampleChart", dataBeast, xAxisData);
        }
    });
    $.ajax({
        type: "GET",
        url: basePath + "/admin/relation/chart",
        success: function (data) {
            relation(data.data,"relationChart");
            relation2(data.data,"relationChart2");
        }
    });
    $("#chart").css({display:"block"});
    $("#relation").css({display:"none"});

    $("#user").click(function(){
        $("#chart").css({display:"block"});
        $("#relation").css({display:"none"});
    });
    $("#connection").click(function(){
        $("#relation").css({display:"block"});
        $("#chart").css({display:"none"});
    });

    $.ajax({
        type: "GET",
        url: basePath + "/admin/system/info",
        success: function (data) {
            var result = data.data;
            $("#studentNum").html(result.student);
            $("#facultyNum").html(result.faculty);
            $("#courseNum").html(result.course);
        }
    });


});