package converter_service;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component
public class Converter {
	@RequestMapping(value = "/convert", method = RequestMethod.POST)
	public String downloadFileAndSendPath(@RequestBody JsonData json) {
		Downloader downloader = new Downloader();
		try {
			// convert youtube link to mp3 and download the file into folder (name=chatid)
			downloader.downloadLocally(json);
		} catch (Exception e) {
			// if there is an error, we don't return a path but an error
			e.printStackTrace();
			return "error";
		}
		// send back file path
		return downloader.getMusicFilePath(json.getTelegramChatId());
	}
}
