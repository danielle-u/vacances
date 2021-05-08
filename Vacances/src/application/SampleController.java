package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class SampleController implements Initializable{

	@FXML
	private TableColumn<Vacance, String> prenomColumn;

	@FXML
	private TextField txtPrenom;

	@FXML
	private TableColumn<Vacance, Double> totalColumn;

	@FXML
	private Button btnClear;

	@FXML
	private TableColumn<Vacance, Double> transportColumn;

	@FXML
	private Button btnEffacer;

	@FXML
	private TableColumn<Vacance, Double> hotelColumn;

	@FXML
	private TextField txtHotel;

	@FXML
	private TextField txtTransport;

	@FXML
	private Button btnModifier;

	@FXML
	private TableView<Vacance> vacancesTable;

	@FXML
	private TableColumn<Vacance, String> nomColumn;

	@FXML
	private TableColumn<Vacance, Double> destinationColumn;

	@FXML
	private ComboBox<String> cboDestination;

	@FXML
	private Button btnAjouter;

	@FXML
	private TextField txtNom;

	private Double pTotal=0.0;

	//liste pour les déstinations - cette liste permettera de donner des valeurs du combobox
	private ObservableList<String> list=(ObservableList<String>) FXCollections.observableArrayList("Paris", "Londres", "Toronto", "Honolulu", "Rio de Janeiro");

	//placer les voyageurs dans une observable list
	public ObservableList<Vacance> vacanceData=FXCollections.observableArrayList();

	//créer une méthode pour accéder à liste des voyageurs
	public ObservableList<Vacance> getvacanceData()
	{
		return vacanceData;
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
		vacancesTable.setItems(vacanceData);

		btnModifier.setDisable(true);
		btnEffacer.setDisable(true);
		btnClear.setDisable(true);

		showVacances(null);

		//mettre à jour l'affichage d'un voyageur sélectionné
		vacancesTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showVacances(newValue));

	}

	//méthode pour trouver des imputs non numériques
	@FXML
	public void verifNum()
	{
		txtTransport.textProperty().addListener((observable, oldValue, newValue) ->
		{
			if(!newValue.matches("^[0-9](\\.[0-9]+)?$"))
			{
				//si le imput est non numérique, ca le remplace 
				txtTransport.setText(newValue.replaceAll("[^\\d*\\.]",""));
			}
		});

		txtHotel.textProperty().addListener((observable, oldValue, newValue) ->
		{
			if(!newValue.matches("^[0-9](\\.[0-9]+)?$"))
			{
				//si le imput est non numérique, ca le remplace 
				txtHotel.setText(newValue.replaceAll("[^\\d*\\.]",""));
			}
		});

	}

	//vérifier champs vides
	private boolean noEmptyImput()
	{
		String errorMessage="";
		if(txtNom.getText().trim().equals(""))
		{
			errorMessage+="Le champ prénom ne doit pas etre vide! \n";
		}
		if(txtNom.getText()==null || txtNom.getText().length()==0)
		{
			errorMessage+="Le champ nom de doit pas etre vide! \n";
		}
		if(cboDestination.getValue()==null)
		{
			errorMessage+="Le champ déstination de doit pas etre vide! \n";
		}
		if(txtTransport.getText()==null || txtTransport.getText().length()==0)
		{
			errorMessage+="Le champ transport de doit pas etre vide! \n";
		}
		if(txtHotel.getText()==null || txtHotel.getText().length()==0)
		{
			errorMessage+="Le champ hotel de doit pas etre vide! \n";
		}
		if(errorMessage.length()==0)
		{
			return true;
		}
		else
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Champs manquants");
			alert.setHeaderText("Completer les champs manquants");
			alert.setContentText(errorMessage);
			alert.showAndWait();
			return false;
		}

	}
	//ajouter un voyageur
	@FXML
	void ajouter()
	{
		//Vérifier si un champ n'est pas vide
		if(noEmptyImput())
		{
			Vacance tmp=new Vacance();

			tmp=new Vacance();
			tmp.setPrenom(txtPrenom.getText()); 
			tmp.setNom(txtNom.getText());
			tmp.setDestination(cboDestination.getValue());
			tmp.setTransport(Double.parseDouble(txtTransport.getText()));
			tmp.setHotel(Double.parseDouble(txtHotel.getText()));
			Double prixT = Double.parseDouble(txtTransport.getText());
			Double prixH = Double.parseDouble(txtHotel.getText());
			Double tot= prixT + prixH;
			tmp.setTotal(tot);
			vacanceData.add(tmp);
			clearFields();
		}
	}

	//effacer le contenu des champs
	@FXML
	void clearFields()
	{
		txtPrenom.setText("");
		txtNom.setText("");
		cboDestination.setValue(null);
		txtTransport.setText("");
		txtHotel.setText("");

	}

	//afficher les étudiants
	public void showVacances (Vacance vacance)
	{
		if(vacance !=null)
		{
			txtPrenom.setText(vacance.getNom());
			txtNom.setText(vacance.getNom());
			cboDestination.setValue(vacance.getDestination());
			txtTransport.setText(Double.toString(vacance.getTransport()));
			txtHotel.setText(Double.toString(vacance.getHotel()));
			btnModifier.setDisable(false);
			btnEffacer.setDisable(false);
			btnClear.setDisable(false);
		}
		else
		{
			clearFields();
		}	


	}

	//mise à jour d'un voyageur
	@FXML
	public void updateVacance()
	{
		//Vérifier si un champ n'est pas vide
		if(noEmptyImput())
		{
			Vacance vacance=vacancesTable.getSelectionModel().getSelectedItem();

			vacance.setPrenom(txtPrenom.getText());
			vacance.setNom(txtNom.getText());
			vacance.setDestination(cboDestination.getValue());
			vacance.setTransport(Double.parseDouble(txtTransport.getText()));
			vacance.setHotel(Double.parseDouble(txtHotel.getText()));
			vacancesTable.refresh(); 
		}
	}

	//effacer un voyageur
	@FXML
	public void deleteVacance() 
	{
		int selectedIndex = vacancesTable.getSelectionModel().getSelectedIndex();
		if(selectedIndex >=0)
		{
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Effacer");
			alert.setContentText("confirmer la supression!");
			Optional<ButtonType> result = alert.showAndWait();
			if(result.get()==ButtonType.OK)
				vacancesTable.getItems().remove(selectedIndex);
		}
	}

	//afficher les statistiques du prix de transport
	@FXML
	void handleStats()
	{
		try
		{
			FXMLLoader loader=new FXMLLoader(Main.class.getResource("TransportStat.FXML"));
			AnchorPane pane=loader.load();
			Scene scene=new Scene(pane);
			TransportStat transportstat=loader.getController();
			transportstat.SetStats(vacanceData);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.setTitle("Statistiques");
			stage.show();
		}
		catch(IOException e)
		{
			//TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//afficher les statistiques du prix de l'hotel
	@FXML
	void handleStatsTwo()
	{
		try
		{
			FXMLLoader loader=new FXMLLoader(Main.class.getResource("HotelStatistiques.FXML"));
			AnchorPane pane=loader.load();
			Scene scene=new Scene(pane);
			HotelStatistiques hotelstatistiques=loader.getController();
			hotelstatistiques.SetStats(vacanceData);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.setTitle("Statistiques");
			stage.show();
		}
		catch(IOException e)
		{
			//TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//afficher les statistiques du prix total
	@FXML
	void handleStatsThree()
	{
		try
		{
			FXMLLoader loader=new FXMLLoader(Main.class.getResource("TotalStatistiques.FXML"));
			AnchorPane pane=loader.load();
			Scene scene=new Scene(pane);
			TotalStatistiques totalstatistiques=loader.getController();
			totalstatistiques.SetStats(vacanceData);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.setTitle("Statistiques");
			stage.show();
		}
		catch(IOException e)
		{
			//TODO Auto-generated catch block
			e.printStackTrace();
		}
	}





	//SAUVEGARDE DE DONNÉES

	//Récupérer le chemin (path) des fichiers si ca existe
	public File getVacanceFilePath()
	{
		Preferences prefs = Preferences.userNodeForPackage(Main.class);
		String filePath = prefs.get("filePath", null);

		if(filePath !=null)
		{
			return new File(filePath);
		}
		else
		{
			return null;
		}
	}

	//Attribuer un chemin de fichiers
	public void setVacanceFilePath(File file)
	{
		Preferences prefs = Preferences.userNodeForPackage(Main.class);
		if(file != null)
		{
			prefs.put("filePath", file.getPath());
		}
		else
		{
			prefs.remove("filePath");
		}
	}

	//Prendre les données de type XML et les convertir en données de type javaFx
	public void loadVacanceDataFromFile(File file)
	{
		try
		{
			JAXBContext context = JAXBContext.newInstance(VacanceListWrapper.class);
			Unmarshaller um = context.createUnmarshaller();

			VacanceListWrapper wrapper = (VacanceListWrapper) um.unmarshal(file);
			vacanceData.clear();
			vacanceData.addAll(wrapper.getVacances());
			setVacanceFilePath(file);

			//donner le titre du fichier schargé
			Stage pStage=(Stage) vacancesTable.getScene().getWindow();
			pStage.setTitle(file.getName());
		}
		catch (Exception e)
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erreur");
			alert.setHeaderText("les données n'ont pas été trouvées");
			alert.setContentText("Les données ne pouvaient pas etre trouvées dans le fichier : \n"+file.getPath());
			alert.showAndWait();
		}
	}

	//Prendre les données de type JavaFx et les convertir en type XML
	public void saveVacanceDataToFile(File file)
	{
		try
		{
			JAXBContext context = JAXBContext.newInstance(VacanceListWrapper.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			VacanceListWrapper wrapper = new VacanceListWrapper();
			wrapper.setVacances(vacanceData);

			m.marshal(wrapper,  file);

			//sauvegarder dans le régistre
			setVacanceFilePath(file);

			//donner le titre du fichier sauvegardé
			Stage pStage=(Stage) vacancesTable.getScene().getWindow();
			pStage.setTitle(file.getName());
		}
		catch(Exception e)
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erreur");
			alert.setHeaderText("données non sauvegardées");
			alert.setContentText("Les données ne pouvaient pas etre sauvegardées dans le fichier : \n"+file.getPath());
			alert.showAndWait();
		}
	}

	//Commencer un nouveau
	@FXML
	private void handleNew()
	{
		getvacanceData().clear();
		setVacanceFilePath(null);
	}

	/*
	 * Le FileCHooser permet à l'usager de choisir le fichier à ouvrir
	 */
	@FXML
	private void handleOpen()
	{
		FileChooser fileChooser = new FileChooser();

		//permettre un filtre sur l'extension du fichier à chercher
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*xml");
		fileChooser.getExtensionFilters().add(extFilter);

		//montrer le dialogue
		File file = fileChooser.showOpenDialog(null);

		if(file != null)
		{
			loadVacanceDataFromFile(file);
		}
	}

	/*
	 * Sauvegarde le fichier correspondant au voyageur atif
	 * S'il n'y a pas de fichier, le menu sauvegarde sous va s'affichier
	 */


	@FXML
	private void handleSave()
	{
		File vacanceFile = getVacanceFilePath();
		if(vacanceFile != null)
		{
			saveVacanceDataToFile(vacanceFile);
		}
		else
		{
			handleSaveAs();
		}
	}

	/*
	 * Ouvrir le FileChooser pour chemin
	 */

	@FXML
	private void handleSaveAs()
	{
		FileChooser fileChooser = new FileChooser();

		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files(*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);

		//sauvegarde
		File file = fileChooser.showSaveDialog(null);

		if(file != null)
		{
			//vérification de l'extension
			if(!file.getPath().endsWith(".xml"))
			{
				file = new File(file.getPath() + ".xml");
			}
			saveVacanceDataToFile(file);
		}
	}


}
