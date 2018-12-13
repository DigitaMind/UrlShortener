package com.interswitch.url.api.dao;

import com.interswitch.url.api.model.Response;
import com.interswitch.url.api.model.Url;

import java.util.List;

public interface UrlDao{
    public Response create(Url url);
    public Response find(String key);
    public List<Url> findAll();
}
