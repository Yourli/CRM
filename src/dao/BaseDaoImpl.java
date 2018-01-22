package dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
    
	private Class clazz1;
	
	//构造
	public BaseDaoImpl() {
	  //得到当前运行类Class
		Class clazz=this.getClass();//class dao.CustomerDao
		//System.out.println("....."+clazz);
		//得到运行类 父类的参数化类型BaseDaoImpl<Customer>
		  Type type=clazz.getGenericSuperclass();
		  ParameterizedType pType=(ParameterizedType) type;
	      
		//第三步  得到实际类型参数 Customer
		  Type[] types=pType.getActualTypeArguments();
		  //Type接口的实现类是就是class
		  Class clazz2=(Class) types[0];
		  clazz1=clazz2;
	}

	//添加
	public void add(T t) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(t);
	}

	//修改
	public void update(T t) {
		this.getHibernateTemplate().update(t);
		
	}

	//删除
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	//根据id查询
	public T findOne(int id) {
		// TODO Auto-generated method stub
		return (T) this.getHibernateTemplate().get(clazz1, id);
	}

	//查询所有
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return (List<T>) this.getHibernateTemplate().find("from "+clazz1.getSimpleName());
	}

}
