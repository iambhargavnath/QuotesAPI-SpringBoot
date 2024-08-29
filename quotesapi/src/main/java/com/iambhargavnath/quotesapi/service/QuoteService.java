package com.iambhargavnath.quotesapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iambhargavnath.quotesapi.model.Quote;
import com.iambhargavnath.quotesapi.repository.QuoteRepository;

@Service
public class QuoteService {
    @Autowired
    private QuoteRepository quoteRepository;

    public List<Quote> getAllQuotes() {
        return quoteRepository.findAll();
    }

    public Optional<Quote> getQuoteById(Long id) {
        return quoteRepository.findById(id);
    }

    public void saveQuote(Quote quotes) {
        quoteRepository.save(quotes);
    }

    public Quote saveQuoteAPI(Quote quote) {
        return quoteRepository.save(quote);
    }

    public Quote updateQuote(Long id, Quote updatedQuote) {
        return quoteRepository.findById(id)
                .map(quote -> {
                    quote.setId(updatedQuote.getId());
                    quote.setQuote(updatedQuote.getQuote());
                    quote.setAuthor(updatedQuote.getAuthor());
                    quote.setLanguage(updatedQuote.getLanguage());
                    return quoteRepository.save(quote);
                })
                .orElseGet(() -> {
                    updatedQuote.setId(id);
                    return quoteRepository.save(updatedQuote);
                });
    }

    public void deleteQuote(Long id) {
        quoteRepository.deleteById(id);
    }
}

