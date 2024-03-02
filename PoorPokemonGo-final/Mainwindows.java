import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.*;
import javax.swing.*;

public class Mainwindows extends JFrame {
	
	Mainwindows() {
		Status player=new Status("player");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
		setLayout(null);
		
		GameMap2 map2 = new GameMap2(this,player);
		Tutorial tutorial = new Tutorial(map2);
		
		add(tutorial);
		add(map2);
		
		map2.requestFocusInWindow();
		map2.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				map2.requestFocusInWindow();
			}
			
		});

	}

}
