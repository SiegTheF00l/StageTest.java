package stagetest;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

/**
 *
 * @author cmason
 */
public class StageTest extends Application {
    private static  final int BOARD_SIZE = 60;
    private static  final int SQUARE_SIZE = 10;
    private static GridPane character = new GridPane();
    private static char[][] playerModel = new char[30][30];
    private static int xcoord = 0;
    
  
    
    private static int tick = 0; 
    private static int fallTick = 0;
    
      private static void configureGridLayout(GridPane gridPane) {
        for (int i = 0, j = 0; i < BOARD_SIZE-15; i++, j++) {
           // if(j<23) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setMinHeight(SQUARE_SIZE);
            rowConstraints.setPrefHeight(SQUARE_SIZE);
            rowConstraints.setMaxHeight(SQUARE_SIZE);
            rowConstraints.setValignment(VPos.CENTER);
            gridPane.getRowConstraints().add(rowConstraints);

           // }
            ColumnConstraints colConstraints = new ColumnConstraints();
            colConstraints.setMinWidth(SQUARE_SIZE);
            colConstraints.setMaxWidth(SQUARE_SIZE);
            colConstraints.setPrefWidth(SQUARE_SIZE);
            colConstraints.setHalignment(HPos.CENTER);
            gridPane.getColumnConstraints().add(colConstraints);
            
        }
    }
      

       private static File getOpenPath() {
        File path;

        final JFileChooser fc = new JFileChooser();
        int returnVal;
        
        returnVal = fc.showOpenDialog(null);

        path = null;
        if (returnVal == 0) {
            path = fc.getSelectedFile();
        }

        
        return path;
    }
        
   public  void loadFile(char array[][], int boardSize, String name) throws IOException {
        File Save;
       
        //Save = new File( this.getClass().getResource("/Resources/"
                    //+ "Default.txt").toString());
          
        Save = new File(name);
        Scanner fileIn;
        boolean valid;

        try {
            
            
            valid = true;
            if (Save.exists()) {
                fileIn = new Scanner(Save);


                for (int i =0; i < boardSize; i++){
                    for (int count = 0; count < boardSize; count++){
                        array[count][i] = fileIn.next().charAt(0);
                    }
                }
                
                character.getChildren().clear();
                for (int i = 0; i < boardSize; i++){
                    for (int count = 0 ; count < boardSize; count++){
                      
                        setBoard(count, i, false);
                       
                    }
                    
                }
                
            } else {
                valid = false;
               // msgBox("File Does Not Exist!");
               System.out.println("not found?!");
            }
        } catch (Exception e) {
            System.out.println("WORKS");
        }

    }
    
       public static void setBoard(int i, int count, boolean print) {

        Rectangle fill;
        Point loc = new Point(i, count);

        fill = new Rectangle(2, 2, pickColor(loc));
       
            
     character.setGridLinesVisible(false);
        try {
            if (i < 30
                    && count < 30) {
                character.add(fill, i, count);
            }
        } catch (Exception e) {
        }
    }
      
        private static javafx.scene.paint.Color pickColor(Point coord) {

        javafx.scene.paint.Color rectColor
                = playerModel[coord.x][coord.y] == 'a' ? javafx.scene.paint.Color.SALMON
                        : playerModel[coord.x][coord.y] == 'R' ? javafx.scene.paint.Color.RED
                        : playerModel[coord.x][coord.y] == 'X' ? javafx.scene.paint.Color.RED
                        : playerModel[coord.x][coord.y] == 'Z' ? javafx.scene.paint.Color.DARKRED
                        : playerModel[coord.x][coord.y] == 'd' ? javafx.scene.paint.Color.LIGHTGREEN
                        : playerModel[coord.x][coord.y] == 'e' ? javafx.scene.paint.Color.GREEN
                        : playerModel[coord.x][coord.y] == 'f' ? javafx.scene.paint.Color.DARKGREEN
                        : playerModel[coord.x][coord.y] == 'g' ? javafx.scene.paint.Color.LIGHTBLUE
                        : playerModel[coord.x][coord.y] == 'B' ? javafx.scene.paint.Color.BLUE
                        : playerModel[coord.x][coord.y] == 'i' ? javafx.scene.paint.Color.DARKBLUE
                        : playerModel[coord.x][coord.y] == 'G' ? javafx.scene.paint.Color.LIGHTGREY
                        : playerModel[coord.x][coord.y] == 'D' ? javafx.scene.paint.Color.DARKGREY
                        : playerModel[coord.x][coord.y] == 'l' ? javafx.scene.paint.Color.BLACK
                        : playerModel[coord.x][coord.y] == 'W' ? javafx.scene.paint.Color.WHITE
                        : javafx.scene.paint.Color.web("DDD1E7");
        return rectColor;

    }
    @Override
    public void start(Stage primaryStage) {
       /* Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });*/
        
      
        Timeline RightWalking= new Timeline(new KeyFrame(Duration.seconds(.1), new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (xcoord != 600)
                xcoord += 10;
             character.setLayoutX(xcoord);
               if (tick == 1){
                  
                   tick = 2;
                   try{
                       
                   loadFile(playerModel, 30, "R2.txt");
                   } catch(Exception e){}
               } else {
                   character.setTranslateX(+5);
                    try{
                        
                   loadFile(playerModel, 30, "R1.txt");
                   } catch(Exception e){}
                   tick = 1;
               }
                
            }
        }));
   
   Timeline LeftWalking= new Timeline(new KeyFrame(Duration.seconds(.1), new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            
            if (xcoord != 0)
                xcoord -= 10;
            character.setLayoutX(xcoord);
               if (tick == 1){
                   tick = 2;
                   
                    try{
                   loadFile(playerModel, 30, "L2.txt");
                   } catch(Exception e){}
               } else {
                    try{
                   loadFile(playerModel, 30, "L1.txt");
                   } catch(Exception e){}
                   tick = 1;
               }
                
            }
        }));
   
    Timeline eyeAnimation= new Timeline(new KeyFrame(Duration.seconds(.25), new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            
               if (tick == 1){
                   tick = 2;
                    try{
                   loadFile(playerModel, 30, "Default2.txt");
                   } catch(Exception e){}
               } else if (tick == 2){
                   tick = 3;
                    try{
                   loadFile(playerModel, 30, "Default3.txt");
                   } catch(Exception e){}
               } else {
                    try{
                   loadFile(playerModel, 30, "Default.txt");
                   } catch(Exception e){}
                   tick = 1;
               }
                
            }
        }));
   
    
        Timeline falling = new Timeline(new KeyFrame(Duration.seconds(.005), new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
               if (fallTick != 0){
                   fallTick -= 1;
                   character.setLayoutY(-fallTick+460);
               } 
        }
        }));
         
        GridPane stage = new GridPane();
        configureGridLayout(stage);
        ScrollPane stageScene = new ScrollPane(stage);
        stageScene.setMaxWidth(610);
        stageScene.setMaxHeight(500);
        stageScene.setFitToHeight(true);
        stageScene.setFitToWidth(true);
        
        for (int i =0; i < 30; i++){
             RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setMinHeight(2);
            rowConstraints.setPrefHeight(2);
            rowConstraints.setMaxHeight(2);
            rowConstraints.setValignment(VPos.CENTER);
            character.getRowConstraints().add(rowConstraints);

           // }
            ColumnConstraints colConstraints = new ColumnConstraints();
            colConstraints.setMinWidth(2);
            colConstraints.setMaxWidth(2);
            colConstraints.setPrefWidth(2);
            colConstraints.setHalignment(HPos.CENTER);
            character.getColumnConstraints().add(colConstraints);
        }
            character.setMaxWidth(60);
        character.setMaxHeight(60);
        character.setLayoutX(0);
        character.setLayoutY(360);
            
            try {
            loadFile(playerModel, 30, "Default.txt");
            } catch(Exception e ){
            System.out.println("FAIL");}
        
        Pane game = new Pane();
        game.setMaxWidth(600);
        game.setMaxHeight(450);
        
        
        game.getChildren().addAll(stageScene, character);
        
       
        //stageScene.set
      

        stage.setStyle("-fx-background-color: DDD1E7; -fx-grid-lines-visible: false");
        StackPane root = new StackPane();
        root.getChildren().add(game);
      
        
        Scene scene = new Scene(root, 800, 700);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
        
       eyeAnimation.setCycleCount(Timeline.INDEFINITE);
       eyeAnimation.play();
                    
         scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent key) {
                //tick = 0;
                if (key.getCode().equals(KeyCode.RIGHT)) {
                    eyeAnimation.stop();
                   
                    if (tick == 0)
                         try{
                   loadFile(playerModel, 30, "R1.txt");
                   } catch(Exception e){}
                    RightWalking.setCycleCount(Timeline.INDEFINITE);
            
                    RightWalking.play();
                    
                } else if (key.getCode().equals(KeyCode.LEFT)){
                    eyeAnimation.stop();
                   
                    if (tick == 0)
                     try{
                   loadFile(playerModel, 30, "L1.txt");
                   } catch(Exception e){}
                    LeftWalking.setCycleCount(Timeline.INDEFINITE);
                    LeftWalking.play();
                }else if (key.getCode().equals(KeyCode.UP)){
                    character.setTranslateY(-100);
                    fallTick = 70;
                    falling.setCycleCount(Timeline.INDEFINITE);
                    falling.play();
                }else;
 
            }
   });
         
         scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent key) {
               
                    tick = 0;
                    RightWalking.stop();
                    LeftWalking.stop();
                    eyeAnimation.setCycleCount(Timeline.INDEFINITE);
                    eyeAnimation.play();
                     try{
                   loadFile(playerModel, 30, "Default.txt");
                   } catch(Exception e){}
    
            }
   });
         
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
