package org.ooad.facebook_v01.resource;

import java.sql.ResultSet;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.ooad.facebook_v01.database.Convertor;
import org.ooad.facebook_v01.database.DatabaseConnection;

@Path("/myTestResource")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TestResource {
	
//	@Path("/fetchList/{userid}/{friendid}")
//	@GET
//	public Response fetchList(@PathParam("userid") String userid, @PathParam("friendid") String friendid) throws Exception{
//		DatabaseConnection dbconn = new DatabaseConnection();
//		ResultSet rs;
//		//System.out.println(userid + ", " + friendid);
//		String query = "SELECT distinct e.event_name, e.event_photo, e.event_pk FROM EVENTLIST el, EVENT e where el.eventlist_status = 'Going' and el.eventlist_user = "+userid+" and e.event_pk = el.event_pk and el.eventlist_friend = " + friendid;
//		rs = dbconn.getStmt().executeQuery(query);
//		JSONArray jsonarray = Convertor.convertToJSON(rs);
//		return Response.status(201).entity(jsonarray.toString()).build();
//	}
	
	@Path("/fetchGroupList/{userid}/{friendid}")
	@GET
	public Response fetchGroupList(@PathParam("userid") String userid, @PathParam("friendid") String friendid) throws Exception{
		DatabaseConnection dbconn = new DatabaseConnection();
		ResultSet rs;
		//System.out.println(userid + ", " + friendid);
		String query = "SELECT g.groupdetails_id,g.groupdetails_name FROM GROUPDETAILS g,GROUPMEMBERS gm,GROUPMEMBERS gm2 WHERE g.groupdetails_id = gm.groupmembers_groupid AND gm.groupmembers_groupid = gm2.groupmembers_groupid AND gm.groupmembers_status=1 AND gm2.groupmembers_status=1 AND gm.groupmembers_memberid ="+userid+" and gm2.groupmembers_memberid ="+friendid;
		rs = dbconn.getStmt().executeQuery(query);
		JSONArray jsonarray = Convertor.convertToJSON(rs);
		return Response.status(201).entity(jsonarray.toString()).build();
	}
	

}
