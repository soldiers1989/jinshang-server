package project.jinshang.service;

import org.apache.http.HttpHost;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class ElasticSearchService {
    private RestHighLevelClient client;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value("${mod.es.ip}")
    private String ip;
    @Value("${mod.es.port}")
    private int port;

    public static final String INDEX_PRODUCT = "product4";
    public static final String INDEX_PRODUCT_TYPE_INFO = "info";

    @PostConstruct
    public void init() throws IOException {
        client = new RestHighLevelClient(RestClient.builder(
                        new HttpHost(ip, port, "http")
                ));
//        GetRequest getRequest = new GetRequest("product");
//        GetResponse response = client.get(getRequest);
//        if(!response.isExists()) {
//            System.out.println(123);
//        IndicesExistsRequest indicesExistsRequest = new IndicesExistsRequest("product");
//        IndicesExistsResponse indicesExistsResponse = client.indices().open(indicesExistsRequest);
//        IndexRequest indexRequest = new IndexRequest("product");
//        System.out.println(client.index(indexRequest).status().getStatus());
        CreateIndexRequest request = new CreateIndexRequest(INDEX_PRODUCT);
        request.mapping(INDEX_PRODUCT_TYPE_INFO,mapping(),XContentType.JSON);
        try {
            client.indices().create(request);
        }catch (Exception e){
            //todo
//            e.printStackTrace();
        }
    }

    public String mapping(){
        return "{\n" +
                "    \"info\": {\n" +
                "        \"properties\": {\n" +
                "            \"id\": { \"type\":  \"long\" },\n" +
                "            \"level1\": {\"type\":  \"keyword\"},\n" +
                "            \"level1id\": {\"type\":  \"long\"},\n" +
                "            \"level2\": {\"type\":  \"keyword\"},\n" +
                "            \"level2id\": {\"type\":  \"long\"},\n" +
                "            \"level3\": {\"type\":  \"keyword\"},\n" +
                "            \"level3id\": {\"type\":  \"long\"},\n" +
                "            \"productsno\": {\"type\":  \"keyword\"},\n" +
                "            \"productnameid\": {\"type\":  \"long\"},\n" +
                "            \"productname\": {\"type\":  \"keyword\"},\n" +
                "            \"productalias\": {\"type\":  \"text\"},\n" +
                "            \"subtitle\": {\"type\":  \"text\"},\n" +
                "            \"brand\": {\"type\":  \"keyword\"},\n" +
                "            \"brandid\": {\"type\":  \"long\"},\n" +
                "            \"materialid\": {\"type\":  \"long\"},\n" +
                "            \"material\": {\"type\":  \"keyword\"},\n" +
                "            \"mark\": {\"type\":  \"text\"},\n" +
                "            \"producttype\": {\"type\":  \"keyword\"},\n" +
                "            \"unit\": {\"type\":  \"keyword\"},\n" +
                "            \"surfacetreatment\": {\"type\":  \"keyword\"},\n" +
                "            \"weight\": {\"type\":  \"double\"},\n" +
                "            \"packagetype\": {\"type\":  \"keyword\"},\n" +
                "            \"recommended\": {\"type\":  \"boolean\"},\n" +
                "            \"pdstate\": {\"type\":  \"long\"},\n" +
                "            \"pddrawing\": {\"type\":  \"keyword\"},\n" +
                "            \"pdpicture\": {\"type\":  \"text\"},\n" +
                "            \"pddes\": {\"type\":  \"keyword\"},\n" +
                "            \"specificationparam\": {\"type\":  \"keyword\"},\n" +
                "            \"seokey\": {\"type\":  \"text\"},\n" +
                "            \"seovalue\": {\"type\":  \"text\"},\n" +
                "            \"createtime\": {\"type\":  \"date\"},\n" +
                "            \"audittime\": {\"type\":  \"date\"},\n" +
                "            \"auditname\": {\"type\":  \"keyword\"},\n" +
                "            \"reason\": {\"type\":  \"text\"},\n" +
                "            \"uptime\": {\"type\":  \"date\"},\n" +
                "            \"downtime\": {\"type\":  \"date\"},\n" +
                "            \"salesnum\": {\"type\":  \"long\"},\n" +
                "            \"cardnumid\": {\"type\":  \"long\"},\n" +
                "            \"cardnum\": {\"type\":  \"keyword\"},\n" +
                "            \"stand\": {\"type\":  \"keyword\"},\n" +
                "            \"seotitle\": {\"type\":  \"text\"},\n" +
                "            \"updatetime\": {\"type\":  \"date\"},\n" +
                "            \"productid\": {\"type\":  \"long\"},\n" +
                "            \"prodstoreunit\": {\"type\":  \"keyword\"},\n" +
                "            \"unitrate\": {\"type\":  \"double\"},\n" +
                "            \"minprice\": {\"type\":  \"double\"},\n" +
                "            \"heightprice\": {\"type\":  \"double\"},\n" +
                "            \"selfsupport\": {\"type\":  \"boolean\"},\n" +
                "            \"type\": {\"type\":  \"long\"},\n" +
                "            \"indexes\":{\"type\": \"text\", \"analyzer\": \"whitespace\"},\n" +
                "            \"membersettingstate\":{\"type\":  \"long\"},\n" +
                "            \"pic\":{\"type\":\"keyword\"},\n" +
                // cat_sort
                //"            \"cat_sort\":{\"type\":\"long\"},\n" +
                // attr_sort
                "            \"attr_sort\":{\"type\":\"long\"},\n" +
                "            \"level1_sort\":{\"type\":\"long\"},\n" +
                "            \"level2_sort\":{\"type\":\"long\"},\n" +
                "            \"level3_sort\":{\"type\":\"long\"},\n" +
                "            \"material_sort\":{\"type\":\"long\"},\n" +
                "            \"cardnum_sort\":{\"type\":\"long\"},\n" +
                "            \"surfacetreatment_sort\":{\"type\":\"long\"},\n" +
                "            \"brand_sort\":{\"type\":\"long\"},\n" +
                "            \"store_sort\":{\"type\":\"long\"},\n" +
                "            \"productname_sort\":{\"type\":\"long\"},\n" +

                "            \"stores\":{\n" +
                "                \"type\": \"nested\",\n" +
                "                \"properties\":{\n" +
                "                    \"storeid\": {\"type\":  \"long\"},\n" +
                "                    \"storename\": {\"type\":  \"keyword\"},\n" +
                "                    \"stepwiseprice\": {\"type\":  \"boolean\"},\n" +
                "                    \"startnum\": {\"type\":  \"double\"},\n" +
                "                    \"prodprice\": {\"type\":  \"double\"},\n" +
                "                    \"threeprice\": {\"type\":  \"double\"},\n" +
                "                    \"ninetyprice\": {\"type\":  \"double\"},\n" +
                "                    \"thirtyprice\": {\"type\":  \"double\"},\n" +
                "                    \"sixtyprice\": {\"type\":  \"double\"},\n" +
                "                    \"intervalprice\": {\"type\":  \"keyword\"},\n" +
                "                    \"marketprice\": {\"type\":  \"double\"},\n" +
                "                    \"costprice\": {\"type\":  \"double\"},\n" +
                "                    \"pdstorenum\": {\"type\":  \"double\"},\n" +
                "                    \"storeunit\": {\"type\":  \"keyword\"},\n" +
                "                    \"aftersale\": {\"type\":  \"keyword\"},\n" +
                "                    \"location\": {\"type\":  \"text\"},\n" +
                "                    \"freightmode\":{\"type\":\"long\"},\n" +
                "                    \"minplus\": {\"type\":  \"double\"},\n" +
                "                    \"pdno\": {\"type\":  \"keyword\"},\n" +
                "                    \"attrlist\":{\n" +
                "                        \"type\": \"nested\",\n" +
                "                        \"properties\":{\n" +
                "                            \"attribute\":{\"type\":\"keyword\"},\n" +
                "                            \"value\":{\"type\":\"keyword\"}\n" +
                "                        }\n" +
                "                    }\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}";
    }

    @PreDestroy
    public void destroy() throws IOException {
        if(client!=null) client.close();
    }

    public RestHighLevelClient getClient() {
        return client;
    }

    public void update(String index, Long id, Map data) throws IOException {
        GetRequest getRequest = new GetRequest(index,INDEX_PRODUCT_TYPE_INFO,String.valueOf(id));
        if(client.get(getRequest).isExists()){
            UpdateRequest request = new UpdateRequest(index,INDEX_PRODUCT_TYPE_INFO,String.valueOf(id)).doc(data);
            client.update(request);
        }else{
            IndexRequest indexRequest = new IndexRequest(index,INDEX_PRODUCT_TYPE_INFO,String.valueOf(id));
            indexRequest.source(data);
            client.index(indexRequest);
        }
        logger.info("elas update index");
    }

    public void bulkUpdate(String index,List<Map<String,Object>> data) throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        for(Map<String,Object> map:data){
            String id = String.valueOf(map.get("id"));
            GetRequest getRequest = new GetRequest(index,INDEX_PRODUCT_TYPE_INFO,id);
            DocWriteRequest request;
            if(client.get(getRequest).isExists()){
                request = new UpdateRequest(index,INDEX_PRODUCT_TYPE_INFO,id).doc(map);
            }else{
                request = new IndexRequest(index,INDEX_PRODUCT_TYPE_INFO,id).source(map);
            }
            bulkRequest.add(request);
        }
        client.bulk(bulkRequest);

    }

    public void del(String index, Long id) throws IOException {
        DeleteRequest request = new DeleteRequest(index,INDEX_PRODUCT_TYPE_INFO,String.valueOf(id));
        client.delete(request);
    }

    public void delIndex(String index) throws IOException{
        DeleteIndexRequest request = new DeleteIndexRequest(index);
        client.indices().delete(request);
    }
}
