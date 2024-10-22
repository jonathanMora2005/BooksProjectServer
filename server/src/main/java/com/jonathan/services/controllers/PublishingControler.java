package com.jonathan.services.controllers;

import com.jonathan.mybooklist.RepositoryFactory;
import com.jonathan.mybooklist.models.*;
import com.jonathan.mybooklist.repositories.GenreRepository;
import com.jonathan.mybooklist.repositories.PublishingRepository;

import java.util.Set;

public class PublishingControler implements Controller{
    PublishingRepository repository;
    private final RepositoryFactory repositoryFactory;
    private final ModelFactory modelFactory;
    public PublishingControler(RepositoryFactory repositoryFactory, ModelFactory modelFactory) {
        this.repositoryFactory = repositoryFactory;
        this.modelFactory = modelFactory;
    }

    @Override
    public String get(int k) {
        Publishing genre = repositoryFactory.getPublishingRepository().get(k);
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{")
                .append("\"id\":").append(genre.getpublishingid()).append(",")
                .append("\"name\":\"").append(genre.getname()).append("\"")
                .append("\"country\":\"").append(genre.getcountry()).append("\"")
                .append("\"email\":\"").append(genre.getemail()).append("\"")
                .append("}");
        return jsonBuilder.toString();
    }

    @Override
    public String get() {
        Set<Publishing> genres = repositoryFactory.getPublishingRepository().getAll();
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[");
        int count = 0;
        int size = genres.size();

        for (Publishing genre : genres) {
            jsonBuilder.append("{")
                    .append("\"id\":").append(genre.getpublishingid()).append(",")
                    .append("\"name\":\"").append(genre.getname()).append("\"")
                    .append("\"country\":\"").append(genre.getcountry()).append("\"")
                    .append("\"email\":\"").append(genre.getemail()).append("\"");

            if (++count < size) {
                jsonBuilder.append(",");
            }
        }
        jsonBuilder.append("]");
        return jsonBuilder.toString();    }

    @Override
    public void post(String value) {
        Publishing author =new com.uvic.teknos.book.models.Publshing();
        author.setpublishingid(0);
        var a = value.split("/");
        author.setname(a[0]);
        author.setcountry(a[1]);
        author.setemail(a[2]);
        repositoryFactory.getPublishingRepository().save(author);
    }

    @Override
    public void put(int key, String value) {
        Publishing author =new com.uvic.teknos.book.models.Publshing();
        author.setpublishingid(key);
        var a = value.split("/");
        author.setname(a[0]);
        author.setcountry(a[1]);
    }

    @Override
    public void delete(int key) {
        Publishing author =new com.uvic.teknos.book.models.Publshing();
        author.setpublishingid(key);
        repositoryFactory.getPublishingRepository().delete(author) ;
    }
}
