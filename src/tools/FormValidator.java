package tools;


import javafx.scene.control.TextField;
import exceptions.InvalidInputError;

public class FormValidator {

    public static boolean isInputFieldFilled( TextField textField) throws InvalidInputError {
        if (!textField.getText().equals("")) return true;
        throw new InvalidInputError("Error: The " + textField.getId() + " field is required.");
    }

    public static boolean isInputNumber(TextField textField) throws InvalidInputError {
        try {
            if (textField.getText().equals("NA")) return true;
            Double.parseDouble(textField.getText());
            return true;
        } catch (NumberFormatException e) {
            throw new InvalidInputError("Error: The " + textField.getId() + " requires a number.");
        }
    }

    public static boolean isNocValid(TextField textfield) throws InvalidInputError {
        if (textfield.getText().length() == 3) return true;
        throw new InvalidInputError("Error: The NOC abbreviation needs to have 3 letters.");

    }


}
