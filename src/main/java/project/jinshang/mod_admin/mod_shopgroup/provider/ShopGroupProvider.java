package project.jinshang.mod_admin.mod_shopgroup.provider;

import org.apache.ibatis.annotations.Param;
import project.jinshang.mod_admin.mod_shopgroup.bean.ShopGroup;

/**
 * @author xiazy
 * @create 2018-06-28 16:52
 **/
public class ShopGroupProvider {

    public String getShopGroup(@Param("dto")ShopGroup dto){
        StringBuilder sql=new StringBuilder();
        sql.append("select S.* from shopgroup S where 1=1");
        if (dto.getName()!=null){
            sql.append(" and S.name=#{dto.name} ");
        }
        if (dto.getId()!=null) {
            sql.append(" and S.id=#{dto.id} ");
        }
        sql.append(" order by S.id ASC");
        return sql.toString();
    }
}
