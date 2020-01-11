package make_game;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Start_Window extends JFrame implements ActionListener{

	 private Image background;
	 private Image background_Start = new ImageIcon(Main.class.getResource("../images/startbackground.jpg")).getImage(); // 1280x720	
	 private Image background_Play = new ImageIcon(Main.class.getResource("../images/gamebackground.png")).getImage(); // 1280x720
	 private Image picture = new ImageIcon(Main.class.getResource("../images/picture1.png")).getImage(); // 480x320
	 private Image picture1 = new ImageIcon(Main.class.getResource("../images/picture1.png")).getImage(); // 480x320
	 private Image picture2 = new ImageIcon(Main.class.getResource("../images/picture2.png")).getImage(); // 480x320
	 private Image picture3 = new ImageIcon(Main.class.getResource("../images/picture3.png")).getImage(); // 480x320
	 private Image picture4 = new ImageIcon(Main.class.getResource("../images/picture4.png")).getImage(); // 480x320
	 
	 private boolean bt = false;
	 
	 private ImageIcon startButton = new ImageIcon(Main.class.getResource("../images/startButton.png"));
	private ImageIcon exitButton = new ImageIcon(Main.class.getResource("../images/exitButton.png"));
	private ImageIcon leftButton = new ImageIcon(Main.class.getResource("../images/leftButton.png"));
	private ImageIcon rightButton = new ImageIcon(Main.class.getResource("../images/rightButton.png"));
	private ImageIcon selectButton = new ImageIcon(Main.class.getResource("../images/selectButton.png"));
	private ImageIcon backButton = new ImageIcon(Main.class.getResource("../images/backButton.png"));
	private JButton start_bt = new JButton(startButton);
	private JButton exit_bt = new JButton(exitButton);
	private JButton right_bt = new JButton(rightButton);
	private JButton left_bt = new JButton(leftButton);
	private JButton select_bt = new JButton(selectButton);
	private JButton back_bt = new JButton(backButton);
		
		MyPanel backpanel = new MyPanel();		
		Kpop backmusic=new Kpop("A.MP3",true);
		public  Start_Window()
		{
			backmusic.start();
			background = background_Start;
			this.setTitle("틀린그림찾기"); // 프레임 이름
			this.setSize(1280,720); // 프레임 크기
			this.setVisible(true); // 프레임 보이게 하기
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프로세스 종료
			this.setResizable(false); // 
			this.setLocationRelativeTo(null);
			
			backpanel.setLayout(null);
			this.add(backpanel);
			
			start_bt.setBounds(400, 400, 200, 100);
	        start_bt.addActionListener(this);
	        start_bt.setActionCommand("start");
	        backpanel.add(start_bt);
	        
	        exit_bt.setBounds(730, 400, 200, 100);
	        exit_bt.addActionListener(this);
	        exit_bt.setActionCommand("exit");
	        backpanel.add(exit_bt);
	        
	        right_bt.setBounds(700, 520, 100, 100);
	        right_bt.addActionListener(this);
	        right_bt.setActionCommand("right");
	        
	        left_bt.setBounds(500 , 520 , 100, 100);
	        left_bt.addActionListener(this);
	        left_bt.setActionCommand("left");
	        
	        select_bt.setBounds(600 , 520 , 100, 100);
	        select_bt.addActionListener(this);
	        select_bt.setActionCommand("select");
	        
	        back_bt.setBounds(1177 , 588 , 100, 100);
	        back_bt.addActionListener(this);
	        back_bt.setActionCommand("back");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand() == "start")
			{
				backpanel.add(left_bt);
				backpanel.add(right_bt);
				backpanel.add(select_bt);
				backpanel.add(back_bt);
				start_bt.setVisible(false);
				exit_bt.setVisible(false);
				background = background_Play;
				Main.image_num = 1;
				bt = true;
				picture = picture1;
				backpanel.repaint();
			}
			
			if(e.getActionCommand() == "back")
			{
				backpanel.remove(right_bt);
				backpanel.remove(left_bt);
				backpanel.remove(select_bt);
				backpanel.remove(back_bt);
				start_bt.setVisible(true);
				exit_bt.setVisible(true);
				background = background_Start;
				bt = false;
				backpanel.repaint();
			}
			
			if(e.getActionCommand() == "right")
			{
				Main.image_num++;
				if(Main.image_num == 1)
				{
					picture = picture1;
					repaint();
				}else if(Main.image_num== 2) {
					picture = picture2;
					repaint();
				}else if(Main.image_num == 3) {
					picture = picture3;
					repaint();
				}else if(Main.image_num == 4) {
					picture = picture4;
					repaint();
				}else if(Main.image_num == 5) {
					Main.image_num = 4;
				}
			}
			
			if(e.getActionCommand() == "left")
			{
				Main.image_num--;
				if(Main.image_num == 1)
				{
					picture = picture1;
					repaint();
				}else if(Main.image_num == 2) {
					picture = picture2;
					repaint();
				}else if(Main.image_num == 3) {
					picture = picture3;
					repaint();
				}else if(Main.image_num == 4) {
					picture = picture4;
					repaint();
				}else if(Main.image_num == 0) {
					Main.image_num = 1;
				}
			}
			
			if(e.getActionCommand() == "select")
			{
				new Play_Window();
			}
			
			if(e.getActionCommand() == "exit")
			{
				System.exit(0);
				backmusic.close();
			}
		}
		
		class MyPanel extends JPanel{
	        public void paintComponent(Graphics g){
	            super.paintComponent(g);
	            g.drawImage(background,0,0,this);
				if(bt == true)
				{
					g.drawImage(picture, 440, 280, null);
				}
	            repaint();
	        }
	    }
}