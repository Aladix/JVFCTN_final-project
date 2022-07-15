package sk.best.newtify.backend.bootstrap.test;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit4.SpringRunner;
import sk.best.newtify.api.dto.ArticleDTO;
import sk.best.newtify.api.dto.CreateArticleDTO;
import sk.best.newtify.api.dto.ETopicType;
import sk.best.newtify.backend.controller.ArticlesController;


/**
 * @author Stanislav Blasko
 * Copyright © 2022 BEST Technická univerzita Košice.
 * All rights reserved.
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@SuppressWarnings("NewClassNamingConvention")
public class NewtifyDatabaseInitializer {

    private static final String DB_FILE_NAME = "newtify.mv.db";

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private ArticlesController articlesController;

    @Test
    public void initDatabase() {
        log.info("Initializing...");
        int maxAttempts = 10;
        StringBuilder upperPath = new StringBuilder("./");
        Resource dbFile = null;
        for (int i = 0; i < maxAttempts; i++) {
            dbFile = resourceLoader.getResource(DB_FILE_NAME + upperPath);
            if (!dbFile.exists()) {
                upperPath.append("../");
            }
        }
        Assertions.assertTrue(dbFile.exists());
        log.info("Done.");
    }


}
