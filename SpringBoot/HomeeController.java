package com.example.demo;

import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
public class HomeeController {
    @CrossOrigin(origins = "*")
    @GetMapping(value="/home")
    public void file(String fileName, HttpServletResponse res) throws IOException, URISyntaxException {
        res.setHeader("Content-Disposition","attachment; filename" + fileName);
        res.getOutputStream().write(contentOf(fileName));
    }
    private byte[] contentOf(String fileName) throws IOException, URISyntaxException {

       return Files.readAllBytes(Paths.get(getClass().getClassLoader().getResource(fileName).toURI()));
    }
}
