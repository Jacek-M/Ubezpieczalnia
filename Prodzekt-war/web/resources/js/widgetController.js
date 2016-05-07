$(document).ready(function(){
    console.log("widgetController - START");
    $('#tableEmployeeContent').TableInfo({
        tableName : "Pracownicy",
        tableId : "tableALL",
        content: "employees"
    });
    
});

