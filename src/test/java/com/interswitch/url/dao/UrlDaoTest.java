package com.interswitch.url.dao;

import com.interswitch.url.api.dao.UrlDao;
import com.interswitch.url.api.model.Url;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UrlDaoTest {

    @Autowired
    UrlDao urlDao;

    @Test
    public void create(){
        Url url = new Url();
        url.setLongUrl("www.google.com");
        System.out.println(urlDao.create(url));
    }

    @Test
    public void find(){
        Url url = new Url();
        url.setShortUrl("localhost:8080/08316aaa");
        System.out.println(urlDao.find(url.getShortUrl()));
    }
}
