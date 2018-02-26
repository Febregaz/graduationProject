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
	private int id;
	private String namee;
	private int countTopics = 0;
	private int countComments = 0;
	private Set<Types> categoryTypes = new HashSet<Types>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		return countTopics;
	}

	public void setCountTopics(int countTopics) {
		this.countTopics = countTopics;
	}

	public int getCountComments() {
		return countComments;
	}

	public void setCountComments(int countComments) {
		this.countComments = countComments;
	}

}
