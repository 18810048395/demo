package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.pojo.Menu;
import com.example.demo.pojo.MenuDTO;
import com.example.demo.pojo.User;
import com.example.demo.service.MenuService;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
@Slf4j
class DemoApplicationServiceTests {

	@Autowired
	private UserService userService;

	@Autowired
	private MenuService menuService;
	@Test
	void contextLoads() {
		// todo
	}

	@Test
	void selectList() {

		List<User> users = userService.list(null);
		users.forEach(System.out::println);
//		List<Map<String, Object>> maps = userService.listMaps(null);
//		maps.forEach(System.out::println);
	}

	@Test
	void selectListByQueryWrapper() {
		// 查询UserName不为空，UserAddressl不为空，Id大于18的用户
		LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
		wrapper.isNotNull(User::getUserAddress)
                    .isNotNull(User::getUserName).ge(User::getId,15);
				List<User> users = userService.list(wrapper);
		users.forEach(System.out::println);
	}

	@Test
	void selectListByTime() {

		Date parseStart = null;
		Date parseUpdate= null;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String startTime = "2022-02-01 06:20:04";
			String endTime = "2022-02-15 06:40:15";
			parseStart = formatter.parse(startTime);
			parseUpdate = formatter.parse(endTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// 查询时间段数据
		LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
		wrapper.ge(User::getCreateTime,parseStart)
		       .le(User::getUpdateTime,parseUpdate);

		List<User> users = userService.list(wrapper);
		users.forEach(System.out::println);
	}

	@Test
	void selectTree() {
		LambdaQueryWrapper<Menu> menuWrapper = new LambdaQueryWrapper<>();
		menuWrapper.eq(Menu::getPid,0);
		List<Menu> menuList = menuService.list(menuWrapper);
		List<MenuDTO>  menuTree = getMenuTree(menuList);
		System.out.println(JSON.toJSON(menuTree));

	}
	public List<MenuDTO> getMenuTree(List<Menu> menus) {
		List<MenuDTO> list = new LinkedList<>();
		menus.forEach(menu -> {
					if (menu != null) {
						LambdaQueryWrapper<Menu> menuWrapper = new LambdaQueryWrapper<>();
						menuWrapper.eq(Menu::getPid,menu.getId());
						List<Menu> menuList = menuService.list(menuWrapper);
						MenuDTO menuDTO = new MenuDTO();
						menuDTO.setId(menu.getId());
						menuDTO.setName(menu.getName());
						menuDTO.setCreateTime(menu.getCreateTime());
						menu.setCreateUser(menu.getCreateUser());
						if (menuList != null && menuList.size() != 0) {
							menuDTO.setChildren(getMenuTree(menuList));
						}
						list.add(menuDTO);
					}
				}
		);
		return list;
	}

	@Test
	void selectCountByQueryWrapper() {
		// 查询Id在15-20之间的用户,且降序排序
		LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
		wrapper.between(User::getId,15,20)
				.orderByDesc(User::getId);
		List<User> users = userService.list(wrapper);
		users.forEach(System.out::println);
	}

	@Test
	void selectByMap() {
		Map<String, Object> map = new HashMap<>();
		//自定义要查询的
		map.put("user_name","小黑0");
		List<User> users = userService.listByMap(map);
		users.forEach(System.out::println);
	}

	@Test
	void selectPage(){
		Page page = new Page<>(2,5);
		LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.ge(User::getId,25);
		Page result = userService.page(page, queryWrapper);
		result.getRecords().forEach(System.out::println);
		System.out.println("总个数==>"+result.getTotal());
	}

	@Test
	void insertTest() {
		List<User> userList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			User user = User.builder().userName("小黑"+i).userSex("M").userAddress("北京市海淀区").build();
			userList.add(user);
		}
		boolean result = userService.saveBatch(userList);
		System.out.println(result); //是否成功

//		User user = User.builder().userName("小黑").userSex("M").userAddress("北京市朝阳区").build();
//		boolean result = userService.save(user);
//		System.out.println(result); //是否成功
	}
//
	@Test
	void updateTest() {
		User user = User.builder().id(20).userName("小黑").userSex("F").userAddress("北京市朝阳区").build();
		boolean result = userService.updateById(user);
		System.out.println(result); //是否成功
	}

	@Test
	void saveOrUpdateTest() {
		User user = User.builder().id(31).userName("小白666").userSex("M").userAddress("北京市朝阳区").build();
		boolean result = userService.saveOrUpdate(user);
		System.out.println(result); //是否成功
	}

	@Test
	void saveOrUpdateBatch() {
		List<User> userList = userService.list();
		User addUser = User.builder().userName("小白").userSex("F").userAddress("北京市朝阳区").build();
		userList.add(addUser);
		userList.stream().forEach(user -> user.setUserAddress("北京市西城区"));

		boolean result = userService.saveOrUpdateBatch(userList);
		System.out.println(result); //是否成功
	}

	@Test
	void updateByUpdateWrapper() {
		LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
		updateWrapper.eq(User::getUserName,"小黑");
		User user = User.builder().userName("小白").userSex("F").userAddress("北京市朝阳区").build();
		boolean result = userService.update(user, updateWrapper);
		System.out.println(result); //是否成功
	}

	@Test
	void testDeleteById(){
		boolean result = userService.removeById(20);
		System.out.println(result); //是否成功
	}

}
