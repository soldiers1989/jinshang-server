package project.jinshang.scheduled.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import project.jinshang.mod_product.bean.ProductInfo;

import java.util.List;

@Mapper
public interface BaiduSeoMapper {

    @Select("select * from productinfo where pdstate=4 order by updatetime DESC")
    public List<ProductInfo> getPushSeoProd();

}
