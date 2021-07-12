package com.revature.services;

import java.util.Scanner;

import com.revature.controller.MenuController;

public class Driver {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("-----------------VVVVVV*********************VVVVVV-----------------");
		System.out.println("Welcome to The Bank of Dreams, please follow our menu instructions!");
		System.out.println("***********************---------------------***********************");
		
		MenuController menuController = new MenuController();
		
		//1. Menu: Ask if user is returning user

		menuController.validateUser();
		
		
		//5. Account page
//		menuController.showAccountPage();
		
		//6. Exit
	}
	
}
