package com.orderHub.orderStatusIntegration.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/orders")
    public String orderPage(Model model) {
        try {
            // To load a page
            model.addAttribute("pageStatus", "OK");
            return "order"; // To get templates/order.html
        } catch (Exception ex) {
            // To handle exception
            model.addAttribute("errorMessage",
                    "The Orders page is currently unavailable. Please try again later.");
            return "error";
        }
    }
}
