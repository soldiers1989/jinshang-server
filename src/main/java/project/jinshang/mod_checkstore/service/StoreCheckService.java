package project.jinshang.mod_checkstore.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import project.jinshang.common.utils.NlpUtils;
import project.jinshang.mod_checkstore.ProductStoreCheckMapper;
import project.jinshang.mod_checkstore.mapper.ProductStoreCheckSearchMapper;
import project.jinshang.mod_checkstore.bean.ProductStoreCheck;
import java.util.List;
import java.util.Map;

@Service
public class StoreCheckService {

    @Autowired
    private NlpUtils nlpUtils;

    @Autowired
    private ProductStoreCheckSearchMapper productStoreCheckSearchMapper;

    @Autowired
    private ProductStoreCheckMapper productStoreCheckMapper;
    /**
     * 总数
     */
    public int countSearchWithKeys(String search_str,String level1, String level2, String level3,
                                   String productname, String brand, String cardnum, String material,
                                   String surfacetreatment, Map<String,Object> attrs,String memberids){
        return productStoreCheckSearchMapper.countSearchWithKeys(nlpUtils.seqForPgQuery(search_str),
                level1,level2,level3,productname,brand,cardnum,material,surfacetreatment,attrs,memberids);
    }

    /**
     * 搜索中 再分类显示结果
     * todo
     * 分组分页
     * start 分页开始 （0 起始）
     * max 分页个数 -1则为全部
     */
    public List<Map> searchWithKeys(
            String search_str,
            String level1,String level2,String level3,
            String productname,String brand,String cardnum,String material,String surfacetreatment,Map<String,Object> attrs,
            int start,int max,String memberids){
        return productStoreCheckSearchMapper.searchWithKeys(nlpUtils.seqForPgQuery(search_str),
                level1,level2,level3,productname,brand,cardnum,material,surfacetreatment,attrs,start,max,memberids);
    }

    /***
     * 提取分类关键字
     * todo
     */
    public List<Map> fetchSearchKeys(String search_str,
                                     String level1,String level2,String level3,
                                     String productname,String brand,String cardnum,String material,String surfacetreatment,Map<String,Object> attrs){
        return productStoreCheckSearchMapper.searchKeys(nlpUtils.seqForPgQuery(search_str),
                level1,level2,level3,productname,brand,cardnum,material,surfacetreatment,attrs);
    }

    public List<Map> fetchSearchAttrKeys(String search_str,
                                         String level1,String level2,String level3,
                                         String productname,String brand,String cardnum,String material,String surfacetreatment,Map<String,Object> attrs){
        return productStoreCheckSearchMapper.searchAttrKeys(nlpUtils.seqForPgQuery(search_str),
                level1,level2,level3,productname,brand,cardnum,material,surfacetreatment,attrs);
    }



    public List<Map> fetchSearchAttrKeysHashAttr(String search_str,
                                                 String level1,String level2,String level3,
                                                 String productname,String brand,String cardnum,String material,String surfacetreatment,Map<String,Object> attrs){
        return productStoreCheckSearchMapper.searchAttrKeysHashAttr(nlpUtils.seqForPgQuery(search_str),
                level1,level2,level3,productname,brand,cardnum,material,surfacetreatment,attrs);
    }

    public List<ProductStoreCheck> getProductStoreCheck(String pdno, int pdid){
        return productStoreCheckMapper.getProductStoreCheck(pdno,pdid);
    }

    public ProductStoreCheck getProductStoreCheckById(int id){
        return productStoreCheckMapper.getProductStoreCheckById(id);
    }

    public void deleteProductStoreCheckById(int id){
        productStoreCheckMapper.deleteProductStoreCheckById(id);
    }

    public int addAndUpdateProductStoreCheck(ProductStoreCheck psCheck){
        if(psCheck.getId()==null) {
            return productStoreCheckMapper.insertSelective(psCheck);
        }else{
            return productStoreCheckMapper.updateByPrimaryKeySelective(psCheck);
        }
    }
}
