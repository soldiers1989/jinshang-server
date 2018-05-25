package project.jinshang.mod_fx.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_fx.FxmoneylistMapper;
import project.jinshang.mod_fx.bean.Fxmoneylist;

import java.util.List;
import java.util.Map;

/**
 * create : zzy
 * date : 2018/05/05
 */
@Service
public class FxMoneyListService {
    @Autowired
    private FxmoneylistMapper fxmoneylistMapper;

    public PageInfo findCommisionList(long memberid, int pageNo, int pageSize) {
        if (pageNo != -1) {
            PageHelper.startPage(pageNo, pageSize);
        }
        List<Map<String, Object>> list = fxmoneylistMapper.findCommisionList(memberid);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;

    }

    public void insertFxmoneylist(Fxmoneylist fxmoneylist) {
        fxmoneylistMapper.insertSelective(fxmoneylist);
    }

    public Fxmoneylist selectById(Long id) {
        return  fxmoneylistMapper.selectByPrimaryKey(id);
    }
}
