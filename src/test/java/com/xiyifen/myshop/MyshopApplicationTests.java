package com.xiyifen.myshop;

import com.xiyifen.myshop.system.dto.UserParam;
import com.xiyifen.myshop.system.entity.Manager;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MyshopApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void testcopier() {
        UserParam userParam = new UserParam();
        userParam.setUsername("xiyifen");
        userParam.setPassword("1234455");
        Manager manager = new Manager();
        BeanCopier beanCopier = BeanCopier.create(UserParam.class, Manager.class, false);
        beanCopier.copy(userParam, manager, null);
        Assert.assertEquals("xiyifen", manager.getUsername());
    }

}
