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
@FacesValidator("TelephoneValidator")
public class TelephoneValidator implements Validator {

    private static final String NUMER_REGEX_1 = "\\d{9}";
    private static final String NUMER_REGEX_2 = "\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{3}";
    private static final String NUMER_REGEX_3 = "\\d{3}[\\s]\\d{7}";
    

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value.toString().isEmpty()) {
            FacesMessage msg = new FacesMessage("Pole wymagane");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        } else if (!value.toString().matches(NUMER_REGEX_1) && !value.toString().matches(NUMER_REGEX_2) && !value.toString().matches(NUMER_REGEX_3)) {
            FacesMessage msg = new FacesMessage("Błędny format");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

}
