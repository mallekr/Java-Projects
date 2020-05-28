import javax.swing.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Tower defense object for tower defense game. Polymorphic -- hopefully.
 * 
 * @author Rich Mallek
 * @version November 18, 2017 
 *
 */

public class TowerDefenseObject {
	private int theX; //x coordinate of object
	private int theY; //y coordinate of object
	BufferedImage image; //object image
	private int width; //width of image
	private int height; // height of image

	public TowerDefenseObject() {

	}

	public TowerDefenseObject(int x, int y, BufferedImage image, int w, int h) {
		theX = x;
		theY = y;
		this.image = image;
		width = w;
		height = h;
	}

	public int getTheX() {
		return theX;
	}

	public void setTheX(int x) {
		theX = x;
	}

	public int getTheY() {
		return theY;
	}

	public void setTheY(int y) {
		theY = y;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void drawTheImage(Graphics g) {
		
		g.drawImage(image, theX, theY, width, height, null);

	}
}
