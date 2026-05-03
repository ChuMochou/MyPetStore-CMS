package org.csu.petstore.controller;

import jakarta.servlet.http.HttpSession;
import org.csu.petstore.Vo.CategoryVO;
import org.csu.petstore.Vo.ItemVO;
import org.csu.petstore.Vo.ProductVO;
import org.csu.petstore.entity.Category;
import org.csu.petstore.entity.Item;
import org.csu.petstore.entity.Product;
import org.csu.petstore.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/catalog")
public class CatalogController {

    @Autowired
    private CatalogService catalogService;
    @Autowired
    private HttpSession session;

    @GetMapping("/categories")
    public String manageCategories(@RequestParam(required = false) String keyword,
                                   @RequestParam(required = false) String editCategoryId, Model model) {
        if (session.getAttribute("loginAccount") == null) {
            return "redirect:/account/signOnForm";
        }
        List<Category> categories;
        if (keyword != null && !keyword.isEmpty()) {
            categories = catalogService.searchCategories(keyword);
            model.addAttribute("keyword", keyword);
        } else {
            categories = catalogService.getAllCategories();
        }
        model.addAttribute("categories", categories);

        // If editing, load the category data
        if (editCategoryId != null && !editCategoryId.isEmpty()) {
            Category editCategory = catalogService.getCategory(editCategoryId);
            if (editCategory != null) {
                model.addAttribute("editCategory", editCategory);
            }
        }
        return "admin/catalog/category";
    }

    @PostMapping("/categories/add")
    public String addCategory(@RequestParam String categoryId,
                              @RequestParam String name,
                              @RequestParam(required = false) String description) {
        Category category = new Category();
        category.setCategoryId(categoryId);
        category.setName(name);
        category.setDescription(description != null ? description : "");
        catalogService.addCategory(category);
        return "redirect:/catalog/categories";
    }

    @PostMapping("/categories/delete")
    public String deleteCategory(@RequestParam String categoryId) {
        catalogService.deleteCategory(categoryId);
        return "redirect:/catalog/categories";
    }

    @PostMapping("/categories/update")
    public String updateCategory(@RequestParam String categoryId,
                                 @RequestParam String name,
                                 @RequestParam(required = false) String description) {
        Category category = new Category();
        category.setCategoryId(categoryId);
        category.setName(name);
        category.setDescription(description != null ? description : "");
        catalogService.updateCategory(category);
        return "redirect:/catalog/categories";
    }

    @GetMapping("/products")
    public String manageProducts(@RequestParam String categoryId,
                                 @RequestParam(required = false) String keyword,
                                 @RequestParam(required = false) String editProductId,
                                 Model model) {
        if (session.getAttribute("loginAccount") == null) {
            return "redirect:/account/signOnForm";
        }
        List<Product> products;
        if (keyword != null && !keyword.isEmpty()) {
            products = catalogService.searchProducts(categoryId, keyword);
            model.addAttribute("keyword", keyword);
        } else {
            products = catalogService.getProductListByCategory(categoryId);
        }
        Category category = catalogService.getCategory(categoryId);
        model.addAttribute("products", products);
        model.addAttribute("categoryName", categoryId);
        model.addAttribute("categoryId", categoryId);

        // If editing, load the product data
        if (editProductId != null && !editProductId.isEmpty()) {
            Product editProduct = catalogService.getProduct(editProductId);
            if (editProduct != null) {
                model.addAttribute("editProduct", editProduct);
            }
        }

        return "admin/catalog/product";
    }

    @PostMapping("/products/add")
    public String addProduct(@RequestParam String categoryId,
                             @RequestParam String productId,
                             @RequestParam String name,
                             @RequestParam(required = false) String description) {
        Product product = new Product();
        product.setCategoryId(categoryId);
        product.setProductId(productId);
        product.setName(name);
        product.setDescription(description != null ? description : "");

        catalogService.addProduct(product);
        return "redirect:/catalog/products?categoryId=" + categoryId;
    }

    @PostMapping("/products/delete")
    public String deleteProduct(@RequestParam String productId,
                                @RequestParam String categoryId) {
        catalogService.deleteProduct(productId);
        return "redirect:/catalog/products?categoryId=" + categoryId;
    }

    @PostMapping("/products/update")
    public String updateProduct(@RequestParam String categoryId,
                                @RequestParam String productId,
                                @RequestParam String name,
                                @RequestParam(required = false) String description) {
        Product product = new Product();
        product.setProductId(productId);
        product.setCategoryId(categoryId);
        product.setName(name);
        product.setDescription(description != null ? description : "");
        catalogService.updateProduct(product);
        return "redirect:/catalog/products?categoryId=" + categoryId;
    }

    @GetMapping("/items")
    public String manageItems(@RequestParam String productId,
                              @RequestParam(required = false) String keyword,
                              @RequestParam(required = false) String editItemId,
                              Model model) {
        if (session.getAttribute("loginAccount") == null) {
            return "redirect:/account/signOnForm";
        }
        List<Item> items;
        if (keyword != null && !keyword.isEmpty()) {
            items = catalogService.searchItems(productId, keyword);
            model.addAttribute("keyword", keyword);
        } else {
            items = catalogService.getItemsByProduct(productId);
        }
        Product product = catalogService.getProduct(productId);
        model.addAttribute("items", items);
        model.addAttribute("productName", product.getName());
        model.addAttribute("productId", productId);
        Category category = catalogService.getCategory(product.getCategoryId());
        model.addAttribute("categoryId", category.getCategoryId());

        // If editing, load the item data
        if (editItemId != null && !editItemId.isEmpty()) {
            Item editItem = catalogService.getItem(editItemId);
            if (editItem != null) {
                model.addAttribute("editItem", editItem);
            }
        }

        return "admin/catalog/item";
    }

    @PostMapping("/items/add")
    public String addItem(@RequestParam String productId,
                          @RequestParam String itemId,
                          @RequestParam java.math.BigDecimal listPrice,
                          @RequestParam java.math.BigDecimal unitCost,
                          @RequestParam int supplierId,
                          @RequestParam(required = false) String status,
                          @RequestParam(required = false) String attribute1,
                          @RequestParam(required = false) String attribute2,
                          @RequestParam(required = false) String attribute3,
                          @RequestParam(required = false) String attribute4,
                          @RequestParam(required = false) String attribute5) {
        Item item = new Item();
        item.setProductId(productId);
        item.setItemId(itemId);
        item.setListPrice(listPrice);
        item.setUnitCost(unitCost);
        item.setSupplierId(supplierId);
        item.setStatus((status != null && !status.isEmpty()) ? status : "P");
        item.setAttribute1(attribute1);
        item.setAttribute2(attribute2);
        item.setAttribute3(attribute3);
        item.setAttribute4(attribute4);
        item.setAttribute5(attribute5);

        catalogService.addItem(item);
        return "redirect:/catalog/items?productId=" + productId;
    }

    @PostMapping("/items/delete")
    public String deleteItem(@RequestParam String itemId,
                             @RequestParam String productId) {
        catalogService.deleteItem(itemId, productId);
        return "redirect:/catalog/items?productId=" + productId;
    }

    @PostMapping("/items/update")
    public String updateItem(@RequestParam String productId,
                             @RequestParam String itemId,
                             @RequestParam java.math.BigDecimal listPrice,
                             @RequestParam java.math.BigDecimal unitCost,
                             @RequestParam int supplierId,
                             @RequestParam(required = false) String status,
                             @RequestParam(required = false) String attribute1,
                             @RequestParam(required = false) String attribute2,
                             @RequestParam(required = false) String attribute3,
                             @RequestParam(required = false) String attribute4,
                             @RequestParam(required = false) String attribute5) {
        Item item = new Item();
        item.setItemId(itemId);
        item.setProductId(productId);
        item.setListPrice(listPrice);
        item.setUnitCost(unitCost);
        item.setSupplierId(supplierId);
        item.setStatus((status != null && !status.isEmpty()) ? status : "P");
        item.setAttribute1(attribute1);
        item.setAttribute2(attribute2);
        item.setAttribute3(attribute3);
        item.setAttribute4(attribute4);
        item.setAttribute5(attribute5);

        catalogService.updateItem(item);
        return "redirect:/catalog/items?productId=" + productId;
    }
}
