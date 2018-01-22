package entity;

import java.util.List;

public class pageBean {
  private Integer currentPage;//当前页
  private Integer totalCount;//总记录数
  private Integer pageSize;//每页记录数
  private Integer totalPage;//
  private Integer begin;
  private List<Customer> list;//每页记录的list集合
public Integer getCurrentPage() {
	return currentPage;
}
public void setCurrentPage(Integer currentPage) {
	this.currentPage = currentPage;
}
public Integer getTotalCount() {
	return totalCount;
}
public void setTotalCount(Integer totalCount) {
	this.totalCount = totalCount;
}
public Integer getPageSize() {
	return pageSize;
}
public void setPageSize(Integer pageSize) {
	this.pageSize = pageSize;
}
public Integer getTotalPage() {
	return totalPage;
}
public void setTotalPage(Integer totalPage) {
	this.totalPage = totalPage;
}
public Integer getBegin() {
	return begin;
}
public void setBegin(Integer begin) {
	this.begin = begin;
}
public List<Customer> getList() {
	return list;
}
public void setList(List<Customer> list) {
	this.list = list;
	
}
  
  
}
