package com.company.cofiguration;

import com.company.Boot;
import com.company.servise.SiteService;
import com.company.servise.SiteServiceImplJson;
import com.company.servise.SiteServiceImplSql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * Created by Onlin on 03.11.2016.
 */

@Configuration
public class AppConfig {

    public static String fileRootName;

    @Autowired
    Environment environment;


    @Bean
    public SiteService siteService() {
        fileRootName = environment.getProperty("db.json.rootName", "User.json");
        if (environment.getRequiredProperty("db.pattern").equalsIgnoreCase("json")) {

            System.out.println(environment.getRequiredProperty("db.pattern"));
            return new SiteServiceImplJson();
        } else {
            System.out.println(environment.getRequiredProperty("db.pattern"));
            return new SiteServiceImplSql();
        }

    }

}
