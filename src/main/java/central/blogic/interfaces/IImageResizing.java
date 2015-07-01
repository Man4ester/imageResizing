package main.java.central.blogic.interfaces;

import java.awt.image.BufferedImage;
import java.util.List;

import main.java.central.enums.Strategy;

/**
 * @author bismark
 *
 */
public interface IImageResizing {

	/*
	 * trim img
	 * width and height parameters what must be on final result
	 */
	public BufferedImage trim(BufferedImage image, int width, int height);

	/*
	 * load list of folders 1 level we don't need more than on sub directory
	 * level
	 */
	public List<String> loadFolderPathList(String root);

	/*
	 * load only files path
	 */
	public List<String> loadFilesPathList(String root);

	/*
	 * copy img with new width, height and prefix
	 */
	public void copyImg(String path, int width, int height, String prefix, String dirFolder, Strategy strategy);
	
	/*
	 * resize img
	 * without trimming
	 */
	public BufferedImage resize(BufferedImage image, int width, int height);
}