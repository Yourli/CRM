package service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import dao.LinkManDao;
import entity.Customer;
import entity.LinkMan;
import entity.pageBean;

@Transactional
public class LinkManService {
  private LinkManDao linkManDao;

public void setLinkManDao(LinkManDao linkManDao) {
	this.linkManDao = linkManDao;
}

public void addLinkMan(LinkMan linkMan) {
	// TODO Auto-generated method stub
	 linkManDao.add(linkMan);
}

public List<LinkMan> list() {
	// TODO Auto-generated method stub
	return linkManDao.findAll();
}

public LinkMan findOne(int linkid) {
	// TODO Auto-generated method stub
	return linkManDao.findOne(linkid);
}

public void update(LinkMan linkMan) {
	// TODO Auto-generated method stub
	linkManDao.update(linkMan);
}

public List<LinkMan> findMoreCondition(LinkMan linkMan) {
	// TODO Auto-generated method stub
	return linkManDao.findMoreCondition(linkMan);
}

public pageBean listpage(Integer currentPage) {
	// TODO Auto-generated method stub
		pageBean bean=new pageBean();
	    bean.setCurrentPage(currentPage);
	    int count=linkManDao.findCount();
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
		List<Customer> list=linkManDao.findPage(begin,pageSize);
		bean.setList(list);
		
		return bean;
}
  
}
