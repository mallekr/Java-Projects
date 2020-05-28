import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.*;

/**
 * Moving tower defense object for tower defense game. Makes the moving objects for the game.
 * 
 * @author Rich Mallek
 * @version November 18, 2017 
 *
 */

public class MovingTowerDefenseObject extends TowerDefenseObject{

	private double velX;
	private double velY;
	
	public MovingTowerDefenseObject(int x, int y, BufferedImage image, int w, int h, double vX, double vY) {
		super(x, y, image, w, h);
		velX = vX;
		velY = vY;
	}
	public MovingTowerDefenseObject() {
		
	}

	public double getVelX() {
		return velX;
	}

	public void setVelX(double velX) {
		this.velX = velX;
	}

	public double getVelY() {
		return velY;
	}

	public void setVelY(double velY) {
		this.velY = velY;
	}
	
	//override the drawTheImage of parent to update x and y 
	@Override
	public void drawTheImage(Graphics g) {
		super.drawTheImage(g);
		this.setTheX(super.getTheX() + (int)velX);
		this.setTheY(super.getTheY() + (int)velY);
		
	}

	
}
