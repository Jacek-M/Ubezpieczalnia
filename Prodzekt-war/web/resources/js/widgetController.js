$(document).ready(function () {
    console.log("widgetController - START");

    $('#tableEmployeeContent').TableInfo({
        tableName: "Pracownicy",
        tableId: "tableALL",
        content: "employees",
        link: "employees"
    });


    $('#tableClientContent').TableInfo({
        tableName: "Klienci",
        tableId: "tableClient",
        content: "clients",
        link: "clients"
    });
    
    $('#tableServiceContent').TableInfo({
        tableName: "Serwisy",
        tableId: "tableService",
        content: "services",
        link: "services"
    });
    
    $('#tableServiceViewContent').TableInfo({
        tableName: "Pracownicy-serwisu",
        tableId: "tableServiceView",
        content: "services",
        link: "employees"
    });

});

