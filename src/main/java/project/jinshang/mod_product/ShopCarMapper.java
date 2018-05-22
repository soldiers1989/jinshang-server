package project.jinshang.mod_product;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import project.jinshang.mod_product.bean.ShopCar;
import project.jinshang.mod_product.bean.ShopCarExample;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

public interface ShopCarMapper {
    int countByExample(ShopCarExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ShopCar record);

    int insertSelective(ShopCar record);

    List<ShopCar> selectByExample(ShopCarExample example);

    ShopCar selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ShopCar record, @Param("example") ShopCarExample example);

    int updateByExample(@Param("record") ShopCar record, @Param("example") ShopCarExample example);

    int updateByPrimaryKeySelective(ShopCar record);

    int updateByPrimaryKey(ShopCar record);

    /**
     * 加载购物车信息
     *
     * @param memberid
     * @return
     */
    @Select("SELECT sc.*,ps.storename,ps.pdstorenum,ps.startnum,ps.minplus,pi.productname," +
            "pi.material,pi.cardnum,pi.brand,pi.packagetype,pi.tag,pi.pdpicture,pi.stand,br.pic,pi.level3,pi.selfsupport " +
            "FROM shopcar sc LEFT JOIN productstore ps " +
            "on sc.pdid=ps.pdid and sc.storeid=ps.storeid and sc.pdno=ps.pdno " +
            "LEFT JOIN productinfo pi on pi.\"id\"=sc.pdid " +
            "LEFT JOIN brand br on pi.brandid=br.\"id\" " +
            "where sc.memberid=#{memberid} order by sc.id desc")
    public List<Map<String, Object>> loadShopCar(@Param("memberid") Long memberid);

    /**
     * 加载订单确认信息
     *
     * @param shopcarids
     * @return
     */
    @Select("SELECT sc.*, ps.storename,ps.pdstorenum,pi.productname,pi.material,pi.cardnum,pi.weight,pi.packagetype," +
            "pi.brand,pi.tag,pi.pdpicture,ps.freightmode,pi.memberid,pi.producttype,br.pic,pi.selfsupport FROM " +
            "shopcar sc LEFT JOIN productstore ps ON sc.pdid = ps.pdid and sc.pdno=ps.pdno AND sc.storeid = ps.storeid " +
            "LEFT JOIN productinfo pi ON pi.\"id\" = sc.pdid LEFT JOIN brand br on pi.brandid=br.\"id\" WHERE sc.\"id\" in(${shopcarids}) and sc.memberid=#{memberid}")
    public List<Map<String, Object>> loadSelectProduct(@Param("shopcarids") String shopcarids, @Param("memberid") Long memberid);

    /**
     * 加载购物车
     *
     * @param ids
     * @return
     */
    @Select("SELECT sc.* FROM shopcar sc  WHERE sc.\"id\" in(${ids})")
    public List<ShopCar> loadSelectShopCar(@Param("ids") String ids);


    /**
     * 批量删除
     *
     * @param list
     */
    @DeleteProvider(type = ShopCarMapper.ShopCarProvider.class, method = "deleteAll")
    void deleteAll(List<Long> list);

    public class ShopCarProvider {
        public String deleteAll(Map map) {
            List<Long> list = (List<Long>) map.get("list");
            StringBuilder sb = new StringBuilder();
            sb.append("delete from ShopCar ");
            sb.append("where id in (");
            MessageFormat mf = new MessageFormat("#'{'list[{0}]}");
            for (int i = 0; i < list.size(); i++) {
                sb.append(mf.format(new Object[]{i}));
                if (i < list.size() - 1) {
                    sb.append(",");
                }
            }
            sb.append(")");
            System.out.println(sb.toString());
            return sb.toString();
        }
    }

  /*     public static void main(String[] args) {

        List<Long> list = new ArrayList<Long>();
           list.add(5l);
           list.add(6l);

        Map<String,List<Long>> map = new HashMap<String,List<Long>>();

        map.put("list",list);

           ShopCarProvider shopCarProvider = new ShopCarProvider();
        String sql = shopCarProvider.deleteAll(map);

        System.out.println(sql);
    }*/
}