package net.codejava.spring.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.OutputStream;

import java.util.List;
import java.util.ArrayList;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.codejava.spring.dao.EnvDetailDAO;
import net.codejava.spring.model.EnvDetail;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.codejava.spring.utils.Constants;

@Controller
public class EnvDetailController {
	int row;

	@Autowired
	private EnvDetailDAO envdetailDAO;

	@RequestMapping(value = "/")
	public ModelAndView welcomeView(ModelAndView model) {
		model.addObject("portfolioList", envdetailDAO.portfolioList());
		//System.out.println(envdetailDAO.portfolioList());
		model.setViewName("welcome");
		return model;
	}
	@RequestMapping(value = "/Portfolio")
	public ModelAndView listEnvDetail(HttpServletRequest request, ModelAndView model) {
		String portfolio = request.getParameter("portfolio");
		model.addObject("portfolio",portfolio);
		//System.out.println(envdetailDAO.appList(portfolio));
		//model.addObject("applications",getAppList(portfolio));
		model.addObject("applications",envdetailDAO.appList(portfolio));
		model.setViewName("Portfolio");
		return model;

	}

	@RequestMapping(value = "/authentication")
	public ModelAndView checkAuth(HttpServletRequest request) throws IOException {

		return new ModelAndView("Authentication", "command", new EnvDetail());
	}

	@RequestMapping(value = "/checkAccessToken", method = RequestMethod.POST)
	public ModelAndView checkAccessToken(EnvDetail envdetail, ModelAndView model) throws IOException {		
		String app = envdetail.getApplication();
		String portfolio = envdetail.getPortfolio();
		if(envdetail.getToken().equals(envdetailDAO.tokenForApp(app, portfolio).toLowerCase())) {
		//if(getAppList(portfolio).contains(app) && envdetail.getToken().equals(app.toLowerCase())) {
			
			List<EnvDetail> envdetail1 = envdetailDAO.list(envdetail.getApplication(), envdetail.getPortfolio());
			model.addObject("envdetail", envdetail1);			
			model.addObject("portfolio",portfolio);
			model.addObject("app",app);
			model.setViewName("app");
		}
		else {
			model.setViewName("wrong");
		}
		return model;

	}
	@RequestMapping(value = "/newPortfolioDetail", method = RequestMethod.GET)
	public ModelAndView newPortfolioDetail(HttpServletRequest request, ModelAndView model) {

		EnvDetail newenvdetail = new EnvDetail();
		model.addObject("envdetail", newenvdetail);
		model.setViewName("EnvPortfolioForm");
		return model;
	}
	@RequestMapping(value = "/newApplicationDetail", method = RequestMethod.GET)
	public ModelAndView newApplicationDetail(HttpServletRequest request, ModelAndView model) {

		String portfolio = request.getParameter("portfolio");
		EnvDetail newenvdetail = new EnvDetail();
		model.addObject("envdetail", newenvdetail);
		model.addObject("portfolio", portfolio);
		model.setViewName("EnvApplicationForm");
		return model;
	}
	@RequestMapping(value = "/newEnvDetail", method = RequestMethod.GET)
	public ModelAndView newEnvDetail(HttpServletRequest request, ModelAndView model) {

		String app = request.getParameter("app");
		String portfolio = request.getParameter("portfolio");
		System.out.println(app + "    "+ portfolio);
		EnvDetail newenvdetail = new EnvDetail();
		model.addObject("envdetail", newenvdetail);
		if (envdetailDAO.appList(portfolio).contains(app)) {
		//if (getAppList(portfolio).contains(app)) {
			model.addObject("app", app);
			model.addObject("portfolio", portfolio);
			model.addObject("PM", envdetailDAO.pmName(app, portfolio));
			model.setViewName("EnvCreateForm");
		}
		else {
			model.setViewName("wrong");
		}

		return model;
	}

	@RequestMapping(value = "/savePortfoliodetail", method = RequestMethod.POST)
	public ModelAndView savePortfoliodetail(@ModelAttribute EnvDetail envdetail, ModelAndView model) {

		
		envdetailDAO.saveOrUpdatePortfolio(envdetail);
		
		model.addObject("portfolioList", envdetailDAO.portfolioList());
		
		model.setViewName("welcome");

		return model;
	}
	/*@RequestMapping(value = "/saveApplicationdetail", method = RequestMethod.POST)
	public ModelAndView saveApplicationdetail(@ModelAttribute EnvDetail envdetail, ModelAndView model) {

		
		envdetailDAO.saveOrUpdatePortfolio(envdetail);
		
		model.addObject("portfolioList", envdetailDAO.portfolioList());
		
		model.setViewName("welcome");

		return model;
	}*/

	
	@RequestMapping(value = "/saveEnvdetail", method = RequestMethod.POST)
	public ModelAndView saveEnvDetail(@ModelAttribute EnvDetail envdetail, ModelAndView model) {

		String app = envdetail.getApplication();
		String portfolio = envdetail.getPortfolio();
		envdetailDAO.saveOrUpdate(envdetail);
		List<EnvDetail> envdetail1 = envdetailDAO.list(app, portfolio);
		model.addObject("envdetail", envdetail1);
		if (envdetailDAO.appList(portfolio).contains(app)) {
	//	if (getAppList(portfolio).contains(app)) {
			model.addObject("portfolio", portfolio);
			model.addObject("app", app);
			
			model.setViewName("app");		
		}else {
			model.setViewName("wrong");
		}

		return model;
	}

	@RequestMapping(value = "/deleteEnv", method = RequestMethod.GET)
	public ModelAndView deleteEnvdetail(HttpServletRequest request, ModelAndView model) {
		int envdetailId = Integer.parseInt(request.getParameter("id"));
		String app = request.getParameter("app");
		String portfolio = request.getParameter("portfolio");
		envdetailDAO.delete(envdetailId);
		List<EnvDetail> envdetail1 = envdetailDAO.list(app, portfolio);
		model.addObject("envdetail", envdetail1);
		System.out.println("Delete :'" +app+"'  '"+portfolio+"'  "+ envdetailDAO.appList(portfolio) +"  "+envdetailDAO.appList(portfolio).contains(app)  );
		
		
			model.addObject("portfolio", portfolio);
			model.addObject("app", app);
			model.setViewName("app");		
		

		return model;

	}

	@RequestMapping(value = "/editEnv", method = RequestMethod.GET)
	public ModelAndView editContact(HttpServletRequest request) {
		int envdetailId = Integer.parseInt(request.getParameter("id"));		
		EnvDetail envdetail = envdetailDAO.get(envdetailId);
		ModelAndView model = new ModelAndView("EnvEditForm");
		model.addObject("envdetail", envdetail);

		return model;
	}

	@ModelAttribute("envList")
	public List<String> getEnvList() {
		List<String> envList = new ArrayList<String>();
		envList.add("DEV");
		envList.add("STAGING");
		envList.add("PRODUCTION");
		return envList;
	}

	@ModelAttribute("typeList")
	public List<String> getTypeList() {
		List<String> typeList = new ArrayList<String>();
		typeList.add("UNIX");
		typeList.add("DB");
		typeList.add("WINDOWS");

		return typeList;
	}

	/*@ModelAttribute("appList")
	public List<String> getAppList(String prtflo) {
		List<String> appList = new ArrayList<String>();
		HashMap<String, List<String>> map = new HashMap<String, List<String>>();
		String portfolio[] = { "RA", "BI", "BSS", "CA", "TESTING","MyApp" };

		String RAApps[] = { "SDP", "LEGAL", "CG", "OSSAPPS", "MSMART", "WELCOMELETTER", "MDM", "CALLIDUS", "MONEYMAP",
				"SMSK", "EBPP", "RMATTENDENCE", "MEDIATION", "DSPSMS", "CGNAT", "SUPERCC", "CIRCLEAPPLICATIONS",
				"CALLDETAILMODULE", "IRPACKEXPIRY", "DOXODS", "TNV" };
		String CAApps[] = { "OTAPUSH", "AlertDB", "CAD", "UCEP", "BAM", "mPesaBAM", "EBAM", "SAG", "CRMonSMS", "CSApps",
				"TYC", "KV", "FileNet", "Hiflier", "HBO", "MGM", "PortingPortal", "Irate", "OCRM", "LMS", "MPESACRM",
				"MSG", "OCG", "CCG", "VASPORT", "RetailerGateway", "MNPVAS", "Gbilling", "Netflix", "GAM", "DND",
				"HLRView", "ECRM", "IVR", "OBD", "EMC", "WebCRM", "PushSMS", "MpesaWebCRM", "MpesaPushSMS", "POD",
				"SecurityCheck", "CQMViewer", "CTI", "SSK", "TCCMS", "TDS", "TMS", "TariffCall", "CSS", "ESS", "IR",
				"BillOnHandset", "PAG", "SMSWork", "PDGOnline", "TITAN", "WAP", "CCSIpaymentAPInew", "ESSAdmin",
				"CSSAdminTool", "MYVFAPIS", "CSSPaymentPostingEAR", "RequestProcesorbeanEAR", "myvfkeywords",
				"ASRMAdmitTool", "Emitra" };
		String BIApps[] = { "DWH", "MISDISHA", "MIS", "MICS", "MMFA", "TNPS", "BigData", "Smart", "NetPerform" };
		String BSSApps[] = { "MyWorld", "SasDedup", "Hyperion", "EAI" };
		String TESTINGApps[] = { "ClearCase", "ClearQuest", "RationalTeamConcert", "RationalDoorsNextGeneration",
				"RationalInsight", "RationalDB", "DSRTool", "IBMRationalPerformanceTester", "HPALMQC", "SPARXSEERSEM",
				"PMOPortal", "UrbanCodeRelease", "UrbanCodeDeploy" };
		
		String MyApp[] = { "ClearCase", "ClearQuest", "RationalTeamConcert", "RationalDoorsNextGeneration",
				"RationalInsight", "RationalDB", "DSRTool", "IBMRationalPerformanceTester", "HPALMQC", "SPARXSEERSEM",
				"PMOPortal", "UrbanCodeRelease", "UrbanCodeDeploy" };
		
		List<List<String>> ls = new ArrayList<List<String>>();
		List<String> listRA = new ArrayList<String>(Arrays.asList(RAApps));
		List<String> listBI = new ArrayList<String>(Arrays.asList(BIApps));
		List<String> listBSS = new ArrayList<String>(Arrays.asList(BSSApps));
		List<String> listCA = new ArrayList<String>(Arrays.asList(CAApps));
		List<String> listTESTING = new ArrayList<String>(Arrays.asList(TESTINGApps));
		List<String> listMyApp = new ArrayList<String>(Arrays.asList(MyApp));

		ls.add(listRA);
		ls.add(listBI);
		ls.add(listBSS);
		ls.add(listCA);
		ls.add(listTESTING);
		ls.add(listMyApp);

		for (int i = 0; i < portfolio.length; i++) {
			map.put(portfolio[i], ls.get(i));
		}

		appList = map.get(prtflo);
		return appList;
	}*/

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void downloadExcel(HttpServletRequest request, HttpServletResponse response, ModelAndView model)
			throws IOException {
		String portfolio = request.getParameter("portfolio");
		String path = Constants.path;
		String fullFilePath = path + portfolio + "_ServerDetails.xlsx";
		boolean flag = generateExcelFile(fullFilePath, portfolio);

		System.out.println("File Generated : " + flag);

		
		System.out.println("Downloading File ...");
		File downloadFile = new File(fullFilePath);
		FileInputStream inStream = new FileInputStream(downloadFile);
		response.setContentLength((int) downloadFile.length());

		// forces download
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", portfolio + "_EnvManagementDataSheet.xlsx");
		response.setHeader(headerKey, headerValue);

		// obtains response's output stream
		OutputStream outStream = response.getOutputStream();

		byte[] buffer = new byte[4096];
		int bytesRead = -1;

		while ((bytesRead = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, bytesRead);
		}

		inStream.close();
		outStream.close();
		System.out.println("File Downloaded !!!");

	}

	public boolean generateExcelFile(String path, String portfolio) {
		boolean flag = false;
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet;
		//List<String> appList = getAppList(portfolio);
		List<String> appList = envdetailDAO.appList(portfolio);
		try {
			FileOutputStream out = new FileOutputStream(new File(path));

			for (String app : appList) {
				sheet = workbook.createSheet(app);
				List<EnvDetail> envdetail = envdetailDAO.list(app.toString(), portfolio);

				flag = templateGeneration(workbook, sheet, envdetail, app);
			}

			workbook.write(out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return flag;
	}

	private boolean templateGeneration(XSSFWorkbook workbook, XSSFSheet sheet, List<EnvDetail> envdetail, String app) {

		boolean flag = true;
		List<String> list = new ArrayList<String>();
		list.add("ID");
		list.add("PORTFOLIO");
		list.add("PM");
		list.add("APPLICATION");
		list.add("ENVIRONMENT");
		list.add("IP");
		list.add("HOSTNAME");
		list.add("USERNAME");
		list.add("TYPE");

		XSSFCell cell;
		XSSFRow row0, row1, row;
		row0 = sheet.createRow(0);
		row1 = sheet.createRow(1);
		int i = 0, j = 0;

		XSSFFont font0 = workbook.createFont();
		font0.setBold(true);
		font0.setFontName("Comic Sans MS");
		font0.setColor(new XSSFColor(new java.awt.Color(255, 255, 255)));

		XSSFCellStyle style0 = workbook.createCellStyle();
		style0.setFillForegroundColor(new XSSFColor(new java.awt.Color(189, 215, 238)));
		style0.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style0.setAlignment(HorizontalAlignment.CENTER);
		style0.setVerticalAlignment(VerticalAlignment.CENTER);
		style0.setFont(font0);

		XSSFFont fontHeader = workbook.createFont();
		fontHeader.setBold(true);
		fontHeader.setFontName("Calibri");

		XSSFCellStyle styleHeader = workbook.createCellStyle();
		styleHeader.setFillForegroundColor(new XSSFColor(new java.awt.Color(155, 194, 230)));
		styleHeader.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		styleHeader.setDataFormat((short) 80);
		styleHeader.setWrapText(true);
		styleHeader.setBorderBottom(BorderStyle.MEDIUM);
		styleHeader.setBorderTop(BorderStyle.MEDIUM);
		styleHeader.setBorderRight(BorderStyle.THIN);
		styleHeader.setAlignment(HorizontalAlignment.CENTER);
		styleHeader.setFont(fontHeader);

		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, list.size() - 1));

		cell = row0.createCell(0);
		cell.setCellValue(app);
		cell.setCellStyle(style0);

		for (String li : list) {
			sheet.setColumnWidth(i, 5000);
			cell = row1.createCell(i++);
			cell.setCellValue(li);
			cell.setCellStyle(styleHeader);
		}

		XSSFFont font = workbook.createFont();

		XSSFCellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(new XSSFColor(new java.awt.Color(255, 242, 204)));
		// style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		styleHeader.setBorderBottom(BorderStyle.MEDIUM);
		styleHeader.setBorderTop(BorderStyle.MEDIUM);
		styleHeader.setBorderRight(BorderStyle.THIN);
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setFont(font);

		i = 2;
		for (EnvDetail bean : envdetail) {
			j = 0;
			sheet.setColumnWidth(i, 5000);
			row = sheet.createRow(i++);

			cell = row.createCell(j++);
			cell.setCellValue(bean.getId());
			cell.setCellStyle(style);

			cell = row.createCell(j++);
			cell.setCellValue(bean.getPortfolio());
			cell.setCellStyle(style);

			cell = row.createCell(j++);
			cell.setCellValue(bean.getPm());
			cell.setCellStyle(style);

			cell = row.createCell(j++);
			cell.setCellValue(bean.getApplication());
			cell.setCellStyle(style);

			cell = row.createCell(j++);
			cell.setCellValue(bean.getEnvironment());
			cell.setCellStyle(style);

			cell = row.createCell(j++);
			cell.setCellValue(bean.getIp());
			cell.setCellStyle(style);

			cell = row.createCell(j++);
			cell.setCellValue(bean.getHostname());
			cell.setCellStyle(style);

			cell = row.createCell(j++);
			cell.setCellValue(bean.getUsername());
			cell.setCellStyle(style);

			cell = row.createCell(j++);
			cell.setCellStyle(style);
			cell.setCellValue(bean.getType());

		}

		return flag;
	}

}
