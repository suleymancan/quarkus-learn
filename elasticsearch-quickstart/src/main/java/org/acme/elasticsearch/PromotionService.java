package org.acme.elasticsearch;

import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import javax.enterprise.context.ApplicationScoped;
import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class PromotionService {

    private final RestHighLevelClient restHighLevelClient;

    private final String INDEX_NAME = "promotion-quarkus-test";

    public PromotionService(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
    }

    public void index(Promotion promotion) throws IOException {
        if(promotion.id == null){
           promotion.id= UUID.randomUUID().toString();
        }
        final IndexRequest indexRequest = new IndexRequest(INDEX_NAME);
        indexRequest.id(String.valueOf(promotion.id));
        indexRequest.source(JsonObject.mapFrom(promotion).toString(), XContentType.JSON);
        restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
    }

    public Optional<Promotion> getById(Long id) throws IOException {
        final GetRequest getRequest = new GetRequest(INDEX_NAME, String.valueOf(id));
        final GetResponse getResponse = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        if(getResponse.isExists()){
            final String sourceAsString = getResponse.getSourceAsString();
            final JsonObject json = new JsonObject(sourceAsString);
            return Optional.of(json.mapTo(Promotion.class));
        }
        return Optional.empty();
    }

    public List<Promotion> searchByName(String name) throws IOException {
        final SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("name", name));
        return search(searchSourceBuilder);
    }


    private List<Promotion> search(SearchSourceBuilder searchSourceBuilder) throws IOException{
        final SearchRequest searchRequest = new SearchRequest(INDEX_NAME);
        searchRequest.source(searchSourceBuilder);

        final SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        final SearchHits hits = search.getHits();
        final List<Promotion> promotions = new ArrayList<>(hits.getHits().length);
        for(SearchHit searchHit : hits.getHits()){
            final String sourceAsString = searchHit.getSourceAsString();
            final JsonObject entries = new JsonObject(sourceAsString);
            promotions.add(entries.mapTo(Promotion.class));
        }
        return promotions;
    }
}
