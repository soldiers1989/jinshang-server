package project.jinshang.mod_product.service;

import project.jinshang.mod_member.service.SellerCategoryService;
import project.jinshang.mod_product.bean.Categories;

/**
 * 产品分类修改同步线程
 *
 * @author xiazy
 * @create 2018-06-13 11:18
 **/
public class CategoriesRunnable  implements Runnable{
    private SellerCategoryService sellerCategoryService;
    private Categories categories;
    private short type;

    public CategoriesRunnable(SellerCategoryService sellerCategoryService, Categories categories, short type) {
        this.sellerCategoryService = sellerCategoryService;
        this.categories = categories;
        this.type = type;
    }

    @Override
    public void run() {
        sellerCategoryService.synCategories(type,categories);
    }
}
