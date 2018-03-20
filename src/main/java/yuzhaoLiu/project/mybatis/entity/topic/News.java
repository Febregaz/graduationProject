/**
 * 
 */
package yuzhaoLiu.project.mybatis.entity.topic;

import yuzhaoLiu.project.mybatis.entity.people.Users;
import yuzhaoLiu.project.mybatis.entity.topic.content.Topics;

import java.util.Date;

/**
 * @author phn
 * 
 */
public class News {
	private int newId;
	private Date newTime;
	private int status; // 0表示未读，1表示已读
	// 多对一
	// private Users topusers;
	private Users newsCommentUser;
	private Topics newsTopic;

	public int getId() {
		return newId;
	}

	public void setId(int id) {
		this.newId = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getNewTime() {
		return newTime;
	}

	public void setNewTime(Date newTime) {
		this.newTime = newTime;
	}

	public Users getNewsCommentUser() {
		return newsCommentUser;
	}

	public void setNewsCommentUser(Users newsCommentUser) {
		this.newsCommentUser = newsCommentUser;
	}

	public Topics getNewsTopic() {
		return newsTopic;
	}

	public void setNewsTopic(Topics newsTopic) {
		this.newsTopic = newsTopic;
	}

}
