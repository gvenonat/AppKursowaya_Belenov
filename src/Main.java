import database.DBAppManager;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.*;
import java.util.*;
import java.lang.Exception;


public class Main extends Application {

    Group root = new Group(); // обязательно
    VBox strings = new VBox(); // вертикально
    HBox login_HBox = new HBox(); // горизонтально - login
    HBox password_HBox = new HBox(); // горизонтально - password
    Button buttonAddSale = new Button("Подключиться");

    final private int WIDTH = 1000;
    final private int HEIGHT = 600;

    private static ArrayList<Sale> sales = new ArrayList<>();
    private static ArrayList<Tariff> tariffs = new ArrayList<>();
    private static ArrayList<Discount> discounts = new ArrayList<>();

    public static void main(String[] args) throws SQLException {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        // first window
        TextField login = new TextField();
        TextField password = new TextField();

        root.getChildren().add(strings);
        strings.getChildren().add(new HBox());// пустая строка
        strings.getChildren().add(new HBox());// пустая строка

        strings.setPadding(new Insets(10, 30, 10, 30));
        strings.setSpacing(40);

        strings.getChildren().add(new Text(" "));
        strings.getChildren().add(new Text("Авторизация пользователя:"));

        strings.setSpacing(10);
        strings.getChildren().add(login_HBox);
        login_HBox.getChildren().add(new Text("Ваш логин:\t"));
        login_HBox.getChildren().add(login);

        strings.setSpacing(10);
        strings.getChildren().add(password_HBox);
        password_HBox.getChildren().add(new Text("Ваш пароль:\t"));
        password_HBox.getChildren().add(password);

        strings.getChildren().add(buttonAddSale);

        buttonAddSale.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                // логика авторизации
                MainProcess mainProcess = new MainProcess();
                try {
                    DBAppManager dbAppManager = new DBAppManager(login.getText(), password.getText());
                    if (dbAppManager.checkConnection()) {
                        mainProcess.showWindow(login.getText(), password.getText());
                    } else {
                        strings.getChildren().add(new Text("Логин или пароль не верный"));
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        MenuBarManager menuBarManager = new MenuBarManager();
/*        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menuBarManager.getMenuBar());
        borderPane.getChildren().add(strings);
        Scene scene = new Scene(borderPane, WIDTH, HEIGHT);*/

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setTitle("Приложение отдела продаж ТПОСС");

        ((Group) scene.getRoot()).getChildren().addAll(menuBarManager.getMenuBar());

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}