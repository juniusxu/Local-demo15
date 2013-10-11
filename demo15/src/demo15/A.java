package demo15;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
public class A {

	public static void main(String[] args) {
	Display display=new Display();
	Shell shell=new Shell(display);
	shell.setText("hello");
	shell.setSize(800,500);
	Text helloText=new Text(shell,SWT.CENTER);
	helloText.setText("hello world");
	helloText.pack();
	shell.pack();
	shell.open();
	while(!shell.isDisposed()){
		if(!display.readAndDispatch())
			display.sleep();
	}
	
	display.dispose();
	System.out.println("\n");
;;;
	
}//main
}
