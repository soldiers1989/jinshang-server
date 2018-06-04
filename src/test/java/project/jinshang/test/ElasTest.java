package project.jinshang.test;

import com.hankcs.hanlp.dictionary.CustomDictionary;
import mizuki.project.core.restserver.util.JsonUtil;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchPhraseQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.ParsedAggregation;
import org.elasticsearch.search.aggregations.bucket.nested.ParsedNested;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_admin.mod_synonym.Synonym;
import project.jinshang.mod_admin.mod_synonym.SynonymMapper;
import project.jinshang.mod_product.ProductInfoMapper;
import project.jinshang.mod_product.bean.ProductAttr;
import project.jinshang.mod_product.bean.ProductInfo;
import project.jinshang.mod_product.bean.ProductInfoExample;
import project.jinshang.mod_product.service.ProductAttrService;
import project.jinshang.mod_product.service.ProductSearchService;
import project.jinshang.service.ElasticSearchService;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasTest {

    @Autowired
    private ElasticSearchService searchService;
    @Autowired
    private ProductSearchService productSearchService;
    @Autowired
    private SynonymMapper synonymMapper;
    @Autowired
    private ProductInfoMapper productInfoMapper;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void test() throws Exception {
        List<Synonym> synonyms = synonymMapper.listAll();
        synonyms.forEach(synonym -> synonym.getWords().forEach(CustomDictionary::add));
        ProductInfoExample example = new ProductInfoExample();
        example.createCriteria().andPdstateEqualTo(Quantity.STATE_4);
        List<ProductInfo> list = productInfoMapper.selectByExample(example);
        list.forEach(info->{
            try {
                productSearchService.saveIndex(info);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("rebuild es data err: ",e);
            }
        });

//        PutMappingRequest request = new PutMappingRequest();
//        request.source(searchService.mapping(),XContentType.JSON);
//        searchService.getClient().
    }

    @Test
    public void delIndex() throws Exception{
        searchService.delIndex(ElasticSearchService.INDEX_PRODUCT);
    }

    @Test
    public void testNest() throws Exception {
        CreateIndexRequest request = new CreateIndexRequest("person");
        searchService.getClient().indices().create(request);
        Map<String,Object> map = new HashMap<>();
        List<Map> list = new ArrayList<>();
        Map<String,Object> map1 = new HashMap<>();
        map1.put("name","ssssx");
        map1.put("age",12);
        Map<String,Object> map2 = new HashMap<>();
        map2.put("name","ggssx");
        map2.put("age",22);
        list.add(map1);list.add(map2);
        map.put("list",list);
        searchService.update("person",1000L,map);
    }

//    @Test
    public void get() throws Exception{
        GetRequest request = new GetRequest(ElasticSearchService.INDEX_PRODUCT,"doc","1639");
//        GetRequest request = new GetRequest("person","doc","1000");
        GetResponse response = searchService.getClient().get(request);
        System.out.println(response.isExists());
        System.out.println(response.getSourceAsString());
    }

//    public void update() throws Exception{
//        Map<String,Object> data = new HashMap<>();
//        data.put("key1",32);
//        data.put("key2","abc  萝卜");
//        UpdateRequest request = new UpdateRequest("item","doc","1")
//                .doc(data);
//        UpdateResponse response = searchService.getClient().update(request);
//    }

    @Test
    public void search() throws Exception{
        SearchRequest searchRequest = new SearchRequest(ElasticSearchService.INDEX_PRODUCT).types(ElasticSearchService.INDEX_PRODUCT_TYPE_INFO);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        // Sort descending by _score (the default)
        BoolQueryBuilder boolQueryBuilder=QueryBuilders.boolQuery();
        List<QueryBuilder> must1 = boolQueryBuilder.must();
        must1.add(QueryBuilders.termQuery("pdstate",4));
        must1.add(QueryBuilders.termQuery("producttype",AppConstant.FASTENER_PRO_TYPE));

        BoolQueryBuilder nestStore = QueryBuilders.boolQuery();
//        nestStore.must().add(QueryBuilders.termQuery("stores.storename","宁波"));
//        nestStore.must().add(QueryBuilders.rangeQuery("stores.prodprice").gte(200));
        boolQueryBuilder.must(QueryBuilders.nestedQuery("stores", nestStore, ScoreMode.Avg));
        searchSourceBuilder.aggregation(AggregationBuilders.terms("productname").field("productname"));
        searchSourceBuilder.aggregation(AggregationBuilders.nested("attrs","stores.attrlist")
                .subAggregation(AggregationBuilders.terms("attribute").field("stores.attrlist.attribute")
                        .subAggregation(AggregationBuilders.terms("value").field("stores.attrlist.value"))));
        searchSourceBuilder.sort(SortBuilders.scoreSort().order(SortOrder.DESC));
        searchSourceBuilder.sort(SortBuilders.fieldSort("selfsupport").order(SortOrder.DESC));
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(5);
        searchSourceBuilder.query(boolQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse response = searchService.getClient().search(searchRequest);
//        System.out.println(JsonUtil.toJson(response));
//        System.out.println(response.getHits().totalHits);
        for(SearchHit hit:response.getHits().getHits()){
            System.out.println(hit.getId()+" "+hit.getSourceAsString());
        }
        for (Aggregation aggregation:response.getAggregations().asList()){
            System.out.println(aggregation.getName());
            if(aggregation instanceof ParsedStringTerms){
                ParsedStringTerms terms = (ParsedStringTerms)aggregation;
                for ( Terms.Bucket bucket : terms.getBuckets()){
                    System.out.println(bucket.getKeyAsString());
                }
            }else if(aggregation instanceof ParsedNested){
                ParsedNested nested = (ParsedNested)aggregation;
                for (Aggregation aggregation1 :nested.getAggregations().asList()){
                    ParsedStringTerms terms = (ParsedStringTerms)aggregation1;
                    for ( Terms.Bucket bucket : terms.getBuckets()){
                        System.out.println("---- attr: "+bucket.getKey());
                        if(bucket.getAggregations()!=null){
                            for(Aggregation aggregation2:bucket.getAggregations()){
                                ParsedStringTerms terms2 = (ParsedStringTerms)aggregation2;
                                for ( Terms.Bucket bucket2 : terms2.getBuckets()) {
                                    System.out.println(bucket2.getKey());
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
