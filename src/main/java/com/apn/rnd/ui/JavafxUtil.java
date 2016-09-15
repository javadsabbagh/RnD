package com.apn.rnd.ui;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author Sabbagh
 */
public class JavafxUtil {

    /**
     * This is how to calculate a text width in JavaFX.
     */
    public static double findTextWidth(String text, Font font) {
        Text temp = new Text(text);
        temp.setFont(font);
        double textWidth = temp.getLayoutBounds().getWidth();

        return textWidth;
    }
}
