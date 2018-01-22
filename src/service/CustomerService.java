package service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import dao.CustomerDao;
import entity.Customer;
import entity.Dict;
import entity.pageBean;
@SuppressWarnings("all")
@Transactional
public class CustomerService {
  private CustomerDao customerDao;

 public void setCustomerDao(CustomerDao customerDao) {
	this.customerDao = customerDao;
 }

public void add(Customer customer) {
	// TODO Auto-generated method stub
	customerDao.add(customer);
}

public List<Customer> findAll() {
	// TODO Auto-generated method stub
	return customerDao.findAll();
 }

public Customer findOne(int cid) {
	// TODO Auto-generated method stub
	
	return customerDao.findOne(cid);
 }

public void delete(Customer c) {
	// TODO Auto-generated method stub
	customerDao.delete(c);
}

 public void update(Customer customer) {
	// TODO Auto-generated method stub
	customerDao.update(customer);
 }

 //封装分页数据到pagebean
public pageBean listpage(Integer currentPage) {
	// TODO Auto-generated method stub
	pageBean bean=new pageBean();
    bean.setCurrentPage(currentPage);
    int count=customerDao.findCount();
	bean.setTotalCount(count);
	//bean.setPageSize(3);
	int pageSize=5;
	//总页数
	int totalpage=0;
	if (count%pageSize==0) {
	   totalpage=count/pageSize;	
	}else {
	   totalpage=count/pageSize+1;
	}
	bean.setTotalPage(totalpage);
	//开始位置	
	int begin=(currentPage-1)*pageSize;
	List<Customer> list=customerDao.findPage(begin,pageSize);
	bean.setList(list);
	
	
	return bean;
}

public List<Customer> findCondition(Customer customer) {
	// TODO Auto-generated method stub
	
	
	return customerDao.findCondition(customer);
}

public List<Customer> findMoreCondition(Customer customer) {
	// TODO Auto-generated method stub
	return customerDao.findMoreCondition(customer);
}

public List<Dict> findAllLevel() {
	// TODO Auto-generated method stub
	return customerDao.findAllLevel();
}

public List findCountSource() {
	
	return customerDao.findCountSource();
}


public List findCountLevel() {
	
	return customerDao.findCountLevel();
}
   
}
