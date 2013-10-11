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
	
			shell.setText("����һ������¼�����");
			b.setText("�뵥����");
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
				t.setText("��걻����");
			}
			public void mouseUp(MouseEvent e){
				t.setText("��걻�ͷ�");
			}
            public void mouseDoubleClick(MouseEvent e){
            	t.setText("��걻˫��");
			}
		}
		class MouseTE implements  MouseTrackListener{
			public void mouseEnter(MouseEvent e){
				t.setText("�������ı�����");
			}
			public void mouseExit(MouseEvent e){
				t.setText("����뿪�ı�����");
			}
			public void mouseHover(MouseEvent e){
				t.setText("������ı�����");
			}
		}
		
	}