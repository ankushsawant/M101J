package com.sawant.ankush;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class HelloWorldSparkStyle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Spark.get(new Route("/") {
			
			@Override
			public Object handle(Request arg0, Response arg1) {
				// TODO Auto-generated method stub
				return "Hello World From Spark";
			}
		});
		
	}

}
