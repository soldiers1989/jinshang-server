package project.jinshang.mod_admin.mod_shopgroup;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AdminAuthorityConst;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_admin.mod_shopgroup.bean.ShopGroup;
import project.jinshang.mod_admin.mod_shopgroup.bean.dto.ShopGroupAndStoreView;
import project.jinshang.mod_admin.mod_shopgroup.service.ShopGroupService;
import project.jinshang.mod_company.bean.Store;

import java.util.ArrayList;
import java.util.List;

/**
 * 商家合单集合
 *
 * @author xiazy
 * @create 2018-06-28 14:52
 **/
@RestController
@RequestMapping("/rest/admin/shopgroup")
@Api(tags = "后台管理-商家合单集合管理模块")
@SessionAttributes({AppConstant.ADMIN_SESSION_NAME})
@Transactional(rollbackFor = Exception.class)
public class ShopGroupRestAction {

    @Autowired
    private ShopGroupService shopGroupService;
    private static final  int CONST_ONE=1;


    @RequestMapping(value = "/getShopGroupList",method = RequestMethod.POST)
    @ApiOperation(value = "获取商家合单集合的列表")
    @PreAuthorize("hasAuthority('"+ AdminAuthorityConst.ORDERCOLLECTIONS+"')")
    public PageRet getShopGroupList(ShopGroup shopGroup,
                                    @RequestParam(required = true,defaultValue = "1") int pageNo,
                                    @RequestParam(required = true,defaultValue = "20") int pageSize, Model model){
        PageRet pageRet=new PageRet();
        pageRet.setResult(BasicRet.SUCCESS);
        pageRet.setMessage("获取商家合单集合的列表成功");
        PageInfo pageInfo=shopGroupService.getShopGroupList(pageNo,pageSize,shopGroup);
        pageRet.data.setPageInfo(pageInfo);
        return pageRet;
    }

    @RequestMapping(value = "/getShopGroupSingle",method = RequestMethod.POST)
    @ApiOperation(value = "获取指定的商家合单集合")
    @PreAuthorize("hasAuthority('"+ AdminAuthorityConst.ORDERCOLLECTIONS+"')")
    public ShopGroupRet getShopGroupSingle(Long shopGroupId,
                                           @RequestParam(required = true,defaultValue = "1") int pageNo,
                                           @RequestParam(required = true,defaultValue = "20") int pageSize,Model model){
        ShopGroupRet shopGroupRet=new ShopGroupRet();
        ShopGroup shopGroup=new ShopGroup();
        shopGroup.setId(shopGroupId);
        shopGroupRet.setResult(BasicRet.SUCCESS);
        shopGroupRet.setMessage("获取指定的商家合单集合成功");
        PageInfo pageInfo=shopGroupService.getShopGroupList(pageNo,pageSize,shopGroup);
        shopGroupRet.setData(pageInfo.getList());
        return shopGroupRet;
    }



    @RequestMapping(value = "/addShopGroup",method = RequestMethod.POST)
    @ApiOperation(value = "添加商家合单集合")
    @PreAuthorize("hasAuthority('"+ AdminAuthorityConst.ORDERCOLLECTIONS+"')")
    public BasicRet addShopGroup(ShopGroup shopGroup,Model model){
        BasicRet basicRet=new BasicRet();
        basicRet.setMessage("添加商家合单集合成功");
        basicRet.setResult(BasicRet.SUCCESS);
        BasicRet basicRet1=this.checkBefore(shopGroup);
        if (basicRet1.getResult()!= CONST_ONE){
            return basicRet1;
        }
        shopGroupService.addShopGroup(shopGroup);
        return basicRet;
    }

    @RequestMapping(value = "/updateShopGroup",method = RequestMethod.POST)
    @ApiOperation(value = "更新商家合单集合")
    @PreAuthorize("hasAuthority('"+ AdminAuthorityConst.ORDERCOLLECTIONS+"')")
    public BasicRet updateShopGroup(ShopGroup shopGroup,Model model){
        BasicRet basicRet=new BasicRet();
        basicRet.setMessage("更新商家合单集合成功");
        basicRet.setResult(BasicRet.SUCCESS);
        BasicRet basicRet1=this.checkBefore(shopGroup);
        if (basicRet1.getResult()!= CONST_ONE){
            return basicRet1;
        }
        shopGroupService.updateShopGroup(shopGroup);
        return basicRet;
    }

    @RequestMapping(value = "/deleteShopGroup",method = RequestMethod.POST)
    @ApiOperation(value = "删除商家合单集合")
    @PreAuthorize("hasAuthority('"+ AdminAuthorityConst.ORDERCOLLECTIONS+"')")
    public BasicRet deleteShopGroup(Long shopGroupId,Model model){
        BasicRet basicRet=new BasicRet();
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("删除商家合单集合成功");
        shopGroupService.deleteShopGroup(shopGroupId);

        //对已有加入进这个商家合单集合的仓库进行重置
        shopGroupService.resertStoreShopGroup(shopGroupId);
        return basicRet;
    }



    @RequestMapping(value = "/getShopGroupSellerList",method = RequestMethod.POST)
    @ApiOperation(value = "商家合单集合的商家列表")
    @PreAuthorize("hasAuthority('"+ AdminAuthorityConst.ORDERCOLLECTIONS+"')")
    public ShopGroupAndStoreRet getShopGroupSellerList(Long shopgroupid,Model model){
        ShopGroupAndStoreRet ret=new ShopGroupAndStoreRet();
        ret.setMessage("获取列表成功");
        ret.setResult(BasicRet.SUCCESS);
        ShopGroup shopGroup=shopGroupService.getByPrimaryKey(shopgroupid);
        Store store=new Store();
        store.setShopgroupid(shopgroupid);
        List<ShopGroupAndStoreView> join=shopGroupService.getShopGroupSellerList(store);
        store=new Store();
        store.setShopgroupid(0L);
        store.setName(shopGroup.getStorename());
        List<ShopGroupAndStoreView> unJoin=shopGroupService.getShopGroupSellerList(store);
        ret.setData1(join);
        ret.setData2(unJoin);
        return ret;
    }


    @RequestMapping(value = "/updateStoreShopGroup",method = RequestMethod.POST)
    @ApiOperation(value = "更新商家所属的商家合单集合")
    @PreAuthorize("hasAuthority('"+ AdminAuthorityConst.ORDERCOLLECTIONS+"')")
    public BasicRet updateStoreShopGroup(Long storeId,Long shopgroupid,Model model){
        BasicRet basicRet=new BasicRet();
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("更新成功");
        shopGroupService.updateStoreShopGroup(storeId,shopgroupid);
        return basicRet;
    }


    /**
     *进行商家合单集合的名称与仓库名称的校验
     * @author xiazy
     * @date  2018/6/29 10:55
     * @param shopGroup
     * @return mizuki.project.core.restserver.config.BasicRet
     */
    private BasicRet checkBefore(ShopGroup shopGroup){
        BasicRet basicRet=new BasicRet();
        basicRet.setMessage("校验成功");
        basicRet.setResult(BasicRet.SUCCESS);
        boolean isExit;
        if (StringUtils.hasText(shopGroup.getName())){
            isExit=shopGroupService.checkShopGroupName(shopGroup);
            if (isExit){
                basicRet.setMessage("该集合名称已经存在");
                basicRet.setResult(BasicRet.ERR);
                return basicRet;
            }
        }else {
            basicRet.setMessage("集合名称不能为空");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }
        if (StringUtils.hasText(shopGroup.getStorename())){
            isExit=shopGroupService.checkStoreExit(shopGroup.getStorename());
            if (!isExit){
                basicRet.setMessage("仓库名称需要与系统内仓库管理中的名称匹配");
                basicRet.setResult(BasicRet.ERR);
                return basicRet;
            }
        }else {
            basicRet.setMessage("仓库名称不能为空");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }
        return basicRet;
    }

    private static class ShopGroupRet extends  BasicRet{
        public List<ShopGroup> data=new ArrayList<>();

        public List<ShopGroup> getData() {
            return data;
        }

        public void setData(List<ShopGroup> data) {
            this.data = data;
        }
    }

    private static class ShopGroupAndStoreRet extends BasicRet{
        private List<ShopGroupAndStoreView> data1=new ArrayList<>();
        private List<ShopGroupAndStoreView> data2=new ArrayList<>();

        public List<ShopGroupAndStoreView> getData1() {
            return data1;
        }

        public void setData1(List<ShopGroupAndStoreView> data1) {
            this.data1 = data1;
        }

        public List<ShopGroupAndStoreView> getData2() {
            return data2;
        }

        public void setData2(List<ShopGroupAndStoreView> data2) {
            this.data2 = data2;
        }
    }
}
