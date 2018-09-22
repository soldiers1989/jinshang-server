package project.jinshang.test;

import com.hankcs.hanlp.dictionary.CustomDictionary;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
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
import project.jinshang.mod_admin.mod_synonym.Synonym;
import project.jinshang.mod_admin.mod_synonym.SynonymMapper;
import project.jinshang.mod_product.ProductInfoMapper;
import project.jinshang.mod_product.bean.ProductInfo;
import project.jinshang.mod_product.bean.ProductInfoExample;
import project.jinshang.mod_product.bean.dto.ProductInfoEsDTO;
import project.jinshang.mod_product.service.ProductSearchServiceImplEs;
import project.jinshang.service.ElasticSearchService;

import java.io.IOException;
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
    private ProductSearchServiceImplEs productSearchService;
    @Autowired
    private SynonymMapper synonymMapper;
    @Autowired
    private ProductInfoMapper productInfoMapper;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void indexOne(){
        ProductInfo info = productInfoMapper.selectByPrimaryKey(9317L);
        System.out.println(productSearchService.genProductInfoData(info).get("indexes"));
    }

//    @Test
    public void test() throws Exception {
        List<Synonym> synonyms = synonymMapper.listAll();
        synonyms.forEach(synonym -> synonym.getWords().forEach(CustomDictionary::add));
        ProductInfoExample example = new ProductInfoExample();
        example.createCriteria().andPdstateEqualTo(Quantity.STATE_4);
        List<ProductInfoEsDTO> list = productInfoMapper.listProductInfoEsDTO();
        List<Map<String,Object>> data = new ArrayList<>();
        list.forEach(info-> data.add(productSearchService.genProductInfoData(info)));
        try {
            searchService.bulkUpdate(ElasticSearchService.INDEX_PRODUCT, data);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("rebuild es data err: ",e);
        }

//        PutMappingRequest request = new PutMappingRequest();
//        request.source(searchService.mapping(),XContentType.JSON);
//        searchService.getClient().
    }

    @Test
    public void delIndex() throws Exception{
        searchService.delIndex(ElasticSearchService.INDEX_PRODUCT);
    }

//    @Test
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

    /**
     * 分组统计
     */
    @Test
    public void aggregations(){
        SearchRequest searchRequest = new SearchRequest(ElasticSearchService.INDEX_PRODUCT).types(ElasticSearchService.INDEX_PRODUCT_TYPE_INFO);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder=QueryBuilders.boolQuery();

        boolQueryBuilder.must().add(QueryBuilders.termQuery("pdstate",4));
        boolQueryBuilder.must().add(QueryBuilders.termQuery("producttype", AppConstant.FASTENER_PRO_TYPE));

        searchSourceBuilder.aggregation(AggregationBuilders.terms("level3").field("level3").size(1000));
        searchSourceBuilder.query(boolQueryBuilder);

        searchRequest.source(searchSourceBuilder);
        try {
            SearchResponse response = searchService.getClient().search(searchRequest);

            List<Aggregation> list = response.getAggregations().asList();
            System.out.println(list.size());
            for(Aggregation aggregation : list){
                if (aggregation instanceof ParsedStringTerms) {
                    ParsedStringTerms terms = (ParsedStringTerms) aggregation;
                    List<? extends Terms.Bucket> buckets = terms.getBuckets();
                    for(Terms.Bucket bucket : buckets){
                        System.out.println(bucket.getKeyAsString()+"===="+bucket.getDocCount());
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }




//    @Test
    public void search() throws Exception{
        SearchRequest searchRequest = new SearchRequest(ElasticSearchService.INDEX_PRODUCT).types(ElasticSearchService.INDEX_PRODUCT_TYPE_INFO);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        // Sort descending by _score (the default)
        BoolQueryBuilder boolQueryBuilder=QueryBuilders.boolQuery();

        boolQueryBuilder.must().add(QueryBuilders.termQuery("pdstate",4));
        boolQueryBuilder.must().add(QueryBuilders.termQuery("producttype", AppConstant.FASTENER_PRO_TYPE));

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

    @Test
    public void searchByStore() throws IOException {
        List<Map<String, Object>> maps = productSearchService.searchByStoreidAndPdno((long) 163, "019333040330");
        for(Map map : maps){
            //System.out.println(map);

            List<Map<String,Object>> stores = (List<Map<String, Object>>) map.get("stores");
            for(Map<String,Object> store : stores){
                System.out.println(store);

               // store.put("pdstorenum",44);
            }

//            List<Map<String,Object>> list = new ArrayList<>();
//            list.add(map);
//            productSearchService.bulkUpdateIndex(list);
        }
    }

    @Test
    public void rebuild() throws InterruptedException {
        productSearchService.rebuildIndex();
    }

}
