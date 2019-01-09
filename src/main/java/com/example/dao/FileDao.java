package com.example.dao;

import com.example.entity.File;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileDao {
    /**
     * 查询单本图书信息
     * @param fId
     */
    public File selectBook(String fId);

    /**
     * 查询所有图书信息
     */
    public List<File> selectAllBook(String username);

    /**
     *根据书名查图书
     * @param fName
     */
    public List<File> selectByBookName(String fName);

    /**
     * 根据种类查图书
     * @param fType
     */
    public List<File> selectByType(String fType);

    public List<File> selectById(String fId);
    /**
     * 添加图书信息
     * @param file
     */
    public void addBook(File file);

    /**
     * 删除图书
     * @param fId
     */
    public void deleteBook(String fId);

    /**
     * 上传文件
     */
    public void addFile(@Param("fName") String fName, @Param("fType") String fType,
                        @Param("fSize") String fSize, @Param("fTime") java.sql.Date fTime);

    /**
     * 获取上传文件的id
     * @param fName
     */
    public int selectUpload(String fName);

    /**
     * 将上传文件的fId插入数据库
     */
    public void updateUpload(@Param("fId") String fId, @Param("id") int id);

    /**
     * 将上传文件的fId、fullname插入数据库
     */
    public void insertUserFile(@Param("fId") String fId, @Param("username") String username);

    /**
     * 删除文件
     * @param fId
     */
    public void deleteFile(String fId);

    /**
     * 删除用户文件
     */
    public void deleteUserFile(@Param("fId") String fId, @Param("username") String username);
}
