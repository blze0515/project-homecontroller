package com.bootreact.hmct.controller.review;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bootreact.hmct.dto.InquiryDTO;
import com.bootreact.hmct.dto.ResponseDTO;
import com.bootreact.hmct.dto.ReviewDTO;
import com.bootreact.hmct.entity.Review;
import com.bootreact.hmct.service.product.ProductService;
import com.bootreact.hmct.service.review.ReviewService;


@RestController
@RequestMapping("/api/review")
public class ReviewController {
	private static final Review[] ReviewList = null;

	@Autowired
	ProductService productService; 
	
	@Autowired
	ReviewService reviewService;
	
//	상품평 생성
	@PostMapping("/insertReview")
	public void insertReview(@RequestBody Map<String, String> paramMap, @AuthenticationPrincipal String userId) {
		try {
			//상품평 추가 처리하기
			reviewService.addReview(userId,
									paramMap.get("commonCode"),
									Integer.parseInt(paramMap.get("productNo")),
									Integer.parseInt(paramMap.get("orderNo")),
									Integer.parseInt(paramMap.get("reviewGrade")),
									paramMap.get("reviewContent"),
								    paramMap.get("reviewTitle"));
			
//			//상품평 목록 받아오기
//			List<Review> ReviewList = reviewService.getReviewList();
//			List<ReviewDTO> reviewDTOList = new ArrayList<ReviewDTO>();
// 		
//			for(Review r : ReviewList) {
//				ReviewDTO reviewDTO = new ReviewDTO();
//				reviewDTO.setReviewNo(r.getReviewNo());
//				reviewDTO.setCommonCode(r.getCommonCode());
//				reviewDTO.setReviewTitle(r.getReviewTitle());
//				reviewDTO.setReviewRegdate(r.getReviewRegdate());
//				reviewDTO.setReviewGrade(r.getReviewGrade());
//				reviewDTO.setReviewContent(r.getReviewContent());
//				reviewDTO.setProductNo(r.getProductNo());
//				reviewDTO.setUser(r.getUser());
//				reviewDTOList.add(reviewDTO);
//			}
//			ResponseDTO<ReviewDTO> response = new ResponseDTO<>();
//			response.setData(reviewDTOList);		
//			return ResponseEntity.ok().body(response);
		}catch(Exception e){
			System.out.println(e.getMessage());
//			ResponseDTO<ReviewDTO> response = new ResponseDTO<>();
//			response.setError(e.getMessage());
//			return ResponseEntity.badRequest().body(response);
		}
}
	
//	상품평 조회
	@PostMapping("/getReviewList")
	public ResponseEntity<?> getReviewList() {
		try {
			//JPA 쓸 경우 - 리턴타입은 ResponseEntity<?>
			List<Review> reivewList = reviewService.getReviewList();
			List<ReviewDTO> reviewDTOList = new ArrayList<ReviewDTO>();
			for(Review r : reivewList) {
				ReviewDTO reviewDTO = new ReviewDTO();
				reviewDTO.setReviewNo(r.getReviewNo());
				reviewDTO.setReviewTitle(r.getReviewTitle());
				reviewDTO.setReviewContent(r.getReviewContent());
				reviewDTO.setReviewGrade(r.getReviewGrade());
				reviewDTO.setReviewRegdate(r.getReviewRegdate());
				
				reviewDTO.setProductNo(r.getOrderItem().getProductOption().getProduct().getProductNo());
				reviewDTO.setCommonCode(r.getOrderItem().getProductOption().getCommon().getCommonCodeName());
				reviewDTO.setUserId(r.getOrderItem().getOrder().getUser().getUserId());
				reviewDTOList.add(reviewDTO);
			}
		ResponseDTO<ReviewDTO> response = new ResponseDTO<>();
		response.setData(reviewDTOList);
		return ResponseEntity.ok().body(response);
			
			//매퍼 쓸 경우 - 리턴타입은 Map<String, Object>
//						- 매개변수는 @RequestParam int productNo
//			List<Map<String, Object>> reviewList = reviewService.getReviewListByProductNo(productNo);
//			
//			Map<String, Object> returnMap = new HashMap<String, Object>();
//			returnMap.put("reviewList", reviewList);
//
//			return returnMap; 
		
		}catch(Exception e) {
			System.out.println(e.getMessage());
			ResponseDTO<InquiryDTO> response = new ResponseDTO<>();
			response.setError(e.getMessage());
			return ResponseEntity.badRequest().body(response);
			
//    		Map<String, Object> errorMap = new HashMap<String, Object>();
//    		errorMap.put("error", e.getMessage());
//    		return errorMap;
		}
	}

//	상품평 수정
//	@PostMapping("/updateReview")
//	public ResponseEntity<?> updateReview(@RequestBody Map<String, String> paramMap) {
//		try {
//			
//			
//			//게시글 목록 받아오기
//			List<Review> reviewList = reviewService.getReviewList();
//			List<ReviewDTO> reviewDTOList = new ArrayList<ReviewDTO>();
// 		
//			for(Review r : reviewList) {
//				
//				ReviewDTO reviewDTO = new ReviewDTO();
//				reviewDTO.setReviewNo(r.getReviewNo());
//				reviewDTO.setReviewTitle(r.getReviewTitle());
//				reviewDTO.setReviewRegdate(r.getReviewRegdate());
//				reviewDTO.setReviewGrade(r.getReviewGrade());
//				reviewDTO.setReviewContent(r.getReviewContent());
//				reviewDTO.setProductNo(r.getProductNo());
//				reviewDTO.setUser(r.getUser());
//				
//				reviewDTOList.add(reviewDTO);
//			}
//		ResponseDTO<ReviewDTO> response = new ResponseDTO<>();//
//		
//		response.setData(reviewDTOList);
//		
//		return ResponseEntity.ok().body(response);
//		
//		}catch(Exception e){
//			System.out.println(e.getMessage());
//			ResponseDTO<InquiryDTO> response = new ResponseDTO<>();
//			response.setError(e.getMessage());
//			return ResponseEntity.badRequest().body(response);
//		}
//}

//	상품평 삭제
//	@DeleteMapping("/deleteReview")
//	public ResponseEntity<?> deleteReview(@RequestBody Map<String, String> paramMap) {
//		try {
//			//상품평 삭제 처리하기
//			reviewService.deleteReview(Integer.parseInt(paramMap.get("reviewNo")));
//			
//			//상품평 목록 받아오기
//			List<Review> reviewList = reviewService.getReviewList();
//			List<ReviewDTO> reviewDTOList = new ArrayList<ReviewDTO>();
// 		
//			for(Review r : reviewList) {
//				ReviewDTO reviewDTO = new ReviewDTO();
//				reviewDTO.setReviewNo(r.getReviewNo());
//				reviewDTO.setReviewTitle(r.getReviewTitle());
//				reviewDTO.setReviewRegdate(r.getReviewRegdate());
//				reviewDTO.setReviewGrade(r.getReviewGrade());
//				reviewDTO.setReviewContent(r.getReviewContent());
//				reviewDTO.setProductNo((int) r.getProductNo());
//				
//				reviewDTOList.add(reviewDTO);
//			}
//		ResponseDTO<ReviewDTO> response = new ResponseDTO<>();
//		
//		response.setData(reviewDTOList);
//		
//		return ResponseEntity.ok().body(response);
//		
//		}catch(Exception e){
//			System.out.println(e.getMessage());
//			ResponseDTO<InquiryDTO> response = new ResponseDTO<>();
//			response.setError(e.getMessage());
//			return ResponseEntity.badRequest().body(response);
//		}
//	}

	//제품의 상품평 별점 평균을 조회
	@GetMapping("/getAvgRevGradeByProductNo")
	public Map<String, Object> getAvgRevGradeByProductNo(@RequestParam int productNo) {		
		try {
			int avgRevGrade = reviewService.getAvgRevGradeByProductNo(productNo);
//			System.out.println("별점 평균 : " + avgRevGrade);
			Map<String, Object> returnMap = new HashMap<String, Object>();
			returnMap.put("avgRevGrade", avgRevGrade);
			return returnMap; 
		}catch(Exception e) {			
    		Map<String, Object> errorMap = new HashMap<String, Object>();
    		errorMap.put("error", e.getMessage());
    		return errorMap;
		}
	}
	
		
}
