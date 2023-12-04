package com.jsfcourse.calc;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import jakarta.ejb.EJB;
import jakarta.faces.context.Flash;
import jakarta.faces.view.ViewScoped;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jsf.dao.ResultsDAO;

import model.Result;

@Named
@RequestScoped
public class CalcListBB {
	
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	ResultsDAO resultsDAO;
	
	public List<Result> getFullList(){
		List<Result> list = null;
		list = resultsDAO.getFullList();
		return list;
	}
	
	public List<Result> getList(){
		List<Result> list = null;
		
		//1. Prepare search params
		Map<String,Object> searchParams = new HashMap<String, Object>();
		
		//2. Get list
		list = resultsDAO.getList(searchParams);
		
		return list;
	}
}