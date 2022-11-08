package com.shoppingmall.user.bo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.shoppingmall.user.model.User;

@SpringBootTest
class UserBOTest {

	@Autowired
	UserBO userBO;
	
	@Transactional
	//@Test
	void 유저추가테스트() {
		userBO.addUser("test2222", "444", "6666" ,"888", "000");
	}
	
	@Test
	void 유저가져오기() {
		User user = userBO.getUserByLoginIdAndPassword("test111", "1111");
		assertNotNull(user);
	}

}
