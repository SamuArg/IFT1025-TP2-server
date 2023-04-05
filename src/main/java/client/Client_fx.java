package client;

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

public class Client_fx extends Application {
    public static void main(String[] args){
        Client_fx.launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        HBox root = new HBox();
        VBox leftBox = new VBox();
        leftBox.setPrefSize(425,700);
        VBox rightBox = new VBox();
        rightBox.setPrefSize(425,700);
        Scene scene = new Scene(root,900,550);
        root.setPadding(new Insets(20,20,20,20));

        Text leftTitle = new Text("Liste de cours");
        leftTitle.setFont(new Font(20));
        leftTitle.setTextAlignment(TextAlignment.CENTER);
        leftTitle.setWrappingWidth(425);

        Text rightTitle = new Text("Formulaire d'inscription");
        rightTitle.setFont(new Font(20));
        rightTitle.setTextAlignment(TextAlignment.CENTER);
        rightTitle.setWrappingWidth(425);

        root.getChildren().add(leftBox);
        root.setSpacing(20);
        leftBox.setSpacing(20);
        rightBox.setSpacing(20);
        root.getChildren().add(rightBox);

        leftBox.getChildren().add(leftTitle);
        leftBox.setAlignment(Pos.TOP_LEFT);
        rightBox.getChildren().add(rightTitle);
        rightBox.setAlignment(Pos.TOP_RIGHT);

        TableView<Object> tableau = new TableView<>();
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

        ComboBox<Object> sessionChoice = new ComboBox<>();
        sessionChoice.setMinWidth(120);
        sessionChoice.getItems().addAll("Automne","Hiver","Été");

        chargerSession.getChildren().add(sessionChoice);

        Button charger = new Button("Charger");

        chargerSession.getChildren().add(charger);

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(10);
        pane.setVgap(10);
        Label prenom = new Label("Prénom");
        pane.add(prenom,0,1);
        TextField prenomField = new TextField();
        pane.add(prenomField,1,1);
        Label nom = new Label("Nom");
        pane.add(nom,0,2);
        TextField nomField = new TextField();
        pane.add(nomField,1,2);
        Label email = new Label("Email");
        pane.add(email,0,3);
        TextField emailField = new TextField();
        pane.add(emailField,1,3);
        Label matricule = new Label("Matricule");
        pane.add(matricule,0,4);
        TextField matriculeField = new TextField();
        pane.add(matriculeField,1,4);
        Button envoyer = new Button("Envoyer");
        pane.add(envoyer,1,5);
        rightBox.getChildren().add(pane);






        primaryStage.setTitle("Inscription UdeM");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

