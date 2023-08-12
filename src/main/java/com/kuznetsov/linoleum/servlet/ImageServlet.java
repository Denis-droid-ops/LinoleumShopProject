package com.kuznetsov.linoleum.servlet;

import com.kuznetsov.linoleum.service.ImageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@WebServlet("/resources/images/*")
public class ImageServlet extends HttpServlet {
    private final ImageService imageService = ImageService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String imagePath = req.getRequestURI();
        imageService.get(imagePath)
                .ifPresentOrElse(image->{
                    resp.setContentType("application/octet-stream");
                    writeImage(image,resp);
                },()->resp.setStatus(404));
    }

    private void writeImage(InputStream image, HttpServletResponse resp) {
       try(image; OutputStream servletOutputStream =resp.getOutputStream()) {
           int currentByte;
           while((currentByte=image.read())!=-1){
               servletOutputStream.write(currentByte);
           }
       } catch (IOException e) {
           e.printStackTrace();
       }
    }
}
