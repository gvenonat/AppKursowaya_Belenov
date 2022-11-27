import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Main extends Application {

    Group root = new Group();

    // HBox — это набор кнопок, текстов, полей для ввода, каждый объект которого расположен последовательно горизонтально
    HBox buttonBox = new HBox();
    // VBox является тем же самым, но хранит свои объекты (children) по вертикали
    VBox strings = new VBox(); // сюда поместим VBox
    ComboBox<User> userBox = new ComboBox<>();

    // 2
    Button buttonGetInfo = new Button("Info");
    Text textInfo = new Text();

    final private int WIDTH = 1000;
    final private int HEIGHT = 600;

    private ArrayList<User> users = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        fillData(); // fill areyList USER

        userBox.getItems().addAll(users);

        root.getChildren().add(strings);

        strings.setPadding(new Insets(10, 30, 10, 30));
        strings.setSpacing(20);

        strings.getChildren().add(new Text("Select the user"));
        strings.getChildren().add(buttonBox);

        buttonBox.setSpacing(10);
        buttonBox.getChildren().add(userBox);

        buttonBox.setSpacing(10);
        buttonBox.getChildren().add(userBox);
        buttonBox.getChildren().add(buttonGetInfo);
        buttonBox.getChildren().add(textInfo);

        Scene scene = new Scene(root, WIDTH, HEIGHT);
//        primaryStage.setTitle("Учет продаж тарифных планов операторов сотовой связи");
        primaryStage.setTitle("Учет продаж тарифных");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void fillData() {
        users.add(new User("Alice", 20, 150, false));
        users.add(new User("Bob", 34, 300, true));
        users.add(new User("Peter", 18, 100, false));
        users.add(new User("Kate", 38, 300, true));
        users.add(new User("Steve", 31, 250, true));
        users.add(new User("Alan", 62, 500, true));
        users.add(new User("Julia", 33, 320, true));
        users.add(new User("Patric", 37, 300, true));
        users.add(new User("Alexander", 34, 280, true));
        users.add(new User("George", 28, 180, true));
        users.add(new User("Mary", 22, 190, false));
    }
}