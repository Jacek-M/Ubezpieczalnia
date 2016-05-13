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
    
    $('#tableBrancheContent').TableInfo({
        tableName: "Oddziały",
        tableId: "tableBranche",
        content: "branches",
        link: "branches"
    });
    
    $('#tableBrancheViewContent').TableInfo({
        tableName: "Pracownicy-oddziału",
        tableId: "tableBrancheView",
        content: "branches",
        link: "employees"
    });
    
    $('#tableRepCarContent').TableInfo({
        tableName: "Samochody-zastepcze",
        tableId: "tableRepCar",
        content: "repCars",
        link: "repCars"
    });

});

