package yuzhaoLiu.project.entity;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Actor {

    @GraphId
    private Long nodeId;

    private String id;
    private String name;

    private Movies movies ;

    public Actor() {
    }

    public Actor(String id, String name, Movies movies) {
        this.id = id;
        this.name = name;
        this.movies = movies;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}