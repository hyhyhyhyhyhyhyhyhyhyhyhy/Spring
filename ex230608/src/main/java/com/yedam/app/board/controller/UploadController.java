package com.yedam.app.board.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {

	@Value("${file.upload.path}")
	private String uploadPath;

	// 페이지 요청 파트
	@GetMapping("upload")
	public void getUploadPath() {
	}

	@PostMapping("/uploadsAjax")
	@ResponseBody
	public void uploadFile(@RequestPart MultipartFile[] uploadFiles) { 		// return List<String> 도 가능
							// @RequestPart (사용자가 보내온 멀티미디어를 받아옴) 배열 값으로 첨부파일을 처리하는 어노테이션 - 스프링에서만 지원
							// MultipartFile은 사용자가 보낸 데이터 정보를 담고 있음

//		List<String> imageList = new ArrayList<>();
		
		for (MultipartFile uploadFile : uploadFiles) {

			// 아래의 if 문을 쓰지 않으면 타입에 상관없이 모든 미디어 파일을 사용할 때 쓰는 걸 말함
			if (uploadFile.getContentType().startsWith("image") == false) {
				System.err.println("this file is not image type");
				return;
			}

			String originalName = uploadFile.getOriginalFilename(); // 사용자가 보낼 때 사용한 파일 이름
			String fileName = originalName.substring(originalName.lastIndexOf("//") + 1); // 실제 저장할 때는 파일명 충돌 방지를 위해 unique 하게 저장

			System.out.println("fileName : " + fileName);

			// 날짜 폴더 생성
			String folderPath = makeFolder();
			// UUID
			String uuid = UUID.randomUUID().toString();
			// 저장할 파일 이름 중간에 "_"를 이용하여 구분
			
			String uploadFileName = folderPath + File.separator + uuid + "_" + fileName;
			
			String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;

			Path savePath = Paths.get(saveName);
			// Paths.get() 메서드는 특정 경로의 파일 정보를 가져옵니다.(경로 정의하기)
			System.out.println("path : " + saveName);
			try {
				uploadFile.transferTo(savePath);
				// uploadFile에 파일을 업로드 하는 메서드 transferTo(file) ★핵심★
			} catch (IOException e) {
				e.printStackTrace();
			}
			
//			// DB에 해당 경로 저장
			// 1) 사용자가 업로드할 때 사용한 파일명
			// 2) 실제 서버에 업로드할 때 사용한 경로
			String imagePath = uploadFileName.replace(File.separator, "/"); // File.separator 는 자바에서 읽을 수 있는 방식
			System.out.println(uploadFileName);
			System.out.println(imagePath);
//			imageList.add(setImagePath(uploadFileName));
//			
//			return imageList;

		}
	}

	private String makeFolder() {

		String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		// LocalDate를 문자열로 포멧 (for 파일이름)
		String folderPath = str.replace("/", File.separator);

		File uploadPathFoler = new File(uploadPath, folderPath);
		// File newFile= new File(dir,"파일명");

		if (uploadPathFoler.exists() == false) {
			uploadPathFoler.mkdirs();
			// 만약 uploadPathFolder가 존재하지않는다면 makeDirectory하라는 의미입니다.
			// mkdir(): 디렉토리에 상위 디렉토리가 존재하지 않을경우에는 생성이 불가능한 함수
			// mkdirs(): 디렉토리의 상위 디렉토리가 존재하지 않을 경우에는 상위 디렉토리까지 모두 생성하는 함수
		}
		return folderPath;
	}
	
	
	// DB에 해당 경로를 /로 저장하는 방법
	private String setImagePath(String uploadFileName) {
		return uploadFileName.replace(File.separator, "/");
	}

}
