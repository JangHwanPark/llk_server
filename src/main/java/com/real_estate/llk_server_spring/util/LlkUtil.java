package com.real_estate.llk_server_spring.util;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.real_estate.llk_server_spring.Product.Entity.Product;
import com.real_estate.llk_server_spring.Product.Entity.ProductImgLinkList;
import com.real_estate.llk_server_spring.Product.Entity.ProductImgLinkListRepository;
import com.real_estate.llk_server_spring.exception.LlkServerException;
import com.real_estate.llk_server_spring.exception.LlkServerExceptionErrorCode;
import com.real_estate.llk_server_spring.review.entity.Review;
import com.real_estate.llk_server_spring.review.entity.ReviewRepository;
import com.real_estate.llk_server_spring.security.jwt.JWTUtil;
import com.real_estate.llk_server_spring.user.entity.Agent;
import com.real_estate.llk_server_spring.user.entity.AgentRepository;
import com.real_estate.llk_server_spring.user.entity.UserRepository;
import com.real_estate.llk_server_spring.user.entity.Users;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class LlkUtil {
    private final JWTUtil util;
    private final UserRepository userRepository;
    private final AgentRepository agentRepository;
    private final ReviewRepository reviewRepository;
    private final ProductImgLinkListRepository productImgLinkListRepository;
    private final AmazonS3Client amazonS3Client;

    @Value("${s3.bucket-name}")
    private String bucketName;
    private static final Logger logger = LogManager.getLogger(LlkUtil.class);

    public String usingRequestGetEmail(HttpServletRequest req) {
        String access = req.getHeader("Access");
        return util.getEmail(access);
    }

    public Users usingRequestGetUser(HttpServletRequest req) {
        String email = usingRequestGetEmail(req);
        return userRepository.findByEmail(email);
    }

    public Agent usingLicenseNumberGetAgent(String licenseNumber) {
        return agentRepository.findByLicenseNumber(licenseNumber)
                .orElseThrow(() -> new LlkServerException(HttpStatus.NOT_FOUND, LlkServerExceptionErrorCode.NOT_FOUND_AGENT));
    }

    public List<Review> usingAgentEntityGetReview(Agent agent) {
        return reviewRepository.findByAgent(agent);
    }

    public void usingStringDataValidationCheck(String str) throws LlkServerException{
        if (str == null || str.isEmpty()) {
            throw new LlkServerException(HttpStatus.BAD_REQUEST,LlkServerExceptionErrorCode.DONT_REQUEST_DATA);
        }
    }

    public void imgFileUploadProc(List<MultipartFile> fileList, Product product, String dirName) {
        try {
            List<ProductImgLinkList> productImgLinkLists = new ArrayList<>();
            for (MultipartFile file : fileList) {
                if(file != null) {
                    File uploadFile = convert(file)
                            .orElseThrow(() -> new LlkServerException(HttpStatus.INTERNAL_SERVER_ERROR,LlkServerExceptionErrorCode.FAIL_IMG_CONVERT));
                    ProductImgLinkList productImgLinkList = new ProductImgLinkList();
                    productImgLinkList.setImgLink(upload(uploadFile, dirName));
                    productImgLinkList.setProduct(product);
                    productImgLinkLists.add(productImgLinkList);
                    productImgLinkListRepository.save(productImgLinkList);
                }
            }
        } catch (LlkServerException e) {
            throw new LlkServerException(HttpStatus.INTERNAL_SERVER_ERROR, LlkServerExceptionErrorCode.FAIL_IMG_CONVERT);
        }
    }

    // S3로 파일 업로드하기
    private String upload(File uploadFile, String dirName) {
        String fileName = dirName + "/" + UUID.randomUUID(); // S3에 저장된 파일 이름
        String uploadImageUrl = putS3(uploadFile, fileName); // s3로 업로드
        removeNewFile(uploadFile); // 로컬에 저장된 File 삭제
        return uploadImageUrl;
    }

    // S3로 업로드
    private String putS3(File uploadFile, String fileName) {
        amazonS3Client.putObject(new PutObjectRequest(bucketName, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3Client.getUrl(bucketName, fileName).toString();
    }


    // 로컬에 저장된 이미지 지우기
    private void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            logger.info("Local:File delete success");
            return;
        }
        logger.info("Local: File delete fail");
    }

    //이미지 변환과정
    private Optional<File> convert(MultipartFile multipartFile) {
        try {
            File convertFile = new File(multipartFile.getOriginalFilename());
            // 바로 위에서 지정한 경로에 File이 생성됨 (경로가 잘못되었다면 생성 불가능)
            if (convertFile.createNewFile()) {
                try (FileOutputStream fos = new FileOutputStream(convertFile)) { // FileOutputStream 데이터를 파일에 바이트 스트림으로 저장하기 위함
                    fos.write(multipartFile.getBytes());
                }
                return Optional.of(convertFile);
            }
        } catch (IOException | LlkServerException e) {
            throw new LlkServerException(HttpStatus.INTERNAL_SERVER_ERROR, LlkServerExceptionErrorCode.FAIL_IMG_CONVERT);
        }

        return Optional.empty();
    }

    //s3 이미지 삭제
    public void deleteFile(String filename) {
        System.out.println("delete filename = " + filename);
        amazonS3Client.deleteObject(new DeleteObjectRequest(bucketName, filename));
        System.out.println(String.format("[%s] deletion complete", filename));

    }
}
