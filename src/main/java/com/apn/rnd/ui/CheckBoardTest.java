package com.apn.rnd.ui;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Javad Sabbagh
 */
public class CheckBoardTest extends Application {
       
    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox();
        CheckBoard board = new CheckBoard();  // create all child controls
        root.getChildren().add(board);        

        /**
         * Canvas control does not automatically resize with its container, why?
         * So we need to resize it manually.
         */
        root.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                board.setWidth(root.getWidth());
            }
        });
        
        root.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                board.setHeight(root.getHeight());
            }
        });
        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("CheckBoard Control Test");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
