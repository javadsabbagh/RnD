package com.apn.rnd.ui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBoundsType;

/**
 *
 * @author Sabbagh
 */
public class CropBox extends Pane {

    private State state;
    private double margin = 0.0;
    private double angle;

    // Handles
    private static final int handleSize = 6;
    private Paint handleColor = Color.RED;
    private Rectangle topLeft;
    private Rectangle top;
    private Rectangle topRight;
    private Rectangle left;
    private Rectangle right;
    private Rectangle bottomLeft;
    private Rectangle bottom;
    private Rectangle bottomRight;
    // Header
    private String titleStr;
    //private Text title;
    private Label title;
    private double headerHeight = 30;
    private Rectangle header;
    // Content
    private Rectangle content;

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
        // 1- Create Handles
        topLeft = new Rectangle(0, 0, handleSize, handleSize);
        top = new Rectangle(0, 0, handleSize, handleSize);
        topRight = new Rectangle(0, 0, handleSize, handleSize);
        left = new Rectangle(0, 0, handleSize, handleSize);
        right = new Rectangle(0, 0, handleSize, handleSize);
        bottomLeft = new Rectangle(0, 0, handleSize, handleSize);
        bottom = new Rectangle(0, 0, handleSize, handleSize);
        bottomRight = new Rectangle(0, 0, handleSize, handleSize);

        // 2- Set Handles Cursors
        topLeft.setCursor(Cursor.NW_RESIZE);
        top.setCursor(Cursor.N_RESIZE);
        topRight.setCursor(Cursor.NE_RESIZE);
        left.setCursor(Cursor.E_RESIZE);
        right.setCursor(Cursor.W_RESIZE);
        bottomLeft.setCursor(Cursor.SW_RESIZE);
        bottom.setCursor(Cursor.S_RESIZE);
        bottomRight.setCursor(Cursor.SE_RESIZE);

        // 3- Paint Handles
        topLeft.setFill(handleColor);
        top.setFill(handleColor);
        topRight.setFill(handleColor);
        left.setFill(handleColor);
        right.setFill(handleColor);
        bottomLeft.setFill(handleColor);
        bottom.setFill(handleColor);
        bottomRight.setFill(handleColor);

        // 4- Layout Handles in the Parent Pane
        // See parent pane width and height change listeners
        /**
         * ********************************************
         *
         * 5- Set Resizers Based on Handle Drag Events.
         *
         * *********************************************
         */
        topLeft.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double displaceWidth = event.getSceneX() - oldX;
                double displaceHeight = event.getSceneY() - oldY;

                double oldWidth = CropBox.this.getPrefWidth();
                double oldHeight = CropBox.this.getPrefHeight();
                CropBox.this.setPrefWidth(CropBox.this.getPrefWidth() - displaceWidth);
                CropBox.this.setPrefHeight(CropBox.this.getPrefHeight() - displaceHeight);
                // fix parent pane position
                CropBox.this.setLayoutX(CropBox.this.getLayoutX() - (CropBox.this.getPrefWidth() - oldWidth));
                CropBox.this.setLayoutY(CropBox.this.getLayoutY() - (CropBox.this.getPrefHeight() - oldHeight));

                oldX = event.getSceneX();
                oldY = event.getSceneY();
            }
        });

        top.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // FIXME checking size an policies must be done on its separate methods
                //  if (getScaleY() < 0.3) return;  
                double displace = event.getSceneY() - oldY;

                double oldHeight = CropBox.this.getPrefHeight();
                CropBox.this.setPrefHeight(CropBox.this.getPrefHeight() - displace);
                /**
                 * Note: The following code is buggy! Don't fix parent pane
                 * position based on the mouse new position:
                 * <p/>
                 * {@code CropBox.this.setLayoutY(event.getSceneY() - margin - handleSize / 2);}
                 */
                CropBox.this.setLayoutY(CropBox.this.getLayoutY() - (CropBox.this.getPrefHeight() - oldHeight));

                oldY = event.getSceneY();
            }
        });

        topRight.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double displaceWidth = event.getSceneX() - oldX;
                double displaceHeight = event.getSceneY() - oldY;

                double oldWidth = CropBox.this.getPrefWidth();
                double oldHeight = CropBox.this.getPrefHeight();
                CropBox.this.setPrefWidth(CropBox.this.getPrefWidth() + displaceWidth);
                CropBox.this.setPrefHeight(CropBox.this.getPrefHeight() - displaceHeight);
                // fix parent pane position
                CropBox.this.setLayoutY(CropBox.this.getLayoutY() - (CropBox.this.getPrefHeight() - oldHeight));

                oldX = event.getSceneX();
                oldY = event.getSceneY();
            }
        });

        left.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double displaceWidth = event.getSceneX() - oldX;

                double oldWidth = CropBox.this.getPrefWidth();
                CropBox.this.setPrefWidth(CropBox.this.getPrefWidth() - displaceWidth);
                // fix parent pane position
                CropBox.this.setLayoutX(CropBox.this.getLayoutX() - (CropBox.this.getPrefWidth() - oldWidth));

                oldX = event.getSceneX();
            }
        });

        right.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double displaceWidth = event.getSceneX() - oldX;

                double oldWidth = CropBox.this.getPrefWidth();
                CropBox.this.setPrefWidth(CropBox.this.getPrefWidth() + displaceWidth);
                // default behavior in JavaFX, no need to fix pane position

                oldX = event.getSceneX();
            }
        });

        bottomLeft.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double displaceWidth = event.getSceneX() - oldX;
                double displaceHeight = event.getSceneY() - oldY;

                double oldWidth = CropBox.this.getPrefWidth();
                double oldHeight = CropBox.this.getPrefHeight();
                CropBox.this.setPrefWidth(CropBox.this.getPrefWidth() - displaceWidth);
                CropBox.this.setPrefHeight(CropBox.this.getPrefHeight() + displaceHeight);
                // fix parent pane position
                CropBox.this.setLayoutX(CropBox.this.getLayoutX() - (CropBox.this.getPrefWidth() - oldWidth));

                oldX = event.getSceneX();
                oldY = event.getSceneY();
            }
        });

        bottom.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double displace = event.getSceneY() - oldY;

                double oldHeight = CropBox.this.getPrefHeight();
                // fix parent pane position
                CropBox.this.setPrefHeight(CropBox.this.getPrefHeight() + displace);

                oldY = event.getSceneY();
            }
        });

        bottomRight.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double displaceWidth = event.getSceneX() - oldX;
                double displaceHeight = event.getSceneY() - oldY;

                double oldWidth = CropBox.this.getPrefWidth();
                double oldHeight = CropBox.this.getPrefHeight();
                CropBox.this.setPrefWidth(CropBox.this.getPrefWidth() + displaceWidth);
                CropBox.this.setPrefHeight(CropBox.this.getPrefHeight() + displaceHeight);
                // no need to fix parent pane position

                oldX = event.getSceneX();
                oldY = event.getSceneY();
            }
        });

    }

    private void layoutHandles() {
        double parentWidth = this.widthProperty().doubleValue();
        double parentHeight = this.heightProperty().doubleValue();

        topLeft.setLayoutX(margin - handleSize / 2);
        topLeft.setLayoutY(margin + headerHeight - handleSize / 2);

        top.setLayoutX(parentWidth / 2 - handleSize / 2); // layout the handle at the center
        top.setLayoutY(margin + headerHeight - handleSize / 2);

        topRight.setLayoutX(parentWidth - margin - handleSize / 2);
        topRight.setLayoutY(margin + headerHeight - handleSize / 2);

        left.setLayoutX(margin - handleSize / 2);
        left.setLayoutY(margin + headerHeight + content.getHeight() / 2 - handleSize / 2);

        right.setLayoutX(parentWidth - margin - handleSize / 2);
        right.setLayoutY(margin + headerHeight + content.getHeight() / 2 - handleSize / 2);

        bottomLeft.setLayoutX(margin - handleSize / 2);
        bottomLeft.setLayoutY(parentHeight - margin - handleSize / 2);

        bottom.setLayoutX(parentWidth / 2 - handleSize / 2);
        bottom.setLayoutY(parentHeight - margin - handleSize / 2);

        bottomRight.setLayoutX(parentWidth - margin - handleSize / 2);
        bottomRight.setLayoutY(parentHeight - margin - handleSize / 2);
    }

    private void createHeader() {
        // draw header rectangle
        header = new Rectangle(margin, margin, CropBox.this.getWidth() - 2 * margin, headerHeight);
        header.setFill(Color.GREENYELLOW);
        header.setOpacity(0.5);

        this.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                header.setWidth(CropBox.this.getWidth() - 2 * margin);
            }
        });

        // draw box title
        // title = new Text(margin, margin, "یک متن نسبتا lkjcdafتصر برای عنوان");
        title = new Label("یک متن نسبتا lkjcdafتصر برای عنوان");
        title.setFont(Font.font("Tahoma", FontWeight.BOLD, 13));
        //title.setFill(Color.BLACK);
        title.setOpacity(0.8);

        title.setTextAlignment(TextAlignment.LEFT);
        //title.setBoundsType(TextBoundsType.VISUAL);
    }

    private void createContent() {
        content = new Rectangle(margin, margin + headerHeight, CropBox.this.getWidth() - 2 * margin, CropBox.this.getHeight() - 2 * margin - headerHeight);
        // content.setFill(Color.BLUE);
        //content.setOpacity(0.0); // fully translucent 
//        content.setOpacity(0.2);
        content.setFill(Color.WHITE);
        content.setStrokeWidth(2);
        content.setStrokeType(StrokeType.CENTERED);
        content.setStroke(Color.BLUE);

        content.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Mouse dragged ... " + " screenX: " + event.getScreenX() + " sceneX: " + event.getSceneX() + " X: " + event.getX());

//                if (oldX == 0 && oldY == 0) return;
                CropBox.this.setLayoutX(getLayoutX() + (event.getSceneX() - oldX));
                CropBox.this.setLayoutY(getLayoutY() + (event.getSceneY() - oldY));

                oldX = event.getSceneX();
                oldY = event.getSceneY();
            }
        });

    }

    private void createBox() {
        createHandles();
        createHeader();
        createContent();

        this.getChildren().addAll(content, header, title, topLeft, top, topRight, left, right, bottomLeft, bottom, bottomRight);

        //this.setOpacity(0.5);  // set opacity specifically on each child
        this.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("on mouse pressed");
                oldX = event.getSceneX();
                oldY = event.getSceneY();
            }
        });

        this.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                //System.out.println("pane old width:" + oldValue.intValue() + " , pane new value " + newValue.intValue());                
                content.setWidth(CropBox.this.getWidth() - 2 * margin);
                layoutHandles();
                title.setLayoutX(CropBox.this.getWidth() / 2 - title.getWidth()/ 2);
            }
        });

        this.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                //System.out.println("pane old width:" + oldValue.intValue() + " , pane new value " + newValue.intValue());                
                /**
                 * Content needs to be updated first, because some handle
                 * positions depend on it.
                 */
                content.setHeight(CropBox.this.getHeight() - 2 * margin - headerHeight);
                layoutHandles();
                title.setLayoutY(margin + headerHeight / 2 /*- title.getBaselineOffset()*/); // fixed position
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
        return titleStr;
    }

    public void setTitle(String title) {
        this.titleStr = title;
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
