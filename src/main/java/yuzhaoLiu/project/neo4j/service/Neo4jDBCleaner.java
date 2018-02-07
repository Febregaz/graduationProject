package yuzhaoLiu.project.neo4j.service;

import org.neo4j.ogm.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhengchaojie on 2017/1/13/0013.
 */
@Service
public class Neo4jDBCleaner {
    @Autowired
    Session session;

    public void cleanDb() {
        session.purgeDatabase();
    }
}
