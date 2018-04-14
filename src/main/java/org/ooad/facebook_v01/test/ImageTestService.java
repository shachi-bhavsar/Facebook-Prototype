package org.ooad.facebook_v01.test;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.ooad.facebook_v01.database.DatabaseConnection;

public class ImageTestService {
	
	public int storeImageService(InputStream inputStream, String filename) throws IOException {
		//System.out.println("Store Image Function!");
		String filepath = "/home/awais/Documents/M.Tech/II - Sem/JaxRS/facebook_v01/src/main/webapp/images/";
		OutputStream outputStream = null;
		String qualifiedUploadFilePath = filename;
		try {
			outputStream = new FileOutputStream(new File(filepath+qualifiedUploadFilePath));
			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
			outputStream.flush();
		} 
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		finally{
			outputStream.close();
		}
		
		int status = insertToDatabase(qualifiedUploadFilePath);
		return status;
	}

	private int insertToDatabase(String qualifiedUploadFilePath) {
		//System.out.println("Insert Image Function!");
		DatabaseConnection dbconn = new DatabaseConnection();
		if(! dbconn.isStatus()){
			return 10;
		}
		String query = "update IMAGETEST set imgurl = '"+ qualifiedUploadFilePath +"' where mobile = 9808897713";
		try{
			dbconn.getStmt().executeUpdate(query);
			dbconn.getConn().close();
		}
		catch(Exception e){
			System.out.print(e.getMessage());
			return 1;
		}
		return 0;
	}
}
