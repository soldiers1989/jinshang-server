package project.jinshang.mod_admin.mod_showcategory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_admin.mod_showcategory.ShowCatedetailMapper;
import project.jinshang.mod_admin.mod_showcategory.bean.ShowCatedetail;
import project.jinshang.mod_admin.mod_showcategory.bean.ShowCatedetailExample;

import java.util.List;

/**
 * create : wyh
 * date : 2018/2/1
 */

@Service
public class ShowCategoryDetailService {

    @Autowired
    private ShowCatedetailMapper showCatedetailMapper;

    /**
     * 添加
     * @param showCatedetail
     */
    public  void insertSelective(ShowCatedetail showCatedetail){
        showCatedetailMapper.insertSelective(showCatedetail);
    }

    /**
     * 根据展示类目id删除
     * @param showid
     */
    public  void delByShowId(Long showid){
        ShowCatedetailExample example =  new ShowCatedetailExample();
        example.createCriteria().andShowidEqualTo(showid);
        showCatedetailMapper.deleteByExample(example);
    }


    /**
     * 根据展示类目id查询
     */
    public List<ShowCatedetail> getByShowId(Long showid){
        ShowCatedetailExample example =  new ShowCatedetailExample();
        example.createCriteria().andShowidEqualTo(showid);
        example.setOrderByClause(" id asc ");
        return  showCatedetailMapper.selectByExample(example);
    }



}
