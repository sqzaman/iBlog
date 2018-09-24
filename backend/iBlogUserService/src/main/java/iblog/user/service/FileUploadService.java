package iblog.user.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math.stat.descriptive.summary.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import iblog.customers.payload.ApiResponse;
import iblog.customers.payload.UploadFileResponse;
import iblog.user.domain.ProductImage;
import iblog.user.domain.User;
import iblog.user.repository.ProductImageRepository;
import iblog.user.repository.UserRepository;

@Service
public class FileUploadService {

	@Autowired
	private FileStorageService fileStorageService;
	@Autowired
	private ProductImageRepository productImageRepository;
	@Autowired
	private UserRepository productRepository;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResponseEntity<?> uploadImage(MultipartFile file, Long productId) {
		String fileName = fileStorageService.storeFile(file);

		String fileUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/productImage/").path(fileName)
				.toUriString();
		//Product product = productRepository.findByProductId(productId).orElse(null);
		User product = productRepository.findById(productId).orElse(null);
		if (product != null) {
			ProductImage productImage = productImageRepository
					.save(new ProductImage(product, fileName, fileUri, file.getContentType(), file.getSize()));

			//return new ResponseEntity<UploadFileResponse>(new UploadFileResponse(productImage.getFileName(),
			//		productImage.getFileUri(), productImage.getFileType(), productImage.getSize()), HttpStatus.OK);
			return new ResponseEntity<ProductImage>(productImage, HttpStatus.OK);

		} else {
			return new ResponseEntity(new ApiResponse(false, "Specified product is not available!"),
					HttpStatus.BAD_REQUEST);
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResponseEntity<?> uploadImages(MultipartFile[] files, Long productId) {

		//Product product = productRepository.findByProductId(productId).orElse(null);
		User product = productRepository.findById(productId).orElse(null);
		List<UploadFileResponse> uploadFileResponses = new ArrayList<>();
		if (product != null) {
			for (MultipartFile file : files) {
				String fileName = fileStorageService.storeFile(file);

				String fileUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/productImage/")
						.path(fileName).toUriString();

				ProductImage productImage = productImageRepository
						.save(new ProductImage());

				uploadFileResponses.add(new UploadFileResponse(productImage.getFileName(), productImage.getFileUri(),
						productImage.getFileType(), productImage.getSize()));

			}
		} else {
			return new ResponseEntity(new ApiResponse(false, "Specified product is not available!"),
					HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<List<UploadFileResponse>>(uploadFileResponses, HttpStatus.OK);

	}
}