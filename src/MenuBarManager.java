import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;

public class MenuBarManager {
    private MenuBar menuBar;
    private Menu fileMenu;
    private Menu editMenu;
    private Menu helpMenu;
    private MenuItem newItem;
    private MenuItem openFileItem;
    private MenuItem exitItem;
    private MenuItem copyItem;
    private MenuItem pasteItem;

    public MenuBarManager() {
        // Create MenuBar
        menuBar = new MenuBar();

        // Create menus
        fileMenu = new Menu("File");
//        editMenu = new Menu("Edit");
        helpMenu = new Menu("Help");

        // Create MenuItems
//        newItem = new MenuItem("New");
//        openFileItem = new MenuItem("Open File");
        exitItem = new MenuItem("Exit");

//        copyItem = new MenuItem("Copy");
//        pasteItem = new MenuItem("Paste");
        helpMenu.getItems().add(new MenuItem("Вы можете добавлять новые продажи и вычислить самые выгодные."));

        // Add menuItems to the Menus
//        fileMenu.getItems().addAll(newItem, openFileItem, exitItem);
        fileMenu.getItems().addAll(exitItem);
//        editMenu.getItems().addAll(copyItem, pasteItem);

        // Add Menus to the MenuBar
        menuBar.getMenus().addAll(fileMenu, helpMenu);


//        helpMenu.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                helpMenu.setText("Вы можете добавлять новые продажи и вычислить самые выгодные.");
//                helpMenu.
//            }
//        });

        // Set Accelerator for Exit MenuItem.
//        exitItem.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));

        // When user click on the Exit item.
        exitItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }
}
