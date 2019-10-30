package utils.service.datacontrol;

import java.util.HashMap;

import utils.thread.mutex.Mutex;



public class Data {
	
	Mutex plate_mutex = new Mutex();
	Mutex place_mutex = new Mutex();
	Mutex mutex = new Mutex();
	
	private HashMap<String, String> plateData = new HashMap<String, String>();
	private HashMap<String, String> placeData = new HashMap<String, String>();
	private String test = "";
	
	public String getTest() throws InterruptedException {
		mutex.lock();
		String returnValue = test;
		mutex.unlock();
		return returnValue;
	}
	public void setTest(String test) throws InterruptedException {
		mutex.lock();
		this.test = test;
		mutex.unlock();
	}
	public HashMap<String, String> getPlateData() throws InterruptedException {
		plate_mutex.lock();
		HashMap<String,	String> returnValue = plateData;
		plate_mutex.unlock();
		return returnValue;
	}
	public void setPlateData(HashMap<String, String> plateData) throws InterruptedException {
		plate_mutex.lock();
		this.plateData = plateData;
		plate_mutex.unlock();
	}
	public HashMap<String, String> getPlaceData() throws InterruptedException {
		place_mutex.lock();
		HashMap<String,	String> returnValue = placeData;
		place_mutex.unlock();
		return returnValue; 
	}
	public void setPlaceData(HashMap<String, String> placeData) throws InterruptedException {
		place_mutex.lock();
		this.placeData = placeData;
		place_mutex.unlock();
	}
}
