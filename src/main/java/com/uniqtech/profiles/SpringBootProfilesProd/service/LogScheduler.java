package com.uniqtech.profiles.SpringBootProfilesProd.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Component
public class LogScheduler {

	@Value("${logging.file.path}")
	private String logFilePath;

	@Value("${temp.folder.path}")
	private String tempFolderPath;

	@Scheduled(cron = "0 */5 * * * ?") // 5 Minutes
	public void moveGzFilesToLocalTemp() {
		File logDirectory = new File(logFilePath);
		File tempDirectory = new File(tempFolderPath);

		if (!tempDirectory.exists()) {
			tempDirectory.mkdirs(); // Create temp directory if it doesn't exist
		}

		if (logDirectory.exists() && logDirectory.isDirectory()) {
			// Filter only .gz files in the directory
			File[] gzFiles = logDirectory.listFiles((dir, name) -> name.endsWith(".gz"));

			if (gzFiles != null && gzFiles.length > 0) {
				for (File file : gzFiles) {
					Path sourcePath = file.toPath();
					Path destinationPath = new File(tempDirectory, file.getName()).toPath();
					try {
						// Move the file to the temporary folder
						Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
						System.out.println("Moved to temp folder: " + destinationPath);

						// Optionally delete the file from the original location (already moved)
						if (file.delete()) {
							System.out.println("Deleted original file: " + sourcePath);
						} else {
							System.err.println("Failed to delete original file: " + sourcePath);
						}
					} catch (IOException e) {
						System.err.println("Failed to move file: " + sourcePath);
						e.printStackTrace();
					}
				}
			} else {
				System.out.println("No .gz files found in the directory: " + logFilePath);
			}
		} else {
			System.out.println("Directory does not exist: " + logFilePath);
		}
	}
}
