package bot_service;

import java.io.File;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class BotLogic extends TelegramLongPollingBot {
	private File fileToSend;

	// What happens when a message is sent to bot
	public void onUpdateReceived(Update update) {
		// We check if the update has a message and the message has text
		if (update.hasMessage() && update.getMessage().hasText()) {
			Long chatId = update.getMessage().getChatId();
			String youtubeUrl = update.getMessage().getText();
			try {
				// let user know what's going on
				execute(new SendMessage(chatId, "Processing your request... It may take some time"));
				getMusicFromYouTube(new JsonData(chatId, youtubeUrl));
				// let user know what's going on
				execute(new SendMessage(chatId, "Sending... It may take a while"));
				// Sending our message to user
				SendAudio music = new SendAudio().setChatId(chatId).setAudio(fileToSend);
				execute(music);
				fileToSend.delete();
			} catch (Exception e) {
				try {
					execute(new SendMessage(chatId, "You may have entered a wrong link. Please, try again"));
				} catch (TelegramApiException e1) {
				}
				e.printStackTrace();
			}
		}
	}

	private void getMusicFromYouTube(JsonData jsonData) throws Exception {
		// 1. send to server URL of video and chatid (a folder of song) as json
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<?> entity = new HttpEntity<Object>(jsonData, headers);
		// 2. Get answer from server
		ResponseEntity<String> responseEntity = restTemplate.exchange("http://" + "converter:8080" + "/convert",
				HttpMethod.POST, entity, String.class);
		// 3. Get path to file from server
		String pathToFile = responseEntity.getBody();

		if (pathToFile.equals("error"))
			throw new Exception("Incorrent youtube link");
		else {
			fileToSend = new File(pathToFile);
		}
	}

	// Telegram configuration
	public String getBotUsername() {
		return "";// your username
	}

	@Override
	public String getBotToken() {
		return "";// your tocken
	}

}
