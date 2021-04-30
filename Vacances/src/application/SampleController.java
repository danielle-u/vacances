package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SampleController {

    @FXML
    private TableColumn<?, ?> prenomColumn;

    @FXML
    private TextField txtPrenom;

    @FXML
    private TableColumn<?, ?> totalColumn;

    @FXML
    private Button btnClear;

    @FXML
    private TextField txtTotal;

    @FXML
    private Button btnCalcul;

    @FXML
    private TableColumn<?, ?> transportColumn;

    @FXML
    private Button btnEffacer;

    @FXML
    private TableColumn<?, ?> hotelColumn;

    @FXML
    private TextField txtHotel;

    @FXML
    private TextField txtTransport;

    @FXML
    private Button btnModifier;

    @FXML
    private TableView<?> vacancesTable;

    @FXML
    private TableColumn<?, ?> nomColumn;

    @FXML
    private TableColumn<?, ?> destinationColumn;

    @FXML
    private ComboBox<?> cboDestination;

    @FXML
    private Button btnAjouter;

    @FXML
    private TextField txtNom;

}
