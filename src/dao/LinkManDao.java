package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import entity.Customer;
import entity.LinkMan;

@SuppressWarnings("all")
public class LinkManDao extends BaseDaoImpl<LinkMan> {
    //多条件组合查询 --hibernate模版/
	
	
	//多条件组合查询--离线方式
	public List<LinkMan> findMoreCondition(LinkMan linkMan) {
		// TODO Auto-generated method stub
		DetachedCriteria criteria=DetachedCriteria.forClass(LinkMan.class);
		
		if (linkMan.getLkmName()!=null && !"".equals(linkMan.getLkmName())) {
			criteria.add(Restrictions.eq("lkmName", linkMan.getLkmName()));
		}
		//判断是否选择了客户
		if (linkMan.getCustomer().getCid()!=null && linkMan.getCustomer().getCid()>0) {
			criteria.add(Restrictions.eq("customer.cid", linkMan.getCustomer().getCid()));
		}
		
		return (List<LinkMan>) this.getHibernateTemplate().findByCriteria(criteria);
	}

	//分页--查询数量
	public int findCount() {
		List<Object> list=(List<Object>) this.getHibernateTemplate().find("select count(*) from LinkMan");
	    if (list!=null&&list.size()!=0) {
	    	Object obj=list.get(0);
	    	
	    	long obj2=(long) obj;
	    	int count=(int) obj2;
	    	return count;
		}		
		return 0;
	}
    //分页
	public List<Customer> findPage(int begin, int pageSize) {
		DetachedCriteria criteria=DetachedCriteria.forClass(LinkMan.class);
		List<Customer> list=(List<Customer>) this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);	
		return list;
	}

	/*public void add(LinkMan linkMan) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(linkMan);
	}*/

	/*public List<LinkMan> list() {
		// TODO Auto-generated method stub
		List<LinkMan> list=(List<LinkMan>) this.getHibernateTemplate().find("from LinkMan");
		return list;
	}*/

	/*public LinkMan findOne(int linkid) {
		// TODO Auto-generated method stub
		
		//this.getHibernateTemplate().get(LinkMan.class, linkid);
		return this.getHibernateTemplate().get(LinkMan.class, linkid);
	}*/

	/*public void update(LinkMan linkMan) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(linkMan);
	}*/

	
}
