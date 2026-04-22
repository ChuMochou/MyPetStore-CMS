package org.csu.petstore.controller;

import jakarta.servlet.http.HttpSession;
import org.csu.petstore.entity.Account;
import org.csu.petstore.entity.Product;
import org.csu.petstore.service.AccountService;
import org.csu.petstore.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private CatalogService catalogService;
    @Autowired
    private HttpSession session;

    @GetMapping("/signOnForm")
    public String signOnForm() {
        return "admin/account/signOn";
    }

    @PostMapping("/signOn")
    public String signOn(String username, String password, String inputCaptcha, Model model) {
//        if (!(session.getAttribute("trueCaptcha")).toString().equalsIgnoreCase(inputCaptcha)) {
//            model.addAttribute("signOnMsg", "Captcha error!");
//            return "account/signOn";
//        }
        if (accountService.checkUsernameAvailable(username)) {
            model.addAttribute("signOnMsg", "Username is not exists!");
            return "admin/account/signOn";
        }
        Account account = accountService.getAccountByUsernameAndPassword(username, password);
        if (account == null) {
            model.addAttribute("signOnMsg", "Password error!");
            return "admin/account/signOn";
        }
        session.setAttribute("loginAccount", account);
        List<Product> myList = catalogService.getProductListByCategory(account.getFavoriteCategory());
        session.setAttribute("myList", myList);
        return "admin/main";
    }

    @GetMapping("/signOff")
    public String logout(jakarta.servlet.http.HttpSession session) {
        session.removeAttribute("loginAccount");
        return "redirect:/account/signOnForm";
    }

    @GetMapping("/newAccountForm")
    public String newAccountForm() {
        return "admin/account/newAccount";
    }

    @PostMapping("/newAccount")
    public String newAccount(String username, String password, String repeatedPassword, Model model) {
        if (!accountService.checkNewAccount(username, password, repeatedPassword, model)) {
            return "admin/account/newAccount";
        }
        accountService.insertNewAccount(username, password);//创建新用户
        return "redirect:/account/signOnForm";
    }

//    @GetMapping("/registerSuccessForm")
//    public String registerSuccessForm() {
//        return "admin/account/registerSuccess";
//    }
}
