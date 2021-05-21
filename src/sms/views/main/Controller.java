package sms.views.main;

import sms.data.PrimeGood;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sms.data.Recipe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class Controller implements Initializable {
    @FXML
    private Button createRecipe;
    @FXML
    private TableView ingredientsTable;
    @FXML
    private TableColumn idCol1;
    @FXML
    private TableColumn nameCol1;
    @FXML
    private TableColumn priceunitCol1;
    @FXML
    private TextField recipeIDTXT;
    @FXML
    private TextField recipeNameTXT;
    @FXML
    private ComboBox setIngredient;
    @FXML
    private TextField amountTXT;
    @FXML
    private TableView recipesTable;
    @FXML
    private Button addButton;
    @FXML
    private TableView<PrimeGood> primerGoodTable;
    @FXML
    private TableColumn<PrimeGood, String> typeCol;
    @FXML
    private TableColumn<PrimeGood, Integer> idCol;
    @FXML
    private TableColumn<PrimeGood, String> nameCol;
    @FXML
    private TableColumn<PrimeGood, Integer> priceCol;
    @FXML
    private TableColumn<PrimeGood, Integer> amountCol;
    @FXML
    private TableColumn<PrimeGood, Double> priceunitCol;
    @FXML
    private ComboBox<String> typeset;
    @FXML
    private TextField idset;
    @FXML
    private TextField nameset;
    @FXML
    private TextField priceset;
    @FXML
    private TextField amountset;

    private ObservableList<PrimeGood> PGlist;
    private ObservableList<Recipe> RecipeList;

    public void createPrimeGood(ActionEvent actionEvent) {
        if (typeset.getSelectionModel().getSelectedIndex() < 0 || idset.getText().equals("") ||
                nameset.getText().equals("") || priceset.getText().equals("") ||
                amountset.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Error adding data");
            alert.setContentText("NO EMPTY CAMPS ALLOWED");
            alert.showAndWait();
        } else {
            boolean isIn = false;
            for (int i = 0; i < PGlist.size(); i++) {
                if (PGlist.get(i).getNumID() == Integer.parseInt(idset.getText())) {
                    isIn = true;
                }
            }
            if (!isIn) {
                PGlist.add(new PrimeGood(typeset.getSelectionModel().getSelectedItem(), Integer.parseInt(idset.getText()),
                        Double.parseDouble(priceset.getText()), Integer.parseInt(amountset.getText()),
                        nameset.getText()));
                saveFile(PGlist);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Error adding data");
                alert.setContentText("ID CANNOT BE REPEATED");
                alert.showAndWait();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        PGlist = FXCollections.observableArrayList(readFile());
        RecipeList = FXCollections.observableArrayList(readRecipe());

        typeset.getItems().addAll("Frozen", "Cool", "Dry", "Paper");
        try {
            for (int i = 0; i < PGlist.size(); i++) {
                setIngredient.getItems().add(PGlist.get(i).getName());
            }
        } catch (Exception e) {}

        typeCol.setCellValueFactory(new PropertyValueFactory("type"));
        idCol.setCellValueFactory(new PropertyValueFactory("numID"));
        nameCol.setCellValueFactory(new PropertyValueFactory("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory("price"));
        amountCol.setCellValueFactory(new PropertyValueFactory("amountPerPack"));
        priceunitCol.setCellValueFactory(new PropertyValueFactory("pricePerUnit"));

        primerGoodTable.setItems(PGlist);
    }

    private List<PrimeGood> readFile() {
        try {
            return Files.lines(Paths.get("PGList.txt"))
                    .map(line -> new PrimeGood(line.split(";")[0], Integer.parseInt(line.split(";")[1]),
                            Double.parseDouble(line.split(";")[2]), Integer.parseInt(line.split(";")[3]),
                                    line.split(";")[4])).collect(Collectors.toList());
        } catch (Exception e) {
            return null;
        }
    }

    private List<Recipe> readRecipe() {
        List<String> aux = new ArrayList<>();
        List<Recipe> auxList = null;
        try {
            BufferedReader input = new BufferedReader(new FileReader("RecipeList.txt"));
            String s = input.readLine();
            while (s != null) {
                aux.add(s);
                s = input.readLine();
            }
            Recipe auxRec = new Recipe(Integer.parseInt(aux.get(0)), aux.get(1), 0);
            int ingNum = Integer.parseInt(aux.get(2));
            aux.remove(0);
            aux.remove(1);
            aux.remove(2);
            for (int i = 0; i < aux.size(); i = i +2) {
                PrimeGood auxPG = null;
                for (int j = 0; j < PGlist.size(); j++) {
                    if (PGlist.get(j).getNumID() == Integer.parseInt(aux.get(i))) {
                        auxPG = PGlist.get(j);
                    }
                }
                    auxRec.addPGood(auxPG, Integer.parseInt(aux.get(i+1)));
            }
                auxList.add(auxRec);
        } catch (Exception e) { return null; }
        return auxList;
    }

    private void saveFile(List<PrimeGood> PGList) {
        try (PrintWriter pw = new PrintWriter("PGList.txt")){
            PGList.stream().forEach(f -> pw.println(f.toString()));
        } catch (Exception e) {}
    }

    public void createRecipe(ActionEvent actionEvent) {
        if (recipeIDTXT.getText().equals("") || recipeNameTXT.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Error adding data");
            alert.setContentText("CAMPS CANNOT BE EMPTY");
            alert.showAndWait();
        } else {
            boolean isIn = false;
            for (int i = 0; i < RecipeList.size(); i++) {
                if (RecipeList.get(i).getNumID() == Integer.parseInt(recipeIDTXT.getText())) {
                    isIn = true;
                }
            }
            if (!isIn) {
                Recipe aux = new Recipe(Integer.parseInt(recipeIDTXT.getText()), recipeNameTXT.getText(), 0);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Error adding data");
                alert.setContentText("This recipe already exists");
                alert.showAndWait();
            }
        }
    }
}
