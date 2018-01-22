package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.text.DefaultEditorKit.CutAction;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import entity.Customer;
import entity.Dict;
import service.CustomerService;


@SuppressWarnings("all")
public class CustomerDao extends BaseDaoImpl<Customer> {

	/*public void add(Customer customer) {
		// TODO Auto-generated method stub
	 //得到模版
		this.getHibernateTemplate().save(customer);
	}*/

	/*public List<Customer> findAll() {
		// TODO Auto-generated method stub	
		return (List<Customer>) this.getHibernateTemplate().find("from Customer");
	}*/

	/*public Customer findOne(int cid) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(Customer.class, cid);
	}*/

	/*public void delete(Customer c) {
		// TODO Auto-generated method stub
	   this.getHibernateTemplate().delete(c);	
	}*/
    
	/*//修改
	public void update(Customer customer) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(customer);
	}*/
	
	
    //分页  查询总数量
	public Integer findCount() {
		List<Object> list=(List<Object>) this.getHibernateTemplate().find("select count(*) from Customer");
	    if (list!=null&&list.size()!=0) {
	    	Object obj=list.get(0);
	    	
	    	long obj2=(long) obj;
	    	int count=(int) obj2;
	    	return count;
		}		
		return 0;
	}
    
	//分页查询
	public List<Customer> findPage(int begin, int pageSize) {
		// TODO Auto-generated method stub
		//第二种  使用离线对象和HibernateTemplate方法实现
		DetachedCriteria criteria=DetachedCriteria.forClass(Customer.class);
		List<Customer> list=(List<Customer>) this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);	
		return list;
	}

	//条件查询
	public List<Customer> findCondition(Customer customer) {
		//第二种
		//List<Customer> list=(List<Customer>) this.getHibernateTemplate().find("from Customer where custName like ?", "%"+customer.getCustName()+"%");
		
		//第三种
		DetachedCriteria criteria=DetachedCriteria.forClass(Customer.class);
		criteria.add(Restrictions.like("custName", "%"+customer.getCustName()+"%"));
		List<Customer> list=(List<Customer>) this.getHibernateTemplate().findByCriteria(criteria);
		return list;
	}

	//多条件查询--客户
	/*public List<Customer> findMoreCondition(Customer customer) {
		// TODO Auto-generated method stub
		//第一种hibernate模版里find方法
		  //拼接hql语句
		  String hql="from Customer where 1=1";
		  //判断条件值是否为空，如果不为空拼接hql语句
		  //定义list集合
		  List<Object> p=new ArrayList<Object>();
				  
		  if (customer.getCustName()!=null && !"".equals(customer.getCustName())) {
			hql+=" and custName=?";
			p.add(customer.getCustName());
		 }
		 if (customer.getCustLevel()!=null && !"".equals(customer.getCustLevel())) {
				hql+=" and custLevel=?";
				p.add(customer.getCustLevel());
				}
		 if (customer.getCustSource()!=null && !"".equals(customer.getCustSource())) {
				hql+=" and custSource=?";
				p.add(customer.getCustSource());
			 }
		 //find 第二个参数是可变参数 数组形式
		
		return (List<Customer>) this.getHibernateTemplate().find(hql, p.toArray());
	 }*/
	
	//多条件组合查询--客户
	public List<Customer> findMoreCondition(Customer customer) {
		//第二种 离线对象查询。
		DetachedCriteria criteria=DetachedCriteria.forClass(Customer.class);
		 if (customer.getCustName()!=null && !"".equals(customer.getCustName())) {
			 //设置对象属性，设置值
			 criteria.add(Restrictions.eq("custName", customer.getCustName()));
			   
			 }
		 if (customer.getCustSource()!=null && !"".equals(customer.getCustSource())) {
				criteria.add(Restrictions.eq("custSource", customer.getCustSource()));
				
			 }
		return (List<Customer>) this.getHibernateTemplate().findByCriteria( criteria);
	 
	}

	//查询级别
	public List<Dict> findAllLevel() {
		
		return (List<Dict>) this.getHibernateTemplate().find("from Dict");
	}
  
	//统计--根据客户来源
	  //需要复杂语句，建议直接调用底层sql实现  SQLQuery
	public List findCountSource() {
		Session session=this.getSessionFactory().getCurrentSession();
		//SQLQuery对象
		SQLQuery query=session.createSQLQuery("SELECT COUNT(*) AS num, custSource FROM t_customer GROUP BY custSource");
		//list默认数组结构
		  //返回值只有俩个字段，数量和名称，转换为map结构 
		query.setResultTransformer(Transformers.aliasToBean(HashMap.class));
		
		List list=query.list();
		 
		
		return list;
	}
    //统计--根据客户级别
	public List findCountLevel() {
		Session session=this.getSessionFactory().getCurrentSession();
		SQLQuery query=session.createSQLQuery("SELECT c.num,d.dname FROM\r\n" + 
				"(SELECT COUNT(*) AS num, levelid FROM t_customer GROUP BY levelid) c,\r\n" + 
				"t_dict d \r\n" + 
				"WHERE d.did=c.levelid");
		query.setResultTransformer(Transformers.aliasToBean(HashMap.class));
		List list=query.list();
		 
		
		return list;
	}
}
