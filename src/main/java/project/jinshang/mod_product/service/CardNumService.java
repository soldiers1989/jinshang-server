package project.jinshang.mod_product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_product.CardNumMapper;
import project.jinshang.mod_product.bean.CardNum;

import java.util.List;

/**
 * create : wyh
 * date : 2017/11/9
 */

@Service
public class CardNumService {

    @Autowired
    private CardNumMapper cardNumMapper;

    public  void  add(CardNum cardNum){
        cardNumMapper.insert(cardNum);
    }

    public  void  update(CardNum cardNum){
        cardNumMapper.updateByPrimaryKey(cardNum);
    }


    public  void  delete(long id){
        cardNumMapper.deleteByPrimaryKey(id);
    }



    public  CardNum getById(long id){
        return  cardNumMapper.selectByPrimaryKey(id);
    }


    /**
     * 根据材质id和牌号名查询
     * @param materialid
     * @param name
     * @return
     */
    public  CardNum getByMaterialIdAndName(long materialid,String name){
        return  cardNumMapper.getByMaterialIdAndName(materialid,name);
    }


    /**
     * 查询该材质id下的所有牌号
     * @param materialid
     * @return
     */
    public List<CardNum> getListByMaterialid(long materialid){
        return  cardNumMapper.getListByMaterialid(materialid);
    }


}
