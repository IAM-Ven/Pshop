package com.caueruleum.pshop.admin.controller.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.caueruleum.pshop.admin.dto.AdminProductDTO;
import com.caueruleum.pshop.admin.service.AdminProductService;
import com.caueruleum.pshop.entity.Category;
import com.caueruleum.pshop.entity.Image;
import com.caueruleum.pshop.entity.Order;
import com.caueruleum.pshop.entity.Product;
import com.caueruleum.pshop.model.PageModel;
import com.caueruleum.pshop.service.CategoryService;
import com.caueruleum.pshop.service.ImageService;
import com.caueruleum.pshop.service.OrderService;
import com.caueruleum.pshop.service.ProductService;

@Controller
@RequestMapping("/admin/database/product/")
public class AdminProductController
{
	@Value("${pshop.defaults.admin.page.size}")
	private int pageSize;

	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private AdminProductService adminProductService;
	
	@GetMapping
	public String showProductTable(@RequestParam(name="page", defaultValue="1")String page, Model model) 
	{
		int p = Integer.parseInt(page);
		
		PageModel<Product> product = productService.findAllPaginate(p, pageSize);
		
		model.addAttribute("products", product.getItems());
		
		return "admin/database/table/product-table";
		
	}
	
	/**
	 * This method handles the filling of the form.
	 * 
	 * @param id id the id of the project.
	 * null = We need to create one, so we give a blank form
	 * Integer = We 
	 * @param model
	 * @return
	 */
	@GetMapping(value= {"/cu/", "/cu/{id}"})
	public String showForm(@PathVariable(required=false) Integer id, Model model) 
	{
		
		AdminProductDTO dto = new AdminProductDTO();

		if(id != null) 
		{
			Product product = this.productService.findById(id);
			
			if(product == null) 
			{
				model.addAttribute("error", "doesNotExist");
				return "redirect:/admin/database/product/";
			}
			
			dto.setId(product.getId());
			dto.setTitle(product.getTitle());
			dto.setDescription(product.getDescription());
			dto.setPrice(product.getPrice());
			dto.setDate(product.getDate().toString());
			
			for(Category c: product.getCategories()) 
			{
				dto.addCategory(c.getId(), c.getName());
			}
			
			for(Order o: product.getOrders()) 
			{
				dto.addOrder(o.getId());
			}
			
			for(Image i: product.getImages()) 
			{
				dto.addImage(i.getId(), i.getPath());
			}
			
		}
		model.addAttribute("dto", dto);
		
		List<Category> categories = this.categoryService.findAll();		
		model.addAttribute("category", categories);
		
		List<Order> orders = this.orderService.findAll();
		model.addAttribute("order", orders);
		
		List<Image> images = this.imageService.findAll();
		model.addAttribute("image", images);
		
		
		return "admin/database/crud/product-crud";
	}
	
	@PostMapping("process-cu/")
	public String processCU(@ModelAttribute AdminProductDTO dto, RedirectAttributes attr, Model model) 
	{
		
		try 
		{
			Product product = this.adminProductService.handleMerge(dto);
			
			if(product == null) 
			{
				attr.addAttribute("success", false);
			}else 
			{
				attr.addAttribute("success", true);
			}
			
		}catch(Exception ex) 
		{
			attr.addAttribute("success", false);
		}
		
		return "redirect:/admin/database/product/";
	}
	
	@GetMapping("/delete/{id}")
	public String processDelete(@PathVariable Integer id, Model model, RedirectAttributes attr) 
	{
		Product product = this.productService.findById(id);
		
		if(product == null) 
		{
			model.addAttribute("success", false);
		}else 
		{
			try 
			{
				this.productService.delete(product);
				
				model.addAttribute("success", true);
			}catch (Exception ex) 
			{
				model.addAttribute("success", false);
			}
		}
		
		return "redirect:/admin/database/product/" + id;
		
	}
	
}
