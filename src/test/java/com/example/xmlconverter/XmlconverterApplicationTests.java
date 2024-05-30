package com.example.xmlconverter;

import com.example.xmlconverter.entity.Product;
import com.example.xmlconverter.entity.Products;
import com.example.xmlconverter.exception.ProductNotFoundException;
import com.example.xmlconverter.service.ConverterService;
import com.example.xmlconverter.service.ConverterServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class XmlconverterApplicationTests {

	private ConverterService converterService;

	@Value("${converter.file.path}")
	private String filePath;
	private Products expectedProducts;
	private int expectedNumberOfRecords;
	@BeforeEach
	public void setUp() {
		converterService = new ConverterServiceImpl();
		expectedProducts = new Products();
		expectedProducts.addProduct(new Product(1, "apple", "fruit", "2303-E1A-G-M-W209B-VM", "FruitsAll", true));
		expectedProducts.addProduct(new Product(2, "orange", "fruit", "5603-J1A-G-M-W982F-PO", "FruitsAll", false));
		expectedProducts.addProduct(new Product(3, "glass", "dish", "9999-E7R-Q-M-K287B-YH", "HomeHome", true));

		expectedNumberOfRecords = expectedProducts.getSize();
	}
	@Test
	void getNumberOfRecordsTest() throws IOException {
		int numberOfRecords = converterService.getNumberOfRecords(filePath);
		assertEquals(expectedNumberOfRecords, numberOfRecords, "Number of products should be equal to " + expectedNumberOfRecords);
	}
	@Test
	void getAllProductsTest() throws IOException {
		List<Product> productList =  converterService.getListOfProducts(filePath);
		assertEquals(expectedProducts.getProductList(), productList);
	}

	@Test
	void getProductByName() throws IOException {
		String expectedName = "glass";
		List<Product> products = converterService.getProductByName(expectedName, filePath);
		List<Product> expectedResult = new ArrayList<>();
		expectedResult.add(expectedProducts.getProductList().get(2));
		assertEquals(expectedResult, products);
	}

	@Test
	void notFoundProductTest() throws IOException {
		String name = "banana";

		ProductNotFoundException e = assertThrows(ProductNotFoundException.class, () -> {
			converterService.getProductByName(name, filePath);
		}, "Exception is not thrown");
	}

	@Test
	void fileNotFoundTest() {
		String filePath = "";

		FileNotFoundException e = assertThrows(FileNotFoundException.class, () ->
			converterService.getNumberOfRecords(filePath)
		, "File path is empty, so the exception should have been thrown");
	}

}
