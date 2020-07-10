package app.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
public class ConfigMVC implements WebMvcConfigurer {

    private static final String[][] mappings = {
            { "/",                " "               },
            { "/registration",    "registration"    },
            { "/index",           "index"           },
            { "/disable-news",    "disable-news"    },
            { "/forgot-password", "forgot-password" },
            { "/main-page",       "main-page"       },
            { "/open-tab",        "open-tab"        },
    };

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        Arrays.stream(mappings).forEach(m -> registry.addViewController(m[0]).setViewName(m[1]));
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }

}
