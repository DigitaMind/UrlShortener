package com.interswitch.url.api.dao;

import com.interswitch.url.api.model.Response;
import com.interswitch.url.api.model.UrlRequest;
import com.interswitch.url.api.service.UrlService;
import com.interswitch.url.api.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UrlDao{
    @Autowired
    private UrlService urlService;



    @Autowired
    private StringRedisTemplate template;

    public Response create(UrlRequest urlRequest) {
        if(urlRequest.getUrl().length() <= 8){
            return new Response(urlRequest.getUrl()+" cannot be shortened.");
        }
        if(urlService.validateUrl(urlRequest)){
            urlRequest = urlService.sanitizeUrl(urlRequest);
            String shortUrl = urlService.generateShortUrl(urlRequest);
            template.opsForValue().set(shortUrl, urlRequest.getUrl());
            return new Response(Constants.DNS+ shortUrl);
        }
        return new Response("Invalid UrlRequest. Try Again.");
    }

    public Response find(String shortUrl) throws Exception {
        String longUrl = template.opsForValue().get(shortUrl);
        if(longUrl == null){
            throw new Exception("Invalid Short Url.");
        }
        return new Response(longUrl);
    }

}
