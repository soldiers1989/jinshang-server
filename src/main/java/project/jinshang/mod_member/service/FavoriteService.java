package project.jinshang.mod_member.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_member.FavoriteMapper;
import project.jinshang.mod_member.bean.Favorite;
import project.jinshang.mod_member.bean.FavoriteExample;
import project.jinshang.mod_member.bean.dto.FavoriteProductDto;
import project.jinshang.mod_product.bean.ProductInfo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * create : wyh
 * date : 2017/12/5
 */

@Service
public class FavoriteService {

    @Autowired
    private FavoriteMapper favoriteMapper;

    public void add(Favorite favorite) {
        favoriteMapper.insert(favorite);
    }

    /**
     * 更加收藏id和用户id删除
     *
     * @param pid
     * @param memberid
     */
    public void delByIdAndMemberid(long pid, long memberid) {
        favoriteMapper.delByIdAndMemberid(pid, memberid);
    }


    public void deleteByExample(FavoriteExample example) {
        favoriteMapper.deleteByExample(example);
    }


    public PageInfo list(int pageNo, int pageSize, long memberid) {
        PageHelper.startPage(pageNo, pageSize);

//        List<FavoriteProductDto> list = favoriteMapper.list(memberid);
        List<FavoriteProductDto> list = favoriteMapper.listByMemberId(memberid);

        PageInfo pageInfo = new PageInfo(list);
        List<FavoriteProductDto> favoriteProductDtos = pageInfo.getList();
        favoriteProductDtos.forEach(favoriteProductDto -> {
            String[] pdPic = (String[]) favoriteProductDto.getPdpicture();
            if (pdPic == null || pdPic.length == 0) {
                favoriteProductDto.setPdpicture(null);
            } else {
                favoriteProductDto.setPdpicture(pdPic[0]);
            }
            if (favoriteProductDto.getPdstorenum() == null) {
                favoriteProductDto.setPdstorenum(new BigDecimal(0));
            } else {
                favoriteProductDto.setPdstorenum(favoriteProductDto.getPdstorenum().abs());
            }
        });
        return pageInfo;
    }

    public boolean getGoodsFavoriteByMemberId(Long memberId, Long pId) {
        FavoriteExample example = new FavoriteExample();
        FavoriteExample.Criteria criteria = example.createCriteria();
        criteria.andMemberidEqualTo(memberId);
        criteria.andPidEqualTo(pId);
        List<Favorite> favorites = favoriteMapper.selectByExample(example);
        if (favorites.size() > 0) {
            return true;
        }
        return false;
    }


    public PageInfo listByType(int pageNo, int pageSize, long memberid,int type) {
        PageHelper.startPage(pageNo, pageSize);

//        List<FavoriteProductDto> list = favoriteMapper.list(memberid);
        List<FavoriteProductDto> list = favoriteMapper.listByMemberIdAndType(memberid,type);
        PageInfo pageInfo = new PageInfo(list);
        List<FavoriteProductDto> favoriteProductDtos = pageInfo.getList();
        favoriteProductDtos.forEach(favoriteProductDto -> {
            String[] pdPic = (String[]) favoriteProductDto.getPdpicture();
            if (pdPic == null || pdPic.length == 0) {
                favoriteProductDto.setPdpicture(null);
            } else {
                favoriteProductDto.setPdpicture(pdPic[0]);
            }
            if (favoriteProductDto.getPdstorenum() == null) {
                favoriteProductDto.setPdstorenum(new BigDecimal(0));
            } else {
                favoriteProductDto.setPdstorenum(favoriteProductDto.getPdstorenum().abs());
            }
        });
        return pageInfo;
    }




}
