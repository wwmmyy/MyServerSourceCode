package android.mobile.mytest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dist.action.admin.BasicAction;
import com.dist.entity.PSns;
import com.dist.entity.PSnsPhoots;
import com.dist.service.PSnsPhootsServiceI;
import com.dist.service.PSnsServiceI;
import com.dist.service.UserServiceI;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 获取Android端上传过来的图片信息
 * 用户在朋友圈发表信息时可上传图片，服务器端接收处理上传的图片
 * 
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class PlanCircleUploadAction extends BasicAction{
    // 上传文件域
    private File image;
    // 上传文件类型
    private String imageContentType;
    // 封装上传文件名
    private String imageFileName;
    // 接受依赖注入的属性
    private String savePath;

    
    
    private PSnsServiceI psnsService;
    private PSnsPhootsServiceI phootService;
    
//    更改用户头像的注入
    private UserServiceI userServiceImpl;
	@Resource
	public void setUserServiceImpl(UserServiceI userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}

    
	@Resource
	public void setPsnsService(PSnsServiceI entityService) {
		this.psnsService = entityService;
	}
	
	@Resource
	public void setPsnsPhootsService(PSnsPhootsServiceI entityService) {
		this.phootService = entityService;
	}
    

	
    
	public void add() {
		HttpServletRequest request = ServletActionContext.getRequest();
		FileOutputStream fos = null;
		FileInputStream fis = null;
		JSONObject obj = new JSONObject();
		String root_ori = "C:\\project/appIcon/origin_planCircleIcon";
		String root_suo = "C:\\project/appIcon/planCircleIcon";

		String savepicname = null;

		try {
			// 保存psns
			PSns tempsns = new PSns();
			tempsns.setUserId(request.getParameter("userId"));// 后期需要判断是否拿到userid，
			tempsns.setContent(request.getParameter("content"));
			// SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			// java.util.Date da = sp.parse(sp.format(new Date()));
			tempsns.setPublishTime(new Timestamp((new java.util.Date()).getTime()));

			if (userServiceImpl.findById(tempsns.getUserId()) != null) {
				String tempsnsid = psnsService.save(tempsns);

				if (image != null) {
					// 保存pshoots
					PSnsPhoots tempshoots = new PSnsPhoots();
					tempshoots.setPSns(psnsService.findById(tempsnsid));
					tempshoots.setSize(image.length());
					savepicname = phootService.save(tempshoots);// 终端是通过保存的图片的表id获取图片的，保存图片的命名也需要时id名

					// String root= ServletActionContext.getRequest().getRealPath("/appIcon/planCircleIcon") ;//暂时存储到apache项目发布目录上后期再改
					File dir = new File(root_suo);
					if (dir.exists() == false) {
						dir.mkdirs();
					}
					File dir2 = new File(root_ori);
					if (dir2.exists() == false) {
						dir2.mkdirs();
					}

					// fos = new FileOutputStream(root + "/" + getImageFileName());
					fos = new FileOutputStream(root_ori + "/" + savepicname + ".jpg");
					fis = new FileInputStream(getImage());
					byte[] buffer = new byte[1024];
					int len = 0;
					while ((len = fis.read(buffer)) != -1) {
						fos.write(buffer, 0, len);
					}

					obj.put("state", "文件上传成功");
					super.writeJson(obj);
				}

			} else {
				obj.put("state", "文件上传失败");
				super.writeJson(obj);
			}

		} catch (Exception e) {

			obj.put("state", "文件上传失败");
			super.writeJson(obj);
			e.printStackTrace();
		} finally {
			close(fos, fis);
		}

		// ScaleImageUtils.resizeByHeight(120, "C:\\project/appIcon/splanCircleIcon", new File(root + "/" + savepicname+".jpg"));
		// 保存缩略图
		try {
			Thumbnails.of(new File(root_ori + "/" + savepicname + ".jpg")).size(300, 158).toFile(new File(root_suo + "/" + savepicname + ".jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
    
    
    
    
    //更改用户头像
    public void upUserImg() {
        HttpServletRequest request=ServletActionContext.getRequest();
        FileOutputStream fos = null;
        FileInputStream fis = null;
        JSONObject obj=new JSONObject();
        
        String root_orgin= "C:\\project/appIcon/origin_userIcon";//表示原始图位置
        String root_suo= "C:\\project/appIcon/userIcon";//表示缩略图位置
        
        
        try {
            if(image!=null){
          
            File dir = new File(root_orgin);
            if (dir.exists() == false) {
                boolean b = dir.mkdirs();
            }
            File dir2 = new File(root_suo);
            if (dir2.exists() == false) {dir2.mkdirs();
            }
//            fos = new FileOutputStream(root + "/" + getImageFileName());
            fos = new FileOutputStream(root_orgin + "/" + request.getParameter("userId")+".jpg");
            fis = new FileInputStream(getImage());
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
    		obj.put("state", true);
    		super.writeJson(obj);        	
            }
        } catch (Exception e) {
            obj.put("state", false);
            super.writeJson(obj);
            e.printStackTrace();
        } finally {
            close(fos, fis);
        }
        
//      保存缩略图
        try {
    			Thumbnails.of( new File(root_orgin + "/" + request.getParameter("userId")+".jpg"))
    			.size(120, 120)
    			.toFile( new File(root_suo + "/" + request.getParameter("userId")+".jpg"));
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    

    /**
     * 文件存放目录
     * 
     * @return
     */
    public String getSavePath() throws Exception{
        return ServletActionContext.getServletContext().getRealPath(savePath); 
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    private void close(FileOutputStream fos, FileInputStream fis) {
        if (fis != null) {
            try {
                fis.close();
                fis=null;
            } catch (IOException e) {
                System.out.println("FileInputStream关闭失败");
                e.printStackTrace();
            }
        }
        if (fos != null) {
            try {
                fos.close();
                fis=null;
            } catch (IOException e) {
                System.out.println("FileOutputStream关闭失败");
                e.printStackTrace();
            }
        }
    }

}