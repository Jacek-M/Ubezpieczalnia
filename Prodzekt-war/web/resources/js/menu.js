$(document).ready(function () {

    var path = window.location.pathname;
    path = path.replace(/\/$/, "");

    //Main manu
    $("#header .menu li a").each(function () {
        var href = $(this).attr('href');
        if (path.substring(0, href.length) === href) {
            $(this).closest('li').addClass('active');
        }
    });

    //Admin menu
    $("#content .menuAdmin li a").each(function () {
        if (path.indexOf("adminPages") > -1) {
            $("#header .menu li a").each(function () {
                var href = $(this).attr('href');
                if (href.indexOf("adminPages") > -1) {
                    $(this).closest('li').addClass('active');
                }
            });
        }
        
        var href = $(this).attr('href');
        if (path.substring(0, href.length) === href) {
            $(this).closest('li').addClass('active');
        }
    });
});