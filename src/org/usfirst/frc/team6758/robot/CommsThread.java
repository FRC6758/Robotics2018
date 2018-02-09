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
import me.devjoe.frc.team6758.vision.gson.Identified;
import me.devjoe.frc.team6758.vision.gson.SwProto;
import me.devjoe.frc.team6758.vision.gson.VisRep;

public class CommsThread implements Runnable {
	
	private InputStream in;
	private OutputStream out;
	private StringBuilder builder = new StringBuilder();
	private boolean jetsonProto = false;
	private boolean run = false;

	public CommsThread() {
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
	
	public void transmitResults(MatOfPoint[] m) {
		if(!jetsonProto) {
			System.out.println("Not in protocol");
			return;
		}
		ArrayList<Double[]> points = new ArrayList<Double[]>();
		ArrayList<VisRep.VisObj> objects = new ArrayList<VisRep.VisObj>();
		VisRep rep = new VisRep();
		for(MatOfPoint p : m) {
			Point[] pts = p.toArray();
			for(Point pt : pts) {
				points.add(new Double[] {pt.x, pt.y});
			}
			Rect object = Imgproc.boundingRect(p);
			VisRep.VisObj obj = rep.new VisObj();
			obj.height = object.height;
			obj.width = object.width;
			obj.x = object.x;
			obj.y = object.y;
			objects.add(obj);
		}
		if(points.size() != 0 || objects.size() != 0) System.out.println(points.size()+" // "+objects.size());
		rep.objects = objects.toArray(new VisRep.VisObj[] {});
		rep.points = points.toArray(new Double[][] {});
		if(points.size() != 0 || objects.size() != 0) send("visrep", rep);
	}

}
