package android.mobile.mytest;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.BeanUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dist.action.admin.BasicAction;
import com.dist.entity.PSns;
import com.dist.entity.PSnsComment;
import com.dist.entity.PSnsPhoots;
import com.dist.entity.PSubscriptionsource;
import com.dist.entity.PUsersubscription;
import com.dist.entity.SApplicationorganization;
import com.dist.entity.SApplicationplatform;
import com.dist.entity.SApplicationpurview;
import com.dist.entity.SApplications;
import com.dist.entity.SDevice;
import com.dist.entity.SDevicetype;
import com.dist.entity.SFeedback;
import com.dist.entity.SMobileLog;
import com.dist.entity.SNews;
import com.dist.entity.SNewsComment;
import com.dist.entity.SNewspic;
import com.dist.entity.SOrganization;
import com.dist.entity.SUserroles;
import com.dist.entity.SUsers;
import com.dist.pagentity.BasePageShow;
import com.dist.pagentity.MobileApplication;
import com.dist.pagentity.MobileNews;
import com.dist.pagentity.MobileNewsImgPath;
import com.dist.pagentity.MobilePCircle;
import com.dist.pagentity.MobilePCircleContImg;
import com.dist.pagentity.MobilePComment;
import com.dist.pagentity.MobileSubSource;
import com.dist.service.SDevicetypeServiceI;
import com.dist.service.SFeedbackServiceI;
import com.dist.service.SMobileLogServiceI;
import com.dist.service.PSnsCommentServiceI;
import com.dist.service.PSnsServiceI;
import com.dist.service.PSubscriptionsourceServiceI;
import com.dist.service.PUsersubscriptionServiceI;
import com.dist.service.SApplicationOrganServiceI;
import com.dist.service.SApplicationPlatformServiceI;
import com.dist.service.SApplicationpurviewServiceI;
import com.dist.service.SApplicationsServiceI;
import com.dist.service.SDeviceServiceI;
import com.dist.service.SNewsServiceI;
import com.dist.service.UserServiceI;
import com.dist.util.HtmlUtil;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class MobileFunctionAction extends BasicAction implements ServletRequestAware, ServletResponseAware, ModelDriven<SUsers> {

	private static final long serialVersionUID = -8395521529200407350L;
	private UserServiceI userService;
	HttpServletRequest request;

	private SUsers user;
	// 应用APP列表
	private SApplicationsServiceI applicationsService;
	private List<SApplications> listapplications;

	private String userId;

	// 新闻通知列表
	private SNewsServiceI snewsService;
	private List<SNews> listnews;

	// 规划圈及相关评论
	private PSnsServiceI snsService;
	private List<PSns> listEntity;
	private BasePageShow pageShow = new BasePageShow();

	// 设备验证
	private SDeviceServiceI deviceService;

	// 用户订阅消息
	private PUsersubscriptionServiceI PUsubScriService;

	// 所有的订阅消息类型列表
	private PSubscriptionsourceServiceI subScriSourceService;

	private PSnsCommentServiceI PSnsCommentService;
	// 应用项目下的具体应用程序服务
	private SApplicationPlatformServiceI applicationPlatformService;
	private List<SApplicationplatform> appplatformlist;

	private SApplicationOrganServiceI apporganService;

	// 应用程序与用户关联表
	private SApplicationpurviewServiceI applicationpurviewService;	
	
	private SMobileLogServiceI mobileLogService;
	
	
//	用户反馈
	private SFeedbackServiceI  feedbackService;
	
	
	private SDevicetypeServiceI   devicetypeService;
	
	
	
	

	@Override
	public SUsers getModel() {
		if (user == null)
			user = new SUsers();
		return user;
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;

	}

	// public UserServiceI getUserServiceImpl() {
	// return userService;
	// }

	
	
	
	@Resource
	public void setSubscriptionsourceService(PSubscriptionsourceServiceI subScriSourceService) {
		this.subScriSourceService = subScriSourceService;
	}
	
	@Resource
	public void setDevicetypeService(SDevicetypeServiceI devicetypeService) {
		this.devicetypeService = devicetypeService;
	}

	@Resource
	public void setFeedbackService(SFeedbackServiceI feedbackService) {
		this.feedbackService = feedbackService;
	}

	@Resource
	public void setMobileLogService(SMobileLogServiceI mobileLogService) {
		this.mobileLogService = mobileLogService;
	}

	@Resource
	public void setApplicationpurviewService(SApplicationpurviewServiceI applicationpurviewService) {
		this.applicationpurviewService = applicationpurviewService;
	}

	@Resource
	public void setApporganService(SApplicationOrganServiceI apporganService) {
		this.apporganService = apporganService;
	}

	@Resource
	public void setPUsersubscriptionService(PUsersubscriptionServiceI PUsubScriService) {
		this.PUsubScriService = PUsubScriService;
	}

	@Resource
	public void setdeviceService(SDeviceServiceI deviceServiceImpl) {
		this.deviceService = deviceServiceImpl;
	}

	@Resource
	public void setPSnsCommentService(PSnsCommentServiceI pSnsCommentService) {
		PSnsCommentService = pSnsCommentService;
	}

	@Resource
	public void setApplicationsService(SApplicationsServiceI applicationsService) {
		this.applicationsService = applicationsService;
	}

	@Resource
	public void setUserServiceImpl(UserServiceI userServiceImpl) {
		this.userService = userServiceImpl;
	}

	@Resource
	public void setSnewsService(SNewsServiceI snewsService) {
		this.snewsService = snewsService;
	}

	@Resource
	public void setPsnsService(PSnsServiceI entityService) {
		this.snsService = entityService;
	}

	@Resource
	public void setApplicationPlatformService(SApplicationPlatformServiceI applicationsService) {
		this.applicationPlatformService = applicationsService;
	}

	// // 所有用户列表
	// public void FunctionList() throws IOException{
	// this.response.setContentType("text/json;charset=utf-8");
	// this.response.setCharacterEncoding("UTF-8");
	// List<SUsers> listU = userService.findUser("from SUsers");
	// String result = JSON.toJSONString(listU);
	// // 解决乱码的问题
	// // getResponse().setContentType("text/html;charset=utf-8");
	// response.getOutputStream().write(result.toString().getBytes("utf-8"));
	// response.getOutputStream().flush();
	// response.getOutputStream().close();
	// }
	//

	// /**
	// *获取所有应用列表信息
	// */
	// public void appAllList() {
	// String deviceType = request.getParameter("deviceType").trim();// 表示设备的类型
	// listapplications = applicationsService.findAll();
	// JSONObject obj = new JSONObject();
	// List<MobileApplication> apps = new ArrayList<MobileApplication>();
	// if (listapplications != null && listapplications.size() > 0) {
	// for (SApplications t : listapplications) {
	// MobileApplication u = new MobileApplication();
	// BeanUtils.copyProperties(t, u);
	//
	// // 应用大类下针对具体操作系统的详情 应用
	// ArrayList<SApplicationplatform> appplatformlist = new ArrayList<SApplicationplatform>(t.getSApplicationplatforms());
	// if (appplatformlist.size() > 0) {
	// if (deviceType.equals("Android")) {
	// // 从列表中获取该应用大类下关于android系统的具体应用
	// for (SApplicationplatform appplatform:appplatformlist) {
	// if (appplatform.getPlatform().trim().equals("Android")||appplatform.getPlatform().trim().equals("Web")) {
	// // u.setApplicationIdentity(appplatformlist.get(i).getApplicationIdentity());
	// // u.setStatus(appplatformlist.get(i).getStatus());
	// // u.setUrl(appplatformlist.get(i).getUrl());
	// // u.setPath(appplatformlist.get(i).getPath());
	// // u.setPlatform(platform)
	//
	// BeanUtils.copyProperties(appplatform, u);
	//
	// break;
	// }
	// }
	// } else if (deviceType.equals("IOS")) {
	// // 从列表中获取该应用大类下关于IOS系统的具体应用
	// for (SApplicationplatform appplatform:appplatformlist) {
	// if (appplatform.getPlatform().trim().equals("IOS")||appplatform.getPlatform().trim().equals("Web")) {
	// // u.setApplicationIdentity(appplatformlist.get(i).getApplicationIdentity());
	// // u.setStatus(appplatformlist.get(i).getStatus());
	// // u.setUrl(appplatformlist.get(i).getUrl());
	// // u.setPath(appplatformlist.get(i).getPath());
	// BeanUtils.copyProperties(appplatform, u);
	// break;
	// }
	// }
	//
	// }
	//
	// }
	//
	// // BeanUtils.copyProperties(t, u);
	// apps.add(u);
	// }
	// }
	// obj.put("result", JSON.toJSONString(apps));
	// obj.put("state", "true");
	// super.writeJson(obj);
	// }

	
	
	
	/**
	 * 表示用户信息反馈
	 */
	public void feedBack(){
		JSONObject obj = new JSONObject();

		try {
			SFeedback fb=new SFeedback();
			fb.setContent(request.getParameter("content"));
			fb.setAppidentify(request.getParameter("appidentify"));
			fb.setUserId(userId);
			fb.setFeedTime(new Timestamp((new java.util.Date()).getTime()));
			feedbackService.save(fb);
			obj.put("state", true);
		} catch (Exception e) {
			// TODO: handle exception
			obj.put("state", false);
			e.printStackTrace();
		}

		writeJson(obj);
	}
	
	
	
	
	/**
	 * 根据用户id获取应用程序的应用列表信息
	 */
	public void appListByUserID() {

		user = userService.findById(userId);

		// 用于存放用户可访问的应用
		Set<SApplications> uapplist = new HashSet<SApplications>(0);

		List<SApplicationpurview> useridapplist = applicationpurviewService.findByProperty("userId", userId);
		// 把用户应用表SApplicationpurview中的用户应用app装到hashset里
		for (SApplicationpurview useridapp : useridapplist) {
			uapplist.add(useridapp.getSApplications());
		}

		SOrganization org=user.getSOrganization();
		while (org != null && org.getSApplicationorganizations() != null) {
			// 得到该用户所属组织下的应用程序列表
			List<SApplicationorganization> apporganlist = new ArrayList<SApplicationorganization>(org.getSApplicationorganizations());
			// 将用户所在组织下的用户应用app装到hashset里
			for (SApplicationorganization apporgan : apporganlist) {
				uapplist.add(apporgan.getSApplications());
			}
			if(apporganlist!=null){apporganlist.clear();}
			org=org.getSorganization();

		}

		// 为用户拥有的可访问的applist
		List<SApplications> applist = new ArrayList<SApplications>(uapplist);
		// 获取所有可访问应用下的可用具体应用程序
		Set<SApplicationplatform> uapplatformlist = new HashSet<SApplicationplatform>(0);
		for (SApplications app : applist) {
			uapplatformlist.addAll(app.getSApplicationplatforms());
		}
		if(applist!=null){applist.clear();}
		appplatformlist = new ArrayList<SApplicationplatform>(uapplatformlist);

		String deviceType = request.getParameter("deviceType");// 表示设备的类型

		JSONObject obj = new JSONObject();
		List<MobileApplication> apps = new ArrayList<MobileApplication>();
		if (appplatformlist != null && appplatformlist.size() > 0) {
			if (deviceType.equals("Android")) {
				// 从列表中获取该应用大类下关于android系统的具体应用
				for (SApplicationplatform appplatform : appplatformlist) {
					MobileApplication u = new MobileApplication();
					if (appplatform.getPlatform().trim().equals("Android") || appplatform.getPlatform().trim().equals("Web")) {
						if (appplatform.getSApplications() != null) {
							BeanUtils.copyProperties(appplatform.getSApplications(), u);
							BeanUtils.copyProperties(appplatform, u);
							apps.add(u);
						}

					}
				}
			} else if (deviceType.equals("IOS")) {
				// 从列表中获取该应用大类下关于IOS系统的具体应用
				for (SApplicationplatform appplatform : appplatformlist) {
					MobileApplication u = new MobileApplication();
					if (appplatform.getPlatform().trim().equals("IOS") || appplatform.getPlatform().trim().equals("Web")) {
						BeanUtils.copyProperties(appplatform.getSApplications(), u);
						BeanUtils.copyProperties(appplatform, u);
						apps.add(u);
					}
				}
			}
			if(appplatformlist!=null){appplatformlist.clear();}
			obj.put("result", JSON.toJSONString(apps));
			obj.put("state", "true");
		} else {
			obj.put("result", "");
			obj.put("state", "no info");
		}

		super.writeJson(obj);
	}

	/**
	 * 根据设备操作系统类型获取应用程序的应用列表信息
	 */
	public void appListByOSType() {
		String deviceType = request.getParameter("deviceType").trim();// 表示设备的类型
		appplatformlist = applicationPlatformService.findAll();
		JSONObject obj = new JSONObject();
		List<MobileApplication> apps = new ArrayList<MobileApplication>();
		if (appplatformlist != null && appplatformlist.size() > 0) {
			if (deviceType.equals("Android")) {
				// 从列表中获取该应用大类下关于android系统的具体应用
				for (SApplicationplatform appplatform : appplatformlist) {
					MobileApplication u = new MobileApplication();
					if (appplatform.getPlatform().trim().equals("Android") || appplatform.getPlatform().trim().equals("Web")) {
						if (appplatform.getSApplications() != null) {
							BeanUtils.copyProperties(appplatform.getSApplications(), u);
							BeanUtils.copyProperties(appplatform, u);
							apps.add(u);
						}
					}
				}
			} else if (deviceType.equals("IOS")) {
				// 从列表中获取该应用大类下关于IOS系统的具体应用
				for (SApplicationplatform appplatform : appplatformlist) {
					MobileApplication u = new MobileApplication();
					if (appplatform.getPlatform().trim().equals("IOS") || appplatform.getPlatform().trim().equals("Web")) {
						BeanUtils.copyProperties(appplatform.getSApplications(), u);
						BeanUtils.copyProperties(appplatform, u);
						apps.add(u);
					}
				}
			}
			if(appplatformlist!=null){appplatformlist.clear();}
		}
		obj.put("result", JSON.toJSONString(apps));
		obj.put("state", "true");
		super.writeJson(obj);
	}

	/**
	 * 获取所有订阅列表及用户的订阅状态 参数 userID MobileSubSource
	 */
	public void userScriSourceList() {
		JSONObject obj = new JSONObject();
		List<PSubscriptionsource> subSourceList = subScriSourceService.findAll();
		List<MobileSubSource> msubsourcelist = new ArrayList<MobileSubSource>();
		List<PUsersubscription> userScription = PUsubScriService.findByProperty("userId", userId);// 表示该用户订阅的所有消息类型list
		for (PSubscriptionsource subsource : subSourceList) {
			MobileSubSource im = new MobileSubSource();
			BeanUtils.copyProperties(subsource, im);
			im.setState(false);
			for (PUsersubscription uscrition : userScription) {
//				////System.out.println("获取到的用户订阅列表id为：：：：" + uscrition.getPSubscriptionsource().getId().trim());
				if (im.getId().trim().equals(uscrition.getPSubscriptionsource().getId().trim())) {
					im.setState(true);
				}
			}
			msubsourcelist.add(im);
		}
		if(subSourceList!=null){subSourceList.clear();}
		obj.put("result", JSON.toJSONString(msubsourcelist));
		obj.put("state", "true");
//		////System.out.println("获取到的用户订阅列表为：：：：" + obj);
		super.writeJson(obj);

	}

	/**
	 * 保存用户订阅的消息设置到服务器数据库sourceId 参数 userId subscribe： [{id:"",state:""},{id:"",state:""}]
	 */
	public void setSubscribe() {
		JSONObject obj = new JSONObject();
		String subscribe = request.getParameter("subscribe");
		////System.out.println("转化后的订阅json为：：：userId：" +userId+ "::json::" + subscribe);

		Gson gson = new Gson();
		java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<List<MobileSubSource>>() {
		}.getType();
		List<MobileSubSource> msubsourcelist = gson.fromJson(subscribe, type);
		////System.out.println("获取订阅size：：：" + msubsourcelist.size()+ "userid" + userId);
		for (MobileSubSource msource : msubsourcelist) {
			////System.out.println("获取订阅 msource.getState()：：：" + msource.getState() );
			if (msource.getState()) {// 添加订阅
				PUsersubscription tempsuscript = new PUsersubscription();
				tempsuscript.setUserId(userId);
				tempsuscript.setPSubscriptionsource(subScriSourceService.findById(msource.getId()));
				tempsuscript.setSubscribeTime(new Timestamp((new java.util.Date()).getTime()));
				PUsubScriService.save(tempsuscript);
			} else {// 取消订阅
				////System.out.println("获取取消订阅分类id：：：" + msource.getId() + "userid" + userId);

				try {
					PUsubScriService.deleteByParams(userId, msource.getId());
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

//				////System.out.println("进入取消订阅：：：userId：" + "::json::" + msource.getId());
			}
		}
		if(msubsourcelist!=null){msubsourcelist.clear();}
		obj.put("state", "true");
		super.writeJson(obj);
	}

	/**
	 * 根据用户的订阅权限显示相应的订阅通知
	 */
	public void newsListByUser() {
		JSONObject obj = new JSONObject();
		try {
			List<PUsersubscription> userScription = PUsubScriService.findByProperty("userId", userId);
//			System.out.println("获取到的userId为：：：：：：：："+userId);
			if (userScription != null && userScription.size() > 0) {
				int tempuSubSize = userScription.size();
				listnews = new ArrayList<SNews>();
				for (int i = 0; i < tempuSubSize; i++) {
					listnews.addAll(new ArrayList<SNews>(userScription.get(i).getPSubscriptionsource().getSNews()));

				}
				if (listnews != null && listnews.size() > 0) {
					// // 根据时间进行排序
					Collections.sort(listnews, new Comparator<SNews>() {
						@Override
						public int compare(SNews o1, SNews o2) {
							// TODO Auto-generated method stub
							Date d1 = o1.getTime();
							Date d2 = o2.getTime();
							if (d1 == null && d2 == null)
								return 0;
							if (d1 == null)
								return -1;
							if (d2 == null)
								return 1;
							return d2.compareTo(d1);
						}
					});
					// 将listnews装入 MobileNews 发送给移动端
					List<MobileNews> apps = new ArrayList<MobileNews>();
					for (SNews t : listnews) {
						MobileNews u = new MobileNews();
						BeanUtils.copyProperties(t, u);

						u.setCategory(t.getPSubscriptionsource().getName());
						u.setContent(HtmlUtil.getTextFromHtml(t.getContent()));

						ArrayList<SNewspic> piclist = new ArrayList<SNewspic>(t.getSNewspics());
						List<MobileNewsImgPath> imagelist = new ArrayList<MobileNewsImgPath>();
						for (SNewspic pic : piclist) {
							MobileNewsImgPath im = new MobileNewsImgPath();
							BeanUtils.copyProperties(pic, im);
							imagelist.add(im);
						}
						u.setImage(imagelist);

						ArrayList<SNewsComment> commentlist = new ArrayList<SNewsComment>(t.getSNewsComment());
						u.setComment(commentlist.size());

						apps.add(u);
					}
					obj.put("result", JSON.toJSONString(apps));
					obj.put("state", "true");
//					System.out.println("返回的参数：：：："+JSON.toJSONString(apps));
				}
			} else {
				obj.put("state", "false");// 说明没有订阅
				obj.put("result", "");
//				System.out.println("返回的参数：：：："+obj);
			}
			if (listnews != null) {
				listnews.clear();
			}
		} catch (Exception e) {
			// TODO: handle exception
//			e.printStackTrace();
			super.writeJson(obj);
		}
		//System.out.println("获取到的用户订阅信息为：：：："+obj);
		super.writeJson(obj);

	}

	/**
	 * 获取所有的终端新闻通知信息
	 */
	public void newsList() {
		int tempTotalSize = 60;// 表示一次加载到终端的总动态条数为60条
		listnews = snewsService.find(0, tempTotalSize);
		JSONObject obj = new JSONObject();
		List<MobileNews> apps = new ArrayList<MobileNews>();
		if (listnews != null && listnews.size() > 0) {
			for (SNews t : listnews) {
				MobileNews u = new MobileNews();
				BeanUtils.copyProperties(t, u);
				//u.setContent(content)
				ArrayList<SNewspic> piclist = new ArrayList<SNewspic>(t.getSNewspics());
				List<MobileNewsImgPath> imagelist = new ArrayList<MobileNewsImgPath>();
				for (SNewspic pic : piclist) {
					MobileNewsImgPath im = new MobileNewsImgPath();
					BeanUtils.copyProperties(pic, im);
					imagelist.add(im);
				}
				u.setImage(imagelist);

				ArrayList<SNewsComment> commentlist = new ArrayList<SNewsComment>(t.getSNewsComment());
				u.setComment(commentlist.size());

				apps.add(u);
			}
		}
		obj.put("result", JSON.toJSONString(apps));
		obj.put("state", "true");
		super.writeJson(obj);
	}

	/**
	 * 显示规划圈动态信息
	 */
	public void snsList() {
		int tempTotalSize = 60;// 表示一次加载到终端的总动态条数为60条
		pageShow.setRows(tempTotalSize);
		// // 获取不同类型的应用程序
		// setAppaCategoryNum();
		// ////System.out.println("获取到的pagenow为：：："+pageShow.getPageNow());
		long temptotal = 0;
		listEntity = snsService.find(pageShow.getSearchinfo(), pageShow.getPageNow(), tempTotalSize);
		// 获取选中要现实的设备状态类型的总数
		temptotal = snsService.count(pageShow.getSearchinfo());
		// 设置总页数
		pageShow.setTotal(temptotal % tempTotalSize == 0 ? temptotal / tempTotalSize : temptotal / tempTotalSize + 1);
		if (pageShow.getPageNow() <= 0) {
			pageShow.setPageNow(1);
		}
		// ////System.out.println("androidJson返回结果listEntity："+JSON.toJSONString(listEntity));

		// 将规划动态信息封装为终端需要的json
		List<MobilePCircle> pcircles = new ArrayList<MobilePCircle>();
		for (PSns sns : listEntity) {
			// ////System.out.println("androidJson返回结果user："+JSON.toJSONString(userService.findById(sns.getUserId())));

			MobilePCircle temppcirle = new MobilePCircle();
			BeanUtils.copyProperties(sns, temppcirle);
			SUsers tempu = userService.findById(sns.getUserId());
			if(tempu!=null){
			temppcirle.setUserName(tempu.getName());
			temppcirle.setReadCount(sns.getPSnsComments().size());

			ArrayList<SUserroles> temproleslist = new ArrayList<SUserroles>(tempu.getSUserroleses());
			temppcirle.setUserRole(temproleslist.size() > 0 ? temproleslist.get(0).getSRole().getName() : "普通人");

			temppcirle.setUserOrgan(tempu.getSOrganization() == null ? "" : tempu.getSOrganization().getName());
			temppcirle.setUserImage(tempu.getId());
			}
			
			ArrayList<PSnsPhoots> piclist = new ArrayList<PSnsPhoots>(sns.getPSnsPhootses());
			List<MobilePCircleContImg> imagelist = new ArrayList<MobilePCircleContImg>();
			for (PSnsPhoots pic : piclist) {
				MobilePCircleContImg im = new MobilePCircleContImg();
				// BeanUtils.copyProperties(pic, im);
				im.setPicpath(pic.getId());
				im.setPicsize(pic.getSize() + "");
				imagelist.add(im);
			}
//			piclist.clear();
			temppcirle.setConentImage(imagelist);			
			
			
			
			

			// 得到每条动态下的评论数
			ArrayList<PSnsComment> temppcommentlist = new ArrayList<PSnsComment>(sns.getPSnsComments());
			// 根据时间进行排序
			Collections.sort(temppcommentlist, new Comparator<PSnsComment>() {
				@Override
				public int compare(PSnsComment o1, PSnsComment o2) {
					// TODO Auto-generated method stub
					Date d1 = o1.getCreateTime();
					Date d2 = o2.getCreateTime();
					if (d1 == null && d2 == null)
						return 0;
					if (d1 == null)
						return -1;
					if (d2 == null)
						return 1;
					return d1.compareTo(d2);
				}
			});
			List<MobilePComment> mobilePCommentlist = new ArrayList<MobilePComment>();
			for (PSnsComment pcomment : temppcommentlist) {
				MobilePComment tempcomment = new MobilePComment();
				BeanUtils.copyProperties(pcomment, tempcomment);
				tempcomment.setPsnsId(sns.getId());
				SUsers tempsuer = userService.findById(pcomment.getUserId());
				if (tempsuer != null) {
					tempcomment.setUserName(tempsuer.getName());
					if (pcomment.getCommentId() != null) {// 说明是被评论的评论
						PSnsComment tempcomment_comment = PSnsCommentService.findById(pcomment.getCommentId());
						if (tempcomment_comment != null) {
							SUsers comment_commentsuer = userService.findById(tempcomment_comment.getUserId());
							if (comment_commentsuer != null) {
								tempcomment.setCommentIdUerName(comment_commentsuer.getName());
								tempcomment.setCommentIdUerId(comment_commentsuer.getId());
							}
						}
					}
				}
				// ArrayList<SUserroles> temproleslist0 = new ArrayList<SUserroles>(tempsuer.getSUserroleses());
				// tempcomment.setUserRole(temproleslist0.size()>0?temproleslist0.get(0).getSRole().getName():"普通人");
				mobilePCommentlist.add(tempcomment);
			}
			if(temppcommentlist!=null){temppcommentlist.clear();};

			temppcirle.setCommentList(mobilePCommentlist);
			pcircles.add(temppcirle);	
		}
//		////System.out.println("获取到的返回值结果为：：："+JSON.toJSONString(pcircles));
		JSONObject obj = new JSONObject();
		obj.put("result", JSON.toJSONString(pcircles));
		obj.put("state", "true");

		// ////System.out.println("返回的规划圈信息为：：："+obj);

		super.writeJson(obj);
	}

	/**
	 * 朋友圈添加评论
	 */
	public void addComment() {
		HttpServletRequest request = ServletActionContext.getRequest();
		PSnsComment temp = new PSnsComment();
		temp.setContent(request.getParameter("content"));
		temp.setUserId(request.getParameter("userId"));
		if (request.getParameter("commentId") != null) {
			temp.setCommentId(request.getParameter("commentId"));
		}
		temp.setPSns(snsService.findById(request.getParameter("snsId")));
		temp.setCreateTime(new Timestamp((new java.util.Date()).getTime()));
		if (temp.getUserId() != null && request.getParameter("snsId") != null) {
			temp=PSnsCommentService.saveResult(temp);
		}
		
		MobilePComment nn=new MobilePComment();		
		BeanUtils.copyProperties(temp, nn);		
		SUsers tempsuer = userService.findById(temp.getUserId());	
		nn.setUserName(tempsuer.getName());
		nn.setUserId(tempsuer.getId());
		nn.setPsnsId(temp.getPSns().getId());		
		
//		////System.out.println("获取到的返回值结果为comment：：："+ JSON.toJSONString(nn));
		JSONObject obj = new JSONObject();
		obj.put("result", JSON.toJSONString(nn));
		obj.put("state", "true");
		super.writeJson(obj);

	}

	/**
	 * 表示评价列表信息
	 */
	public void commentList() {

		String snsId = request.getParameter("snsId");
		PSns tempsns = snsService.findById(snsId);

		ArrayList<PSnsComment> temppcommentlist = new ArrayList<PSnsComment>(tempsns.getPSnsComments());

		// Map<String, Object> params =new HashMap<String, Object>();
		// params.put("PSns.id", snsId);
		// ArrayList<PSnsComment> temppcommentlist =(ArrayList<PSnsComment>) PSnsCommentService.find(params, 0, 2);

		// 根据时间进行排序
		Collections.sort(temppcommentlist, new Comparator<PSnsComment>() {
			@Override
			public int compare(PSnsComment o1, PSnsComment o2) {
				// TODO Auto-generated method stub
				Date d1 = o1.getCreateTime();
				Date d2 = o2.getCreateTime();
				if (d1 == null && d2 == null)
					return 0;
				if (d1 == null)
					return -1;
				if (d2 == null)
					return 1;
				return d1.compareTo(d2);

			}
		});
		List<MobilePComment> mobilePCommentlist = new ArrayList<MobilePComment>();
		for (PSnsComment pcomment : temppcommentlist) {
			MobilePComment tempcomment = new MobilePComment();
			BeanUtils.copyProperties(pcomment, tempcomment);
			tempcomment.setPsnsId(snsId);
			SUsers tempsuer = userService.findById(pcomment.getUserId());
			tempcomment.setUserName(tempsuer.getName());
			// ArrayList<SUserroles> temproleslist0 = new ArrayList<SUserroles>(tempsuer.getSUserroleses());
			// tempcomment.setUserRole(temproleslist0.size()>0?temproleslist0.get(0).getSRole().getName():"普通人");
			mobilePCommentlist.add(tempcomment);
		}
		JSONObject obj = new JSONObject();
		obj.put("result", JSON.toJSONString(mobilePCommentlist));
		obj.put("state", "true");
		// ////System.out.println("获取到的评价列表为：" + obj);
		super.writeJson(obj);

	}

	/**
	 * 用户点赞记录
	 */
	public void commentPraise() {
		// HttpServletRequest request=ServletActionContext.getRequest();
		PSns psns = snsService.findById(request.getParameter("snsId"));
		int tempint = 0;
		if (psns.getGood() == null) {
			psns.setGood(1);
		} else {
			psns.setGood(psns.getGood() + 1);
		}

		snsService.save(psns);

		JSONObject obj = new JSONObject();
		obj.put("result", "1");
		obj.put("state", "true");
		// ////System.out.println("获取到的评价列表为：" + obj);
		super.writeJson(obj);
	}

	/**
	 * 新闻阅读数目记录
	 */
	public void newsPraise() {
		// HttpServletRequest request=ServletActionContext.getRequest();
		SNews snews = snewsService.findById(request.getParameter("newsId"));
		if (snews.getBrowse() == null) {
			snews.setBrowse(1);
		} else {
			snews.setBrowse(snews.getBrowse() + 1);
		}
		snewsService.save(snews);

		JSONObject obj = new JSONObject();
		obj.put("result", "1");
		obj.put("state", "true");
		// ////System.out.println("获取到的评价列表为：" + obj);
		super.writeJson(obj);
	}

	
	
	
	/**
	 * 设备状态验证
	 */
	public void mobileDeviceValidate() {
		// 设备Status状态0表示待审核，1表示正常 。 2表示挂失，3表示禁用
		JSONObject obj = new JSONObject();
		// HttpServletRequest request=ServletActionContext.getRequest();
		String deviceid = request.getParameter("deviceNumber");
		List<SDevice> sdevicelist = deviceService.findByProperty("deviceNumber", deviceid);
		SDevice sdevice = null;
		if (null != sdevicelist && sdevicelist.size() > 0) {
			sdevice = sdevicelist.get(0);
			obj.put("result", sdevicelist.get(0).getStatus());//获取设备状态返回
		}else{
			obj.put("result", "-1");  
		}
		obj.put("state", true);  
		writeJson(obj);
	}
	
	
	
	
	/**
	 * 设备提交申请     remark   deviceNumber  name    system(操作系统  IOS  Android)  hardware（硬件类型 Phone   Pad）    
	 */
	public void mobileDeviceReg() {
		// 设备Status状态0表示待审核，1表示正常 。 2表示挂失，3表示禁用
		JSONObject obj = new JSONObject();
		String deviceNumber = request.getParameter("deviceNumber");
		String remark = request.getParameter("remark");
		String name = request.getParameter("name");
		String system = request.getParameter("system");
		String hardware = request.getParameter("hardware");
		
		List<SDevice> sdevicelist = deviceService.findByProperty("deviceNumber", deviceNumber);
		SDevice sdevice = null;
		if (null != sdevicelist && sdevicelist.size() > 0) {
			sdevice = sdevicelist.get(0);
			obj.put("result", sdevicelist.get(0).getStatus());//获取设备状态返回
		}else{
			sdevice=new SDevice();
			sdevice.setDeviceNumber(deviceNumber);
			sdevice.setRemark(remark);
			sdevice.setStatus("0");
			sdevice.setRegisterTime(new Timestamp((new java.util.Date()).getTime()));
			sdevice.setName(name==null?"无名称":name);
			
			List<SDevicetype>  temptype=devicetypeService.findByPropertys(system, hardware);
			if(temptype!=null&&temptype.size()>0){
				sdevice.setSDevicetype(temptype.get(0));
			}
			
			
			deviceService.save(sdevice);
			obj.put("result", "0");//获取设备状态返回
		}
		
		writeJson(obj);
	}
	
	
	
	
	
	
	
	
//	ActionContext usersession = ActionContext.getContext();

	
	/**
	 * 用户登录验证
	 */
	public void mobileLogin() {
		// 设备Status状态0表示待审核，1表示正常 。 2表示挂失，3表示禁用
		JSONObject obj = new JSONObject();
		// HttpServletRequest request=ServletActionContext.getRequest();
		String deviceid = request.getParameter("deviceNumber");
		List<SDevice> sdevicelist = deviceService.findByProperty("deviceNumber", deviceid);
		SDevice sdevice = null;
		if (null != sdevicelist && sdevicelist.size() > 0) {
			sdevice = sdevicelist.get(0);
		}
		if (sdevice == null) {// 说明是新增设备
//			sdevice = new SDevice();
//			sdevice.setRegisterTime(new Timestamp((new java.util.Date()).getTime()));
//			sdevice.setRemark("新增终端用户设备");
//			sdevice.setStatus("0");
//			sdevice.setDeviceNumber(deviceid);
//			sdevice.setName(request.getParameter("deviceName"));
//			deviceService.save(sdevice);
			obj.put("result", "-1");
		} else {// 说明该设备已经存在
			if (sdevice.getStatus().equals("2") || sdevice.getStatus().equals("3")) {// 说明设备不正常
				obj.put("result", sdevice.getStatus());
			} else {// 说明设备正常，用户可以登录
				obj.put("result", sdevice.getStatus());
				if (request.getParameter("handPassword") == null) {// 终端用户可以通过数字密码或手势密码登录，当前为数字密码登录。
					SUsers u = new SUsers();
					String loginName = request.getParameter("loginName");
					String password = request.getParameter("password");
					u.setLoginName(loginName.trim());
					u.setPassword(password.trim());
					// ////System.out.println("获取到的登录名为：" + loginName+" 密码为："+password);
					u = userService.login(u);
					if (u != null) {// 说明用户登录成功
						obj.put("state", true);
						obj.put("userId", u.getId());
//						  ////System.out.println("获取到的登录用户的id为：" + u.getId());
						obj.put("userName", u.getName());
						ArrayList<SUserroles> temprolelist = new ArrayList<SUserroles>(u.getSUserroleses());
					 	if (temprolelist != null && temprolelist.size() > 0) {
							String temprole = "";
							for (SUserroles sr : temprolelist) {
								temprole += " " + sr.getSRole().getName();
							}
							obj.put("userRole", temprole);
						}
						if (u.getSOrganization() != null) {
							obj.put("userOrganzation", u.getSOrganization().getName());
						}
						
//						将终端登录信息保存到数据库
						savemobileLog(u.getId(),request.getParameter("appidentify"), request.getParameter("action") ,deviceid,true);		
		
						
						
//						usersession.getSession().put("userid", u.getId());
//						System.err.println("保存了用户session"+usersession.getSession().get("userid"));
						
					} else {
						obj.put("state", false);// 说明用户登录失败
					}
				} else {// 手势密码登录验证，若验证手势密码需要用户设置手势密码时将其传到服务器保存，暂时没上传，后期安全性升级时再进行处理

				}
			}
		}
		writeJson(obj);
	}

	
	

	
	
	/**
	 * 记录移动端用户登录及在线的日志
	 * 传的参数有 state 、appidentify、action、deviceNumber  userId
	 */
	public void mobileLog() {	
		JSONObject obj = new JSONObject();
		try {
			String deviceNumber = request.getParameter("deviceNumber");// 表示设备编号
			String appidentify = request.getParameter("appidentify");// 表示应用id
			String action = request.getParameter("action");// 表示设备的类型 login   exit
			boolean state =false;
			if( request.getParameter("state")!=null){
				 state =Boolean.parseBoolean( request.getParameter("state"));// 表示设备是否在线				
			}
			
			savemobileLog(userId,appidentify, action, deviceNumber,state);	
			obj.put("state", true); 

		} catch (Exception e) {
			// TODO: handle exception 
			obj.put("state",false );
			e.printStackTrace();
		}		
		
		writeJson(obj);
		
      } 
	
	
	
	/**
	 * 保存登录信息到数据库
	 * @param userId
	 * @param appidentify
	 * @param action
	 * @param deviceNumber
	 */
	public void savemobileLog(String userId,String appidentify,String action,String deviceNumber ,boolean state) {	

		SMobileLog templog=new SMobileLog();
		templog.setLogtime(new Timestamp((new java.util.Date()).getTime()));
		templog.setAction(action);
		templog.setDeviceNumber(deviceNumber);		
		templog.setUserid(userId==null?"visit":userId);			
		
//		 List<SApplicationplatform>  applist=applicationPlatformService.findByProperty("applicationIdentity", appidentify);
//		if(applist!=null&&applist.size()>0){			
//			templog.setAppid(applist.get(0).getSApplications().getId());			
//		}
		templog.setAppidentify(appidentify);		
//		if(action.equals("login")){
//			templog.setOnline(true);
//		}else{
//			templog.setOnline(false);
//		}
		templog.setOnline(state);
		String templogid=mobileLogService.save(templog);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

	public BasePageShow getPageShow() {
		return pageShow;
	}

	public void setPageShow(BasePageShow pageShow) {
		this.pageShow = pageShow;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
