package cn.wangjiannan.test.base;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml", "classpath:applicationContext-springMVC.xml" })
// @WebAppConfiguration("src/main/resources") : 注解在类上,用来声明加载的ApplicationContex 是一个WebApplicationContext
// ,它的属性指定的是Web资源的位置,默认为 src/main/webapp ,自定义修改为 resource
@WebAppConfiguration
public abstract class BaseWebTest {
	protected Logger logger = LoggerFactory.getLogger(getClass());

}
