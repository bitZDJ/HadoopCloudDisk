package com.example.controller;

import com.example.entity.File;
import com.example.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.channels.FileChannel;
import java.util.Date;
import java.util.List;

@Controller
public class FileController {

    @Autowired
    FileService fileService;

    //用户查询得到所有文件信息，Get
    @RequestMapping(value = "/user_query",method = RequestMethod.GET)
    public String user_query(Model model, HttpSession session){
        String username = (String) session.getAttribute("name");
        List<File> list= fileService.selectAllBook(username);
        model.addAttribute("list",list);
        return "user_query";
    }

    //用户查看文件详情，get -bookId
    @RequestMapping(value = "/user_book_message" ,method = RequestMethod.GET)
    public String user_book_message(@RequestParam(value = "bookId") String bookId, Model model){
        System.out.println("在bookController里面bookId--"+bookId);
        File file = fileService.selectBook(bookId);
        System.out.println("在bookController里面返回的Book对象--"+ file.getfId());
        model.addAttribute("book", file);
        return "user_book_message";
    }

    //用户根据文件标号查文件，get -bookId   根据bookId查找的是唯一的
    @RequestMapping(value = "/user_query_by_fId" ,method = RequestMethod.GET)
    public String user_query_by_fId(@RequestParam("fId")  String fId ,Model model){
        List<com.example.entity.File> list= fileService.selectById(fId);
        model.addAttribute("list",list);
        return "user_query";
    }

    //用户根据种类查文件,get -Type
    @RequestMapping(value = "/user_query_by_fType" ,method = RequestMethod.GET)
    public String user_query_by_fType(@RequestParam("fType") String fType ,Model model){
        System.out.println(fType+"根据种类查，在userController");
        List<File> list= fileService.selectByType(fType);
        model.addAttribute("list",list);
        return "user_query";
    }

    //用户根据文件名查文件,get -fName
    @RequestMapping(value = "/user_query_by_fName",method = RequestMethod.GET)
    public String user_query_by_fName(@RequestParam("fName") String fName ,Model model){
        List<File> list= fileService.selectByBookName(fName);
        model.addAttribute("list",list);
        return "user_query";
    }

    /**
     * 文件上传功能
     * @param file
     * @throws IOException
     */
    @RequestMapping(value="/upload",method= RequestMethod.POST)
    public String uploadFile(HttpSession session,
                           MultipartFile file, HttpServletRequest request) throws IOException {
        String username = (String) session.getAttribute("name");
        System.out.println("用户名：" + username);
        String path = request.getSession().getServletContext().getRealPath("upload");
        path += '\\' + username;
        System.out.println("文件在服务器的路径：" + path);
        //文件名
        String fileName = file.getOriginalFilename();

        System.out.println("文件名：" + fileName);

        java.io.File dir = new java.io.File(path,fileName);
        if(!dir.exists()){
            dir.mkdirs();
        }
        //MultipartFile自带的解析方法
        file.transferTo(dir);

        //获取文件大小
        FileInputStream fis = new FileInputStream(dir);
        FileChannel fc = fis.getChannel();
        String size = getPrintSize(fc.size());

        //获取文件类型
        String type = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());

        //获取文件上传日期
        Date utilDate = new Date();
        java.sql.Date date = new java.sql.Date(utilDate.getTime());

        fileName = URLEncoder.encode(fileName, "UTF-8");
        fileService.addFile(fileName, type, size, date);
        int x = fileService.selectUpload(fileName);
        String id = String.valueOf(x);
        fileService.updateUpload(id, x);
        System.out.println("文件上传成功");
        fileService.insertUserFile(id, username);
        fis.close();
        return "user_query";
    }

    /**
     * 文件下载功能
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/down")
    public void down(HttpServletRequest request, HttpServletResponse response,
                     @RequestParam("fName") String fName, HttpSession session) throws Exception{
        String username = (String) session.getAttribute("name");
        String fileName = request.getSession().getServletContext().getRealPath("upload")+"\\"+username + "\\" + fName;
        System.out.println(fileName);
        //获取输入流
        InputStream bis = new BufferedInputStream(new FileInputStream(new java.io.File(fileName)));
        //假如以中文名下载的话
        String filename = fName;
        //转码，免得文件名中文乱码
        filename = URLEncoder.encode(filename,"UTF-8");
        //设置文件下载头
        response.addHeader("Content-Disposition", "attachment;filename=" + filename);
        //设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("multipart/form-data");
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        int len = 0;
        while((len = bis.read()) != -1){
            out.write(len);
            out.flush();
        }
        bis.close();
        out.close();
    }

    @RequestMapping("/delete")
    public String down(HttpServletRequest request, @RequestParam("fId") String fId,
                       @RequestParam("fName") String fName, HttpSession session){
        String username = (String) session.getAttribute("name");
        String fileName = request.getSession().getServletContext().getRealPath("upload") + "\\" + username + "\\" + fName;

        System.out.println(fileName);

        java.io.File file = new java.io.File(fileName);
        System.out.println("文件是否存在：" + file.exists());
        System.out.println("是否为文件：" + file.isFile());
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + fileName + "成功！");
            }
        }
        fileService.deleteFile(fId);
        fileService.deleteUserFile(fId, username);
        return "user_query";
    }


    public String getPrintSize(long size) {
        // 如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
        double value = (double) size;
        if (value < 1024) {
            return String.valueOf(value) + "B";
        } else {
            value = new BigDecimal(value / 1024).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
        }
        // 如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
        // 因为还没有到达要使用另一个单位的时候
        // 接下去以此类推
        if (value < 1024) {
            return String.valueOf(value) + "KB";
        } else {
            value = new BigDecimal(value / 1024).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
        }
        if (value < 1024) {
            return String.valueOf(value) + "MB";
        } else {
            // 否则如果要以GB为单位的，先除于1024再作同样的处理
            value = new BigDecimal(value / 1024).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
            return String.valueOf(value) + "GB";
        }
    }

}
