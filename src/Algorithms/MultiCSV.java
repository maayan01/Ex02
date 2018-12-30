package Algorithms;

import java.io.File;
import java.io.IOException;

import GIS.GIS_layer;
import GIS.GIS_project;
import GIS.My_GIS_project;
import File_format.CSVread;
import File_format.Csv2Kml;

public class MultiCSV 
{
	String path = "/Ex2/csv_doc/";
	final File fileRef = new File(path);

	/***
	 * read all csv file from the folder 
	 * and create var that contain all roads that was done 
	 * @param folder
	 * @return manyRoads ;
	 */
	public static GIS_project multiCsv(final File folder ) 
	{
		String fileName="";
		GIS_layer road;
		GIS_project manyRoads = new My_GIS_project();

		for (final File file : folder.listFiles()) 
		{
			//checks whether the file denoted by this abstract pathname is a directory.
			if (file.isDirectory()) 
			{
				multiCsv(file);
			} 
			// this is kind of some file
			else 
			{
				fileName = file.getName();
				//checks if the file is from type csv
				if(fileName.endsWith(".csv")) 
				{
					road = CSVread.CSVreader(file.getPath());
					// enter another road for the set of roads
					manyRoads.add(road);
				}
			}
		}
		return manyRoads;
	}
	//----------------------------------------------------------------
	

}
