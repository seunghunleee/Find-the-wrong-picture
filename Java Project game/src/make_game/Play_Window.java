package make_game;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Play_Window extends JFrame implements ActionListener{
	
	private Image pause = new ImageIcon(Main.class.getResource("../images/pause.jpg")).getImage(); // 400x240;
	
	private Image sbackground = new ImageIcon(Main.class.getResource("../images/picture1.png")).getImage(); // 400x240;
	private Image sbackground_other = new ImageIcon(Main.class.getResource("../images/picture1_change.png")).getImage(); // 400x240
	private Image sbackground1 = new ImageIcon(Main.class.getResource("../images/picture1.png")).getImage(); // 400x240
    private Image sbackground1_other = new ImageIcon(Main.class.getResource("../images/picture1_change.png")).getImage(); // 400x240
    private Image sbackground2 = new ImageIcon(Main.class.getResource("../images/picture2.png")).getImage(); // 240x320
    private Image sbackground2_other = new ImageIcon(Main.class.getResource("../images/picture2_change.png")).getImage(); // 240x320
    private Image sbackground3 = new ImageIcon(Main.class.getResource("../images/picture3.png")).getImage(); // 240x320
    private Image sbackground3_other = new ImageIcon(Main.class.getResource("../images/picture3_change.png")).getImage(); // 240x320
    private Image sbackground4 = new ImageIcon(Main.class.getResource("../images/picture4.png")).getImage(); // 240x320
    private Image sbackground4_other = new ImageIcon(Main.class.getResource("../images/picture4_change.png")).getImage(); // 240x320
    
    public static int ans1 = 0 , ans2 = 0 , ans3 = 0 , ans4 = 0 , ans5=0; 
    public static int pointX = 0 , pointY = 0;
    public static int ans1X = 0 , ans1Y = 0 , ans2X = 0 , ans2Y = 0 , ans3X = 0 , ans3Y = 0 , ans4X = 0 , ans4Y = 0 , ans5X = 0 , ans5Y = 0;
    public static int ans1Xr =0 , ans2Xr =0 , ans3Xr =0 , ans4Xr =0 , ans5Xr =0;
    public static int time_sec = 1 , time_num=0;
    
    private boolean pause_start = false;
    public boolean swit = false;
    
    public static int checknum=0,remain_num=5,find_sum=0,find=0;
    
    private JButton exit_bt = new JButton("EXIT");
    private JButton pause_bt = new JButton();
    private JButton restart_bt = new JButton();
    
    private JLabel label_check = new JLabel();
    private JLabel label_time = new JLabel();
    
    MyPanel panel = new MyPanel();
    thread_time nowtime = new thread_time();
    
    public double time;
    
    public Play_Window()
    {
    	ans1 = 0;
    	ans2 = 0;
    	ans3 = 0;
    	ans4 = 0;
    	ans5 = 0;
    	checknum = 0;
    	time_sec = 1;
    	this.setTitle("틀린그림찾기");
    	this.setSize(800, 500);
    	this.setVisible(true);
    	this.setResizable(false);
    	this.setLocationRelativeTo(null);
    	addWindowListener(new WindowAdapter() {
    		public void windowClosing(WindowEvent e) 
    		{
    			dispose();
    		}
		});
    	
    	time = System.currentTimeMillis();
    	swit = true;
		nowtime.start();
    	
    	this.add(panel);
    	panel.setLayout(null);
    	
    	label_check.setFont(new Font("바탕", Font.BOLD, 20));
        label_check.setBounds(0, 350, 800, 20);
        panel.add(label_check);
        
        label_time.setText("제한시간 60초 // 경과 시간 : " + 0 + "초");
        label_check.setText("클릭 가능 횟수 : " + 10 + "회 // 클릭 횟수 : " + 0 + "회 // 남은 그림 : " + 5 + "개 // 찾은 갯수 : " + 0 +"개");
        panel.addMouseListener(new java.awt.event.MouseAdapter() {
    		public void mouseClicked(java.awt.event.MouseEvent e) {
    			pointX = e.getX();
    			pointY = e.getY();
    			
    			if(pointY<240)
	    		{
    				if(Main.image_num == 1)
    				{
    					game1();
    				}else if(Main.image_num == 2) {
    					game2();
    				}else if(Main.image_num == 3) {
    					game3();
    				}else if(Main.image_num == 4) {
    					game4();
    				}
    				
    				find=find_sum;
	    			checknum++;Main.cc++;
					find_sum = ans1 + ans2 + ans3 + ans4 + ans5;
					remain_num = 5 - ans1 - ans2 - ans3 - ans4 - ans5;
					label_check.setText("클릭 가능 횟수 : " + 10 + "회 // 클릭 횟수 : " + checknum + "회 // 남은 그림 : " + remain_num + "개 // 찾은 갯수 : " + find_sum +"개");
					if(find == 5)
					{
						nowtime.stop();
						JOptionPane.showMessageDialog(null, "성공 입니다!! \n클릭 한 횟수 : " + checknum,"축하합니다!!",JOptionPane.PLAIN_MESSAGE);
			    		dispose();
					}else if(checknum == 10) {
						nowtime.stop();
						JOptionPane.showMessageDialog(null, "실패 입니다!! \n클릭 횟수 초과입니다. 다시 도전하세요.","실패입니다!!",JOptionPane.PLAIN_MESSAGE);
			    		dispose();
					}
    			}
    		}    		
		});
        
        exit_bt.setBounds(400, 240, 400, 100);
        exit_bt.addActionListener(this);
        exit_bt.setActionCommand("exit");
        panel.add(exit_bt);
        
        pause_bt.setBounds(0, 240, 400, 100);
        pause_bt.addActionListener(this);
        pause_bt.setActionCommand("pause");
        pause_bt.setText("Pause");
        panel.add(pause_bt);
        
        restart_bt.setBounds(0, 240, 400, 100);
        restart_bt.addActionListener(this);
        restart_bt.setActionCommand("restart");
        restart_bt.setText("ReStart");
        panel.add(restart_bt);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
    	
    	// TODO Auto-generated method stub
    	if(e.getActionCommand() == "exit")
    	{
    		time_sec = 1;
    		dispose();
    	}
    	if(e.getActionCommand() == "pause")
    	{
			time_num=0;
    		pause_start = true;
    		panel.remove(pause_bt);
    		panel.add(restart_bt);
    	}
    	if(e.getActionCommand() ==  "restart")
    	{
			time_num=1;
    		pause_start = false;
    		panel.remove(restart_bt);
    		panel.add(pause_bt);
    	}
    }
    
    /////////////////////// 시간 경과 함수
    public class thread_time extends Thread
    {
		public void run() {
			label_time.setFont(new Font("바탕", Font.BOLD, 20));
			label_time.setBounds(0, 400, 400, 30);
			panel.add(label_time);
			time_sec = 1;
			while(swit)
			{
				if(System.currentTimeMillis() - time > 60000 )
		    	{ 
					swit = false;
					JOptionPane.showMessageDialog(null, "실패 입니다!! \n시간 초과입니다. 다시 도전하세요.","실패입니다!!",JOptionPane.PLAIN_MESSAGE);
		    		dispose();
		    	}
				if((System.currentTimeMillis()-time) == time_sec*1000)
				{
					label_time.setText("제한시간 60초 // 경과 시간 : " + time_sec + "초");
					time_sec++;
				}
			}
		}
	}
    
    //////////////////////////////좌표 체크 함수////////////////////////////
    public void game1() {
    	ans1X = 225;ans1Y = 170;ans1Xr = 225+400;
		ans2X = 157;ans2Y = 229;ans2Xr = 157+400;
		ans3X = 359;ans3Y = 214;ans3Xr = 359+400;
		ans4X = 362;ans4Y = 83;ans4Xr = 362+400;
		ans5X = 293;ans5Y = 31;ans5Xr = 293+400;
    	if(pointX<400 && pointY<=400)
		{
			//225,170 오리
			if(((ans1X-6<=pointX)&&(pointX<=ans1X+6))&&((ans1Y-6<=pointY)&&(pointY<=ans1Y+6)))
			{
				ans1 = 1;
			}
			
			//157,229 거북이 +-4
			if(((ans2X-4<=pointX)&&(pointX<=ans2X+4))&&((ans2Y-4<=pointY)&&(pointY<=ans2Y+4)))
			{
				ans2 = 1;
			}

			//359,214 오른쪽 아래 나비 +-2
			if(((ans3X-2<=pointX)&&(pointX<=ans3X+2))&&((ans3Y-2<=pointY)&&(pointY<=ans3Y+2)))
			{
				ans3 = 1;
			}

			//362,83 벽돌무늬 +-2
			if(((ans4X-2<=pointX)&&(pointX<=ans4X+2))&&((ans4Y-2<=pointY)&&(pointY<=ans4Y+2)))
			{
				ans4 = 1;
			}

			//293,31창문 중앙 대칭 +-8
			if(((ans5X-8<=pointX)&&(pointX<=ans5X+8))&&((ans5Y-8<=pointY)&&(pointY<=ans5Y+8)))
			{
				ans5 = 1;
			}
		}
		
		if(pointX>400 && pointY<=400)
		{
			//225,170 오리
			if(((ans1Xr-6<=pointX)&&(pointX<=ans1Xr+6))&&((ans1Y-6<=pointY)&&(pointY<=ans1Y+6)))
			{
				ans1 = 1;
			}

			//157,229 거북이
			if(((ans2Xr-4<=pointX)&&(pointX<=ans2Xr+4))&&((ans2Y-4<=pointY)&&(pointY<=ans2Y+4)))
			{
				ans2 = 1;
			}

			//359,214오른쪽 아래 나비
			if(((ans3Xr-2<=pointX)&&(pointX<=ans3Xr+2))&&((ans3Y-2<=pointY)&&(pointY<=ans3Y+2)))
			{
				ans3 = 1;
			}
			
			//362,83 벽돌무늬
			if(((ans4Xr-2<=pointX)&&(pointX<=ans4Xr+2))&&((ans4Y-2<=pointY)&&(pointY<=ans4Y+2)))
			{
				ans4 = 1;
			}
			
			//293,31창문 중앙 대칭
			if(((ans5Xr-8<=pointX)&&(pointX<=ans5Xr+8))&&((ans5Y-8<=pointY)&&(pointY<=ans5Y+8)))
			{
				ans5 = 1;
			}
		}
		find_sum = ans1 + ans2 + ans3 + ans4 + ans5;
    }
    
    public void game2() {
    	ans1X = 91;ans1Y = 142;ans1Xr = 91+400;
    	ans2X = 345;ans2Y = 158;ans2Xr = 345+400;
    	ans3X = 183;ans3Y = 164;ans3Xr = 183+400;
    	ans4X = 39;ans4Y = 18;ans4Xr = 39+400;
    	ans5X = 331;ans5Y = 90;ans5Xr = 331+400;
    	if(pointX<400 && pointY<=400)
		{
    		//91,142 비둘기대칭 +-4
			if(((ans1X-4<=pointX)&&(pointX<=ans1X+4))&&((ans1Y-4<=pointY)&&(pointY<=ans1Y+4)))
			{
				ans1 = 1;
			}
			//345,158 나비옆에 하트지움 +-2
			if(((ans2X-2<=pointX)&&(pointX<=ans2X+2))&&((ans2Y-2<=pointY)&&(pointY<=ans2Y+2)))
			{
				ans2 = 1;
			}
			//183,164 사람날개무늬 +-2
			if(((ans3X-2<=pointX)&&(pointX<=ans3X+2))&&((ans3Y-2<=pointY)&&(pointY<=ans3Y+2)))
			{
				ans3 = 1;
			}
			//39,18 태양 +-8
			if(((ans4X-8<=pointX)&&(pointX<=ans4X+8))&&((ans4Y-8<=pointY)&&(pointY<=ans4Y+8)))
			{
				ans4 = 1;
			}
			//331,90 새두마리있는거대칭 +-6
			if(((ans5X-6<=pointX)&&(pointX<=ans5X+6))&&((ans5Y-6<=pointY)&&(pointY<=ans5Y+6)))
			{
				ans5 = 1;
			}
		}
		
		if(pointX>400 && pointY<=400)
		{
    		//91,142 비둘기대칭 +-4
			if(((ans1Xr-4<=pointX)&&(pointX<=ans1Xr+4))&&((ans1Y-4<=pointY)&&(pointY<=ans1Y+4)))
			{
				ans1 = 1;
			}
			//345,158 나비옆에 하트지움 +-2
			if(((ans2Xr-2<=pointX)&&(pointX<=ans2Xr+2))&&((ans2Y-2<=pointY)&&(pointY<=ans2Y+2)))
			{
				ans2 = 1;
			}
			//183,164 사람날개무늬 +-2
			if(((ans3Xr-2<=pointX)&&(pointX<=ans3Xr+2))&&((ans3Y-2<=pointY)&&(pointY<=ans3Y+2)))
			{
				ans3 = 1;
			}
			//39,18 태양 +-8
			if(((ans4Xr-8<=pointX)&&(pointX<=ans4Xr+8))&&((ans4Y-8<=pointY)&&(pointY<=ans4Y+8)))
			{
				ans4 = 1;
			}
			//331,90 새두마리있는거대칭 +-6
			if(((ans5Xr-6<=pointX)&&(pointX<=ans5Xr+6))&&((ans5Y-6<=pointY)&&(pointY<=ans5Y+6)))
			{
				ans5 = 1;
			}
		}
		find_sum = ans1 + ans2 + ans3 + ans4 + ans5;
    }
    public void game3() {
    	ans1X = 374;ans1Y = 21;ans1Xr = 374+400;
    	ans2X = 58;ans2Y = 98;ans2Xr = 58+400;
    	ans3X = 80;ans3Y = 217;ans3Xr = 80+400;
    	ans4X = 23;ans4Y = 49;ans4Xr = 23+400;
    	ans5X = 233;ans5Y = 17;ans5Xr = 233+400;
    	if(pointX<400 && pointY<=400)
		{
    		//374,21오른쪽위 계란대칭 +-8
			if(((ans1X-8<=pointX)&&(pointX<=ans1X+8))&&((ans1Y-8<=pointY)&&(pointY<=ans1Y+8)))
			{
				ans1 = 1;
			}
			//58,98화분에 꽃무늬 +-6
			if(((ans2X-6<=pointX)&&(pointX<=ans2X+6))&&((ans2Y-6<=pointY)&&(pointY<=ans2Y+6)))
			{
				ans2 = 1;
			}
			//80,217나비날개무늬 +-8
			if(((ans3X-8<=pointX)&&(pointX<=ans3X+8))&&((ans3Y-8<=pointY)&&(pointY<=ans3Y+8)))
			{
				ans3 = 1;
			}
			//23,49화분에 꽃모양 +-4
			if(((ans4X-4<=pointX)&&(pointX<=ans4X+4))&&((ans4Y-4<=pointY)&&(pointY<=ans4Y+4)))
			{
				ans4 = 1;
			}
			//233,17토끼옆에 꽃 +-6
			if(((ans5X-6<=pointX)&&(pointX<=ans5X+6))&&((ans5Y-6<=pointY)&&(pointY<=ans5Y+6)))
			{
				ans5 = 1;
			}
		}
		
		if(pointX>400 && pointY<=400)
		{
			//374,21오른쪽위 계란대칭 +-8
			if(((ans1Xr-8<=pointX)&&(pointX<=ans1Xr+8))&&((ans1Y-8<=pointY)&&(pointY<=ans1Y+8)))
			{
				ans1 = 1;
			}
			//58,98화분에 꽃무늬 +-6
			if(((ans2Xr-6<=pointX)&&(pointX<=ans2Xr+6))&&((ans2Y-6<=pointY)&&(pointY<=ans2Y+6)))
			{
				ans2 = 1;
			}
			//80,217나비날개무늬 +-8
			if(((ans3Xr-8<=pointX)&&(pointX<=ans3Xr+8))&&((ans3Y-8<=pointY)&&(pointY<=ans3Y+8)))
			{
				ans3 = 1;
			}
			//23,49화분에 꽃모양 +-4
			if(((ans4Xr-4<=pointX)&&(pointX<=ans4Xr+4))&&((ans4Y-4<=pointY)&&(pointY<=ans4Y+4)))
			{
				ans4 = 1;
			}
			//233,17토끼옆에 꽃 +-6
			if(((ans5Xr-6<=pointX)&&(pointX<=ans5Xr+6))&&((ans5Y-6<=pointY)&&(pointY<=ans5Y+6)))
			{
				ans5 = 1;
			}
		}
		
		find_sum = ans1 + ans2 + ans3 + ans4 + ans5;
    }
    public void game4() {
    	ans1X = 360;ans1Y = 223;ans1Xr = 360+400;
    	ans2X = 45;ans2Y = 135;ans2Xr = 45+400;
    	ans3X = 107;ans3Y = 29;ans3Xr = 107+400;
    	ans4X = 116;ans4Y = 213;ans4Xr = 116+400;
    	ans5X = 124;ans5Y = 122;ans5Xr = 124+400;
    	if(pointX<400 && pointY<=400)
		{
    		//360,223오른쪽아래 공무늬 +-6
			if(((ans1X-6<=pointX)&&(pointX<=ans1X+6))&&((ans1Y-6<=pointY)&&(pointY<=ans1Y+6)))
			{
				ans1 = 1;
			}
			//45,135고양이손무늬 +-2
			if(((ans2X-2<=pointX)&&(pointX<=ans2X+2))&&((ans2Y-2<=pointY)&&(pointY<=ans2Y+2)))
			{
				ans2 = 1;
			}
			//107,29검은애뿔 +-6
			if(((ans3X-6<=pointX)&&(pointX<=ans3X+6))&&((ans3Y-6<=pointY)&&(pointY<=ans3Y+6)))
			{
				ans3 = 1;
			}
			//116,213쥐밑에 꽃잎 +-6
			if(((ans4X-6<=pointX)&&(pointX<=ans4X+6))&&((ans4Y-6<=pointY)&&(pointY<=ans4Y+6)))
			{
				ans4 = 1;
			}
			//124,122쥐태엽 +-4
			if(((ans5X-4<=pointX)&&(pointX<=ans5X+4))&&((ans5Y-4<=pointY)&&(pointY<=ans5Y+4)))
			{
				ans5 = 1;
			}
		}
		
		if(pointX>400 && pointY<=400)
		{
			//360,223오른쪽아래 공무늬 +-6
			if(((ans1Xr-6<=pointX)&&(pointX<=ans1Xr+6))&&((ans1Y-6<=pointY)&&(pointY<=ans1Y+6)))
			{
				ans1 = 1;
			}
			//45,135고양이손무늬 +-2
			if(((ans2Xr-2<=pointX)&&(pointX<=ans2Xr+2))&&((ans2Y-2<=pointY)&&(pointY<=ans2Y+2)))
			{
				ans2 = 1;
			}
			//107,29검은애뿔 +-6
			if(((ans3Xr-6<=pointX)&&(pointX<=ans3Xr+6))&&((ans3Y-6<=pointY)&&(pointY<=ans3Y+6)))
			{
				ans3 = 1;
			}
			//116,213쥐밑에 꽃잎 +-6
			if(((ans4Xr-6<=pointX)&&(pointX<=ans4Xr+6))&&((ans4Y-6<=pointY)&&(pointY<=ans4Y+6)))
			{
				ans4 = 1;
			}
			//124,122쥐태엽 +-4
			if(((ans5Xr-4<=pointX)&&(pointX<=ans5Xr+4))&&((ans5Y-4<=pointY)&&(pointY<=ans5Y+4)))
			{
				ans5 = 1;
			}
		}
		
		find_sum = ans1 + ans2 + ans3 + ans4 + ans5;
    }
    
    /////////////////배경화면
class MyPanel extends JPanel{
        
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(sbackground, 0, 0, 400,240, null);
            g.drawImage(sbackground_other, 400, 0, 400, 240, null);
            
            if(pause_start == true)
            {
            	g.drawImage(pause, 0, 0,null);
            }
            
        	if(Main.image_num == 1)
        	{
        		sbackground = sbackground1;
        		sbackground_other = sbackground1_other;
        	}else if(Main.image_num == 2) {
        		sbackground = sbackground2;
        		sbackground_other = sbackground2_other;
        	}else if(Main.image_num == 3) {
        		sbackground = sbackground3;
        		sbackground_other = sbackground3_other;
        	}else if(Main.image_num == 4) {
        		sbackground = sbackground4;
        		sbackground_other = sbackground4_other;
        	}
        	
        	if(ans1 == 1)
        	{
        		g.drawOval(ans1X-25, ans1Y-25, 50, 50);
        		g.drawOval(ans1Xr-25, ans1Y-25, 50, 50);
        	}
        	
        	if(ans2 == 1)
        	{
        		g.drawOval(ans2X-25, ans2Y-25, 50, 50);
        		g.drawOval(ans2Xr-25, ans2Y-25, 50, 50);
        	}
        	
        	if(ans3 == 1)
        	{
        		g.drawOval(ans3X-25, ans3Y-25, 50, 50);
        		g.drawOval(ans3Xr-25, ans3Y-25, 50, 50);
        	}
        	
        	if(ans4 == 1)
        	{
        		g.drawOval(ans4X-25, ans4Y-25, 50, 50);
        		g.drawOval(ans4Xr-25, ans4Y-25, 50, 50);
        	}
        	
        	if(ans5 == 1)
        	{
        		g.drawOval(ans5X-25, ans5Y-25, 50, 50);
        		g.drawOval(ans5Xr-25, ans5Y-25, 50, 50);
        	}
            repaint();
        }
    }
}