package com.cbar.dao;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cbar.entity.Currency;
import com.cbar.util.HibernateUtil;

public class CurrencyDAO {

	
	public static Currency getCurrency(String currencyCode) {

		Currency currency=null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            
            //retrieve the specified currency
            currency= session.get(Currency.class, currencyCode);
            
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currency;
	}
	
	public static void saveData(ArrayList<Currency> currencies) {
        Transaction transaction = null;
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the currency objects
            for(Currency cur:currencies) {
            	session.saveOrUpdate(cur);
            }
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List < Currency > currenciesP = session.createQuery("from Currency", Currency.class).list();
            currenciesP.forEach(c -> System.out.println(c.toString()));
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        
	}
	
	
}
