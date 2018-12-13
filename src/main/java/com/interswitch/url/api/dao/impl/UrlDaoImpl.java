package com.interswitch.url.api.dao.impl;

import com.interswitch.url.api.dao.UrlDao;
import com.interswitch.url.api.model.Response;
import com.interswitch.url.api.model.Url;
import com.interswitch.url.api.service.UrlService;
import com.interswitch.url.api.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UrlDaoImpl implements UrlDao {
    @Autowired
    private  UrlService urlService;

    @Autowired
    private StringRedisTemplate template;

    @Override
    public Response create(Url url) {
        if(url.getLongUrl().length() <= 8){
            return new Response(url.getLongUrl()+" cannot be shortened.");
        }
        if(urlService.validateUrl(url)){
            url = urlService.sanitizeUrl(url);
            url = urlService.generateShortUrl(url);
            template.opsForValue().set(url.getShortUrl(), url.getLongUrl());
            return new Response(Constants.DNS+url.getShortUrl());
        }
        return new Response("Invalid Url. Try Again.");
    }

    @Override
    public Response find(String shortUrl) {
        String longUrl = template.opsForValue().get(shortUrl);
        return new Response(longUrl);
    }

    @Override
    public List<Url> findAll() {
        return null;
    }
}
