import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;
import java.lang.Exception;


public class Main extends Application {

    Group root = new Group(); // обязательно
    VBox strings = new VBox(); // вертикально
    HBox addSale = new HBox(); // горизонтально - добавить продажу
    HBox TariffsAll = new HBox(); // горизонтально - список всех тарифов
    HBox DiscountsAll = new HBox(); // горизонтально - список всех скидок
    HBox hBoxChartSale = new HBox(); // горизонтально - добавить продажу
    HBox nothing = new HBox(); // горизонтально - добавить продажу
    ComboBox<Tariffs> tariffsBox = new ComboBox<>();
    ComboBox<Discounts> discountsBox = new ComboBox<>();
    Text textInfo = new Text();
    Button buttonAddSale = new Button("Add Sale");

    Button buttonChartSale = new Button("Chart Sale");

    final private int WIDTH = 1000;
    final private int HEIGHT = 600;

    private static ArrayList<Sales> sales = new ArrayList<>();
    private static ArrayList<Tariffs> tariffs = new ArrayList<>();
    private static ArrayList<Discounts> discounts = new ArrayList<>();

    public static void main(String[] args) throws SQLException {
        insertData();
        launch(args);
    }

    private static void insertData() throws SQLException {
        DBAppManager dbAppManager = DBAppManager.getInstance();

        ResultSet tariffsData = dbAppManager.getTariffsData();
        while (tariffsData.next()) {
            tariffs.add(new Tariffs(tariffsData.getInt(1),
                            tariffsData.getString(2),
                            tariffsData.getInt(3),
                            tariffsData.getString(4),
                            tariffsData.getDate(5),
                            tariffsData.getDate(6)));
        }

        ResultSet discountsData = dbAppManager.getDiscountsData();
        while (discountsData.next()) {
            discounts.add(new Discounts(discountsData.getInt(1),
                    discountsData.getInt(2),
                    discountsData.getString(3),
                    discountsData.getInt(4)));
        }

/*        ResultSet salesData = dbAppManager.getSalesData();
        while (salesData.next()) {
            tariffs.add(new Tariffs(tariffsData.getInt(1),
                    tariffsData.getString(2),
                    tariffsData.getInt(3),
                    tariffsData.getString(4),
                    tariffsData.getDate(5),
                    tariffsData.getDate(6)));
        }*/
    }
    public void start(Stage primaryStage) throws Exception {

        root.getChildren().add(strings);

        strings.setPadding(new Insets(10, 30, 10, 30));
        strings.setSpacing(20);

        strings.getChildren().add(new Text("Добавить новую продажу:"));
        strings.getChildren().add(addSale);

        // это поле выбора из данных
        tariffsBox.getItems().addAll(tariffs);
        discountsBox.getItems().addAll(discounts);

        TariffsAll.setSpacing(10);
        TariffsAll.getChildren().add(tariffsBox);
        TariffsAll.getChildren().add(textInfo); // зачем ?
//        TariffsAll.setPrefWidth(50);

        DiscountsAll.setSpacing(10);
        DiscountsAll.getChildren().add(discountsBox);
        DiscountsAll.getChildren().add(textInfo); // зачем ?
//        DiscountsAll.setPrefWidth(50);

        addSale.setSpacing(10);
        addSale.getChildren().add(new Text("Название тарифа: "));
        addSale.getChildren().add(TariffsAll); // список всех тарифов
        addSale.getChildren().add(new Text("Скидка - сколько лет: "));
        addSale.getChildren().add(DiscountsAll); // список всех скидок
        addSale.getChildren().add(buttonAddSale);

        strings.getChildren().add(nothing);
        strings.getChildren().add(hBoxChartSale);
        hBoxChartSale.getChildren().add(new Text("График продаж: "));
        hBoxChartSale.getChildren().add(buttonChartSale); // отрисовать график

        buttonAddSale.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                // логика (добавления в бд + в классы + перерисовка графика)

            }
        });

        buttonChartSale.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                // логика (добавления в бд + в классы + перерисовка графика)

            }
        });

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setTitle("Company AIS_UPTP");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}