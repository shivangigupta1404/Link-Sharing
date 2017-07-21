package controller;

import dao.UserDao;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.springframework.core.io.Resource;


@Controller
public class ImageController {
    @Autowired
    UserDao userDao;

    @Value(value = "classpath:images/user.jpg")
    private Resource deafault_image;

    @ResponseBody
    @RequestMapping(value ="/images/{u}")
    void displayImage(@PathVariable String u, HttpServletResponse response) throws IOException {
        User user=userDao.findById(Integer.parseInt(u));
        System.out.println("in image");
        System.out.println(user);
        if(user.getPhoto()!=null) {
            byte[] content = user.getPhoto();
            response.setContentType("Content-Type:image/*");
            response.setContentLength(content.length);
            response.getOutputStream().write(content);
        }
      /*  else{
            Resource resource = applicationContext.getResource("classpath:xyz.xml");
            InputStream is = resource.getInputStream();
            FileSystemResource resource = new FileSystemResource("/WEB-INF/content/somecontent.txt");
            File imgPath = new File(ImageName);
            BufferedImage bufferedImage = ImageIO.read(imgPath);

            // get DataBufferBytes from Raster
            WritableRaster raster = bufferedImage .getRaster();
            DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();

            return ( data.getData() );
        }*/
    }
}



/*<%
        String filename = "knowledgeCenter.zip";
        String filepath = "d:\\projects\\";
        response.setContentType("APPLICATION/OCTET-STREAM");
        response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");
        java.io.FileInputStream fileInputStream=new java.io.FileInputStream(filepath + filename);

        int i;
        while ((i=fileInputStream.read()) != -1) {
        out.write(i);
        }
        fileInputStream.close();
        %>

        request.getRealPath("/");*/
