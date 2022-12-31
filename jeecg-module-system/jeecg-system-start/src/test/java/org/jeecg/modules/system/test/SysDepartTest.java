package org.jeecg.modules.system.test;

import org.jeecg.JeecgSystemApplication;
import org.jeecg.modules.system.entity.SysDepart;
import org.jeecg.modules.system.mapper.SysDepartMapper;
import org.jeecg.modules.system.service.ISysDepartService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = JeecgSystemApplication.class)
public class SysDepartTest {

    @Autowired
    private SysDepartMapper sysDepartMapper;

    @Autowired
    private ISysDepartService sysDepartService;

    @Test
    public void getParentDepartId() {
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        SysDepart sysDepart = sysDepartMapper.getParentDepartId("1608026128133992449");
        System.out.println(sysDepart);
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    }

    @Test
    public void queryParentDepartsByDepartIds() {
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        List<String> ids = new ArrayList<String>();
        ids.add("1608025448539303938");
        ids.add("1608025897954783233");
        ids.add("1608026059779420162");
        ids.add("1608026128133992449");
        ids.add("1608027319215665153");
        ids.add("1608050103648055297");
        ids.add("1608778285954203649");
        ids.add("1608778310813843458");
        ids.add("1608778330883588097");
        ids.add("1608778353096622082");
        Map<String, SysDepart> map = sysDepartService.queryParentDepartsByDepartIds(ids);
        System.out.println(map);
        System.out.println(
            "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }
}
