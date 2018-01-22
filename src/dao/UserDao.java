package dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;

import entity.User;
@SuppressWarnings("all")
public class UserDao {
  private HibernateTemplate hibernateTemplate;

 public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
	this.hibernateTemplate = hibernateTemplate;
 }
  
 
public User loginUser( User user) {
	  //登录的查询  根据用户名和密码
	  
	List<User> list=(List<User>)
			hibernateTemplate.find("from User where username=? and password1=?",user.getUsername(),user.getPassword1());
	
	if (list!=null&&list.size()!=0) {
		//如果查询后，list没有值，会出现下标越界异常。
		User user2=list.get(0);
		return user2;
	}
	 return null;
  }

public User regist(User user) {
	//注册查询  根据用户名
	List<User> list=(List<User>) hibernateTemplate.find("from User where username=?", user.getUsername());
	if (list!=null && list.size()!=0) {
	  User user2=list.get(0);
	  return user2;
	}else {
		this.hibernateTemplate.save(user);
		return null;
	}
		
}
 
}
