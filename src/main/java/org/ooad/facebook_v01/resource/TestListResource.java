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


@Path("/testMemberResource")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TestListResource {
	
	
//		@Path("/fetchMemberList/{eventid}")
//		@GET
//		public Response fetchList(@PathParam("eventid") String eventid) throws Exception{
//			DatabaseConnection dbconn = new DatabaseConnection();
//			ResultSet rs;
//			String query = "SELECT distinct u.userdetails_pk, u.userdetails_picurl, u.userdetails_firstname, u.userdetails_lastname, u.userdetails_dob, e.event_name, e.event_start_date, e.event_end_date FROM USERDETAILS u, EVENTLIST el, EVENT e where el.eventlist_status = 'Going' and el.event_pk = "+eventid+" and e.event_pk = el.event_pk and el.eventlist_friend = u.userdetails_pk;";
//			rs = dbconn.getStmt().executeQuery(query);
//			JSONArray jsonarray = Convertor.convertToJSON(rs);
//			return Response.status(201).entity(jsonarray.toString()).build();
//		}
//		
		@Path("/fetchGroupMemberList/{groupid}")
		@GET
		public Response fetchGroupMemberList(@PathParam("groupid") String groupid) throws Exception{
			DatabaseConnection dbconn = new DatabaseConnection();
			ResultSet rs;
			String query = "SElECT * FROM USERDETAILS u , GROUPDETAILS gd , GROUPMEMBERS gmm where gd.groupdetails_id = gmm.groupmembers_groupid AND u.userdetails_pk = gmm.groupmembers_memberid  and gd.groupdetails_id = "+groupid;
			rs = dbconn.getStmt().executeQuery(query);
			JSONArray jsonarray = Convertor.convertToJSON(rs);
			return Response.status(201).entity(jsonarray.toString()).build();
		}
		
		@Path("/fetchAdmin/{groupid}")
		@GET
		public Response fetchAdmin(@PathParam("groupid") String groupid) throws Exception{
			DatabaseConnection dbconn = new DatabaseConnection();
			ResultSet rs;
			String query = "SElECT * FROM USERDETAILS u , GROUPDETAILS gd  where  u.userdetails_pk = gd.groupdetails_createdby  and gd.groupdetails_id = "+groupid;
			rs = dbconn.getStmt().executeQuery(query);
			JSONArray jsonarray = Convertor.convertToJSON(rs);
			return Response.status(201).entity(jsonarray.toString()).build();
		}
		@Path("/fetchCount/{userid}/{friendid}/{listid}")
		@GET
		public Response fetchCount(@PathParam("listid") String groupid,@PathParam("userid") String userid, @PathParam("friendid") String friendid) throws Exception{
			DatabaseConnection dbconn = new DatabaseConnection();
			ResultSet rs;
			String query = "SElECT count(*) as c FROM GROUPDETAILS gd,GROUPMEMBERS gmt where gd.groupdetails_id = gmt.groupmembers_groupid and gd.groupdetails_id in(SELECT g.groupdetails_id FROM GROUPDETAILS g,GROUPMEMBERS gm,GROUPMEMBERS gm2 WHERE g.groupdetails_id = gm.groupmembers_groupid AND gm.groupmembers_groupid = gm2.groupmembers_groupid AND gm.groupmembers_status=1 AND gm2.groupmembers_status=1 AND gm.groupmembers_memberid = "+userid+" and gm2.groupmembers_memberid = "+friendid+") and gmt.groupmembers_memberid ="+groupid;
			rs = dbconn.getStmt().executeQuery(query);
			JSONArray jsonarray = Convertor.convertToJSON(rs);
			return Response.status(201).entity(jsonarray.toString()).build();
		}

}
