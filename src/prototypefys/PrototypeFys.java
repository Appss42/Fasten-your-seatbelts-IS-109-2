package prototypefys;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nl.hva.hboict.sql.DataRow;
import nl.hva.hboict.sql.DataTable;
import nl.hva.hboict.sql.SQLDataBase;

/**
 *y ertkerlgdf
 * @author Koen Hengsdijk
 */
public class PrototypeFys extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
      
        LoginScherm loginscherm = new LoginScherm();
        
        GridPane scherm = loginscherm.MaakHetScherm();
        
        BagageCatalogue cataloog = new BagageCatalogue(); 
        GridPane scherm2 = cataloog.MaakCatalogue();
    
    
       
    
    
    Scene scene = new Scene(scherm, 900, 800);
    Scene scene2 = new Scene(scherm2, 900, 800);    
    
        primaryStage.setTitle("loginscherm test");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
        
        Button login = new Button();
        login.setText("Login");
        login.setStyle("-fx-background-color: #F42BA0;-fx-text-fill:#FAFA4A");
        login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(scene2);
            }
        });
        
        loginscherm.VoegKnopToe(scherm, login);
    }

   
    
    
}
