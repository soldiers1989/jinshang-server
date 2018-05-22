package project.jinshang.mod_admin.mod_navigation.service;

import com.alibaba.druid.sql.PagerUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.common.bean.Page;
import project.jinshang.mod_admin.mod_navigation.NavigationAction;
import project.jinshang.mod_admin.mod_navigation.NavigationMapper;
import project.jinshang.mod_admin.mod_navigation.bean.Navigation;
import project.jinshang.mod_admin.mod_navigation.bean.NavigationExample;

import java.util.List;

@Service
public class NavigationService {

    @Autowired
    private NavigationMapper navigationMapper;

    public void addNavigation(Navigation navigation) {

        navigationMapper.insert(navigation);
    }

    public void deleteNavigation(long id) {

        navigationMapper.deleteByPrimaryKey(id);
    }

    public void updateNavigation(Navigation navigation) {

        navigationMapper.updateByPrimaryKey(navigation);

    }

    public List<Navigation> listAllNavigation() {
        NavigationExample navigationExample = new NavigationExample();
        return navigationMapper.selectByExample(navigationExample);
    }

    public PageInfo getNavigationList(int pageNo, int pageSize, String naType) {
        PageHelper.startPage(pageNo, pageSize);

        NavigationExample example = new NavigationExample();
        NavigationExample.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(naType)) {
            criteria.andNatypeEqualTo(naType);
        }
        example.setOrderByClause("nasort asc");
        List<Navigation> navigations = navigationMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(navigations);
        return pageInfo;
    }

    public List<Navigation> getNavigationListByNaType(String naType) {
        NavigationExample example = new NavigationExample();
        NavigationExample.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(naType)) {
            criteria.andNatypeEqualTo(naType);
        }
        criteria.andIsShowEqualTo(1);
        example.setOrderByClause("nasort asc");
        return navigationMapper.selectByExample(example);
    }

    /**
     * 判断该序号是否被占用
     *
     * @return
     */
    public List<Navigation> getListByNaSort(int naSort) {
        NavigationExample example = new NavigationExample();
        NavigationExample.Criteria criteria = example.createCriteria();
        criteria.andNasortEqualTo(naSort);
        return navigationMapper.selectByExample(example);
    }

    public Navigation selectNavigationById(Long id) {
        return navigationMapper.selectByPrimaryKey(id);
    }

    public int selectMaxSort() {
        return navigationMapper.selectMaxSort();
    }

}
