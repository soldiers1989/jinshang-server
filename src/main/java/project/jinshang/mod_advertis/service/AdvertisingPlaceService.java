package project.jinshang.mod_advertis.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.common.utils.StringUtils;
import project.jinshang.mod_advertis.AdvertisingPlaceMapper;
import project.jinshang.mod_advertis.bean.AdvertisementExample;
import project.jinshang.mod_advertis.bean.AdvertisingPlace;
import project.jinshang.mod_advertis.bean.AdvertisingPlaceExample;

import java.util.List;

@Service
public class AdvertisingPlaceService {

    @Autowired
    AdvertisingPlaceMapper advertisingPlaceMapper;

    public void addAdvertisingPlace(AdvertisingPlace advertisingPlace) {
        advertisingPlaceMapper.insertSelective(advertisingPlace);
    }

    public void deleteAdvertisingPlaceById(Long advertisingPlaceId) {
        advertisingPlaceMapper.deleteByPrimaryKey(advertisingPlaceId);
    }


    public void updateAdvertisingPlace(AdvertisingPlace advertisingPlace) {
        advertisingPlaceMapper.updateByPrimaryKeySelective(advertisingPlace);
    }

    public AdvertisingPlace getDetailByPrimaryId(Long id) {
        return advertisingPlaceMapper.selectByPrimaryKey(id);
    }

    public PageInfo getAdvertisingPlaceList(int pageNo, int pageSize, Short stop, String showtype, String advtype) {
        PageHelper.startPage(pageNo, pageSize);

        AdvertisingPlaceExample example = new AdvertisingPlaceExample();
        AdvertisingPlaceExample.Criteria criteria = example.createCriteria();

        if (stop != null) {
            if (stop == 2) {
                criteria.andStopEqualTo((short) 0);
            } else {
                criteria.andStopEqualTo(stop);
            }
        }

        if (StringUtil.isNotEmpty(showtype)) {
            criteria.andShowtypeEqualTo(showtype);
        }

        if (StringUtil.isNotEmpty(advtype)) {
            criteria.andAdvtypeEqualTo(advtype);
        }

        List<AdvertisingPlace> advertisingPlaces = advertisingPlaceMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(advertisingPlaces);

        return pageInfo;
    }

    public List<AdvertisingPlace> getAdvertisingPlaceUsingList() {
        AdvertisingPlaceExample example = new AdvertisingPlaceExample();
        AdvertisingPlaceExample.Criteria criteria = example.createCriteria();

        criteria.andStopEqualTo((short) 0);

        return advertisingPlaceMapper.selectByExample(example);
    }

    public AdvertisingPlace getAdvertisingPlaceByPosition(String position) {
        AdvertisingPlaceExample example = new AdvertisingPlaceExample();
        AdvertisingPlaceExample.Criteria criteria = example.createCriteria();
        criteria.andPositionEqualTo(position);
        List<AdvertisingPlace> advertisingPlaces = advertisingPlaceMapper.selectByExample(example);
        if (advertisingPlaces.size() == 0) {
            return null;
        } else {
            return advertisingPlaces.get(0);
        }
    }

}
