package action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.ServletActionRedirectResult;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.org.apache.bcel.internal.generic.MONITOREXIT;

import entity.Customer;
import entity.LinkMan;
import entity.pageBean;
import service.CustomerService;
import service.LinkManService;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan>{
	//模型驱动封装
	/*
	 * 可以封装linkman基本信息
	 * 但客户的id值不能直接封装，把客户id封装到LinkMan实体类的customer属性类
	 */
	private LinkMan linkMan=new LinkMan();
	@Override
	public LinkMan getModel() {
		// TODO Auto-generated method stub
		return linkMan;
	}
	
	private LinkManService linkManService;

public void setLinkManService(LinkManService linkManService) {
	this.linkManService = linkManService;
}


  //注入customerservice
  private CustomerService customerService;
  public void setCustomerService(CustomerService customerService) {
	this.customerService = customerService;
}

  //
public String toAddPage() {
	//查询所有客户到list 显示到页面  所属客户的下拉列表
	 List<Customer> list=customerService.findAll();
	 ServletActionContext.getRequest().setAttribute("list", list);
	 return "toAddPage";
 }

  
  
 public File getUpload() {
	return upload;
}
public void setUpload(File upload) {
	this.upload = upload;
}
public String getUploadFileName() {
	return uploadFileName;
}

public void setUploadFileName(String uploadFileName) {
	this.uploadFileName = uploadFileName;
}

private File upload;//命名规范：表单里文件上传项name值
private String uploadFileName;//表单里文件上传项name值+FileName；
//添加联系人到数据库
  public String addLinkMan() throws IOException, FileSizeLimitExceededException{
	//把客户id封装到linkman的customer属性
  linkManService.addLinkMan(linkMan);
  //文件上传  struts2做了封装
  /*
   * 要上传的文件  流
   * 在action成员变量位置定义变量：上传文件，文件名称
   * 生成变量get，set方法
   */   
  //判断是否选择上传文件
  if (upload!=null) {
		//在服务器文件夹创建文件
		 File serverFile=new File("e:\\ceshi"+"/"+uploadFileName);
		 FileUtils.copyFile(upload, serverFile);
   }	
     return "addLinkMan";
 }
  //判断上传的文件是否超过200M
  public String fileLengthLimit() {
	  ServletActionContext.getRequest().setAttribute("msg", "您上传的文件超过200M，请重新选择");
	  
	  return "toAddPage";  
  }

  //联系人列表-分页列出
  //分页
  private Integer currentPage; 
public Integer getCurrentPage() {
	return currentPage;
}
public void setCurrentPage(Integer currentPage) {
	this.currentPage = currentPage;
}

public String list() {	
	  pageBean pageBean=linkManService.listpage(currentPage);
	  ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
	  return "list";
}
 

  //修改--到修改联系人页面
  public String showLinkMan() {
	//使用模型驱动得到id值
	 int linkid=linkMan.getLinkid();
	 LinkMan linkMan=linkManService.findOne(linkid);
	 ServletActionContext.getRequest().setAttribute("linkman", linkMan);
	  //所属客户 下拉表
	 List<Customer> listCustomer=customerService.findAll();
	 ServletActionContext.getRequest().setAttribute("listCustomer", listCustomer);
	 
	 return "showLinkMan";
  }
//修改--修改联系人
  public String update() {
	
	//模型驱动封装表单数据
	linkManService.update(linkMan);
	return "update";
}
  
  //到联系人组合查询页面
   public String toSelectLinkManPage() {
	List<Customer> list=customerService.findAll();
	ServletActionContext.getRequest().setAttribute("list", list);
	return "toSelectLinkManPage";
 }
   
   
   public String  moreCondition() {
	   
	List<LinkMan> list=linkManService.findMoreCondition(linkMan);
	ServletActionContext.getRequest().setAttribute("list", list);
	return "moreCondition";
}
   
}
