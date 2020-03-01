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
public class SimpleTest {

    @Test
    public void testcopier() {
//        final BeanCopier beanCopier = BeanCopier.create(UserParam.class, Manager.class, false);
//        UserParam userParam = new UserParam();
//        userParam.setUsername("xiyifen");
//        userParam.setPassword("1234455");
//        Manager manager = new Manager();
//        beanCopier.copy(userParam, manager, null);
//        Assert.assertEquals("xiyifen", manager.getUsername());
        // create(Class source, Class target, boolean useConverter)
        final BeanCopier beanCopier = BeanCopier.create(UserParam.class, Manager.class, false);
        UserParam userDto=new UserParam();
        userDto.setUsername("zhangsan");
        Manager user = new Manager();
        beanCopier.copy(userDto, user, null);
        Assert.assertEquals("zhangsan", userDto.getUsername());
    }


}
