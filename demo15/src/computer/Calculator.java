package computer;
public class Calculator
{
	/**
	 * @param args
	 * @Project Calculator
	 * @time 2012-01-09 22:33:09
	 * @Author YUZM
	 */
	public static void main(String[] args)
	{
		// create application frame
		CalculatorFrame frame = new CalculatorFrame();

		//show frame
		frame.setBounds(100,50,390,225);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLocation(400, 200);
	}
}
