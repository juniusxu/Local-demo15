package computer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

class CalcControler implements ActionListener
{
	JTextField textField;
	
	//integer1 ,integer2 
	String op1, op2, operator;
	
	String errMsg = "Error";
	
	//the state for now ,begin state = 0
	int state = 0;
	
	CalcControler( JTextField tf) 
	{
		textField = tf;
	}

	public void actionPerformed( ActionEvent e ) 
	{
		String s;
		s = e.getActionCommand();
		
		switch( state )
		{
		case 0:
			inputState0(s);
			break;
		case 1:
			inputState1(s);
			break;
		case 2:
			inputState2(s);
			break;
		case 3:
			inputState3(s);
			break;
		case 4:
			inputState4(s);
			break;
		case 5:
			inputState5(s);
			break;
		default:
			System.out.println( "Unknow state error!" );
			System.exit(1);
		}
	}
	
	private boolean isDigit( String s )
	{
		boolean b;
		b = s.equals("0")||s.equals("1")||s.equals("2")||s.equals("3")||s.equals("4")
			||s.equals("5")||s.equals("6")||s.equals("7")||s.equals("8")||s.equals("9");
		return b;
	}
	private int fN( float fop1 )
	{
		int ruslt=0;
		if ( fop1 == 0 || fop1 == 1 )
		{
			ruslt = 1;
		}
		else
		{
			ruslt = (int) (fop1 * fN(fop1-1) ); 
		}
		return ruslt;
		
	}
	private boolean isOperator(String s)
	{
		return s.equals("+")||s.equals("-")||s.equals("*")||s.equals("/")
				||s.equals("x^2")||s.equals("x^3")||s.equals("x^y")
				||s.equals("sqrt")||s.equals("sin")||s.equals("cos")
				||s.equals("tan")||s.equals("n!")||s.equals("(")||s.equals(")");
	}
	
	//state 0 start
	private void inputState0( String s )
	{
		if ( isDigit(s)||s.equals("+/-")||s.equals(".") )
		{
			state = 2;
			textField.setText("0");
			inputState2(s);
		}
		if ( isOperator(s) )
		{
			op1 = "0";
			operator = s;
			state = 4;
		}
		if ( s.equals("1/x") )
		{
			textField.setText(errMsg);
			state = 1;
		}
	}
	
	//state 1 error
	private void inputState1( String s )
	{
		if ( isDigit(s)||s.equals("+/-")||s.equals(".") )
		{
			textField.setText("0");
			state = 0;
			inputState0(s);
		}
		else
		{
			state = 0;
			textField.setText("0");
		}
	}
	
	//state 2 op1 reading,op1 is being input
	private void inputState2( String s )
	{
		if ( isDigit(s) )
		{
			String text = textField.getText();
			if ( text.equals("0") )
				text = s;
			else
				text = text + s;
			textField.setText(text);
		}
		
		if ( s.equals(".") )
		{
			String text = textField.getText();
			if ( !text.contains(".") )
			{
				text = text + s;
				textField.setText(text);
			}
		}
		if ( s.equals("+/-") )
		{
			String text = textField.getText();
			if ( text.charAt(0) == '-' )
				text = text.substring(1);
			else
				text = "-" + text;
			textField.setText(text);
		}
		if ( isOperator(s)||s.equals("1/x") )
		{
			state = 3;
			op1 = textField.getText();
			inputState3(s);
		}
		if ( s.equals("=") )
		{
			state = 3;
			op1 = textField.getText();
		}
		if ( s.equals("C") )
			textField.setText("0");
		if ( s.equals("Backspace") )
		{
			String text = textField.getText();
			if ( text.length() == 1 )
				textField.setText("0");
			else
				textField.setText( text.substring(0, text.length()-1) );
		}
	}

	//state 3 op1 read only, only op1 was input ,op2 = operator = null
	private void inputState3( String s )
	{
		if ( isDigit(s)||s.equals(".") )
		{
			op1 = "";
			textField.setText("0");
			state = 2;
			inputState2(s);
		}
		if ( s.equals("+/-") )
		{
			state = 2;
			op1 = "";
			inputState2(s);
		}
		
		if ( isOperator(s) )
		{
			operator = s;
			state = 4;
			if (operator.equals("x^2")|| operator.equals("x^3")||operator.equals("sin")
					||operator.equals("cos")||operator.equals("tan")||operator.equals("sqrt")
					||operator.equals("n!"))
			{
				inputState6(s);
			}
		}
		if ( s.equals("1/x") )
		{
			Float fOp1 = new Float(op1);
			if ( fOp1.floatValue() ==0.0 )
			{
				state = 1;
				//error
				textField.setText(errMsg);
			}
			else
			{
				float f = 1.0f/fOp1.floatValue();
				op1 = String.valueOf(f);
				textField.setText(op1);
			}
		}
		
		if ( s.equals("C") || s.equals("Backspace") )
		{
			state = 0;
			textField.setText("0");
		}
	}
	
	//state 2, op1 and operator are read, op2 = null 
	private void inputState4( String s )
	{
		if ( isDigit(s)||s.equals("+/-")||s.equals(".") )
		{
			textField.setText("0");
			state = 5;
			inputState5(s);
		}
		
		if ( isOperator(s))
			operator = s;
		if ( s.equals("1/x") )
		{
			state = 3;
			operator = "";
			inputState3(s);
		}
		if ( s.equals("C") || s.equals("Backspace") )
		{
			state = 0;
			textField.setText("0");
		}
	}
	
	//state5,op2 reading,in reading of op2
	private void inputState5( String s )
	{
		if ( isDigit(s) )
		{
			String text = textField.getText();
			if ( text.equals("0") )
				text = s;
			else
				text = text + s;
			textField.setText(text);
		}
		if ( s.equals(".") )
		{
			String text = textField.getText();
			if ( !text.contains(".") )
			{
				text = text + s;
				textField.setText(text);
			}
		}
		if ( s.equals("+/-") )
		{
			String text = textField.getText();
			if (text.charAt(0) == '-' )
				text = text.substring(1);
			else
				text = '-' + text;
			textField.setText(text);
		}
		
		if ( isOperator(s) )
		{
			op2 = textField.getText();
			Float f1, f2;
			f1 = new Float(op1);
			f2 = new Float(op2);
			float fop1, fop2;
			fop1 = f1.floatValue();
			fop2 = f2.floatValue();
			if ( operator.equals("+") )
				fop1 = fop1 + fop2;
			else if ( operator.equals("-") )
				fop1 = fop1 - fop2;
			else if ( operator.equals("*") )
				fop1 = fop1 * fop2;
			else if ( operator.equals("/") )
			{
				if ( fop2 != 0.0f )
					fop1 = fop1 / fop2;
				else
				{
					state = 1;
					textField.setText(errMsg);
					return;
				}
			}
			else
			{
				System.out.println("Unknown operator error!");
				state = 1;
				textField.setText(errMsg);
				return;
			}
			
			//here we got good calculating result
			op1 = String.valueOf(fop1);
			textField.setText(op1);
			operator = s;
			state = 4;
		}
		if ( s.equals("1/x") )
		{
			op1 = textField.getText();
			state = 3;
			inputState3(s);
		}
		if ( s.equals("=") )
		{
			op2 = textField.getText();
			Float f1, f2;
			f1 = new Float(op1);
			f2 = new Float(op2);
			float fop1, fop2;
			fop1 = f1.floatValue();
			fop2 = f2.floatValue();
			if ( operator.equals("+") )
				fop1 = fop1 + fop2;
			else if ( operator.equals("-") )
				fop1 = fop1 - fop2;
			else if ( operator.equals("*") )
				fop1 = fop1 * fop2;
			else if ( operator.equals("/") )
			{
				if ( fop2 != 0.0f )
					fop1 = fop1 / fop2;
				else
				{
					state = 1;
					textField.setText(errMsg);
					return;
				}
			}	
			else if ( operator.equals("x^y") )
				fop1 = (float) Math.pow( fop1, fop2 );
			else
			{
				System.out.println("Unknown operator error!");
				state = 1;
				textField.setText(errMsg);
				return;
			}
			
			//here we got good calculating result
			op1 = String.valueOf(fop1);
			textField.setText(op1);
			state = 3;
		}
		if ( s.equals("C") )
		{
			state = 0;
			textField.setText("0");
		}
		if ( s.equals("Backspace") )
		{
			String text = textField.getText();
			if ( text.length() == 1 )
				textField.setText("0");
			else
				textField.setText( text.substring(0, text.length()-1) );
		}
	}
	
	//state6, new calculation
	private void inputState6( String s )
	{
		Float f1;
		f1 = new Float(op1);
		float fop1;
		fop1 = f1.floatValue();
		if ( operator.equals("sqrt") )
		{
			if ( fop1 < 0 )
			{
				textField.setText(errMsg);
				return;
			}
			else
				fop1 = (float) Math.sqrt( fop1 );
		}
		else if ( operator.equals("x^2") )
			fop1 = fop1 * fop1;

		else if ( operator.equals("x^3") )
			fop1 = fop1 * fop1 * fop1;
		
		else if ( operator.equals("sin") )
			fop1 = (float) Math.sin( fop1 );
			
		else if ( operator.equals("cos") )
			fop1 = (float) Math.cos( fop1 );
		
		else if ( operator.equals("tan") )
			fop1 = (float) Math.tan( fop1 );
		
		else if( operator.equals("n!") )
			fop1 = this.fN(fop1);
		
		op1 = String.valueOf(fop1);
		textField.setText(op1);
	}

}
