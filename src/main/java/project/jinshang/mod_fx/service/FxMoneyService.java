package project.jinshang.mod_fx.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_fx.FxmoneyMapper;
import project.jinshang.mod_fx.bean.Fxmoney;

/**
 * create : zzy
 * date : 2018/05/05
 */
@Service
public class FxMoneyService {
    @Autowired
    private FxmoneyMapper fxmoneyMapper;

    public Fxmoney getByMemberId(Long memberid) {
         return fxmoneyMapper.getByCMemberId(memberid);
    }

    public void updateByFxmoney(Fxmoney fxmoney) {
        fxmoneyMapper.updateByPrimaryKeySelective(fxmoney);
    }

    public Fxmoney getByCMemberId(Long cmemberid) {
        return  fxmoneyMapper.getByCMemberId(cmemberid);
    }

    public void insertFxmoney(Fxmoney fxmoney) {
        fxmoneyMapper.insertSelective(fxmoney);
    }
}
