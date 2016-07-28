package com.apn.rnd.thumbnail;

import java.io.File;
import java.io.IOException;
import net.coobird.thumbnailator.Thumbnails;

/**
 *
 * @author Sabbagh
 */
public class ThumbnailatorTest {

    public static void main(String[] args) throws IOException {
        long t = System.currentTimeMillis();
        Thumbnails.of(new File("C:\\Documents and Settings\\sabbagh.BEHINCO\\Desktop\\5.JPG"))
                .size(900, 700)
                .outputFormat("jpg")
                .toFile("C:\\Documents and Settings\\sabbagh.BEHINCO\\Desktop\\cropped.JPG");
        System.out.println("time took(ms) : "+ (System.currentTimeMillis() - t));
    }

}
