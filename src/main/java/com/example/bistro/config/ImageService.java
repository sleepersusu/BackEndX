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
					
					mediaType = getImageMediaType(Membersbyte);
					
					HttpHeaders httpHeaders = new HttpHeaders();
					httpHeaders.setContentType(mediaType);
					
					return new ResponseEntity<byte[]>(Membersbyte, httpHeaders, HttpStatus.OK);
				}else {
					//return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
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
	private MediaType getImageMediaType(byte[] imageBytes) {
	    // JPEG 文件判断
	    if (imageBytes.length > 3 && imageBytes[0] == (byte) 0xFF && imageBytes[1] == (byte) 0xD8 && imageBytes[2] == (byte) 0xFF) {
	    	return MediaType.IMAGE_JPEG;
	    }
	    // PNG 文件判断
	    if (imageBytes.length > 4 && imageBytes[0] == (byte) 0x89 && imageBytes[1] == (byte) 0x50 && imageBytes[2] == (byte) 0x4E && imageBytes[3] == (byte) 0x47) {
	    	return MediaType.IMAGE_PNG;
	    }
	    // GIF 文件判断
	    if (imageBytes.length > 6 && imageBytes[0] == (byte) 0x47 && imageBytes[1] == (byte) 0x49 && imageBytes[2] == (byte) 0x46 && 
	        (imageBytes[3] == (byte) 0x38 && (imageBytes[4] == (byte) 0x39 || imageBytes[4] == (byte) 0x37)) && imageBytes[5] == (byte) 0x61) {
	        return MediaType.IMAGE_GIF;
	    }
	    // WEBP 文件判断
	    if (imageBytes.length > 3 && imageBytes[0] == (byte) 0x52 && imageBytes[1] == (byte) 0x49 && imageBytes[2] == (byte) 0x46 && imageBytes[3] == (byte) 0x46) {
	        return MediaType.valueOf("image/webp");
	    }
	    // 如果无法识别图片类型，返回 null
	    return null;
	}

}
