package utils.service.datacontrol;

import java.util.HashMap;

import utils.thread.mutex.Mutex;



public class Data {
	
	Mutex enterPlate_mutex = new Mutex();
	Mutex exitPlate_mutex = new Mutex();
	Mutex place_mutex = new Mutex();
	Mutex placeInfo_mutex = new Mutex();
	Mutex mutex = new Mutex();
	
	private HashMap<String, String> enterPlateData = new HashMap<String, String>();
	private HashMap<String, String> exitPlateData = new HashMap<String, String>();
	private HashMap<String, String> placeData = new HashMap<String, String>();
	private byte placeInfo = 0x00;
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
	public HashMap<String, String> getEnterPlateData() throws InterruptedException {
		enterPlate_mutex.lock();
		HashMap<String, String> returnValue = enterPlateData;
		enterPlate_mutex.unlock();
		return returnValue;
	}
	public void setEnterPlateData(HashMap<String, String> enterPlateData) throws InterruptedException {
		enterPlate_mutex.lock();
		this.enterPlateData = enterPlateData;
		enterPlate_mutex.unlock();
	}
	public HashMap<String, String> getExitPlateData() throws InterruptedException {
		exitPlate_mutex.lock();
		HashMap<String, String> returnValue = exitPlateData;
		exitPlate_mutex.unlock();
		return returnValue;
	}
	public void setExitPlateData(HashMap<String, String> exitPlateData) throws InterruptedException {
		exitPlate_mutex.lock();
		this.exitPlateData = exitPlateData;
		exitPlate_mutex.unlock();
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
	public byte getPlaceInfo() throws InterruptedException {
		placeInfo_mutex.lock();
		byte returnValue = placeInfo;
		placeInfo_mutex.unlock();
		return returnValue;
	}
	public void setPlaceInfo(byte placeInfo) throws InterruptedException {
		placeInfo_mutex.lock();
		this.placeInfo = placeInfo;
		placeInfo_mutex.unlock();
	}
	
}
