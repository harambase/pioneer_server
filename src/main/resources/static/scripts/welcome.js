$(function () {
    var user;
    $.ajax({
        url: basePath + "/admin/get/current",
        type: "GET",
        async: false,
        success: function (data) {
            if (data.code === 2001)
                user = data.data;
            else
                Showbo.Msg.alert(data.msg, function () {});
        }
    });

    $("#adSys").css({display: "none"});
    $("#faSys").css({display: "none"});
    $("#stSys").css({display: "none"});
    if(user.type.indexOf("a") !== -1){
        $("#adSys").css({display: "block"});
    }
    if(user.type.indexOf("f") !== -1){
        $("#faSys").css({display: "block"});
    }
    if(user.type.indexOf("s") !== -1){
        $("#stSys").css({display: "block"});
    }

    $("#admin").click(function(){
        window.location.href = basePath + "/welcomeAdmin";
    });
    $("#faculty").click(function(){
        window.location.href = basePath + "/welcomeFaculty";
    });
    $("#student").click(function(){
        window.location.href = basePath + "/welcomeStudent";
    });
});

