package com.xydd.regularcrawler.service.impl;


import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.xydd.regularcrawler.domain.ClassificationEntity;
import com.xydd.regularcrawler.service.GetClassificationService;
import com.xydd.regularcrawler.utils.AjaxResult;
import com.xydd.regularcrawler.utils.RedisClient;
import com.xydd.regularcrawler.utils.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class GetClassificationServiceImpl implements GetClassificationService {



    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisClient redisClient;

    @Override
    public AjaxResult getClassification(URL url1, int i) throws IOException {
        List<ClassificationEntity> list = redisClient.getList("yingshi:fenlei", ClassificationEntity.class);
        if (list.size()>0){
            return AjaxResult.success(list);
        }
        Document parse = Jsoup.parse(url1, i);
        Elements select = parse.select(".width1200 ul>li>.dropdown>a");
        ArrayList<ClassificationEntity> classificationEntities = new ArrayList<>();
        for (Element element : select) {
            //通过属性名获取对应的属性值
            ClassificationEntity classificationEntity = new ClassificationEntity();
            classificationEntity.setClassificationUrl(element.attr("href"));
            classificationEntity.setClassificationName(element.attr("title"));
            if (StrUtil.isNotBlank(classificationEntity.getClassificationName())) {
                classificationEntities.add(classificationEntity);
            }
        }
        redisClient.set("yingshi:fenlei", JSONUtil.toJsonStr(classificationEntities),30L, TimeUnit.DAYS);
        return AjaxResult.success(classificationEntities);
    }
}
