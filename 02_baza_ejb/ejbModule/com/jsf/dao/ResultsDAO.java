package com.jsf.dao;

import java.util.List;
import java.util.Map;

import model.Result;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class ResultsDAO {
	private final static String UNIT_NAME = "02_baza_jpa";

	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;
	
	public void create(Result results) {
		em.persist(results);
	}

	public Result merge(Result results) {
		return em.merge(results);
	}

	public void remove(Result results) {
		em.remove(em.merge(results));
	}
	
	public Result find(Object id) {
		return em.find(Result.class, id);
	}
	
	public List<Result> getFullList() {
		List<Result> list = null;

		Query query = em.createQuery("select r from Result r");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public List<Result> getList(Map<String, Object> searchParams) {
		List<Result> list = null;

		String select = "select r ";
		String from = "from Result t ";
		String where = "";
		String orderby = "order by r.id desc";

		Query query = em.createQuery(select + from + where + orderby);

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
