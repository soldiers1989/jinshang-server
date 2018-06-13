package project.jinshang.mod_product.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import project.jinshang.mod_product.ProductStoreMapper;
import project.jinshang.mod_product.bean.ProductStore;
import project.jinshang.mod_product.bean.ProductStoreExample;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * create : wyh
 * date : 2017/11/10
 */

@Service
public class ProductStoreService {

    @Value("${shop.self-support.id}")
    private String shopSelfSupportid;

    @Autowired
    private ProductStoreMapper productStoreMapper;


    public void add(ProductStore productStore) {
        productStoreMapper.insert(productStore);
    }


    public void insertSelective(ProductStore productStore) {
        productStoreMapper.insertSelective(productStore);
    }

    public void deleteByProductid(long productid) {
        productStoreMapper.deleteByProductid(productid);
    }


    public List<ProductStore> getByProductid(long productid) {
        return productStoreMapper.getByProductid(productid);
    }


    public void updateById(ProductStore productStore) {
        productStoreMapper.updateByPrimaryKey(productStore);
    }


    public int countByExample(ProductStoreExample example) {
        return productStoreMapper.countByExample(example);
    }


    /**
     * 根据商品id修改库存
     *
     * @param pid
     * @param storenum
     */
    public void updatePdstorenumByPid(long pid, BigDecimal storenum) {
        productStoreMapper.updatePdstorenumByPid(pid, storenum);
    }


    public int countByMemberidAndPdno(long memberid, String pdno) {
        return productStoreMapper.countByMemberidAndPdno(memberid, pdno);
    }


    public ProductStore getByMemberidAndPdno(long memberid, String pdno) {
        return productStoreMapper.getByMemberidAndPdno(memberid, pdno);
    }

    /**
     * 根据商品id 和 商品编号获取商品库存信息
     *
     * @param pdid
     * @param pdno
     * @return
     */
    public ProductStore getByPdidAndPdno(long pdid, String pdno) {
        return productStoreMapper.getByPdidAndPdno(pdid, pdno);
    }


    /**
     * 减库存
     *
     * @param pdid
     * @param pdno
     * @param pdstorenum
     */
    public void decrStoreNumByPdidAndPdno(long pdid, String pdno, BigDecimal pdstorenum) {
        productStoreMapper.decrStoreNumByPdidAndPdno(pdid, pdno, pdstorenum);
    }

    /**
     * 加库存
     *
     * @param pdid
     * @param pdno
     * @param pdstorenum
     */
    public void addStoreNumByPdidAndPdno(long pdid, String pdno, BigDecimal pdstorenum) {
        productStoreMapper.addStoreNumByPdidAndPdno(pdid, pdno, pdstorenum);
    }


    public ProductStore selectByProductidForFastener(long pdid) {
        return productStoreMapper.selectByProductidForFastener(pdid);
    }

    /**
     * 查询某个用户是否在某个仓库下是否存在某个商品
     *
     * @param memberid
     * @param storeid
     * @param pdno
     * @return
     */
    public ProductStore getByStoreidAndPdNo(long memberid, long storeid, String pdno) {
        return productStoreMapper.getByStoreidAndPdNo(memberid, storeid, pdno);
    }


    /**
     * 更新仓库商品信息
     *
     * @param storeid
     * @param pdno
     * @param num
     * @return
     */
    public int updateNumByStoreidAndPdno(long storeid, String pdno, BigDecimal num) {
        return productStoreMapper.updateNumByStoreidAndPdno(storeid, pdno, num);
    }

    public int updateNumByStoreidAndPdno(String pdno, BigDecimal num) {
        List<String> shopSelfSupportIds = Arrays.asList(shopSelfSupportid.split("\\|"));
        String s = "";
        for (int i = 0; i < shopSelfSupportIds.size(); i++) {
            if (i == 0 && !"6700".equals(shopSelfSupportIds.get(i))) {
                s = s + "(" + shopSelfSupportIds.get(i);
            } else if (!"6700".equals(shopSelfSupportIds.get(i))) {
                s = s + "," + shopSelfSupportIds.get(i);
            }
        }
        s = s + ")";
        return productStoreMapper.updateProductStoreNum(pdno, num, s);
    }


    public int updateNumByStoreidAndPdno(Long memberid,String pdno, BigDecimal num) {
        return productStoreMapper.updateProductStoreNum(pdno, num, "("+String.valueOf(memberid)+")");
    }


    public int updateStorenameByStoreid(long storeid, String name) {
        return productStoreMapper.updateStorenameByStoreid(storeid, name);
    }



    public ProductStore getProductStore(long pdid, String pdno, long storeid) {
        return  productStoreMapper.getProductStore(pdid,pdno,storeid);
    }


}
