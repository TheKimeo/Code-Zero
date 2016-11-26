package Controller;

import javax.swing.JFrame;

import com.neet.DiamondHunter.Main.GamePanel;

public class Main {

	public static void main(String[] args) {
		
		JFrame window = new JFrame("Diamond Hunter");
		window.add(new GamePanel());
		
		window.setResizable(false);
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GameManager gm = new GameManager();
		gm.run();
	}

}
