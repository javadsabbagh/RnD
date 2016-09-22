package com.apn.rnd.ui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 *
 * @author Javad Sabbagh
 */
public class CheckBoard extends Canvas{
    private double SQURE_SIE = 12;
    private double cols;
    private double rows;
    private Paint color1 = Color.gray(0.5); // more gray value makes it more pale
    private Paint color2 = Color.WHITE;

    public CheckBoard() {
        createBoard();
    }

    public CheckBoard(double width, double height) {
        super(width, height);
        createBoard();
    }

    public Paint getColor1() {
        return color1;
    }

    public void setColor1(Paint color1) {
        this.color1 = color1;
        repaint();
    }

    public Paint getColor2() {
        return color2;
    }

    public void setColor2(Paint color2) {
        this.color2 = color2;
        repaint();
    }

    public double getSquareSize() {
        return SQURE_SIE;
    }

    public void setSquareSize(double squareSize) {
        this.SQURE_SIE = squareSize;
        repaint();
    }           
    
           
   private void createBoard() {
        this.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                repaint();
            }
        });

        this.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                repaint();
            }
        });
    }
    
    private void repaint() {
        cols = Math.ceil(this.getWidth() / SQURE_SIE);
        rows = Math.ceil(this.getHeight() / SQURE_SIE);

        GraphicsContext gc = this.getGraphicsContext2D();
        Paint tempColor;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {                
                tempColor = (i+j)%2 == 0 ? color1 : color2;
                gc.setFill(tempColor);
                gc.fillRect(j * SQURE_SIE, i* SQURE_SIE, SQURE_SIE, SQURE_SIE);
            }
        }
    }
     
    
}
