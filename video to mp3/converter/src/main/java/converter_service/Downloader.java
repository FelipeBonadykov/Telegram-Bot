package converter_service;

import java.io.File;

public class Downloader {
	private File fileToSendAndRemove = null;

 	public void downloadLocally(JsonData json) throws Exception {
 		ProcessBuilder pb = new ProcessBuilder("youtube-dl",  "-x", 
				"--output", "music/"+ json.getTelegramChatId() +"/%(title)s.mp3",
				json.getUrlOfYouTubeVideo());
		Process process = pb.start();
		process.waitFor();
	}

 	//rethink the logic
	public String getMusicFilePath(long folderName) {
		File folder = new File("music/" + folderName);
		fileToSendAndRemove = folder.listFiles()[0];
		return fileToSendAndRemove.getAbsolutePath();
	}

}
