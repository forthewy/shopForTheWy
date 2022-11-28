package com.shoppingmall.bookmark;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shoppingmall.bookmark.bo.BookmarkBO;
import com.shoppingmall.bookmark.model.BookmarkView;

@RequestMapping("/bookmark")
@Controller
public class BookmarkController {

	@Autowired
	private BookmarkBO bookmarkBO;
	
	@RequestMapping("/bookmark_list_view")
	public String bookmarkListView(
			HttpSession session, Model model) {
		
		// 로그인되어 있지 않다면 인터셉터에서 로그인 화면 이동
		int userId = (int) session.getAttribute("userId");
		
		List<BookmarkView> bookmarkViewList = bookmarkBO.generateBookmarkViewListByUserId(userId);
		
		model.addAttribute("bookmarkViewList", bookmarkViewList);
		model.addAttribute("viewName", "bookmark/bookmarkList");
		
		return "template/layout";
	}
}
