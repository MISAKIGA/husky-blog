package com.misakiga.husky.business;

import com.misakiga.husky.business.dto.params.ProfileParam;
import com.misakiga.husky.commons.utils.MapperUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class tests {

    @Test
    public void testProfileParam() throws Exception {
        System.out.println(MapperUtils.obj2json(new ProfileParam()));
    }
}
