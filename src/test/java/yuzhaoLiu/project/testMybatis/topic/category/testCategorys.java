package yuzhaoLiu.project.testMybatis.topic.category;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMapping;
import yuzhaoLiu.project.controller.mybatis.category.categoryUtil.getCategoryMapper;
import yuzhaoLiu.project.mybatis.entity.topic.category.Categorys;
import yuzhaoLiu.project.mybatis.mapper.topic.category.categorysMapper;
import yuzhaoLiu.project.mybatis.util.MyBatisUtil;

import java.util.List;

public class testCategorys {
    private static Logger logger = Logger.getLogger(testCategorys.class);

    static SqlSessionFactory sqlSessionFactory = null;
    static {
        sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
    }

    @Test
    public void readCategorys(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            categorysMapper categorysMapper = sqlSession.getMapper(categorysMapper.class);
            List<Categorys> categorysList = categorysMapper.readCategorys();
            for(Categorys c : categorysList){
                System.out.println("category:"+c.getId());
            }
            logger.info("display successfully !");
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void updateCategoryComments(){
        Categorys category = getCategoryMapper.getTheCategorysMapper().getCategoryById(1);
        logger.info(category.getCountComments());
        category.setCountComments(category.getCountComments()+1);
        logger.info(category.getCountComments());
        getCategoryMapper.getTheCategorysMapper().updateCommentCount(category);
        getCategoryMapper.sqlCommit();
    }
}
