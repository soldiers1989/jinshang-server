package project.jinshang.mod_member.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.common.utils.BeanUtils;
import project.jinshang.mod_member.bean.ApiRecord;
import project.jinshang.mod_member.bean.ApiRecordExample;
import project.jinshang.mod_member.bean.dto.ApiRecordQueryDto;
import project.jinshang.mod_member.bean.dto.ApiRecordViewDto;
import project.jinshang.mod_member.mapper.ApiRecordMapper;

import java.lang.reflect.InvocationTargetException;
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


    /**
     * 根据id查询对接记录
     * @param id
     * @return
     */
    public ApiRecordViewDto getApiRecordById(Long id){
        ApiRecord apiRecord=apiRecordMapper.selectByPrimaryKey(id);
        ApiRecordViewDto apiRecordViewDto=new ApiRecordViewDto();
        if (apiRecord!=null){
            apiRecordViewDto.setId(apiRecord.getId());
            apiRecordViewDto.setApiCode(apiRecord.getApicode());
            apiRecordViewDto.setAppId(apiRecord.getAppid());
            apiRecordViewDto.setAppUrl(apiRecord.getAppurl());
            apiRecordViewDto.setContent(apiRecord.getContent());
            apiRecordViewDto.setCreateTime(apiRecord.getCreatetime());
            apiRecordViewDto.setReturnJson(apiRecord.getReturnjson());
        }
        return apiRecordViewDto;
    }

}
