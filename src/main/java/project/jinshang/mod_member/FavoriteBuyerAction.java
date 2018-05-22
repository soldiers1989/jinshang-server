package project.jinshang.mod_member;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.mod_common.bean.BasicExtRet;
import project.jinshang.mod_member.bean.Favorite;
import project.jinshang.mod_member.bean.FavoriteExample;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_member.service.FavoriteService;

import java.util.*;

/**
 * create : wyh
 * date : 2017/12/5
 */

@RestController
@RequestMapping("/rest/buyer/favorite")
@SessionAttributes({AppConstant.MEMBER_SESSION_NAME})
@Api(tags = "买家商品收藏相关接口")
public class FavoriteBuyerAction {

    @Autowired
    private FavoriteService favoriteService;

    @PostMapping("/add")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "商品id", name = "pid", required = true, paramType = "query", dataType = "int"),
    })
    @ApiOperation("添加收藏")
    public BasicRet add(@RequestParam(required = true) long pid, Model model) {
        BasicRet basicRet = new BasicRet();

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        if (favoriteService.getGoodsFavoriteByMemberId(member.getId(), pid)) {
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("添加成功");
            return basicRet;
        }
        Favorite favorite = new Favorite();
        favorite.setMemberid(member.getId());
        favorite.setPid(pid);
        favorite.setCreatetime(new Date());

        favoriteService.add(favorite);

        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("添加成功");
        return basicRet;
    }


    @PostMapping("/delete")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "收藏id", name = "pid", required = true, paramType = "query", dataType = "int"),
    })
    @ApiOperation("取消收藏")
    public BasicRet delete(@RequestParam(required = true) long pid, Model model) {
        BasicRet basicRet = new BasicRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        favoriteService.delByIdAndMemberid(pid, member.getId());
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("删除成功");
        return basicRet;
    }


    @PostMapping("/batch/delete")
    @ApiOperation("批量取消收藏")
    public BasicRet batchDelete(@RequestParam(required = true) Long[] pIds, Model model) {
        BasicRet basicRet = new BasicRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        if (pIds == null || pIds.length == 0) {
            basicRet.setMessage("请填写要取消的收藏");
            basicRet.setResult(BasicRet.ERR);
            return basicRet;
        }

        FavoriteExample example = new FavoriteExample();
        FavoriteExample.Criteria criteria = example.createCriteria();

        criteria.andPidIn(Arrays.asList(pIds));
        criteria.andMemberidEqualTo(member.getId());

        favoriteService.deleteByExample(example);

        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("删除成功");
        return basicRet;
    }


    @PostMapping("/list")
    @ApiOperation("收藏列表")
    public PageRet list(@RequestParam(required = true, defaultValue = "1") int pageNo,
                        @RequestParam(required = true, defaultValue = "10") int pageSize, Model model) {
        PageRet pageRet = new PageRet();

        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);

        PageInfo pageInfo = favoriteService.list(pageNo, pageSize, member.getId());

        pageRet.data.setPageInfo(pageInfo);
        pageRet.setMessage("查询成功");
        pageRet.setResult(BasicRet.SUCCESS);
        return pageRet;
    }


    @PostMapping("/state")
    @ApiOperation("获取某个商品用户收藏状态")
    @ApiImplicitParam(value = "商品id", name = "pid", required = true, paramType = "query", dataType = "int")
    public BasicExtRet getGoodsFavoriteState(@RequestParam(required = true) Long pid,
                                             Model model) {
        BasicExtRet basicExtRet = new BasicExtRet();
        Member member = (Member) model.asMap().get(AppConstant.MEMBER_SESSION_NAME);
        basicExtRet.setMessage("查询成功");
        basicExtRet.setResult(BasicRet.SUCCESS);
        Map<String, Boolean> result = new HashMap<>(1);

        if (favoriteService.getGoodsFavoriteByMemberId(member.getId(), pid)) {
            result.put("state", true);
            basicExtRet.setData(result);
        } else {
            result.put("state", false);
            basicExtRet.setData(result);
        }
        return basicExtRet;
    }


}
