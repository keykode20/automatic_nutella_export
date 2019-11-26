package com.example.automation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Apptest {
    public static void main(String[] args) throws InterruptedException, IOException {

        List<String> strings = Files.readAllLines(Paths.get("C:\\automation\\src\\main\\resources\\lines1.txt"));

        for (int i = 0; i < strings.size(); i++) {

            TakeScreenshot takeScreenshot = new TakeScreenshot();
            takeScreenshot.setFilename(strings.get(i).replaceAll("[^a-zA-Z0-9]", ""));
            Thread threadTakeScreenShot = new Thread(takeScreenshot);

            OpenBrowser openBrowser = new OpenBrowser();
            openBrowser.setUrl(strings.get(i));
            Thread threadOpenBrowser = new Thread(openBrowser);

            threadOpenBrowser.start();
            threadOpenBrowser.join();

            threadTakeScreenShot.start();
            threadTakeScreenShot.join();
            Thread.sleep(10000);
        }
    }
}
