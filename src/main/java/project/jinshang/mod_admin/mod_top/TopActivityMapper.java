package project.jinshang.mod_admin.mod_top;

import org.apache.ibatis.annotations.*;
import project.jinshang.mod_admin.mod_top.bean.ProductModel;
import project.jinshang.mod_admin.mod_top.bean.TopActivity;
import project.jinshang.mod_admin.mod_top.bean.TopActivityExample;
import project.jinshang.mod_admin.mod_top.bean.TopActivityProduct;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

public interface TopActivityMapper {
    int countByExample(TopActivityExample example);

    int deleteByExample(TopActivityExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TopActivity record);

    int insertSelective(TopActivity record);

    List<TopActivity> selectByExample(TopActivityExample example);

    TopActivity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TopActivity record, @Param("example") TopActivityExample example);

    int updateByExample(@Param("record") TopActivity record, @Param("example") TopActivityExample example);

    int updateByPrimaryKeySelective(TopActivity record);

    int updateByPrimaryKey(TopActivity record);


    @Select("SELECT tap.id,tap.normalprice,store.pdno," +
            "tap.pdid,info.pdpicture,info.productname,info.brand,info.stand,info.mark,info.material,info.cardnum,info.pdstate,info.selfsupport,tap.state " +
            "FROM limittimestore store LEFT JOIN limittimeprod tap on store.limittimeid=tap.id and store.pdid=tap.pdid " +
            "LEFT JOIN productinfo info ON tap.pdid = info.ID and store.pdid=info.id " +
            "WHERE (tap.state=1 or tap.state=4) and tap.memberid = #{memberid}")
    List<ProductModel> getProductList(Long memberid);

    /**
     * 批量插入活动商品
     * @param list
     */
    @InsertProvider(type = TopActivityMapper.TopActivityProvider.class, method = "insertAll")
    void insertAll(List<TopActivityProduct> list);

    public class TopActivityProvider {
        public String insertAll(Map map) {
            List<TopActivityProduct> list = (List<TopActivityProduct>) map.get("list");
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO topactivityproduct ");
            sb.append("(activityid,topid,activitytype,pdid,pdno) ");
            sb.append("VALUES ");
            MessageFormat mf = new MessageFormat("(#'{'list[{0}].activityid},#'{'list[{0}].topid},#'{'list[{0}].activitytype},#'{'list[{0}].pdid},#'{'list[{0}].pdno})");
            for (int i = 0; i < list.size(); i++) {
                sb.append(mf.format(new Object[]{i}));
                if (i < list.size() - 1) {
                    sb.append(",");
                }
            }
            return sb.toString();
        }
    }

    /**
     * 获取限时购商品
     * @return
     */
    @Select("SELECT " +
            "tap.ID as tapid," +
            "limp.id as limitid," +
            "ps.prodprice," +
            "store.limitprice,"+
            "limp.begintime," +
            "limp.endtime,limp.state,"+
            "store.pdno," +
            "info.producttype,"+
            "info.unit,"+
            "info.id," +
            "info.pdpicture," +
            "info.productname," +
            "info.brand," +
            "info.stand," +
            "info.mark," +
            "info.material," +
            "info.cardnum," +
            "info.pdstate," +
            "info.selfsupport," +
            "sinfo.shopname " +
            "FROM " +
            "topactivityproduct tap " +
            "LEFT JOIN topactivity tat ON tap.topid = tat.ID " +
            "LEFT JOIN limittimeprod limp ON tap.activityid = limp.ID and tap.pdid=limp.pdid " +
            "LEFT JOIN productinfo info ON limp.pdid = info.ID " +
            "LEFT JOIN sellercompanyinfo sinfo ON info.memberid = sinfo.memberid " +
            "LEFT JOIN limittimestore store on limp.ID=store.limittimeid and limp.pdid=store.pdid and tap.pdno=store.pdno " +
            "LEFT JOIN productstore ps on ps.pdid=store.pdid and ps.pdno=store.pdno "+
            "where tap.activitytype=#{activitytype} and tap.topid=#{topid} and (limp.state=4 or (limp.state=1  and   begintime -now() <= '${befotime} hour' ))")
    List<ProductModel> getLimitProductList(@Param("activitytype") Short activitytype,@Param("topid") Long topid,@Param("befotime") int befotime);





    /**
     * 获取限时购商品
     * @return
     */
    @Select("SELECT " +
            "tap.ID as tapid," +
            "limp.id as limitid," +
            "ps.prodprice," +
            "store.limitprice,"+
            "limp.begintime," +
            "limp.endtime,limp.state,"+
            "store.pdno," +
            "info.producttype,"+
            "info.unit,"+
            "info.id," +
            "info.pdpicture," +
            "info.productname," +
            "info.brand," +
            "info.stand," +
            "info.mark," +
            "info.material," +
            "info.cardnum," +
            "info.pdstate," +
            "info.selfsupport," +
            "sinfo.shopname " +
            "FROM " +
            "topactivityproduct tap " +
            "LEFT JOIN topactivity tat ON tap.topid = tat.ID " +
            "LEFT JOIN limittimeprod limp ON tap.activityid = limp.ID and tap.pdid=limp.pdid " +
            "LEFT JOIN productinfo info ON limp.pdid = info.ID " +
            "LEFT JOIN sellercompanyinfo sinfo ON info.memberid = sinfo.memberid " +
            "LEFT JOIN limittimestore store on limp.ID=store.limittimeid and limp.pdid=store.pdid and tap.pdno=store.pdno " +
            "LEFT JOIN productstore ps on ps.pdid=store.pdid and ps.pdno=store.pdno "+
            "where tap.activitytype=#{activitytype} and tap.topid=#{topid}")
    List<ProductModel> getLimitProductList2(@Param("activitytype") Short activitytype,@Param("topid") Long topid);


    @Update("update limittimeprod set state=5 where id=#{param1}")
    void updateLimitTimeProdState(long id);

    /**
     * 加载限时购活动商家
     * @return
     */
    @Select("SELECT DISTINCT(info.memberid),info.shopname from limittimeprod lprod LEFT JOIN sellercompanyinfo info on lprod.memberid=info.memberid")
    List<ProductModel> getActivityMember();

    /**
     * 批量删除
     * @param list
     */
    @DeleteProvider(type = TopActivityMapper.TopActivityProductProvider.class, method = "deleteAll")
    void deleteAll(List<Long> list);

    public class TopActivityProductProvider {
        public String deleteAll(Map map) {
            List<Long> list = (List<Long>) map.get("list");
            StringBuilder sb = new StringBuilder();
            sb.append("delete from TopActivityProduct ");
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
}