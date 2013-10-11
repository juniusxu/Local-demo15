package demo15;

import java.lang.reflect.Method;

public class Invoke {

	public static void main(String[] args) {
		Class<?> c1=null;
		Object obj=null;
		try{
			c1=Class.forName("Person");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		try{
			obj=c1.newInstance();
		}catch(InstantiationException e){
			e.printStackTrace();
		}catch(IllegalAccessException e){
			e.printStackTrace();
		}
		setter(obj,"name","小军",String .class);
		setter(obj,"age",30,int.class);
		System.out.print("姓名:");
		getter(obj,"name");
		System.out.print("年龄:");
		getter(obj,"age");
	}
	public static void setter(Object obj,String att,Object value,Class<?> type){
		try{
			Method met=obj.getClass().getMethod("set"+initStr(att),type);
			met.invoke(obj, value);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void getter(Object obj,String att){
		try{
			Method met=obj.getClass().getMethod("set"+initStr(att));
			System.out.println(met.invoke(obj));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static String initStr(String old){
		String str=old.substring(0,1).toUpperCase()+old.substring(1);
		return str;
	}
};
class Person2{
	private String name;
	private int age;
	public Person2(){
		
	}
	public Person2(String name,int age){
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

}