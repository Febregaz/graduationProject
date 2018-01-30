package yuzhaoLiu.project.testNeo4j;

import yuzhaoLiu.project.entity.Actor;
import yuzhaoLiu.project.entity.Movies;
import yuzhaoLiu.project.repository.ActorRepository;
import yuzhaoLiu.project.repository.MovieRepository;
import yuzhaoLiu.project.service.neo4J.Neo4jDBCleaner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceContext.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class Neo4jTest {

    @Autowired
    ActorRepository actorRepository;//各个数据自身调用neo4jCRUD操作的接口

    @Autowired
    MovieRepository movieRepository;//各个数据自身调用neo4jCRUD操作的接口

    @Autowired
    Neo4jDBCleaner cleaner;

    @Test
    public void clearDatabase(){
        cleaner.cleanDb();
        System.out.println("清空数据库");
    }

    @Test
    public void createAData() {
        Movies movies = new Movies("1" , "头文字D") ;
        Actor actor = new Actor("1", "周杰伦", movies);
        actorRepository.save(actor);
        System.out.println("保存一条记录成功");
    }

}
