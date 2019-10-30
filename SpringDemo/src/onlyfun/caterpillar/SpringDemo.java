package onlyfun.caterpillar;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class SpringDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = new FileSystemXmlApplicationContext("beans-config.xml");
		
		HelloBean hello = (HelloBean) context.getBean("helloBean");
		System.out.println(hello.getHelloWord());
		System.out.println(hello.getAge());
		System.out.println(hello.getScore());
		System.out.println(hello.getNum());
	}
}
