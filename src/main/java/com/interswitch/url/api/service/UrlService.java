package com.interswitch.url.api.service;

import com.google.common.hash.Hashing;
import com.interswitch.url.api.model.Url;
import com.interswitch.url.api.utils.Constants;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UrlService {
    public static Url sanitizeUrl(Url url){
        String longUrl = url.getLongUrl();
        if (longUrl.substring(0, 7).equals("http://"))
            longUrl = longUrl.substring(7);

        if (longUrl.substring(0, 8).equals("https://"))
            longUrl = longUrl.substring(8);

        if (longUrl.charAt(longUrl.length() - 1) == '/')
            longUrl = longUrl.substring(0, longUrl.length() - 1);
        url.setLongUrl(longUrl);
        return url;
    }

    public static Url generateShortUrl(Url url){
        String shortUrl = Hashing.murmur3_32().hashString(url.getLongUrl(), StandardCharsets.UTF_8).toString();
        url.setShortUrl(shortUrl);
        return url;
    }

    public static boolean validateUrl(Url url){
        String pattern = "^(http:\\/\\/www\\.|https:\\/\\/www\\.|http:\\/\\/|https:\\/\\/)?[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$";
        Pattern urlPattern = Pattern.compile(pattern);
        Matcher check = urlPattern.matcher(url.getLongUrl());
        return check.find();
    }



}
