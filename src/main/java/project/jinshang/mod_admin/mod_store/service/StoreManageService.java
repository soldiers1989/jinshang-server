package project.jinshang.mod_admin.mod_store.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_admin.mod_store.bean.StoreManageQueryDto;
import project.jinshang.mod_company.StoreMapper;
import project.jinshang.mod_company.service.StoreService;

import java.util.List;
import java.util.Map;

/**
 * create : wyh
 * date : 2018/1/3
 */

@Service
public class StoreManageService {

    @Autowired
    private StoreMapper storeMapper;

    public PageInfo searchManageList(StoreManageQueryDto queryDto, int pageNo, int pageSize){
        PageHelper.startPage(pageNo,pageSize);
        List<Map<String,Object>> list =  storeMapper.searchManageList(queryDto);

        PageInfo pageInfo =  new PageInfo(list);
        return  pageInfo;
    }


    /**
     * 导出excel
     * @param queryDto
     * @return
     */
    public List<Map<String,Object>> searchManageListForExcel(StoreManageQueryDto queryDto){
        List<Map<String,Object>> list =  storeMapper.searchManageList(queryDto);
        return  list;
    }



}
