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
        success: function () {
            relation("relationChart");
            relation2("relationChart2")
        }
    });
    $("#chart").css({display:"none"});
    $("#relation").css({display:"block"});

    $("#user").click(function(){
        $("#chart").css({display:"block"});
        $("#relation").css({display:"none"});
    });
    $("#connection").click(function(){
        $("#relation").css({display:"block"});
        $("#chart").css({display:"none"});
    });

});