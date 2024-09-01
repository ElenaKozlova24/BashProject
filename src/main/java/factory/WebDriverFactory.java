package factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverFactory {
    public static WebDriver createNewDriver(String webDriverName, String browserVersion, String seleniumHubUrl, Object... options) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        switch (webDriverName.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if (options.length > 0 && options[0] instanceof ChromeOptions) {
                    chromeOptions = (ChromeOptions) options[0];
                }
                chromeOptions.setCapability("browserVersion", browserVersion);
                capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (options.length > 0 && options[0] instanceof FirefoxOptions) {
                    firefoxOptions = (FirefoxOptions) options[0];
                }
                firefoxOptions.setCapability("browserVersion", browserVersion);
                capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
                break;
            default:
                throw new RuntimeException("Unsupported browser: " + webDriverName);
        }

        return new RemoteWebDriver(new URL(seleniumHubUrl), capabilities);
    }
}
