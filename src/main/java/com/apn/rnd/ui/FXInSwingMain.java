package com.apn.rnd.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author Sabbagh
 */
public class FXInSwingMain extends JApplet {

    private static final int JFXPANEL_WIDTH_INT = 300;
    private static final int JFXPANEL_HEIGHT_INT = 250;
    private static JFXPanel fxContainer;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /**
         * Swing Threads in EDT
         */
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            } catch (Exception e) {
            }

            JFrame frame = new JFrame("JavaFX 2 in Swing");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JApplet applet = new FXInSwingMain();
            applet.init();

            frame.setContentPane(applet.getContentPane());

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            applet.start();
        });
    }

    @Override
    public void init() {
        fxContainer = new JFXPanel();
        fxContainer.setPreferredSize(new Dimension(JFXPANEL_WIDTH_INT, JFXPANEL_HEIGHT_INT));
        add(fxContainer, BorderLayout.CENTER);

        /**
         * Create JavaFX controls in JavaFX Platform Thread.
         */
        Platform.runLater(this::createScene);
    }

    private void createScene() {
        Paint fillColor = Color.LIGHTBLUE;
        double width = 10;
        double height = 10;
        double margin = 10.0;

        Rectangle topLeft = new Rectangle(10, 10, width, height);
        Rectangle top = new Rectangle(10, 10, width, height);
        Rectangle topRight = new Rectangle(10, 10, width, height);
        Rectangle left = new Rectangle(10, 10, width, height);
        Rectangle right = new Rectangle(10, 10, width, height);
        Rectangle bottomLeft = new Rectangle(10, 10, width, height);
        Rectangle bottom = new Rectangle(10, 10, width, height);
        Rectangle bottomRight = new Rectangle(10, 10, width, height);

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

        AnchorPane root = new AnchorPane();
        root.getChildren().addAll(topLeft, top, topRight, left, right, bottomLeft, bottom, bottomRight);

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

        root.setOpacity(10);

        root.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println("old value:" + oldValue.intValue() + " new value " + newValue.intValue());

                // FIXME you need to consider margins, and shape size
                AnchorPane.setLeftAnchor(top, (root.widthProperty().doubleValue() - 2 * margin - 2 * width) / 2.0);
                AnchorPane.setTopAnchor(left, root.getHeight() / 2);
                AnchorPane.setTopAnchor(right, root.getHeight() / 2);
                AnchorPane.setLeftAnchor(bottom, root.widthProperty().doubleValue() / 2.0);
            }
        });

        root.setStyle("-fx-padding: 10;"
                + "-fx-border-style: solid inside;"
                + "-fx-border-width: 2;"
                + "-fx-border-insets: 5;"
                + "-fx-border-radius: 5;"
                + "-fx-border-color: blue;");
        
        root.setOpacity(0.5);
        
//        root.setRotate(45);
//        root.setLayoutX(radian);
//        Pane pane = new Pane();
//        
//        pane.getChildren().add(root);

        fxContainer.setScene(new Scene(root));
    }

}
