import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Dimension;


@SuppressWarnings("serial")
public class GameFrame extends JFrame{
	private JLabel lblMoney;
	private JLabel lblLives;
	private int level,money, x, y;
	private int lives;
	private int numRows = 7;
	private int numCols = 9;
	private GamePanel panel;
	private JButton btnTreb;
	private JButton btnCat;
	private JLabel lblLevel;
	private JButton btnStart;
	private boolean catapult, isCatClicked;
	private boolean treb, isTrebClicked;

	
	public GameFrame() {
		GameListener listen = new GameListener();
		this.addMouseListener(listen);
		this.addMouseMotionListener(listen);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(838, 650));
		setTitle("Awesome TD Game");
		catapult = false;
		treb = false;

		Icon imgCat = new ImageIcon("cat.jpg");
		Icon imgTreb = new ImageIcon("treb.jpg");

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {85, 0, 0};
		gridBagLayout.rowHeights = new int[] {0, 0, 0, 75, 75, 75, 75};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		getContentPane().setLayout(gridBagLayout);

		panel = new GamePanel(numRows,numCols, this);
		panel.setMaximumSize(new Dimension(755, 660));
		//panel.setBorder(null);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridheight = 7;
		gbc_panel.weightx = 0.05;
		gbc_panel.weighty = 0.05;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		getContentPane().add(panel, gbc_panel);
		
		btnStart = new JButton("Start");
		GridBagConstraints gbc_btnStart = new GridBagConstraints();
		btnStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed (ActionEvent e) {
				panel.startTheGame();
			}
		});
		gbc_btnStart.insets = new Insets(0, 0, 5, 5);
		gbc_btnStart.gridx = 0;
		gbc_btnStart.gridy = 0;
		getContentPane().add(btnStart, gbc_btnStart);

		lblLevel = new JLabel("Level: " + panel.getLevel());
		GridBagConstraints gbc_lblLevel = new GridBagConstraints();
		gbc_lblLevel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblLevel.insets = new Insets(0, 0, 5, 5);
		gbc_lblLevel.gridx = 0;
		gbc_lblLevel.gridy = 2;
		getContentPane().add(lblLevel, gbc_lblLevel);

		lblLives = new JLabel("Lives: " + panel.getLives());
		GridBagConstraints gbc_lblLives = new GridBagConstraints();
		gbc_lblLives.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblLives.insets = new Insets(0, 0, 5, 5);
		gbc_lblLives.gridx = 0;
		gbc_lblLives.gridy = 3;
		getContentPane().add(lblLives, gbc_lblLives);

		lblMoney = new JLabel("Money: $" + panel.getMoney());
		GridBagConstraints gbc_lblMoney = new GridBagConstraints();
		gbc_lblMoney.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblMoney.insets = new Insets(0, 0, 5, 5);
		gbc_lblMoney.gridx = 0;
		gbc_lblMoney.gridy = 4;
		getContentPane().add(lblMoney, gbc_lblMoney);

		btnCat = new JButton("");
		//btnCat.setBorder(null);
		btnCat.setIcon(imgCat);
		btnCat.setPreferredSize(new Dimension(60,43));
		btnCat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed (ActionEvent e) {
				panel.addTower(1);
			}
		});
		GridBagConstraints gbc_btnCat = new GridBagConstraints();
		gbc_btnCat.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCat.insets = new Insets(0, 0, 5, 5);
		gbc_btnCat.gridx = 0;
		gbc_btnCat.gridy = 5;
		getContentPane().add(btnCat, gbc_btnCat);

		btnTreb = new JButton("");
		btnTreb.setIcon(imgTreb);
		btnTreb.setPreferredSize(new Dimension(60,41));
		btnTreb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed (ActionEvent e) {
				panel.addTower(2);
			}
		});
		GridBagConstraints gbc_btnTreb = new GridBagConstraints();
		gbc_btnTreb.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnTreb.insets = new Insets(0, 0, 0, 5);
		gbc_btnTreb.gridx = 0;
		gbc_btnTreb.gridy = 6;
		getContentPane().add(btnTreb, gbc_btnTreb);

		this.setVisible(true);
	}

	public boolean isCatClicked() {
		return isCatClicked;
	}

	public void setCatClicked(boolean isCatClicked) {
		this.isCatClicked = isCatClicked;
	}

	public boolean isTrebClicked() {
		return isTrebClicked;
	}

	public void setTrebClicked(boolean isTrebClicked) {
		this.isTrebClicked = isTrebClicked;
	}

	public boolean isCatapult() {
		return catapult;
	}

	public void setCatapult(boolean catapult) {
		this.catapult = catapult;
	}

	public boolean isTreb() {
		return treb;
	}

	public void setTreb(boolean treb) {
		this.treb = treb;
	}

	public void setLblLevel(String level) {
		lblLevel.setText(level);
	}

	public void setMoneyLabel(String money) {
		lblMoney.setText(money);
	}

	public JLabel getLblMoney() {
		return lblMoney;
	}

	public void setLblMoney(JLabel lblMoney) {
		this.lblMoney = lblMoney;
	}

	public JLabel getLblLives() {
		return lblLives;
	}

	public void setLblLives(String lives) {
		lblLives.setText(lives);
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

	public static void main(String args[]) {
		new GameFrame();

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
			
			/*if(e.getSource() == btnCat) {
				treb = false;
				catapult = !isCatapult();
				panel.addPicture("cat.jpg");
			}
			if(e.getSource() == btnTreb) {
				catapult = false;
				treb = !isTreb();
				panel.addPicture("treb.jpg");
			}
			if(e.getSource() == btnStart) {
				panel.setMoney(500);
				panel.setLevel(1);
				panel.setLives(500);
			}*/

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
			
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub

		}

		
	}
}
