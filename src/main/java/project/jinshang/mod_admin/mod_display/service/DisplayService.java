package project.jinshang.mod_admin.mod_display.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_admin.mod_display.DisplayMapper;
import project.jinshang.mod_admin.mod_display.bean.DisplayExample;
import project.jinshang.mod_admin.mod_display.bean.Display;

import java.util.List;

@Service
public class DisplayService {

    @Autowired
    private DisplayMapper displayMapper;

    public void  addDisplay(Display display){
        if("null".equals(display.getDpsuperior())){
            display.setDpsuperior(display.getDpclass());
        }

        displayMapper.insert(display);
    }

    public void deleteDisplay(long id){
        displayMapper.deleteByPrimaryKey(id);
    }

    public void  updateDisplay(Display display){
        displayMapper.updateByPrimaryKeySelective(display);
    }

    public List<Display> listAllDisplay(){
        DisplayExample displayExample=new DisplayExample();
        return  displayMapper.selectByExample(displayExample);
    }

    /**
     * prarentid
     * @param prarentid
     */
    public int selectCountByPrarentid(long prarentid){
        DisplayExample displayExample =  new DisplayExample();
        displayExample.createCriteria().andPraentidEqualTo(prarentid);
        int count = displayMapper.countByExample(displayExample);
        return count;
    }

    public Display selectByDisPlayId(Long id){
        return displayMapper.selectByPrimaryKey(id);
    }
}
