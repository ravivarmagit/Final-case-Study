package com.example.deal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.mongodb.internal.connection.Time;


@SpringBootTest
class DealApplicationTests {
	
	@MockBean
	private DealRepository dealRepository;
	
	@Autowired
	private DealController dealcontroller;
	

	@Test
	public void getAllDealTest()  {
		 when(dealRepository.findAll()).thenReturn(
	                Stream.of(
	                        new Deal("","amazon","mobile","10% offer","www.amazon.in"))
	                        .collect(Collectors.toList()));
	        assertEquals(1,dealcontroller.getAllDeal().size());
		
	}
	
	@Test
	public void addDealTest() {
		Deal deal = new Deal("","amazon","mobile","10% offer","www.amazon.in");
		when(dealRepository.save(deal)).thenReturn(deal);
		 assertEquals(deal,dealcontroller.addDeals(deal));
	}
	
	@Test
	public void deleteDealTest() {
	
		String dealId = "1";
		
	Deal deal = new Deal("1","amazon","mobile","10% offer","www.amazon.in");
		dealRepository.deleteById(dealId);
		verify(dealRepository).deleteById(dealId);
		
		
	}

}
