package project.jinshang.mod_company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.common.utils.ProductCategoryUtils;
import project.jinshang.mod_company.SellerCompanyInfoMapper;
import project.jinshang.mod_company.bean.SellerCompanyInfo;
import project.jinshang.mod_company.bean.SellerCompanyInfoExample;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_product.bean.Categories;
import project.jinshang.mod_product.service.CategoriesService;
import project.jinshang.mod_shop.ShopsMapper;
import project.jinshang.mod_shop.bean.Shops;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * create : wyh
 * date : 2017/11/4
 */

@Service
public class SellerCompanyInfoService {

    @Autowired
    private SellerCompanyInfoMapper sellerCompanyInfoMapper;

    @Autowired
    private CategoriesService categoriesService;



    public  int countByExample(SellerCompanyInfoExample example){
        return  sellerCompanyInfoMapper.countByExample(example);
    }


    /**
     * 申请开店
     * @param info
     */
    public  void applyToShop(SellerCompanyInfo info){
        info.setCreatedate(new Date());
        info.setValidate((short)0);
        sellerCompanyInfoMapper.insertSelective(info);
    }


    public  void  updateSellerCompanyInfo(String alipayname,String alipayno,String wxname,String wxno){
        SellerCompanyInfoExample sellerCompanyInfoExample=new SellerCompanyInfoExample();
        SellerCompanyInfo info=new SellerCompanyInfo();
        info.setAlipayname(alipayname);
        info.setAlipayno(alipayno);
        info.setWxname(wxname);
        info.setWxno(wxno);
        sellerCompanyInfoMapper.updateByExampleSelective(info, sellerCompanyInfoExample);
    }

    /**
     * 根据memberid获取卖家公司信息
     * @param memberId
     * @return
     */
    public  SellerCompanyInfo getSellerCompanyByMemberid(long memberId){
        return  sellerCompanyInfoMapper.getSellerCompanyByMemberid(memberId);
    }


    public  SellerCompanyInfo getById(long id){
        return  sellerCompanyInfoMapper.selectByPrimaryKey(id);
    }


    public  void  updateByPrimaryKeySelective(SellerCompanyInfo info){
        sellerCompanyInfoMapper.updateByPrimaryKeySelective(info);
    }


    public  SellerCompanyInfo getByCompanyName(String companyname){
        return  sellerCompanyInfoMapper.getByCompanyName(companyname);
    }

    public  void  delById(long id){
        sellerCompanyInfoMapper.deleteByPrimaryKey(id);
    }



    /**
     * 获取卖家可发布的产品分类
     * @param memberid
     * @return
     */
    public List<Categories> getPushCategory(Long memberid) {
        SellerCompanyInfo sellerCompanyInfo = this.getSellerCompanyByMemberid(memberid);
        if (sellerCompanyInfo == null) {
            throw  new RuntimeException("卖家公司为空");
        }

        Long[] categoryidArr = (Long[]) sellerCompanyInfo.getBusinesscategory();
        if (categoryidArr == null || categoryidArr.length <= 0) {
            throw  new RuntimeException("店铺可经营的类别为空");
        }

        List<Categories> allCategory = categoriesService.getAll();

        List<Categories> publishList = new ArrayList<>();

        List<Categories> productCategoryList = allCategory;
        for (Long cateid : categoryidArr) {
            if (cateid != null && cateid != 0) {
                Categories parCate = ProductCategoryUtils.getByParendId(productCategoryList, cateid);
                if (parCate != null) {
                    parCate.setList(ProductCategoryUtils.getChildsManyGroupNoRate(productCategoryList, cateid));
                    publishList.add(parCate);
                }
            } else {
                publishList = productCategoryList;
                break;
            }
        }

        return  publishList;
    }

    /**
     *根据给定的条件查询卖家分公司信息
     * @author xiazy
     * @date  2018/6/6 11:34
     * @param example
     * @return java.util.List<project.jinshang.mod_company.bean.SellerCompanyInfo>
     */
    public List<SellerCompanyInfo> selectByExample(SellerCompanyInfoExample example){
        return sellerCompanyInfoMapper.selectByExample(example);
    }


}
