package com.sawant.ankush;

import java.io.StringWriter;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class HelloWorldMongoDBSparkFreemarkerStyle {

	public static void main(String[] args) throws UnknownHostException {
		// TODO Auto-generated method stub
		
		final Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(HelloWorldFreemarkerStyle.class, "/");
		
		MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));
		
		DB database = client.getDB("course");
		final DBCollection collection = database.getCollection("hello");
		
		Spark.get(new Route("/") {
			
			@Override
			public Object handle(Request arg0, Response arg1) {
				// TODO Auto-generated method stub
				
				StringWriter writer = new StringWriter();

				try {
					Template helloTemplate = configuration.getTemplate("hello.ftl");
					
					Map<String, Object> helloMap = new HashMap<String, Object>();
					helloMap.put("name", "Freemarker");
					
					DBObject document = collection.findOne();
					
					helloTemplate.process(document, writer);
										
				} catch (Exception e) {
					// TODO Auto-generated catch block
					halt(500);
					e.printStackTrace();
				}
				return writer;
			}
		});
	}

}
