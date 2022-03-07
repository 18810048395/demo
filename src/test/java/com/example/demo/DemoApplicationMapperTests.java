package com.example.demo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.dao.MenuMapper;
import com.example.demo.dao.UserMapper;
import com.example.demo.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Slf4j
class DemoApplicationMapperTests {

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
//		List<Map<String, Object>> maps = userMapper.selectMaps(null);
//		maps.forEach(System.out::println);
	}

	@Test
	void selectListByQueryWrapper() {
		// 查询UserName不为空，UserAddressl不为空，Id大于18的用户
		LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
		wrapper.isNotNull(User::getUserAddress)
                    .isNotNull(User::getUserName).ge(User::getId,18);
				List<User> users = userMapper.selectList(wrapper);
		users.forEach(System.out::println);
	}

	@Test
	void selectCountByQueryWrapper() {
		// 查询Id在15-20之间的用户,且降序排序
		LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
		wrapper.between(User::getId,15,20)
				.orderByDesc(User::getId);
		List<User> users = userMapper.selectList(wrapper);
		users.forEach(System.out::println);
	}

	@Test
	void selectByMap() {
		Map<String, Object> map = new HashMap<>();
		//自定义要查询的
		map.put("user_name","小黑0");
		List<User> users = userMapper.selectByMap(map);
		users.forEach(System.out::println);
	}

	@Test
	void selectPage(){
		Page page = new Page<>(2,5);
		LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.ge(User::getId,25);
		Page result = userMapper.selectPage(page, queryWrapper);
		result.getRecords().forEach(System.out::println);
		System.out.println("总个数==>"+result.getTotal());
	}

	@Test
	void insertTest() {
		for (int i = 0; i < 10; i++) {
			User user = User.builder().userName("小黑"+i).userSex("M").userAddress("北京市海淀区").build();
			int result = userMapper.insert(user);
			System.out.println(result); //受影响的行数
		}
//		User user = User.builder().userName("小黑").userSex("M").userAddress("北京市朝阳区").build();
//		int result = userMapper.insert(user);
//		System.out.println(result); //受影响的行数
	}

	@Test
	void updateTest() {
		User user = User.builder().id(9).userName("小黑").userSex("F").userAddress("北京市朝阳区").build();
		int result = userMapper.updateById(user);
		System.out.println(result); //受影响的行数
	}

	@Test
	void updateByUpdateWrapper() {
		LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
		updateWrapper.eq(User::getUserName,"小白");
		User user = User.builder().userName("小黑").userSex("F").userAddress("北京市朝阳区").build();
		int result = userMapper.update(user, updateWrapper);
		System.out.println(result); //受影响的行数
	}

	@Test
	void testDeleteById(){
		userMapper.deleteById(9);
	}

}
