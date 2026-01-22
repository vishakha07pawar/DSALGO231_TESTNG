package utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static String browserType = null;
    private static String applicationURL;
    static Properties prop = null;

    public static String getAppUrl() {
        return applicationURL;
    }

    private void setAppUrl(String appURL) {
        applicationURL = appURL;
    }

    public static String getBrowserType() {
        return browserType;
    }

    public static void setBrowserType(String browser) {
        if(browser != null && !browser.isBlank())
            browserType = browser;
    }

    public void loadProperties() {
        try {
            prop = new Properties();
            String configFileResourcePath = "/config/" + "config.properties";
            InputStream configResourceInputStream = getClass().getResourceAsStream(configFileResourcePath);
            prop.load(configResourceInputStream);
            setAppUrl(prop.getProperty("appURL"));

            if (getBrowserType() == null || getBrowserType().isEmpty()) {
                setBrowserType(prop.getProperty("browser"));
            }

            if (configResourceInputStream != null)
                configResourceInputStream.close();
        } catch (Exception e) {
            LoggerFactory.getLogger().error("Unexcepted error occurred when loading configuration. {}", e.getMessage());
        }
    }
}