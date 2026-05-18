package org.csu.petstore.controller;

import jakarta.servlet.http.HttpSession;
import org.csu.petstore.entity.*;
import org.csu.petstore.service.AccountService;
import org.csu.petstore.service.CatalogService;
import org.csu.petstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private HttpSession session;

    @GetMapping("/signOn")
    public String index() {
        return "admin/account/signOn";
    }

    @GetMapping("/main")
    public String adminMain() {
        return "admin/main";
    }

    @GetMapping("/users")
    public String manageUsers(@RequestParam(required = false) String keyword, Model model) {
        if(session.getAttribute("loginAccount")==null) {
            return "redirect:/account/signOnForm";
        }
        List<Account> accounts;
        if (keyword != null && !keyword.isEmpty()) {
            accounts = accountService.searchAccounts(keyword);
            model.addAttribute("keyword", keyword);
        } else {
            accounts = accountService.getAllAccounts();
        }
        model.addAttribute("accounts", accounts);
        return "admin/user/users";
    }

    @GetMapping("/userDetails")
    public String viewUserDetails(@RequestParam String username, Model model) {
        Account account = accountService.getAccount(username);
        if (account == null) {
            return "redirect:/admin/users";
        }
        model.addAttribute("account", account);
        return "admin/user/userDetails";
    }

    @PostMapping("/users/resetPassword")
    public String resetPassword(@RequestParam String username) {
        accountService.resetPassword(username, "123456");
        return "redirect:/user/userDetails?username=" + username;
    }

    @GetMapping("/users/edit")
    public String editUser(@RequestParam String username, Model model) {
        if(session.getAttribute("loginAccount")==null) {
            return "redirect:/account/signOnForm";
        }
        Account account = accountService.getAccount(username);
        if (account == null) {
            return "redirect:/user/users";
        }
        model.addAttribute("account", account);
        return "admin/user/editUser";
    }

    @PostMapping("/users/update")
    public String updateUser(
            @RequestParam String username,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String address1,
            @RequestParam(required = false) String address2,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String zip,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String languagePrefer,
            @RequestParam(required = false) String favoriteCategory,
            @RequestParam(required = false) Integer myListOption,
            @RequestParam(required = false) Integer bannerOption,
            Model model) {
        if(session.getAttribute("loginAccount")==null) {
            return "redirect:/account/signOnForm";
        }
        
        try {
            Account account = accountService.getAccount(username);
            if (account == null) {
                model.addAttribute("errorMsg", "User not found!");
                return "admin/user/editUser";
            }
            
            // Update account fields
            account.setFirstName(firstName != null ? firstName : "");
            account.setLastName(lastName != null ? lastName : "");
            account.setEmail(email != null ? email : "");
            account.setPhone(phone != null ? phone : "");
            account.setStatus(status != null ? status : "OK");
            account.setAddress1(address1 != null ? address1 : "");
            account.setAddress2(address2 != null ? address2 : "");
            account.setCity(city != null ? city : "");
            account.setState(state != null ? state : "");
            account.setZip(zip != null ? zip : "");
            account.setCountry(country != null ? country : "");
            account.setLanguagePrefer(languagePrefer != null ? languagePrefer : "Chinese");
            account.setFavoriteCategory(favoriteCategory != null ? favoriteCategory : "CATS");
            account.setMyListOption(myListOption != null ? myListOption : 1);
            account.setBannerOption(bannerOption != null ? bannerOption : 1);
            
            accountService.updateAccount(account);
            model.addAttribute("successMsg", "User information updated successfully!");
            model.addAttribute("account", account);
        } catch (Exception e) {
            model.addAttribute("errorMsg", "Failed to update user information: " + e.getMessage());
            Account account = accountService.getAccount(username);
            model.addAttribute("account", account);
        }
        
        return "admin/user/editUser";
    }
}
