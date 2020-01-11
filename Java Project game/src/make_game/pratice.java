package make_game;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class pratice extends JFrame implements ActionListener{
	
	private Image pause = new ImageIcon(Main.class.getResource("../images/pause.jpg")).getImage(); // 400x240;
	
	private Image sbackground = new ImageIcon(Main.class.getResource("../images/picture4.png")).getImage(); // 400x240;
	private Image sbackground_other = new ImageIcon(Main.class.getResource("../images/picture4_change.png")).getImage(); // 400x240
	private Image sbackground1 = new ImageIcon(Main.class.getResource("../images/picture1.png")).getImage(); // 400x240
    private Image sbackground1_other = new ImageIcon(Main.class.getResource("../images/picture1_change.png")).getImage(); // 400x240
    private Image sbackground2 = new ImageIcon(Main.class.getResource("../images/picture2.png")).getImage(); // 240x320
    private Image sbackground2_other = new ImageIcon(Main.class.getResource("../images/picture2_change.png")).getImage(); // 240x320
    private Image sbackground3 = new ImageIcon(Main.class.getResource("../images/picture3.png")).getImage(); // 240x320
    private Image sbackground3_other = new ImageIcon(Main.class.getResource("../images/picture3_change.png")).getImage(); // 240x320
    private Image sbackground4 = new ImageIcon(Main.class.getResource("../images/picture4.png")).getImage(); // 240x320
    private Image sbackground4_other = new ImageIcon(Main.class.getResource("../images/picture4_change.png")).getImage(); // 240x320
    
    //private boolean ans1 = false , ans2 = false , ans3 = false , ans4 = false;
    public static int ans1 = 0 , ans2 = 0 , ans3 = 0 , ans4 = 0 , ans5=0; 
    public static int pointX = 0 , pointY = 0;
    private boolean pause_start = false;
    
    private int checknum=0,remain_num=5,find_sum=0;
    
    private JButton exit_bt = new JButton("EXIT");
    private JButton pause_bt = new JButton();
    private JButton restart_bt = new JButton();
    
    private JLabel label_check = new JLabel();
    private JLabel label_checknum = new JLabel();
    
    MyPanel panel = new MyPanel();
    
    public pratice()
    {    	
    	ans1 = 0;
    	ans2 = 0;
    	ans3 = 0;
    	ans4 = 0;
    	ans5 = 0;
    	this.setTitle("틀린그림찾기");
    	this.setSize(800, 600);
    	this.setVisible(true);
    	this.setResizable(false);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setLocationRelativeTo(null);
    	
    	this.add(panel);
    	panel.setLayout(null);
    	
    	label_checknum.setFont(new Font("바탕", Font.BOLD, 20));
    	label_checknum.setBounds(0, 450, 800, 20);
    	panel.add(label_checknum);
    	
    	label_check.setFont(new Font("바탕", Font.BOLD, 20));
        label_check.setBounds(0, 350, 800, 20);
        panel.add(label_check);
        
        label_check.setText("클릭 가능 횟수 : " + 10 + "회 // 클릭 횟수 : " + 0 + "회 // 남은 그림 : " + 5 + "개 // 찾은 갯수 : " + 0 +"개");
        panel.addMouseListener(new java.awt.event.MouseAdapter() {
    		public void mouseClicked(java.awt.event.MouseEvent e) {
    			pointX = e.getX();
    			pointY = e.getY();
    			
    			label_checknum.setText("X : " + pointX + " Y : " + pointY);
    			
    			if(pointY<240)
	    		{
	    			checknum++;Main.cc++;
	    	        
					if(pointX > 400 && pointY<=400)
					{
											
						//444,180 나비
						if(((444-8<=pointX)&&(pointX<=444+8))&&((180-8<=pointY)&&(pointY<=180+8)))
						{
							ans1 = 1;
						}
						
						//696,231 꽃
						if(((696-8<=pointX)&&(pointX<=696+8))&&((231-8<=pointY)&&(pointY<=231+8)))
						{
							ans2 = 1;
						}
					}
					
					if(pointX<400 && pointY<=400)
					{
						//044,180 나비
						if(((44-8<=pointX)&&(pointX<=44+8))&&((180-8<=pointY)&&(pointY<=180+8)))
						{
							ans1 = 1;
						}
						
						//296,231 꽃
						if(((296-8<=pointX)&&(pointX<=296+8))&&((231-8<=pointY)&&(pointY<=231+8)))
						{
							ans2 = 1;
						}
					}
					find_sum = ans1 + ans2 + ans3 + ans4 + ans5;
					remain_num = 5 - ans1 - ans2 - ans3 - ans4 - ans5;
					label_check.setText("클릭 가능 횟수 : " + 10 + "회 // 클릭 횟수 : " + checknum + "회 // 남은 그림 : " + remain_num + "개 // 찾은 갯수 : " + find_sum +"개");
					/*if(remain_num == 3)
					{
						JOptionPane.showMessageDialog(null, "성공 입니다!! \n클릭 한 횟수 : " + checknum,"축하합니다!!",JOptionPane.PLAIN_MESSAGE);
			    		dispose();
					}else if(checknum == 10) {
						JOptionPane.showMessageDialog(null, "실패 입니다!! \n클릭 힛수 초과입니다. 다시 도전하세요.","실패입니다!!",JOptionPane.PLAIN_MESSAGE);
			    		dispose();
					}*/
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
    		dispose();
    	}
    	if(e.getActionCommand() == "pause")
    	{
    		pause_start = true;
    		panel.remove(pause_bt);
    		panel.add(restart_bt);
    	}
    	if(e.getActionCommand() ==  "restart")
    	{
    		pause_start = false;
    		panel.remove(restart_bt);
    		panel.add(pause_bt);
    	}
    }
    
    // 시간초
    public void sec() {
        JLabel tl = new JLabel();
        Font f = new Font("바탕", Font.BOLD, 20);
        tl.setBounds(300, 340, 400, 20);
        tl.setFont(f);
        panel.add(tl);
    	int k=0;
	    while(true)
	    {
	    	tl.setText("경과시간 : "+ k + "초");
		   	try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		   	k++;
	    }
    }
    
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
        		g.drawOval(444-25, 180-25, 50, 50);
        		g.drawOval(444-25-400, 180-25, 50, 50);
        	}
        	
        	if(ans2 == 1)
        	{
        		g.drawOval(696-25, 231-25, 50, 50);
        		g.drawOval(696-25-400, 231-25, 50, 50);
        	}
            repaint();
        }
    }
}