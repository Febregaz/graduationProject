package yuzhaoLiu.project.repository;

import org.springframework.data.neo4j.repository.GraphRepository;
import yuzhaoLiu.project.entity.Movies;

public interface MovieRepository extends GraphRepository<Movies>{
}
