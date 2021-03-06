package controllers;

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

import models.tblPartnersProducts;
import services.PartnersProductsService;

/**
 * Servlet implementation class PartnersProductss
 */
@WebServlet("/partnersProductss")
public class PartnersProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ObjectMapper mapper = new ObjectMapper();
	
	@EJB
	PartnersProductsService partnersProductsService = new PartnersProductsService();
    public PartnersProducts()
    {
    	super();

    	//Set pretty printing of json
    	this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameterMap().containsKey("action"))
		{
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing action parameter");
			return;
		}
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getPartnersProductss" :
			{
				try
				{ 
		    	
					List<tblPartnersProducts> partnersProductss = partnersProductsService.getPartnersProducts();
					String arrayToJson = this.mapper.writeValueAsString(partnersProductss);

					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			case "getPartnersProducts" :
			{
				try {
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
		    	
					tblPartnersProducts partnersProducts = partnersProductsService.getPartnersProductById(id);
					String arrayToJson = this.mapper.writeValueAsString(partnersProducts);

					response.getWriter().write(arrayToJson);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
			case "getByPartnerProductIdAndMerchantProductId" :
			{
				for(String parameter : new String[] {"partnerProductId", "merchantProductId"})
				{
					if(!request.getParameterMap().containsKey(parameter))
					{
						response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing "+parameter);
						return;
					}
				}

				tblPartnersProducts result = partnersProductsService.getByPartnerProductIdAndMerchantProductId(request.getParameter("partnerProductId"), request.getParameter("merchantProductId"));
				response.getWriter().write(this.mapper.writeValueAsString(result));
				break;
			}
			// aripe 2018-03-31
			
			case "getByPartnerIdAndPartnerProductId": 	
			{
				for(String parameter : new String[] {"partnerId", "partnerProductId"})
				{
					if(!request.getParameterMap().containsKey(parameter))
					{
						response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing "+parameter);
						return;
					}
				}
		    	
				tblPartnersProducts result = partnersProductsService.getByPartnerIdAndPartnerProductId(request.getParameter("partnerId"), request.getParameter("partnerProductId"));
				response.getWriter().write(this.mapper.writeValueAsString(result));
				break;
			}
			default:
			{
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action parameter: "+action);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameterMap().containsKey("action"))
		{
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing action parameter");
			return;
		}

		switch (request.getParameter("action")) 
		{
			case "addPartnersProducts" :
				try {
					tblPartnersProducts partnersProducts = this.mapper.readValue(request.getInputStream(), tblPartnersProducts.class);

					Integer id = partnersProductsService.addPartnersProduct(partnersProducts);
					if(id != null) { response.getWriter().print(id); }
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;

			case "updatePartnersProducts" :
				try {
					final JsonNode json = this.mapper.readTree(request.getInputStream());
					final Integer id = json.get("id").asInt();
					final Float price = json.get("price").floatValue();
					
					if(partnersProductsService.updatePartnersProduct(id, price) != null)
					{
						response.getWriter().print("True");
						return;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				response.getWriter().print("False");
				break;

			case "updatePartnersProductsPrice" :
				// aripe 2018-04-03
				try {
					tblPartnersProducts partnersProducts = this.mapper.readValue(request.getInputStream(), tblPartnersProducts.class);
					if(partnersProductsService.updatePartnersProductsPrice(partnersProducts) != null) { response.getWriter().print("True"); }
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;

			case "deletePartnersProducts" :
				try {
					JsonNode json = this.mapper.readTree(request.getInputStream());

					if(partnersProductsService.deletePartnersProduct(json.get("id").asInt())) { response.getWriter().print("True"); }
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
		}
	}

}