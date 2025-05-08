package com.example.userdetails.dto;

public class YoutubeVideoDto {
	private String title;
    private String videoId;
    private String videoUrl;
    private String thumbnailUrl;
    private String channelTitle;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getVideoId() {
		return videoId;
	}
	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	public String getThumbnailUrl() {
		return thumbnailUrl;
	}
	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}
	public String getChannelTitle() {
		return channelTitle;
	}
	public void setChannelTitle(String channelTitle) {
		this.channelTitle = channelTitle;
	}
	public YoutubeVideoDto(String title, String videoId, String thumbnailUrl, String channelTitle) {
		super();
		this.title = title;
		this.videoId = videoId;
		this.videoUrl = "https://www.youtube.com/watch?v=" + videoId;
		this.thumbnailUrl = thumbnailUrl;
		this.channelTitle = channelTitle;
	}
	
}
