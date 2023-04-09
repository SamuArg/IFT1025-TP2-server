package client.client_fx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import server.models.Course;

import java.util.ArrayList;

/**
 * Cette classe représente toute la partie graphique de l'application
 */
public class Vue extends HBox {

    private TableView<Object> tableau;
    private ComboBox<Object> sessionChoice;
    private Button charger;
    private Button envoyer;
    private TextField prenomField;
    private TextField nomField;
    private TextField emailField;
    private TextField matriculeField;
    private Alert errors;
    private Alert confirmation;

    /**
     * Constructeur initialisant toutes les composantes graphiques de l'application
     */
    public Vue(){
        VBox leftBox = new VBox();
        leftBox.setPrefSize(425,700);
        VBox rightBox = new VBox();
        rightBox.setPrefSize(425,700);
        this.setPadding(new Insets(20,20,20,20));

        Text leftTitle = new Text("Liste de cours");
        leftTitle.setFont(new Font(20));
        leftTitle.setTextAlignment(TextAlignment.CENTER);
        leftTitle.setWrappingWidth(425);

        Text rightTitle = new Text("Formulaire d'inscription");
        rightTitle.setFont(new Font(20));
        rightTitle.setTextAlignment(TextAlignment.CENTER);
        rightTitle.setWrappingWidth(425);

        this.getChildren().add(leftBox);
        this.setSpacing(20);
        leftBox.setSpacing(20);
        rightBox.setSpacing(20);
        this.getChildren().add(rightBox);

        leftBox.getChildren().add(leftTitle);
        leftBox.setAlignment(Pos.TOP_LEFT);
        rightBox.getChildren().add(rightTitle);
        rightBox.setAlignment(Pos.TOP_RIGHT);

        tableau = new TableView<>();
        TableColumn<Object, Object> column1 = new TableColumn<>("Code");
        column1.setCellValueFactory(new PropertyValueFactory<>("code"));
        TableColumn<Object, Object> column2 = new TableColumn<>("Cours");
        column2.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableau.getColumns().add(column1);
        tableau.getColumns().add(column2);
        tableau.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        leftBox.getChildren().add(tableau);

        HBox chargerSession = new HBox();
        chargerSession.setAlignment(Pos.CENTER);
        chargerSession.setSpacing(100);
        leftBox.getChildren().add(chargerSession);

        sessionChoice = new ComboBox<>();
        sessionChoice.setMinWidth(120);
        sessionChoice.getItems().addAll("Automne","Hiver","Ete");

        chargerSession.getChildren().add(sessionChoice);

        charger = new Button("Charger");

        chargerSession.getChildren().add(charger);

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(10);
        pane.setVgap(10);
        Label prenom = new Label("Prénom");
        pane.add(prenom,0,1);
        prenomField = new TextField();
        pane.add(prenomField,1,1);
        Label nom = new Label("Nom");
        pane.add(nom,0,2);
        nomField = new TextField();
        pane.add(nomField,1,2);
        Label email = new Label("Email");
        pane.add(email,0,3);
        emailField = new TextField();
        pane.add(emailField,1,3);
        Label matricule = new Label("Matricule");
        pane.add(matricule,0,4);
        matriculeField = new TextField();
        pane.add(matriculeField,1,4);
        envoyer = new Button("Envoyer");
        pane.add(envoyer,1,5);
        rightBox.getChildren().add(pane);
        errors = new Alert(Alert.AlertType.ERROR);
        confirmation = new Alert(Alert.AlertType.CONFIRMATION);
    }

    /**
     * Cette methode permet de trouver le tableau
     * @return Retourne le TableView contenu dans la variable tableau
     */
    public TableView<Object> getTableau(){
        return this.tableau;
    }

    /**
     * Cette méthode permet d'aller chercher la valeur de la session choisi par l'utilisateur
     * @return La String de la session choisi par l'utilisateur
     */
    public String getSessionChoice(){
        return (String) this.sessionChoice.getValue();
    }

    /**
     * Cette méthode permet de trouver la référence du Button charger
     * @return Le button charger
     */
    public Button getCharger(){
        return this.charger;
    }

    /**
     * Cette méthode permet de trouver la référence du Button envoyer
     * @return Le button envoyer
     */
    public Button getEnvoyer() {
        return this.envoyer;
    }

    /**
     * Cette méthode permet de remplir le TableView tableau avec les données voulues
     * @param courses Le ArrayList contenant les Course qui vont remplir le tableau
     */
    public void setCourses(ArrayList<Course> courses){
        for (int i = 0; i < courses.size(); i++){
            this.tableau.getItems().add(courses.get(i));
        }
    }

    /**
     * Cette méthode permet de trouver la référence du TextField prenomField
     * @return Le TextField prenomField
     */
    public TextField getPrenomField(){
        return prenomField;
    }
    /**
     * Cette méthode permet de trouver la référence du TextField nomField
     * @return Le TextField nomField
     */
    public TextField getNomField(){
        return nomField;
    }
    /**
     * Cette méthode permet de trouver la référence du TextField matriculeField
     * @return Le TextField matriculeField
     */
    public TextField getMatriculeField(){
        return matriculeField;
    }
    /**
     * Cette méthode permet de trouver la référence du TextField emailField
     * @return Le TextField emailField
     */
    public TextField getEmailField(){
        return emailField;
    }
    /**
     * Cette méthode permet de trouver la référence du Alert errors
     * @return Le Alert errors
     */
    public Alert getErrors(){
        return errors;
    }
    /**
     * Cette méthode permet de trouver la référence du Alert confirmation
     * @return Le Alert confirmation
     */
    public Alert getConfirmation(){
        return confirmation;
    }

    /**
     * Cette méthode trouve quel cours a été sélectionné par l'utilisateur dans le tableau
     * @return Le Course sélectionné par l'utilisateur dans le tableau
     */
    public Course getSelectedCourse(){
        return (Course) tableau.getSelectionModel().getSelectedItem();
    }

}

