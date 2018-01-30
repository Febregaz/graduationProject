package yuzhaoLiu.project.entity;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Movies {
    @GraphId
    private Long nodeId ;

    private String movieId ;
    private String movieName ;

    public Movies() {
    }

    public Movies(String movieId, String movieName) {
        this.movieId = movieId;
        this.movieName = movieName;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
}
