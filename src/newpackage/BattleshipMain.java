package newpackage;

import java.util.Random;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import newpackage.BoardFx.Cell;


public class BattleshipMain extends Application {

    private boolean running = false;
    private BoardFx enemyBoard, playerBoard;

    private int shipsToPlace = 5;
    public int enemymovesleft = 40;
    
    int playermovesleft = 40;
    
    private boolean enemyTurn = false;

    private Random random = new Random();

    private Parent createContent() {
        BorderPane root = new BorderPane();
        root.setPrefSize(1500, 900);

        root.setTop(new Text( "moves to finish "+ enemymovesleft));
        
         MenuBar menuBar = new MenuBar();
      Menu menu = new Menu("Menu");
      
      Menu subMenu = new Menu("APPLICATION");
      MenuItem menuItem11 = new MenuItem("LOAD");
      subMenu.getItems().add(menuItem11);
      menu.getItems().add(subMenu);
      
      MenuItem menuItem1 = new MenuItem("DETAILS");
      menu.getItems().add(menuItem1);
      
      
     
      MenuItem menuItem12 = new MenuItem("EXIT");
      subMenu.getItems().add(menuItem12);
      menu.getItems().add(subMenu);
      
      menuBar.getMenus().add(menu);
      
        VBox vBox = new VBox(menuBar);
               
            root.setRight(vBox);
                 
        
        enemyBoard = new BoardFx(true, event -> {
            if (!running){
                playermovesleft = playermovesleft - 1 ;
                return;
        }
            Cell cell = (Cell) event.getSource();
            if (cell.wasShot)
                return;

            enemyTurn = !cell.shoot();

            if (enemyBoard.ships == 0 || enemymovesleft == 0) {
                System.out.println("YOU WIN");
                System.exit(0);
            }

            if (enemyTurn)
                enemyMove();
        });

        playerBoard = new BoardFx(false, event -> {
            if (running)
                return;

            Cell cell = (Cell) event.getSource();
            if (playerBoard.placeShip(new Ship(shipsToPlace, event.getButton() == MouseButton.PRIMARY), cell.x, cell.y)) {
                if (--shipsToPlace == 0) {
                    startGame();
                }
            }
        });

        HBox hbox = new HBox(100, playerBoard, enemyBoard);
        hbox.setAlignment(Pos.CENTER);

        root.setCenter(hbox);

        return root;
    }

    private void enemyMove() {
        while (enemyTurn) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);
            
             enemymovesleft = enemymovesleft - 1;
            BoardFx.Cell cell = playerBoard.getCell(x, y);
            if (cell.wasShot)
                continue;
            
            enemyTurn = cell.shoot();

            if (playerBoard.ships == 0 || playermovesleft == 0) {
                System.out.println("YOU LOSE");
                System.exit(0);
            }
        }
    }

    private void startGame() {
        // place enemy ships
        int type = 5;
        
        while (type > 0) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            if (enemyBoard.placeShip(new Ship(type, Math.random() < 0.5), x, y)) {
                type--;
            }
        }

        running = true;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent());
        primaryStage.setTitle("MediaBattleship");
        
       
       
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
       }
     

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}