package com.example.automation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@SpringBootApplication
public class AutomationApplication {
	static {

		System.setProperty("java.awt.headless", "false");
	}
	public static void main(String[] args) {
		SpringApplication.run(AutomationApplication.class, args);
	}
	@EventListener({ApplicationReadyEvent.class})
	void applicationReadyEvent() {
		System.out.println("Application started ... launching browser now");
		browse("www.google.com");
	}

	public static void browse(String url) {
		if(Desktop.isDesktopSupported()){
			System.out.println("desktop is supported");
			Desktop desktop = Desktop.getDesktop();
            try {
				desktop.browse(new URI(url));
                Thread.sleep(10000);
                screenshot();
            } catch (IOException | URISyntaxException e) {
				e.printStackTrace();
			} catch (AWTException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
			System.out.println("desktop not supported");
			Runtime runtime = Runtime.getRuntime();
			try {
				runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
                Thread.sleep(10000);
                screenshot();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (AWTException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
	}

	public static void screenshot() throws AWTException, IOException, InterruptedException {
        BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        File temp = File.createTempFile("asdqwe", ".png");
        File file = new File("c:\\asdqwe\\testFile1.png");
        if (file.createNewFile())
        {
            System.out.println("File is created!");
        } else {
            System.out.println("File already exists.");
        }
        ImageIO.write(image, "png", file);
    }

}
