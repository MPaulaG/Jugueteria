package sys.componentes;

import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("vlogin")//alias del validador

public class ValidacionesLogin implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        String usuario = value.toString().trim();
        if (usuario.length() == 0) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Ingrese el usuario"));
        } else {
            String expresionLetras = "^[a-zA-ZñÑáéíóúÁÉÍÓÚ]+$";
            boolean aceptable = Pattern.matches(expresionLetras, usuario);
            if (!aceptable) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                        "Ingrese solo letras"));

            }

        }

    }
}
