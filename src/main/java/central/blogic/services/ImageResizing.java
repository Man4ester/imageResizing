package main.java.central.blogic.services;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;

import com.sun.prism.Image;

import main.java.central.blogic.interfaces.IImageResizing;

public class ImageResizing implements IImageResizing {

	private static final String JPG = "jpg";

	@Override
	public BufferedImage resize(BufferedImage image, int width, int height) {
		int w, h;
		if (image.getWidth() == width && image.getHeight() == height) {
			w = image.getWidth();
			h = image.getHeight();
		} else {
			float dx = ((float) width) / image.getWidth();
			float dy = ((float) height) / image.getHeight();
			if (dx == dy) {
				w = width;
				h = (int) (dx * image.getHeight());
			} else {
				w = (int) (dy * image.getWidth());
				h = height;
			}
		}
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2D = null;
		try {
			graphics2D = bufferedImage.createGraphics();
			graphics2D.fillRect(0, 0, width, height);
			graphics2D.drawImage(image.getScaledInstance(w, h, BufferedImage.SCALE_SMOOTH), 0, 0, null);
		} finally {
			if (graphics2D != null) {
				graphics2D.dispose();
			}
		}
		return bufferedImage;
	}

	@Override
	public List<String> loadFolderPathList(String root) {
		List<String> lst = new ArrayList<>();
		if (StringUtils.isNotBlank(root)) {
			if (root.endsWith(File.separator)) {
				root = root.substring(0, root.length() - 1);
			}
			File folder = new File(root);
			for (String dir : folder.list()) {
				if ((new File(root + File.separator + dir)).isDirectory()) {
					lst.add(root + File.separator + dir);
				}
			}
		}
		return lst;
	}

	@Override
	public List<String> loadFilesPathList(String root) {
		List<String> lst = new ArrayList<>();
		if (StringUtils.isNotBlank(root)) {
			if (root.endsWith(File.separator)) {
				root = root.substring(0, root.length() - 1);
			}
			File dir = new File(root);
			if (!dir.exists()) {
				return lst;
			}
			for (String name : dir.list()) {
				if ((new File(root + File.separator + name)).isFile()) {
					lst.add(root + File.separator + name);
				}
			}
		}
		return lst;
	}

	@Override
	public void copyImg(String path, int width, int height, String prefix, String dirFolder) {
		if (StringUtils.isBlank(path)) {
			return;
		}
		File file = new File(path);
		if (file == null || !file.exists()) {
			return;
		}
		try {
			BufferedImage img = ImageIO.read(file);
			if (img == null) {
				return;
			}
			if (width == 0 || height == 0) {
				return;
			}
			BufferedImage imgDest = resize(img, width, height);
			ImageIO.write(imgDest, JPG, new File(dirFolder + File.separator + prefix + file.getName()));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
