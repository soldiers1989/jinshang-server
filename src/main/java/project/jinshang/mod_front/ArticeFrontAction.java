package project.jinshang.mod_front;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.utils.ProductCategoryUtils;
import project.jinshang.mod_admin.mod_article.bean.Article;
import project.jinshang.mod_admin.mod_article.bean.ArticleCategory;
import project.jinshang.mod_admin.mod_article.bean.ArticleListCategory;
import project.jinshang.mod_admin.mod_article.service.ArticleCategoryService;
import project.jinshang.mod_admin.mod_article.service.ArticleService;
import project.jinshang.mod_common.bean.BasicExtRet;

import java.io.PipedOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = {"/rest/front/ArticeFront"})
@Api(tags = "文章分类、内容列表", description = "前端显示文章列表接口")
public class ArticeFrontAction {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleCategoryService articleCategoryService;

    @RequestMapping(value = "/listAllArticle", method = RequestMethod.POST)
    @ApiOperation("根据文章ID或者分类ID查询文章列表")
    public PageRet listAllArticle(
            @RequestParam(required = false, value = "id", defaultValue = "-1") long id,
            @RequestParam(required = false, value = "docid", defaultValue = "-1") long docid,
            @RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
            @RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) {

        PageRet pageRet = new PageRet();
        pageRet.setResult(BasicRet.SUCCESS);
        PageInfo pageInfo = articleService.listAllArticle(id, docid, pageNo, pageSize);
        pageRet.data.setPageInfo(pageInfo);
        return pageRet;
    }

    @RequestMapping(value = "/getArticleListCategoryList", method = RequestMethod.POST)
    @ApiOperation(value = "获取文章分类")
    @ResponseBody
    public BasicRet getArticleListCategoryList() {
        BasicExtRet basicRet = new BasicExtRet();

        List<ArticleCategory> list = articleCategoryService.getAll();
        List<ArticleListCategory> articleListCategoryList = ProductCategoryUtils.toArticleProdCate(list);
        articleListCategoryList = ProductCategoryUtils.getArticleChildsManyGroup(articleListCategoryList, 0);
        basicRet.setData(articleListCategoryList);
        basicRet.setResult(BasicRet.SUCCESS);
        return basicRet;
    }

    @RequestMapping(value = "/article/list", method = RequestMethod.POST)
    @ApiOperation("分页获取该文章类型下的文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "pageNo", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "pageSize", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "titleName", value = "titleName", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "articleCategoryId", value = "articleCategoryId", required = false, paramType = "query", dataType = "Long"),
    })
    public PageRet getArticleList(@RequestParam int pageNo,
                                  @RequestParam int pageSize,
                                  @RequestParam(required = false) String titleName,
                                  @RequestParam(required = false) Long articleCategoryId) {
        PageRet pageRet = new PageRet();
        PageInfo pageInfo = articleService.getArticleList(pageNo, pageSize, titleName, articleCategoryId);
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setMessage("获取成功");
        pageRet.setResult(BasicRet.SUCCESS);
        return pageRet;
    }

    @RequestMapping(value = "/homepage/list", method = RequestMethod.POST)
    @ApiOperation(value = "首页获取相关文章列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "articleCategoryName", value = "获取相关文章类别的标题", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "num", value = "获取的条数", required = true, paramType = "query", dataType = "int"),
    })
    public BasicExtRet getHomePageArticleList(@RequestParam String articleCategoryName,
                                              @RequestParam int num) {
        BasicExtRet basicExtRet = new BasicExtRet();
        List<Article> articles = articleService.getHomePageArticleList(articleCategoryName, num);
        basicExtRet.setData(articles);
        basicExtRet.setResult(BasicRet.SUCCESS);
        basicExtRet.setMessage("获取成功");
        return basicExtRet;
    }

    @RequestMapping(value = "/app/list", method = RequestMethod.POST)
    @ApiOperation(value = "手机端获取咨讯列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "pageNo", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "pageSize", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "docName", value = "文章种类标题", required = true, paramType = "query", dataType = "string"),
    })
    public BasicExtRet getAppArticleList(int pageNo, int pageSize, String docName) {
        BasicExtRet pageRet = new BasicExtRet();
        pageRet.setMessage("成功");
        pageRet.setResult(BasicRet.SUCCESS);
        PageInfo pageInfo = articleService.selectAppArticleList(pageNo, pageSize, docName);
        pageRet.setData(pageInfo);
        return pageRet;
    }

    @RequestMapping(value = "/articleType/childList", method = RequestMethod.POST)
    @ApiOperation(value = "获取某个类型下的子类型列表")
    @ApiImplicitParam(name = "docName", value = "文章种类标题", required = true, paramType = "query", dataType = "string")
    public BasicExtRet getArticleTypeChildList(String docName) {
        BasicExtRet basicExtRet = new BasicExtRet();
        Map<String, Object> map = new HashMap<>();
        ArticleCategory parentArticle = articleCategoryService.getArticleCategoryByDocName(docName);
        if (parentArticle == null) {
            basicExtRet.setMessage("没有该类型");
            basicExtRet.setResult(BasicRet.ERR);
            return basicExtRet;
        } else {
            map.put("parent", parentArticle);
            map.put("child", articleCategoryService.getChildListByParentName(docName));
            basicExtRet.setData(map);
            basicExtRet.setMessage("成功");
            basicExtRet.setResult(BasicRet.SUCCESS);
            return basicExtRet;
        }
    }

    @RequestMapping(value = "/article/pc/list", method = RequestMethod.POST)
    @ApiOperation("分页获取某个文章类型下的文章列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "pageNo", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "pageSize", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "titleName", value = "titleName", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "articleCategoryId", value = "articleCategoryId", required = true, paramType = "query", dataType = "Long"),
    })
    public PageRet getArticleListByArticleTypeId(@RequestParam int pageNo,
                                                 @RequestParam int pageSize,
                                                 @RequestParam(required = false) String titleName,
                                                 @RequestParam(required = true) Long articleCategoryId) {
        PageRet pageRet = new PageRet();
        PageInfo pageInfo = articleService.getArticleListByArticleTypeId(pageNo, pageSize, titleName, articleCategoryId);
        pageRet.data.setPageInfo(pageInfo);
        pageRet.setMessage("获取成功");
        pageRet.setResult(BasicRet.SUCCESS);
        return pageRet;
    }

    @RequestMapping(value = "/article/detail", method = RequestMethod.POST)
    @ApiOperation("获取文章详情")
    @ApiImplicitParam(name = "articleId", value = "文章的id", required = true, paramType = "query", dataType = "Long")
    public BasicExtRet getArticleDetail(Long articleId) {
        BasicExtRet pageRet = new BasicExtRet();
        pageRet.setData(articleService.getArticleById(articleId));
        pageRet.setMessage("获取成功");
        pageRet.setResult(BasicRet.SUCCESS);
        return pageRet;
    }
}
