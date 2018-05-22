package project.jinshang.mod_product.service;


import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
 * create : wyh
 * date : 2017/11/15
 */
public class ProductInfoProvider {


    public  String ProductInfoWithRate(@Param("productname") String productname,
                                       @Param("brand") String brand,
                                       @Param("level1id") int level1id,
                                       @Param("level2id") int level2id,
                                       @Param("level3id") int level3id,
                                       @Param("material") String material,
                                       @Param("cardnum") String cardnum,
                                       @Param("mark") String mark){
        String[] keys = new String[]{"P.*","c.name as categoryname","parentid","uprate","goldmemberrate","serverrate","thirdrate","secondrate","firstrate","freerate","businessrate","payrate","servernetrate"};
        SQL sql = new SQL(){{
            SELECT(keys);
            FROM("productinfo P,categories C");
            WHERE("P.level3id=C.id");
            if (productname != null && !"".equals(productname)) WHERE("productname=#{productname}");
            if(brand != null && !"".equals(brand)) WHERE("brand=#{brand}");
            if(level1id != 0) WHERE("level1id=#{level1id}");
            if(level2id != 0) WHERE("level2id=#{level2id}");
            if(level3id != 0) WHERE("level3id=#{level3id}");
            if(material != null && !"".equals(material)) WHERE("material=#{material}");
            if(cardnum != null && !"".equals(cardnum)) WHERE("cardnum=#{cardnum}");
            if(mark != null && !"".equals(mark)) WHERE("mark=#{mark}");

            ORDER_BY("P.id desc");
        }};

        return  sql.toString();
    }

}
