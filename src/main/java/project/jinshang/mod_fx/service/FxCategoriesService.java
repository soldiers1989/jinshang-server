package project.jinshang.mod_fx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_fx.FxcategoriesMapper;
import project.jinshang.mod_fx.bean.Fxcategories;
import project.jinshang.mod_fx.bean.FxcategoriesExample;

import java.math.BigDecimal;


/**
 * create : zzy
 * date : 2018/05/05
 */
@Service
public class FxCategoriesService {

    @Autowired
    private FxcategoriesMapper fxcategoriesMapper;

    public Fxcategories getCategoriesInfoById(Long cid) {
        return fxcategoriesMapper.getCategoriesInfoById(cid);
    }

    public void insertFxCategories(Fxcategories fxcategories) {
        fxcategoriesMapper.insertSelective(fxcategories);
    }


    public void updateFxCategories(Long cid, BigDecimal ratio) {
         fxcategoriesMapper.updateFxCategories(cid,ratio);
    }
}
