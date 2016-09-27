package JavaFXPlayGif;

import java.awt.image.BufferedImage;
import java.net.URISyntaxException;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AnimatedGifDemo extends Application {

    @Override
    public void start(Stage primaryStage) throws URISyntaxException {

        HBox root = new HBox();

        // TODO: provide gif file, ie exchange banana.gif with your file
       // Animation ani = new AnimatedGif("/img/banana.gif", 1000);
       // Animation ani = new AnimatedGif("/img/Explosion_XL.gif", 1000);
       // Animation ani = new AnimatedGif("/img/newExp.gif", 1000);
        Animation ani = new AnimatedGif("src/img/newExp2.gif", 1000);
        ani.setCycleCount(1);
        ani.play();

        Button btPause = new Button( "Pause");
        btPause.setOnAction( e -> ani.pause());

        Button btResume = new Button( "Resume");
        btResume.setOnAction( e -> ani.play());

        root.getChildren().addAll( ani.getView(), btPause, btResume);

        Scene scene = new Scene(root, 1600, 900);

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    public class AnimatedGif extends Animation {

        public AnimatedGif( String filename, double durationMs) {

            GifDecoder d = new GifDecoder();
            d.read( filename);

            Image[] sequence = new Image[ d.getFrameCount()];
            for( int i=0; i < d.getFrameCount(); i++) {

                WritableImage wimg = null;
                BufferedImage bimg = d.getFrame(i);
                sequence[i] = SwingFXUtils.toFXImage( bimg, wimg);

            }

            super.init( sequence, durationMs);
        }

    }

    public class Animation extends Transition {

        private ImageView imageView;
        private int count;

        private int lastIndex;

        private Image[] sequence;

        private Animation() {
        }

        public Animation( Image[] sequence, double durationMs) {
            init( sequence, durationMs);
        }

        private void init( Image[] sequence, double durationMs) {
            this.imageView = new ImageView(sequence[0]);
            this.sequence = sequence;
            this.count = sequence.length;

            setCycleCount(1);
            setCycleDuration(Duration.millis(durationMs));
            setInterpolator(Interpolator.LINEAR);

        }

        protected void interpolate(double k) {

            final int index = Math.min((int) Math.floor(k * count), count - 1);
            if (index != lastIndex) {
                imageView.setImage(sequence[index]);
                lastIndex = index;
            }

        }

        public ImageView getView() {
            return imageView;
        }

    }

}