package com.example.bistro.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.bistro.members.Members;
import com.example.bistro.members.MembersRepository;
import com.example.bistro.menu.Menu;
import com.example.bistro.menu.MenuRepository;

@Service
public class ImageService {

	@Autowired
	private MembersRepository memberRepo;

	@Autowired
	private MenuRepository menuRepo;

	public void imageUpload(String type, Integer id, byte[] fileByte) {
		switch (type) {
		case "member": {
			Optional<Members> op = memberRepo.findById(id);
			if(op.isPresent()) {
				Members member = op.get();
				member.setMemberImg(fileByte);
				memberRepo.save(member);
			}						
			break;
		}

		case "menu": {
			Optional<Menu> op = menuRepo.findById(id);
			if(op.isPresent()) {
				Menu menu = op.get();
				menu.setProductImg(fileByte);
				menuRepo.save(menu);
			}
			
			break;
		
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + type);
		}

	}

	public ResponseEntity<byte[]> imageDownload(String type, Integer id) {
		switch (type) {
		case "member": {
			Optional<Members> op = memberRepo.findById(id);

			if (op.isPresent()) {

				Members Members = op.get();
				byte[] Membersbyte = Members.getMemberImg();
				if(Membersbyte!=null) {
					MediaType mediaType = MediaType.APPLICATION_OCTET_STREAM; // 默認為通用二進位流
					
					mediaType = MediaType.IMAGE_JPEG;
					
					HttpHeaders httpHeaders = new HttpHeaders();
					httpHeaders.setContentType(mediaType);
					
					return new ResponseEntity<byte[]>(Membersbyte, httpHeaders, HttpStatus.OK);
				}else {
					return ResponseEntity.status(HttpStatus.OK).build();
				}
			}
		}

		case "menu": {

			Optional<Menu> op = menuRepo.findById(id);

			if (op.isPresent()) {

				Menu menu = op.get();
				byte[] menubyte = menu.getProductImg();
				String originalFilename = menu.getProductImgUrl();

				String fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

				MediaType mediaType = MediaType.APPLICATION_OCTET_STREAM; // 默認為通用二進位流

				if (fileExtension.equalsIgnoreCase("png")) {
					mediaType = MediaType.IMAGE_PNG;
				} else if (fileExtension.equalsIgnoreCase("jpg") || fileExtension.equalsIgnoreCase("jpeg")) {
					mediaType = MediaType.IMAGE_JPEG;
				}

				HttpHeaders httpHeaders = new HttpHeaders();
				httpHeaders.setContentType(mediaType);

				return new ResponseEntity<byte[]>(menubyte, httpHeaders, HttpStatus.OK);
			}

		}
		default:
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		}
	}

}
