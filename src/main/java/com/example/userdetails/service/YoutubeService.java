package com.example.userdetails.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.userdetails.dto.YoutubeVideoDto;

@Service	
public class YoutubeService {
	 @Value("${youtube.api.key}")
	    private String apiKey;
	 @Value("${youtube.api.url}")
	 	private String youtubeApiUrl;
	 public List<YoutubeVideoDto> fetchTopVideo(String query)
	 {
		 String uri = UriComponentsBuilder.fromHttpUrl(youtubeApiUrl)
				    .queryParam("part", "snippet")
				    .queryParam("maxResults", "3")
				    .queryParam("q", query)
				    .queryParam("type", "video")
				    .queryParam("order", "viewCount")
				    .queryParam("videoDefinition", "high")
				    .queryParam("safeSearch", "strict")
				    .queryParam("relevanceLanguage", "en")
				    .queryParam("key", apiKey)
				    .toUriString();

		 RestTemplate restTemplate = new RestTemplate();
	        String response = restTemplate.getForObject(uri, String.class);
	        List<YoutubeVideoDto> result=new ArrayList<>();
	        JSONObject json = new JSONObject(response);
	        JSONArray items = json.getJSONArray("items");

	        for (int i = 0; i < items.length(); i++) {
	            JSONObject item = items.getJSONObject(i);
	            String videoId = item.getJSONObject("id").getString("videoId");
	            JSONObject snippet = item.getJSONObject("snippet");

	            String title = snippet.getString("title");
	            String thumbnailUrl = snippet.getJSONObject("thumbnails").getJSONObject("medium").getString("url");
	            String channelTitle = snippet.getString("channelTitle");

	            result.add(new YoutubeVideoDto(title, videoId, thumbnailUrl, channelTitle));
	        }

	        return result;

	        
	 }
	 
	 

}
