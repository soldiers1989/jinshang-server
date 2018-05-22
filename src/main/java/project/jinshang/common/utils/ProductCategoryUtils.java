package project.jinshang.common.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import project.jinshang.mod_admin.mod_article.bean.ArticleCategory;
import project.jinshang.mod_admin.mod_article.bean.ArticleListCategory;
import project.jinshang.mod_product.bean.Categories;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * create : wyh
 * date : 2017/11/17
 */
public class ProductCategoryUtils {

    /**
     * 根据子类id 找最上级父类id
      * @param list
     * @param subid
     * @return
     */
   public  static  long get_top_parent_id(List<Categories> list , long subid){
       for(Categories productCategory : list){
           if(productCategory.getId() == subid){
                if(productCategory.getParentid() != 0){
                    return  get_top_parent_id(list,productCategory.getParentid());
                }else{
                    return  productCategory.getId();
                }
           }
       }

       return  -1;
   }



    public static List get_parent_list(List<Categories> list , long subid, List<Categories> resultList){
        for(Categories productCategory : list){
            if(productCategory.getId() == subid){
                resultList.add(productCategory);
                if(productCategory.getParentid() != 0){
                    get_parent_list(list,productCategory.getParentid(),resultList);
                }
            }
        }
        return  resultList;
    }

    /**
     * 查找上级分类
     * @param list
     * @param parendid
     * @return
     */
    public static Categories getByParendId(List<Categories> list, long parendid) {
        for (Categories productCategory : list) {
            if (productCategory.getId() == parendid) {
                return productCategory;
            }
        }
        return null;
    }


//    public static  List<Categories> toProdCate(List<Categories> list){
//        return  list;
//    }


    /**
     * 获取子类，带级别
     * @param list
     * @param pid
     * @param level
     * @return
     */
    public  static List<Categories> getChildsManyGroup(List<Categories> list, long pid,int level){
        List<Categories> arr = new ArrayList<Categories>();
        for(Categories location : list){
            if(pid == location.getParentid()){
                location.setLevel(level);

                if(location.getBrokeragerate() == null || location.getBrokeragerate().compareTo(new BigDecimal(-1)) == 0){
                   BigDecimal brokeragerate =  getBrokeragerate(list,location.getId());
                   location.setBrokeragerate(brokeragerate);
                }

                if(location.getServicesrate() == null || location.getServicesrate().compareTo(new BigDecimal(-1)) ==0){
                    BigDecimal serviceRate =  getServicesrate(list,location.getId());
                    location.setServicesrate(serviceRate);
                }

                location.setList(getChildsManyGroup(list, location.getId(),level+1));
                arr.add(location);
            }
        }
        return arr;
    }


    public  static List<Categories> getChildsManyGroupNoRate(List<Categories> list, long pid,int level){
        List<Categories> arr = new ArrayList<Categories>();
        for(Categories location : list){
            if(pid == location.getParentid()){
                location.setLevel(level);

                location.setList(getChildsManyGroup(list, location.getId(),level+1));
                arr.add(location);
            }
        }
        return arr;
    }


    /**
     * 获取子类
     * @param list
     * @param pid
     * @return
     */
    public  static List<Categories> getChildsManyGroup(List<Categories> list, long pid){
        List<Categories> arr = new ArrayList<Categories>();
        for(Categories location : list){
            if(pid == location.getParentid()){
                if(location.getBrokeragerate() == null || location.getBrokeragerate().compareTo(new BigDecimal(0)) == 0){
                    BigDecimal brokeragerate =  getBrokeragerate(list,location.getId());
                    location.setBrokeragerate(brokeragerate);
                }
                location.setList(getChildsManyGroup(list, location.getId()));
                arr.add(location);
            }
        }
        return arr;
    }



    public  static List<Categories> getChildsManyGroupNoRate(List<Categories> list, long pid){
        List<Categories> arr = new ArrayList<Categories>();
        for(Categories location : list){
            if(pid == location.getParentid()){
                location.setList(getChildsManyGroup(list, location.getId()));
                arr.add(location);
            }
        }
        return arr;
    }



    /**
     * 根据id 获取
     * @param list
     * @param id
     * @return
     */
    public  static  Categories getById(List<Categories> list , long id){
        for(Categories category : list){
            if(category.getId() == id){
                return  category;
            }
        }
        return  null;
    }


    /**
     * 获取佣金比率
     * @param list
     * @param id
     * @return
     */
    public  static BigDecimal getBrokeragerate(List<Categories> list , long id){
        for(Categories cate : list){
            if(cate.getId() == id){
                if(cate.getBrokeragerate().compareTo(new BigDecimal(-1)) == 0){  //-1 沿用上级
                   return getBrokeragerate(list,cate.getParentid());
                }else{
                    return  cate.getBrokeragerate();
                }
            }
        }
        return  new BigDecimal(0);
    }


    /**
     * 获取服务费比率
     * @param list
     * @param id
     * @return
     */
    public  static BigDecimal getServicesrate(List<Categories> list , long id){
        for(Categories cate : list){
            if(cate.getId() == id){
                if(cate.getServicesrate().compareTo(new BigDecimal(-1)) == 0){  //-1 沿用上级
                    return getServicesrate(list,cate.getParentid());
                }else{
                    return  cate.getServicesrate();
                }
            }
        }
        return  new BigDecimal(0);
    }




    /*
    获取文章分类所有列表
     */
    public static  List<ArticleListCategory> toArticleProdCate(List<ArticleCategory> list){
        List<ArticleListCategory> articleListCategories = new ArrayList<>();
        for(ArticleCategory articleCategory : list){
            ArticleListCategory articleListCategory = new ArticleListCategory();
            articleListCategory.setId(articleCategory.getId());
            articleListCategory.setDocname(articleCategory.getDocname());
            articleListCategory.setPraentid(articleCategory.getPraentid() == null ? 0 : articleCategory.getPraentid());
            articleListCategory.setDocorder(articleCategory.getDocorder()==null ? 0 : articleCategory.getDocorder());
            articleListCategory.setDocislist(articleCategory.getDocislist() == null ? (short) 0 : articleCategory.getDocislist());
            articleListCategories.add(articleListCategory);
        }
        return  articleListCategories;
    }

    public  static List<ArticleListCategory> getArticleChildsManyGroup(List<ArticleListCategory> list, long id){
        List<ArticleListCategory> articleList = new ArrayList<ArticleListCategory>();
        for(ArticleListCategory article : list){
            if(id == article.getPraentid()){
                article.setList(getArticleChildsManyGroup(list, article.getId()));
                articleList.add(article);
            }
        }
        return articleList;
    }





    public static void main(String[] args) {
//        List<ProductCategory> list = new ArrayList<>();
//        ProductCategory p1 = new ProductCategory();
//        p1.setBrokeragerate(new BigDecimal(0));
//        p1.setParentid(0);
//        p1.setName("aaaa");
//        p1.setId(135);
//        list.add(p1);
//
//
//        ProductCategory p2 = new ProductCategory();
//        p2.setBrokeragerate(new BigDecimal(0));
//        p2.setParentid(135);
//        p2.setName("bbb");
//        p2.setId(136);
//        list.add(p2);
//
//        ProductCategory p3 = new ProductCategory();
//        p3.setBrokeragerate(new BigDecimal(0));
//        p3.setParentid(136);
//        p3.setName("bbb");
//        p3.setId(137);
//        list.add(p3);
//
//
//
//        System.out.println(getBrokeragerate(list,137));
    }





}
