package com.excel.search.util;

import com.alibaba.fastjson.JSON;
import com.excel.feign.pojo.TbJob;
import com.excel.util.EntityReflectionUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SearchUtil {
    public static List<String> handelResponseToStringList(SearchResponse response, String columnName) {
        SearchHits searchHits = response.getHits();
        // 查询到的文档数组
        SearchHit[] hits = searchHits.getHits();
        List<String> resList = new ArrayList<>();
        for (SearchHit hit : hits) {
            String json = hit.getSourceAsString();
            TbJob tbJob = JSON.parseObject(json, TbJob.class);
            String stringValue = Objects.requireNonNull(EntityReflectionUtils.getFieldValue(tbJob, columnName)).toString();
            resList.add(stringValue);
        }
        return getFirstToTenElements(resList.stream().distinct().collect(Collectors.toList()));
    }

    private static List<String> getFirstToTenElements(List<String> inputList) {
        if (inputList.size() <= 10) {
            return inputList;
        } else {
            return inputList.subList(0, 10);
        }
    }
}
