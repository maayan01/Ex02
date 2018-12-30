package File_format;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.My_GIS_element;
import GIS.My_Meta_data;
import Geom.Point3D;

public class Csv2Kml 
{
	/**
	 * make kml file from GIS data that represent the road
	 * @param gisLayer
	 * @param fileName
	 * */
	public static void makeKmlFile(GIS_layer gisLayer, String fileName) 
	{
		ArrayList<String> kmlTags = new ArrayList<String>();
		String kmlOpening ,kmlFooter ,placeOnMap; 
		My_GIS_element gisElement;
		Point3D p;

		kmlOpening = openingTags();
		kmlTags.add(kmlOpening);

		try
		{
			FileWriter fw = new FileWriter(fileName);
			BufferedWriter bw = new BufferedWriter(fw);
			Iterator<GIS_element> itr = gisLayer.iterator();

			while(itr.hasNext()) 
			{
				gisElement = (My_GIS_element)itr.next();
				p=(Point3D) gisElement.getGeom();
				placeOnMap= placemark(p ,gisElement);
				kmlTags.add(placeOnMap);
			}
			
			kmlFooter = "</Folder></Document></kml>";
			kmlTags.add(kmlFooter);

			String kmlFile = kmlTags.toString();
			bw.write(kmlFile);
			bw.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
//-------------------------------------------help methods-------------------------------------
	/**
	 * return the opening tags of kml file
	 * */
	private static String openingTags ()
	{
		String kmlHeader,kmlStyle,ans=""; 

		kmlHeader = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
				+"<kml xmlns=\"http://www.opengis.net/kml/2.2\">"
				+ "<Document>";

		kmlStyle ="<Style id=\"red\">"
				+"<IconStyle>"
				+"<Icon>"
				+"<href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href>"
				+"</Icon>"
				+"</IconStyle>"
				+"</Style>"

				+"<Style id=\"yellow\">"
				+"<IconStyle>"
				+"<Icon>"
				+"<href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href>"
				+"</Icon>"
				+"</IconStyle>"
				+"</Style>"

				+"<Style id=\"green\">"
				+"<IconStyle>"
				+"<Icon>"
				+"<href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href>"
				+"</Icon>"
				+"</IconStyle>"
				+"</Style>"
				+"<Folder><name>Wifi Networks</name>";
		ans = kmlHeader+kmlStyle;
		return ans;
	}
	
	/**
	 * return the placemark tag of kml file
	 * @param p
	 * @param gisElement
	 * */
	private static String placemark(Point3D p, My_GIS_element gisElement)
	{
		String placeOnMap="";
		My_Meta_data md;

		md = (My_Meta_data)gisElement.getData();
		placeOnMap ="<Placemark>\n"
				+"<name><![CDATA["+md.getName()+" ]]></name>"
				+"<description>"
				+"<![CDATA[BSSID: <b>"+md.getName()+"</b>"
				+"<br/>"
				+"Capabilities: <b>"+md.getRssi()+"</b>"
				+"<br/>"
				+"Frequency: <b>"+md.getRssi()+"</b>"
				+"<br/>"
				+"Timestamp: <b>"+md.getUTC()+"</b>"
				+"<br/>"
				+"Date: <b>"+md.getDate()+"</b>]]>"
				+"</description>\n"
				+"<styleUrl>"+md.getColor()+"</styleUrl>\n"
				+"<Point>\n" 
				+"<coordinates>"+p.x()+","+p.y()+"</coordinates>" 
				+"</Point>\n" 
				+"</Placemark>\n";
		return placeOnMap;
	}
	//-------------------------------------------------------------------------------

}
