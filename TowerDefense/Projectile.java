import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Projectile extends MovingTowerDefenseObject{
	
	private int damage;
	private Enemy e;
	private int enemyX;
	private int enemyY;
	private double pX;
	private double pY;
	
	public Projectile (int x, int y, BufferedImage image, int w, int h, double vX, double vY, int dmg) {
		super(x, y, image, w, h, vX, vY);
		pX = vX;
		pY = vY;
		this.damage = dmg;
	}
	
	public Projectile (Projectile p) {
		super.setTheX(p.getTheX());
		super.setTheY(p.getTheY());
		super.setWidth(p.getWidth());
		super.setHeight(p.getHeight());
		super.setImage(p.getImage());
		super.setVelX(p.getVelX());
		super.setVelY(p.getVelY());
		this.damage = p.damage;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	/*public void fireAtEnemy(Enemy e) {
		pX = this.getTheX();
		pY = this.getTheY();
		enemyX = e.getTheX();
		enemyY = e.getTheY();
		
	}*/
	@Override
	public void drawTheImage(Graphics g) {
		super.drawTheImage(g);
		try {
			Thread.sleep(5);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}
