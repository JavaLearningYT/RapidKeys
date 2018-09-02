package com.rapidkeys;
import java.io.*;
import java.util.*;
import javafx.scene.control.Label;
class TextGraber {
	File filepath;
	BufferedReader read;
	long skip;
	boolean newWords;
	String linetext="";
	String word;
	
	public TextGraber(File filelocation)
	{
		filepath = filelocation; 
		System.out.println(filepath);
		try
		{
			read = new BufferedReader(new FileReader(filepath));
		}
		catch(FileNotFoundException fnfe)
		{
			System.out.println("ERROR");
		}
		newWords=true;
	}
	public String getLabel1()
	{

		if (linetext.length()<=1)
		{
			newWords=true;
			
		}
		if (newWords)
		{
			linetext=getLabel2();
			newWords=false;
		}
		if (linetext == null )
		{
			return null;
		}
		if (linetext.contains(" "))
		{
		word = linetext.substring(0,linetext.indexOf(" "));
		word=word.trim();
		linetext=linetext.substring(linetext.indexOf(" "),linetext.length());
		linetext=linetext.trim();
		}
		else 
		{
			word = linetext;
			linetext="";
		}
		System.out.println(linetext+","+word);	
		return	word;
	}
	
	public String getLabel2()
	{
		String line="";
		try{
		try{
		line = read.readLine().trim();
		}
		catch ( IOException iox1 )
		{
			System.out.println("Error reading line in file: TextGraber, getLabel2()"); 
			line = null; // whenever a null is returned to the main program the game is over 
		}
		}
		catch(NullPointerException npe)
		{
			line=null;
		}
		System.out.println("ello");
		return line;
	}

	public String getLabel3()
	{
		String b ="";
		try{
	     String a = read.readLine();
	     b=a;
	     while ( a != null )  // while not end of file
	     {
	       a = read.readLine();
	       if (a!=null)
	       {
	       		b=b+a;	
	       }
	     }
		}
		catch(IOException iox2)
		{
		}
	     return b;
	}
	/*
	public String normalizer(String unchecked) // this will be added later and will remove irregularities from the text for instance different char value commas
	{	
	}
	*/
}
