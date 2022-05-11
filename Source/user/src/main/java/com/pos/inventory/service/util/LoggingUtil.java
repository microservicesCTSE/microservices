package com.pos.inventory.service.util;

import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Slf4j
@Component
public class LoggingUtil {

	public static final String PATH_DELIMITER = "/";

	public void log(Object entity, String logPath, boolean isRequest, String fileName) {
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String formattedDate = dateFormat.format(date);

		dateFormat = new SimpleDateFormat("HH");
		String formattedHour = dateFormat.format(date);
		int formatHour = Integer.parseInt(formattedHour);

		dateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
		String fullDateFormat = dateFormat.format(date);

		String hourRange = null;
		if (formatHour == 23) {
			hourRange = formatHour + "-" + 00;
		} else {
			hourRange = formatHour + "-" + (formatHour + 1);
		}
		String folderPath = logPath + PATH_DELIMITER + formattedDate + PATH_DELIMITER + hourRange;
		createFolders(folderPath);
		if (isRequest) {
			createFile(folderPath + PATH_DELIMITER + fullDateFormat + fileName + "_REQ.txt", entity);
		} else {
			createFile(folderPath + PATH_DELIMITER + fullDateFormat + fileName + "_RES.txt", entity);
		}

	}

	private void createFile(String filePath, Object entity) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), entity);

			log.info("The Object  was succesfully written to a file");

		} catch (Exception ex) {
			log.error("Exception occurs : ", ex);
		}
	}

	private void createFolders(String logPath) {
		File newFolder = new File(logPath);
		newFolder.mkdirs();
	}
}
