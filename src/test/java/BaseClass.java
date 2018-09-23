import org.apache.commons.exec.util.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.openqa.selenium.remote.BrowserType.IE;

public class BaseClass {

    WebDriver driver;
    Properties prop = new Properties();


    @Before
    public void before() {
        loadProperties();
        String browser = getProperties("browser");
        if (browser.equalsIgnoreCase("Firefox")) {
            System.setProperty("webdriver.gecko.driver", getClass().getClassLoader().getResource("geckodriver").getPath());
            driver = new FirefoxDriver();
        } else {
            System.setProperty("webdriver.chrome.driver", getClass().getClassLoader().getResource("chromedriver").getPath());
            driver = new ChromeDriver();
        }
    }

    private void loadProperties() {


        InputStream input = null;

        try {

            input = getClass().getClassLoader().getResourceAsStream("config.properties");

            // load a properties file
            prop.load(input);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public String getProperties(String value) {
        return prop.getProperty(value);
    }


    @After
    public void after() {
        driver.close();
    }
}
