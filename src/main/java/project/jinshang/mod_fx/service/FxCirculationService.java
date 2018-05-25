package project.jinshang.mod_fx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_fx.FxcirculationMapper;
import project.jinshang.mod_fx.bean.Fxcirculation;

/**
 * create : zzy
 * date : 2018/05/05
 */
@Service
public class FxCirculationService {
    @Autowired
    private FxcirculationMapper fxcirculationMapper;

    public void insertFxcirculation(Fxcirculation fxcirculation) {
         fxcirculationMapper.insertSelective(fxcirculation);
    }
}
