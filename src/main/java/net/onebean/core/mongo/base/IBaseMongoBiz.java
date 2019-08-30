package net.onebean.core.mongo.base;

import net.onebean.core.mongo.query.MongoPagination;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

/**
 * mangoDB service接口的父接口
 * @author 0neBean
 *
 * @param <T>
 */
public interface IBaseMongoBiz<T> {

    /**
     * 查询方法
     * @param query 查询条件
     * @return List<T>
     */
    List<T> find(Query query) ;
  
    /** 
     * 通过一定的条件查询一个实体 
     *  
     * @param query 
     * @return 
     */  
    public T findOne(Query query) ;  
  
    /** 
     * 通过条件查询更新数据 
     *  
     * @param query 
     * @param update 
     * @return 
     */  
    public void update(Query query, Update update) ;  
  
    /** 
     * 保存一个对象到mongodb 
     *  
     * @param entity 
     * @return 
     */  
    public T save(T entity) ;  
  
    /** 
     * 通过ID获取记录 
     *  
     * @param id 
     * @return 
     */  
    public T findById(String id) ;  
  
    /** 
     * 通过ID获取记录,并且指定了集合名(表的意思) 
     *  
     * @param id 
     * @param collectionName 
     *            集合名 
     * @return 
     */  
    public T findById(String id, String collectionName) ;  
      
    /** 
     * 分页查询 
     * @param page 
     * @param query 
     * @return 
     */  
    public MongoPagination<T> findPage(MongoPagination <T> page, Query query);
      
    /** 
     * 求数据总和 
     * @param query 
     * @return 
     */  
    public long count(Query query);  
      
}  