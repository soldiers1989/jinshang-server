package project.jinshang.mod_common;

import org.springframework.beans.factory.annotation.Autowired;
import project.jinshang.mod_product.service.CategoriesService;

import java.io.File;

/**
 * UpdateProdCategory
 *
 * @author xiazy
 * @create 2018-07-27 14:50
 **/
public class UpdateProdCategory implements Runnable{
    private CategoriesService categoriesService;
    private File  file;

    public UpdateProdCategory(CategoriesService categoriesService, File file) {
        this.categoriesService = categoriesService;
        this.file = file;
    }

    @Override
    public void run() {
        try {
            categoriesService.processProductCategoryForImport(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
