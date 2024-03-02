import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameMap2 extends JPanel implements ActionListener{
	private int person_x = 750;
	private int person_y = 280;  
	public JLabel character;
	public JLabel pein;
	public JLabel data_s;
	public JLabel computer;
	public JLabel monster4;
	public JLabel monster5;
	public JLabel monster6;
	int x_up  = 0;
	int x_lower = 0;
	int y_up =0;
	int y_lower = 0;
	
	ImageIcon down_1 = new ImageIcon("image\\MapPlayer\\正面1.png"); 
	ImageIcon down_2 = new ImageIcon("image\\MapPlayer\\正面2.png"); 
	ImageIcon down_3 = new ImageIcon("image\\MapPlayer\\正面3.png"); 
	ImageIcon up_1 = new ImageIcon("image\\MapPlayer\\背面1.png"); 
	ImageIcon up_2 = new ImageIcon("image\\MapPlayer\\背面2.png"); 
	ImageIcon up_3 = new ImageIcon("image\\MapPlayer\\背面3.png"); 
	ImageIcon right_1 = new ImageIcon("image\\MapPlayer\\向右1.png"); 
	ImageIcon right_2 = new ImageIcon("image\\MapPlayer\\向右2.png"); 
	ImageIcon right_3 = new ImageIcon("image\\MapPlayer\\向右3.png"); 
	ImageIcon left_1 = new ImageIcon("image\\MapPlayer\\向左1.png"); 
	ImageIcon left_2 = new ImageIcon("image\\MapPlayer\\向左2.png"); 
	ImageIcon left_3 = new ImageIcon("image\\MapPlayer\\向左3.png"); 
	
	
	
	//angry

	
	
	private Status MyPlayer;
	private Timer listenEnd=new Timer();
	private JFrame mainField;
	private int Mymoney;
	private Battle battle;
	private Store st;
	
	
	
	public GameMap2(JFrame jFrame,Status p) {
		//���脖�frame

		this.mainField=jFrame;
		//��ainwindow��layer
		this.MyPlayer=p;
		st = new Store(this);
		mainField.add(st);
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		
		button_location();
 		store_buttom();
		bag_buttom();
		newBackground();
		this.setLayout(null);
		
		this.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				
				//System.out.println(e.getKeyChar());
				if(e.getKeyCode() == KeyEvent.VK_UP) {
					person_y -=10;
					character.setLocation(person_x,person_y);
					character.setIcon(up_1);
				}
				if(e.getKeyCode() == KeyEvent.VK_DOWN) {
					person_y +=10;
					character.setLocation(person_x,person_y);
					character.setIcon(down_1);
				}
				if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
					person_x +=10;
					character.setLocation(person_x,person_y);
					character.setIcon(right_1);
				}
				if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					person_x -=10;
					character.setLocation(person_x,person_y);
					character.setIcon(left_1);
				}
				if(e.getKeyCode()==KeyEvent.VK_SPACE) {
					//if(person_x + 40 );
				}
				the_TA_locaton(person_x,person_y);
				the_professors(person_x,person_y);
			}

			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			
		});
		setFocusable(true);
		setVisible(false);
		requestFocusInWindow();
	
		

	}

	
	
	private void newBackground() {
		JLabel jlb = new JLabel();
		int width = 1300,height = 700;
		ImageIcon image = new ImageIcon("image\\Map.png");		
		image.setImage(image.getImage().getScaledInstance(width, height,Image.SCALE_DEFAULT ));	
		jlb.setIcon(image);
		jlb.setSize(width, height);
			
		this.add(jlb);
	}
//	ImageIcon pp = new ImageIcon("image\\Monster\\1.gif");
	ImageIcon pp = new ImageIcon("image\\MapMonster\\培英.jpg");
//	ImageIcon da = new ImageIcon("image\\Monster\\2.gif");
	ImageIcon da = new ImageIcon("image\\MapMonster\\資結.jpg");
//	ImageIcon com = new ImageIcon("image\\Monster\\3.gif");
	ImageIcon com = new ImageIcon("image\\MapMonster\\英超.jpg");
	ImageIcon m4=new ImageIcon("image\\Monster\\4.gif");
	ImageIcon m5=new ImageIcon("image\\Monster\\5.gif");
	ImageIcon m6=new ImageIcon("image\\Monster\\6.gif");
	private void button_location() {		
		pp.setImage(pp.getImage().getScaledInstance(250, 162,Image.SCALE_DEFAULT ));
		da.setImage(da.getImage().getScaledInstance(250, 162,Image.SCALE_DEFAULT ));
		pein = new JLabel(pp);		
		data_s = new JLabel(da);	
		computer = new JLabel(com);	
		monster4=new JLabel(m4);
		monster5=new JLabel(m5);
		monster6=new JLabel(m6);
		pein.setSize(200, 160);
		pein.setLocation(500, 80);
		data_s.setSize(200, 160);
		data_s.setLocation(40, 60);
		
		
		computer.setSize(200,160);
		computer.setLocation(280, 490);
		
		monster4.setSize(200,160);
		monster5.setSize(200,160);
		monster6.setSize(200,160);
		monster4.setLocation(270,0);
		monster5.setLocation(950,450);
		monster6.setLocation(570,500);
		
		
		character = new JLabel(down_1);
		//character.setContentAreaFilled(false);
		character.setSize(60,70);
		character.setLocation(person_x, person_y);      //�������停 x+10 y+40
		//setlocation(character);

		//character.setBorderPainted(false); 
		
		
		//setVisible(true);
		
		this.add(character);
		this.add(pein);
		this.add(data_s);
		this.add(computer);
		this.add(monster4);
		this.add(monster5);
		this.add(monster6);
		this.requestFocusInWindow();
		
	}
	

	
	
	
	/*private void setlocation(JButton a) {
		a.setLocation(person_x + 40,person_y -10);
		
	}*/
	

	
	private void store_buttom() {
		ImageIcon store_image = new ImageIcon("image\\Store_icon.png");		
		store_image.setImage(store_image.getImage().getScaledInstance(100, 60,Image.SCALE_DEFAULT ));	
		JButton store = new JButton(store_image);
		store.setActionCommand("store");
		store.setIcon(store_image);
		store.setSize(100,60);
		//store.setLocation(1425, 735);
		store.setLocation(0, 0);
		store.addActionListener(this);
		store.setContentAreaFilled(false);
		setVisible(true);	
		this.add(store);
	}
	
	
	
	private void bag_buttom() {
		ImageIcon bag_buttom = new ImageIcon("image\\Bag_icon.png");		
		bag_buttom.setImage(bag_buttom.getImage().getScaledInstance(100, 60,Image.SCALE_DEFAULT ));	
		JButton bag = new JButton(bag_buttom);
		bag.setActionCommand("bag");
		bag.setIcon(bag_buttom);
		bag.setSize(100,60);
		int bag_x = 1300;
		int bag_y = 735; 
		bag.setLocation(bag_x, bag_y);
		//bag.setLocation(0, 0);
		bag.addActionListener(this);
		bag.setContentAreaFilled(false);
		setVisible(true);	
		this.add(bag);
	}

	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "store":	
			System.out.println("open store");
			st.init(MyPlayer);
			//mainField.pack();
			//this.mainField.setSize(1300,500);
			break;
		case "bag":
			System.out.println("open bag");
			break;	
		}
		this.requestFocusInWindow();
	}	
	
	public void  the_TA_locaton(int person_x,int person_y) {
		if(person_x >850 && person_x < 1300 && person_y > 0 && person_y < 400) {
			Random random=new Random();
			int randomNum=random.nextInt(10);
			System.out.println(randomNum);
			if(randomNum==1) {

				
				
				//����
				Status M=new Status("TA");
				enterBattle(M);
			}
		}
	}
	
	public void the_professors(int person_x,int person_y) {
		boolean enter=false;
		Status M=new Status("whatever");
		if(person_x >500 && person_x < 700 && person_y > 80 && person_y < 240) {
			System.out.println("pein");
			
			//�ㄐ閫貊�擛�
			//閮�策��oss name
			M=new Status("pein");
			enter=true;

			


		}
		if(person_x >40 && person_x < 240 && person_y > 60 && person_y < 220) {
			System.out.println("data_s");
			
			M=new Status("DataStructure");
			enter=true;
		}

		if(person_x >280 && person_x < 480 && person_y > 490 && person_y < 650) {
			System.out.println("computer");
			
			M=new Status("ComputerScience");
			enter=true;
		}
		
		//4
		if(person_x >270 && person_x < 470 && person_y > 0 && person_y < 160) {
			System.out.println("4");
			
			M=new Status("monster4");
			enter=true;
		}
		//5
		if(person_x >950 && person_x < 1150 && person_y > 450 && person_y < 610) {
			System.out.println("5");
			
			M=new Status("monster5");
			enter=true;
		}
		//6
		if(person_x >570 && person_x < 770 && person_y > 500 && person_y < 660) {
			System.out.println("6");
			
			M=new Status("monster6");
			enter=true;
		}
		if(enter)
		{
			enterBattle(M);
		}
		
	}
	private void enterBattle(Status M) {
		MyPlayer.blood= 100;
		
		battle=new Battle(MyPlayer,M);
		mainField.add(battle);
		this.setVisible(false);
		Timer listenEnd=new Timer();
		listenEnd.schedule(new TimerTask() {
				
			@Override
			
			public void run() {
				// TODO Auto-generated method stub
				//銝�皜祆�蝯��
				if(battle.isOver())
				{
					backMap();
					//System.out.println("fuck");
					listenEnd.cancel();
					
					
					
				}
				
				
			}
		}, 0, 500);
	}
	private void backMap() {
		Mymoney+=battle.getMoney();
		battle.setVisible(false);
		this.setVisible(true);
		battle=null;
		this.requestFocusInWindow();
	}
	
	/*public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
			case "store":	
				System.out.println("open store");	
				break;
			case "bag":
				System.out.println("open bag");
				break;		
			case "character":
				System.out.println("move");
				break;
			case"center":
				System.out.println("center");
				if(person_x == 100 && person_y == 280) {  //from water		
						Timer timer = new Timer();  
						TimerTask task = new TimerTask() {  
				            @Override
				            public void run() {  
				            	person_x += 1;
				            	character.setLocation(person_x,person_y);
				            	if(person_x == 750) {
				            		//setlocation(character);
				            		timer.cancel();
				            	}
				            }  
				        };  
				        
				        long delay = 0;  
				        long intevalPeriod = 4;  
				        timer.scheduleAtFixedRate(task, delay, intevalPeriod); 
				        timer.schedule(task, delay);  
						
				        
					}
			*/	



	
	
	
	
	
	


	
}


