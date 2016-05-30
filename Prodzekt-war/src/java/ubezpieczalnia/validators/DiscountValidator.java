/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubezpieczalnia.validators;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Jacek
 */
@FacesValidator("DiscountValidator")
public class DiscountValidator implements Validator {

private static final String NUMBER_PATTERN = "^[0-9]{1,2}$";

    private Pattern pattern;
    private Matcher matcher;

    public DiscountValidator() {
        pattern = Pattern.compile(NUMBER_PATTERN);
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value.toString().isEmpty()) {
            FacesMessage msg = new FacesMessage("Pole wymagane");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        } else if (!pattern.matcher(value.toString()).matches()) {
            FacesMessage msg = new FacesMessage("Max wartość to 99%");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

}
