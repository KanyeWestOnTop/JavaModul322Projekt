package ch.iso.m322.view;

import java.io.IOException;

public class JTrainingGUI {

	public static void main(String[] args) throws IOException {
		final MyFrame myFrame = new MyFrame();
		myFrame.setDefaultCloseOperation(MyFrame.EXIT_ON_CLOSE);
		
		myFrame.setSize(1000, 600);
		myFrame.setLocation(10, 10);
		myFrame.setVisible(true);
		
	}

}
