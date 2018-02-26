package yuzhaoLiu.project.testNeo4j;

import org.apache.log4j.Logger;
import yuzhaoLiu.project.neo4j.entity.Actor;
import yuzhaoLiu.project.neo4j.entity.Movies;
import yuzhaoLiu.project.neo4j.neo4jConfiguration.neoConfig;
import yuzhaoLiu.project.neo4j.repository.ActorRepository;
import yuzhaoLiu.project.neo4j.repository.MovieRepository;
import yuzhaoLiu.project.neo4j.service.Neo4jDBCleaner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {neoConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class testNeo4j {

    @Autowired
    ActorRepository actorRepository;//各个数据自身调用neo4jCRUD操作的接口

    @Autowired
    MovieRepository movieRepository;//各个数据自身调用neo4jCRUD操作的接口

    @Autowired
    Neo4jDBCleaner cleaner;

    private static Logger logger = Logger.getLogger(testNeo4j.class);

    /*
    Operation : ruin
    */
    @Test
    public void clearDatabase(){
        cleaner.cleanDb();
        System.out.println("数据自毁");
    }

    /*
    Operation : Create
    */
    @Test
    public void createAData() {
        Movies movies = new Movies("1" , "头文字D") ;
        Actor actor = new Actor("1", "周杰伦", movies);
        Actor actor1 = new Actor("2", "陈冠希", movies);
        actorRepository.save(actor);
        actorRepository.save(actor1);
        logger.info("create a data successfully !");
    }

    /*
    Operation : Delete
    */
    @Test
    public void deleteAllOfActor(){
        actorRepository.deleteAll();
        logger.info("all actor's data delete successfully !");
    }
    @Test
    public void deleteAllOfMovies(){
        movieRepository.deleteAll();
        logger.info("all movie's data delete successfully !");
    }

    /*
    Operation : Update
    */
    @Test
    public void updateAData(){
        Actor actor = actorRepository.findOne(8842L);
        actor.setName("刘宇钊");
        actorRepository.save(actor);
        logger.info("update a data successfully !");
    }

    /*
    Operation : Read
    */
    @Test
    public void readAllDate(){
        Iterable<Actor> actorList = actorRepository.findAll();
        for( Actor actor : actorList){
            System.out.println("name:"+actor.getName());
        }
        logger.info("display successfully !");
    }

}
