package onlyfun.caterpillar;

import java.util.Date;

public class HelloBean {

	private String helloWord;
	private Integer age;
	private Integer score;
	private int num;
	
	public HelloBean() {
		super();
	}

	public HelloBean(String helloWord, Integer age, Integer score, int num) {
		super();
		this.helloWord = helloWord;
		this.age = age;
		this.score = score;
		this.num = num;
	}

	public String getHelloWord() {
		return helloWord;
	}

	public void setHelloWord(String helloWord) {
		this.helloWord = helloWord;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

}
