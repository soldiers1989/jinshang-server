package project.jinshang.scheduled.mapper;

import org.apache.ibatis.annotations.*;
import project.jinshang.mod_member.SellerCategoryMapper;
import project.jinshang.mod_member.bean.SellerCategory;
import project.jinshang.mod_product.bean.ShippingTemplateGroup;
import project.jinshang.scheduled.Bean.ProductStoreInfo;
import project.jinshang.scheduled.ProdShipTempTask;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

/**
 * 产品运费合集同步修复
 *
 * @author xiazy
 * @create 2018-07-19 17:43
 **/
@Mapper
public interface ProdShipTempMapper {


    /**
     * 初始化产品表，运费模板合集统一设置为  包邮 -1
     * @author xiazy
     * @date  2018/7/20 15:43
     * @param
     * @return void
     */
    @Update("update productinfo set shippingtemplatesgroup=-1")
    public void  updateProductInfo();

    @Select("select  pc.pdid,pc.freightmode,pi.memberid,pi.id  from  productstore pc inner join productinfo pi on pc.pdid=pi.id where pc.pdid in (select pdid from  productstore where freightmode >0 GROUP BY pdid)")
    List<ProductStoreInfo> getPdidByGroup();


    /**
     * 批量插入
     * @param list
     */
    @InsertProvider(type = ProdShipTempMapper.ShippingTemplateProvider.class, method = "insertAll")
    void insertAll(List<ShippingTemplateGroup> list);


    @UpdateProvider(type=ProdShipTempMapper.ShippingTemplateProvider.class,method = "updateAll")
    void updateAll(List<ProductStoreInfo> list);



    @Update("update productinfo set shippingtemplatesgroup =#{shippingtemplatesgroup} where id=#{id}")
    void updateSingle(@Param("id")long id,@Param("shippingtemplatesgroup")long shippingtemplatesgroup);



    public class ShippingTemplateProvider {
        public String insertAll(Map map) {
            List<ShippingTemplateGroup> list = (List<ShippingTemplateGroup>) map.get("list");
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO shippingtemplatesgroup ");
            sb.append("(memberid,selflifting,sfarrivepay,expresspay,expreselflifting,exprehousehoid,expretemp,expreselftemp,exprehousetemp,name ) ");
            sb.append("VALUES ");
            MessageFormat mf = new MessageFormat("(#'{'list[{0}].memberid},#'{'list[{0}].selflifting},#'{'list[{0}].sfarrivepay},#'{'list[{0}].expresspay},#'{'list[{0}].expreselflifting},#'{'list[{0}].exprehousehoid},#'{'list[{0}].expretemp},#'{'list[{0}].expreselftemp},#'{'list[{0}].exprehousetemp},#'{'list[{0}].name})");
            for (int i = 0; i < list.size(); i++) {
                sb.append(mf.format(new Object[]{i}));
                if (i < list.size() - 1) {
                    sb.append(",");
                }
            }

            return sb.toString();
        }



        public String updateAll(Map map){
            List<ProductStoreInfo> list = (List<ProductStoreInfo>) map.get("list");
            StringBuilder sb = new StringBuilder();
            sb.append("update productinfo set shippingtemplatesgroup=tmp.info from (values ");
            MessageFormat mf = new MessageFormat("(#'{'list[{0}].id},#'{'list[{0}].shippingtemplatesgroup})");
            for (int i = 0; i < list.size(); i++) {
                sb.append(mf.format(new Object[]{i}));
                if (i < list.size() - 1) {
                    sb.append(",");
                }
            }
            sb.append(" )as tmp (id,info) where productinfo.id=tmp.id");

            System.out.println(sb.toString());
            return sb.toString();
        }
    }

}
