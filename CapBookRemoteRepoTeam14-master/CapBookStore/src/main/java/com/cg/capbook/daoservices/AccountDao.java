package com.cg.capbook.daoservices;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.capbook.beans.Account;

public interface AccountDao extends JpaRepository<Account, String>{

	
	//@Query("select firstName from account u where u.emailId in (select f.FRIEND_EMAIL_ID from friend where f.ACCOUNT_EMAIL_ID=:emailId)")
	//List<String> getDob(@Param("emailId") String emailId);
	// and u.DOB=:currentDate  		,@Param("currentDate") String currentDate
}
