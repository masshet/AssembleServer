package by.mrstark.assembleserver.controller;

import by.mrstark.assembleserver.entity.Account;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mrstark on 13.3.16.
 */

@RestController
@RequestMapping("/user")
public class AccountController {

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Account getAccount() {
        return createMockAccount();
    }

    private Account createMockAccount() {
        Account account = new Account("user", "pass");
        account.setFirstName("Alex");
        account.setLastName("Masliakov");
        return account;
    }

}
