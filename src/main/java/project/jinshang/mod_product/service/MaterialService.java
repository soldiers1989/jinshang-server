package project.jinshang.mod_product.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import mizuki.project.core.restserver.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_product.MaterialMapper;
import project.jinshang.mod_product.bean.Material;
import project.jinshang.mod_product.bean.MaterialExample;

import java.util.List;

/**
 * create : wyh
 * date : 2017/11/9
 */

@Service
public class MaterialService {

    @Autowired
    private MaterialMapper materialMapper;

    public Material getByName(String name){
        return  materialMapper.getByName(name);
    }


    public  void add(Material material){
        materialMapper.insert(material);
    }


    public  void update(Material material){
        materialMapper.updateByPrimaryKey(material);
    }


    public  Material getById(long id){
        return  materialMapper.selectByPrimaryKey(id);
    }


    public  void deleteById(long id){
        materialMapper.deleteByPrimaryKey(id);
    }



    public PageInfo getList(int pageNo,int pageSize,String name,int number){
        PageHelper.startPage(pageNo,pageSize);

        MaterialExample example = new MaterialExample();
        MaterialExample.Criteria criterion = example.createCriteria();

        if(StringUtils.hasText(name)){
            criterion.andNameLike("%"+name+"%");
        }

        if(number>0){
            criterion.andNumberEqualTo(number);
        }

        List<Material> list = materialMapper.selectByExample(example);

        return  new PageInfo(list);

    }


    /**
     * 获取所有材质信息
     * @return
     */
    public  List<Material> getAll(){
        MaterialExample example = new MaterialExample();
        example.setOrderByClause(" number asc ");
        List<Material> list = materialMapper.selectByExample(example);
        return  list;
    }


}
