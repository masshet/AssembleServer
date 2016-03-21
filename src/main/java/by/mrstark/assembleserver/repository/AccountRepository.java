package by.mrstark.assembleserver.repository;

import by.mrstark.assembleserver.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mrstark on 13.3.16.
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByUsernameAndPassword(String username, String password);
    Account findByUsername(String username);
}
