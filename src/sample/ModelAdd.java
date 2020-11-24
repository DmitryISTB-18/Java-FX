package sample;

import Model.Fish;
import Model.Birds;
import Model.Animals;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class ModelAdd implements Initializable {

    @FXML
    private TextField txtAnimalsName;

    @FXML
    private TextField txtHabitatType;

    @FXML
    private CheckBox checkBoxAdd;

    @FXML
    private RadioButton rbAdd, rbAdd1;

    @FXML
    private Button buttonAdd;

    private boolean clickCheck = false;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        // Group
        ToggleGroup group = new ToggleGroup();
        rbAdd.setToggleGroup(group);
        rbAdd1.setToggleGroup(group);


        txtHabitatType.setVisible(false);
        checkBoxAdd.setVisible(false);

        if (rbAdd.isSelected()) {
            txtHabitatType.setVisible(true);
        } else if (rbAdd1.isSelected()) {
            checkBoxAdd.setVisible(true);
        }
    }

    public void addAnimals(){
        // проверяем Корректность ввода
        if (!checkInput().equals("")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Некорректный ввод!!!");
            alert.setHeaderText("Ошибка");
            alert.setContentText(checkInput());

            alert.showAndWait();
            return;
        }

        clickCheck = true;
        Stage stage = (Stage) buttonAdd.getScene().getWindow();
        stage.close();
    }

    public Animals getAnimals() {
        String animalsName = txtAnimalsName.getText();

        if (rbAdd.isSelected()){
            String habitatType = txtHabitatType.getText();
            return new Fish(animalsName,  habitatType);

        } else {
            boolean relationshipFlight = checkBoxAdd.isSelected();
            return new Birds(animalsName, relationshipFlight);
        }
    }

    public void setAnimals(Animals animals){
        txtAnimalsName.setText(animals.getAnimalsName());


        if (animals instanceof Birds){
            rbAdd1.setSelected(true);
            Birds birds = (Birds) animals;
            checkBoxAdd.setSelected(birds.isRelationshipFlight());

        } else {
            rbAdd.setSelected(true);
            Fish fish = (Fish) animals;
            txtHabitatType.setText(fish.getHabitatType());
        }
    }



    /*
        метод, который проверяет входные значения!
     */
    private String checkInput(){
        String res = "";

        if (isEmpty(txtAnimalsName))
            res = "Введите название животного";


        if (rbAdd1.isSelected()) {

        } else if (rbAdd.isSelected()){
            if (isEmpty(txtHabitatType))
                res = "Введите место обитания";
        } else {
            res = "Выберите вид животного";
        }

        return res;
    }

    public boolean isClickCheck() {

        return clickCheck;
    }


    private boolean isEmpty(TextField txt){
        if (txt.getText().equals(""))
            return true;
        return false;
    }

    public void clickF(ActionEvent actionEvent) {

        txtHabitatType.setVisible(true);
        checkBoxAdd.setVisible(false);
    }

    public void clickB(ActionEvent actionEvent) {
        checkBoxAdd.setVisible(true);
        txtHabitatType.setVisible(false);
    }
}
