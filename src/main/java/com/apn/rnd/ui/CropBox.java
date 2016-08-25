package com.apn.rnd.ui;

import java.awt.Rectangle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Cursor;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 *
 * @author Sabbagh
 */
public class CropBox extends AnchorPane {

    private State state;
    private double margin;
    private double angle;
    private String title;

    public CropBox() {
        createBox();
    }

    public CropBox(Rectangle rect, String title) {
    }

    public CropBox(Rectangle rect, double angle, String title) {
    }

    public CropBox(double x, double y, double width, double height, double angle, String title) {
    }

    private void createBox() {
        Paint fillColor = Color.LIGHTBLUE;
        double width = 10;
        double height = 10;
        double margin = 10.0;

        javafx.scene.shape.Rectangle topLeft = new javafx.scene.shape.Rectangle(10, 10, width, height);
        javafx.scene.shape.Rectangle top = new javafx.scene.shape.Rectangle(10, 10, width, height);
        javafx.scene.shape.Rectangle topRight = new javafx.scene.shape.Rectangle(10, 10, width, height);
        javafx.scene.shape.Rectangle left = new javafx.scene.shape.Rectangle(10, 10, width, height);
        javafx.scene.shape.Rectangle right = new javafx.scene.shape.Rectangle(10, 10, width, height);
        javafx.scene.shape.Rectangle bottomLeft = new javafx.scene.shape.Rectangle(10, 10, width, height);
        javafx.scene.shape.Rectangle bottom = new javafx.scene.shape.Rectangle(10, 10, width, height);
        javafx.scene.shape.Rectangle bottomRight = new javafx.scene.shape.Rectangle(10, 10, width, height);

        topLeft.setCursor(Cursor.NW_RESIZE);
        top.setCursor(Cursor.N_RESIZE);
        topRight.setCursor(Cursor.NE_RESIZE);

        double degree = 100.0;
        double radian = Math.toRadians(degree);
        topLeft.setRotate(radian);

        topLeft.setFill(fillColor);
        top.setFill(fillColor);
        topRight.setFill(fillColor);
        left.setFill(fillColor);
        right.setFill(fillColor);
        bottomLeft.setFill(fillColor);
        bottom.setFill(fillColor);
        bottomRight.setFill(fillColor);

//        AnchorPane root = new AnchorPane();
        this.getChildren().addAll(topLeft, top, topRight, left, right, bottomLeft, bottom, bottomRight);

        AnchorPane.setTopAnchor(topLeft, margin);
        AnchorPane.setTopAnchor(top, margin);
        AnchorPane.setTopAnchor(topRight, margin);

        AnchorPane.setBottomAnchor(bottomLeft, margin);
        AnchorPane.setBottomAnchor(bottom, margin);
        AnchorPane.setBottomAnchor(bottomRight, margin);

        AnchorPane.setLeftAnchor(topLeft, margin);
        AnchorPane.setLeftAnchor(left, margin);
        AnchorPane.setLeftAnchor(bottomLeft, margin);

        AnchorPane.setRightAnchor(topRight, margin);
        AnchorPane.setRightAnchor(right, margin);
        AnchorPane.setRightAnchor(bottomRight, margin);

        this.setOpacity(10);

        this.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println("old value:" + oldValue.intValue() + " new value " + newValue.intValue());

                // FIXME you need to consider margins, and shape size
                AnchorPane.setLeftAnchor(top, (CropBox.this.widthProperty().doubleValue() - 2 * margin - 2 * width) / 2.0);
                AnchorPane.setTopAnchor(left, CropBox.this.getHeight() / 2);
                AnchorPane.setTopAnchor(right, CropBox.this.getHeight() / 2);
                AnchorPane.setLeftAnchor(bottom, CropBox.this.widthProperty().doubleValue() / 2.0);
            }
        });

        this.setStyle("-fx-padding: 10;"
                + "-fx-border-style: solid inside;"
                + "-fx-border-width: 2;"
                + "-fx-border-insets: 5;"
                + "-fx-border-radius: 5;"
                + "-fx-border-color: blue;");

        this.setOpacity(0.5);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public double getMargin() {
        return margin;
    }

    public void setMargin(double margin) {
        this.margin = margin;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * ************************************************
     *
     *                  State of a CropBox
     *
     * ************************************************
     */
    public enum State {
        ROTATE, SEELECTED
    }
}
