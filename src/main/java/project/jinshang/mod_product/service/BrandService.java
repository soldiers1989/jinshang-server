package project.jinshang.mod_product.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_product.BrandMapper;
import project.jinshang.mod_product.bean.Brand;
import project.jinshang.mod_product.bean.BrandExample;

import  project.jinshang.mod_product.bean.BrandExample.*;

import java.util.List;

/**
 * create : wyh
 * date : 2017/11/8
 */
@Service
public class BrandService {

    @Autowired
    private BrandMapper brandMapper;

    public Brand getByName(String name){
        return  brandMapper.getByName(name);
    }



    public Brand getByNameAndCateid(String name,Long catid){
        return  brandMapper.getByNameAndCateid(name,catid);
    }



    public  void  addBrand(Brand brand){
        brandMapper.insert(brand);
    }


    public  int  countByExample(BrandExample example){
        return  brandMapper.countByExample(example);
    }

    public  Brand getById(Long id){
        return  brandMapper.selectByPrimaryKey(id);
    }


    public  void  updateByPrimaryKey(Brand brand){
        brandMapper.updateByPrimaryKey(brand);
    }


    public  void  deleteById(Long id){
        brandMapper.deleteByPrimaryKey(id);
    }


    public PageInfo getListByPage(int pageNo,int pageSize,Brand brand){
        PageHelper.startPage(pageNo,pageSize);
        BrandExample example =  new BrandExample();

        Criteria criterion = example.createCriteria();

        if(StringUtils.hasText(brand.getName())){
            criterion.andNameLike("%"+brand.getName()+"%");
        }

        if(brand.getAuditstate() != null && brand.getAuditstate()>=0){
            criterion.andAuditstateEqualTo(brand.getAuditstate());
        }

        if(brand.getMemberid() != null && brand.getMemberid()>0){
            criterion.andMemberidEqualTo(brand.getMemberid());
        }


        if(brand.getCategoryid() != null && brand.getCategoryid()>0){
            criterion.andCategoryidEqualTo(brand.getCategoryid());
        }



        List<Brand> list = brandMapper.selectByExample(example);

        PageInfo pageInfo = new PageInfo(list);
        return  pageInfo;
    }


    public  Brand getBrandByIdsAndName(String ids,String name){
        return  brandMapper.getBrandByIdsAndName(ids,name);
    }

    /**
     * 根据分类和审核状态查询
     * @param cateid
     * @param auditstate
     * @return
     */
    public  List<Brand> getByCateid(Long cateid,Short auditstate){
        return  brandMapper.getByCateid(cateid,auditstate);
    }
}
