package com.imooc.mall.common;

import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * constant value
 */
@Component
public class Constant {
    public static final String IMOOC_MALL_USER = "imooc_mall_user";
    public static final String SALT = "8hjkijkUS.:[]}!&*)s452";

    //@Value("${file.upload.dir}")
    // static variable can't inject value
    public static String FILE_UPLOAD_DIR;
    @Value("${file.upload.dir}")
    public void setFileUploadDir(String fileUploadDir) {
        FILE_UPLOAD_DIR = fileUploadDir;
    }

    public interface ProductListOrderBy{
        Set<String> PRICE_ASC_DESC = Sets.newHashSet("price desc", "price asc");
    }

    public interface SaleStatus{
        int NOT_SALE = 0; //Listing status of the item
        int SALE = 1; //Merchandise off the shelves
    }
    public interface Cart{
        int UN_CHECKED = 0; //The shopping cart is not selected
        int CHECKED = 1; //Shopping cart selected state
    }
}
