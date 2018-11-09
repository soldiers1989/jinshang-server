package project.jinshang.mod_front;

import com.github.pagehelper.PageInfo;
import com.thoughtworks.xstream.mapper.Mapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.jinshang.common.bean.PageRet;
import project.jinshang.common.constant.Quantity;
import project.jinshang.common.utils.ProductCategoryUtils;
import project.jinshang.mod_admin.mod_article.bean.Article;
import project.jinshang.mod_admin.mod_article.bean.ArticleCategory;
import project.jinshang.mod_admin.mod_article.bean.ArticleListCategory;
import project.jinshang.mod_admin.mod_article.service.ArticleCategoryService;
import project.jinshang.mod_admin.mod_article.service.ArticleService;
import project.jinshang.mod_common.bean.BasicExtRet;

import java.io.PipedOutputStream;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = {"/rest/front/NewArticeFront"})
@Api(tags = "文章分类、内容列表", description = "前台显示文章列表接口")
public class NewArticeFrontAction {

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

//    @RequestMapping(value = "/article/pc/list", method = RequestMethod.POST)
//    @ApiOperation("分页获取某个文章类型下的文章列表")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "pageNo", value = "pageNo", required = true, paramType = "query", dataType = "int"),
//            @ApiImplicitParam(name = "pageSize", value = "pageSize", required = true, paramType = "query", dataType = "int"),
//            @ApiImplicitParam(name = "articleCategoryId", value = "articleCategoryId", required = false, paramType = "query", dataType = "Long"),
//    })
//    public ArticleRet getArticleListByArticleTypeId(@RequestParam int pageNo,
//                                                 @RequestParam int pageSize,
//                                                 @RequestParam(required = false) Long articleCategoryId) {
//        ArticleRet pageRet = new ArticleRet();
//        PageInfo pageInfo = articleService.getArticleListByArticleTypeId(pageNo, pageSize,articleCategoryId);
//        List<Article> articleList=pageInfo.getList();
//        Map<Long,List> listmap=new HashMap();
//        List<ArticleCategory> categoryList=new ArrayList<>();
//        List<ArticleCategory> categoryList1=new ArrayList<>();
//        List<ArticleCategory> categoryList2=new ArrayList<>();
//        List<ArticleCategory> categoryList3=new ArrayList<>();
//        if (articleList!=null&&articleList.size()>0){
//            for (Article article:articleList){
//                Long categoryId=article.getDocid();
//                List<Article> articleList1=new ArrayList<>();
//                if (listmap.get(categoryId)== null){
//                    articleList1.add(article);
//                }else {
//                    articleList1= (List) listmap.get(categoryId);
//                    articleList1.add(article);
//                }
//                listmap.put(categoryId,articleList1);
//            }
//        }
//        if (pageNo!=Quantity.INT_0&&pageSize!=Quantity.INT_0){
//            for (Map.Entry<Long,List> entry:listmap.entrySet()){
//                List<Article> list=entry.getValue();
//                if (list.size()>Quantity.STATE_5){
//                    entry.setValue(list.subList(0,Quantity.STATE_5));
//                }
//            }
//        }
//        for (Map.Entry<Long,List> entry:listmap.entrySet()){
//            ArticleCategory articleCategory=articleCategoryService.getArticleCategoryById(entry.getKey());
//            articleCategory.setArticleList(entry.getValue());
//            switch (articleCategory.getDocname()){
//                case Quantity.PICTURE:
//                    categoryList2.add(articleCategory);
//                    break;
//                case Quantity.VIDEO:
//                    categoryList3.add(articleCategory);
//                    break;
//                default:
//                    categoryList.add(articleCategory);
//            }
//        }
//        categoryList=categoryList.stream().sorted(Comparator.comparing(ArticleCategory::getDocorder)).collect(Collectors.toList());
//        for (ArticleCategory articleCategory:categoryList){
//            if (articleCategory.getPraentid()!=117&&articleCategory.getId()!=111){
//                categoryList1.add(articleCategory);
//            }
//        }
//        pageRet.setData1(categoryList1);
//        pageRet.setData2(categoryList2);
//        pageRet.setData3(categoryList3);
////        Collections.sort(categoryList);
////        categoryList.stream().sorted(Comparator.comparing(l->l.getDocorder(), Comparator.nullsFirst(ArticleCategory::compareTo).reversed())).collect(Collectors.toList());
////        categoryList.sort(Comparator.nullsFirst(Comparator.comparing(ArticleCategory::getDocorder)));
//        pageRet.data.setPageInfo(pageInfo);
//        pageRet.setMessage("获取成功");
//        pageRet.setResult(BasicRet.SUCCESS);
//        return pageRet;
//    }



    @RequestMapping(value = "/article/getArticleListByCatename", method = RequestMethod.POST)
    @ApiOperation("获取某个文章类型下的文章列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "count", value = "个数", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "catename", value = "分类名", required = true, paramType = "query", dataType = "string"),
    })
    public ArticleListRet getArticleListByCatename(@RequestParam int count,
                                                    @RequestParam String catename) {
        ArticleListRet articleListRet = new ArticleListRet();

        ArticleCategory category = articleCategoryService.getArticleCategoryByDocName(catename);

        if(category == null){
            articleListRet.setResult(BasicRet.ERR);
            articleListRet.setMessage("分类不存在");
            return articleListRet;
        }

        List<Article> list = articleService.searchArticleByDocIdSortBySort(count,category.getId());
        articleListRet.data.list = list;
        articleListRet.data.articleCategory = category;
        articleListRet.setResult(BasicRet.SUCCESS);
        return articleListRet;
    }


    @RequestMapping(value = "/article/listPageByCateid", method = RequestMethod.POST)
    @ApiOperation("分页获取某个文章类型下的文章列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "pageNo", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "pageSize", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "articleCategoryId", value = "articleCategoryId", required = true, paramType = "query", dataType = "Long"),
    })
    public ArticlePageRet getArticleListByArticleTypeId(@RequestParam int pageNo,
                                                    @RequestParam int pageSize,
                                                    @RequestParam(required = true) Long articleCategoryId) {
        ArticlePageRet pageRet = new ArticlePageRet();

        ArticleCategory articleCategory = articleCategoryService.getArticleCategoryById(articleCategoryId);

        if(articleCategory == null){
            pageRet.setMessage("分类不存在");
            pageRet.setResult(BasicRet.ERR);
            return pageRet;
        }

        PageInfo pageInfo = articleService.getArticlePageByDocid(pageNo, pageSize, articleCategoryId);

        pageRet.data.articleCategory = articleCategory;
        pageRet.data.pageInfo = pageInfo;
        pageRet.setResult(BasicRet.SUCCESS);
        return pageRet;
    }



    private class ArticlePageRet extends BasicRet{
        private class ArticlePageData{
            private PageInfo pageInfo;
            private ArticleCategory articleCategory;

            public PageInfo getPageInfo() {
                return pageInfo;
            }

            public void setPageInfo(PageInfo pageInfo) {
                this.pageInfo = pageInfo;
            }

            public ArticleCategory getArticleCategory() {
                return articleCategory;
            }

            public void setArticleCategory(ArticleCategory articleCategory) {
                this.articleCategory = articleCategory;
            }
        }

        private ArticlePageData data = new ArticlePageData();

        public ArticlePageData getData() {
            return data;
        }

        public void setData(ArticlePageData data) {
            this.data = data;
        }
    }






    private class ArticleListRet extends BasicRet{
        private class ArticleListData{
            private List<Article> list;
            private ArticleCategory articleCategory;

            public ArticleCategory getArticleCategory() {
                return articleCategory;
            }

            public void setArticleCategory(ArticleCategory articleCategory) {
                this.articleCategory = articleCategory;
            }

            public List<Article> getList() {
                return list;
            }

            public void setList(List<Article> list) {
                this.list = list;
            }
        }

        private ArticleListData data = new ArticleListData();

        public ArticleListData getData() {
            return data;
        }

        public void setData(ArticleListData data) {
            this.data = data;
        }
    }





    @RequestMapping(value = "/article/detail", method = RequestMethod.POST)
    @ApiOperation("获取文章详情")
    @ApiImplicitParam(name = "articleId", value = "文章的id", required = true, paramType = "query", dataType = "Long")
    public BasicExtRet getArticleDetail(Long articleId) {
        BasicExtRet pageRet = new BasicExtRet();
        pageRet.setData(articleService.selectArticleDetail(articleId));
        pageRet.setMessage("获取成功");
        pageRet.setResult(BasicRet.SUCCESS);
        return pageRet;
    }

    @PostMapping(value = "/getArticleCarousel")
    @ApiOperation("获取资讯中心首页的文章轮播图")
    public BasicRet getArticleCarousel(Model model){
        BasicExtRet basicExtRet=new BasicExtRet();
        List<Article> list=articleService.getArticleCarousel();
        if (list!=null&&list.size()>0){
            for (Article article:list){
                if (article.getType().compareTo(Quantity.STATE_1)==0){
                    article.setPic(article.getPicList().get(0).getPath());
                }
            }
        }
        basicExtRet.setData(list);
        basicExtRet.setMessage("获取资讯中心首页的文章轮播图成功");
        basicExtRet.setResult(BasicRet.SUCCESS);
        return basicExtRet;
    }

    @PostMapping(value = "/getRecommendArticel")
    @ApiOperation("为您推荐列表获取")
    public BasicExtRet getRecommendArticel(Model model,@RequestParam int size){
        BasicExtRet basicExtRet=new BasicExtRet();
        basicExtRet.setResult(BasicRet.SUCCESS);
        basicExtRet.setMessage("推荐列表获取成功");
        List<Article> list=articleService.selectRecomendArticle(size);
        basicExtRet.setData(list);
        return basicExtRet;
    }

    private class  ArticleRet extends  PageRet{
        //文章类型
        public   List<ArticleCategory> data1 = new ArrayList<>();
        //图片类型
        public   List<ArticleCategory> data2 = new ArrayList<>();

        //视频类型
        public   List<ArticleCategory> data3 = new ArrayList<>();


        //列表
        public List<Article> data4=new ArrayList<>();

        public List<ArticleCategory> getData1() {
            return data1;
        }

        public void setData1(List<ArticleCategory> data1) {
            this.data1 = data1;
        }

        public List<ArticleCategory> getData2() {
            return data2;
        }

        public void setData2(List<ArticleCategory> data2) {
            this.data2 = data2;
        }

        public List<ArticleCategory> getData3() {
            return data3;
        }

        public void setData3(List<ArticleCategory> data3) {
            this.data3 = data3;
        }

        public List<Article> getData4() {
            return data4;
        }

        public void setData4(List<Article> data4) {
            this.data4 = data4;
        }
    }
}
