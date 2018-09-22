package project.jinshang.mod_product.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_product.bean.SearchAttrSort;
import project.jinshang.mod_product.bean.SearchAttrSortExample;
import project.jinshang.mod_product.bean.SearchAttrSortValue;
import project.jinshang.mod_product.bean.SearchAttrSortValueExample;
import project.jinshang.mod_product.mapper.SearchAttrSortMapper;
import project.jinshang.mod_product.mapper.SearchAttrSortValueMapper;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SearchAttrSortService {

    @Autowired
    private SearchAttrSortMapper searchAttrSortMapper;

    @Autowired
    private SearchAttrSortValueMapper searchAttrSortValueMapper;


    public int getCountBySortnamType(String sortname,Short type){
        SearchAttrSortExample example = new SearchAttrSortExample();
        SearchAttrSortExample.Criteria criteria = example.createCriteria();
        criteria.andSortnameEqualTo(sortname).andTypeEqualTo(type);
        return searchAttrSortMapper.countByExample(example);
    }

    public void insertSelective(SearchAttrSort searchAttrSort){
        searchAttrSortMapper.insertSelective(searchAttrSort);
    }

    public PageInfo getByPage(int pageNo,int pageSize){
        PageHelper.startPage(pageNo,pageSize);
        SearchAttrSortExample example = new SearchAttrSortExample();
        example.setOrderByClause(" id asc ");
        return new PageInfo(searchAttrSortMapper.selectByExample(example));
    }

    public void delById(Long id){
        searchAttrSortMapper.deleteByPrimaryKey(id);
    }

    public SearchAttrSort getById(Long id){
        return  searchAttrSortMapper.selectByPrimaryKey(id);
    }


}
