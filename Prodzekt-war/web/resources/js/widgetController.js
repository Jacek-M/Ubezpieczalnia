$(document).ready(function () {
    console.log("widgetController - START");

    $('#tableEmployeeContent').TableInfo({
        tableName: "Pracownicy",
        tableId: "tableALL",
        content: "employees",
        tableActions: "admin",
        link: "employees"
    });


    $('#tableClientContent').TableInfo({
        tableName: "Klienci",
        tableId: "tableClient",
        content: "clients",
        tableActions: "admin",
        link: "clients"
    });

    $('#tableServiceContent').TableInfo({
        tableName: "Serwisy",
        tableId: "tableService",
        content: "services",
        tableActions: "admin",
        link: "services"
    });

    $('#tableServiceViewContent').TableInfo({
        tableName: "Pracownicy-serwisu",
        tableId: "tableServiceView",
        content: "services",
        tableActions: "admin",
        link: "employees"
    });

    $('#tableBrancheContent').TableInfo({
        tableName: "Oddziały",
        tableId: "tableBranche",
        content: "branches",
        tableActions: "admin",
        link: "branches"
    });

    $('#tableBrancheViewContent').TableInfo({
        tableName: "Pracownicy-oddziału",
        tableId: "tableBrancheView",
        content: "branches",
        tableActions: "admin",
        link: "employees"
    });

    $('#tableRepCarContent').TableInfo({
        tableName: "Samochody-zastepcze",
        tableId: "tableRepCar",
        content: "repCars",
        tableActions: "admin",
        link: "repCars"
    });

    $('#tableParticipantContent').TableInfo({
        tableName: "Uczestnicy",
        tableId: "tableParticipant",
        content: "participants",
        tableActions: "admin",
        link: "participants"
    });

    $('#tableInsuranceContent').TableInfo({
        tableName: "Ubezpieczenia",
        tableId: "tableInsurance",
        content: "insurances",
        tableActions: "admin",
        link: "insurances"
    });

    $('#tableUmowaContent').TableInfo({
        tableName: "Moje umowy",
        tableId: "tableAgreements",
        content: "agreements",
        tableActions: "client",
        link: "agreements"
    });
    
    $('#tableAgreementContent').TableInfo({
        tableName: "Umowy",
        tableId: "tableAgreement",
        content: "agreements",
        link: "agreements"
    });
    
        $('#tableAgreementViewContent').TableInfo({
        tableName: "Szkody w umowie",
        tableId: "tableAgreementView",
        content: "agreements",
        link: "agreements"
    });

});

