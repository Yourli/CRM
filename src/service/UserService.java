package service;

import org.springframework.transaction.annotation.Transactional;

import dao.UserDao;
import entity.User;

@Transactional
public class UserService {
   private UserDao userDao;

  public void setUserDao(UserDao userDao) {
	this.userDao = userDao;
  }

 public User login(User user) {
	return userDao.loginUser(user);
 }

public User regist(User user) {
	// TODO Auto-generated method stub
	return userDao.regist(user);
 }
   
}
