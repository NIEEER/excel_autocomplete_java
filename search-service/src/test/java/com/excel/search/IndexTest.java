package com.excel.search;


import com.alibaba.fastjson.JSON;
import com.excel.feign.client.DataManagerClient;
import com.excel.feign.pojo.TbJob;
import com.excel.search.config.ElasticSearchClientConfig;
import com.excel.util.EntityReflectionUtils;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
public class IndexTest {
    private RestHighLevelClient client;

    @BeforeEach
    void setUp() {
        this.client = new RestHighLevelClient(RestClient.builder(
                HttpHost.create("http://192.168.43.228:9200")
        ));
    }

    @AfterEach
    void tearDown() throws IOException {
        this.client.close();
    }
    @Autowired
    private ElasticSearchClientConfig esClient;
    @Autowired
    DataManagerClient dataManagerClient;

    private List<String> handelResponse(SearchResponse response, String columnName) {
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
        return resList.stream().distinct().collect(Collectors.toList());
    }

    @Test
    public void testClient() throws IOException {
        GetIndexRequest request = new GetIndexRequest("hotel");
        boolean exists = esClient.getRestHighLevelClient().indices().exists(request, RequestOptions.DEFAULT);
        System.err.println(exists);
    }

    @Test
    public void testAddDocument() throws IOException {
        TbJob job = dataManagerClient.getJobById("330");
        System.out.println(job);
        IndexRequest request = new IndexRequest("job").id(job.getId().toString());
        request.source(JSON.toJSONString(job), XContentType.JSON);
        esClient.getRestHighLevelClient().index(request, RequestOptions.DEFAULT);
    }

    @Test
    public void testGetDocumentById() throws IOException {
        GetRequest request = new GetRequest("job", "330");
        GetResponse response = esClient.getRestHighLevelClient().get(request, RequestOptions.DEFAULT);
        String res = response.getSourceAsString();
        TbJob job = JSON.parseObject(res, TbJob.class);
        System.out.println(job);
    }

    @Test
    public void testSearchDocument() throws IOException {
        String name = "employmentBureau";
        String text = "卫";
        // request
        SearchRequest request = new SearchRequest("job");
        // DSL
        request.source().query(QueryBuilders.matchPhrasePrefixQuery(name, text)
                .slop(1).maxExpansions(50));
        // search
        SearchResponse searchResponse = esClient.getRestHighLevelClient().search(request, RequestOptions.DEFAULT);
        List<String> responseList = handelResponse(searchResponse, "employmentBureau");
        System.out.println(responseList);
    }

    @Test
    public void testDeleteDocument() throws IOException {
        DeleteRequest request = new DeleteRequest("job", "330");
        esClient.getRestHighLevelClient().delete(request, RequestOptions.DEFAULT);
    }

    @Test
    public void searchColumnAllValueList() throws IOException {
        SearchRequest request = new SearchRequest("job_match");
        request.source().query(QueryBuilders.matchAllQuery()).fetchSource("characterOfStructure", null);
        SearchResponse response = esClient.getRestHighLevelClient().search(request, RequestOptions.DEFAULT);
        List<String> stringList = handelResponse(response, "characterOfStructure");
        System.out.println(stringList);
    }

    @Test
    public void addBulkDocument() throws IOException {
        List<TbJob> listAllJob = dataManagerClient.getAllJob();
        BulkRequest request = new BulkRequest();
        for (TbJob job : listAllJob) {
            request.add(new IndexRequest("job_match")
                    .id(job.getId().toString())
                    .source(JSON.toJSONString(job), XContentType.JSON));
        }
        esClient.getRestHighLevelClient().bulk(request, RequestOptions.DEFAULT);
    }
}

