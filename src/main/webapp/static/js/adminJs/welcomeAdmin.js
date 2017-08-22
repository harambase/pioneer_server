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
});