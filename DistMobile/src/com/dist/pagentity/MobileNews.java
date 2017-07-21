package com.dist.pagentity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.dist.entity.SNewspic;
/**
 * 表示最新新闻,通知的类
* @类名: newsMessage 
* @描述: TODO 
* @作者: 王明远 
* @日期: 2015-3-26 上午10:55:24 
* @修改人: 
 * @修改时间: 
 * @修改内容:
 * @版本: V1.0
 * @版权:Copyright ©  All rights reserved.
 */
public class MobileNews {
    //    [{"title":"市领导到城市规划展览馆调研",
    //    "type":"news",
    //    "time":"2014-10-17 08:50",
    //    "content":"展馆的发展中要突出地域特色，要精心选择图片。文字介绍要详细准确，讲解人员要进一步提升语言表述能力及互动能力，讲述要真实生动。。。",
    //    "image":[{"path":""http://192.168.1.71/iprotal/img/newpic1.jpg""}],
    //    "infoUrl":"",
    //    "browse":"18",
    //    "comment":"7"},
    //    {"title":"市领导到城市规划展览馆调研",
    //    "type":"news",
    //    "time":"2014-10-17 08:50",
    //    "content":"展馆的发展中要突出地域特色，要精心选择图片。文字介绍要详细准确，讲解人员要进一步提升语言表述能力及互动能力，讲述要真实生动。。。",
    //    "image":[{"path":""http://192.168.1.71/iprotal/img/newpic1.jpg""}],
    //    "infoUrl":"",
    //    "browse":"18",
    //    "comment":"7"},
    //  {"title":"市领导到城市规划展览馆调研",
    //    "type":"news",
    //    "time":"2014-10-17 08:50",
    //    "content":"展馆的发展中要突出地域特色，要精心选择图片。文字介绍要详细准确，讲解人员要进一步提升语言表述能力及互动能力，讲述要真实生动。。。",
    //    "image":[{"path":""http://192.168.1.71/iprotal/img/newpic1.jpg""}],
    //    "infoUrl":"",
    //    "browse":"18",
    //    "comment":"7"}]
	private String id;
    private String title;
    private String type;
    private Date time;
    private String content;
    private   List<MobileNewsImgPath>  image;
    private String infoUrl;
    private Integer browse;
    private Integer comment;
    private String  category;//所属的类别
    
    
    

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    

    
    
//    public Set<imagePath> getImage() {
//		return image;
//	}
//
//	public void setImage(Set<imagePath> image) {
//		this.image = image;
//	}

	public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<MobileNewsImgPath> getImage() {
        return image;
    }

    public void setImage(List<MobileNewsImgPath> image) {
        this.image = image;
    }


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getInfoUrl() {
		return infoUrl;
	}

	public void setInfoUrl(String infoUrl) {
		this.infoUrl = infoUrl;
	}

	public Integer getBrowse() {
		return browse;
	}

	public void setBrowse(Integer browse) {
		this.browse = browse;
	}

	public Integer getComment() {
		return comment;
	}

	public void setComment(Integer comment) {
		this.comment = comment;
	}

	

}
