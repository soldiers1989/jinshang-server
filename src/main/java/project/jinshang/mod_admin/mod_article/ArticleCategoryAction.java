package project.jinshang.mod_admin.mod_article;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.bean.MemberLogOperator;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.AppConstant;
import project.jinshang.common.utils.ProductCategoryUtils;
import project.jinshang.mod_admin.mod_article.bean.ArticleListCategory;
import project.jinshang.mod_admin.mod_article.service.ArticleCategoryService;
import project.jinshang.mod_admin.mod_article.bean.ArticleCategory;
import project.jinshang.mod_admin.mod_article.service.ArticleService;
import project.jinshang.mod_common.bean.BasicExtRet;
import project.jinshang.mod_member.bean.Admin;
import project.jinshang.mod_member.bean.Member;
import project.jinshang.mod_product.bean.Categories;
import project.jinshang.mod_product.service.MemberOperateLogService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Api(tags = "文章管理—文章分类", description = "文章分类管理相关接口")
@RequestMapping(value = "/rest/admin/article")
@SessionAttributes({AppConstant.ADMIN_SESSION_NAME})
@Transactional(rollbackFor = Exception.class)
public class ArticleCategoryAction {
    @Autowired
    private ArticleCategoryService articleCategoryService;

    @Autowired
    ArticleService articleService;

    @Autowired
    MemberLogOperator memberLogOperator;

    @Autowired
    MemberOperateLogService memberOperateLogService;

    @RequestMapping(value = "/addClsaaify", method = RequestMethod.POST)
    @ApiOperation(value = "添加文章分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "praentid", value = "父级ID{父级分类都为0}", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "docname", value = "文章分类名称", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "docorder", value = "文章序排序", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "docislist", value = "该分类是否可以有子类{0:不可以;1:可以}", defaultValue = "0", required = true, paramType = "query", dataType = "int"),
    })
    public BasicRet addClsaaify(ArticleCategory articleCategory, Model model, HttpServletRequest request) {
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        BasicRet basicRet = new BasicRet();
        if (articleCategory.getPraentid() == null) {
            articleCategory.setPraentid((long) 0);
        }
        if (articleCategory.getPraentid() != 0) {
            ArticleCategory ac = articleCategoryService.selectById(articleCategory.getPraentid());
            if (ac.getDocislist() == 0) {
                basicRet.setResult(BasicRet.ERR);
                basicRet.setMessage("该分类不能添加子类");
                return basicRet;
            }
        }
        List<ArticleCategory> articleCategories = articleCategoryService.getArticleCategoryListBySortId(articleCategory.getPraentid(), articleCategory.getDocorder());
        if (articleCategories.size() != 0) {
            ArticleCategory defaultArticleCategory = articleCategories.get(0);
            defaultArticleCategory.setDocorder(articleCategoryService.getMaxSortIdByParentId(articleCategory.getPraentid()) + 1);
            articleCategoryService.updateClsaafiy(defaultArticleCategory);
        }
        articleCategoryService.addClassify(articleCategory);
        basicRet.setResult(BasicRet.SUCCESS);
        basicRet.setMessage("添加成功");
        memberLogOperator.saveMemberLog(null, admin, "新增文章种类：" + articleCategory.getDocname(), "/addClsaaify", request, memberOperateLogService);
        return basicRet;
    }

    @RequestMapping(value = "/deleteClsaaify", method = RequestMethod.POST)
    @ApiOperation(value = "删除分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", required = true, paramType = "query", dataType = "int"),
    })
    public BasicRet deleteClsaaify(long id, Model model, HttpServletRequest request) {
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        BasicRet basicRet = new BasicRet();
        ArticleCategory articleCategory = articleCategoryService.getArticleCategoryById(id);
        if (articleCategory == null) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("不存在该分类");
            return basicRet;
        }
        int Count = articleCategoryService.selectCountByPraentId(id);
        int articleCount = articleService.getArticleCountByArticleCategoryId(id);
        if (Count != 0) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("该分类下还有子分类，请先删除子分类！");
            return basicRet;
        } else if (articleCount != 0) {
            basicRet.setResult(BasicRet.ERR);
            basicRet.setMessage("该分类下还有文章，请先删除文章！");
            return basicRet;
        } else {
            articleCategoryService.deleteClsaaify(id);
            basicRet.setResult(BasicRet.SUCCESS);
            basicRet.setMessage("删除成功");
            memberLogOperator.saveMemberLog(null, admin, "删除文章种类：" + articleCategory.getDocname(), "/deleteClsaaify", request, memberOperateLogService);
        }
        return basicRet;
    }

    @RequestMapping(value = "/updateClsaafiy", method = RequestMethod.POST)
    @ApiOperation(value = "编辑分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "praentid", value = "父级ID{父级分类都为0}", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "docname", value = "文章分类名称", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "docorder", value = "文章排序", required = true, paramType = "query", dataType = "int"),
    })
    public BasicRet updateClsaafiy(ArticleCategory articleCategory, Model model, HttpServletRequest request) {
        Admin admin = (Admin) model.asMap().get(AppConstant.ADMIN_SESSION_NAME);
        BasicRet basicRet = new BasicRet();
        ArticleCategory dbArticleCategory = articleCategoryService.getArticleCategoryById(articleCategory.getId());
        List<ArticleCategory> articleCategories = articleCategoryService.getArticleCategoryListBySortId(articleCategory.getPraentid(), articleCategory.getDocorder());
        if (!dbArticleCategory.getPraentid().equals(articleCategory.getPraentid())) {
            if (articleCategories.size() != 0) {
                ArticleCategory defaultArticleCategory = articleCategories.get(0);
                defaultArticleCategory.setDocorder(articleCategoryService.getMaxSortIdByParentId(articleCategory.getPraentid()) + 1);
                articleCategoryService.updateClsaafiy(defaultArticleCategory);
            }
        } else if (!dbArticleCategory.getDocorder().equals(articleCategory.getDocorder())) {
            if (articleCategories.size()>0){
                ArticleCategory defaultArticleCategory = articleCategories.get(0);
                defaultArticleCategory.setDocorder(dbArticleCategory.getDocorder());
                articleCategoryService.updateClsaafiy(defaultArticleCategory);
            }
        }
        articleCategoryService.updateClsaafiy(articleCategory);
        basicRet.setMessage("修改成功");
        basicRet.setResult(BasicRet.SUCCESS);
        memberLogOperator.saveMemberLog(null, admin, "更新文章种类id：" + articleCategory.getDocname(), "/updateClsaafiy", request, memberOperateLogService);
        return basicRet;
    }

    @RequestMapping(value = "/maxSort", method = RequestMethod.POST)
    @ApiOperation(value = "获取最大的序列号")
    @ApiImplicitParam(name = "parentId", value = "文章分类父类id", required = true, paramType = "query", dataType = "string")
    public BasicExtRet getMaxSortId(Long parentId) {
        BasicExtRet basicRet = new BasicExtRet();
        basicRet.setData(articleCategoryService.getMaxSortIdByParentId(parentId));
        basicRet.setMessage("修改成功");
        basicRet.setResult(BasicRet.SUCCESS);
        return basicRet;
    }
}
