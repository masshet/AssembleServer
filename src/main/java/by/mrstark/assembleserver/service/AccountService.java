package by.mrstark.assembleserver.service;

import by.mrstark.assembleserver.entity.Account;
import by.mrstark.assembleserver.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mrstark on 17.3.16.
 */
@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    public Account findByUsernameAndPassword(String username, String password) {
        return repository.findByUsernameAndPassword(username, password);
    }

    public boolean findByUsername(String username) {
        if (repository.findByUsername(username) != null) {
            return false;
        }
        return true;
    }

    public Account add(Account account) {
        return repository.saveAndFlush(account);
    }

    public void delete(Long id) {
        repository.delete(id);
    }

    public Account edit(Account account) {
        return repository.saveAndFlush(account);
    }

    public List<Account> findAll() {
        return repository.findAll();
    }

}
