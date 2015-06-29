package test.java;

import main.java.central.blogic.interfaces.IImageResizing;
import main.java.central.blogic.services.ImageResizing;

public class FoldersTest {

	private static final String FOLDER = "/home/bismark/tmp/image_source/";

	public static void main(String[] args) {
		System.out.println("Hello world");
		IImageResizing service = new ImageResizing();
		int count = 0;
		for (String name : service.loadFolderPathList(FOLDER)) {
			System.out.println("name: " + name);
			for (String file : service.loadFilesPathList(name)) {
				count++;
				System.out.println("    file: " + file);
				service.copyImg(file, 240, 400, "small_", name);
			}
		}
		System.out.println("Total number: " + count);
	}

}