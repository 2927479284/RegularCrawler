package com.xydd.regularcrawler.controller;


import cn.hutool.core.util.StrUtil;
import com.xydd.regularcrawler.domain.ClassificationEntity;
import com.xydd.regularcrawler.service.GetClassificationService;
import com.xydd.regularcrawler.utils.AjaxResult;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

@RestController()
@RequestMapping("api")
public class GetClassificationController {

    @Value("${movies.url}")
    public String url;


    @Autowired
    private GetClassificationService getClassificationService;

    /**
     * 获取所有影视分类 地址 + 名称
     * @return
     * @throws IOException
     */
    @GetMapping("/classification")
    public AjaxResult getClassification() throws IOException {
        URL url1 = new URL(url + "/index.php/vod/type/id/1.html");
        return getClassificationService.getClassification(url1,5000);
    }



}
