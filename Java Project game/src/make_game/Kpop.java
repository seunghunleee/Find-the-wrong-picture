package make_game;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Kpop extends Thread{
	
 private Player player;
 private File file; 
 private FileInputStream fis;
 private BufferedInputStream bis;
 private boolean lop;
 	public  Kpop(String name, boolean lop) {
	
 			try {	
 				file= new File("./bin/make_game/"+name);
 				fis = new FileInputStream(file);
 				bis = new BufferedInputStream(fis);
 				player=new Player(bis);
 				}catch(Exception e) {
 					System.out.println(e.getMessage());
 				}
 	}
 	public void close() {
 		lop=false;
 		player.close();	
 	}
 @Override
 	public void run(){
	 try {
		do {
			player.play();
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			player=new Player(bis);
		 }while(lop);
	 	}catch(Exception e) {
	 		System.out.println(e.getMessage());
	 	}
 	} 
}
