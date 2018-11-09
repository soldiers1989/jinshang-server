package project.jinshang.mod_smallprog.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_smallprog.mapper.WxSmallProgMapper;

import java.util.List;
import java.util.Map;


/**
 * 微信小程序service
 *
 * @author xiazy
 * @create 2018-08-02 11:52
 **/
@Service
public class WxSmallProgService {
    @Autowired
    private WxSmallProgMapper wxSmallProgMapper;
    private Logger logger= LoggerFactory.getLogger(WxSmallProgService.class);


    public List<Map<String,Object>> getRankProductList(int count){
        List<Map<String,Object>>  result=wxSmallProgMapper.getRankProductList(count);
        return result;
    }
}
