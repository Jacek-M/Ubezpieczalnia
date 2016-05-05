$(document).ready(function (){
    
    var table = $("#tableALL").DataTable();
    
    $(".action-cell").append(_createTableIconClicked("eyeIcon", "eye-icon", "fa fa-eye", "Zaznacz"));
    $(".action-cell").append(_createTableIconClicked("pencilIcon", "pencil-icon", "fa fa-pencil", "Edytuj"));
    $(".action-cell").append(_createTableIconClicked("trashIcon", "trash-icon", "fa fa-trash", "Usuń"));

});



_createTableIconClicked = function(id, className, awesomeIconClass, tooltip) {
    var htmlFinal = "<a href='javascript:void(0);' id='" + id + "' class='" + className + "' title='" + tooltip + "'><i class='" + awesomeIconClass + "' aria-hidden='true'></i></a>";
    return htmlFinal;
};


