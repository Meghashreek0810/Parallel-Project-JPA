package com.cg.pwa.dao;




import java.awt.Window.Type;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


import com.cg.pwa.bean.Account1;

import com.cg.pwa.exception.AccountException;



public class AccountDao implements IAccountDao {

	EntityManagerFactory factory=Persistence.createEntityManagerFactory("pwa");
	EntityManager em=factory.createEntityManager();
	
	
	public Account1 createAccount(Account1 acc) throws AccountException {
		
		em.getTransaction().begin();
		em.persist(acc);
		em.getTransaction().commit();
		
			return acc;
			}

	

	
	public double showBalance(String mobileNo) throws AccountException {
		
		
		
		
		String strqry="select a from Account1 a where a.mobileNo=:m";
		TypedQuery<Account1> query=em.createQuery(strqry,Account1.class);
		query.setParameter("m",mobileNo);
		
		Account1 acc=query.getSingleResult();
		if(acc==null) {
			System.out.println("it is null");
			throw new AccountException("account number doesnot exist");
			
			
		}
		else {
				
			return acc.getBalance();
		
		}
		

	}
	public double depositAmount(String mobileNo, double depositAmount)throws AccountException {
		
		try {
		double oldBalance=showBalance(mobileNo);
		double newBalance=oldBalance+depositAmount;
		em.getTransaction().begin();
		Query query=em.createQuery("update Account1  set balance=:b where mobileNo=:m");
		query.setParameter("b", newBalance);
		query.setParameter("m", mobileNo);
		
		int res=query.executeUpdate();
		em.getTransaction().commit();
		if(res!=0) {
			return newBalance;
		}
			
		
		else {
			throw new  AccountException("account number doesnot exist");
		}
		}catch(Exception e) {
			throw new AccountException(e.getMessage());
		}
	}

	
	public double withdrawAmount(String mobileNo, double withdrawAmount)throws AccountException {
		
		
	
		
		
		try {
			
			double oldBalance=showBalance(mobileNo);
			if(withdrawAmount<oldBalance)
				
			{	
			
				
				double newBalance=oldBalance-withdrawAmount;
				em.getTransaction().begin();
				Query query=em.createQuery("update Account1 set balance=:b where mobileNo=:m");
				query.setParameter("b", newBalance);
				query.setParameter("m", mobileNo);
				int rs=query.executeUpdate();
				em.getTransaction().commit();
				if(rs==1)
				{
					
					return newBalance;
				}
				else
				{
					throw new AccountException("balance cannot be updated");
				}
			
			}
			else
			{
				throw new AccountException("enter withdraw amount less than available balance");
			}
		
		
		}catch(Exception e) {
			throw new AccountException(e.getMessage());
	}
}

	public double fundTransfer(String mobileNo1, String mobileNo2, double amount) throws AccountException {

		if(showBalance(mobileNo2)!=0) {
		double balance1=withdrawAmount(mobileNo1, amount);
		double balance2=depositAmount(mobileNo2, amount);
	
	
	
		return balance1;
		}
		else {
			throw new AccountException("account 2 doesnot exixt");
		}
	}

	
	public Account1 printTransaction(String mobileNo) throws AccountException {

		
		
		
		
		try {
			TypedQuery<Account1> query=em.createQuery("select a from Account1 a where a.mobileNo=:m",Account1.class);
			query.setParameter("m", mobileNo);
			Account1 account=query.getSingleResult();
			if(account!=null) {
				return account;
				
			}
			else
			{
				throw new AccountException("mobile number doesnot exist");
			}
		   }
		catch (AccountException e) 
		{
			throw new AccountException("Account doesnot not not exist");
		}
		
		
	}
	
	
}
