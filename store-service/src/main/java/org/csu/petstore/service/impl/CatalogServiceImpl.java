package org.csu.petstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.csu.petstore.Vo.CategoryVO;
import org.csu.petstore.Vo.ItemVO;
import org.csu.petstore.Vo.ProductVO;
import org.csu.petstore.entity.Category;
import org.csu.petstore.entity.Item;
import org.csu.petstore.entity.ItemQuantity;
import org.csu.petstore.entity.Product;
import org.csu.petstore.persistence.CategoryMapper;
import org.csu.petstore.persistence.ItemMapper;
import org.csu.petstore.persistence.ItemQuantityMapper;
import org.csu.petstore.persistence.ProductMapper;
import org.csu.petstore.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("catalogService")
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private ItemQuantityMapper itemQuantityMapper;

    @Override
    public CategoryVO getCategoryVO(String categoryId) {
        CategoryVO categoryVO = new CategoryVO();
        QueryWrapper<Category> categoryQueryWrapper = new QueryWrapper<>();
        categoryQueryWrapper.eq("catid", categoryId);
        Category category = categoryMapper.selectOne(categoryQueryWrapper);

        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
        productQueryWrapper.eq("category", categoryId);
        List<Product> productList = productMapper.selectList(productQueryWrapper);

        categoryVO.setCategoryId(categoryId);
        categoryVO.setCategoryName(category.getName());
        categoryVO.setProductList(productList);
        return categoryVO;
    }

    @Override
    public ProductVO getProductVO(String productId) {
        ProductVO productVO = new ProductVO();
        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
        productQueryWrapper.eq("productid", productId);
        Product product = productMapper.selectOne(productQueryWrapper);

        QueryWrapper<Item> itemQueryWrapper = new QueryWrapper<>();
        itemQueryWrapper.eq("productid", productId);
        List<Item> itemList = itemMapper.selectList(itemQueryWrapper);

        productVO.setProductId(productId);
        productVO.setCategoryId(product.getCategoryId());
        productVO.setProductName(product.getName());
        productVO.setItemList(itemList);
        return productVO;
    }

    @Override
    public ItemVO getItemVO(String itemId) {
        ItemVO itemVO = new ItemVO();
        QueryWrapper<Item> itemQueryWrapper = new QueryWrapper<>();
        itemQueryWrapper.eq("itemid", itemId);
        Item item = itemMapper.selectOne(itemQueryWrapper);

        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
        productQueryWrapper.eq("productid", item.getProductId());
        Product product = productMapper.selectOne(productQueryWrapper);

        QueryWrapper<ItemQuantity> itemQuantityQueryWrapper = new QueryWrapper<>();
        itemQuantityQueryWrapper.eq("itemid", itemId);
        ItemQuantity itemQuantity = itemQuantityMapper.selectOne(itemQuantityQueryWrapper);

        itemVO.setItemId(itemId);
        itemVO.setListPrice(item.getListPrice());
        itemVO.setAttributes(item.getAttribute1());
        itemVO.setProductId(product.getProductId());
        itemVO.setProductName(product.getName());
//        itemVO.setDescription(product.getDescription());
        String[] temp = product.getDescription().split("\"");
        if(temp.length > 2) {
            itemVO.setDescriptionImage(temp[1]);
            itemVO.setDescriptionText(temp[2].substring(1));
        }
        else {
            itemVO.setDescriptionImage("");
            itemVO.setDescriptionText(temp[0]);
        }
        itemVO.setQuantity(itemQuantity.getQuantity());
        return itemVO;
    }

    @Override
    public Item getItem(String itemId) {
        QueryWrapper<Item> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("itemid", itemId);
        return itemMapper.selectOne(queryWrapper);
    }

    @Override
    public Product getProduct(String productId) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("productid", productId);
        return productMapper.selectOne(queryWrapper);
    }

    @Override
    public Category getCategory(String categoryId) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("catid", categoryId);
        return categoryMapper.selectOne(queryWrapper);
    }

    @Override
    public List<Product> getProductListByCategory(String categoryId) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category", categoryId);
        return productMapper.selectList(queryWrapper);
    }

    @Override
    public List<Category> getAllCategories() {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("deleted", 0);
        return categoryMapper.selectList(queryWrapper);
    }

    @Override
    public void addCategory(Category category) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("catid", category.getCategoryId());
        if (categoryMapper.selectOne(queryWrapper) != null) {
            categoryMapper.update(category, queryWrapper);
        } else categoryMapper.insert(category);
    }

    @Override
    public void deleteCategory(String categoryId) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("catid", categoryId);
        List<Product> productList = getProductListByCategory(categoryId);
        for (Product product : productList) {
            deleteProduct(product.getProductId());
        }
        categoryMapper.delete(queryWrapper);
    }

    @Override
    public void addProduct(Product product) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("productid", product.getProductId());
        Product existingProduct = productMapper.selectOne(queryWrapper);
        
        if (existingProduct != null) {
            productMapper.update(product, queryWrapper);
        } else productMapper.insert(product);
    }

    @Override
    public void deleteProduct(String productId) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("productid", productId);
        List<Item> itemList = getItemsByProduct(productId);
        for (Item item : itemList) {
            deleteItem(item.getItemId(), productId);
        }
        productMapper.delete(queryWrapper);
    }

    @Override
    public void addItem(Item item) {
        QueryWrapper<Item> itemQueryWrapper = new QueryWrapper<>();
        QueryWrapper<ItemQuantity> itemQuantityQueryWrapper = new QueryWrapper<>();
        itemQueryWrapper.eq("itemid", item.getItemId());
        itemQuantityQueryWrapper.eq("itemid", item.getItemId());
        ItemQuantity itemQuantity = itemQuantityMapper.selectOne(itemQuantityQueryWrapper);
        if (itemMapper.selectOne(itemQueryWrapper) != null) {
            itemMapper.update(item, itemQueryWrapper);
            itemQuantityMapper.update(itemQuantity, itemQuantityQueryWrapper);
        } else {
            itemMapper.insert(item);
            itemQuantity=new ItemQuantity();
            itemQuantity.setItemId(item.getItemId());
            itemQuantity.setQuantity(10000);
            itemQuantityMapper.insert(itemQuantity);
        }
    }

    @Override
    public void deleteItem(String itemId, String productId) {
        QueryWrapper<Item> itemQueryWrapper = new QueryWrapper<>();
        QueryWrapper<ItemQuantity> itemQuantityQueryWrapper = new QueryWrapper<>();
        itemQueryWrapper.eq("itemid", itemId);
        itemQuantityQueryWrapper.eq("itemid", itemId);
        itemMapper.delete(itemQueryWrapper);
        itemQuantityMapper.delete(itemQuantityQueryWrapper);
    }

    @Override
    public List<Item> getItemsByProduct(String productId) {
        QueryWrapper<Item> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("productid", productId);
        return itemMapper.selectList(queryWrapper);
    }

    @Override
    public void updateCategory(Category category) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("catid", category.getCategoryId());
        categoryMapper.update(category, queryWrapper);
    }

    @Override
    public void updateProduct(Product product) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("productid", product.getProductId());
        productMapper.update(product, queryWrapper);
    }

    @Override
    public void updateItem(Item item) {
        QueryWrapper<Item> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("itemid", item.getItemId());
        itemMapper.update(item, queryWrapper);
    }

    @Override
    public List<Category> searchCategories(String keyword) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("deleted", 0);
        if (keyword != null && !keyword.isEmpty()) {
            String lowerKeyword = keyword.toLowerCase();
            queryWrapper.and(wrapper -> wrapper
                .apply("LOWER(catid) LIKE {0}", "%" + lowerKeyword + "%")
                .or().apply("LOWER(name) LIKE {0}", "%" + lowerKeyword + "%")
                .or().apply("LOWER(descn) LIKE {0}", "%" + lowerKeyword + "%")
            );
        }
        return categoryMapper.selectList(queryWrapper);
    }

    @Override
    public List<Product> searchProducts(String categoryId, String keyword) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category", categoryId);
        if (keyword != null && !keyword.isEmpty()) {
            String lowerKeyword = keyword.toLowerCase();
            queryWrapper.and(wrapper -> wrapper
                .apply("LOWER(productid) LIKE {0}", "%" + lowerKeyword + "%")
                .or().apply("LOWER(name) LIKE {0}", "%" + lowerKeyword + "%")
                .or().apply("LOWER(descn) LIKE {0}", "%" + lowerKeyword + "%")
            );
        }
        return productMapper.selectList(queryWrapper);
    }

    @Override
    public List<Item> searchItems(String productId, String keyword) {
        QueryWrapper<Item> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("productid", productId);
        if (keyword != null && !keyword.isEmpty()) {
            String lowerKeyword = keyword.toLowerCase();
            queryWrapper.and(wrapper -> wrapper
                .apply("LOWER(itemid) LIKE {0}", "%" + lowerKeyword + "%")
                .or().apply("LOWER(status) LIKE {0}", "%" + lowerKeyword + "%")
                .or().apply("LOWER(attr1) LIKE {0}", "%" + lowerKeyword + "%")
                .or().apply("LOWER(attr2) LIKE {0}", "%" + lowerKeyword + "%")
                .or().apply("LOWER(attr3) LIKE {0}", "%" + lowerKeyword + "%")
                .or().apply("LOWER(attr4) LIKE {0}", "%" + lowerKeyword + "%")
                .or().apply("LOWER(attr5) LIKE {0}", "%" + lowerKeyword + "%")
            );
        }
        return itemMapper.selectList(queryWrapper);
    }
}
