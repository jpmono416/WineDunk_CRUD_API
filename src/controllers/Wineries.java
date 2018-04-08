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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import models.tblWineries;
import services.WineriesService;

/**
 * Servlet implementation class Wineries
 */
@WebServlet("/wineries")
public class Wineries extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ObjectMapper mapper = new ObjectMapper();
	
	@EJB
	WineriesService wineryService = new WineriesService();
    public Wineries() { super(); }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!request.getParameterMap().containsKey("action"))
		{
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing 'action' parameter");
			return;
		}
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getWineries" :
			{
				try 
				{ 
					//Set pretty printing of json
			    	this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<tblWineries> wineries = wineryService.getWineries();
					String arrayToJson = this.mapper.writeValueAsString(wineries);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			
			case "getWinery" :
			{
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					//Set pretty printing of json
					this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					tblWineries winery = wineryService.getWineryById(id);

					if(winery!=null)
					{
						String arrayToJson = this.mapper.writeValueAsString(winery);
						response.getWriter().write(arrayToJson);
					}
				}
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			case "getByName":
			{				
				if(!request.getParameterMap().containsKey("name"))
				{
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing 'name' parameter");
					return;
				}

				final String name = request.getParameter("name");

				final String countryName = request.getParameterMap().containsKey("countryName") ? request.getParameter("countryName") : "";
				final String regionName = request.getParameterMap().containsKey("regionName") ? request.getParameter("regionName") : "";
				final String appellationName = request.getParameterMap().containsKey("appellationName") ? request.getParameter("appellationName") : "";

				System.out.println(countryName);
				System.out.println(regionName);
				System.out.println(appellationName);

				tblWineries winery = this.wineryService.getByName(countryName, regionName, appellationName, name);

				System.out.println(winery);

				if(winery!=null)
					response.getWriter().write(this.mapper.writeValueAsString(winery));

				return;
							
			}
			default:
			{
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameterMap().containsKey("action")) { return; }

		StringBuilder sb = new StringBuilder();
	    BufferedReader reader = request.getReader();
	    String line;
        
	    while ((line = reader.readLine()) != null) 
        { sb.append('\n').append(line); }
	    reader.close();

	    String content = sb.toString().replaceFirst("\n", "");
	    
		String action = request.getParameter("action");
		switch (action) 
		{
			case "addWinery" :
			{
				try
				{
					tblWineries winery = new tblWineries();
					winery = this.mapper.readValue(content, tblWineries.class);
					Integer id = wineryService.addWinery(winery);
					if(id!=null) { response.getWriter().print(id); }
					else { response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Something went wrong while inserting the winery "+winery.getName()); }
				} catch (Exception e) {return;}
				break;
			}
			
			case "updateWinery" :
			{
				try
				{
					tblWineries winery = new tblWineries();
					winery = this.mapper.readValue(content, tblWineries.class);
					
					if(wineryService.updateWinery(winery)) { response.getWriter().print("True"); }
				} catch (Exception e) {return;}
				break;
			}
			
			case "deleteWinery" :
			{
				try
				{
					Integer id = Integer.parseInt(content);
					if(wineryService.deleteWinery(id)) { response.getWriter().print("True"); }
				} catch (Exception e) { return; }
				break;
			}
		}
	}

}