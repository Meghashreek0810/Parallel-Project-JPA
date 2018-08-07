package com.cg.pwa.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cg.pwa.bean.Account1;
import com.cg.pwa.exception.AccountException;
import com.cg.pwa.service.AccountService;
import com.cg.pwa.service.IAccountService;

public class Account1Test {

	IAccountService accountService = new AccountService();

	@Test
	public void testCreateAccountForName() {

		Account1 account = new Account1();
		account.setCustomerName("john896");
		account.setMobileNo("0123456789");
		account.setBalance(5000);
		account.setEmailId("john@gmail.com");

		try {
			accountService.createAccount(account);
		} catch (AccountException e) {
			assertEquals("name should contain only alphabets", e.getMessage());
		}

	}

	@Test
	public void testCreateAccountForMobile() {

		Account1 account = new Account1();
		account.setCustomerName("john");
		account.setMobileNo("01234");
		account.setBalance(5000);
		account.setEmailId("john@gmail.com");
		try {

			accountService.createAccount(account);

		} catch (AccountException e) {

			assertEquals("mobile number should be 10 digits", e.getMessage());
		}

	}

	@Test
	public void testCreateAccountForEmail() {
		Account1 account = new Account1();
		account.setCustomerName("john");
		account.setMobileNo("0123456789");
		account.setBalance(5000);
		account.setEmailId("johngmail.com");
		try {

			accountService.createAccount(account);

		} catch (AccountException e) {

			assertEquals("enter valid email id", e.getMessage());
		}

	}

	@Test
	public void testCreateAccountForAmount() {
		Account1 account = new Account1();
		account.setCustomerName("john");
		account.setMobileNo("0123456789");
		account.setBalance(0);
		account.setEmailId("john@gmail.com");
		try {

			accountService.createAccount(account);

		} catch (AccountException e) {

			assertEquals("enter amount greater than 0", e.getMessage());
		}

	}

	// new account
	@Test
	public void testCreateAccount() {

		Account1 account = new Account1();
		account.setCustomerName("mary");
		account.setMobileNo("9945875149");
		account.setBalance(15000);
		account.setEmailId("mary@gmail.com");

		try {

			accountService.createAccount(account);

		} catch (AccountException e) {

			assertEquals("account created successfully", e.getMessage());
		}

	}

	// test cases for showBalance method

	@Test
	public void testShowBalanceForMobile() {

		Account1 account = new Account1();
		account.setMobileNo("98766");
		try {

			accountService.showBalance(account.getMobileNo());
		} catch (AccountException e) {

			assertEquals("mobile number should be 10 digits", e.getMessage());
		}
	}

	@Test
	public void testShowBalance() {

		Account1 account = new Account1();
		account.setMobileNo("9945632145");
		try {
			double amt = accountService.showBalance(account.getMobileNo());
			assertEquals(10000, amt, 0);
		} catch (AccountException e) {
			System.out.println(e.getMessage());
		}
	}

	// test cases for depositAmount method

	@Test
	public void testDepositAmountForMobile() {

		Account1 account = new Account1();
		account.setMobileNo("9945");
		double amount = 120;

		try {
			accountService.depositAmount(account.getMobileNo(), amount);
		} catch (AccountException e) {
			assertEquals("mobile number should be 10 digits", e.getMessage());
		}
	}

	@Test
	public void testDepositAmountForAccount() {

		Account1 account = new Account1();
		account.setMobileNo("0123456789");
		double amount = 120;
		try {
			accountService.depositAmount(account.getMobileNo(), amount);
		} catch (AccountException e) {
			assertEquals("No entity found for query", e.getMessage());
		}
	}

	@Test
	public void testDepositAmountForAmount() {

		Account1 account = new Account1();
		account.setMobileNo("9535597905");
		double amount = -100;
		try {
			accountService.depositAmount(account.getMobileNo(), amount);
		} catch (AccountException e) {
			assertEquals("enter amount greater than 0", e.getMessage());
		}
	}

	@Test
	public void testDepositAmount() {

		Account1 account = new Account1();
		account.setMobileNo("9687421581");
		double amount = 1000;
		try {
			double balance = accountService.depositAmount(account.getMobileNo(), amount);

			assertEquals(15000, balance, 0.0);
		} catch (AccountException e) {
			System.out.println(e.getMessage());
		}
	}

	// test cases for withdrawAmount method

	@Test
	public void testWithdrawAmountForMobile() {

		Account1 account = new Account1();
		account.setMobileNo("0123");
		double amount = 500;
		;
		try {
			double balance = accountService.withdrawAmount(account.getMobileNo(), amount);
		} catch (AccountException e) {
			assertEquals("mobile number should be 10 digits", e.getMessage());
		}
	}

	@Test
	public void testWithdrawAmountForAccount() {

		Account1 account = new Account1();
		account.setMobileNo("0123456789");
		double amount = 500;
		try {
			double balance = accountService.withdrawAmount(account.getMobileNo(), amount);
		} catch (AccountException e) {

			assertEquals("No entity found for query", e.getMessage());
		}
	}

	@Test
	public void testWithdrawAmountForAmount1() {

		Account1 account = new Account1();
		account.setMobileNo("9535597985");
		double amount = -100;
		try {
			double balance = accountService.withdrawAmount(account.getMobileNo(), amount);
		} catch (AccountException e) {
			assertEquals("enter amount greater than 0", e.getMessage());
		}

	}

	@Test
	public void testWithdrawAmountForAmount2() {

		Account1 account = new Account1();
		account.setMobileNo("9535597985");
		double amount = 5000;
		try {
			double balance = accountService.withdrawAmount(account.getMobileNo(), amount);

		} catch (AccountException e) {

			assertEquals("enter withdraw amount less than available balance", e.getMessage());
		}
	}

	@Test
	public void testWithdrawAmount() {

		Account1 account = new Account1();
		account.setMobileNo("9987698536");
		double amount = 100;
		try {
			double balance = accountService.withdrawAmount(account.getMobileNo(), amount);
			System.out.println(balance);
			assertEquals(2100, balance, 0.00);
		} catch (AccountException e) {
			System.out.println(e.getMessage());

		}

	}

	// test cases for fundTrnasfer method

	@Test
	public void testFundTransferForMobile1() {

		Account1 account1 = new Account1();
		account1.setMobileNo("1234");
		Account1 account2 = new Account1();
		account2.setMobileNo("1234567890");
		double amount = 200;
		try {
			accountService.fundTransfer(account1.getMobileNo(), account2.getMobileNo(), amount);
		} catch (AccountException e) {

			assertEquals("mobile number should be 10 digits", e.getMessage());
		}

	}

	@Test
	public void testFundTransferForMobile2() {

		Account1 account1 = new Account1();
		account1.setMobileNo("1234567890");
		Account1 account2 = new Account1();
		account2.setMobileNo("12345");
		double amount = 200;
		try {
			accountService.fundTransfer(account1.getMobileNo(), account2.getMobileNo(), amount);
		} catch (AccountException e) {

			assertEquals("mobile number should be 10 digits", e.getMessage());
		}

	}

	@Test
	public void testFundTransferForBAmount() {

		Account1 account1 = new Account1();
		account1.setMobileNo("9535597985");
		Account1 account2 = new Account1();
		account2.setMobileNo("8763549869");
		double amount = -100;
		try {
			accountService.fundTransfer(account1.getMobileNo(), account2.getMobileNo(), amount);
		} catch (AccountException e) {

			assertEquals("enter amount greater than 0", e.getMessage());
		}

	}

	@Test
	public void testFundTransferForWAmount() {

		Account1 account1 = new Account1();
		account1.setMobileNo("9535597985");
		Account1 account2 = new Account1();
		account2.setMobileNo("8763549869");
		double amount = 50000;
		try {
			accountService.fundTransfer(account1.getMobileNo(), account2.getMobileNo(), amount);
		} catch (AccountException e) {

			assertEquals("enter withdraw amount less than available balance", e.getMessage());
		}

	}

	@Test
	public void testFundTransfer() {

		Account1 account1 = new Account1();
		account1.setMobileNo("9945875145");
		Account1 account2 = new Account1();
		account2.setMobileNo("8763549869");
		double amount = 100;

		try {
			double balance = accountService.fundTransfer(account1.getMobileNo(), account2.getMobileNo(), amount);
			assertEquals(7400, balance, 0.00);
		} catch (AccountException e) {
			System.out.println(e.getMessage());

		}

	}

	// test cases for printTransaction method

	@Test
	public void testPrintTransactionForMobile() {
		Account1 account = new Account1();
		account.setMobileNo("01234");
		try {
			accountService.printTransaction(account.getMobileNo());

		} catch (AccountException e) {

			assertEquals("mobile number should be 10 digits", e.getMessage());
		}
	}

	@Test
	public void testPrintTransactionForName() {
		Account1 account = new Account1();
		account.setMobileNo("9535597985");
		try {
			Account1 acc1 = accountService.printTransaction(account.getMobileNo());

			assertEquals("john", acc1.getCustomerName());
		} catch (AccountException e) {

			System.out.println(e.getMessage());

		}
	}

	@Test
	public void testPrintTransactionForBalance() {
		Account1 account = new Account1();
		account.setMobileNo("9945632145");
		try {
			Account1 account1 = accountService.printTransaction(account.getMobileNo());

			assertEquals(10000, account1.getBalance(), 0);
		} catch (AccountException e) {
			System.out.println(e.getMessage());

		}
	}

	@Test
	public void testPrintTransaction() {
		Account1 account = new Account1();
		account.setMobileNo("9535597985");
		try {
			Account1 account1 = accountService.printTransaction(account.getMobileNo());

			assertNotNull(account1);
		} catch (AccountException e) {
			System.out.println(e.getMessage());

		}
	}

}
