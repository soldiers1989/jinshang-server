package project.jinshang.mod_advertis.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.common.bean.PageRet;
import project.jinshang.mod_advertis.AdvertisementMapper;
import project.jinshang.mod_advertis.bean.Advertisement;
import project.jinshang.mod_advertis.bean.AdvertisementExample;

import java.util.Date;
import java.util.List;

@Service
public class AdvertisementService {

    @Autowired
    AdvertisementMapper advertisementMapper;

    public int getAdvertisementCountByAdvertisingPlaceId(Long advertisingPlaceId) {
        return advertisementMapper.countByAdvertisingPlaceId(advertisingPlaceId);
    }

    public void insertAdvertisement(Advertisement advertisement) {
        advertisementMapper.insertSelective(advertisement);
    }

    public void deleteAdvertisementById(Long advertisementId) {
        advertisementMapper.deleteByPrimaryKey(advertisementId);
    }

    public void updateAdvertisement(Advertisement advertisement) {
        advertisementMapper.updateByPrimaryKeySelective(advertisement);
    }

    public Advertisement getAdvertisementById(Long advertisementId) {
        return advertisementMapper.selectByPrimaryKey(advertisementId);
    }

    public PageInfo getAdvertisementList(int pageNo, int pageSize, String title, Long placeId, String overdue) {
        PageHelper.startPage(pageNo, pageSize);

        AdvertisementExample advertisementExample = new AdvertisementExample();
        AdvertisementExample.Criteria criteria = advertisementExample.createCriteria();

        if (StringUtil.isNotEmpty(title)) {
            criteria.andTitleLike("%" + title + "%");
        }

        if (placeId != null) {
            criteria.andPlaceidEqualTo(placeId);
        }

        if ("0".equals(overdue)) {
            criteria.andEndtimeGreaterThan(new Date());
        } else if ("1".equals(overdue)) {
            criteria.andEndtimeLessThan(new Date());
        }

        advertisementExample.setOrderByClause("sort asc");

        List<Advertisement> advertisements = advertisementMapper.selectByExample(advertisementExample);
        PageInfo pageInfo = new PageInfo(advertisements);
        return pageInfo;
    }


    /**
     * 根据广告位名称获取广告
     *
     * @param position
     * @param count
     * @return
     */
    public List<Advertisement> getAdvertisment(String position, int count) {
        return advertisementMapper.getAdvertisment(position, count);
    }

    public int getMaxSortIdByAdvertisingPlaceId(Long advertisingPlaceId) {
        AdvertisementExample example = new AdvertisementExample();
        AdvertisementExample.Criteria criteria = example.createCriteria();
        criteria.andPlaceidEqualTo(advertisingPlaceId);
        example.setOrderByClause("sort desc");
        List<Advertisement> advertisements = advertisementMapper.selectByExample(example);
        if (advertisements.size() == 0) {
            return 0;
        } else {
            return advertisements.get(0).getSort();
        }
    }

    public List<Advertisement> selectAdvertisementListByPlaceIdAndSortId(Long placeId, int sortId) {
        AdvertisementExample example = new AdvertisementExample();
        AdvertisementExample.Criteria criteria = example.createCriteria();
        criteria.andPlaceidEqualTo(placeId);
        criteria.andSortEqualTo(sortId);
        return advertisementMapper.selectByExample(example);
    }
}
