package com.jonathan.services.controllers;

import com.jonathan.mybooklist.RepositoryFactory;
import com.jonathan.mybooklist.models.Genre;
import com.jonathan.mybooklist.models.ModelFactory;
import com.jonathan.mybooklist.models.ReadBook;
import com.jonathan.mybooklist.repositories.BookreadRepository;
import com.jonathan.mybooklist.repositories.GenreRepository;
import com.uvic.teknos.book.repositorys.JdbcReadBookRepository;

public class ReadBookControler implements Controller{
    private final RepositoryFactory repositoryFactory;
    private final ModelFactory modelFactory;

    public ReadBookControler(RepositoryFactory repositoryFactory, ModelFactory modelFactory) {
        this.repositoryFactory = repositoryFactory;
        this.modelFactory = modelFactory;
    }

    @Override
    public String get(int k) {
        return "";
    }

    @Override
    public String get() {
        return "";
    }

    @Override
    public void post(String value) {

    }

    @Override
    public void put(int key, String value) {

    }

    @Override
    public void delete(int key) {

    }
}
