//package com.uniqtech.profiles.SpringBootProfilesProd.service;
//
//import java.io.File;
//import java.nio.file.Paths;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
//import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
//import software.amazon.awssdk.regions.Region;
//import software.amazon.awssdk.services.s3.S3Client;
//import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
//import software.amazon.awssdk.services.s3.model.PutObjectRequest;
//import software.amazon.awssdk.services.s3.model.PutObjectResponse;
//
//@Service
//public class S3Service {
//
//	private final S3Client s3Client;
//	private final String bucketName;
//
//	public S3Service(@Value("${aws.s3.access-key}") String accessKey, @Value("${aws.s3.secret-key}") String secretKey,
//			@Value("${aws.s3.region}") String region, @Value("${aws.s3.bucket-name}") String bucketName) {
//		this.bucketName = bucketName;
//		AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKey, secretKey);
//		this.s3Client = S3Client.builder().credentialsProvider(StaticCredentialsProvider.create(awsCreds))
//				.region(Region.of(region)).build();
//	}
//
//	public String uploadFile(String filePath) {
//		File file = new File(filePath);
//		String key = file.getName();
//
//		PutObjectRequest putObjectRequest = PutObjectRequest.builder().bucket(bucketName).key(key).build();
//
//		PutObjectResponse response = s3Client.putObject(putObjectRequest, Paths.get(filePath));
//		return response.eTag();
//	}
//
//}
