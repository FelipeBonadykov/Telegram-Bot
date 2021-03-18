package bot_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@SpringBootApplication
public class TelegramApplication {
	public static void main(String[] args) {
		SpringApplication.run(TelegramApplication.class, args);
		// Initialize Api Context
		ApiContextInitializer.init();
		// Instantiate Telegram Bots API
		TelegramBotsApi botsApi = new TelegramBotsApi();
		// Register our bot
		try {
			botsApi.registerBot(new BotLogic());
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
}
