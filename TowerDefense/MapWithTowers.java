import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;


@SuppressWarnings("serial")
public class MapWithTowers extends JFrame {

	//@SuppressWarnings("unused")
	@Override
	public void paint(Graphics g){
		super.paint(g);
		try{

			//Feel free to use a different image, but I just stuck this here for kicks
			BufferedImage towerImage = ImageIO.read(new File("cat.jpg"));
			BufferedImage towerImage2 = ImageIO.read(new File("treb.jpg"));
			
			//Use this to test your constructor
			//tower constructor: public Tower(int x, int y, BufferedImage image, int w, int h, int reload, int timer)
			Tower t1 = new Tower(300, 100, towerImage, 50, 50, 5, 10);
			Tower t2 = new Tower(350, 450, towerImage2, 50, 50, 10, 10);
			Tower t3 = new Tower(10, 400, towerImage2, 50, 50, 10, 10);
			t1.drawTheImage(g);
			t2.drawTheImage(g);
			t3.drawTheImage(g);
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	public static void main (String [] args){
		MapWithTowers m = new MapWithTowers();
		m.setSize(600, 600);
		m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m.setVisible(true);
	}
}

