package com.jsfcourse.calc;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import jakarta.ejb.EJB;
import jakarta.faces.context.Flash;
import jakarta.faces.view.ViewScoped;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.Serializable;

import com.jsf.dao.ResultsDAO;

import model.Result;

@Named
@ViewScoped
public class CalcAddBB implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private static final String PAGE_STAY_AT_THE_SAME = null;
	
	private Result results = new Result();
	private Result loaded = null;
	
	private Double loan;
	private Double installments;
	private Double interest;
	private Double result;
	
	@Inject
	FacesContext ctx;
	
	@EJB
	ResultsDAO resultsDAO;

	@Inject
	FacesContext context;

	@Inject
	Flash flash;
	
	public Result getResults() {
		return results;
	}
	
	public void onLoad() throws IOException {
		// 1. load person passed through session
		// HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		// loaded = (Person) session.getAttribute("person");

		// 2. load person passed through flash
		loaded = (Result) flash.get("results");

		// cleaning: attribute received => delete it from session
		if (loaded != null) {
			results = loaded;
			// session.removeAttribute("person");
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne użycie systemu", null));
			// if (!context.isPostback()) { //possible redirect
			// context.getExternalContext().redirect("personList.xhtml");
			// context.responseComplete();
			// }
		}

	}

	public Double getLoan() {
		return loan;
	}

	public void setLoan(Double loan) {
		this.loan = loan;
	}

	public Double getInstallments() {
		return installments;
	}

	public void setInstallments(Double installments) {
		this.installments = installments;
	}

	public Double getInterest() {
		return interest;
	}

	public void setInterest(Double interest) {
		this.interest = interest;
	}

	public Double getResult() {
		return result;
	}

	public void setResult(Double result) {
		this.result = result;
	}

	public boolean validate(double loan, double interest, double installments) {
		if(loan <= 0) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Kwota kredytu nie może być mniejsza lub równa 0", null));
			return false;
		}
		if(installments <= 0) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Kredyt nie może mieć mniejszej liczby rat niż 1", null));
			return false;
		}
		if(interest <= 0 || interest > 20) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Kredyt nie może mieć oprocentowania poniżej 0% lub powyżej 20%", null));
			return false;
		}
		return true;
	}
	
	public String calc() {		
		try {
			double loan = this.loan;
			double interest = this.interest;
			double installments = this.installments;
			
			if(interest == 0) result = loan/installments;
			else {
			double result1 = loan * (interest/100);
			double result2 = 1 - Math.pow((1 + (interest/100)), (installments * -1));
			
			result = Math.round((result1/result2) * 100.0) / 100.0;
			}
			
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana poprawnie", null));
			
			//resultsDAO.create(results);
			return "showresult";
		} catch (Exception e) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd podczas przetwarzania parametrów", null));
			return null; 
		}
				
	}

	public String info() {
		return "info"; 
	}
	

}
