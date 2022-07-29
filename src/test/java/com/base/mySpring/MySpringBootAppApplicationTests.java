package com.base.mySpring;

import com.base.mySpring.entity.Account;
import com.base.mySpring.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.DelegatingServletOutputStream;

import java.util.Optional;

@SpringBootTest
class MySpringBootAppApplicationTests {

	@Autowired
	AccountRepository accountRepository;

	@Test
	void optional() {

		Account account = new Account();
		account.setUsername("spring");
		account.setPassword("teset12");

		accountRepository.save(account);

	}

	@Test
	public void fine(){

		Optional<Account> optionalAccount = accountRepository.findByUsername("spring");
		if(optionalAccount.isPresent()){
			Account account = optionalAccount.get();
			System.out.println("account = " + account);
		}else{
			System.out.println("Account Not Exist");
		}

		Optional<Account> optionalAccount1 = accountRepository.findByUsername("spring");
		Account account2 = optionalAccount1.orElseGet(() -> new Account());
		System.out.println("account2 = " + account2);

		Optional<Account> optionalAccount2 = accountRepository.findByUsername("spring");
		Account account3 = optionalAccount1.orElseThrow(() -> new RuntimeException("Account not exist"));


	}

}
