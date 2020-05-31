package com.caueruleum.pshop.admin.service;

import com.caueruleum.pshop.admin.dto.AdminProductDTO;
import com.caueruleum.pshop.entity.Product;

public interface AdminProductService
{

	public Product handleMerge(AdminProductDTO dto);

}
