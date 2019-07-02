* # 配置
#### 使用该模块需要引用配置项,并做相应配置,配置项参考如下:
[public-conf.mongodb](https://github.com/0nebean/public.conf/blob/master/conf/public-conf.mongodb.properties)


#### 生成mongoDB 数据模型代码 如下,运行mian方法按照提示生成即可:
```java
    public class CodeTool {
    
        public static void main(String[] args) {
            /*生成mysql orm java代码*/
    //        CreateJavaTool.runIt();
    //        /*生成mongodb orm java代码*/
            CreateMongoJavaTool.runIt();
    //        /*密码生成器*/
    //        PasswordGetter.runIt();
        }
    }

```
#### 列入生成的Model名称是 `TestInfo` 将会得到如下类
* TestInfo (实体类)
* TestInfoDao (mongoBD interface)
* TestInfoService (business interface)
* TestInfoServiceImpl  (business interface impl)

#### 其中 business interface 包含了若干CRUD方法实现 可以直接调用
```java
    public List<T> find(Query query);

    public T findOne(Query query);

    public void update(Query query, Update update);

    public T save(T entity);

    public T findById(String id);

    public T findById(String id, String collectionName);

    public MongoPagination<T> findPage(MongoPagination<T> page, Query query);

    public long count(Query query);
```