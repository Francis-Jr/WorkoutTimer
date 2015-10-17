package menu;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class MenuItem {
	
	private String text = "";
	private int x = 0 , y = 0;
	private BufferedImage img = null; //TODO loadImg("pics/missing.png");
	private final Color textColor = Color.white;
	private final Color selectColor = Color.magenta;
	private final Font textFont = new Font("Helvetica",Font.BOLD,30);
	private boolean selected = false;
	
	
	public MenuItem(String a, int b, int c, BufferedImage d){
		text = a;
		x= b;
		y = c;
		img = d;
	}
	
	public MenuItem(String a, int b, int c, String d){
		text = a;
		x= b;
		y = c;
		img = loadImg(d);
	}
	
	public void paint(Graphics g){
		g.drawImage(img, x, y, null);
		g.setColor(selected ? selectColor : textColor);
		g.setFont(textFont);
		g.drawString(text, x+100, y+10);
	}
	
	
	public BufferedImage loadImg(String path){
		BufferedImage tmp = new BufferedImage(1,2,3);
		try
		{
			tmp = ImageIO.read( getClass().getClassLoader().getResource(path) );
		} catch (IOException e) {}
		return tmp;
	}
	
	public void select(){
		selected = true;
	}
	
	public void unselect(){
		selected = false;
	}
	
	public void setSelected(boolean a){
		if(a)	select();
		else 	unselect();
	}
}
