package com.shoppingmall.user.bo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.shoppingmall.user.model.User;

@SpringBootTest
class UserBOTest {

	@Autowired
	UserBO userBO;
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
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
	
	@Test
	void 테스트() {
		String a = "";
		String b = null;
		List<Integer> list = null;
		List<Integer> list1 = new ArrayList<>();
		
		if (ObjectUtils.isEmpty(a)) {
			log.info("### a is empty");
		}
	}

}
