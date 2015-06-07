package app.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import app.common.AppBase;

@Repository
public class AppBaseDao extends AppBase {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate_MainDataSource;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate_SlaveDataSource;

	@Autowired
	public void setMainDataSource(DataSource mainDataSource) {
	    this.namedParameterJdbcTemplate_MainDataSource = new NamedParameterJdbcTemplate(mainDataSource);
	}
	
	@Autowired
	public void setSlaveDataSource(DataSource slaveDataSource) {
		this.namedParameterJdbcTemplate_MainDataSource = new NamedParameterJdbcTemplate(slaveDataSource);
	}
	
	
	protected NamedParameterJdbcTemplate getQueryDao() {
		return namedParameterJdbcTemplate_SlaveDataSource;
	}
	
	protected NamedParameterJdbcTemplate getUpdateDao() {
		return namedParameterJdbcTemplate_MainDataSource;
	}
	
}
