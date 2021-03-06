/**
 * 
 */
package yuzhaoLiu.project.mybatis.entity.topic.content;

import yuzhaoLiu.project.mybatis.entity.people.Users;

import java.util.Date;

/**
 * @author phn
 * 
 */
public class Comments {
	private int commentId;
	private String content;
	private int floor;
	private Date commentTime;
	private int integral;
	private int commentStatus;  //评论的状态，是否被删除，0表示没有，1表示被删除
	// 多对一
	private Topics commentsTopic;
	private Users commentsUser;

	public int getId() {
		return commentId;
	}

	public void setId(int id) {
		this.commentId = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public int getIntegral() {
		return integral;
	}

	public void setIntegral(int integral) {
		this.integral = integral;
	}

	public int getStatus() {
		return commentStatus;
	}

	public void setStatus(int commentStatus) {
		this.commentStatus = commentStatus;
	}

	public Date getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}

	public Topics getCommentsTopic() {
		return commentsTopic;
	}

	public void setCommentsTopic(Topics commentsTopic) {
		this.commentsTopic = commentsTopic;
	}

	public Users getCommentsUser() {
		return commentsUser;
	}

	public void setCommentsUser(Users commentsUser) {
		this.commentsUser = commentsUser;
	}

}
