import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*; // Button, Label
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;
import static javafx.scene.layout.BackgroundPosition.CENTER;
import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;
import static javafx.scene.layout.BackgroundRepeat.REPEAT;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.text.*;
import javafx.event.*;
import javafx.scene.image.Image;

public class testGame extends Application {
    Stage window;
    Scene scene1, scene2, scene3;
    static List<String> nameList = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        primaryStage.setTitle("Card Game");
        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0f);
        ds.setColor(Color.color(0.4f, 0.4f, 0.4f));

        Text title = new Text(100, 200, "\n Welcome to the Game ");
        title.setTextAlignment(TextAlignment.CENTER);
        title.setEffect(ds);
        title.setCache(true);
        title.setX(10.0f);
        title.setY(270.0f);
        title.setFill(Color.WHITE);
        title.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 48));
        Button btn1 = new Button();
        btn1.setText("START");
        btn1.setStyle("-fx-font-size: 1.4em; -fx-border-color: #778899; -fx-border-width: 3px;");
        btn1.setOnAction(e -> window.setScene(scene2));
        Button btn2 = new Button();
        btn2.setText("EXIT GAME");
        btn2.setStyle("-fx-font-size: 1.4em; -fx-border-color: #778899; -fx-border-width: 3px;");
        btn2.setOnAction(e -> window.close());
        Image image1 = new Image("image/background.jpg", 800, 0, true, true);
        GridPane gridPane1 = new GridPane();
        gridPane1.setHgap(10);
        gridPane1.setVgap(10);
        gridPane1.add(title, 6, 0);
        gridPane1.setAlignment(Pos.TOP_CENTER);
        gridPane1.add(btn1, 15, 33);
        gridPane1.add(btn2, 0, 33);
        gridPane1.setBackground(new Background(new BackgroundImage(image1, REPEAT, NO_REPEAT, CENTER,
                new BackgroundSize(1.0, 1.0, true, true, false, false))));
        scene1 = new Scene(gridPane1, 1000, 500);
        Game game = new Game();
        GridPane root = new GridPane();
        Image image2 = new Image("image/bac.jpg", 800, 0, true, true);
        root.setBackground(new Background(new BackgroundImage(image2, REPEAT, NO_REPEAT, CENTER,
                new BackgroundSize(1.0, 1.0, true, true, false, false))));
        root.getChildren().add(createAddPlayerPane(game));
        scene2 = new Scene(root, 1000, 500);

        primaryStage.setScene(scene1);
        primaryStage.show();

    }

    private GridPane createAddPlayerPane(Game game) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 5, 5));
        grid.setHgap(10);
        grid.setVgap(10);
        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0f);
        ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
        Text title = new Text(10, 20, " \n3-Player Phase\n ");
        title.setEffect(ds);
        title.setCache(true);
        title.setX(10.0f);
        title.setY(270.0f);
        title.setFill(Color.WHITESMOKE);
        title.setStroke(Color.BLACK);
        Effect glow = new Glow(1.0);
        title.setEffect(glow);
        title.setFont(Font.font("Arial", FontWeight.BOLD, 45));
        grid.add(title, 23, 0);
        Label label = new Label("Player 1");
        label.setTextFill(Color.WHITE);
        label.setFont(new Font("Verdana", 20));
        grid.add(label, 20, 1);
        TextField player1 = new TextField();
        player1.setPromptText("Enter player 1's name");
        grid.add(player1, 23, 1);
        Label label2 = new Label("Player 2");
        label2.setTextFill(Color.WHITE);
        label2.setFont(new Font("Verdana", 20));
        grid.add(label2, 20, 6);
        TextField player2 = new TextField();
        player2.setPromptText("Enter player 2's name");
        grid.add(player2, 23, 6);
        Label label3 = new Label("Player 3");
        label3.setTextFill(Color.WHITE);
        label3.setFont(new Font("Verdana", 20));
        grid.add(label3, 20, 11);
        TextField player3 = new TextField();
        player3.setPromptText("Enter player 3's name");
        grid.add(player3, 23, 11);

        Button submit = new Button("Submit");
        submit.setStyle("-fx-font-size: 1.2em; -fx-border-color: #90EE90; -fx-border-width: 2px;");
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (player1.getText().trim().isEmpty() || player2.getText().trim().isEmpty()
                        || player3.getText().trim().isEmpty()) {
                    Alert a = new Alert(AlertType.NONE);
                    a.setAlertType(AlertType.ERROR);
                    a.setContentText("Please fill up all players' name");
                    a.show();
                } else {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setHeaderText("Success");
                    alert.setContentText("Player's name succesfully added!");
                    alert.showAndWait();
                    nameList.add(player1.getText());
                    nameList.add(player2.getText());
                    nameList.add(player3.getText());
                    window.close();

                }
            }
        });
       
        grid.add(submit, 23, 16);
        return grid;
    }

    public static void main(String[] args) {
        launch(args);
        Scanner input = new Scanner(System.in);
        Game game = new Game();
        do {
            for(int i = 0; i < 3; i++){
                game.addPlayer(nameList.get(i));
            }
            game.distributeCardsToPlayers();
            System.out.println();
            game.availableCardsList();
            System.out.println();
            System.out.println ("Press S to Shuffle or ENTER to start.");
            String option = input.nextLine();
            if (option.equals(""))
                System.out.println("<Enter is pressed>");
            while (!option.equals("") && !option.equals("S")) {
                System.out.println ("Invalid entry!");
                System.out.println ("Press S to Shuffle or ENTER to start.");
                option = input.nextLine();
            }
            int round = 1;
            while (option.equals("S")) {
                game.distributeCardsToPlayers();
                game.availableCardsList();
                System.out.println();
                System.out.println ("Press S to Shuffle or ENTER to start.");
                option = input.nextLine();
                if (option.equals(""))
                    System.out.println("<Enter is pressed>");
            }
            
            while (round <= 3) {
                System.out.println();
                System.out.println ("*** ROUND " + round + " ***");
                System.out.println ("Cards at Hand: ");
                game.sortedCardsAtHand_getWinner();
                System.out.println();
                game.displayScores();
                System.out.println();
                game.availableCardsList();
                System.out.println();
                if (round != 3)
                    System.out.println ("Press ENTER to next round.");
                else {
                    game.winnerOf3PlPhase();
                    System.out.println ("Press ENTER to proceed to 2-Player Phase");
                }
                input.nextLine();
                round += 1;
            }
            System.out.println("******************");
            System.out.println("* 2-Player Phase *");
            System.out.println("******************");
            game.distributeCardsToPlayers();
            System.out.println();
            game.availableCardsList();
            System.out.println ("Press S to Shuffle or ENTER to start.");
            option = input.nextLine();
            if (option.equals(""))
                System.out.println("<Enter is pressed>");
            while (!option.equals("") && !option.equals("S")) {
                System.out.println ("Invalid entry!");
                System.out.println ("Press S to Shuffle or ENTER to start.");
                option = input.nextLine();
            }
            while (option.equals("S") ) {
                game.distributeCardsToPlayers();
                game.availableCardsList();
                System.out.println ("Press S to Shuffle or ENTER to start.");
                option = input.nextLine();
                if (option.equals(""))
                    System.out.println("<Enter is pressed>");
            }
            round = 1;
            while (round <= 4) {
                System.out.println ("*** ROUND " + round + " ***");
                System.out.println ("Cards at Hand: ");
                System.out.println();
                game.sortedCardsAtHand_getWinner();
                System.out.println();
                game.displayScores();
                System.out.println();
                game.availableCardsList();
                System.out.println();
                if (round != 4)
                    System.out.println ("Press ENTER to next round.");
                else {
                    game.winnerOf2PlPhase();
                    input.nextLine();
                    System.exit(0);
                }
                input.nextLine();
                System.out.println();
                round += 1;
            }
        } while (true);
    }

   
}