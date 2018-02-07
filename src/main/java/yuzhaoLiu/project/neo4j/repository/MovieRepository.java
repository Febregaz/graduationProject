package yuzhaoLiu.project.neo4j.repository;

import org.springframework.data.neo4j.repository.GraphRepository;
import yuzhaoLiu.project.neo4j.entity.Movies;

public interface MovieRepository extends GraphRepository<Movies>{
}
