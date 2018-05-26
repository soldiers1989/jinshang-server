package project.jinshang.mod_member.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_member.bean.ApiRecord;
import project.jinshang.mod_member.bean.ApiRecordExample;
import project.jinshang.mod_member.bean.dto.ApiRecordQueryDto;
import project.jinshang.mod_member.mapper.ApiRecordMapper;

import java.util.List;

/**
 * Api对接Service
 *
 * @author xiazy
 * @create 2018-05-25 11:51
 **/
@Service
public class ApiRecordService {
    @Autowired
    public ApiRecordMapper apiRecordMapper;

    /**
     *根据条件，查询api对接记录
     * @param pageNo
     * @param pageSize
     * @param dto
     * @return
     */
    public PageInfo apiRecordlist(int pageNo, int pageSize, ApiRecordQueryDto dto){
        PageHelper.startPage(pageNo,pageSize);
        ApiRecordExample apiRecordExample=new ApiRecordExample();
        ApiRecordExample.Criteria criteria=apiRecordExample.createCriteria();
        if(dto.getStartCreateTime()!=null){
            criteria.andCreatetimeGreaterThanOrEqualTo(dto.getStartCreateTime());
        }
        if (dto.getEndCreateTime()!=null){
            criteria.andCreatetimeLessThanOrEqualTo(dto.getEndCreateTime());
        }
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(dto.getAppId())){
            criteria.andAppidEqualTo(dto.getAppId());
        }
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(dto.getApiCode())){
            criteria.andApicodeEqualTo(dto.getApiCode());
        }
        List<ApiRecord> apiRecordList=apiRecordMapper.selectByExample(apiRecordExample);
        PageInfo pageInfo=new PageInfo(apiRecordList);
        return pageInfo;
    }

}
