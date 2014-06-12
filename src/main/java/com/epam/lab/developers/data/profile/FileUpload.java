package com.epam.lab.developers.data.profile;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import com.epam.lab.developers.dao.impl.UserDAO;
import com.epam.lab.developers.data.DataHolder;
import com.epam.lab.developers.entity.User;

@WebServlet("/file-upload")
public class FileUpload extends HttpServlet {

    private static final long serialVersionUID = 1L;
    static final Logger logger = Logger.getLogger(FileUpload.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUpload() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        boolean isMultiPart = ServletFileUpload.isMultipartContent(request);// перевіряє тип даних
        if(!isMultiPart) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024 * 1024);
        File tempDir = (File)getServletContext().getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(tempDir);
        ServletFileUpload fileUpload = new ServletFileUpload(factory);
        fileUpload.setSizeMax(1024 * 1024 * 30);

        try {
            List items = fileUpload.parseRequest(request);
            Iterator iter = items.iterator();

            while(iter.hasNext()) {

                FileItem item = (FileItem)iter.next();
                if(item.isFormField()) {
                    processFormField(item);

                } else {
                    processUploadedFile(item, request);
                }

            }
        } catch(Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        response.sendRedirect(request.getContextPath() + "/profile");
    }

    private void processUploadedFile(FileItem item, HttpServletRequest request) throws Exception {
        File uploadetFile = null;
        if(!item.getName().equals("")) {
            do {

                System.out.println(item.getName() + "dfgdfgfd");
                Random k = new Random();
                int kk = k.nextInt(1000);// краще цього не читайте, логіки тут мало
                String path = getServletContext().getRealPath("resources/img/user/" + kk + item.getName());
                System.out.println(path);
                uploadetFile = new File(path);
                HttpSession session = request.getSession();
                User user = DataHolder.getInstance().getUserSessions().get(session);
                user.getInfo().setPhoto(kk + "" + item.getName());
                updateUser(user);
                logger.debug(user.getName() + " has changed photo");

            } while(uploadetFile.exists());

            uploadetFile.createNewFile();
            item.write(uploadetFile);
        }
    }

    private void processFormField(FileItem item) {
        System.out.println(item.getFieldName() + "=" + item.getString());
    }

    private void updateUser(User user) {
        new UserDAO().update(user);
    }
}
