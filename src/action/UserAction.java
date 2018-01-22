package action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


import entity.User;
import service.UserService;

public class UserAction extends ActionSupport implements ModelDriven<User>{
    private UserService userService; 
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	//模型驱动  封装
	private User user=new User();
	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	
	/*//属性封装
	private String username;
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}*/
    
	//登录
	public String login() {	
		   //模型驱动封装
			User userExist = userService.login(user);
			//判断 
			if(userExist != null) {//成功
				//使用session保持登录状态
				HttpServletRequest request = ServletActionContext.getRequest();
				request.getSession().setAttribute("username", userExist.getUsername());
				return "loginsuccess";
			} else {//失败
				ServletActionContext.getRequest().setAttribute("msg", "用户名不存在，请注册");
				return "regist";
			}
     }
	
	//安全退出
	//登录
		public String logout() {	
			   
					return "logout";
			
	     }

	//注册
  public String regist() {
	  //先判断用户名密码要符合规范   先存到map集合
	   Map<String, String> map=new HashMap<String,String>();
	   String username=user.getUsername();
	   if (username==null || username.trim().isEmpty()) {//去空格后为空
		  map.put("name", "用户名不能为空");
	   }else if (username.length()<3 || username.length()>15){
		   map.put("name", "用户名长度在3~15之间");
	   }
	   
		String password1=user.getPassword1();
		String password2=user.getPassword2();
		if (password1==null || password1.trim().isEmpty()) {
			map.put("password", "密码不能为空");
		}else if (password1.length()<3 || password1.length()>15){
			map.put("password", "密码长度在3~15之间");
		}else if (!password1.equalsIgnoreCase(password2)){
			map.put("password", "两次输入密码不一致");
		}
		
		 //判断map是否为空，不为空保存到request域中
		 if (map!=null && map.size()>0) {
			  ServletActionContext.getRequest().setAttribute("errors", map);
			  return "regist";
			}
       
	   //得到user 判断是否已注册
	   User user1=userService.regist(user);
	   if (user1==null) {//
		  return "login";
	   }else {
		  ServletActionContext.getRequest().setAttribute("msg", "用户名已存在，请重新输入");
		  return "regist";
	 }
	
   }
}
