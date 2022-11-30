import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setTitle("Company");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    Group root = new Group();

    final private int WIDTH = 1000;
    final private int HEIGHT = 600;
}