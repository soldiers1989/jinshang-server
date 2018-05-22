package project.jinshang.mod_sale_rank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_product.OrderProductMapper;
import project.jinshang.mod_product.bean.OrderProduct;
import project.jinshang.mod_sale_rank.bean.SaleRankModel;

import java.util.List;

@Service
public class SaleRankService {

    @Autowired
    private OrderProductMapper orderProductMapper;


    /**
     * 获取螺栓的排行榜
     * @return
     */
    public List<SaleRankModel> getLuoSuanRank(){
        return orderProductMapper.getLuoSuanRank();
    }

    /**
     * 获取螺母的排行榜
     * @return
     */
    public List<SaleRankModel> getLuoMuRank(){
        return orderProductMapper.getLuoMuRank();
    }

    /**
     * 获取螺钉的排行榜
     * @return
     */
    public List<SaleRankModel> getLuoDingRank(){
        return orderProductMapper.getLuoDingRank();
    }

    /**
     * 获取挡圈的排行榜
     * @return
     */
    public List<SaleRankModel> getDangQuanRank(){
        return orderProductMapper.getDangQuanRank();
    }
}



