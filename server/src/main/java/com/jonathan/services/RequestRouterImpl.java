package com.jonathan.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jonathan.services.controllers.Controller;
import com.jonathan.services.controllers.GenreControler;
import com.uvic.teknos.book.models.Genre;
import rawhttp.core.RawHttp;
import rawhttp.core.RawHttpRequest;
import rawhttp.core.RawHttpResponse;

import java.util.Map;

public class RequestRouterImpl implements RequestRouter {
    private static RawHttp rawHttp = new RawHttp();
    private final Map<String, Controller> controllers;

    public RequestRouterImpl(Map<String, Controller> controllers) {
        this.controllers = controllers;
    }

    /**
     *
     *
     * @param request
     * @return the http response to send to the client
     */

    public RawHttpResponse<?> execRequest(RawHttpRequest request) {
        var path = request.getUri().getPath();
        var method = request.getMethod();
        var pathParts = path.split("/");
        var controllerName = pathParts[1];
        var responseJsonBody = "";
        System.out.println("execRequest");
        switch (controllerName) {
            case "genre":
                responseJsonBody = manageGenre(request, method, pathParts, responseJsonBody);
                break;
            case "author":
                responseJsonBody = manageAuthor(request, method, pathParts, responseJsonBody);
                break;
            case "publishing":
                responseJsonBody = managePublishing(request, method, pathParts, responseJsonBody);
                break;
            case "readBook":
                responseJsonBody = manageReadBook(request, method, pathParts, responseJsonBody);
                break;
            case "peandingBook":
                responseJsonBody = managePeandingBook(request, method, pathParts, responseJsonBody);
                break;

        }

        RawHttpResponse response = null;
        try {
            // TODO: Router logic

            response = rawHttp.parseResponse("HTTP/1.1 200 OK\r\n" +
                    "Content-Type: text/json\r\n" +
                    "Content-Length: " + responseJsonBody.length() + "\r\n" +
                    "\r\n" +
                    responseJsonBody);
            System.out.println(response);
        } catch (Exception exception) {
            response = null;
        }

        return response;
    }

    private String managePeandingBook(RawHttpRequest request, String method, String[] pathParts, String responseJsonBody) {
        var controller = controllers.get(pathParts[1]);
        if (method.equals("POST")) {
            var publishingJson = request.getBody().get().toString();
            controller.post(publishingJson);

        } else if (method.equals("GET") && pathParts.length >= 2) {
            responseJsonBody = controller.get(Integer.parseInt(pathParts[2]));


        }else if (method.equals("GET")) {
            responseJsonBody = controller.get();

        } else if (method.equals("DELETE")) {
            var publishingId = Integer.parseInt(pathParts[2]);
            controller.delete(publishingId);
        } else if (method.equals("PUT")) {
            var publishingId = Integer.parseInt(pathParts[2]);
            var mapper = new ObjectMapper();
            var authorJson = request.getBody().get().toString();
            controller.put(publishingId, authorJson);
        }
        return responseJsonBody;
    }

    private String manageReadBook(RawHttpRequest request, String method, String[] pathParts, String responseJsonBody) {
        var controller = controllers.get(pathParts[1]);
        if (method.equals("POST")) {
            var readBookJson = request.getBody().get().toString();
            controller.post(readBookJson);

        } else if (method.equals("GET") && pathParts.length == 2) {
            responseJsonBody = controller.get();

        }else if (method.equals("GET")) {
            responseJsonBody = controller.get(Integer.parseInt(pathParts[2]));

        } else if (method.equals("DELETE")) {
            var readBookId = Integer.parseInt(pathParts[2]);
            controller.delete(readBookId);
        } else if (method.equals("PUT")) {
            var readBookId = Integer.parseInt(pathParts[2]);
            var mapper = new ObjectMapper();
            var readBookJson = request.getBody().get().toString();
            controller.put(readBookId, readBookJson);
        }
        return responseJsonBody;
    }

    private String managePublishing(RawHttpRequest request, String method, String[] pathParts, String responseJsonBody) {
        var controller = controllers.get(pathParts[1]);

        if (method.equals("POST")) {
            var publishingJson = pathParts[3]+"/"+pathParts[4]+"/"+pathParts[5];
            controller.post(publishingJson);

        } else if (method.equals("GET") && Integer.parseInt(pathParts[2]) == 0) {
            responseJsonBody = controller.get();

        }else if (method.equals("GET")) {

           responseJsonBody = controller.get(Integer.parseInt(pathParts[2]));

        } else if (method.equals("DELETE")) {
            var publishingId = Integer.parseInt(pathParts[2]);
            controller.delete(publishingId);
        } else if (method.equals("PUT")) {
            var publishingId = Integer.parseInt(pathParts[2]);
            var mapper = new ObjectMapper();
            var publishingJson = pathParts[3]+"/"+pathParts[4]+"/"+pathParts[5];
            controller.put(publishingId, publishingJson);
        }
        return responseJsonBody;
    }

    private String manageAuthor(RawHttpRequest request, String method, String[] pathParts, String responseJsonBody) {
        var controller = controllers.get(pathParts[1]);
        if (method.equals("POST")) {
            var authorJson = request.getBody().get().toString();
            var strigAuthorJson = pathParts[3]+"/"+pathParts[4];
            controller.post(strigAuthorJson);
        } else if (method.equals("GET") && Integer.parseInt(pathParts[2]) != 0) {
            responseJsonBody = controller.get(Integer.parseInt(pathParts[2]));
        }else if (method.equals("GET")) {
            responseJsonBody = controller.get();
        } else if (method.equals("DELETE")) {
            var genreId = Integer.parseInt(pathParts[2]);
            controller.delete(genreId);
        } else if (method.equals("PUT")) {
            var authorId = Integer.parseInt(pathParts[2]);
            var mapper = new ObjectMapper();

            var authorJson = pathParts[3]+"/"+pathParts[4];
            controller.put(authorId, authorJson);

        }
        return responseJsonBody;
    }

    private String manageGenre(RawHttpRequest request, String method, String[] pathParts, String responseJsonBody) {
        var controller = controllers.get(pathParts[1]);

        if (method.equals("POST")) {
            var genreJson = pathParts[3];
            controller.post(genreJson);

        } else if (method.equals("GET") && Integer.parseInt(pathParts[2]) != 0) {
            responseJsonBody = controller.get(Integer.parseInt(pathParts[2]));


        }else if (method.equals("GET")) {
            responseJsonBody = controller.get();



        } else if (method.equals("DELETE")) {
            var genreId = Integer.parseInt(pathParts[2]);
            controller.delete(genreId);
        } else if (method.equals("PUT")) {
            var genreId = Integer.parseInt(pathParts[2]);
            var mapper = new ObjectMapper();

            var ggenreJson = pathParts[3];
            controller.put(genreId, ggenreJson);

        }
        return responseJsonBody;
    }
}