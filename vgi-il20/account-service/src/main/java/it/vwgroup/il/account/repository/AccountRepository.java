package it.vwgroup.il.account.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.vwgroup.il.account.domain.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {

	Account findByName(String name);

}
