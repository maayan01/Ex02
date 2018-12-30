/*
 * i use this code example:https://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
 */

package File_format;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import GIS.GIS_layer;
import GIS.GIS_project;
import GIS.My_GIS_element;
import GIS.My_GIS_layer;
import GIS.My_GIS_project;

public class CSVread 
{
	public static GIS_layer CSVreader(String path_file) 
	{		
		BufferedReader br = null;
		String csvFile = path_file;
		String line = "";
		String cvsSplitBy = ",";
		GIS_layer csvLayer= new My_GIS_layer();
		int i=0;
		
		try 
		{
			br = new BufferedReader(new FileReader(csvFile));
			GIS_project files= new My_GIS_project(); 
			while ((line = br.readLine()) != null) 
			{
				//The data start from the third line;
				if(i>1) 
				{
					String[] userData = line.split(cvsSplitBy);
					csvLayer.add(new My_GIS_element(userData));
				}
				i++;
			}
			files.add(csvLayer);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
        
		finally 
		{
			if (br != null) 
			{
				try 
				{
					br.close();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}
		return csvLayer;
	}
}
