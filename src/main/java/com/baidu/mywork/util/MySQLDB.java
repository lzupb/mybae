package com.baidu.mywork.util;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;


/**
 * MySQL示例，通过该示例可熟悉BAE平台MySQL的使用（CRUD）
 */
@Component
public class MySQLDB {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	private TransactionTemplate transactionTemplate;
	
	private static final Logger logger = LoggerFactory
			.getLogger(MySQLDB.class);

	public void update(final String sql, final Map<String, Object> params) throws SQLException {
		logger.info("MySQLDB update sql:"+sql);
		transactionTemplate.setReadOnly(false);
		transactionTemplate.execute(new TransactionCallback() {
			@Override
			public Object doInTransaction(TransactionStatus status) {
				return jdbcTemplate.update(sql, params);
			}			
		});		
		logger.info("MySQLDB update finished!");
	}
	
	public List<Map<String, Object>> query(final String sql,final Map<String, Object> params) throws SQLException {
		logger.info("MySQLDB query sql:"+sql);
		transactionTemplate.setReadOnly(true);
		return transactionTemplate.execute(new TransactionCallback<List<Map<String, Object>>>() {
			@Override
			public List<Map<String, Object>> doInTransaction(TransactionStatus status) {
				return jdbcTemplate.queryForList(sql, params);
			}			
		});			
	}
	

}
