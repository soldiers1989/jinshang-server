package project.jinshang.mod_product.service;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_product.ShippingTemplateGroupMapper;
import project.jinshang.mod_product.bean.ShippingTemplateGroup;
import project.jinshang.mod_product.bean.ShippingTemplateGroupExample;

import java.util.ArrayList;
import java.util.List;

/**
 * 卖家运费模板service
 *
 * @author xiazy
 * @create 2018-07-17 18:44
 **/
@Service
public class ShippingTemplateGroupService {
    @Autowired
    private ShippingTemplateGroupMapper shippingTemplateGroupMapper;

    public List<ShippingTemplateGroup> listShippingTemplateGroupByMemberid(int pageNo, int pageSize, long memberid){
        if(pageNo==-1){
            PageHelper.startPage(pageNo,0,true,null,true);
        }else {
            PageHelper.startPage(pageNo,pageSize);
        }
        ShippingTemplateGroupExample example = new ShippingTemplateGroupExample();
        ShippingTemplateGroupExample.Criteria criteria = example.createCriteria();
        criteria.andMemberidEqualTo(memberid);
        List<ShippingTemplateGroup> list = shippingTemplateGroupMapper.selectByExample(example);
        return  list;
    }



    public void addShippingTemplateGroup(ShippingTemplateGroup shippingTemplateGroup){
        shippingTemplateGroupMapper.insertSelective(shippingTemplateGroup);
    }


    public ShippingTemplateGroup getShippingTemplateGroup(long id){
        return shippingTemplateGroupMapper.selectByPrimaryKey(id);
    }

    public List<ShippingTemplateGroup> getShippingTemplateGroupByName(String stgName,long memberId){
//        List<ShippingTemplateGroup> shippingTemplateGroupList=new ArrayList<>();
        ShippingTemplateGroupExample example=new ShippingTemplateGroupExample();
        ShippingTemplateGroupExample.Criteria criteria=example.createCriteria();
        criteria.andNameEqualTo(stgName);
        criteria.andMemberidEqualTo(memberId);
        return shippingTemplateGroupMapper.selectByExample(example);
    }


    public Long getGroupIdByMemberidAndName(long memberid,String name){
        return shippingTemplateGroupMapper.getGroupIdByMemberidAndName(memberid,name);
    }


    public void updateShippingTemplateGroup(ShippingTemplateGroup shippingTemplateGroup){
        shippingTemplateGroupMapper.updateByPrimaryKey(shippingTemplateGroup);
    }

    public void delShippingTemplateGroup(long id){
        shippingTemplateGroupMapper.deleteByPrimaryKey(id);
    }


    /**
     * 查询是否支持指定的发货方式
     * @param shippingTemplateGroup
     * @return
     */
    public boolean supportSend(ShippingTemplateGroup shippingTemplateGroup,String type){
        List<String> shippintMethod = this.getSupportList(shippingTemplateGroup);
        if(!shippintMethod.contains(type)){
            return false;
        }
        return true;
    }

    public List<String> getSupportList(ShippingTemplateGroup shippingTemplateGroup){
        List<String> shippintMethod = new ArrayList<>();
        if (shippingTemplateGroup.getSelflifting() != null && shippingTemplateGroup.getSelflifting() ) {
            shippintMethod.add("自提");
        }
        if (shippingTemplateGroup.getSfarrivepay() != null && shippingTemplateGroup.getSfarrivepay()) {
            shippintMethod.add("顺丰到付");
        }
        if (shippingTemplateGroup.getExpreselflifting() != null && shippingTemplateGroup.getExpreselflifting() && shippingTemplateGroup.getExpreselftemp()>0) {
            shippintMethod.add("物流自提");
        }
        if (shippingTemplateGroup.getExprehousehoid() != null && shippingTemplateGroup.getExprehousehoid() && shippingTemplateGroup.getExprehousetemp() >0) {
            shippintMethod.add("物流到户");
        }
        if (shippingTemplateGroup.getExpresspay() != null && shippingTemplateGroup.getExpresspay() && shippingTemplateGroup.getExpretemp()>0) {
            shippintMethod.add("快递");
        }

        return shippintMethod;
    }


    public Long getTemplateid(ShippingTemplateGroup shippingTemplateGroup,String type){
        if("物流自提".equals(type)){
            return shippingTemplateGroup.getExpreselftemp();
        }else if("物流到户".equals(type)){
            return shippingTemplateGroup.getExprehousetemp();
        }else if("快递".equals(type)){
            return shippingTemplateGroup.getExpretemp();
        }
        return (long)-1;
    }


    /**
     * 查询是否有运费集合还使用该运费模板
     * @param templatesId
     * @return
     */
    public int getCountUsedShippingTemplates(Long templatesId){
        return  shippingTemplateGroupMapper.getCountUsedShippingTemplates(templatesId);
    }

}
