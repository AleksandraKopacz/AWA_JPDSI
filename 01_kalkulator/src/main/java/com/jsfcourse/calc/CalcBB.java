package com.jsfcourse.calc;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class CalcBB {
	private String loan;
	private String installments;
	private String interest;
	private Double result;
	
	@Inject
	FacesContext ctx;

	public String getLoan() {
		return loan;
	}

	public void setLoan(String loan) {
		this.loan = loan;
	}

	public String getInstallments() {
		return installments;
	}

	public void setInstallments(String installments) {
		this.installments = installments;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public Double getResult() {
		return result;
	}

	public void setResult(Double result) {
		this.result = result;
	}

	public String calc() {		
		try {
			double loan = Double.parseDouble(this.loan);
			double interest = Double.parseDouble(this.interest);
			double installments = Double.parseDouble(this.installments);
			
			if(interest == 0) result = loan/installments;
			else {
			double result1 = loan * (interest/100);
			double result2 = 1 - Math.pow((1 + (interest/100)), (installments * -1));
			
			result = Math.round((result1/result2) * 100.0) / 100.0;
			}
			
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana poprawnie", null));
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
