package org.csu.petstore.service;

import org.csu.petstore.Vo.CategoryVO;
import org.csu.petstore.Vo.ItemVO;
import org.csu.petstore.Vo.ProductVO;
import org.csu.petstore.entity.Category;
import org.csu.petstore.entity.Item;
import org.csu.petstore.entity.Product;

import java.util.List;

public interface CatalogService {

    CategoryVO getCategoryVO(String categoryId);

    ProductVO getProductVO(String productId);

    ItemVO getItemVO(String itemId);

    Item getItem(String itemId);

    Product getProduct(String productId);

    Category getCategory(String categoryId);

    List<Product> getProductListByCategory(String categoryId);

    List<Category> getAllCategories();

    void addCategory(Category category);

    void deleteCategory(String categoryId);

    void addProduct(Product product);

    void deleteProduct(String productId);

    void addItem(Item item);

    void deleteItem(String itemId, String productId);

    List<Item> getItemsByProduct(String productId);

    void updateCategory(Category category);

    void updateProduct(Product product);

    void updateItem(Item item);

    List<Category> searchCategories(String keyword);

    List<Product> searchProducts(String categoryId, String keyword);

    List<Item> searchItems(String productId, String keyword);

}
