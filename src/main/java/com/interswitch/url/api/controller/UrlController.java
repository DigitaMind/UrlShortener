package com.interswitch.url.api.controller;

import com.interswitch.url.api.dao.UrlDao;
import com.interswitch.url.api.model.Response;
import com.interswitch.url.api.model.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/url")
public class UrlController {

    @Autowired
    UrlDao urlDao;


    @PostMapping
    public Response create(@RequestBody Url url){
        return urlDao.create(url);
    }

    @GetMapping("/{code}")
    public void get(@PathVariable String code, HttpServletResponse httpServletResponse) throws IOException {
        String url = urlDao.find(code).toString();
        httpServletResponse.sendRedirect(url);
    }
}
