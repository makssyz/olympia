package tools;

import javafx.scene.control.TextField;
import exceptions.InvalidInputError;
import lists.Database;
import lists.Form;

public class FormValidator {
    Form form;
    Database database;

    public FormValidator(Form form) {
        this.form = form;
        this.database = form.getDatabase();
    }

    public void validateInput() throws InvalidInputError {

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
            isIdValid();
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

    private void isInputFieldFilled(TextField textField) throws InvalidInputError {
        if (!textField.getText().equals("")) return;
        throw new InvalidInputError("Error: The " + textField.getId() + " is required.");
    }

    private void isInputNumber(TextField textField) throws InvalidInputError {
        try {
            if (textField.getText().equals("NA")) return;
            Double.parseDouble(textField.getText());
        } catch (NumberFormatException e) {
            throw new InvalidInputError("Error: The " + textField.getId() + " requires a number.");
        }
    }

    private void isNocValid(TextField nocField) throws InvalidInputError {
        if (nocField.getText().length() == 3) return;
        throw new InvalidInputError("Error: The NOC abbreviation needs to have 3 letters.");
    }

    private void isIdValid() throws InvalidInputError {
        int id = Integer.parseInt(form.getId());
        String typedName = form.getName();
        String athleteName = database.getAthleteMap().get(id).getName();
        if (typedName.equals(athleteName)) return;
        throw new InvalidInputError("Error: The Athlete with the id " + id + " is called '" + athleteName
                + "', not '" + typedName + "'.");
    }

}
