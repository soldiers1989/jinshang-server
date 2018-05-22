package project.jinshang.mod_admin.mod_pdlabel.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_admin.mod_pdlabel.PdlabelMapper;
import project.jinshang.mod_admin.mod_pdlabel.bean.Pdlabel;
import project.jinshang.mod_admin.mod_pdlabel.bean.PdlabelExample;


import java.util.Date;
import java.util.List;

@Service
public class PdlabelService {

    @Autowired
    private PdlabelMapper pdlabelMapper;

    public  void  addPdlabel(Pdlabel pdlabel){
        pdlabel.setCreattime(new Date());
        pdlabelMapper.insertSelective(pdlabel);
    }

    public  void  deletePdlable(long id){

        pdlabelMapper.deleteByPrimaryKey(id);
    }

    public void updatePdlable(Pdlabel pdlabel){
        pdlabel.setCreattime(new Date());
        pdlabelMapper.updateByPrimaryKey(pdlabel);
    }

    public PageInfo list(int pageNo,int pageSize,Pdlabel pdlabel){
        PageHelper.startPage(pageNo,pageSize);

        PdlabelExample example = new PdlabelExample();
        PdlabelExample.Criteria criteria = example.createCriteria();
        if(pdlabel != null){
            if(pdlabel.getLabelname() != null && !pdlabel.getLabelname().equals("")){
                criteria.andLabelnameEqualTo(pdlabel.getLabelname());
            }
        }

        example.setOrderByClause(" id desc ");
        List<Pdlabel> list = pdlabelMapper.selectByExample(example);

        PageInfo pageInfo =  new PageInfo(list);
        return  pageInfo;

    }
}
