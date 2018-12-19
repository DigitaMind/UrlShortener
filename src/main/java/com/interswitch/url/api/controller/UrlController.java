package com.interswitch.url.api.controller;

import com.interswitch.url.api.dao.UrlDao;
import com.interswitch.url.api.model.Response;
import com.interswitch.url.api.model.UrlRequest;
import com.interswitch.url.api.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/url")
public class UrlController {

    @Autowired
    private UrlDao urlDao;


    @PostMapping
    public Response create(@RequestBody UrlRequest urlRequest){
        return urlDao.create(urlRequest);
    }

    @GetMapping("/{code}")
    public RedirectView get(@PathVariable String code, HttpServletResponse httpServletResponse) throws Exception {
        RedirectView redirectView = new RedirectView();
        String url = urlDao.find(code).toString();
        redirectView.setUrl("https://"+url);
        return redirectView;
    }
}
