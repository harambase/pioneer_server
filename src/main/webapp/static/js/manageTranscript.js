$(function () {
    var userType = "s";
    $(".user-view").css({display: "none"});
    $(".class-view").css({display: "none"});
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
        $(this).siblings("li").removeClass("active");
        $(this).addClass("active");
    });
    $("#faculty").click(function () {
        $("#user-title").html("Lists of faculties in System");
        $(".user-table").css({display: "block"});
        userType = "f";
        userTable.draw();
        $(this).siblings("li").removeClass("active");
        $(this).addClass("active");
    });

    $(".w_close").click(function () {
        $(".user-pop").css({display: "none"});
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
            [5, 10, 25, 50],
            [5, 10, 25, 50]
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
});