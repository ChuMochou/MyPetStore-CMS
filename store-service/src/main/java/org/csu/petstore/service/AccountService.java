package org.csu.petstore.service;

import org.csu.petstore.entity.Account;
import org.springframework.ui.Model;

import java.util.List;

public interface AccountService {
    Account getAccountByUsername(String username);

    Account getAccountByUsernameAndPassword(String username, String password);

    boolean checkUsernameAvailable(String username);

    boolean checkNewAccount(String username, String password, String repeatedPassword, Model model);

    void insertNewAccount(String username, String password);

    List<Account> getAllAccounts();

    List<Account> searchAccounts(String keyword);

    Account getAccount(String username);

    void resetPassword(String username, String newPassword);
}