package JavaFXPlayGif;

import java.net.URISyntaxException;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AnimatedGifDemo extends Application 
{

    @Override
    public void start(Stage primaryStage) throws URISyntaxException 
    {

        HBox root = new HBox();

        // TODO: provide gif file, ie exchange banana.gif with your file
        //Animation ani = new AnimatedGif("src/img/banana.gif", 1000);
       // Animation ani = new AnimatedGif("src/img/Explosion_XL.gif", 1000);
       // Animation ani = new AnimatedGif("src/img/newExp2.gif", 1000);
        Animation ani = new AnimatedGif("src/img/newExp.gif", 1000);
        ani.setCycleCount(1);
        ani.play();

        Button btPause = new Button( "Pause");
        btPause.setOnAction( e -> ani.pause());

        Button btResume = new Button( "Resume");
        btResume.setOnAction( e -> ani.play());

        root.getChildren().addAll( ani.getView(), btPause, btResume);

        Scene scene = new Scene(root, 800, 500);

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}