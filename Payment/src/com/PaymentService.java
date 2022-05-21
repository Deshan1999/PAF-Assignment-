package com;

import model.Payment;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Payment")
public class PaymentService {
	Payment PaymentObj = new Payment();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPayment() {
		return PaymentObj.readPayment();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPayment(
			@FormParam("pay_method") String pay_method,
			@FormParam("account_no") String account_no,
			@FormParam("pay_date") String pay_date,
			@FormParam("pay_amount") String pay_amount) {
	String output = PaymentObj.insertPayment(pay_method, account_no, pay_date, pay_amount);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)

	public String updatePayment(String paymentData) {
		
		JsonObject paymentObject = new JsonParser().parse(paymentData).getAsJsonObject();
		// Read the values from the JSON object
		String pay_ID = paymentObject.get("pay_ID").getAsString();
		String pay_method = paymentObject.get("pay_method").getAsString();
		String account_no = paymentObject.get("account_no").getAsString();
		String pay_date = paymentObject.get("pay_date").getAsString();
		String pay_amount = paymentObject.get("pay_amount").getAsString();	
		
		String output = PaymentObj.updatePayment(pay_ID, pay_method, account_no, pay_date, pay_amount);
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePayment(String paymentData) {
		
		Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());

		// Read the value from the element
		String pay_ID = doc.select("pay_ID").text();
		String output = PaymentObj.deletePayment(pay_ID);
		return output;
		
	}
}
