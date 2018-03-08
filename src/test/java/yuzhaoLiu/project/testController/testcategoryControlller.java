package yuzhaoLiu.project.testController;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextConfiguration("classpath:spring/spring-mvc.xml")
public class testcategoryControlller {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testGoCate() throws Exception{
        /**
         * 1.get和post都能使用
         * 2.get(这里是controller的请求路径)
         * 3.param(参数传值，key-value形式)
         * 4.andExpect(status().isOk())判断请求状态是否成功，成功返回200
         * 5..andDo(print())打印信息
         */
        String result = this.mockMvc
                .perform(get("/category/goCategory")
                        .param("categoryId", "1")
                        .param("nowPage" , "1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        //可以把获取的值去在线JSON校验格式化工具查看信息
        //https://www.bejson.com/
        System.out.println("result = " + result);
    }

}
