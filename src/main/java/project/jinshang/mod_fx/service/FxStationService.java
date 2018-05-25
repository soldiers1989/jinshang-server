package project.jinshang.mod_fx.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_fx.FxstationMapper;
import project.jinshang.mod_fx.bean.Fxstation;

/**
 * create : zzy
 * date : 2018/05/05
 */
@Service
public class FxStationService {

    @Autowired
    private FxstationMapper fxstationMapper;

    public Fxstation getStationInfoById(Long id) {
        return fxstationMapper.selectByPrimaryKey(id);
    }

    public void updateStationInfo(Fxstation fxstation) {
        fxstationMapper.updateByPrimaryKeySelective(fxstation);
    }


    public Fxstation getStationInfo() {
        return  fxstationMapper.getStationInfo();
    }
}
