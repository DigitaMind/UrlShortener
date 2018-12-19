package com.interswitch.url.api.service;

import com.google.common.hash.Hashing;
import com.interswitch.url.api.model.UrlRequest;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UrlService {
    public UrlRequest sanitizeUrl(UrlRequest urlRequest){
        String longUrl = urlRequest.getUrl();
        if (longUrl.substring(0, 7).equals("http://"))
            longUrl = longUrl.substring(7);

        if (longUrl.substring(0, 8).equals("https://"))
            longUrl = longUrl.substring(8);

        if (longUrl.charAt(longUrl.length() - 1) == '/')
            longUrl = longUrl.substring(0, longUrl.length() - 1);
        urlRequest.setUrl(longUrl);
        return urlRequest;
    }

    public String generateShortUrl(UrlRequest urlRequest){
        return Hashing.murmur3_32().hashString(urlRequest.getUrl(), StandardCharsets.UTF_8).toString();
    }

    public boolean validateUrl(UrlRequest urlRequest){
        String pattern = "^(http://www\\.|https://www\\.|http://|https://)?[a-z0-9]+([\\-.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(/.*)?$";
        Pattern urlPattern = Pattern.compile(pattern);
        Matcher check = urlPattern.matcher(urlRequest.getUrl());
        return check.find();
    }



}
