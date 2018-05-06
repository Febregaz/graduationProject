/**
 * 
 */
package yuzhaoLiu.project.mybatis.entity.topic.category;

import java.util.HashSet;
import java.util.Set;

/**
 * @author phn
 * 
 */
public class Categorys {
	private int categoryId;
	private String namee;
	private int countTopicsCate = 0;
	private int countCommentsCate = 0;
	private Set<Types> categoryTypes = new HashSet<Types>();

	public int getId() {
		return categoryId;
	}

	public void setId(int id) {
		this.categoryId = id;
	}

	public String getNamee() {
		return namee;
	}

	public void setNamee(String namee) {
		this.namee = namee;
	}

	public Set<Types> getCategoryTypes() {
		return categoryTypes;
	}

	public void setCategoryTypes(Set<Types> categoryTypes) {
		this.categoryTypes = categoryTypes;
	}

	public int getCountTopics() {
		return countTopicsCate;
	}

	public void setCountTopics(int countTopicsCate) {
		this.countTopicsCate = countTopicsCate;
	}

	public int getCountComments() {
		return countCommentsCate;
	}

	public void setCountComments(int countCommentsCate) {
		this.countCommentsCate = countCommentsCate;
	}

}
