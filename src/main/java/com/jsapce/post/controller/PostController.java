package com.jsapce.post.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jsapce.post.model.Post;
import com.jsapce.post.service.PostService;

@Controller
public class PostController {

	private PostService postService;
	
	@Autowired
	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	@GetMapping("/")
	public String index() {
		return "redirect:/posts";
	}
	
	@GetMapping("/posts")
	public ModelAndView getPosts() {
		ModelAndView mv = new ModelAndView("posts");
		mv.addObject("posts", this.postService.getAll());
		return mv;
	}
	
	
	@GetMapping("/posts/{id}")
	public ModelAndView getPostDetails(@PathVariable("id") Long id) {
		
		ModelAndView mv = new ModelAndView("postDetails");
		mv.addObject("post", this.postService.getById(id).get());
		return mv;
	}
	
	@GetMapping("/new")
	public String getPostForm() {
		return "postForm";
	}
	
	
	@PostMapping("/new")
	public String savePost(@Valid Post post, BindingResult bindResult, RedirectAttributes redirectAttributes) {

		if(bindResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("message", "Verifique se os campos obrigatórios estão preenchidos!");
			return "redirect:/new";
		}
		
		this.postService.save(post);
		
		return "redirect:/posts";
		
	}
	
	
	
}
