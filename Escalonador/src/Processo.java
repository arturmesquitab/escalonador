import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.JFileChooser;

public class Processo 
{
	public enum Prioridade
	{
		LOW, MEDIUM, HIGH
	}
	private Date TA; //TimeArrived
	private int ID;
	private float BurstTime;
	private Prioridade p;
	public Processo(Date tA, int iD, float burstTime, Prioridade p) {
		this.TA = tA;
		this.ID = iD;
		this.BurstTime = burstTime;
		this.p = p;
	}
	public Date getTA() {
		return TA;
	}
	public int getID() {
		return ID;
	}
	public float getBurstTime() {
		return BurstTime;
	}
	public Prioridade getP() {
		return p;
	}
	public void run() throws InterruptedException
	{
		Thread.sleep((long) BurstTime);
	}
}
