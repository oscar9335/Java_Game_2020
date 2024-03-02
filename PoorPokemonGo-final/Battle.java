
import java.awt.Color;
import java.awt.Font;
import 	java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;
public class Battle extends JPanel implements ActionListener{
	 private Player player;
	 private Monster monster;
	 private Timer AnnounceRound=new Timer();
	 private Timer AnnounceSkill=new Timer();
	 private Timer monsterTimer;
	 private int monsterPath;
	 private JLabel allAnnounce=new JLabel();
	 private JButton escape=new JButton("Escape");
	 private boolean endFlag=false;
	 
	 private int Y_tempAttack;	
	 private int M_tempAttack;
	 
	 public String M_name;
	 public Battle(Status PlayerState,Status MonsterState) {
		setSize(Toolkit.getDefaultToolkit().getScreenSize());		
		this.setLayout(null);
		allAnnounce.setSize(500,100);
		allAnnounce.setForeground(Color.white);
		allAnnounce.setFont(new Font("dialog", 1, 50));
		allAnnounce.setLocation(500,250);
		this.add(allAnnounce);
		//����ata
		M_name=MonsterState.name;
		player=new Player(this,PlayerState);
		monster=new Monster(this,MonsterState);
		addButtonEvent();
		newBackground();
		//��身摰�����
		escape.setSize(100,50);
		escape.setLocation(1100,600);
		escape.addActionListener(this);
		add(escape);
		
		//����
		allAnnounce.setText("Your turn");
		AnnounceRound.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				allAnnounce.setText("");
				for(int i=0;i<player.skillUse.size();++i)			
					player.skillUse.get(i).setEnabled(true);
			}
		}, 3000);
		
		
		
		//System.out.println("fuck");
	}
	private void newBackground() {
		Random random=new Random();
		int path=random.nextInt(5)+1;
		try {
			JLabel jlb = new JLabel();	//靘��Lble
			int width = 1300,height = 700;	//�����Lable��祝摨血��漲
			ImageIcon image = new ImageIcon("image\\field1.png");//靘��mageIcon �隞�
	/*銝����:敺甇文�內��� Image(image.getImage());
	�甇文蝷�遣蝡��葬���,蝮格����祝摨�,擃漲��Lble銝��(getScaledInstance(width, height,Image.SCALE_DEFAULT ))
	��敺府敶梯情撠梯身摰敺��葬���(image.setImage)
	*/
			image.setImage(image.getImage().getScaledInstance(width, height,Image.SCALE_DEFAULT ));//�隞亦銝銝蝔�Ⅳ靘誨�
	//Image img = image.getImage();
	//img = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
	//image.setImage(img);
		jlb.setIcon(image);
		jlb.setSize(width, height);
		this.add(jlb);
	} catch (Exception e) {
		// TODO: handle exception
	System.out.println("fuck");
		}
	
		
	}
	
	public void actionPerformed(ActionEvent e) {
		//�����
		
		if(e.getActionCommand().equals("Escape"))
			this.endFlag=true;
		else {
			int blood;
			int attack;
			Y_tempAttack=player.attack;
			M_tempAttack=monster.attack;
			
			
			//皜����
			int n=player.skill.get(e.getActionCommand());
			n--;
			player.skill.put(e.getActionCommand(), n);
			JLabel temp=new JLabel();
			temp=player.skillWatched.get(e.getActionCommand());
			temp.setText(Integer.toString(n));
			player.skillWatched.put(e.getActionCommand(), temp);
			
			switch (e.getActionCommand()) {
			case "coffee":
				blood=player.blooBar.getValue();
				player.blood=blood+10;
				player.blooBar.setValue(blood+10);
				allAnnounce.setText("Use coffee");		
			break;
			case "redBlue":
				blood=player.blooBar.getMaximum()/2;
				player.blood=blood;
				player.blooBar.setValue(blood);
				System.out.println(player.blooBar.getValue());
				allAnnounce.setText("Use redBlue!");		
			break;
			case "leg":
				
				attack=(player.attack+=10);
				player.attackText.setText("Attack:"+attack);
				allAnnounce.setText("Use Smart student");
				
			break;
			case "underwear":
				//System.out.println(property.Y_attack);
				player.attack+=20;
				attack=player.attack;
				player.attackText.setText("Attack:"+attack);
				allAnnounce.setText("Underwear!");
			break;
			case "G_test":
				attack=(monster.attack-=10);
				monster.attackText.setText("Attack:"+attack);
				allAnnounce.setText("Normal test ");
			break;
			case "ticket":
				monster.roundDelay=1;
			allAnnounce.setText("Go home ticket!");
			break;
			
			
			//�畾��
			case "phone":
				if(M_name.equals("monster5"))
				{
					monster.roundDelay=2;
					allAnnounce.setText("Phone call from children");
				}	
				else {
					allAnnounce.setText("Nothing happened");
				}
				break;
			case "medician":
				if(M_name.equals("monster4"))
				{
					monster.blood/=2;
					monster.blooBar.setValue(monster.blood);
					allAnnounce.setText("Diarria!");
				}	
				else {
					allAnnounce.setText("Nothing happened");
				}
				break;
			case "mask":
				if(M_name.equals("DataStructure"))
				{
					monster.roundDelay=2;
					allAnnounce.setText("Mask out of stonk");
				}	
				else {
					allAnnounce.setText("Nothing happened");
				}
				break;
			case "H_test":
				if(M_name.equals("pein"))
				{
					monster.roundDelay=2;
					allAnnounce.setText("You should not pass!");
				}	
				else {
					allAnnounce.setText("Nothing happened");
				}
				break;
			default:
				allAnnounce.setText("Attack!!!");
				break;
			}
			for(int i=0;i<player.skillUse.size();++i)
				//System.out.println(property.skillUse.get(i));
				player.skillUse.get(i).setEnabled(false);
				
				
			
			AnnounceSkill.schedule(new TimerTask() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					allAnnounce.setText("");
					
					StartGame();
					
				}
			}, 2000);
			
		}
		
		

		

	}
	private void addButtonEvent() {
		for(int i=0;i<player.skillUse.size();++i)
		{
			//System.out.println(property.skillUse.get(i));
			player.skillUse.get(i).addActionListener(this);
			player.skillUse.get(i).setEnabled(false);
			
		}
		//System.out.println(property.skillUse.get(0).getActionListeners().length);
		
	}
	public void playerRound() {
		int tempWidth=player.playerWidth;
		int tempHeight=player.playerHeight;
		int tempX=player.p.getLocation().x;
		int tempY=player.p.getLocation().y;
		
		//�ㄐ頝���
		player.playerImageNum=0;
		player.playerPath="image\\playerAttack\\player";
		player.playerWidth=400;
		player.playerHeight=500;
		player.p.setLocation(500,100);
		player.p.setSize(400,500);
		
		//������
		
		monster.blood-=player.attack;
		monster.blooBar.setValue(monster.blood);
		++monster.power;
		monster.powerBar.setValue(monster.power);//power銝��
		
		//蝑��頝��
		try {
			Thread.sleep(2300);
		} catch (InterruptedException exception) {
			// TODO: handle exception
			exception.printStackTrace();
		}
		
		
		//����撅�����
		player.playerPath="image\\player\\player0";
		player.playerWidth=tempWidth;
		player.playerHeight=tempHeight;
		player.p.setLocation(tempX,tempY);
		player.p.setSize(tempWidth,tempHeight);
		
		player.attack=Y_tempAttack;
		monster.attack=M_tempAttack;
		player.attackText.setText("Attack:"+player.attack);
		monster.attackText.setText("Attack:"+monster.attack);
		
		
	}

	public void monsterRound() {
		
		allAnnounce.setText("Enemy turn!");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException exception) {
			// TODO: handle exception
			exception.printStackTrace();
		}
		allAnnounce.setText("");
		
		monsterTimer=new Timer();
		if(monster.powerBar.getValue()==monster.powerBar.getMaximum())
		{
			allAnnounce.setText("Enemy alltermate!");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException exception) {
				// TODO: handle exception
				exception.printStackTrace();
			}
			allAnnounce.setText("");
		}
		
		
		
		useMonsterSkill();
		//頝�芰��
		monster.setMonsterImage(true);
		//蝑�頝��
		try {
			Thread.sleep(1500);
		} catch (InterruptedException exception) {
			// TODO: handle exception
			exception.printStackTrace();
		}

		
		
		//�������
		//����摰�����之蝯�
		
		//�摰之蝯��敺仃
		player.blood-=monster.attack;
		player.blooBar.setValue(player.blood);
		
		
		monster.setMonsterImage(false);
		
		
		//������身摰�
		monster.attack=M_tempAttack;
		monster.attackText.setText("Attack:"+monster.attack);
		

	}
	public void useMonsterSkill() {
		//�ㄐ�����ame隞亙�之蝯�
		if(monster.powerBar.getValue()==monster.powerBar.getMaximum())
		{
			switch (this.M_name) {
			case "pein":
				//���雿�����2����
				player.roundDelay=2;
				break;
			case "DataStructure":
				//���銵����������
				monster.blood/=2;
				monster.blooBar.setValue(monster.blood);
				monster.attack*=2;
				monster.attackText.setText("Attack:"+monster.attack);
				break;
			case "ComputerScience":
				
				//���銵�������
				if(monster.blooBar.getMaximum()/2>=monster.blood)
				{
					monster.blood*=2;
					monster.blooBar.setValue(monster.blood);
				}
				else {
					monster.blood=monster.blooBar.getMaximum();
					monster.blooBar.setValue(monster.blood);
				}
				
				break;
			case "monster4":
				//��������2����
				player.roundDelay=2;
				break;
			case "monster5":
				//���������
				player.attack/=2;
				player.attackText.setText("Attack:"+player.attack);
				break;
			case "monster6":
				//��銵������1
				player.blood=1+monster.attack;
				
				break;
			default:
				break;
			}
			monster.powerBar.setValue(0);
			monster.power=0;
		}
	}
	//���utton�����
 	public void StartGame() {
		//������
		//隞�銵其�◤撠���辣�鈭�
		if(player.roundDelay>0)
		{
			allAnnounce.setText("Cannot move");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException exception) {
				// TODO: handle exception
				exception.printStackTrace();
			}
			--player.roundDelay;
		}
		else {
			playerRound();
			//�ㄐ��撠�銝����
			isWinGame();
			
		}
		if(monster.roundDelay>0)
		{
			allAnnounce.setText("Cannot move");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException exception) {
				// TODO: handle exception
				exception.printStackTrace();
			}
			--monster.roundDelay;
		}
		else {
			monsterRound();
			//�ㄐ����銝����
			isWinGame();
		}

		//System.out.println("successful");
		allAnnounce.setText("Your turn");
		AnnounceRound.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(player.roundDelay>0)
				{
					StartGame();
				}
				else {
					allAnnounce.setText("");
					for(int i=0;i<player.skillUse.size();++i)
					{
						String skillText= player.skillUse.get(i).getText();
						//憒�����撠曹��rue
						if(player.skill.get(skillText)>0)
							player.skillUse.get(i).setEnabled(true);
					}

				}

			}
		}, 3000);
		
	 }
	private void isWinGame() {
		if(player.blooBar.getValue()==0)
		{
			
			allAnnounce.setText("You Lose 0.0");
			//蝑恐閮�頝��
			try {
				Thread.sleep(2000);
			} catch (InterruptedException exception) {
				// TODO: handle exception
				exception.printStackTrace();
			}
			endFlag=true;
			try {
				Thread.sleep(500);
			} catch (InterruptedException exception) {
				// TODO: handle exception
				exception.printStackTrace();
			}
		}
		else if(monster.blooBar.getValue()==0) {
			allAnnounce.setText("You Win :)");
			
			//蝑恐閮�頝��
			try {
				Thread.sleep(2000);
			} catch (InterruptedException exception) {
				// TODO: handle exception
				exception.printStackTrace();
			}
			endFlag=true;
			try {
				Thread.sleep(500);
			} catch (InterruptedException exception) {
				// TODO: handle exception
				exception.printStackTrace();
			}
		}
	}
	public boolean isOver() {
		return endFlag;
	}
	public int getMoney() {
		return monster.money;
	}
}
