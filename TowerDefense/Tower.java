import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
/**
 * Tower object for tower defense game.
 * 
 * @author Rich Mallek
 * @version November 18, 2017 
 *
 */

public class Tower extends TowerDefenseObject{
	private int reloadSpeed;
	private int reloadTimer;
	private int towerNumber;
	private Projectile bullet;
	private boolean fire;
	private BufferedImage img;

	public Tower(int x, int y, BufferedImage image, int w, int h, int reload, int timer) {
		super(x, y, image, w, h);
		reloadSpeed = reload;
		reloadTimer = timer;
		try {
			img = ImageIO.read(new File("rock.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bullet = new Projectile(x, y, img, 5, 5,10.0, 10.0, 10);

	}

	public boolean canFire() {
		reloadTimer -= 1;
		if(reloadTimer == 0) {
			reloadTimer = 10;
			return true;
		}
		return false;
	}

	public Projectile fireAtEnemy(Enemy e) {
		int shootVx = ((e.getTheX() + 25) - this.getTheX())/10;
		int shootVy = ((e.getTheY() + 25) - this.getTheY())/10;
		bullet = new Projectile(this.getTheX(), this.getTheY(), img, 5, 5, shootVx, shootVy, 10);
		
		return bullet;
	}


	public int getTowerNumber() {
		return towerNumber;
	}

	public void setTowerNumber(int towerNumber) {
		this.towerNumber = towerNumber;
	}

	public int getReloadSpeed() {
		return reloadSpeed;
	}

	public Projectile getBullet() {
		return bullet;
	}

	public void setBullet(Projectile bullet) {
		this.bullet = bullet;
	}

	public void setReloadSpeed(int reloadSpeed) {
		this.reloadSpeed = reloadSpeed;
	}

	public int getReloadTimer() {
		return reloadTimer;
	}

	public void setReloadTimer(int reloadTimer) {
		this.reloadTimer = reloadTimer;
	}


}
