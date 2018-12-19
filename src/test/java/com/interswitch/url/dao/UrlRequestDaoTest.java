package com.interswitch.url.dao;

import com.interswitch.url.api.dao.UrlDao;
import com.interswitch.url.api.model.UrlRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UrlRequestDaoTest {

    @Autowired
    private UrlDao urlDao;

    @Test
    public void create(){
        UrlRequest urlRequest = new UrlRequest();
        urlRequest.setUrl("www.google.com");
        System.out.println(urlDao.create(urlRequest));
    }

    @Test
    public void find(){
        UrlRequest urlRequest = new UrlRequest();
        urlRequest.setUrl("localhost:8080/08316aaa");
        try {
            System.out.println(urlDao.find(urlRequest.getUrl()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
