$(document).ready(function () {
    console.log("start utils");
    isChecked();
    isClicked();
    addDataPicker();
    
    
});

function addDataPicker() {
    $(".data-picker-class").datepicker();
    $( ".data-picker-class" ).datepicker( "option", "dateFormat", "dd-mm-yy" );
    
}

function isClicked() {
    $("#buyInsuranceForm").on("click", "input[type='submit']", function(){
        var value = $("#umowaCar").prop("checked");
        if(value === false) {
            $("#buyInsuranceForm input[type='text']").text("2000");
            $("#buyInsuranceForm .paliwo-select").val("Diesel");
        }
    });
}


function isChecked() {
    $("#umowaCar").prop("checked", true);
    $(".car-select").prop("disabled", true);
    $("#umowaCar").on("change", function () {
        $(".car-select").prop("selectedIndex", 0);
        var value = $(this).prop("checked");
        if (value === false) {
            $("#buyInsuranceForm input[type='text']").prop("disabled", true);
            $("#buyInsuranceForm select").prop("disabled", true);
            $(".car-select").prop("disabled", false);
        }
        else {
            $("#buyInsuranceForm input[type='text']").prop("disabled", false);
             $("#buyInsuranceForm select").prop("disabled", false);
            $(".car-select").prop("disabled", true);
        }
    });
}
