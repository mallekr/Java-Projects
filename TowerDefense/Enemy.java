import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Enemy extends MovingTowerDefenseObject{

	private double enemyHealth;
	
	public Enemy (int x, int y, BufferedImage image, int w, int h, double vX, double vY, double eHealth) {
		super(x, y, image, w, h, vX, vY);
		enemyHealth = eHealth;
	}

	public double getEnemyHealth() {
		return enemyHealth;
	}

	public void setEnemyHealth(double enemyHealth) {
		this.enemyHealth = enemyHealth;
	}
	
	@Override
	public void drawTheImage(Graphics g) {
		if (g != null) {
		super.drawTheImage(g);
		}
		try {
			Thread.sleep(5);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
}
