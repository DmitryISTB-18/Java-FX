package sample;

import Model.Animals;
import Model.Request;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private ArrayList<Animals> animals = new ArrayList<>();

    @FXML
    private ListView<Animals> list;

    public void add(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addForm.fxml"));
        // а затем уже непосредственно вызываем загрузку дерева разметки из файла
        Parent root = loader.load();

        ModelAdd modelAdd = loader.getController();
        stage.setScene(new Scene(root));
        stage.setTitle("Добавить");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                ((Node)event.getSource()).getScene().getWindow() );
        stage.showAndWait();

        if (modelAdd.isClickCheck()) { // если нажали Добавить
            Animals animalsAdd = modelAdd.getAnimals();
            animals.add(animalsAdd); // add to list

            list.getItems().add(animalsAdd); //add to list
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // добавляем
        try {
            // создаем поток для чтения из файла
            FileInputStream fis = new FileInputStream("settings.xml");
            // создаем xml декодер из файла
            XMLDecoder decoder = new XMLDecoder(fis);


             //* С помощью decoder.readObject() читаем объект из файла
            // * а так как джава сама не может определить, что в файле
             //* мы ей подсказываем, указывая в скобочках (Settings)
            //* ну и просто загоняем в переменную settings

            Settings settings = (Settings) decoder.readObject();

            // а теперь заполняем форму
            animals = settings.ani;
            list.setItems(
                    FXCollections.observableArrayList(settings.ani));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * удалить
     */
    public void remove(){
        Animals selectedAnimals = list.getSelectionModel().getSelectedItem();
        if (selectedAnimals != null) {
            final int selectedIdx = list.getSelectionModel().getSelectedIndex();
            System.out.println(selectedIdx);
            list.getItems().remove(selectedIdx);
            animals.remove(selectedIdx);

        } else {
            // Ничего не выбрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка!!!");
            alert.setHeaderText("Вы ничего не выбрали!");
            alert.setContentText("Выберите животное из списка");

            alert.showAndWait();
        }

    }

    /**
     * Изменить
     */
    public void change(ActionEvent event) throws IOException{
        Animals selectedAnimals = list.getSelectionModel().getSelectedItem();
        if (selectedAnimals != null) {
            final int selectedIdx = list.getSelectionModel().getSelectedIndex();
            list.getItems().remove(selectedIdx);
            animals.remove(selectedIdx);

            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("addForm.fxml"));
            // а затем уже непосредственно вызываем загрузку дерева разметки из файла
            Parent root = loader.load();

            ModelAdd modelAdd = loader.getController();
            modelAdd.setAnimals(selectedAnimals);

            stage.setScene(new Scene(root));
            stage.setTitle("Добавить");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(
                    ((Node)event.getSource()).getScene().getWindow() );
            stage.showAndWait();

            Animals changedAnimals = modelAdd.getAnimals(); // добавить изменения

            list.getItems().add(selectedIdx, changedAnimals);
            animals.add(selectedIdx, changedAnimals);

        } else {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка!!!");
            alert.setHeaderText("Вы ничего не выбрали!");
            alert.setContentText("Выберите животное из списка");

            alert.showAndWait();
        }
    }

    public void onStageClose() {
        // создали экземпляр класса
        Settings settings = new Settings();
        settings.ani = animals;

        // добавляем
        try {
            // создаем поток для записи в файл experiment.xml
            FileOutputStream fos = new FileOutputStream("settings.xml");
            // создали энкодер, которые будет писать в поток
            XMLEncoder encoder = new XMLEncoder(fos);

            // записали настройки
            encoder.writeObject(settings);

            // закрыли энкодер и поток для записи
            // если не закрыть, то файл будет пустой
            encoder.close();
            fos.close();
        } catch (Exception e) {
            // на случай ошибки
            e.printStackTrace();
        }
    }


     // Запрос

    public void click(ActionEvent actionEvent) {
        Animals selectedAnimals = list.getSelectionModel().getSelectedItem();
        if (selectedAnimals != null) {
            final int selectedIdx = list.getSelectionModel().getSelectedIndex();

            Request request = animals.get(selectedIdx);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Запрос");
            alert.setHeaderText("Кол-во символов в названии: " + request.quanAni());

            alert.showAndWait();

        } else {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка!!!");
            alert.setHeaderText("Вы ничего не выбрали");
            alert.setContentText("Выберите животное из списка");

            alert.showAndWait();
        }
    }
}
