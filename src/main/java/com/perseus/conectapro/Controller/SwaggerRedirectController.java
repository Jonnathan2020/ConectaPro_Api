package com.perseus.conectapro.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class SwaggerRedirectController {
    @GetMapping("/")
    public RedirectView redirectToSwagger() {
        // Redireciona para o Swagger UI
        return new RedirectView("/swagger-ui.html");
    }
}
