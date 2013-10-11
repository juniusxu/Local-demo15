package demo15;


import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.events.*;
public class MouseEv {
		Display display=new Display();
		Shell shell=new Shell(display);
		Button b=new Button(shell,SWT.CENTER);
		Text t=new Text(shell,SWT.CENTER);
		public MouseEv(){
	
			shell.setText("这是一个鼠标事件例子");
			b.setText("请单击我");
			b.pack();
			
			t.setBounds(30,30,100,200);
		
			MouseTE mte=new MouseTE();
			t.addMouseTrackListener(mte);
			MouseE me=new MouseE();
			b.addMouseListener(me);
			MouseM mm=new MouseM();
			shell.addMouseMoveListener(mm);
			shell.setSize(300,300);
			//shell.pack();
			shell.open();
		   while(!shell.isDisposed()){
			   if(!display.readAndDispatch())
				   display.sleep();
			   }
			   display.dispose();
		   }	
		public static void main(String[] args) {
			new MouseEv();			
		}
		class MouseM implements MouseMoveListener{
			public void mouseMove(MouseEvent e){
				int x=e.x;
				int y=e.y;
				b.setBounds(x, y, 60, 40);
			}
		}
		class MouseE extends MouseAdapter{
			public void mouseDown(MouseEvent e){
				t.setText("鼠标被按下");
			}
			public void mouseUp(MouseEvent e){
				t.setText("鼠标被释放");
			}
            public void mouseDoubleClick(MouseEvent e){
            	t.setText("鼠标被双击");
			}
		}
		class MouseTE implements  MouseTrackListener{
			public void mouseEnter(MouseEvent e){
				t.setText("鼠标进入文本区域");
			}
			public void mouseExit(MouseEvent e){
				t.setText("鼠标离开文本区域");
			}
			public void mouseHover(MouseEvent e){
				t.setText("鼠标在文本区域");
			}
		}
		
	}