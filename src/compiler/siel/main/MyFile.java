package compiler.siel.main;

import java.io.*;
import java.util.*;

public class MyFile {

    public MyFile( String filename ) {
        this.filename = filename;
    }

    public String getName() {
        return filename;
    }

    private String filename;
    public static final int
       ok_e = 0,
       do_not_exist_e = 1,
       cannot_be_read_e = 2,
       read_error_e = 3,
       write_error_e = 4,
       close_error_e = 5;

    private int error;

    public int getError() {
        return error;
    }

    public boolean writeFile(char toWrite[])  {
    	FileWriter outFile;
    	boolean ret = true;

    	try {
			outFile = new FileWriter(filename);
		} catch (IOException e) {
			return false;
		}
		try {
			int size = toWrite.length;
			if ( toWrite[size-1] == '\0' )
				size--;
			outFile.write(toWrite, 0, size);
		} catch (IOException e) {
			ret = false;
		}
		try {
			outFile.close();
		} catch (IOException e) {
			ret = false;
		}
		return ret;
    }

    public ArrayList<String> readLinesFile() {
    	ArrayList<String> list = new ArrayList<String>();

    	FileReader fr;
    	String s;
    	BufferedReader br;
		try {
			fr = new FileReader(filename);
	    	br = new BufferedReader(fr);
	    	while( (s = br.readLine()) != null )
	    		list.add(s);

         	fr.close();
		} catch (IOException e) {
			return null;
		}
		return list;

    }

    @SuppressWarnings("resource")
	public char []readFile() {
        FileReader stream;
        int numChRead;

        error = ok_e;

        File file = new File(filename);
        if ( ! file.exists() ) {
           error = do_not_exist_e;
           return null;
        }
        else if ( ! file.canRead() ) {
           error = cannot_be_read_e;
           return null;
         }

         try {
             stream = new FileReader(file);
         } catch ( FileNotFoundException e ) {
            error = do_not_exist_e;
            return null;
         }
                // one more character for '\0' at the end that will be added by the
                // compiler
         char []input = new char[ (int ) file.length() + 1 ];

         try {
            numChRead = stream.read( input, 0, (int ) file.length() );
         } catch ( IOException e ) {
            error = cannot_be_read_e;
            return null;
         }

         if ( numChRead != file.length() ) {
            error = read_error_e;
            return null;
         }
         try {
            stream.close();
         } catch ( IOException e ) {
            error = close_error_e;
            return null;
         }
         return input;
    }

}