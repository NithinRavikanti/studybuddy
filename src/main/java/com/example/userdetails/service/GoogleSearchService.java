package com.example.userdetails.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class GoogleSearchService {
	
	@Value("${google.api.key}")
	private String apiKey;
	
	@Value("${google.search.engine.id}")
	private String serachId;
	
	public List<Map<String,String>> serachWeb(String query)
	{
		List<Map<String,String>> result=new ArrayList<>();
		String url = UriComponentsBuilder.fromHttpUrl("https://www.googleapis.com/customsearch/v1")
                .queryParam("key", apiKey)
                .queryParam("cx", serachId)
                .queryParam("q", query)
                .queryParam("num", "10")
                .toUriString();
		RestTemplate restTemplate=new RestTemplate();
		String json=restTemplate.getForObject(url,String.class);
		JSONObject root=new JSONObject(json);
		JSONArray items=root.optJSONArray("items");
		if (items == null) return result;
		for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.getJSONObject(i);
            Map<String, String> results= new HashMap<>();
            results.put("title", item.optString("title"));
            results.put("link", item.optString("link"));
            results.put("snippet", item.optString("snippet"));
            result.add(results);
        }

        return result;
		
	}

}
