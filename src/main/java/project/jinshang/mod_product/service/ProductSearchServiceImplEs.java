package project.jinshang.mod_product.service;

import com.hankcs.hanlp.dictionary.CustomDictionary;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.utils.NlpUtils;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_admin.mod_synonym.Synonym;
import project.jinshang.mod_admin.mod_synonym.SynonymMapper;
import project.jinshang.mod_member.MemberMapper;
import project.jinshang.mod_product.BrandMapper;
import project.jinshang.mod_product.ProductAttrMapper;
import project.jinshang.mod_product.ProductInfoMapper;
import project.jinshang.mod_product.ProductStoreMapper;
import project.jinshang.mod_product.bean.*;
import project.jinshang.service.ElasticSearchService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProductSearchServiceImplEs implements ProductSearchService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private NlpUtils nlpUtils;
    @Autowired
    private ProductInfoMapper productInfoMapper;
    @Autowired
    private SynonymMapper synonymMapper;
    @Autowired
    private ProductStoreMapper productStoreMapper;
    @Autowired
    private ProductAttrMapper attrMapper;

    private ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    /***
     * es数据结构：
     *  index: product
     *  doc: id : productid
     *  value:
     *      productInfo所有field
     *      stores:[ productstore参数 ]
     *      Member.membersettingstate,
     *      Brand.pic
     *      indexes-分词信息
     */
    @Autowired
    private ElasticSearchService elasticSearchService;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private BrandMapper brandMapper;

    private String genIndex(ProductInfo productInfo,List<ProductStore> stores){
        Set<String> params  = new HashSet<>();
        for (ProductStore store:stores){
            List<ProductAttr> list = store.getAttrList();
            if(list != null) {
                for (int i = 0; i < list.size(); i++) {
                    ProductAttr attr = list.get(i);
                    params.add(StringUtils.nvl(attr.getValue()).toLowerCase());
                }
            }
        }
        params.add(StringUtils.nvl(productInfo.getProductname()).toLowerCase());
        params.add(StringUtils.nvl(productInfo.getMark()).toLowerCase());
        params.add(StringUtils.nvl(productInfo.getBrand()).toLowerCase());
        params.add(StringUtils.nvl(productInfo.getLevel1()).toLowerCase());
        params.add(StringUtils.nvl(productInfo.getLevel2()).toLowerCase());
        params.add(StringUtils.nvl(productInfo.getLevel3()).toLowerCase());
        params.add(StringUtils.nvl(productInfo.getStand()).toLowerCase());
        params.add(StringUtils.nvl(productInfo.getMaterial()).toLowerCase());
        params.add(StringUtils.nvl(productInfo.getCardnum()).toLowerCase());
        params.add(StringUtils.nvl(productInfo.getProductalias().toLowerCase()));
//        params.add(StringUtils.nvl(productInfo.getPddes().toLowerCase()));
        String[] paramArr =  new String[params.size()];
        return nlpUtils.seqForPgVector(params.toArray(paramArr));
    }

    public void rebuildIndex(){
        // todo
        cachedThreadPool.execute(() -> {
        // 重新加载词典
            List<Synonym> synonyms = synonymMapper.listAll();
            synonyms.forEach(synonym -> synonym.getWords().forEach(CustomDictionary::add));
            ProductInfoExample example = new ProductInfoExample();
            example.createCriteria().andPdstateEqualTo(Quantity.STATE_4);
            List<ProductInfo> list = productInfoMapper.selectByExample(example);
            list.forEach(info->{
                try {
                    saveIndex(info);
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error("rebuild es data err: ",e);
                }
            });

        });
    }

    public void saveIndex(ProductInfo productInfo) {
        try {
            Map<String, Object> info = new HashMap<>();
            info.put("id",productInfo.getId());
            info.put("level1",productInfo.getLevel1());
            info.put("level1id",productInfo.getLevel1id());
            info.put("level2",productInfo.getLevel2());
            info.put("level2id",productInfo.getLevel2id());
            info.put("level3",productInfo.getLevel3());
            info.put("level3id",productInfo.getLevel3id());
            info.put("productsno",productInfo.getProductsno());
            info.put("productnameid",productInfo.getProductnameid());
            info.put("productname",productInfo.getProductname());
            info.put("productalias",productInfo.getProductalias());
            info.put("subtitle",productInfo.getSubtitle());
            info.put("brand",productInfo.getBrand());
            info.put("brandid",productInfo.getBrandid());
            info.put("materialid",productInfo.getMaterialid());
            info.put("material",productInfo.getMaterial());
            info.put("mark",productInfo.getMark());
            info.put("producttype",productInfo.getProducttype());
            info.put("unit",productInfo.getUnit());
            info.put("surfacetreatment",productInfo.getSurfacetreatment());
            info.put("weight",formatDecimal(productInfo.getWeight()));
            info.put("packagetype",productInfo.getPackagetype());
            info.put("recommended",productInfo.getRecommended());
            info.put("pdstate",productInfo.getPdstate());
            info.put("pddrawing",productInfo.getPddrawing());
            info.put("pdpicture",productInfo.getPdpicture());
            info.put("pddes",productInfo.getPddes());
            info.put("specificationparam",productInfo.getSpecificationparam());
            info.put("seokey",productInfo.getSeokey());
            info.put("seovalue",productInfo.getSeovalue());
            info.put("createtime",productInfo.getCreatetime());
            info.put("audittime",productInfo.getAudittime());
            info.put("auditname",productInfo.getAuditname());
            info.put("reason",productInfo.getReason());
            info.put("uptime",productInfo.getUptime());
            info.put("downtime",productInfo.getDowntime());
            info.put("salesnum",productInfo.getSalesnum());
            info.put("cardnumid",productInfo.getCardnumid());
            info.put("cardnum",productInfo.getCardnum());
            info.put("stand",productInfo.getStand());
            info.put("seotitle",productInfo.getSeotitle());
            info.put("updatetime",productInfo.getUpdatetime());
            info.put("productid",productInfo.getProductid());
            info.put("prodstoreunit",productInfo.getProdstoreunit());
            info.put("unitrate",formatDecimal(productInfo.getUnitrate()));
            info.put("minprice",formatDecimal(productInfo.getMinprice()));
            info.put("heightprice",formatDecimal(productInfo.getHeightprice()));
            info.put("selfsupport",productInfo.getSelfsupport());
            info.put("type",productInfo.getType());

            info.put("membersettingstate",memberMapper.selectByPrimaryKey(productInfo.getMemberid()).getMembersettingstate());
            Brand brand = brandMapper.selectByPrimaryKey(productInfo.getBrandid());
            info.put("pic",brand!=null?brand.getPic():null);
            List<ProductStore> list = productStoreMapper.getByProductid(productInfo.getId());
            List<Map> stores = new ArrayList<>();
            for(ProductStore productStore:list){
                Map<String,Object> ps = new HashMap<>();
                ps.put("storeid",productStore.getStoreid());
                ps.put("storename",productStore.getStorename());
                ps.put("stepwiseprice",productStore.getStepwiseprice());
                ps.put("startnum",formatDecimal(productStore.getStartnum()));
                ps.put("prodprice",formatDecimal(productStore.getProdprice()));
                ps.put("threeprice",formatDecimal(productStore.getThreeprice()));
                ps.put("ninetyprice",formatDecimal(productStore.getNinetyprice()));
                ps.put("thirtyprice",formatDecimal(productStore.getThirtyprice()));
                ps.put("sixtyprice",formatDecimal(productStore.getSixtyprice()));
                ps.put("intervalprice",productStore.getIntervalprice());
                ps.put("marketprice",formatDecimal(productStore.getMarketprice()));
                ps.put("costprice",formatDecimal(productStore.getCostprice()));
                ps.put("pdstorenum",formatDecimal(productStore.getPdstorenum()));
                ps.put("storeunit",productStore.getStoreunit());
                ps.put("aftersale",productStore.getAftersale());
                ps.put("location",productStore.getLocation());
                ps.put("freightmode",productStore.getFreightmode());
                ps.put("minplus",formatDecimal(productStore.getMinplus()));
                ps.put("pdno",productStore.getPdno());
                List<ProductAttr> attrList = attrMapper.getListByPidAndPdno(productInfo.getId(),productStore.getPdno());
                productStore.setAttrList(attrList);
                List<Map> attrs = new ArrayList<>();
                for(ProductAttr attr:attrList){
                    Map<String,Object> amap= new HashMap<>();
                    amap.put("attribute",attr.getAttribute());
                    amap.put("value",attr.getValue());
                    attrs.add(amap);
                }
                ps.put("attrlist",attrs);
                stores.add(ps);
            }
            info.put("stores",stores);
            info.put("indexes", genIndex(productInfo,list));
            elasticSearchService.update(ElasticSearchService.INDEX_PRODUCT, productInfo.getId(), info);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("save index err:",e);
        }
    }

    public void delIndex(long productId){
        try {
            elasticSearchService.del(ElasticSearchService.INDEX_PRODUCT,productId);
        } catch (IOException e) {
            logger.error("del index err:",e);
        }
    }

    /**
     * list:
     * total:
     * aggs:{ key:list }
     * aggs_attr:{key: list}
     */
    @Override
    public Map<String, Object> search(String search_str, String level1, String level2, String level3, String productname, String brand, String cardnum, String material, String surfacetreatment, Map<String, Object> attrs, int start, int max, String sorttype, Integer selfsupport, Integer havestore, Integer forwardtime, String store,Integer type,String productType) {
        SearchRequest searchRequest = new SearchRequest(ElasticSearchService.INDEX_PRODUCT).types(ElasticSearchService.INDEX_PRODUCT_TYPE_INFO);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder=QueryBuilders.boolQuery();
        boolQueryBuilder.must().add(QueryBuilders.termQuery("pdstate",4));
        boolQueryBuilder.must().add(QueryBuilders.termQuery("producttype",productType));

        if(StringUtils.hasText(level1)) boolQueryBuilder.must().add(QueryBuilders.termQuery("level1",level1));
        if(StringUtils.hasText(level2)) boolQueryBuilder.must().add(QueryBuilders.termQuery("level2",level2));
        if(StringUtils.hasText(level3)) boolQueryBuilder.must().add(QueryBuilders.termQuery("level3",level3));
        if(StringUtils.hasText(productname)) boolQueryBuilder.must().add(QueryBuilders.matchQuery("productname",productname));
        if(StringUtils.hasText(brand)) boolQueryBuilder.must().add(QueryBuilders.termQuery("brand",brand));
        if(StringUtils.hasText(cardnum)) boolQueryBuilder.must().add(QueryBuilders.termQuery("cardnum",cardnum));
        if(StringUtils.hasText(material)) boolQueryBuilder.must().add(QueryBuilders.termQuery("material",material));
        if(StringUtils.hasText(surfacetreatment)) boolQueryBuilder.must().add(QueryBuilders.termQuery("surfacetreatment",surfacetreatment));
        if(selfsupport!=null && selfsupport == 1) boolQueryBuilder.must().add(QueryBuilders.termQuery("selfsupport",true));
        if(type!=null && type>0) boolQueryBuilder.must().add(QueryBuilders.termQuery("type",type));

        BoolQueryBuilder nestStore = QueryBuilders.boolQuery();
        if(StringUtils.hasText(store))
            nestStore.must().add(QueryBuilders.termQuery("stores.storename",store));
        if(havestore!=null && havestore == 1)
            nestStore.must().add(QueryBuilders.rangeQuery("stores.pdstorenum").gt(1));
        if(forwardtime!=null && forwardtime == 1){
            nestStore.should().add(QueryBuilders.rangeQuery("stores.ninetyprice").gt(0));
            nestStore.should().add(QueryBuilders.rangeQuery("stores.thirtyprice").gt(0));
            nestStore.should().add(QueryBuilders.rangeQuery("stores.sixtyprice").gt(0));
        }
        if(nestStore.must().size()>0 || nestStore.should().size()>0)
            boolQueryBuilder.must().add(QueryBuilders.nestedQuery("stores", nestStore, ScoreMode.Avg));

        if(attrs!=null){
            for(String key:attrs.keySet()){
                BoolQueryBuilder nestAttr = QueryBuilders.boolQuery();
                nestAttr.must().add(QueryBuilders.termQuery("stores.attrlist.attribute",key));
                nestAttr.must().add(QueryBuilders.termQuery("stores.attrlist.value",attrs.get(key)));
                boolQueryBuilder.must().add(QueryBuilders.nestedQuery("stores.attrlist",nestAttr,ScoreMode.Avg));
            }
        }
        String query = nlpUtils.seqForVectorNoSynonym(search_str);
        if(StringUtils.hasText(query)) {
            boolQueryBuilder.must().add(QueryBuilders.matchQuery("indexes", query));
        }
        // todo sort
        searchSourceBuilder.sort(SortBuilders.scoreSort().order(SortOrder.DESC));
        searchSourceBuilder.sort(SortBuilders.fieldSort("selfsupport").order(SortOrder.DESC));
        if(StringUtils.hasText(sorttype)){
            String[] sorttypes = sorttype.trim().split(" ");
            if(sorttypes.length==2){
                searchSourceBuilder.sort(SortBuilders.fieldSort(sorttypes[0]).order("desc".equals(sorttypes[1])?SortOrder.DESC:SortOrder.ASC));
            }
        }

        searchSourceBuilder.from(start);
        searchSourceBuilder.size(max);
        searchSourceBuilder.query(boolQueryBuilder);
        if(productType.equals(AppConstant.FASTENER_PRO_TYPE)) searchSourceBuilder.aggregation(AggregationBuilders.terms("productname").field("productname"));
        searchSourceBuilder.aggregation(AggregationBuilders.terms("level1").field("level1"));
        searchSourceBuilder.aggregation(AggregationBuilders.terms("level2").field("level2"));
        searchSourceBuilder.aggregation(AggregationBuilders.terms("level3").field("level3"));
        searchSourceBuilder.aggregation(AggregationBuilders.terms("brand").field("brand"));
        if(productType.equals(AppConstant.FASTENER_PRO_TYPE)) searchSourceBuilder.aggregation(AggregationBuilders.terms("cardnum").field("cardnum"));
        if(productType.equals(AppConstant.FASTENER_PRO_TYPE)) searchSourceBuilder.aggregation(AggregationBuilders.terms("material").field("material"));
        if(productType.equals(AppConstant.FASTENER_PRO_TYPE)) searchSourceBuilder.aggregation(AggregationBuilders.terms("surfacetreatment").field("surfacetreatment"));
        if(productType.equals(AppConstant.FASTENER_PRO_TYPE)) searchSourceBuilder.aggregation(AggregationBuilders.nested("storename","stores").subAggregation(AggregationBuilders.terms("storename").field("stores.storename")));
        if(productType.equals(AppConstant.FASTENER_PRO_TYPE)) searchSourceBuilder.aggregation(AggregationBuilders.nested("attrs","stores.attrlist")
                .subAggregation(AggregationBuilders.terms("attribute").field("stores.attrlist.attribute")
                        .subAggregation(AggregationBuilders.terms("value").field("stores.attrlist.value"))));

        searchRequest.source(searchSourceBuilder);
        Map<String,Object> ret = new HashMap<>();
        try {
            List<Map> list = new ArrayList<>();
            SearchResponse response = elasticSearchService.getClient().search(searchRequest);
            for(SearchHit hit:response.getHits().getHits()){
                Map<String,Object> map = hit.getSourceAsMap();
                map.remove("indexes");
                List<Map> stores = (List<Map>) map.get("stores");
                // todo
                if(stores!=null && stores.size()==1){
                    map.putAll(stores.get(0));
                }
                list.add(map);
            }
            ret.put("list",list);
            ret.put("total",response.getHits().totalHits);
            // attr 关键字
            Map<String,List> aggsMap = new HashMap<>();
            Map<String,List> aggsAttrMap = new HashMap<>();
            if(response.getAggregations()!=null) {
                for (Aggregation aggregation : response.getAggregations().asList()) {
                    if (aggregation instanceof ParsedStringTerms) {
                        // 一般关键字
                        List<String> aggslist = new ArrayList<>();
                        ParsedStringTerms terms = (ParsedStringTerms) aggregation;
                        for (Terms.Bucket bucket : terms.getBuckets()) {
                            aggslist.add(bucket.getKeyAsString());
                        }
                        aggsMap.put(aggregation.getName(),aggslist);
                    } else if (aggregation instanceof ParsedNested) {
                        ParsedNested nested = (ParsedNested) aggregation;
                        if("storename".equals(aggregation.getName())){
                            List<String> aggslist = new ArrayList<>();
                            for (Aggregation aggregation1 : nested.getAggregations().asList()) {
                                ParsedStringTerms terms = (ParsedStringTerms) aggregation1;
                                for (Terms.Bucket bucket : terms.getBuckets()) {
                                    aggslist.add(bucket.getKeyAsString());
                                }
                            }
                            aggsMap.put(aggregation.getName(),aggslist);
                        }else if("attrs".equals(aggregation.getName())){
                            // 属性关键字
                            for (Aggregation aggregation1 : nested.getAggregations().asList()) {
                                ParsedStringTerms terms = (ParsedStringTerms) aggregation1;
                                for (Terms.Bucket bucket : terms.getBuckets()) {
                                    // 属性
                                    List<String> aggsAttrlist = new ArrayList<>();
                                    if (bucket.getAggregations() != null) {
                                        for (Aggregation aggregation2 : bucket.getAggregations()) {
                                            ParsedStringTerms terms2 = (ParsedStringTerms) aggregation2;
                                            for (Terms.Bucket bucket2 : terms2.getBuckets()) {
                                                // value
                                                aggsAttrlist.add(bucket2.getKeyAsString());
                                            }
                                        }
                                    }
                                    aggsAttrMap.put(bucket.getKeyAsString(),aggsAttrlist);
                                }
                            }
                        }

                    }
                }
            }
            ret.put("aggs",aggsMap);
            ret.put("aggs_attr",aggsAttrMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public List<Map> searchWithKeys(
            String search_str,
            String level1,String level2,String level3,
            String productname,String brand,String cardnum,String material,String surfacetreatment,Map<String,Object> attrs,
            int start,int max,String sorttype,Integer selfsupport,Integer havestore,Integer forwardtime,String store
    ){
        return null;
    }

    @Override
    public List<Map> fetchSearchKeys(String search_str, String level1, String level2, String level3, String productname, String brand, String cardnum, String material, String surfacetreatment, Map<String, Object> attrs, Integer selfsupport, Integer havestore, Integer forwardtime, String store) {
        return null;
    }

    @Override
    public List<Map> otherProdFetchSearchKeys(String search_str, String level1, String level2, String level3, String productname, String brand, Map<String, Object> attrs, int type) {
        return null;
    }

    @Override
    public List<Map> fetchSearchAttrKeys(String search_str, String level1, String level2, String level3, String productname, String brand, String cardnum, String material, String surfacetreatment, Map<String, Object> attrs, Integer selfsupport, Integer havestore, Integer forwardtime, String store) {
        return null;
    }

    @Override
    public List<Map> fetchSearchAttrKeysHashAttr(String search_str, String level1, String level2, String level3, String productname, String brand, String cardnum, String material, String surfacetreatment, Map<String, Object> attrs, Integer selfsupport, Integer havestore, Integer forwardtime, String store) {
        return null;
    }

    @Override
    public List<Map> otherProdFetchSearchAttrKeys(String search_str, String level1, String level2, String level3, String productname, String brand, Map<String, Object> attrs, int type) {
        return null;
    }

    @Override
    public List<Map> otherProdSearchWithKeys(String search_str, String level1, String level2, String level3, String productname, String brand, Map<String, Object> attrs, int pageNo, int pageSize, String sorttype, int type) {
        return null;
    }

    @Override
    public int countSearchWithKeys(String search_str, String level1, String level2, String level3, String productname, String brand, String cardnum, String material, String surfacetreatment, Map<String, Object> attrs, Integer selfsupport, Integer havestore, Integer forwardtime, String store) {
        return 0;
    }

    @Override
    public int otherProdCountSearchWithKeys(String search_str, String level1, String level2, String level3, String productname, String brand, Map<String, Object> attrs, int type) {
        return 0;
    }

    private Double formatDecimal(BigDecimal decimal){
        return decimal==null?null:decimal.doubleValue();
    }
}
