package com.excel.search.service;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static com.excel.search.util.SearchUtil.handelResponseToStringList;

@Service
public class JobSearchService {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    public List<String> searchColumnValueList_back(String columnName, String inputValue) throws IOException {
        // request
        SearchRequest request = new SearchRequest("job");
        // DSL
        request.source().query(QueryBuilders.matchPhrasePrefixQuery(columnName, inputValue)
                .slop(1).maxExpansions(15)).size(30);
        // search
        SearchResponse searchResponse = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        return handelResponseToStringList(searchResponse, columnName);
    }

    public List<String> searchColumnValueList(String columnName, String inputValue) throws IOException {
        // request
        SearchRequest request = new SearchRequest("job");
        // DSL
        request.source().query(QueryBuilders.matchQuery(columnName, inputValue)
                .fuzziness(Fuzziness.AUTO).maxExpansions(50));
        // search
        SearchResponse searchResponse = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        return handelResponseToStringList(searchResponse, columnName);
    }

    public Boolean searchColumnValueExist(String columnName, String inputValue) throws IOException {
        // request
        SearchRequest request = new SearchRequest("job_match");
        // DSL
        request.source().query(QueryBuilders.termQuery(columnName, inputValue));
        // search
        SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        // 搜索到的结果数量
        long value = response.getHits().getTotalHits().value;
        return value > 0;
    }

    public List<String> searchColumnAllValueList(String columnName) throws IOException {
        SearchRequest request = new SearchRequest("job_match");
        request.source().query(QueryBuilders.matchAllQuery()).fetchSource(columnName, null).size(30);
        SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        return handelResponseToStringList(response, columnName);
    }
}

