import com.sun.jdi.event.ExceptionEvent;
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
    ComboBox<Tariffs> tariffsBox = new ComboBox<>();
    ComboBox<Discounts> discountsBox = new ComboBox<>();
    Text textInfo = new Text();
    Button buttonAddSale = new Button("Add Sale");

    final private int WIDTH = 1000;
    final private int HEIGHT = 600;

    private ArrayList<Sales> sales = new ArrayList<>();
    private ArrayList<Tariffs> tariffs = new ArrayList<>();
    private ArrayList<Discounts> discounts = new ArrayList<>();

    public static void main(String[] args) {
        try {
            connectDB();
        } catch (Exception e) {
            return;
        }
        insertData();
        launch(args);
    }

    private static void connectDB() throws SQLException {
        final String user = "postgres";
        final String password = "12345";
        final String url = "jdbc:postgresql://localhost:5432/AIS_UPTP";

        final Connection connection = DriverManager.getConnection(url, user, password);

        try (PreparedStatement statement = (connection).prepareStatement("SELECT amount, time_in_company FROM discounts WHERE id = (?)")) {

            statement.setInt(1, 2);

            final ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String byAmount = "amount: " + resultSet.getString("amount");
                final int byTimeInCompany = resultSet.getInt("time_in_company");
                System.out.println(byAmount);
                System.out.println(byTimeInCompany);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            connection.close();
        }
    }

    private static void insertData(){

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

        DiscountsAll.setSpacing(10);
        DiscountsAll.getChildren().add(discountsBox);
        DiscountsAll.getChildren().add(textInfo); // зачем ?

        addSale.setSpacing(10);
        addSale.getChildren().add(new Text("Название тарифа: "));
        addSale.getChildren().add(TariffsAll); // список всех тарифов
        addSale.getChildren().add(new Text("Скидка: "));
        addSale.getChildren().add(DiscountsAll); // список всех скидок
        addSale.getChildren().add(buttonAddSale);

        strings.getChildren().add(new Text("График продаж: "));

        buttonAddSale.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                // логика (добавления в бд + в классы + перерисовка графика)

            }
        });

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setTitle("Company");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}