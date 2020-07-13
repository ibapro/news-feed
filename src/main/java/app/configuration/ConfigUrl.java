package app.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigUrl {

    private static String url;

    public static String getUrl() {
        return url;
    }

    @Value("${news.api.url}")
    public void setUrl(String url) {
        ConfigUrl.url = url;
    }

}