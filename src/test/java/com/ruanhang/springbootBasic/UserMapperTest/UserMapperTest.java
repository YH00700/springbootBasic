package com.ruanhang.springbootBasic.UserMapperTest;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ruanhang.springbootBasic.mapper.UserMapper;

/** 
 * @author  阮航  
 * @date 创建时间：2017年11月22日 上午11:29:17 
 * @version 1.0 
*/
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
	
	@Resource
	private UserMapper userMapper;
	
	@Test
	public void testInsert()  {
//		userMapper.insertUser(new UserEntity("aa", "a123456", UserSexEnum.MAN));
//		userMapper.insertUser(new UserEntity("bb", "b123456", UserSexEnum.WOMAN));
//		userMapper.insertUser(new UserEntity("dd", "b123456", UserSexEnum.WOMAN));
//
//		Assert.assertEquals(2, userMapper.getAll().size());
//		
//		List<UserEntity> liEntities = userMapper.getAll();
//		for(UserEntity userEntity : liEntities){
//			System.out.println(userEntity.toString());
//		}
		
//		UserEntity user = new UserEntity();
//		user.setUserName("sss");
//		user.setUserSex(UserSexEnum.WOMAN);
//		userMapper.update(29l, user);
		
		//UserEntity userEntity = userMapper.getOne(29l);
		//System.out.println(userEntity.toString());
	}	
}
