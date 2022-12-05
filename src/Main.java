import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
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
    HBox addSale = new HBox(); // горизонтально - добавить продажу
    HBox TariffsAll = new HBox(); // горизонтально - список всех тарифов
    HBox DiscountsAll = new HBox(); // горизонтально - список всех скидок
    HBox hBoxChartSale = new HBox(); // горизонтально - добавить продажу
    ComboBox<Tariff> tariffsBox = new ComboBox<>();
    ComboBox<Discount> discountsBox = new ComboBox<>();
    Text textInfo = new Text();
    Button buttonAddSale = new Button("Add Sale");

    Button buttonChartSale = new Button("Chart Sale");

    final private int WIDTH = 1000;
    final private int HEIGHT = 600;

    private static ArrayList<Sale> sales = new ArrayList<>();
    private static ArrayList<Tariff> tariffs = new ArrayList<>();
    private static ArrayList<Discount> discounts = new ArrayList<>();

    static DBAppManager dbAppManager = DBAppManager.getInstance();
    PieChart pieChart = null;

    public static void main(String[] args) throws SQLException {
        insertData();
        launch(args);
    }

    private static void insertData() throws SQLException {
        ResultSet tariffsData = dbAppManager.getTariffsData();
        while (tariffsData.next()) {
            tariffs.add(new Tariff(tariffsData.getInt(1),
                            tariffsData.getString(2),
                            tariffsData.getInt(3),
                            tariffsData.getString(4),
                            tariffsData.getDate(5),
                            tariffsData.getDate(6)));
        }

        discounts.add(new Discount());
        ResultSet discountsData = dbAppManager.getDiscountsData();
        while (discountsData.next()) {
            discounts.add(new Discount(discountsData.getInt(1),
                    discountsData.getInt(2),
                    discountsData.getString(3),
                    discountsData.getInt(4)));
        }

        ResultSet salesData = dbAppManager.getSalesData();
        while (salesData.next()) {
            sales.add(new Sale(salesData.getInt(1),
                    salesData.getInt(2),
                    salesData.getInt(3)));
        }
    }
    public void start(Stage primaryStage) throws Exception {

        root.getChildren().add(strings);
        strings.getChildren().add(new HBox());// пустая строка
        strings.getChildren().add(new HBox());// пустая строка

        strings.setPadding(new Insets(10, 30, 10, 30));
        strings.setSpacing(20);

        strings.getChildren().add(new Text("Добавить новую продажу:"));
        strings.getChildren().add(addSale);

        tariffsBox.getItems().addAll(tariffs);
        discountsBox.getItems().addAll(discounts);

        TariffsAll.setSpacing(10);
        TariffsAll.getChildren().add(tariffsBox);
        TariffsAll.getChildren().add(textInfo);
//        TariffsAll.setPrefWidth(50);

        DiscountsAll.setSpacing(10);
        DiscountsAll.getChildren().add(discountsBox);
        DiscountsAll.getChildren().add(textInfo);
//        DiscountsAll.setPrefWidth(50);

        addSale.setSpacing(10);
        addSale.getChildren().add(new Text("Название тарифа: "));
        addSale.getChildren().add(TariffsAll); // список всех тарифов
        addSale.getChildren().add(new Text("Скидка - сколько лет: "));
        addSale.getChildren().add(DiscountsAll); // список всех скидок
        addSale.getChildren().add(buttonAddSale);

        strings.getChildren().add(new HBox()); // пустая строка
        strings.getChildren().add(hBoxChartSale);
        hBoxChartSale.getChildren().add(new Text("Статистика по продажам: "));
        hBoxChartSale.getChildren().add(buttonChartSale); // отрисовать график

        buttonAddSale.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                // логика (добавления в бд + в классы + перерисовка графика)
                Tariff tariff = tariffsBox.getSelectionModel().getSelectedItem();
                Discount discount = discountsBox.getSelectionModel().getSelectedItem();
                long id = 0;
                if (tariff != null) {
                    try {
                        if (discount != null && discount.getId() != -1) {
                            id = dbAppManager.saveSaleData(tariff.getId(), discount.getId());
                            sales.add(new Sale((int) id, tariff.getId(), discount.getId()));
                            System.out.println(String.format("add Sale with [tariff]:%s, [tarId]:%s, [Discount]:%s", tariff.getName(), tariff.getId().toString(), discount));
                        } else {
                            id = dbAppManager.saveSaleData(tariff.getId());
                            sales.add(new Sale((int) id, tariff.getId()));
                            System.out.println(String.format("add Sale with [tariff]:%s, [tarId]:%s", tariff.getName(), tariff.getId().toString()));
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        buttonChartSale.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                // логика (перерисовка графика)
                Map<String, Integer> mapTariffs = new LinkedHashMap<>();
                for (Sale sale : sales) {
                    Tariff tariffForMap = tariffs.stream()
                                    .filter(tariff -> tariff.getId().equals(sale.getId_tariff()))
                                    .findFirst()
                                    .orElse(null);
                    String nameTariff = tariffForMap == null ? "unknown" : tariffForMap.getName();
                    Integer countTariff = mapTariffs.get(nameTariff) == null ? 0 : mapTariffs.get(nameTariff);
                    System.out.println(String.format("Map[%s]: счётчик = %s, fromWeb[id] = %s", nameTariff, countTariff, sale.getId_tariff().toString()));
                    mapTariffs.put(nameTariff, countTariff + 1);
                }
                Integer summ = 0;
                for (Map.Entry<String, Integer> entry : mapTariffs.entrySet()) {
                    summ += entry.getValue();
                }
                ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
                for (Map.Entry<String, Integer> entry : mapTariffs.entrySet()) {
                    data.add(new PieChart.Data(entry.getKey(), 100 * entry.getValue() / summ));
                }
                if (pieChart == null) {
                    pieChart = new PieChart(data);
                    pieChart.setTitle("Продажи тарифов");
                    strings.getChildren().addAll(pieChart);
                } else {
                    pieChart.getData().clear();
                    pieChart.getData().retainAll();
                    pieChart.setData(data);
                }
            }
        });

        MenuBarManager menuBarManager = new MenuBarManager();
/*        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menuBarManager.getMenuBar());
        borderPane.getChildren().add(strings);
        Scene scene = new Scene(borderPane, WIDTH, HEIGHT);*/

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setTitle("Company AIS_UPTP");

        ((Group) scene.getRoot()).getChildren().addAll(menuBarManager.getMenuBar());

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}