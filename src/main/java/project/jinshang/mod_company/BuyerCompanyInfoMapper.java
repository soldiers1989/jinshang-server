package project.jinshang.mod_company;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.Cacheable;
import project.jinshang.mod_company.bean.BuyerCompanyInfo;
import project.jinshang.mod_company.bean.BuyerCompanyInfoExample;


public interface BuyerCompanyInfoMapper {
    int countByExample(BuyerCompanyInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BuyerCompanyInfo record);

    int insertSelective(BuyerCompanyInfo record);

    List<BuyerCompanyInfo> selectByExample(BuyerCompanyInfoExample example);

    BuyerCompanyInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BuyerCompanyInfo record, @Param("example") BuyerCompanyInfoExample example);

    int updateByExample(@Param("record") BuyerCompanyInfo record, @Param("example") BuyerCompanyInfoExample example);

    int updateByPrimaryKeySelective(BuyerCompanyInfo record);

    int updateByPrimaryKey(BuyerCompanyInfo record);

	@Select("select * from buyerCompanyInfo where memberId=#{memberId}")
    BuyerCompanyInfo getBuyerCompanyInfoByMemberId(@Param("memberId") long memberId);

}