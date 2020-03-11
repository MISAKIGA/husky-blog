package com.misakiga.husky.provider.tests;

import com.misakiga.husky.provider.ProviderMemberBootstrap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProviderMemberBootstrap.class)
public class UmsAllTests {

    @Test
    public void selectAllPer(){
        System.out.println("sssss");
    }
}
