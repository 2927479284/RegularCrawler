package com.xydd.regularcrawler;

import com.xydd.regularcrawler.controller.GetClassificationController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class RegularCrawlerApplicationTests {

    @Autowired
    private GetClassificationController getClassificationController;

    @Test
    void contextLoads() throws IOException {
        Object classification = getClassificationController.getClassification();

    }

}
