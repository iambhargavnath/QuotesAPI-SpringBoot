package com.iambhargavnath.quotesapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.iambhargavnath.quotesapi.model.Quote;
import com.iambhargavnath.quotesapi.service.QuoteService;

@Controller
public class QuoteController {
    @Autowired
    private QuoteService quoteService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("allQuotes", quoteService.getAllQuotes());
        return "index";
    }

    @GetMapping("/add")
    public String showAddQuoteForm(Model model) {
        model.addAttribute("quotes", new Quote());
        return "add_quote";
    }

    @PostMapping("/save")
    public String saveQuote(@ModelAttribute("quotes") Quote quotes) {
        quoteService.saveQuote(quotes);
        return "redirect:/";
    }

    // JSON API for all Quotes
    @GetMapping("/api/quotes")
    public ResponseEntity<List<Quote>> getAllQuotes() {
        List<Quote> quotes = quoteService.getAllQuotes();
        return new ResponseEntity<>(quotes, HttpStatus.OK);
    }

    // API to get a Quote by id
    @GetMapping("api/quote/{id}")
    public ResponseEntity<Quote> getQuoteById(@PathVariable Long id) {
        Optional<Quote> quote = quoteService.getQuoteById(id);
        return quote.map(q -> new ResponseEntity<>(q, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // API to save a Quote
    @PostMapping("/api/save")
    public ResponseEntity<Quote> saveQuoteAPI(@RequestBody Quote quote) {
        Quote savedQuote = quoteService.saveQuoteAPI(quote);
        return new ResponseEntity<>(savedQuote, HttpStatus.CREATED);
    }

    // API to update a Quote by id
    @PutMapping("/api/update/{id}")
    public ResponseEntity<Quote> updateQuote(@PathVariable Long id, @RequestBody Quote updatedQuote) {
        return new ResponseEntity<>(quoteService.updateQuote(id, updatedQuote), HttpStatus.OK);
    }

    // API to delete a Quote by id
    @DeleteMapping("/api/delete/{id}")
    public ResponseEntity<Void> deleteQuote(@PathVariable Long id) {
        quoteService.deleteQuote(id);
        return ResponseEntity.noContent().build();
    }

}
