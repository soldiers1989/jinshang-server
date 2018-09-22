package project.jinshang.mod_member;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.Cacheable;
import project.jinshang.mod_member.bean.SellerCategory;
import project.jinshang.mod_member.bean.SellerCategoryExample;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

public interface SellerCategoryMapper {
    int countByExample(SellerCategoryExample example);

    int deleteByExample(SellerCategoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SellerCategory record);

    int insertSelective(SellerCategory record);

    List<SellerCategory> selectByExample(SellerCategoryExample example);

    SellerCategory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SellerCategory record, @Param("example") SellerCategoryExample example);

    int updateByExample(@Param("record") SellerCategory record, @Param("example") SellerCategoryExample example);

    int updateByPrimaryKeySelective(SellerCategory record);

    int updateByPrimaryKey(SellerCategory record);

    /**
     * 批量插入
     * @param list
     */
    @InsertProvider(type = SellerCategoryMapper.SellerCategoryProvider.class, method = "insertAll")
    void insertAll(List<SellerCategory> list);

    public class SellerCategoryProvider {
        public String insertAll(Map map) {
            List<SellerCategory> list = (List<SellerCategory>) map.get("list");
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO SellerCategory ");
            sb.append("(categoryid,brokeragerate,name,parentid,title,keywords,description,img,sort,sellerid ) ");
            sb.append("VALUES ");
            MessageFormat mf = new MessageFormat("(#'{'list[{0}].categoryid},#'{'list[{0}].brokeragerate},#'{'list[{0}].name},#'{'list[{0}].parentid},#'{'list[{0}].title},#'{'list[{0}].keywords},#'{'list[{0}].description},#'{'list[{0}].img},#'{'list[{0}].sort},#'{'list[{0}].sellerid})");
            for (int i = 0; i < list.size(); i++) {
                sb.append(mf.format(new Object[]{i}));
                if (i < list.size() - 1) {
                    sb.append(",");
                }
            }

            return sb.toString();
        }
    }


    @Select("select sellerid  from  sellercategory GROUP BY sellerid")
    List<Long> getSellerIdGroupBy();

}