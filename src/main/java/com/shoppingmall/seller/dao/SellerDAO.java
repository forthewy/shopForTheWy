package com.shoppingmall.seller.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.shoppingmall.seller.model.Seller;

@Repository
public interface SellerDAO {

	public int insertSeller(
			@Param("userId")int userId,
			@Param("shopName") String shopName,
			@Param("address") String address,
			@Param("shopPhoneNumber") String shopPhoneNumber);
	
	public List<Seller> selectSellerByState(String state);
	
	public Seller selectSellerByUserId(Integer userId);
	
	public int updateSellerByUserId(
			@Param("userId")int userId,
			@Param("shopName") String shopName,
			@Param("address") String address,
			@Param("shopPhoneNumber") String shopPhoneNumber,
			@Param("bannerImg") String bannerImg,
			@Param("shopMainImg") String shopMainImg);
	
	public Seller selectSellerById (int id);
	
	public List<Seller> selectSellerListByUserId(List<Integer> idList);
	
}
