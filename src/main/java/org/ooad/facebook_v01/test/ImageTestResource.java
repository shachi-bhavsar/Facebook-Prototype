package org.ooad.facebook_v01.test;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.JSONArray;
import org.ooad.facebook_v01.database.Convertor;
import org.ooad.facebook_v01.database.DatabaseConnection;

@Path("/imageTest")
public class ImageTestResource {
	
	private ImageTestService imageTestService = new ImageTestService();
	
	
	@Path("/getMessage")
	@Produces(MediaType.TEXT_PLAIN)
	@GET
	public String getMethod(){
		return "Hello Get";
	}
	
	@Path("/getUserDetails")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Response getDetails() throws Exception{
		DatabaseConnection dbconn = new DatabaseConnection();
		if(! dbconn.isStatus()){
			return Response.status(210).entity("DBError").build();
		}
		String query = "SELECT * FROM IMAGETEST WHERE MOBILE = 9808897713";
		ResultSet rs;
		rs = dbconn.getStmt().executeQuery(query);
		JSONArray jsonarray = Convertor.convertToJSON(rs);
		dbconn.getConn().close();
		return Response.status(201).entity(jsonarray.toString()).build();
	}
	
	
	@POST
	@Path("/pictureUpload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public void uploadImage(@FormDataParam("uploadFile") InputStream fileInputStream, @FormDataParam("uploadFile") FormDataContentDisposition fileFormDataContentDisposition) {
		//System.out.println("I came to upload function!!");
		
		@SuppressWarnings("unused")
		int returnvalue=1;
		String filename = "abc.jpg";

		try {
			filename = fileFormDataContentDisposition.getFileName();
			returnvalue = imageTestService.storeImageService(fileInputStream, filename);
		} 
		catch(IOException ioe){
			ioe.printStackTrace();
		}
		finally{
		}
	}
}