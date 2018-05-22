package project.jinshang.mod_member.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_member.SellerCategoryMapper;
import project.jinshang.mod_member.bean.SellerCategory;
import project.jinshang.mod_member.bean.SellerCategoryExample;
import project.jinshang.mod_member.bean.SellerProductCategory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class SellerCategoryService {

    @Autowired
    private SellerCategoryMapper sellerCategoryMapper;


    public SellerCategory getSellerCategory(Long id){
        return sellerCategoryMapper.selectByPrimaryKey(id);
    }

    public void updateSellerCategory(SellerCategory sellerCategory){
        sellerCategoryMapper.updateByPrimaryKeySelective(sellerCategory);
    }

    public void insertAll(List<SellerCategory> list){

        if(list.size()>1000){
            List newList = list.subList(0,1000);
            List newList2 = list.subList(1000,list.size());
            sellerCategoryMapper.insertAll(newList);
            sellerCategoryMapper.insertAll(newList2);
        }else {
            sellerCategoryMapper.insertAll(list);
        }
    }

    public List<SellerCategory> getAll(Long sellerid){
        SellerCategoryExample sellerCategoryExample = new SellerCategoryExample();
        sellerCategoryExample.createCriteria().andSelleridEqualTo(sellerid);
        return sellerCategoryMapper.selectByExample(sellerCategoryExample);
    }

    public List<SellerProductCategory>  toProdCate(List<SellerCategory> list){
        List<SellerProductCategory> categoryList = new ArrayList<>();
        for(SellerCategory cate : list){
            SellerProductCategory productCategory = new SellerProductCategory();
            BeanUtils.copyProperties(cate,productCategory);
            categoryList.add(productCategory);

        }
        return  categoryList;
    }




}
