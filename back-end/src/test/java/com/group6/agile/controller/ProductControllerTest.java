package com.group6.agile.controller;

import com.group6.agile.dto.response.ProductResponse;
import com.group6.agile.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private List<ProductResponse> mockProducts;

    @BeforeEach
    void setUp() {
        ProductResponse product1 = new ProductResponse();
        product1.setId("prod1");
        product1.setName("T-Shirt");
        product1.setDescription("A comfortable cotton t-shirt.");
        product1.setImageUrl("https://example.com/tshirt.jpg");
        product1.setStar(4.5f);
        product1.setPrice(199);

        ProductResponse product2 = new ProductResponse();
        product2.setId("prod2");
        product2.setName("Jeans");
        product2.setDescription("Stylish denim jeans.");
        product2.setImageUrl("https://example.com/jeans.jpg");
        product2.setStar(4.0f);
        product2.setPrice(499);

        mockProducts = Arrays.asList(product1, product2);
    }

    @Test
    void testGetProductsByCategory_Success() throws Exception {
        when(productService.getProductsByCategory(anyString(), anyString(), anyString()))
                .thenReturn(mockProducts);

        mockMvc.perform(get("/api/products")
                        .param("categoryId", "cat1")
                        .param("sortBy", "price")
                        .param("sortOrder", "asc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("T-Shirt"))
                .andExpect(jsonPath("$[0].price").value(199))
                .andExpect(jsonPath("$[1].name").value("Jeans"))
                .andExpect(jsonPath("$[1].price").value(499));
    }

    @Test
    void testGetProductsByCategory_MissingCategoryId() throws Exception {
        when(productService.getProductsByCategory(null, anyString(), anyString()))
                .thenThrow(new IllegalArgumentException("Category ID is required"));

        mockMvc.perform(get("/api/products")
                        .param("sortBy", "price")
                        .param("sortOrder", "asc"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testGetProductsByCategory_SortByName() throws Exception {
        when(productService.getProductsByCategory(anyString(), anyString(), anyString()))
                .thenReturn(mockProducts);

        mockMvc.perform(get("/api/products")
                        .param("categoryId", "cat1")
                        .param("sortBy", "name")
                        .param("sortOrder", "asc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Jeans"))
                .andExpect(jsonPath("$[1].name").value("T-Shirt"));
    }
}