package main;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;

public class GameWindow extends JFrame{
	public GameWindow(GamePanel gamePanel) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/*After setDefaultCloseOperation we should place 
		 *setVisible otherwise we get some problems 
		 *the screen will not render*/
		this.add(gamePanel);
		this.setResizable(false);
		this.pack(); // Window to be sized to fit the preferred sizeand layouts of its subcomponents (we have one component -> JPanel)
		this.setLocationRelativeTo(null);
		this.setVisible(true); 
		this.addWindowFocusListener(new WindowFocusListener() {
			@Override
			public void windowGainedFocus(WindowEvent e) {}
			@Override
			public void windowLostFocus(WindowEvent e) {
				gamePanel.getPlayer().stopWhenLostWindowFocus();
			}
		});
	}
	
}
