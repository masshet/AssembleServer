package by.mrstark.assembleserver.controller;

import by.mrstark.assembleserver.entity.Account;
import by.mrstark.assembleserver.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public Account getAccess() {
        return accountService.findByUsernameAndPassword("masshet", "masshet");
    }

}
