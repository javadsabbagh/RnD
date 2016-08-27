package com.apn.rnd.ui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Sabbagh
 */
public class CropBox extends AnchorPane {

    private static final int handleSize = 10;
    private Paint handleColor = Color.RED;
    private State state;
    private double margin = 20.0;
    private double angle;
    private String title;

    public CropBox() {
        createBox();
    }

    public CropBox(Rectangle2D rect, String title) {
    }

    public CropBox(Rectangle2D rect, double angle, String title) {
    }

    public CropBox(double x, double y, double width, double height, double angle, String title) {
    }

    private void createHandles() {
        Rectangle topLeft = new Rectangle(10, 10, handleSize, handleSize);
        Rectangle top = new Rectangle(10, 10, handleSize, handleSize);
        Rectangle topRight = new Rectangle(10, 10, handleSize, handleSize);
        Rectangle left = new Rectangle(10, 10, handleSize, handleSize);
        Rectangle right = new Rectangle(10, 10, handleSize, handleSize);
        Rectangle bottomLeft = new Rectangle(10, 10, handleSize, handleSize);
        Rectangle bottom = new Rectangle(10, 10, handleSize, handleSize);
        Rectangle bottomRight = new Rectangle(10, 10, handleSize, handleSize);

        topLeft.setCursor(Cursor.NW_RESIZE);
        top.setCursor(Cursor.N_RESIZE);
        topRight.setCursor(Cursor.NE_RESIZE);
        left.setCursor(Cursor.E_RESIZE);
        right.setCursor(Cursor.W_RESIZE);
        bottomLeft.setCursor(Cursor.SW_RESIZE);
        bottom.setCursor(Cursor.S_RESIZE);
        bottomRight.setCursor(Cursor.SE_RESIZE);

        topLeft.setFill(handleColor);
        top.setFill(handleColor);
        topRight.setFill(handleColor);
        left.setFill(handleColor);
        right.setFill(handleColor);
        bottomLeft.setFill(handleColor);
        bottom.setFill(handleColor);
        bottomRight.setFill(handleColor);

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

        this.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println("old value:" + oldValue.intValue() + " new value " + newValue.intValue());

                // FIXME you need to consider margins, and shape size
                AnchorPane.setLeftAnchor(top, (CropBox.this.widthProperty().doubleValue() - 2 * margin - 2 * handleSize) / 2.0);
                AnchorPane.setTopAnchor(left, CropBox.this.getHeight() / 2);
                AnchorPane.setTopAnchor(right, CropBox.this.getHeight() / 2);
                AnchorPane.setLeftAnchor(bottom, CropBox.this.widthProperty().doubleValue() / 2.0);
            }
        });

        this.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println("old value:" + oldValue.intValue() + " new value " + newValue.intValue());

                // FIXME you need to consider margins, and shape size
                AnchorPane.setLeftAnchor(top, (CropBox.this.widthProperty().doubleValue() - 2 * margin - 2 * handleSize) / 2.0);
                AnchorPane.setTopAnchor(left, CropBox.this.getHeight() / 2);
                AnchorPane.setTopAnchor(right, CropBox.this.getHeight() / 2);
                AnchorPane.setLeftAnchor(bottom, CropBox.this.widthProperty().doubleValue() / 2.0);
            }
        });
        top.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // FIXME checking size an policies must be done on its separate methods
                //  if (getScaleY() < 0.3) return;  
                double yy = event.getSceneY() - oldY;

                double oldH = CropBox.this.getPrefHeight();
                CropBox.this.setPrefHeight(CropBox.this.getPrefHeight() - yy);
                /**
                 * Note: code commented below has a bug! Don't use displace fix
                 * in this way.
                 */
                // CropBox.this.setLayoutY(event.getSceneY() - margin - handleSize / 2); 
                CropBox.this.setLayoutY(CropBox.this.getLayoutY() - (CropBox.this.getPrefHeight() - oldH));

                oldY = event.getSceneY();
            }
        });

        // Add all components to the root node (CropBox)
        this.getChildren().addAll(topLeft, top, topRight, left, right, bottomLeft, bottom, bottomRight);
    }

    private void createHeader() {
        // draw header rectangle

        // draw box title
    }

    private void createContent() {

    }

    private void createBox() {
        createHandles();

        this.setOpacity(0.5);

//        this.setOnMouseDragged(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                System.out.println("Mouse dragged ... " + " screenX: " + event.getScreenX() + " sceneX: " + event.getSceneX() + " X: " + event.getX());
//
////                if (oldX == 0 && oldY == 0) return;
//                CropBox.this.setLayoutX(getLayoutX() + (event.getSceneX() - oldX));
//                CropBox.this.setLayoutY(getLayoutY() + (event.getSceneY() - oldY));
//
//                oldX = event.getSceneX();
//                oldY = event.getSceneY();
//            }
//        });
        this.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("on mouse pressed");
                oldX = event.getSceneX();
                oldY = event.getSceneY();
            }
        });
    }

    private double oldX;
    private double oldY;

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
     * State of a CropBox
     *
     * ************************************************
     */
    public enum State {
        ROTATE, SEELECTED
    }
}
