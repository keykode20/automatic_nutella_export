package com.example.automation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class TakeScreenshot implements Runnable {

    private String filename;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public void run() {
        System.out.println("starting take Screenshot");
        BufferedImage image = null;
        try {
            Thread.sleep(1000);
            File temp = File.createTempFile("asdqwe", ".png");
            File file = new File("c:\\asdqwe\\"+getFilename()+".png");
            image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            ImageIO.write(image, "png", file);

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("ending take Screenshot");

    }
}
