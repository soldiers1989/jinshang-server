package project.jinshang.mod_admin.mod_count.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_admin.mod_count.SearchKeyRecordMapper;
import project.jinshang.mod_admin.mod_count.bean.SearchKeyQueryParam;
import project.jinshang.mod_admin.mod_count.bean.SearchKeyRecord;
import project.jinshang.mod_admin.mod_count.bean.SearchKeyStatistcModel;

import java.util.List;
import java.util.Map;

@Service
public class SearchKeyRecordService {
    @Autowired
    private SearchKeyRecordMapper searchKeyRecordMapper;

    public void insertSearchKeyRecord(SearchKeyRecord searchKeyRecord) {
        searchKeyRecordMapper.insertSelective(searchKeyRecord);
    }

    /**
     * 统计相同关键词 搜索次数
     * @param searchKeyQueryParam
     * @return
     */
    public List<SearchKeyStatistcModel> searchcountStatistic(SearchKeyQueryParam searchKeyQueryParam) {
        return  searchKeyRecordMapper.searchcountStatistic(searchKeyQueryParam);
    }


    public PageInfo getListByPage(int pageNo, int pageSize,SearchKeyQueryParam searchKeyQueryParam) {
        PageHelper.startPage(pageNo, pageSize);
        List<Map<String,Object>> list = searchKeyRecordMapper.selectByObject(searchKeyQueryParam);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }


}
