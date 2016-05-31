$(document).ready(function () {
    console.log("start utils");
    isChecked();
    isClicked();
    addDataPicker();
    
    
});

function addDataPicker() {
    $(".szkoda-dataZdarzenia").datepicker();
    $( ".szkoda-dataZdarzenia" ).datepicker( "option", "dateFormat", "dd-mm-yy" );
}

function isClicked() {
    $("#buyInsuranceForm").on("click", "input[type='submit']", function(){
        var value = $("#umowaCar").prop("checked");
        if(value === false) {
            $("#buyInsuranceForm input[type='text']").text("2000");
        }
    });
}


function isChecked() {
    $("#umowaCar").prop("checked", true);
    $(".car-select").prop("disabled", true);
    $("#umowaCar").on("change", function () {
        var value = $(this).prop("checked");
        if (value === false) {
            $("#buyInsuranceForm input[type='text']").prop("disabled", true);
            $(".car-select").prop("disabled", false);
        }
        else {
            $("#buyInsuranceForm input[type='text']").prop("disabled", false);
            $(".car-select").prop("disabled", true);
        }
    });
}
