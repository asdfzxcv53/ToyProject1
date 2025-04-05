package com.example.jpaproject;

import com.example.jpaproject.domain.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class JpaprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaprojectApplication.class, args);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();

		try{
			Product p1 = new Product("saewooggang", 1200, 100);
			em.persist(p1);
			Product p2 = new Product("gamjaggang", 1500, 200);
			em.persist(p2);
			em.flush();
			em.clear();

			OrderedProduct o1 = new OrderedProduct(1L, "saewooggang", 1200, 1);
			OrderedProduct o2 = new OrderedProduct(2L, "gamjaggang", 1500, 1);

			List<OrderedProduct> orderedProducts = new ArrayList<>();
			orderedProducts.add(o1);
			orderedProducts.add(o2);

			Orders orders = new Orders(orderedProducts);

			em.persist(orders);

			for(OrderedProduct o : orderedProducts){
				OrderList ol = new OrderList();
				ol.setOrderAmount(o.getAmount());
				ol.setTotalPrice(o.getAmount() * o.getPrice());
				em.persist(ol);

				ol.setOrders(orders);
				ol.setProduct(em.find(Product.class, o.getId()));
			}

			em.flush();
			em.clear();

			Orders findOrders = em.find(Orders.class, orders.getId());
			List<OrderList> orderLists = findOrders.getOrderList();

			System.out.println("================");

			for(OrderList ol : orderLists){
				System.out.print("상품이름 : " + ol.getProduct().getName());
				System.out.print("개당가격 : " + ol.getProduct().getPrice());
				System.out.print("구매수량 : " + ol.getOrderAmount());
				System.out.print("총 가격 : " + ol.getTotalPrice());
				System.out.println();
			}

			System.out.println("================");

			tx.commit();
		}catch(Exception e){
			tx.rollback();
		}finally{
			em.close();
		}

		;
	}

}
