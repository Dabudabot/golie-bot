package org.golie.bot.logic.objects;

import java.util.Vector;

public class Response {

    private int id;
    private int probability;
    private Vector<String> words;
    private Vector<String> keywords;
    private Vector<String> responses;

    public int getProbability() {
        return probability;
    }

    public void setProbability(int probability) {
        this.probability = probability;
    }

    public Vector<String> getWords() {
        return words;
    }

    public void setWords(Vector<String> words) {
        this.words = words;
    }

    public Vector<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(Vector<String> keywords) {
        this.keywords = keywords;
    }

    public Vector<String> getResponses() {
        return responses;
    }

    public void setResponses(Vector<String> responses) {
        this.responses = responses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
