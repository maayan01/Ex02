package File_format;

import java.io.BufferedWriter;
import java.io.File;
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
	
//-----------------------------------------------------------------------
	/**
	 * create new kml file on the computer
	 *@ param fileName
	 * */
	private static File newKmlFile(String fileName)
	{
	    String folderName="D:\\Users\\eli\\Documents\\ArielJAVAProjects\\Ex2\\kml_files\\";
		File file = new File( folderName+fileName+".kml");
		// creates the file
		try 
		{
			file.createNewFile();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return file;
	}
//-----------------------------------------------------------------------
	/**
	 * make kml file from GIS data that represent the road
	 * @param gisLayer
	 * @param fileName
	 * */
	public static void makeKmlFile(GIS_layer gisLayer, String fileName) 
	{
		
		File file = newKmlFile(fileName);
		
		String kmlOpening ,kmlFooter ,placeOnMap , allPlacemark="",kmltags=""; 
		My_GIS_element gisElement;
		Point3D p;

		kmlOpening = openingTags();
		kmltags = kmltags +kmlOpening;

		try
		{
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			Iterator<GIS_element> itr = gisLayer.iterator();
             
			while(itr.hasNext()) 
			{
				gisElement = (My_GIS_element)itr.next();
				p=(Point3D) gisElement.getGeom();
				placeOnMap= placemark(p ,gisElement);
				allPlacemark =allPlacemark+ placeOnMap;
			}

			kmltags = kmltags + allPlacemark;
			kmlFooter = "</Folder></Document></kml>";
			kmltags = kmltags + kmlFooter;
	
			bw.write(kmltags);
			wellDone(file);
			bw.close();
		} 
		catch (IOException e) 
		{
			System.out.println("Failed to create kml file ");
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

		kmlHeader = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
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
	/** give the path where kml file saved
	 * @param file*/
	private static void wellDone(File file)
	{
		System.out.println("well done!  kml file is created ");
		System.out.println("The path is "+file.getAbsolutePath());
		System.out.println("*************************************");
	}
	//-------------------------------------------------------------------------------


}
