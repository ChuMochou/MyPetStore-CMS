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
}
