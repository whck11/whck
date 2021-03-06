package com.whck.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;

import com.whck.dmo.Dc;
import com.whck.dmo.Device;
import com.whck.dmo.Zone;

@Service
public class CommandResolverImpl implements CommandResolver {

	@Override
	public String deResolve(Dc dc) {
		return null;
	}

	@Override
	public Dc resolve(String command) {
		int index = command.indexOf("E8E8E8E8E8E8E8E8");
		index += 16;
		String c = command.substring(index, index += 3);
		Dc dc = new Dc();
		Device device = new Device();
		String id = command.substring(index, index += 11);
		String longitude = command.substring(index, index += 3);
		String latitude = command.substring(index, index += 3);
		String lenth = command.substring(index, index += 2);
		int end = command.indexOf("ABABABABABABABAB");
		String data = command.substring(index, end);
		Zone zone=new Zone();
		zone.setLongitude(longitude);
		zone.setLatitude(latitude);
		dc.setId(id);
		dc.setZone(zone);
		List<Device> devices=new ArrayList<>();
		devices.add(device);
		dc.setDevices(devices);
		if (c.equals("D00001")) {
			File file=new File(new Random().nextInt()+"c.bin");
			file.setWritable(true);
			FileWriter writer=null;
			try {
				writer=new FileWriter(file);
				writer.write(data);
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				if (null!=writer) {
					try {
						writer.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return dc;
	}

	@Override
	public Zone resoleZoneFromXml(File file) {
		Zone zone=new Zone();
		Document document = null;
		SAXReader saxReader = new SAXReader();
		try {
			document = saxReader.read(file);
			Element root= document.getRootElement();
			zone.setLatitude(root.elementText("latitude"));
			zone.setLongitude(root.elementText("longtitude"));
			zone.setArea(Double.valueOf(root.elementText("area")));
			zone.setZoneName(root.elementText("zoneName"));
		} catch (DocumentException e) {
			e.printStackTrace();
		} 
		return zone;
	}
}
