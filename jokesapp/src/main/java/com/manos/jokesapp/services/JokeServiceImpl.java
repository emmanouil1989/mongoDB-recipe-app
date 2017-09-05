package com.manos.jokesapp.services;

        import guru.springframework.norris.chuck.ChuckNorrisQuotes;
        import org.springframework.stereotype.Service;

@Service
public class JokeServiceImpl implements JokeService {
    @Override
    public String getRandomJokes() {
        ChuckNorrisQuotes quotes = new ChuckNorrisQuotes();

        return quotes.getRandomQuote();
    }
}
