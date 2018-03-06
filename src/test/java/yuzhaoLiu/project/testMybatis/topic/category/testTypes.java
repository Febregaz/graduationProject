package yuzhaoLiu.project.testMybatis.topic.category;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.junit.Test;
import yuzhaoLiu.project.mybatis.entity.topic.category.Types;
import yuzhaoLiu.project.mybatis.mapper.topic.category.typesMapper;
import yuzhaoLiu.project.mybatis.util.MyBatisUtil;

import java.util.List;

public class testTypes {
    private static Logger logger = Logger.getLogger(testTypes.class);

    static SqlSessionFactory sqlSessionFactory = null;
    static {
        sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
    }

    @Test
    public void readTypes(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            typesMapper typesMapper = sqlSession.getMapper(typesMapper.class);
            Types type = typesMapper.getTypeById(1);
            /*for(Types t : typesList){
                System.out.println("types:"+t.getTypesCategory().getNamee());
            }*/
            logger.info(type.getName());
            logger.info("display successfully !");
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testShortcut(){
        System.out.println("ctrl+shift+F10:it will execute the test method where the cursor is");
    }
}
