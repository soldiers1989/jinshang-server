package project.jinshang.mod_bankaccount;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import project.jinshang.mod_bankaccount.bean.BankAccount;
import project.jinshang.mod_bankaccount.bean.BankAccountExample;


public interface BankAccountMapper {
    int countByExample(BankAccountExample example);

    int deleteByPrimaryKey(long id);

    int insert(BankAccount record);

    int insertSelective(BankAccount record);

    List<BankAccount> selectByExample(BankAccountExample example);

    BankAccount selectByPrimaryKey(long id);

    int updateByExampleSelective(@Param("record") BankAccount record, @Param("example") BankAccountExample example);

    int updateByExample(@Param("record") BankAccount record, @Param("example") BankAccountExample example);

    int updateByPrimaryKeySelective(BankAccount record);

    int updateByPrimaryKey(BankAccount record);


    @Update("update bankAccount set isdefault=#{isdefault} where memberid=#{memberid} and batype=#{batype} ")
    int updateIsdefault(@Param("isdefault") short isdefault,@Param("batype") short batype,@Param("memberid") long memberid);

}