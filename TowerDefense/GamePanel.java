import javax.swing.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

//import GameFrame.GameListener;

@SuppressWarnings("serial")
public class GamePanel extends JPanel{
	private BufferedImage[][] imgs;
	private int rows;
	private int cols;
	private int x,start, b;
	private int y, enemyNo;
	private final int tileSize = 64;
	private GameFrame gameFrame;
	private BufferedImage img, eImg;
	private Tower[] tower;
	private Enemy[] enemy;
	private int tcounter;
	private int towerCount;
	private int money, lives, level;
	private boolean showTower;
	private Projectile[] p;


	public GamePanel(int rows, int cols, GameFrame game) {
		GameListener listen = new GameListener();
		this.addMouseMotionListener(listen);
		this.addMouseListener(listen);
		this.rows = rows;
		this.cols = cols;
		imgs = new BufferedImage[rows][cols];
		gameFrame = game;
		tower = new Tower[20];
		money = 500;
		lives = 500;
		level = 1;
		enemy = new Enemy[10];
		showTower = true;
		p = new Projectile[500];

		try {
			Scanner in = new Scanner(Paths.get("Map1.txt"));

			for(int i = 0; i < rows;i++) {
				for(int j = 0; j < cols; j++) {
					String s = in.next();
					if(s.equals("W")) {
						addPicture(i,j, "grass.jpg");
					}
					else {
						addPicture(i, j, "road.jpg");
					}

				}
			}
			in.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void addPicture(int x, int y, String filename){
		if (x < 0 || x >= rows){
			System.err.println("There is no row " + x);
		}
		else if (y < 0 || y >= cols){
			System.err.println("There is no col " + y);
		}
		else{
			try {

				imgs[x][y] = ImageIO.read(new File(filename));
			} catch (IOException e) {
				System.err.println("Unable to read the file: " + filename);
			}
		}
	}

	public void addTower(int towerType) {
		try {
			if(towerType == 1) {
				img = ImageIO.read(new File("cat.jpg"));
				showTower = true;
			}
			if (towerType == 2) {
				img = ImageIO.read(new File("treb.jpg"));
				showTower = true;
			}
			eImg = ImageIO.read(new File("ogre.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void startTheGame() {
		addEnemy(10);
		start = 1;

	}

	public void addEnemy(int number) {
		int enemyX = -100; 
		for(int i = 0; i < number; i++) {
			enemy[i] = new Enemy(enemyX, 200, eImg, 50, 50, 5, 0, 100);
			enemyX -= 200;

		}
	}
	public void checkEnemy() {
		if(start > 0) {
			for (int i = 0; i < enemy.length; i++) {
				if (enemy[i] != null) {
					if (enemy[i].getTheX() > 800) {
						lives -= 10;
						enemy[i] = null;
						gameFrame.setLblLives("Lives: " + lives);
					}
				}
			}
		}
	}
	public void checkDmg() {
		if (start > 0) {
			for (int i = 0; i < p.length;i++) {
				for (int j = 0; j < enemy.length; j++) {
					if(enemy[j] != null && p[i] != null) {

						if (p[i].getTheX() >= enemy[j].getTheX() && p[i].getTheX() <= enemy[j].getTheX()+50 && p[i].getTheY() >= enemy[j].getTheY() && p[i].getTheY() <= enemy[j].getTheY()+50) {
							enemy[j].setEnemyHealth(enemy[j].getEnemyHealth()-p[i].getDamage());
							System.out.println(enemy[j].getEnemyHealth());
						}
						else if (p[i].getTheX() < 0 || p[i].getTheY() < 0 || p[i].getTheX() > 900 || p[i].getTheY() > 700) {
							p[i] = null;
						}
					}

				}
			}
		}
	}

	public void checkBullet() {
		for(int i = 0; i < p.length; i++) {
			if (p[b] != null) {
				if (p[b].getTheX() < 0 || p[b].getTheY() < 0 || p[b].getTheX() > 900 || p[b].getTheY() > 700) {
					p[b] = null;
				}
			}
		}
	}
	public void checkEnemyHealth() {
		if (start > 0) {
			for (int i = 0; i < enemy.length;i++) {
				if(enemy[i] != null) {
					if(enemy[i].getEnemyHealth() == 0) {
						enemy[i] = null;
						money += 100;
						gameFrame.setMoneyLabel("Money: $" + money);
					}
				}

			}

		}
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);


		for (int i = 0; i < rows; i++){
			for (int j = 0; j < cols; j++){
				g.drawImage(imgs[i][j], j*tileSize, i*tileSize, null);
			}
		}
		g.drawImage(img, x, y, null);

		if(towerCount > 0) {
			for(int i = 0; i <= 9;i++) {
				if (tower[i] != null) {
					tower[i].drawTheImage(g);
					if (start > 0) {
						if (tower[i].canFire()) {
							if (enemy[enemyNo] != null) {

								p[b] = tower[i].fireAtEnemy(enemy[enemyNo]);
								b++;
							}
							else if(enemy[enemyNo] == null){ 
								enemyNo++;
								}
							if (enemyNo > 9){
								JOptionPane.showMessageDialog(null, "Game Over Suckaaaaaaaaaaaaaaaaaaaaa!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
								break;
							}
						}
					}
				}

			}
		}

		if(start > 0) {
			for(int i = 0; i < enemy.length;i++) {
				if (enemy[i] != null) {
					enemy[i].drawTheImage(g);
					repaint();
				}
			}
		}
		if (start > 0) {
			for(int i = 0; i < p.length;i++) {
				if(p[i] != null) {
					p[i].drawTheImage(g);
					repaint();
				}
			}
		}
		checkDmg();
		checkEnemyHealth();
		checkEnemy();
		//checkBullet();
	}


	public void drawTheTower(boolean showTower) {
		this.showTower = showTower;
	}

	public int getTheX() {
		return x;
	}

	public void setTheX(int x) {
		this.x = x;
	}

	public int getTheY() {
		return y;
	}

	public void setTheY(int y) {
		this.y = y;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}



	private class GameListener implements MouseListener, MouseMotionListener, ActionListener{

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			/*c = new Color((float)Math.random(), (float)Math.random(), (float)Math.random());

			numLives--;
			drBsJFrame.setTheTextOfTheLivesLabel("Lives: " + numLives);
			repaint();*/
			if (money - 100 >= 0) {
				tower[tcounter] = new Tower(e.getX(), e.getY(), img, 60, 43, 3, 10);
				money -= 100;
				gameFrame.setMoneyLabel("Money: $" + money);
				tcounter++;
				towerCount++;
				showTower = false;

			}

			repaint();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseMoved(MouseEvent e) {
			if (showTower == true) {
				x = e.getX();
				y = e.getY();


			}
			repaint();
		}

		public void actionPerformed(ActionEvent e) {
			/*if(e.getSource() == btn) {
				System.out.println("They Clicked the Button.  Initiate Self Destruct");
			}
			String s = pwd.getPassword().toString();
			lbl.setText(s);*/
		}

		/*@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}*/

	}
}
