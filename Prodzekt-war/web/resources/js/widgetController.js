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
    $('#tableClientViewContent').TableInfo({
        tableName: "Umowy",
        tableId: "tableClientView",
        content: "clients",
        tableActions: "admin",
        link: "agreements"
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
    $('#tableParticipantViewContent').TableInfo({
        tableName: "Szkody",
        tableId: "tableParticipantView",
        content: "participants",
        tableActions: "admin",
        link: "incidents"
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
        tableActions: "admin",
        link: "agreements",
        action: "1"
    });
    $('#tableAgreementIncidentsView').TableInfo({
        tableName: "Szkody w umowie",
        tableId: "tableIncidents",
        content: "agreements",
        tableActions: "client",
        link: "incidents",
        action: "1"
    });

    $('#tableValuationsCustomerViewContent').TableInfo({
        tableName: "Wyceny",
        tableId: "tableValuationsCustomerView",
        content: "incidents",
        tableActions: "client",
        link: "valuations"
    });


    $('#tableAgreementViewContent').TableInfo({
        tableName: "Szkody",
        tableId: "tableAgreementView",
        content: "agreements",
        tableActions: "admin",
        link: "incidents",
        action: "1"
    });
    $('#tableIncidentContent').TableInfo({
        tableName: "Szkody",
        tableId: "tableIncident",
        content: "incidents",
        tableActions: "admin",
        link: "incidents"
        
    });
    $('#tableIncidentViewContent').TableInfo({
        tableName: "Wyceny",
        tableId: "tableIncidentView",
        content: "incidents",
        tableActions: "admin",
        link: "valuations"
    });

    $('#tableValuationContent').TableInfo({
        tableName: "Wyceny",
        tableId: "tableValuation",
        content: "valuations",
        tableActions: "admin",
        link: "valuations"

    });


    $('#tablePlatnosciContent').TableInfo({
        tableName: "Nieopłacone umowy",
        tableId: "tablePaymentsInsurances",
        content: "agreements",
        tableActions: "client",
        link: "agreements"

    });

    $('#tablePlatnosciSzkodaContent').TableInfo({
        tableName: "Nieopłacone szkody",
        tableId: "tablePaymentsIncidents",
        content: "incidents",
        tableActions: "client",
        link: "incidents"
    });

    $('#tableIncidentWorkerContent').TableInfo({
        tableName: "Szkody",
        tableId: "tableIncidentWorker",
        content: "order",
        tableActions: "worker",
        link: "order",
        action: "3"
    });
    $('#tableIncidentWorkerAddContent').TableInfo({
        tableName: "Szkody",
        tableId: "tableIncidentWorkerAdd",
        content: "order",
        tableActions: "worker",
        link: "order",
        action: "2"
    });
    $('#tableOrderViewContent').TableInfo({
        tableName: "Wyceny",
        tableId: "tableOrderView",
        content: "order",
        tableActions: "worker",
        link: "valuations",
        action: "2"
    });
    $('#tableIncidentWorkerHistoryContent').TableInfo({
        tableName: "Szkody",
        tableId: "tableIncidentWorkerHistory",
        content: "order",
        tableActions: "worker",
        link: "valuations",
        action: "2"
    });

});