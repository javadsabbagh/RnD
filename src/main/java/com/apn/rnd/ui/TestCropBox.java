package com.apn.rnd.ui;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Sabbagh
 */
public class TestCropBox extends Application {

    private Pane root;

    @Override
    public void start(Stage primaryStage) {
        createComponents();

        Scene scene = new Scene(root);

        primaryStage.setTitle("Testing cop box");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void createComponents() {
//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction((ActionEvent event) -> {
//            System.out.println("Hello World!");
//        });

        CropBox box1 = new CropBox();
        box1.setLayoutX(270);
        box1.setLayoutY(40);
        box1.setPrefWidth(200);
        box1.setPrefHeight(200);

        CropBox box2 = new CropBox();
        box2.setLayoutX(40);
        box2.setLayoutY(270);
        box2.setPrefWidth(200);
        box2.setPrefHeight(200);

        CropBox box3 = new CropBox();
        box3.setLayoutX(270);
        box3.setLayoutY(270);
        box3.setPrefWidth(200);
        box3.setPrefHeight(200);

        CropBox box4 = new CropBox();
        box4.setLayoutX(33);
        box4.setLayoutY(28);
        box4.setPrefWidth(200);
        box4.setPrefHeight(200);

        ImageView imageView = new ImageView("file:///C:/Documents%20and%20Settings/sabbagh.BEHINCO/Desktop/Doc/005.bmp");
        imageView.setLayoutX(0);
        imageView.setLayoutY(0);
        imageView.setFitWidth(650);
        imageView.setFitHeight(650);
                
        root = new Pane();
        root.setPrefWidth(650);
        root.setPrefHeight(650);

        root.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                imageView.setFitWidth(newValue.doubleValue());
            }
        });
        
        root.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                imageView.setFitHeight(newValue.doubleValue());
            }
        });
        
        root.getChildren().addAll(imageView, box1, box2, box3, box4);
    }

}
