package project.jinshang.mod_product.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_product.bean.SearchAttrSortValue;
import project.jinshang.mod_product.bean.SearchAttrSortValueExample;
import project.jinshang.mod_product.mapper.SearchAttrSortValueMapper;

import java.util.List;

@Service
public class SearchAttrSortValueService {

    @Autowired
    private SearchAttrSortValueMapper searchAttrSortValueMapper;

    public SearchAttrSortValue getById(Long id){
        return searchAttrSortValueMapper.selectByPrimaryKey(id);
    }

    public void  insertSelective(SearchAttrSortValue searchAttrSortValue){
        searchAttrSortValueMapper.insertSelective(searchAttrSortValue);
    }

    public void deleteBySearchAttrId(Long searchAttrId){
        SearchAttrSortValueExample example = new SearchAttrSortValueExample();
        SearchAttrSortValueExample.Criteria criteria = example.createCriteria();
        criteria.andSearchAttrIdEqualTo(searchAttrId);
        searchAttrSortValueMapper.deleteByExample(example);
    }

    public void deleteById(Long id){
        searchAttrSortValueMapper.deleteByPrimaryKey(id);
    }

    public PageInfo getBySearchAttrId(Long searchAttrId,Integer pageNo,Integer pageSize){
        PageHelper.startPage(pageNo,pageSize);
        SearchAttrSortValueExample example = new SearchAttrSortValueExample();
        example.setOrderByClause(" weight asc ");
        SearchAttrSortValueExample.Criteria criteria = example.createCriteria();
        criteria.andSearchAttrIdEqualTo(searchAttrId);
        List list = searchAttrSortValueMapper.selectByExample(example);
        return new PageInfo(list);
    }

    public List<SearchAttrSortValue> selectByExample(SearchAttrSortValueExample example){
        return searchAttrSortValueMapper.selectByExample(example);
    }

    public int countByExample(SearchAttrSortValueExample example){
        return  searchAttrSortValueMapper.countByExample(example);
    }


}
