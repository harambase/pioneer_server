var pinNumForm = $("#pinNumForm").validate({});

$("#pinValidate").click(function(){
    if(pinNumForm.form()){
        /*删除操作*/
        $.ajax({
            url: basePath + "/pin/validate?pin="+$("#pin").val(),
            type: "GET",
            success: function (data) {
                if (data.code === 2001)
                    Showbo.Msg.alert("验证成功!", function () {
                    });
                else
                    Showbo.Msg.alert("验证失败!", function () {
                    });
            }
        });
    }
});