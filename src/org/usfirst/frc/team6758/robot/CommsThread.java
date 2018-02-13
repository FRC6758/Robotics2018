package org.usfirst.frc.team6758.robot;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import me.devjoe.frc.team6758.vision.gson.AbstractPacket;
import me.devjoe.frc.team6758.vision.gson.DataPacket;
import me.devjoe.frc.team6758.vision.gson.Ident;
import me.devjoe.frc.team6758.vision.gson.VisRep;
import me.devjoe.frc.team6758.vision.gson.VisRep.VisObj;

public class CommsThread implements Runnable {
	
	private InputStream in;
	private OutputStream out;
	private StringBuilder builder = new StringBuilder();
	private boolean jetsonProto = false;
	private boolean run = false;
	public Robot spawner;

	public CommsThread(Robot spawner) {
		try {
			Robot.sock.connect(new InetSocketAddress("10.67.58.12", 8703));
		} catch (IOException e1) {
			System.out.println("Jetson is not running or could not be detected...");
			return;
		};
		try {
			in = Robot.sock.getInputStream();
			out = Robot.sock.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.spawner = spawner;
		run = true;
	}
	
	@Override
	public void run() {
		while(run) {
			if(Thread.interrupted()) break;
			int s = -1;
			try {
				s = in.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(s == -1) {
				System.out.println("Connection with server lost.");
				break;
			}
			byte b = (byte)s;
			builder.append(new String(new byte[] {b}, StandardCharsets.UTF_8));
			if(builder.toString().endsWith("\n")) {
				exec(builder.toString());
				builder = new StringBuilder();
			}
		}
	}
	
	public void exec(String str) {
		System.out.println("IN: "+str);
		Gson gson = new Gson();
		DataPacket packet = gson.fromJson(str, DataPacket.class);
		String i = packet.i;
		JsonObject d = gson.toJsonTree(packet.d).getAsJsonObject();
		switch(i) {
		case "hello":
			send("ident", new Ident());
			break;
		case "visrep":
			System.out.println("Reported");
		}
	}
	
	public void send(String i, AbstractPacket d) {
		DataPacket packet = new DataPacket();
		Gson gson = new Gson();
		packet.d = d;
		packet.i = i;
		byte[] bytes = (gson.toJson(packet)+"\n").getBytes();
		try {
			out.write(bytes, 0, bytes.length);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(bytes.length);
	}
	
	public void dataIn(Double[][] points, VisObj[] objects) {
		ArrayList<Point> pts = new ArrayList<Point>();
		for(Double[] p : points) {
			Point po = new Point();
			po.x = p[0];
			po.y = p[1];
			pts.add(po);
		}
		ArrayList<Rect> rts = new ArrayList<Rect>();
		for(VisObj o : objects) {
			Rect r = new Rect();
			r.height = o.height;
			r.width = o.width;
			r.x = o.x;
			r.y = o.y;
			rts.add(r);
		}
		spawner.autonData(pts.toArray(new Point[] {}), rts.toArray(new Rect[] {}));
	}

}
