package com.example.demo;

import com.example.demo.dao.UserMapper;
import com.example.demo.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
class DemoApplicationTests {

	@Autowired
	private UserMapper userMapper;

	@Test
	void contextLoads() {
		// todo
	}

	@Test
	void selectList() {
		List<User> users = userMapper.selectList(null);
		users.forEach(System.out::println);
	}

	@Test
	void insertTest() {
		User user = User.builder().userName("小黑").userSex("M").userAddress("北京市朝阳区").build();
		int result = userMapper.insert(user);
		System.out.println(result); //受影响的行数
	}

	@Test
	void updateTest() {
		User user = User.builder().id(8).userName("小白").userSex("M").userAddress("北京市西城区").build();
		int result = userMapper.updateById(user);
		System.out.println(result); //受影响的行数
	}

}
