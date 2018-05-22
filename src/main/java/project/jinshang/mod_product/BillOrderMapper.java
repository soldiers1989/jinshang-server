package project.jinshang.mod_product;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import project.jinshang.mod_product.bean.BillOrder;
import project.jinshang.mod_product.bean.BillOrderExample;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

public interface BillOrderMapper {
    int countByExample(BillOrderExample example);

    int deleteByExample(BillOrderExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BillOrder record);

    int insertSelective(BillOrder record);

    List<BillOrder> selectByExample(BillOrderExample example);

    BillOrder selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BillOrder record, @Param("example") BillOrderExample example);

    int updateByExample(@Param("record") BillOrder record, @Param("example") BillOrderExample example);

    int updateByPrimaryKeySelective(BillOrder record);

    int updateByPrimaryKey(BillOrder record);

    /**
     * 批量插入
     * @param list
     */
    @InsertProvider(type = BillOrderMapper.BillOrderProvider.class, method = "insertAll")
    void insertAll(List<BillOrder> list);

    public class BillOrderProvider {
        public String insertAll(Map map) {
            List<BillOrder> list = (List<BillOrder>) map.get("list");
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO billorder ");
            sb.append("(billrecordid,orderid) ");
            sb.append("VALUES ");
            MessageFormat mf = new MessageFormat("(#'{'list[{0}].billrecordid},#'{'list[{0}].orderid})");
            for (int i = 0; i < list.size(); i++) {
                sb.append(mf.format(new Object[]{i}));
                if (i < list.size() - 1) {
                    sb.append(",");
                }
            }
            return sb.toString();
        }
    }
}