package com.example.demo.runner;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import com.example.demo.repo.ProductRepository;
import com.example.entity.Product;

@Component
public class TestRunner implements CommandLineRunner {

	@Autowired
	private ProductRepository repo;
	private static Logger logger = LogManager.getLogger(TestRunner.class);

	@Override
	public void run(String... args) throws Exception {
		logger.info("ok");
		logger.error("Ok Done");

		repo.save(new Product(1, "car", 55.5));
		repo.save(new Product(2, "box", 555.5));
		repo.save(new Product(4, "gfh", 585.5));
		System.out.println("hfjfk");
		Optional<Product> p = repo.findById(1);
		if (p.isPresent()) {
			System.out.println(p);
		}
		List<Product> ps = repo.findAll();
		for (Product product : ps) {
			System.out.println(product);
		}
		System.out.println(repo.findAll(Example.of(new Product(1))));
		repo.findAll(Sort.by(Direction.DESC, "prodName")).forEach(System.out::println);
		System.out.println("--------------");
		repo.findAll()
		.stream()
		.filter((p1)->p1.getProdName()=="car")
		.sorted((p1,p2)->p1.getProdId()-p2.getProdId())
		.forEach(System.out::println);
		

	}

}
