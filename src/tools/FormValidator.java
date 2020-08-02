package tools;

import items.Athlete;
import javafx.scene.control.TextField;
import exceptions.InvalidInputError;
import lists.Form;

public class FormValidator {

    public static void validateInput(Form form) throws InvalidInputError {
        isInputFieldFilled(form.getNameInputField());
        isInputFieldFilled(form.getTeamInputField());
        isInputFieldFilled(form.getNocInputField());
        isNocValid(form.getNocInputField());
        isInputFieldFilled(form.getYearInputField());
        isInputFieldFilled(form.getCityInputField());
        isInputFieldFilled(form.getSportInputField());
        isInputFieldFilled(form.getEventInputField());

        if (form.isExists()) {
            isInputFieldFilled(form.getIdInputField());
            isIdValid(form);
        }

        isInputNumber(form.getYearInputField());
        if (!form.getBirthyearInputField().getText().equals("")) {
            isInputNumber(form.getBirthyearInputField());
        } else {
            form.getBirthyearInputField().setText("NA");
            form.setBirthyear("NA");
        }
        if (!form.getHeightInputField().getText().equals("")) {
            isInputNumber(form.getHeightInputField());
        } else {
            form.getHeightInputField().setText("NA");
            form.setHeight("NA");
        }
        if (!form.getWeightInputField().getText().equals("")) {
            isInputNumber(form.getWeightInputField());
        } else {
            form.getWeightInputField().setText("NA");
            form.setWeight("NA");
        }
    }

    private static void isInputFieldFilled(TextField textField) throws InvalidInputError {
        if (!textField.getText().equals("")) return;
        throw new InvalidInputError("Error: The " + textField.getId() + " is required.");
    }

    private static void isInputNumber(TextField textField) throws InvalidInputError {
        try {
            if (textField.getText().equals("NA")) return;
            Double.parseDouble(textField.getText());
        } catch (NumberFormatException e) {
            throw new InvalidInputError("Error: The " + textField.getId() + " requires a number.");
        }
    }

    private static void isNocValid(TextField nocField) throws InvalidInputError {
        if (nocField.getText().length() == 3) return;
        throw new InvalidInputError("Error: The NOC abbreviation needs to have 3 letters.");
    }

    private static void isIdValid(Form form) throws InvalidInputError {
        int id = Integer.parseInt(form.getId());
        String typedName = form.getName();
        String typedBirthyear = form.getBirthyear();
        Athlete athlete = form.getDatabase().getAthleteMap().get(id);
        String athleteName = athlete.getName();
        String athleteBirthyear = athlete.getBirthyear();
        if (typedName.equals(athleteName) && typedBirthyear.equals(athleteBirthyear)) return;
        throw new InvalidInputError("Error: The athlete with the id " + id + " is '" + athlete
                + "' and doesn't match provided athlete '" + typedName + " (" + typedBirthyear + ")'.");
    }

}
