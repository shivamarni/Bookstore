package com.bridgelabz.bookstore.serviceimpl;


import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.exception.S3BucketException;
import com.bridgelabz.bookstore.repository.BookRepository;
import com.bridgelabz.bookstore.repository.SellerRepository;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.service.AmazonS3ClientService;
import com.bridgelabz.bookstore.utility.JWTUtility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.transaction.Transactional;

@Service
public class AmazonS3ClientServiceImpl {

	private String awsS3AudioBucket;
	private AmazonS3 amazonS3;
	// private static final Logger logger =
	// LoggerFactory.getLogger(AmazonS3ClientServiceImpl.class);
	
	@Autowired
	BookServiceImpl bookImpl;
	
	@Autowired
	BookRepository bookrepo;

	@Autowired
	public AmazonS3ClientServiceImpl(Region awsRegion, AWSCredentialsProvider awsCredentialsProvider,
			String awsS3AudioBucket) {
		this.amazonS3 = AmazonS3ClientBuilder.standard().withCredentials(awsCredentialsProvider)
				.withRegion(awsRegion.getName()).build();
		this.awsS3AudioBucket = awsS3AudioBucket;
	}

	@Async
	@Transactional
	public String uploadFileToS3Bucket(MultipartFile multipartFile, boolean enablePublicReadAccess, Long bookId)
			throws S3BucketException, BookStoreException {
		String fileName = multipartFile.getOriginalFilename();


		Book book = bookImpl.getBookById(bookId);
		String profile = "https://" + awsS3AudioBucket + ".s3.ap-south-1.amazonaws.com/" + fileName;
		try {
			// creating the file in the server (temporarily)
			File file = new File(fileName);
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(multipartFile.getBytes());
			fos.close();
			PutObjectRequest putObjectRequest = new PutObjectRequest(this.awsS3AudioBucket, fileName, file);
			enablePublicReadAccess=true;
			if (enablePublicReadAccess) {
				putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead);
			}
			this.amazonS3.putObject(putObjectRequest);
			book.setBookImage(profile);
			bookrepo.save(book);
			
			file.delete();
		} catch (Exception ex) {
			throw new S3BucketException("Image already exists with same name", HttpStatus.NOT_ACCEPTABLE);
		}
		return profile;
	}

//	@Async
//	@Transactional
//	public String deleteFileFromS3Bucket(String token) throws S3BucketException, UserException {
//		Long userId = (Long) jwtop.parseJWT(token);
//
//		User user = repository.getUserById(userId)
//				.orElseThrow(() -> new UserException("user not found", HttpStatus.NOT_FOUND));
//		String fullFileLink=user.getProfilePicLink();
//		String fileName=fullFileLink.substring(49);
//		try {
//			amazonS3.deleteObject(new DeleteObjectRequest(awsS3AudioBucket, fileName));
//		} catch (Exception ex) {
//			throw new S3BucketException("Image not found", HttpStatus.NOT_FOUND);
//		}
//		user.setProfilePicLink(null);
//		repository.registrationSave(user);
//		return fileName;
//	}

	
}
