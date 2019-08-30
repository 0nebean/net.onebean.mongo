package net.onebean.core.mongo.base;

import java.util.List;

import net.onebean.core.mongo.query.MongoPagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 *  * service 层的基类，所有service类必须继承自此类，该类不能直接使用。
 * 将service层一些通用的操作给抽离出来，封装到此类中，其他service类必须继承此类，子类可以直接使用此类中的方法。
 * 该类使用泛型实现了实体和dao层封装
 * 参考‘net.onebean.core.BaseBiz’
 * @author 0neBean
 *
 * @param <T>
 * @param <K>
 */
public abstract class BaseMongoBiz<T, K extends BaseMongoDao<T>> implements IBaseMongoBiz<T> {	
	
	/**
	 * dao原型属性
	 */
	protected K baseMongoDao;

	/**
	 * 根据K泛型自动装载baseMongoDao
	 * 
	 * @param baseMongoDao mongo基orm类
	 */
	@Autowired
	public final void setbaseMongoDao(K baseMongoDao) {
		this.baseMongoDao = baseMongoDao;
	}

	@Override
	public List<T> find(Query query) {
		 return baseMongoDao.find(query);  
	}

	@Override
	public T findOne(Query query) {
		 return baseMongoDao.findOne(query);  
	}

	@Override
	public void update(Query query, Update update) {
		baseMongoDao.update(query, update);
	}

	@Override
	public T save(T entity) {
		 baseMongoDao.save(entity);  
	     return entity;
	}

	@Override
	public T findById(String id) {
		return baseMongoDao.findById(id);
	}

	@Override
	public T findById(String id, String collectionName) {
		 return baseMongoDao.findById(id, collectionName);
	}

	@Override
	public MongoPagination<T> findPage(MongoPagination <T> page, Query query) {
        return baseMongoDao.findPage(page, query); 
	}

	@Override
	public long count(Query query) {
		return baseMongoDao.count(query);  
	}
	
}
