package com.example.service;

import com.example.dao.FileDao;
import com.example.entity.File;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bookService")
public class FileService {
    @Autowired
    FileDao fileDao;

    /**
     * 查询单本图书信息
     * @param fId
     */
    public File selectBook(String fId){
        return  fileDao.selectBook(fId);
    }

    /**
     * 查询所有图书信息
     */
    public List<File> selectAllBook(String username){
        return fileDao.selectAllBook(username);
    }

    /**
     *根据书名查图书
     * @param fName
     */
    public List<File> selectByBookName(String fName){
        fName="%"+fName+"%";
        System.out.println(fName+"根据作者模糊查，在BookService");
        return fileDao.selectByBookName(fName);
    }

    /**
     * 根据种类查图书
     * @param fType
     */
    public List<File> selectByType(String fType){
        fType="%"+fType+"%";
        System.out.println(fType+"根据作者模糊查，在BookService");
        return fileDao.selectByType(fType);
    }

    public List<File> selectById(String fId){
        fId="%"+fId+"%";
        System.out.println(fId+"根据Id查模糊，在BookService");
        return fileDao.selectById(fId);
    }

    /**
     * 添加图书信息
     * @param file
     */
    public String  addBook(File file){
        fileDao.addBook(file);
        return "success";
    }

    /**
     * 删除图书
     * @param fId
     */
    public String deleteBook(String fId){
        try{
            fileDao.deleteBook(fId);
            return "success";
        }catch (Exception e){
            return "error";
        }
    }

    /**
     * 上传文件
     * @param fName
     * @param fType
     * @param fSize
     * @param fTime
     */
    public void addFile(String fName, String fType, String fSize, java.sql.Date fTime)
    {
        System.out.println(fName + "上传文件，在BookService");
        fileDao.addFile(fName,fType,fSize,fTime);

    }

    /**
     * 获取上传文件的id
     * @param fName
     */
    public int selectUpload(String fName)
    {
        return fileDao.selectUpload(fName);
    }

    /**
     * 将上传文件的fId插入数据库
     * @param fId
     * @param id
     */
    public void updateUpload(String fId, int id)
    {
        fileDao.updateUpload(fId, id);
    }

    /**
     * 将上传文件的fId、fullname插入数据库
     */
    public void insertUserFile(@Param("fId") String fId, @Param("username") String username){
        fileDao.insertUserFile(fId, username);
    }

    /**
     * 删除文件
     * @param fId
     */
    public void deleteFile(String fId){
        fileDao.deleteFile(fId);
    }

    /**
     * 删除用户文件
     */
    public void deleteUserFile(@Param("fId") String fId, @Param("username") String username){
        fileDao.deleteUserFile(fId, username);
    }
}