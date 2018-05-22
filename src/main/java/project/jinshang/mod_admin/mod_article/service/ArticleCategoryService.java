package project.jinshang.mod_admin.mod_article.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_admin.mod_article.ArticleCategoryMapper;
import project.jinshang.mod_admin.mod_article.bean.ArticleCategory;
import project.jinshang.mod_admin.mod_article.bean.ArticleCategoryExample;
import project.jinshang.mod_admin.mod_article.bean.ArticleListCategory;
import project.jinshang.mod_product.bean.Categories;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleCategoryService {
    @Autowired
    private ArticleCategoryMapper articleCategoryMapper;

    /**
     * 添加分类
     */
    public void addClassify(ArticleCategory articleCategory) {
        articleCategoryMapper.insert(articleCategory);

    }

    public void deleteClsaaify(long id) {

        articleCategoryMapper.deleteByPrimaryKey(id);
    }

    public ArticleCategory selectById(long id) {
        return articleCategoryMapper.selectById(id);
    }

    public int selectCountByPraentId(long id) {
        return articleCategoryMapper.selectCountByPraentId(id);

    }

    public void updateClsaafiy(ArticleCategory articleCategory) {
        articleCategoryMapper.updateByPrimaryKeySelective(articleCategory);
    }

    public List<ArticleCategory> listAllClassfiy() {
        ArticleCategoryExample articleCategoryExample = new ArticleCategoryExample();
        return articleCategoryMapper.selectByExample(articleCategoryExample);
    }

    /**
     * 获取全部（id,name,parentid,sort）
     *
     * @return
     */
    public List<ArticleCategory> getAll() {
        return articleCategoryMapper.getAll();
    }

    public List<ArticleCategory> getArticleCategoryList() {
        ArticleCategoryExample articleCategoryExample = new ArticleCategoryExample();
        ArticleCategoryExample.Criteria criteria = articleCategoryExample.createCriteria();

        criteria.andPraentidEqualTo(0l);
        articleCategoryExample.setOrderByClause("docorder asc");

        List<ArticleCategory> articleCategories = articleCategoryMapper.selectByExample(articleCategoryExample);

        for (ArticleCategory articleCategory : articleCategories) {
            ArticleCategoryExample childrenArticleCategoryExample = new ArticleCategoryExample();
            ArticleCategoryExample.Criteria childrenCriteria = childrenArticleCategoryExample.createCriteria();
            childrenCriteria.andPraentidEqualTo(articleCategory.getId());
            childrenArticleCategoryExample.setOrderByClause("docorder asc");
            List<ArticleCategory> childrenArticleCategories = articleCategoryMapper.selectByExample(childrenArticleCategoryExample);
            articleCategory.setChildrenArticleCategory(childrenArticleCategories);
        }

        return articleCategories;
    }

    public List<ArticleCategory> getChildListByParentName(String docName) {
        return articleCategoryMapper.getChildListByParentName(docName);
    }

    public ArticleCategory getArticleCategoryByDocName(String docName) {
        ArticleCategoryExample example = new ArticleCategoryExample();
        ArticleCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andDocnameEqualTo(docName);
        List<ArticleCategory> articleCategories = articleCategoryMapper.selectByExample(example);
        if (articleCategories.size() == 0) {
            return null;
        } else {
            return articleCategories.get(0);
        }
    }

    public int getMaxSortIdByDocName(String docName) {
        if (docName == null) {
            ArticleCategoryExample example = new ArticleCategoryExample();
            ArticleCategoryExample.Criteria criteria = example.createCriteria();
            criteria.andPraentidEqualTo((long) 0);
            example.setOrderByClause("docorder desc");
            List<ArticleCategory> articleCategories = articleCategoryMapper.selectByExample(example);
            if (articleCategories.size() == 0) {
                return 0;
            } else {
                return articleCategories.get(0).getDocorder();
            }

        } else {
            List<ArticleCategory> articleCategories = articleCategoryMapper.getChildListByParentName(docName);
            if (articleCategories.size() == 0) {
                return 0;
            } else {
                return articleCategories.get(articleCategories.size() - 1).getDocorder();
            }
        }
    }

    public int getMaxSortIdByParentId(Long parentId) {
        ArticleCategoryExample example = new ArticleCategoryExample();
        ArticleCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andPraentidEqualTo(parentId);
        example.setOrderByClause("docorder desc");
        List<ArticleCategory> articleCategories = articleCategoryMapper.selectByExample(example);
        if (articleCategories.size() == 0) {
            return 0;
        } else {
            return articleCategories.get(0).getDocorder();
        }
    }

    public List<ArticleCategory> getArticleCategoryListBySortId(Long parentId, int sortId) {
        ArticleCategoryExample example = new ArticleCategoryExample();
        ArticleCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andPraentidEqualTo(parentId);
        criteria.andDocorderEqualTo(sortId);
        return articleCategoryMapper.selectByExample(example);
    }

    public ArticleCategory getArticleCategoryById(Long id) {
        return articleCategoryMapper.selectByPrimaryKey(id);
    }
}
