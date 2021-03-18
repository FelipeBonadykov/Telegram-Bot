package bot_service;

public class JsonData {
	private long telegramChatId;
	private String urlOfYouTubeVideo;

	public long getTelegramChatId() {
		return telegramChatId;
	}

	public String getUrlOfYouTubeVideo() {
		return urlOfYouTubeVideo;
	}

	public JsonData(long telegramChatId, String urlOfYouTubeVideo) {
		this.telegramChatId = telegramChatId;
		this.urlOfYouTubeVideo = urlOfYouTubeVideo;
	}
}
