package com.example.texteditor;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.texteditor.gui.TextEditorGUI;

@SpringBootApplication
public class TexteditorApplication {

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(TexteditorApplication.class);
		builder.headless(false);
		ConfigurableApplicationContext context = builder.run(args);

		TextEditorGUI textEditorGUI = context.getBean(TextEditorGUI.class);
		textEditorGUI.createAndShowGUI();
	}
}