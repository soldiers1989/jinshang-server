package project.jinshang.mod_coupon.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.exception.MyException;
import project.jinshang.common.utils.GenerateNo;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_coupon.YhqTicketMapper;
import project.jinshang.mod_coupon.YhqTicketpacksMapper;
import project.jinshang.mod_coupon.YhqTicketsetMapper;
import project.jinshang.mod_coupon.bean.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * create : zzy
 * date : 2018/05/26
 */
@Service
@Transactional
public class YhqTicketService {

    @Autowired
    private YhqTicketMapper yhqTicketMapper;
    @Autowired
    private YhqTicketpacksMapper yhqTicketpacksMapper;
    @Autowired
    private YhqTicketsetMapper yhqTicketsetMapper;
    
    @Autowired
    private YhqTicketSetService yhqTicketSetService;
    
    @Autowired
    private YhqTicketPacksService yhqTicketPacksService;

    public void insertYhqTicketInfo(YhqTicket yhqTicket) {
        yhqTicketMapper.insertSelective(yhqTicket);
    }

    public PageInfo getListByPage(int pageNo, int pageSize, YhqTicket yhqTicket) {
        if(pageNo != -1){
            PageHelper.startPage(pageNo,pageSize);
        }
        List<Map<String,Object>> list = yhqTicketMapper.selectByObject(yhqTicket);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    public YhqTicket getYhqTicketInfoById(Long id) {
       return yhqTicketMapper.selectByPrimaryKey(id);
    }

    public void updateYhqTicketInfo(YhqTicket yhqTicket) {
        yhqTicketMapper.updateByPrimaryKeySelective(yhqTicket);
    }

    public long countByExample(YhqTicketExample yhqTicketExample) {
        return  yhqTicketMapper.countByExample(yhqTicketExample);
    }

    public PageInfo getListByPage1(int pageNo, int pageSize, YhqTicket yhqTicket) {
        if(pageNo != -1){
            PageHelper.startPage(pageNo,pageSize);
        }
        List<Map<String,Object>> list = yhqTicketMapper.selectByObject1(yhqTicket);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    public List<Map<String,Object>> getListYhqTicketInfoById(Long id) {
        return  yhqTicketMapper.getListYhqTicketInfoById(id);
    }

    public List<Map<String,Object>> selectByTicketSetIdAndStatus(long ticketid1, int status) {
        return yhqTicketMapper.selectByTicketSetIdAndStatus(ticketid1,status);
    }

    public YhqTicket updateByYhqTicket(String no, Date createtime, long memberid) {
        return  yhqTicketMapper.updateByYhqTicket(no,createtime,memberid);
    }

    public  List<YhqTicket> getNotUseYhqTicket(long memberid) {
        Date nowdate = new Date();
        return  yhqTicketMapper.getNotUseYhqTicket(memberid,nowdate);
    }

    public List<YhqTicket> getUseYhqTicket(long memberid) {
        Short status = 99;
        return yhqTicketMapper.getUseYhqTicket(memberid,status);
    }

    public List<YhqTicket> getExpiretYhqTicket(long memberid) {
        Date nowdate = new Date();
        return  yhqTicketMapper.getExpiretYhqTicket(memberid,nowdate);
    }


    public int updateByOrders(YhqTicket yhqTicket){
        return yhqTicketMapper.updateByOrders(yhqTicket);
    }
    //
    public void insertYhqTicketForRegister(YhqTicket yhqTicket, YhqTicketpacks yhqTicketpacks, long memberid) {
        JSONArray jsonArray = JSONArray.fromObject(yhqTicketpacks.getTicketlist());
        long ticketsetid = 0;
        for(int i= 0;i < jsonArray.size();i++) {
            InsertTicket(yhqTicket, memberid, jsonArray, i,null);
        }
        //减少优惠券礼包数量
        if(yhqTicketpacks.getSurpluscount() !=0) {
            long surpluscount = (yhqTicketpacks.getSurpluscount() - 1);
            yhqTicketPacksService.updateSurpluscount(yhqTicketpacks.getId(), surpluscount);
        }

    }




    public void insertYhqTicketForExecute(YhqTicket yhqTicket, YhqProject yhqProject) {
        JSONArray jsonArray = JSONArray.fromObject(yhqProject.getTicketlist());
        //循环rule 取出用户id
        JSONArray jsonArrayRule = JSONArray.fromObject(yhqProject.getRule());
        for (int i1= 0; i1<jsonArrayRule.size();i1++) {
            JSONObject jsonObjectRule = jsonArrayRule.getJSONObject(i1);
            //取出memerid 知道发放给谁
            String tmpmemberid = jsonObjectRule.getString("uid");
            long memberid = Long.parseLong(tmpmemberid);
            long ticketsetid = 0;
            InsertTicket(yhqTicket, memberid, jsonArray, i1,yhqProject.getId());
            }
        }




    private void InsertTicket(YhqTicket yhqTicket, long memberid, JSONArray jsonArray, int i,Long projectid) {
        long ticketsetid;
        JSONObject jsonObject = jsonArray.getJSONObject(i);
        //取出优惠券名称
        String  name  = jsonObject.getString("name");
        //取出ticketsetid
        String id = jsonObject.getString("ticketid");

        ticketsetid = Long.parseLong(id);
        String count1 = jsonObject.getString("count");
        long count = Long.parseLong(count1);
        //根据ticksetid查出ticketset信息
        YhqTicketset yhqTicketset = yhqTicketsetMapper.selectByPrimaryKey(ticketsetid);
        for (int j = 0;j<count;j++ ) {
            yhqTicket.setNo(GenerateNo.getYhqNo());
            yhqTicket.setName(name);
            yhqTicket.setType(yhqTicketset.getType());
            yhqTicket.setPlanid(yhqTicketset.getPlanid());
            yhqTicket.setTicketsetid(ticketsetid);
            yhqTicket.setAbout(yhqTicketset.getAbout());
            BigDecimal zero = new BigDecimal(0);
            yhqTicket.setCapital(zero);
            yhqTicket.setAvailable(zero);
            yhqTicket.setRule(yhqTicketset.getRule());
            yhqTicket.setStarttime(yhqTicketset.getStarttime());
            yhqTicket.setEndtime(yhqTicketset.getEndtime());
            yhqTicket.setValidityrule(yhqTicketset.getValidityrule());
            yhqTicket.setValiditystarttime(yhqTicketset.getValiditystarttime());
            yhqTicket.setValidityendtime(yhqTicketset.getValidityendtime());
            //注册就是已领取
            yhqTicket.setStatus(2l);
            yhqTicket.setMemberid(memberid);
            yhqTicket.setGettime(new Date());
//            yhqTicket.setOrdersid(0l);
            yhqTicket.setUsersid(0l);
            yhqTicket.setCreatetime(new Date());
            if(projectid != null){
                yhqTicket.setProjectid(projectid);
            }

            yhqTicketMapper.insertSelective(yhqTicket);

            //减少优惠券配置表数量
            //需要礼包数量乘以优惠券张数 for循环生成几张优惠券就就少几张优惠券数量
            YhqTicketset yhqTicketset1 = yhqTicketSetService.getYhqTicketSetInfoById(ticketsetid);
            if(yhqTicketset1.getSurpluscount() !=0) {
                long surpluscount1 = (yhqTicketset1.getSurpluscount()-1);
                yhqTicketSetService.updateSurpluscount(yhqTicketset1.getId(), surpluscount1);
            }
        }
    }

    public void insertYhqTicketForExecute1(YhqProject oldYhqProject) throws MyException {
        //从rule里面取出用户id,知道发给哪个用户
        JSONArray jsonArrayRule = JSONArray.fromObject(oldYhqProject.getRule());
            JSONObject jsonObjectRule = jsonArrayRule.getJSONObject(0);
            //取出memerid 知道发放给谁
            String tmpmemberid = jsonObjectRule.getString("uid");
            long memberid = Long.parseLong(tmpmemberid);
            long ticketsetid = 0;
            InsertTicket1(oldYhqProject, memberid,jsonArrayRule);
    }

    private void InsertTicket1(YhqProject oldYhqProject, long memberid,JSONArray jsonArrayRule) throws MyException {
        JSONArray jsonArray = JSONArray.fromObject(oldYhqProject.getTicketlist());
        for (int i=0;i<jsonArray.size();i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            //取出ticketsetid
            String id = jsonObject.getString("ticketid");
            long ticketsetid = Long.parseLong(id);
            String newcount = jsonObject.getString("count");
            long count = Long.parseLong(newcount);
            //根据ticksetid查出ticketset信息
            YhqTicketset yhqTicketset = yhqTicketsetMapper.selectByPrimaryKey(ticketsetid);

            for (int j = 0; j < count; j++) {
                YhqTicketset yhqTicketset1 = yhqTicketsetMapper.selectByPrimaryKey(ticketsetid);
                if(yhqTicketset1.getSurpluscount() ==0){
                    throw new MyException(yhqTicketset1.getName()+"的优惠券配置数量已经为0");
                }
                YhqTicket yhqTicket = new YhqTicket();
                yhqTicket.setNo(GenerateNo.getYhqNo());
                yhqTicket.setName(yhqTicketset.getName());
                yhqTicket.setType(yhqTicketset.getType());
                yhqTicket.setPlanid(yhqTicketset.getPlanid());
                yhqTicket.setTicketsetid(ticketsetid);
                yhqTicket.setAbout(yhqTicketset.getAbout());
                yhqTicket.setCapital(Quantity.BIG_DECIMAL_0);
                yhqTicket.setAvailable(Quantity.BIG_DECIMAL_0);
                yhqTicket.setRule(yhqTicketset.getRule());
                yhqTicket.setStarttime(yhqTicketset.getStarttime());
                yhqTicket.setEndtime(yhqTicketset.getEndtime());
                yhqTicket.setValidityrule(yhqTicketset.getValidityrule());
                yhqTicket.setValiditystarttime(yhqTicketset.getValiditystarttime());
                yhqTicket.setValidityendtime(yhqTicketset.getValidityendtime());
                //发放了就是已领取
                yhqTicket.setStatus(2l);
                yhqTicket.setMemberid(memberid);
                yhqTicket.setGettime(new Date());
//             yhqTicket.setOrdersid(0l);
                yhqTicket.setUsersid(0l);
                yhqTicket.setCreatetime(new Date());
                if (oldYhqProject!= null) {
                    if (oldYhqProject.getId() != null) {
                        yhqTicket.setProjectid(oldYhqProject.getId());
                    }
                }
                yhqTicketMapper.insertSelective(yhqTicket);
                //减少优惠券配置表数量
                //for循环生成几张优惠券就就少几张优惠券数量
                YhqTicketset oldYhqTicketset = yhqTicketSetService.getYhqTicketSetInfoById(ticketsetid);
                if(oldYhqTicketset.getSurpluscount() !=0) {
                    long surpluscount1 = (oldYhqTicketset.getSurpluscount()-1);
                    yhqTicketSetService.updateSurpluscount(oldYhqTicketset.getId(), surpluscount1);
                }
        }



        }
    }


}
