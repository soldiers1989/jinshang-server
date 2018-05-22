package project.jinshang.mod_admin.mod_article.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_admin.mod_article.ArticleCategoryMapper;
import project.jinshang.mod_admin.mod_article.ArticleMapper;
import project.jinshang.mod_admin.mod_article.bean.Article;
import project.jinshang.mod_admin.mod_article.bean.ArticleCategory;
import project.jinshang.mod_admin.mod_article.bean.ArticleExample;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleCategoryMapper articleCategoryMapper;

    public void addArticle(Article article) {
        article.setCreattime(new Date());
        article.setUpdatetime(new Date());
        if (article.getDocshow() == null) {
            article.setDocshow((short) 1);
        }
        articleMapper.insert(article);
    }

    public void deleteArticle(long id) {
        articleMapper.deleteByPrimaryKey(id);
//        Article article = articleMapper.selectByPrimaryKey(id);
//        article.setDocstatus((short) 1);
//        articleMapper.updateByPrimaryKeySelective(article);
    }

    public void updateArticle(Article article) {
        article.setUpdatetime(new Date());
        articleMapper.updateByPrimaryKeySelective(article);
    }
  /*  public  List<Article>listAllArticle(){
        ArticleExample articleExample=new ArticleExample();
        return  articleMapper.selectByExample(articleExample);
    }*/

    public PageInfo listAllArticle(long id, long docid, int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        Article article = new Article();
//        if (id!=-1){
//            article.setId(id);
//        }else {
//            article.setId(null);
//        }
//        if (docid!=-1){
//            article.setDocid(docid);
//        }else {
//            article.setDocid(null);
//        }
        Map<String, Object> map = new HashMap<String, Object>();
        if (id == -1) {
            map.put("id", null);
        } else {
            map.put("id", id);
        }
        if (docid == -1) {
            map.put("docid", null);
        } else {
            map.put("docid", docid);
        }
        List<Map> list = articleMapper.getAllArticle(map);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    public PageInfo getArticleList(int pageNo, int pageSize, String titleName, Long articleCategoryId) {
        PageHelper.startPage(pageNo, pageSize);

        ArticleExample articleExample = new ArticleExample();
        ArticleExample.Criteria criteria = articleExample.createCriteria();

        if (StringUtil.isNotEmpty(titleName)) {
            criteria.andDoctitleLike("%" + titleName + "%");
        }

        if (articleCategoryId != null) {
            ArticleCategory articleCategory = articleCategoryMapper.selectByPrimaryKey(articleCategoryId);
            if (articleCategory.getPraentid() == 0) {
                List<Long> articleCategorieIds = articleCategoryMapper.selectIdsByParentId(articleCategory.getId());
                criteria.andDocidIn(articleCategorieIds);
            } else {
                criteria.andDocidEqualTo(articleCategoryId);
            }
        }
        articleExample.setOrderByClause("docorder asc");

        List<Article> articles = articleMapper.selectByExample(articleExample);

        PageInfo pageInfo = new PageInfo(articles);
        for (Object o : pageInfo.getList()) {
            Article article = (Article) o;
            if (article.getDocid() != null) {
                ArticleCategory articleCategory = articleCategoryMapper.selectByPrimaryKey(article.getDocid());
                if (articleCategory != null) {
                    article.setDocName(articleCategory.getDocname());
                }
            }
        }
        return pageInfo;
    }

    public int getArticleCountByArticleCategoryId(Long articleCategoryId) {
        ArticleExample articleExample = new ArticleExample();
        ArticleExample.Criteria criteria = articleExample.createCriteria();
        criteria.andDocidEqualTo(articleCategoryId);
        return articleMapper.countByExample(articleExample);
    }

    public List<Article> getHomePageArticleList(String articleCategoryName, int num) {
        return articleMapper.searchListByArticleCategoryName(articleCategoryName, num);
    }

    public Article selectArticleByTitleName(String titleName) {
        ArticleExample example = new ArticleExample();
        ArticleExample.Criteria criteria = example.createCriteria();
        criteria.andDoctitleEqualTo(titleName);
        List<Article> articles = articleMapper.selectByExample(example);
        if (articles.size() != 0) {
            return articles.get(0);
        } else {
            return null;
        }
    }

    public PageInfo selectAppArticleList(int pageNo, int pageSize, String docName) {
        PageHelper.startPage(pageNo, pageSize);
        List<Article> articles = articleMapper.selectAppArticleList(docName);
        PageInfo pageInfo = new PageInfo(articles);
        return pageInfo;
    }

    public List<Article> getArticleListBySortId(Long docId, int sortId) {
        ArticleExample example = new ArticleExample();
        ArticleExample.Criteria criteria = example.createCriteria();
        criteria.andDocorderEqualTo(sortId);
        criteria.andDocidEqualTo(docId);
        return articleMapper.selectByExample(example);
    }

    public int maxSortIdByDocId(Long docId) {
        ArticleExample example = new ArticleExample();
        ArticleExample.Criteria criteria = example.createCriteria();
        criteria.andDocidEqualTo(docId);
        example.setOrderByClause("docorder desc");
        List<Article> articles = articleMapper.selectByExample(example);
        if (articles.size() == 0) {
            return 0;
        } else {
            return articles.get(0).getDocorder();
        }
    }

    public Article getArticleById(Long id) {
        return articleMapper.selectByPrimaryKey(id);
    }

    public PageInfo getArticleListByArticleTypeId(int pageNo, int pageSize, String titleName, Long articleCategoryId) {
        ArticleCategory articleCategory = articleCategoryMapper.selectByPrimaryKey(articleCategoryId);
        List<Long> childIds = articleCategoryMapper.selectIdsByParentId(articleCategoryId);
        String ids = "";
        if (articleCategory.getPraentid() != 0) {
            ids = String.valueOf(articleCategoryId);
        } else {
            if (childIds.size() != 0) {
                ids = "";
                for (Long id : childIds) {
                    ids = ids + id + ",";
                }
                ids = ids.substring(0, ids.length() - 1);
            }
        }
        PageHelper.startPage(pageNo, pageSize);
        if (titleName != null) {
            titleName = "%" + titleName + "%";
        } else {
            titleName = "%";
        }
        List<Article> articles = articleMapper.selectArticleListByTypeIds(titleName, ids);
        PageInfo pageInfo = new PageInfo(articles);
        return pageInfo;
    }
}
