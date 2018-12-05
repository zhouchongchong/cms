package com.cloudminds.cms.dao.mongo.abs;

import com.cloudminds.cms.utils.GenericsUtils;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @Author: aiying014
 * Created by 76409 on 14:03 2018/7/10.
 * @Description:
 */
public abstract class BaseMongoDao<T> {
	private static final Logger _log = LoggerFactory.getLogger(BaseMongoDao.class);

	@Autowired
	protected MongoTemplate mongoTemplate;
	private static final String _ID = "_id";

	/**
	 * save
	 *
	 * @param model
	 * @return
	 */
  	public boolean save(T model){
  		boolean result = true;
		try {
			mongoTemplate.save(model);
		} catch (Exception e) {
			_log.error(e.getMessage(),e);
			result = false;
		}
		return result;
	}

	/**
	 * save
	 *
	 * @param model
	 * @return
	 */
	public T saveEntity(T model){
		try {
			mongoTemplate.save(model);
		} catch (Exception e) {
			_log.error(e.getMessage(),e);
		}
		return model;
	}

	/**
	 * base model id to remove record in db
	 *
	 * @param id model id
	 * @param tClass
	 * @return
	 */
	public boolean removeById(String id,Class<T> tClass){
  		boolean result;
		try {
			DeleteResult deleteResult = mongoTemplate.remove(new Query(Criteria.where(_ID).is(id)), tClass);
			result = deleteResult.wasAcknowledged();
		} catch (Exception e) {
			_log.error(e.getMessage(),e);
			result = false;
		}
		return result;
	}

	/**
	 * base model to del document in db
	 * @param model
	 * @return
	 */
	public boolean removeAllByCondition(T model){
		boolean result;
		try {
			Query query = beFromeModel(model);
			DeleteResult remove = mongoTemplate.remove(query, model.getClass());
			result = remove.wasAcknowledged();
		} catch (Exception e) {
			_log.error(e.getMessage(),e);
			result = false;
		}
		return result;
	}
	/**
	 * 获取需要操作的实体类class
	 *
	 * @return
	 */
	private Class<T> getEntityClass(T tClass) {
		return GenericsUtils.getSuperClassGenricType(tClass.getClass());
	}

	/**
	 * 根据id 精确查找
	 * @param id
	 * @param tClass
	 * @return
	 */
	public T findById(String id,T tClass){
		return mongoTemplate.findById(id, getEntityClass( tClass));
	}

	/**
	 * 根据条件精确查找
	 *
	 * @param model
	 * @return
	 */
	public List<T> findByCondition(T model,Class tClass){
		Query query = beFromeModel(model);
		return mongoTemplate.find(query,tClass);
	}

	/**
	 * 组装精确的查询语句
	 *
	 * @param model
	 * @return
	 */
	private Query beFromeModel(T model){
		return beFromeModel(model,true);
	}
	/**
	 * 组装精确的查询语句
	 *
	 * @param model
	 * @return
	 */
	private Query beFromeModel(T model,boolean flag){
		Query query = new Query();
		Field[] fields = model.getClass().getDeclaredFields();
		try {
			for (Field field : fields) {
				field.setAccessible(true);
				if (!StringUtils.isEmpty(field.get(model))){
					if (flag){
						query.addCriteria(Criteria.where(field.getName()).is(field.get(model)));
					} else {
						String regStr =  "^.*%s.*$";
						Pattern compile = Pattern.compile(String.format(regStr,field.get(model)), Pattern.CASE_INSENSITIVE);
						query.addCriteria(Criteria.where(field.getName()).regex(compile));
					}
				}
			}
		} catch (IllegalAccessException e) {
			_log.error(e.getMessage(),e);
		}
		return query;
	}

	/**
	 * 通过反射获取字段的值
	 *
	 * @param model
	 * @param filedName
	 * @return
	 * @throws Exception
	 */
	private Object filedValue(T model,String filedName) throws Exception{
		Field declaredField = model.getClass().getDeclaredField(filedName);
		declaredField.setAccessible(true);
		return declaredField.get(model);
	}

	/**
	 * 修改
	 *
	 * @param model
	 * @return
	 */
	public boolean updateOne(T model,Class tClass){
		UpdateResult updateResult = null;
		try {
			Query query = new Query();
			if (!StringUtils.isEmpty(filedValue(model,_ID))){
				query.addCriteria(Criteria.where(_ID).is(filedValue(model,_ID)));
			}
			Update update = new Update();
			updateSets(update,model);
			updateResult = mongoTemplate.updateFirst(query, update, tClass);
		} catch (Exception e) {
			_log.error(e.getMessage(),e);
		}
		return updateResult.wasAcknowledged();
	}

	/**
	 * 设置 update
	 *
	 * @param update
	 * @param model
	 */
	private void updateSets(Update update,T model){
		Field[] fields = model.getClass().getDeclaredFields();
		try {
			for (Field field : fields) {
				field.setAccessible(true);
				if (!StringUtils.isEmpty(field.get(model))){
					update.set(field.getName(),field.get(model));
				}
			}
		} catch (IllegalAccessException e) {
			_log.error(e.getMessage(),e);
		}
	}
}
