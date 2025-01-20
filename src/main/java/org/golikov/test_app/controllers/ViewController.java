package org.golikov.test_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Контроллер для отображения веб-страниц
 */
@Controller
public class ViewController {
    @GetMapping("/")
    public String index() {
        return "forward:/index.html";
    }
}
