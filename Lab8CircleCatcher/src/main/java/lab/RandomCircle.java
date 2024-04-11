package lab;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.Random;


public class RandomCircle extends Circle {
    private boolean captured;
    private int radius;
    private Color color;
    private TranslateTransition animation;


    public RandomCircle(Pane p){
        super();
        this.setRadius();
        this.setColor();
        this.setCaptured(false);
        super.setRadius(this.radius);
        super.setFill(this.getColor());
        animation = new TranslateTransition();
//        this.setOnMouseClicked(event -> {
//            TranslateTransition returnToCenter = new TranslateTransition(new Duration(500), this);
//            returnToCenter.setToX(p.getWidth()/2);
//            returnToCenter.setToY(p.getHeight()/2);
//            returnToCenter.play();
//            this.setCaptured(true);
//        });

        wakeCircle(p);
    }

    private void wakeCircle(Pane p) {
        animation.setDuration(Duration.millis(new Random().nextInt(1000)+ 500));
        animation.setNode(this);
        animation.setFromX(new Random().nextDouble() * (p.getWidth() - (2* getRadius())) + 25);
        animation.setToX((new Random().nextDouble() * (p.getWidth() - (2* getRadius()))) + getRadius());
        animation.setFromY((new Random().nextDouble() * (p.getHeight() - (2* getRadius()))) + getRadius() + 25);
        animation.setToY((new Random().nextDouble() * (p.getHeight() - (2* getRadius()))) + getRadius() + 25);
        animation.setCycleCount(Animation.INDEFINITE);
        animation.setAutoReverse(true);
        animation.play();
    }

    public void setRadius() {
        Random random = new Random();
        int rad = 0;
        do {
            rad = random.nextInt(50);
        } while(rad < 25);
        this.radius = rad;
    }

    public Color getColor() {
        return color;
    }

    public void setColor() {
        Random random = new Random();
        this.color = Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble());
    }

    public boolean isCaptured() {
        return captured;
    }

    public void setCaptured(boolean captured) {
        this.captured = captured;
        if (this.isCaptured()){
            animation.stop();
        }
    }
}
