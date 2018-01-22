package action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import entity.Customer;
import entity.Dict;
import entity.pageBean;
import service.CustomerService;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{
	
	//模型驱动封装实体类
	private Customer customer=new Customer();
	@Override
	public Customer getModel() {
		// TODO Auto-generated method stub
		return customer;
	}
	
	//service添加到action
	private CustomerService customerService;
    public void setCustomerService(CustomerService customerService) {
	this.customerService = customerService;
   }
    
  //到添加页面
  public String toAddPage() {
	  //数据字典  查询所有级别
	 List<Dict> list=customerService.findAllLevel();
	 ServletActionContext.getRequest().setAttribute("list", list);
	 return "toAddPage";
  }
  
  //添加
  public String add() {
	customerService.add(customer);
	return "add";
  }
  
  
  //定义list集合放到值栈
   private List<Customer> list;
public List<Customer> getList() {
	return list;
}

   
  //删除
  public String delete() {
   //使用模型驱动获取表单数据cid值
   int cid=customer.getCid();
   Customer c=customerService.findOne(cid);
   
   //调用方法删除
   if (c!=null) {
	   customerService.delete(c);
   }
   return "delete";
 }
  
  //5修改客户-显示要修改的客户
  public String showCustomer() {
	  int cid=customer.getCid();
	  Customer c=customerService.findOne(cid);  
	  //查询结果显示到页面 保存在域
	  ServletActionContext.getRequest().setAttribute("customer", c);
	  //数据字典  客户级别
	  List<Dict> list=customerService.findAllLevel();
		 ServletActionContext.getRequest().setAttribute("list", list);
	  return "showCustomer";
 }
  
  //修改客户--修改
  public  String update() {
	
	 customerService.update(customer); 
	  return "update";
}
  //分页
  private Integer currentPage; 
public Integer getCurrentPage() {
	return currentPage;
}
public void setCurrentPage(Integer currentPage) {
	this.currentPage = currentPage;
}

//获取客户列表
public String list() {
	 //list=customerService.findAll();
	  //list传到页面去,放到域对象
	 // ServletActionContext.getRequest().setAttribute("list", list);
	  
	   //放到值栈 
	   //list=customerService.findAll();
	  //分页功能  分页列出
	  pageBean pageBean=customerService.listpage(currentPage);
	  ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
	  return "list";
 }

/*public String listpage() {
  //属性封装得到请求参数  模型驱动已对customer用过一次
   pageBean pageBean=customerService.listpage(currentPage);
   ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
  return "listpage";
 }*/


//条件查询
public String findCondition() {
	//如果输入客户名称 根据客户名称查询，不输入全查询
	if (customer.getCustName()!=null && !"".equals(customer.getCustName())) {
		 list=customerService.findCondition(customer);
		ServletActionContext.getRequest().setAttribute("list", list);
	}else {
		list=customerService.findAll();
	}
	
	return "findcondition";
}

//综合查询--到客户查询页面
 public String toSelectCustomerPage() {
	return "toSelectCustomerPage";
 }
 
//多条件查询--客户
 public String moreCondition() {
	
	list= customerService.findMoreCondition(customer);
	ServletActionContext.getRequest().setAttribute("list", list);
	return "moreCondition";
  } 
 
 //统计
  //根据客户来源
  public String countSource() {
	 List list= customerService.findCountSource();
	 ServletActionContext.getRequest().setAttribute("list", list);
	 
	 return "countSource";
  }
  //根据客户级别
   public String countLevel() {
	  @SuppressWarnings("all")
	List list=customerService.findCountLevel();
	  ServletActionContext.getRequest().setAttribute("list", list);
	  return "countLevel";
	}
  
}
