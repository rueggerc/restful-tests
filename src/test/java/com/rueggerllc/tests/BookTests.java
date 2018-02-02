package com.rueggerllc.tests;

import java.net.URI;
import java.util.List;

import javax.ws.rs.core.UriBuilder;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rueggerllc.beans.Book;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class BookTests {

	private static Logger logger = Logger.getLogger(BookTests.class);
	private static String bookServiceURI = "http://localhost:8080/restful-jersey/rest/books";
	
	
	@BeforeClass
	public static void setupClass() throws Exception {
	}
	
	@AfterClass
	public static void tearDownClass() throws Exception {
	}

	@Before
	public void setupTest() throws Exception {
	}

	@After
	public void tearDownTest() throws Exception {
	}
	
	@Test
	@Ignore
	public void dummyTest() {
		logger.info("Dummy Test");
	}
	
	@Test
	@Ignore
	public void testGetBooks() {
		try {
			ClientConfig config = new DefaultClientConfig();
			Client client = Client.create(config);
			URI getURI = getBookTargetURI("books");
			String booksJSON = client.resource(getURI).accept("application/json").get(String.class);
			logger.info("JSON=\n" + booksJSON);
			
			ObjectMapper mapper = new ObjectMapper();
			List<Book> books = mapper.readValue(booksJSON, mapper.getTypeFactory().constructCollectionType(List.class, Book.class));
			for (Book book : books) {
				logger.info(book + "\n");
			}
			
		} catch (Exception e) {
			logger.error("ERROR", e);
		}
	}
	
	@Test
	// @Ignore
	public void testGetBook() {
		try {
			ClientConfig config = new DefaultClientConfig();
			Client client = Client.create(config);
			URI getURI = getBookTargetURI("book/id/3");
			String bookJSON = client.resource(getURI).accept("application/json").get(String.class);
			logger.info("JSON=\n" + bookJSON);
			
			ObjectMapper mapper = new ObjectMapper();
			Book book = mapper.readValue(bookJSON, Book.class);
			logger.info("Book=\n" + book);
			
		} catch (Exception e) {
			logger.error("ERROR", e);
		}
	}
	
	private URI getBookTargetURI(String path) {
		String endpoint = String.format("%s/%s", bookServiceURI, path);
		logger.info("Service URI=" + endpoint);
		URI bookURI = UriBuilder.fromUri(endpoint).build();
		return bookURI;
	}	
	
	
	
}
