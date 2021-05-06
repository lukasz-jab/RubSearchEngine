package appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    WebDriver wd;
    String browser;
    SessionHelper session;
    NavigationHelper navigation;
    Properties properties;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
        if (browser.equals("firefox")) {
            wd = new FirefoxDriver();
            //System.out.println(((HasCapabilities) wd).getCapabilities());
        } else if (browser.equals("chrome")) {
            wd = new ChromeDriver();
            //System.out.println(((HasCapabilities) wd).getCapabilities());
        } else {
            System.out.println("Unrecognized browser");
        }
        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wd.manage().window().maximize();
        session = new SessionHelper(wd);
        navigation = new NavigationHelper(wd);
    }

    public void stop() {
        wd.quit();
        wd = null;
    }

    public SessionHelper session() {
        return session;
    }

    public NavigationHelper navigation() {
        return navigation;
    }

}




