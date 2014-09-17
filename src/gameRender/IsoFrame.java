package gameRender;


import java.awt.Color;
import javax.swing.JFrame;

public class IsoFrame extends JFrame  {
		
		private final IsoCanvas canvas;
		
		public IsoFrame(){
			super("  ISO-TEST");		
			canvas = new IsoCanvas();
			add(canvas); 
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			pack();
			setResizable(false); //prevents board from being resizable
		}
		public IsoCanvas canvas() {
			return canvas;
		}

}
