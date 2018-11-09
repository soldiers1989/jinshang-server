package project.jinshang.mod_coupon.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_coupon.YhqTicketpacksMapper;
import project.jinshang.mod_coupon.bean.YhqTicketpacks;
import project.jinshang.mod_coupon.bean.YhqTicketpacksExample;

import java.util.List;
import java.util.Map;

/**
 * create : zzy
 * date : 2018/05/26
 */
@Service
public class YhqTicketPacksService {
    @Autowired
    private YhqTicketpacksMapper yhqTicketpacksMapper;

    public PageInfo getListByPage(int pageNo, int pageSize, YhqTicketpacks yhqTicketpacks) {
        if(pageNo != -1){
            PageHelper.startPage(pageNo,pageSize);
        }
        List<Map<String,Object>> list = yhqTicketpacksMapper.selectByObject(yhqTicketpacks);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    public void insertYhqTicketpacksInfo(YhqTicketpacks yhqTicketpacks) {
        yhqTicketpacksMapper.insertSelective(yhqTicketpacks);
    }

    public YhqTicketpacks getYhqTicketpacksInfoById(Long id) {
       return yhqTicketpacksMapper.selectByPrimaryKey(id);
    }

   public List<Map<String,Object>> selectById(Long id,YhqTicketpacks yhqTicketpacks) {
        JSONArray jsonArray = JSONArray.fromObject(yhqTicketpacks.getTicketlist());
        List<Map<String,Object>> list = null;
        for(int i= 0;i < jsonArray.size();i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String ticketid = jsonObject.getString("ticketid");
            long ticketid1=Long.parseLong(ticketid);
            list = yhqTicketpacksMapper.selectById(id,ticketid1);
        }

        return list;
    }


    public void deleteYhqTicketpacksInfoById(Long id) {
        yhqTicketpacksMapper.deleteByPrimaryKey(id);
    }

    public long countByExample(YhqTicketpacksExample yhqTicketpacksExample) {
        return  yhqTicketpacksMapper.countByExample(yhqTicketpacksExample);
    }


    public List<YhqTicketpacks> selectByNoAndSurplusCount(String packsno) {
        return yhqTicketpacksMapper.selectByNoAndSurplusCount(packsno);

    }


    public List<YhqTicketpacks> getAllYhqTicketpacks() {
        return  yhqTicketpacksMapper.getAllYhqTicketpacks();
    }




    public void updateSurpluscount(Long id,Long surpluscount) {
        yhqTicketpacksMapper.updateSurpluscount(id,surpluscount);

    }

    public void updateYhqTicketPacksInfo(YhqTicketpacks yhqTicketpacks) {
        yhqTicketpacksMapper.updateByPrimaryKeySelective(yhqTicketpacks);
    }

    public PageInfo getListByPage1(int pageNo, int pageSize, YhqTicketpacks yhqTicketpacks) {
        if(pageNo != -1){
            PageHelper.startPage(pageNo,pageSize);
        }
        List<Map<String,Object>> list = yhqTicketpacksMapper.selectByObject1(yhqTicketpacks);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    public YhqTicketpacks getYhqTicketpacksInfoByName(String name) {
        return  yhqTicketpacksMapper.getYhqTicketpacksInfoByName(name);
    }
}
