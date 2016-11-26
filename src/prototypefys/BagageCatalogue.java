package prototypefys;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import nl.hva.hboict.sql.DataRow;
import nl.hva.hboict.sql.DataTable;
import nl.hva.hboict.sql.SQLDataBase;

/**
 *
 * @author Koen Hengsdijk
 */
public class BagageCatalogue {

    BagageCatalogue() {
    }
    
    public final String DB_NAME = "MyAirline";
    public final String DB_SERVER = "localhost:3306";
    public final String DB_ACCOUNT = "beheerder";
    public final String DB_PASSWORD = "nooitgedacht";

    public GridPane MaakCatalogue() {
        
         // setup a connection to MyAirline database on my local server
        SQLDataBase dataBase
                = new SQLDataBase(DB_NAME, DB_SERVER, DB_ACCOUNT, DB_PASSWORD);
        
        // get a table of airport information from the database
        DataTable airportData
                = dataBase.executeDataTableQuery("SELECT * FROM bagage");
        
        GridPane root = new GridPane();
        root.getColumnConstraints().add(new ColumnConstraints(200));
        
        GridPane Zoekscherm = new GridPane();
        Zoekscherm.setPrefSize(150, 100);
        Zoekscherm.setMaxSize(150, 100);
        Zoekscherm.setStyle("-fx-base:orange;-fx-border-color:black");
        Zoekscherm.setAlignment(Pos.CENTER);

        Zoekscherm.setPrefSize(250, 250);
        Zoekscherm.setMaxSize(250, 250);
        
        StackPane EmptyPane = new StackPane();
        EmptyPane.setPrefSize(250, 150);
        
        TextField tekst = new TextField("search");

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(5, 12, 5, 12));
        hbox.setSpacing(10);
        

        Button buttonCurrent = new Button("main menu");
        buttonCurrent.setPrefSize(100, 20);

        Button buttonProjected = new Button("options");
        buttonProjected.setPrefSize(100, 20);
        hbox.getChildren().addAll(buttonCurrent, buttonProjected);

        Zoekscherm.add(tekst, 1, 0);
        
        root.add(EmptyPane, 0, 1);
        root.add(Zoekscherm, 0 , 3);
        root.add(hbox, 0, 2);
        
        
        root.add(createJavaFXReadOnlyDataTableView(airportData), 1 , 2 , 1, 2);
        

        return root;
        
        
    }
    private static TableView<DataRow> createJavaFXReadOnlyDataTableView(DataTable dt) {
        TableView<DataRow> tv = new TableView<>();
        tv.setPrefWidth(5000);

        // define a JavaFX TableColumn for every column in the DataTable
        for (int c = 0; c < dt.getNColumns(); c++) {
            TableColumn<DataRow, String> tc = new TableColumn(dt.getColumnName(c));
            tc.setStyle("-fx-alignment: CENTER;");
            tc.setResizable(true);
            final int colIndex = c;

            // configure the Cell Value generator
            tc.setCellValueFactory(data -> {
                DataRow dr = data.getValue();
                String cellValue;
                cellValue = dr.getString(colIndex);
                return new ReadOnlyStringWrapper(cellValue);
            });
            tv.getColumns().add(tc);
        }

        // Add all Data Table data to the TableView
        for (int i = 0; i < dt.size(); i++) {
            tv.getItems().add(dt.get(i));
        }

        // align the view with the boundaries of the container
        tv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        //tv.setColumnResizePolicy((param) -> true);

        return tv;
    }

    public void VoegKnopToe(GridPane scherm, Button Knop){
        
        scherm.add(Knop, 0, 3); 
    }
}
