package com.iambhargavnath.quotesapi.repository;

import com.iambhargavnath.quotesapi.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
}
