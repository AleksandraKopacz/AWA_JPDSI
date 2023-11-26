package model;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the results database table.
 * 
 */
@Entity
@Table(name="results")
@NamedQuery(name="Result.findAll", query="SELECT r FROM Result r")
public class Result implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private double installments;

	private double interest;

	private double loan;

	private double result;

	public Result() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getInstallments() {
		return this.installments;
	}

	public void setInstallments(double installments) {
		this.installments = installments;
	}

	public double getInterest() {
		return this.interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

	public double getLoan() {
		return this.loan;
	}

	public void setLoan(double loan) {
		this.loan = loan;
	}

	public double getResult() {
		return this.result;
	}

	public void setResult(double result) {
		this.result = result;
	}

}