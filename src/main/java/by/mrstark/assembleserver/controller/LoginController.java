package by.mrstark.assembleserver.controller;

import by.mrstark.assembleserver.entity.Account;
import by.mrstark.assembleserver.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by mrstark on 17.3.16.
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Account getAccess(@RequestParam(value = "username", defaultValue = "masshet") String username,
                             @RequestParam(value = "password", defaultValue = "masshet") String password) {
        return accountService.findByUsernameAndPassword(username, password);
    }

}
