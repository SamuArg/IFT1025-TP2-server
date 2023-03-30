package client;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Separator;
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
        Scene scene = new Scene(root,900,700);
        root.setPadding(new Insets(15,15,15,15));

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
        root.getChildren().add(rightBox);

        leftBox.getChildren().add(leftTitle);
        leftBox.setAlignment(Pos.TOP_LEFT);
        rightBox.getChildren().add(rightTitle);
        rightBox.setAlignment(Pos.TOP_RIGHT);


        primaryStage.setTitle("Inscription UdeM");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
