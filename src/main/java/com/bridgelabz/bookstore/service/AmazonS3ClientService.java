package com.bridgelabz.bookstore.service;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.bookstore.exception.S3BucketException;


public interface AmazonS3ClientService
{
   void uploadFileToS3Bucket(MultipartFile multipartFile, boolean enablePublicReadAccess) throws S3BucketException;

   void deleteFileFromS3Bucket(String fileName) throws S3BucketException;
}
