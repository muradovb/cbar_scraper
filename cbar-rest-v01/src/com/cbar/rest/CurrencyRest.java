package com.cbar.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;

import com.cbar.dao.CurrencyDAO;
import com.cbar.entity.Currency;
import com.cbar.scraper.CurrencyScraper;

@Path("/api")
public class CurrencyRest {

	private static boolean populated = false;
	
	@Path("currency/{code}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Currency getCurrency(@PathParam("code") String c) throws JSONException {
		if(!populated) {
			CurrencyDAO.saveData(CurrencyScraper.getInstance().getCurrencyData());
			populated=true;
		}
		return CurrencyDAO.getCurrency(c);
	}
	
}
