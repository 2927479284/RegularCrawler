package com.xydd.regularcrawler.service;

import com.xydd.regularcrawler.utils.AjaxResult;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;


public interface GetClassificationService {

    /**
     *
     * @param url1
     * @param i
     * @return
     */
    AjaxResult getClassification(URL url1, int i) throws IOException;
}
