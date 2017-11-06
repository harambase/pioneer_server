var yes = false;
var transcript = false;
var course = false;

var createPinForm = $("#createPinForm").validate({});

//种类
$("#course-div").click(function (){
    $("#course").prop("checked", !course);
    course = !course;
});
$("#transcript-div").click(function (){
    $("#transcript").prop("checked", !transcript);
    transcript = !transcript;
});

//确认
$("#yes-div").click(function(){
    $("#yes").prop("checked", !yes);
    yes = !yes;
});

$("#generate").click(function(){
   if(createPinForm.form()){
       $('input[name="type"]:checked').each(function(){
           var role = $(this).val();
           $.ajax({
               url:basePath+"/pin/generate?startTime=" + $("#startTime").val()
                   + "&endTime=" + $("#endTime").val()
                   + "&role=" + role
                   + "&info=" + $("#info").val()
                   + "&remark=" + $("#comments").val(),
               type: "GET",
               success: function (data) {
                   if (data.code === 2001)
                       Showbo.Msg.alert("更新成功!", function () {
                           window.location.reload();
                       });
                   else
                       Showbo.Msg.alert(data.msg, function () {});
               }
           })
       });
   }
});