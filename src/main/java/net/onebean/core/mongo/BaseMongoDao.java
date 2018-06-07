package net.onebean.core.mongo;

import java.util.List;

import net.onebean.core.mongo.common.Parse;
import net.onebean.core.mongo.common.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;


/**
 * mangoDB CRUD操作基类封装
 * @author 0neBean
 *
 * @param <T>
 */
public abstract class BaseMongoDao<T> {  
  
    /** 
     * spring mongodb　集成操作类　 
     */  
	@Autowired
    protected MongoTemplate mongoTemplate;  
	
	/** 
     * 获取需要操作的实体类class 
     *  
     * @return 
     */  
	private Class<T> getEntityClass(){  
        return ReflectionUtils.getSuperClassGenricType(getClass());
    }  
  
	
	public List<T> find(Query query) {
		 return mongoTemplate.find(query, this.getEntityClass(),this.getClassName(this));  
	}

	
	public T findOne(Query query) {
		 return mongoTemplate.findOne(query, this.getEntityClass(),this.getClassName(this));  
	}

	
	public void update(Query query, Update update) {
		mongoTemplate.findAndModify(query, update, this.getEntityClass(),this.getClassName(this));
	}

	
	public T save(T entity) {
		 mongoTemplate.insert(entity, this.getClassName(this));
	     return entity;
	}

	public T findById(String id) {
		return mongoTemplate.findById(id, this.getEntityClass(),this.getClassName(this));
	}

	
	public T findById(String id, String collectionName) {
		 return mongoTemplate.findById(id, this.getEntityClass(), collectionName); 
	}

	public void remove(Query query){
		 mongoTemplate.remove(query, this.getClassName(this)); 
	}
	
	public MongoPagination<T> findPage(MongoPagination <T> page, Query query) {
        long count = this.count(query);  
        page.setTotalCount(Parse.toInt(count));
        int pageNumber = page.getCurrentPage();
        int pageSize = page.getPageSize();  
        query.skip((pageNumber - 1) * pageSize).limit(pageSize);  
        List<T> rows = this.find(query);  
        page.setRows(rows);  
        return page; 
	}

	public long count(Query query) {
		return mongoTemplate.count(query,this.getClassName(this));  
	}
	
	protected String getClassName(BaseMongoDao<T> self){
		return self.getEntityClass().getSimpleName();
	}
      
}  