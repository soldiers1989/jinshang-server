package project.jinshang.mod_sellerbill.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_sellerbill.bean.SellerBill;
import project.jinshang.mod_sellerbill.bean.SellerBillQuery;
import project.jinshang.mod_sellerbill.mapper.SellerBillMapper;

@Service
public class SellerBillServices {

    @Autowired
    private SellerBillMapper sellerBillMapper;

    @Autowired
    private  SellerBillOrderService sellerBillOrderService;



    public int insert(SellerBill sellerBill){
       return sellerBillMapper.insertSelective(sellerBill);
    }

    public void deleteById(long id){
        sellerBillMapper.deleteByPrimaryKey(id);
    }

    public  void  update(SellerBill sellerBill){
        sellerBillMapper.updateByPrimaryKeySelective(sellerBill);
    }


    public  SellerBill getById(Long id){
        return  sellerBillMapper.selectByPrimaryKey(id);
    }


    public PageInfo getSellerBillByPage(SellerBillQuery query,int pageNo,int pageSize){
        PageHelper.startPage(pageNo,pageSize);

        if(StringUtils.hasText(query.getSellercompanyname())){
            query.setSellercompanyname("%"+query.getSellercompanyname()+"%");
        }

        if(StringUtils.hasText(query.getOrderno())){
            query.setOrderno("%"+query.getOrderno()+"%");
        }

        return  new PageInfo(sellerBillMapper.getSellerBill(query));
    }


}
