package net.codejava.spring.dao;

import java.util.List;

import net.codejava.spring.model.EnvDetail;

public interface EnvDetailDAO {
	
	public void saveOrUpdatePortfolio(EnvDetail envdetail);
	
	public void saveOrUpdate(EnvDetail envdetail);
	
	public void delete(int envdetailId);
	
	public EnvDetail get(int envdetailId);
	
	public List<EnvDetail> list(String application, String portfolio);
	
	public List<String> appList(String portfolio);
	
	public String tokenForApp(String app, String portfolio);
	
	public List<String> portfolioList();
	
	public String pmName(String app, String portfolio);
}
