package computer;

import java.awt.Composite;
import java.awt.Label;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Calculator {
	private Display display;
	private Shell shell;
	private Composite com1;
	private Composite com2;
	private Composite com3;
	private Label text;
	private Double num1=null;
	private Double num2=null;
	private String operator=null;
	private boolean numberEnd=true;
	String str[][]={
			{"7","8","9","/","sqrt"},
			{"4","5","6","*","%"},
			{"1","2","3","-","1/x"},
			{"0","+/-",".","+","="}
	};
	public Calculator(){
		display=new Display();
		shell=new Shell(display,SWT.NONE|SWT.MIN);
		paintShell();//绘制主窗口
		shell.open();
		while(!shell.isDisposed()){
			if(!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
	private void paintShell() {
		// TODO 自动生成的方法存根
		
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根

		new Calculator();
	}

}
