package com.dist.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.jsp.JspWriter;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dist.action.admin.BasicAction;
import com.dist.entity.PSubscriptionsource;
import com.dist.entity.SNews;
import com.dist.entity.SNewsComment;
import com.dist.entity.SNewspic;
import com.dist.pagentity.Json;
import com.dist.pagentity.MobileApplication;
import com.dist.pagentity.AppsPageShow;
import com.dist.pagentity.BasePageShow;
import com.dist.service.PSubscriptionsourceServiceI;
import com.dist.service.SNewsCommentServiceI;
import com.dist.service.SNewsServiceI;
import com.dist.service.SNewspicServiceI;
import com.opensymphony.xwork2.ModelDriven;

@Controller
public class SnewsAction extends BasicAction implements ModelDriven<SNews> {

	private static final long serialVersionUID = -5805035190964460569L;
	private SNews news = new SNews();

	private SNewsServiceI newsService;
	private SNewspicServiceI newsPicService;
	private SNewsCommentServiceI SncommentService;
	private List<SNews> listnews;
	private String selectedIndex = "3.0";

	private AppsPageShow pageShow = new AppsPageShow();

	private File picfile;
	private String picfileFileName;
	private File upload;
	private String uploadFileName;
	
	// 所有的订阅消息类型列表
	private PSubscriptionsourceServiceI subScriSourceService;
	
	@Resource
	public void setSubScriSourceService(
			PSubscriptionsourceServiceI subScriSourceService) {
		this.subScriSourceService = subScriSourceService;
	}

	private List<PSubscriptionsource> subSourceList;
	
	

	public List<PSubscriptionsource> getSubSourceList() {
		return subSourceList;
	}

	public void setSubSourceList(List<PSubscriptionsource> subSourceList) {
		this.subSourceList = subSourceList;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	
	
	@Resource
	public void setSncommentService(SNewsCommentServiceI sncommentService) {
		SncommentService = sncommentService;
	}

	@Resource
	public void setNewsPicService(SNewspicServiceI newsPicService) {
		this.newsPicService = newsPicService;
	}

	@Resource
	public void setNewsService(SNewsServiceI newsService) {
		this.newsService = newsService;
	}

	@Override
	public SNews getModel() {
		// TODO Auto-generated method stub
		return news;
	}

	public String getSelectedIndex() {
		return selectedIndex;
	}

	public void setSelectedIndex(String selectedIndex) {
		this.selectedIndex = selectedIndex;
	}

	/**
	 * 查询及显示主页 新闻通知主页，获取主页的新闻通知列表等信息
	 * 
	 * @return 相应类型的app应用 listsnews 及 不同类型的应用的数目 封装在了 pageShow里面
	 *         搜索时调用接口snews!index.action?pageShow.searchinfo=
	 *         &pageShow.type=0,1,2,3,4....
	 */
	public String index() {
		pageShow.setRows(BasePageShow.PAGESIZE);
		long temptotal = 0;
		listnews = newsService.find(pageShow.getSearchinfo(),pageShow.getPageNow(), 888);
		for(SNews item :listnews){
			item.setSNewsComment(null);
			item.setSNewspics(null);
		}
//		System.out.println("新闻列表"+JSON.toJSONString(listnews));
		// 获取选中要现实的设备状态类型的总数
		temptotal = newsService.find(pageShow.getSearchinfo(), 0, 10000000).size();
		// 设置总页数
		pageShow.setTotal(temptotal % BasePageShow.PAGESIZE == 0 ? temptotal
				/ BasePageShow.PAGESIZE : temptotal / BasePageShow.PAGESIZE + 1);
		if (pageShow.getPageNow() <= 0) {
			pageShow.setPageNow(1);
		}
		subSourceList= subScriSourceService.findAll();
		for(PSubscriptionsource item : subSourceList){
			item.setSNews(null);
			item.setPUsersubscriptions(null);
		}
		
		return "index";
	}

	public String add() {
		subSourceList= subScriSourceService.findAll();
		return "add";
	}
	public void deleteImg(){
		newsPicService.deleteById(news.getId());
		Json j=new Json();
		j.setSuccess(true);
		super.writeJson(j);
	}
	/**
	 * 删除新闻通知
	 * 
	 * @return 调用用接口snews!delete.action?app.id=
	 */
	public void delete() {
		
//		news=newsService.findById(news.getId());
		try {
			List<SNewspic> listpic =newsPicService.findByProperty("SNews.id", news.getId());
			for (SNewspic pic : listpic) {
				newsPicService.deleteById(pic.getId());
			}
			listpic.clear();
			List<SNewsComment> listcom =SncommentService.findByProperty("SNews.id", news.getId());
			for (SNewsComment comment : listcom) {
				SncommentService.deleteById(comment.getId());
			}
//			news.getPSubscriptionsource().getSNews().remove(news);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
		newsService.deleteById(news.getId());
		Json j=new Json();
		j.setSuccess(true);
		super.writeJson(j);
	}

	// /**
	// *获取终端应用列表信息
	// */
	// public void appList() {
	// listsnews=snewsService.findAll();
	// JSONObject obj=new JSONObject();
	// List<Application> apps = new ArrayList<Application >();
	// if (listsnews != null && listsnews.size() > 0) {
	// for (SNews t : listsnews) {
	// Application u = new Application();
	// BeanUtils.copyProperties(t, u);
	// apps.add(u);
	// }
	// }
	// obj.put("result", JSON.toJSONString(apps));
	// obj.put("state", "true");
	// super.writeJson(obj);
	// }

	/**
	 * 获取某个新闻通知的详情
	 * 
	 * @return 调用用接口snews!detail.action?app.id=。。。。
	 * 
	 */
	public String detail() {
		news = newsService.findById(news.getId());
		subSourceList= subScriSourceService.findAll();
//		System.out.println("获取NEWS=：" + JSON.toJSONString(news));
		return "detail";
	}

	
	
	
	/**
	 * 手机端浏览新闻详情
	 * @return
	 */
	public String   mobileNewsdetail(){
		news=newsService.findById(news.getId());
		
		return "newsinfo";
	}
	
	
	
	
	
	/**
	 * 更新新闻通知
	 * 
	 * @return 调用用接口snews!update.action?app.id =.. &app.name=........
	 */
	public String update() {
		SNews temp = newsService.findById(news.getId());
		temp.setInfoUrl(news.getInfoUrl());
		temp.setContent(news.getContent());
		temp.setTitle(news.getTitle());
		temp.setType(news.getType());
		newsService.save(temp);
		saveSubIcon(news.getId());
		return "gotolist";
	}

	/**
	 * 添加新闻通知 将自己的新闻通知发布注册到后台服务端
	 * 
	 * @return 调用用接口snews!update.action?app.id =.. &app.name=........
	 */
	public String save() {
		news.setTime(new Timestamp((new java.util.Date()).getTime()));
		String newsid = newsService.save(news);
		return saveSubIcon(newsid);
		
	}
	/*
	 * 保存简介图片
	 */
	public String saveSubIcon(String newsid){
			
//	     SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss"); 
//       Calendar calendar = Calendar.getInstance(); 
////      news.set(sdf.format(calendar.getTime()));
//   
//      
//       String root_ori= "C:\\project/appIcon/origin_newsIcon";
//       String  root_suo= "C:\\project/appIcon/newsIcon";
//       
//       
//   //取文件后缀名 
//    String picfilename=sdf.format(calendar.getTime()) +picfileFileName;  
//    try {	
//	     InputStream is1=new FileInputStream(picfile); 
////	     root1= ServletActionContext.getRequest().getRealPath("/appIcon/newsIcon") ;//存储到apache项目发布目录上 	     
//        
//	     File copyFile1=new File(root_ori,picfilename); 	     
//	     OutputStream os1=new FileOutputStream(copyFile1);	     
//	    byte[] buffer1=new byte[400]; 	     
//	    int length1=0; 	     
//	    while((length1=is1.read(buffer1)) > 0 ){ 
//	       os1.write(buffer1, 0, length1); 
//	     } 
//	     is1.close(); 
//	     os1.close(); 				     
//	     
////	     保存上传的图片
//	     String newsid=newsService.save(news);
//	     SNewspic newspic=new SNewspic();
//	     newspic.setPicname(picfilename);
//	     newspic.setPath(picfilename);
//	     newspic.setSNews(newsService.findById(newsid));
//	     newsPicService.save(newspic);				     
//			} catch (Exception e) {
//				// TODO: handle exception
//				e.printStackTrace();
//				 return "error"; 
//			}
//    
////     保存缩略图
//       try {
//			Thumbnails.of( new File(root_ori + "/" + picfilename+".jpg"))
//			.size(160, 100)
//			.toFile( new File(root_suo + "/" + picfilename+".jpg"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	
//	return "success";

			
		
	       String root_ori= "C:\\project/appIcon/origin_newsIcon";
	       String  root_suo= "C:\\project/appIcon/newsIcon";
		
		if (picfileFileName == null)
			return index();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar calendar = Calendar.getInstance();
		// 取文件后缀名
		String picfilename = sdf.format(calendar.getTime())+ picfileFileName.substring(picfileFileName.lastIndexOf('.'));
		try {
			InputStream is1 = new FileInputStream(picfile);
			// root1=
			// ServletActionContext.getRequest().getRealPath("/appIcon/newsIcon")
			// ;//存储到apache项目发布目录上
//			root1 = "C:\\project/appIcon/newsIcon";	   
            File dir = new File(root_suo);
            if (dir.exists() == false) { dir.mkdirs();}
            File dir2 = new File(root_ori);
            if (dir2.exists() == false) { dir2.mkdirs();}
			
			
			File copyFile1 = new File(root_ori, picfilename);
			OutputStream os1 = new FileOutputStream(copyFile1);
			byte[] buffer1 = new byte[400];
			int length1 = 0;
			while ((length1 = is1.read(buffer1)) > 0) {
				os1.write(buffer1, 0, length1);
			}
			is1.close();
			os1.close();

			SNewspic newspic = new SNewspic();
			newspic.setPicname(picfilename);
			newspic.setPath(picfilename);
			newspic.setSNews(newsService.findById(newsid));
			newsPicService.save(newspic);

			
			
			
//		    保存缩略图
		      try {
					Thumbnails.of( new File(root_ori + "/" + picfilename))
					.size(160, 100)
					.toFile( new File(root_suo + "/" + picfilename));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
			
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "error";
		}
		

		
		return index();
	}
	
	
	
	
	//保存新闻内容页面图片
	public void saveIcon() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar calendar = Calendar.getInstance();
		uploadFileName = sdf.format(calendar.getTime())
				+ uploadFileName.substring(uploadFileName.lastIndexOf('.'));
//		System.out.println("文件名称" + uploadFileName);
		   String root_suo= "C:\\project/appIcon/origin_newsIcon";
	       String   root_ori= "C:\\project/appIcon/newsIcon";
	
		try {
			InputStream is1 = new FileInputStream(upload);
			// root1=
			// ServletActionContext.getRequest().getRealPath("/appIcon/newsIcon")
			// ;//存储到apache项目发布目录上
			File copyFile1 = new File(root_ori, uploadFileName);
			OutputStream os1 = new FileOutputStream(copyFile1);
			byte[] buffer1 = new byte[400];
			int length1 = 0;
			while ((length1 = is1.read(buffer1)) > 0) {
				os1.write(buffer1, 0, length1);
			}
			is1.close();
			os1.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("错误上传");
		}
		try {
			String callback = ServletActionContext.getRequest().getParameter(
					"CKEditorFuncNum");
			PrintWriter out = getResponse().getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("window.parent.CKEDITOR.tools.callFunction(" + callback
					+ ",'" + "appIcon/newsIcon/" + uploadFileName + "','')");
			out.println("</script>");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
//	    保存缩略图
	      try {
				Thumbnails.of( new File(root_ori + "/" + uploadFileName))
				.size(500, 400)
				.toFile( new File(root_suo + "/" + uploadFileName));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	public SNews getApp() {
		return news;
	}

	public void setApp(SNews app) {
		this.news = app;
	}

	public AppsPageShow getPageShow() {
		return pageShow;
	}

	public void setPageShow(AppsPageShow pageShow) {
		this.pageShow = pageShow;
	}

	public SNews getNews() {
		return news;
	}

	public void setNews(SNews news) {
		this.news = news;
	}

	public List<SNews> getListnews() {
		return listnews;
	}

	public void setListnews(List<SNews> listnews) {
		this.listnews = listnews;
	}

	public File getPicfile() {
		return picfile;
	}

	public void setPicfile(File picfile) {
		this.picfile = picfile;
	}

	public String getPicfileFileName() {
		return picfileFileName;
	}

	public void setPicfileFileName(String picfileFileName) {
		this.picfileFileName = picfileFileName;
	}

}
