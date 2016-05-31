

function buyInsurance(insuranceId) {
    var url = "/Prodzekt-war/faces/customerPages/agreements/buyAgreements.xhtml";
    var form = $('<form action="' + url + '" method="post">' +
            '<input type="text" name="post_id" value="' + insuranceId + '" />' +
            '<input type="text" name="post_type" value="buyInsurance" />' +
            '</form>');
    $('body').append(form);
    form.submit();

}
