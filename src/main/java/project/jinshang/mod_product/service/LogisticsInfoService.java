package project.jinshang.mod_product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_product.bean.LogisticsInfo;
import project.jinshang.mod_product.bean.dto.LogisticsInfoDto;
import project.jinshang.mod_product.LogisticsInfoMapper;

import java.util.List;

@Service
public class LogisticsInfoService {
    @Autowired
    private LogisticsInfoMapper logisticsInfoMapper;

    public void insertLogisticsInfo(LogisticsInfo logisticsInfo) {
        logisticsInfoMapper.insertSelective(logisticsInfo);
    }

    public List<LogisticsInfoDto> selectByOrderNo(String orderno) {
        return  logisticsInfoMapper.selectByOrderNo(orderno);
    }

    public LogisticsInfo selectLogisticsInfoByOrderNo(String orderno) {
        return logisticsInfoMapper.selectLogisticsInfoByOrderNo(orderno);
    }

    /**
     * 根据orderno查询物流信息 取发货时间最新的那条
     * @param orderno
     * @return
     */
    public LogisticsInfo selectLogisticsInfoByOrderNoAndTime(String orderno) {
        return  logisticsInfoMapper.selectLogisticsInfoByOrderNoAndTime(orderno);
    }

    public void updateLogisticsInfo(LogisticsInfo logisticsInfo) {
        logisticsInfoMapper.updateByPrimaryKeySelective(logisticsInfo);
    }
}
