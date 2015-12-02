package com.baidu.mywork.util;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class MongoUtil {

	public static void save(Object entity) {
		MongoTemplate mongoTemplate = SpringContextUtil.getBeanByType(MongoTemplate.class);
		mongoTemplate.save(entity);
	}
	
	public static <T> T loadByKey(String key,String value,Class<T> entityClass) {
		MongoTemplate mongoTemplate = SpringContextUtil.getBeanByType(MongoTemplate.class);
		Query query = new Query(Criteria.where(key).is(value));
		return mongoTemplate.findOne(query, entityClass);
	}

}
