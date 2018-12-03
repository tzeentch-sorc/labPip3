package model;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.ServletException;
import java.security.InvalidParameterException;

@FacesValidator("model.YValidator")
public class YValidator implements Validator {

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        try{
            double yVal = (Double) o;
            if((yVal<=-3)||(yVal>=3)) throw new InvalidParameterException();
        }
        catch (InvalidParameterException e){
            FacesMessage msg =
                    new FacesMessage("Y value validation failed","Invalid Y. Shall be (-3..3)");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }catch (ClassCastException e) {
            FacesMessage msg =
                    new FacesMessage("Y value validation failed", "Y is a number (-3..3)");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }catch (NullPointerException e){
            FacesMessage msg =
                    new FacesMessage("Y value validation failed", "Y is required. Trust me, I`m a Doctor.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}
