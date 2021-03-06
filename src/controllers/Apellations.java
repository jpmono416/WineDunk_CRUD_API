package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import models.tblAppellations;
import models.tblColours;
import models.tblRegions;
import models.viewAppellationsWithWines;
import services.ApellationsService;
import services.DefaultServiceClass;

/**
 * Servlet implementation class Apellations
 */
@WebServlet("/appellations")
public class Apellations extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ObjectMapper mapper = new ObjectMapper();
	
	@EJB
	ApellationsService apellationService = new ApellationsService();
	
	@EJB
	DefaultServiceClass defaultService = new DefaultServiceClass();
	
    public Apellations() { super(); }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getAppellations" :{
				try 
				{ 
					//Set pretty printing of json
			    	this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<tblAppellations> apellations = apellationService.getApellations();
					String arrayToJson = this.mapper.writeValueAsString(apellations);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			
			case "getAppellation" : {
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
			    	//Set pretty printing of json
					this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					tblAppellations apellation = apellationService.getApellationById(id);
					
					if(apellation!=null)
					{
						String arrayToJson = this.mapper.writeValueAsString(apellation);
						response.getWriter().write(arrayToJson);
					}
				}
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			
			case "getAppellationNameById" : {
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					tblAppellations appellation = apellationService.getApellationById(id);
					if ( (appellation != null) && (appellation.getId() == id) ) {
						response.setStatus(200);
						response.getWriter().write(appellation.getName());	
					} else {
						response.setStatus(204);
						response.getWriter().write("");
					}
				}
				catch (Exception e) { 
					response.setStatus(500);
					response.getWriter().write("");
					e.printStackTrace(); 
				}
				break;
			}
			
			case "getByName": {
				if(!request.getParameterMap().containsKey("name"))
					return;

				tblAppellations appellation = this.apellationService.getApellationByName(request.getParameter("name"));

				if(appellation!=null)
					response.getWriter().write(this.mapper.writeValueAsString(appellation));

				break;
			}
			
			case "getAppellationsByRegionId": {
				
				if (request.getParameterMap().containsKey("regionId")) {
					try {
						Integer regionId = Integer.parseInt(request.getParameter("regionId"));
						if (regionId > 0) {
							
							this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
					    	
							List<viewAppellationsWithWines> appellations = null;
							appellations = apellationService.getAppellationsByRegionId(regionId);	
							
							String arrayToJson = this.mapper.writeValueAsString(appellations);
							response.setStatus(200);
							response.getWriter().write(arrayToJson);
							
						} else {
							response.setStatus(204);
							response.getWriter().write("");
						}
							
					} catch (Exception e) {
						response.setStatus(500);
						response.getWriter().write("");
						e.printStackTrace(); 
					}
				} else {
					response.setStatus(204);
					response.getWriter().write("");
				}

				break;
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameterMap().containsKey("action")) { return; }

		switch (request.getParameter("action")) 
		{
			case "addAppellation" : {
				try
				{
					StringBuilder sb = new StringBuilder();
				    BufferedReader reader = request.getReader();
				    String line;
			        
				    while ((line = reader.readLine()) != null) 
			        { sb.append('\n').append(line); }
				    reader.close();

				    String content = sb.toString().replaceFirst("\n", "");
					tblAppellations apellation = this.mapper.readValue(content, tblAppellations.class);
					Integer id = apellationService.addApellation(apellation);
					if(id!=null) { response.getWriter().print(id); }
					else { response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Something went wrong inserting the appellation "+apellation.getName()); }
				} catch (Exception e) {
					e.printStackTrace();
				}

				break;
			}
			
			case "updateAppellation" : {
				try
				{
					tblAppellations apellation = this.mapper.readValue(request.getInputStream(), tblAppellations.class);
					
					if(apellationService.updateApellation(apellation)) { response.getWriter().print("True"); }
				} catch (Exception e) {e.printStackTrace();}

				break;
			}
			
			case "deleteAppellation" : {
				try
				{
					JsonNode json = this.mapper.readTree(request.getInputStream());
					if(apellationService.deleteApellation(json.get("id").asInt())) { response.getWriter().print("True"); }
				} catch (Exception e) { e.printStackTrace(); }
				break;
			}
		}
	}

}