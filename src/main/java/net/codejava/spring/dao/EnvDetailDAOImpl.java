package net.codejava.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import net.codejava.spring.model.EnvDetail;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

/**
 * An implementation of the ContactDAO interface.
 * @author www.codejava.net
 *
 */
public class EnvDetailDAOImpl implements EnvDetailDAO {

	private JdbcTemplate jdbcTemplate;
	
	public EnvDetailDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void saveOrUpdatePortfolio(EnvDetail envdetail) {
		//derby local
		String sql = "INSERT INTO ENV_APPLICATION (id,portfolio,pm,application,token)"
				+ " VALUES (NEXT VALUE FOR port_id,?,?,?,?)";
	jdbcTemplate.update(sql,envdetail.getPortfolio(),envdetail.getPm(), envdetail.getApplication(),envdetail.getToken());
		
	}
	
	@Override
	public void saveOrUpdate(EnvDetail envdetail) {
		if (envdetail.getId() > 0) {
			// update
			String sql = "UPDATE ENV_DETAIL SET portfolio=?,pm=?,application=?,environment=?, ip=?, hostname=?, "
						+ "username=?,type=? WHERE id=?";
			jdbcTemplate.update(sql, envdetail.getPortfolio(),envdetail.getPm(),envdetail.getApplication(),envdetail.getEnvironment(), envdetail.getIp(),
					envdetail.getHostname(), envdetail.getUsername(), envdetail.getType(),envdetail.getId());
		} else {
			// insert
					
			//SIT Test servers
			/*String sql = "INSERT INTO ENV_DETAIL (id,portfolio,pm,application,environment, ip, hostname, username,type)"
					+ " VALUES (id_sequence.nextval,?,?,?,?,?,?,?,?)";*/
			//Local
			String sql = "INSERT INTO ENV_DETAIL (id,portfolio,pm,application,environment, ip, hostname, username,type)"
					+ " VALUES (NEXT VALUE FOR seq_id,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql,envdetail.getPortfolio(),envdetail.getPm(), envdetail.getApplication(),envdetail.getEnvironment(), envdetail.getIp(),
				envdetail.getHostname(), envdetail.getUsername(),envdetail.getType());
		}
		
	}

	@Override
	public void delete(int envdetailId) {
		String sql = "DELETE FROM ENV_DETAIL WHERE id=?";
		jdbcTemplate.update(sql, envdetailId);
	}

	@Override
	public List<EnvDetail> list(String application, String portfolio) {		
		
		
		String sql = "SELECT * FROM ENV_DETAIL where application="+"'"+application+"' and portfolio="+"'"+portfolio+"' ORDER BY ENVIRONMENT";
		
		List<EnvDetail> listEnvDetail = jdbcTemplate.query(sql, new RowMapper<EnvDetail>() {

			@Override
			public EnvDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
				EnvDetail envdetail = new EnvDetail();
	
				envdetail.setId(rs.getInt("id"));
				envdetail.setPortfolio(rs.getString("portfolio"));
				envdetail.setPm(rs.getString("pm"));
				envdetail.setApplication(rs.getString("application"));
				envdetail.setEnvironment(rs.getString("environment"));
				envdetail.setIp(rs.getString("ip"));
				envdetail.setHostname(rs.getString("hostname"));
				envdetail.setUsername(rs.getString("username"));
				envdetail.setType(rs.getString("type"));
				
				return envdetail;
			}
			
		});
				
		return listEnvDetail;
	}

	@Override
	public EnvDetail get(int envdetailId) {
		String sql = "SELECT * FROM ENV_DETAIL WHERE id=" + envdetailId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<EnvDetail>() {

			@Override
			public EnvDetail extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					EnvDetail envdetail = new EnvDetail();
					envdetail.setId(rs.getInt("id"));
					envdetail.setPortfolio(rs.getString("portfolio"));
					envdetail.setPm(rs.getString("pm"));
					envdetail.setApplication(rs.getString("application"));
					envdetail.setEnvironment(rs.getString("environment"));
					envdetail.setIp(rs.getString("ip"));
					envdetail.setHostname(rs.getString("hostname"));
					envdetail.setUsername(rs.getString("username"));
					envdetail.setType(rs.getString("type"));
					return envdetail;
				}
				
				return null;
			}
			
		});
	}

	@Override
	public List<String> appList(String portfolio) {
		String sql = "SELECT application FROM ENV_APPLICATION WHERE portfolio='" + portfolio+"' order by application";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<String>>() {

			@Override
			public List<String> extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				List<String> list = new ArrayList<String>();
				while(rs.next()) {
					
					list.add(rs.getString("application"));
					
				}
				
				return list;
			}
			
		});
	}

	@Override
	public String tokenForApp(String app, String portfolio) {
		String sql = "SELECT token FROM ENV_APPLICATION WHERE portfolio='" + portfolio+"' and application='"+app+"'" ;
		 return jdbcTemplate.query(sql,new ResultSetExtractor<String>() {

				@Override
				public String extractData(ResultSet rs) throws SQLException,
						DataAccessException {
					if (rs.next()) {
						
						return rs.getString("token");
					}
					
					return null;
				}
				
			});
	}

	@Override
	public List<String> portfolioList() {
		String sql = "SELECT distinct(portfolio) FROM ENV_APPLICATION order by portfolio";
		return jdbcTemplate.query(sql,new ResultSetExtractor<List<String>>() {

			@Override
			public List<String> extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				List<String> list = new ArrayList<String>();
				while (rs.next()) {
					
					list.add(rs.getString("portfolio"));
				}
				
				return list;
			}
			
		});
	}

	@Override
	public String pmName(String app, String portfolio) {
		String sql = "SELECT PM FROM ENV_APPLICATION WHERE portfolio='" + portfolio+"' and application='"+app+"'" ;
		 return jdbcTemplate.query(sql,new ResultSetExtractor<String>() {

				@Override
				public String extractData(ResultSet rs) throws SQLException,
						DataAccessException {
					if (rs.next()) {
						
						return rs.getString("PM");
					}
					
					return null;
				}
				
			});
	}

	

	
}
