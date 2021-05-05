package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class SampleController implements Initializable{

    @FXML
    private TableColumn<Vacances, String> prenomColumn;

    @FXML
    private TextField txtPrenom;

    @FXML
    private TableColumn<Vacances, Double> totalColumn;

    @FXML
    private Button btnClear;

    @FXML
    private TextField txtTotal;

    @FXML
    private Button btnCalcul;

    @FXML
    private TableColumn<Vacances, Double> transportColumn;

    @FXML
    private Button btnEffacer;

    @FXML
    private TableColumn<Vacances, Double> hotelColumn;

    @FXML
    private TextField txtHotel;

    @FXML
    private TextField txtTransport;

    @FXML
    private Button btnModifier;

    @FXML
    private TableView<Vacances> vacancesTable;

    @FXML
    private TableColumn<Vacances, String> nomColumn;

    @FXML
    private TableColumn<Vacances, Double> destinationColumn;

    @FXML
    private ComboBox<String> cboDestination;

    @FXML
    private Button btnAjouter;

    @FXML
    private TextField txtNom;
    
    //liste pour les déstinations - cette liste permettera de donner des valeurs du combobox
    private ObservableList<String> list=(ObservableList<String>) FXCollections.observableArrayList("Paris", "Londres", "Toronto", "Honolulu", "Rio de Janeiro");
    
    //placer les voyageurs dans une observable list
    public ObservableList<Vacances> vacancesData=FXCollections.observableArrayList();
    
    //créer une méthode pour accéder à liste des voyageurs
    public ObservableList<Vacances> getvacancesData()
    {
    	return vacancesData;
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		cboDestination.setItems(list);
		//attribuer les valeurs aux colonnes du tableView
		prenomColumn.setCellValueFactory(new PropertyValueFactory <> ("prenom"));
		nomColumn.setCellValueFactory(new PropertyValueFactory <> ("nom"));
		destinationColumn.setCellValueFactory(new PropertyValueFactory <> ("destination"));
		transportColumn.setCellValueFactory(new PropertyValueFactory <> ("transport"));
		hotelColumn.setCellValueFactory(new PropertyValueFactory <> ("hotel"));
		totalColumn.setCellValueFactory(new PropertyValueFactory <> ("total"));
		vacancesTable.setItems(vacancesData);
	}
}
