package project.jinshang.mod_product.service;

import com.github.pagehelper.PageHelper;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_product.ShippingTemplateGroupMapper;
import project.jinshang.mod_product.ShippingTemplatesMapper;
import project.jinshang.mod_product.bean.ShippingTemplateGroup;
import project.jinshang.mod_product.bean.ShippingTemplateGroupExample;
import project.jinshang.mod_product.bean.ShippingTemplates;
import project.jinshang.mod_product.bean.ShippingTemplatesExample;

import java.util.List;

/**
 * create : wyh
 * date : 2017/11/13
 */
@Service
public class ShippingTemplatesService {

    @Autowired
    private ShippingTemplatesMapper shippingTemplatesMapper;
    @Autowired
    private ShippingTemplateGroupMapper shippingTemplateGroupMapper;


    public long add(ShippingTemplates templates){
      return   shippingTemplatesMapper.insert(templates);
    }


    public  ShippingTemplates getById(long id){
        return  shippingTemplatesMapper.selectByPrimaryKey(id);
    }


    public  void  deleteById(long id){
        shippingTemplatesMapper.deleteByPrimaryKey(id);
    }


    public List<ShippingTemplates> listTemplatesByMemberid(long memberid){
        ShippingTemplatesExample example = new ShippingTemplatesExample();
        ShippingTemplatesExample.Criteria criteria = example.createCriteria();
        criteria.andMemberidEqualTo(memberid);
        List<ShippingTemplates> list = shippingTemplatesMapper.selectByExample(example);
        return  list;
    }


    public List<ShippingTemplates>  listTemplates(ShippingTemplatesExample example){
        List<ShippingTemplates> list = shippingTemplatesMapper.selectByExample(example);
        return list;
    }

    public  void update(ShippingTemplates templates){
        shippingTemplatesMapper.updateByPrimaryKey(templates);
    }


    /**
     * 根据模版id 获取完整的信息，包括特殊地区的运费信息（链接areacost查询）
     * @param id
     * @return
     */
    public  ShippingTemplates getFullTemplatesById(long id){
        return  shippingTemplatesMapper.getFullTemplatesById(id);
    }


    public ShippingTemplates getByNameAndMemberid(String name,long memberid){
        return  shippingTemplatesMapper.getByNameAndMemberid(name,memberid);
    }


    public ShippingTemplates getByShopgroupid(long shopgroupid){
        return shippingTemplatesMapper.getByShopgroupid(shopgroupid);
    }

}
