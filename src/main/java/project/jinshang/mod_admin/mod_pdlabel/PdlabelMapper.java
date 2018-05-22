package project.jinshang.mod_admin.mod_pdlabel;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import project.jinshang.mod_admin.mod_pdlabel.bean.Pdlabel;
import project.jinshang.mod_admin.mod_pdlabel.bean.PdlabelExample;

public interface PdlabelMapper {
    int countByExample(PdlabelExample example);

    int deleteByPrimaryKey(long id);

    int insert(Pdlabel record);

    int insertSelective(Pdlabel record);

    List<Pdlabel> selectByExample(PdlabelExample example);

    Pdlabel selectByPrimaryKey(long id);

    int updateByExampleSelective(@Param("record") Pdlabel record, @Param("example") PdlabelExample example);

    int updateByExample(@Param("record") Pdlabel record, @Param("example") PdlabelExample example);

    int updateByPrimaryKeySelective(Pdlabel record);

    int updateByPrimaryKey(Pdlabel record);
}