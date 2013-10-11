package demo15;
interface China{
	public static final String NATIONAL="China";
	public static final String AUTHOR="xj";
	public void sayChina();
	public String sayHello(String name,int age);
}
public class Person implements China{
	private String name;
	private int age;
	public Person(){
		
	}
	public Person(String name,int age){
		this.setName(name);
		this.setAge(age);
	}
	public void tell(){
		System.out.println("姓名："+name+"\t年龄:"+age);
		
	}
	public String getName(){
		return name;		
	}
	public void setName(String n){
		name=n;
	}
	public int getAge(){
		return age;
	}
	public void setAge(int n){
		if(n>0&&n<200)
			age=n;
	}
	public void sayChina(){
		System.out.println("作者:"+AUTHOR+"，国籍:"+NATIONAL);
	}
	public String sayHello(String name,int age){
		return name+"你好,我今年"+age+"岁了。";
	}
}
