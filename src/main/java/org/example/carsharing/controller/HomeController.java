package org.example.carsharing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
// говорит Spring: «Этот класс будет обрабатывать HTTP-запросы и отдавать HTML-страницы».
public class HomeController {
    //Когда приходит GET-запрос на путь "/" (то есть главная страница сайта),
    // dolyhen viyivat etot metod index
    @GetMapping("/")
    // metod index budet vizivat html kogda pereidem na glavnuyu stranicu
   public String index(Model model) {
        // Эти строки — это способ передать переменные со значениями внутрь HTML.
        // atrubute name imya peremennoi atribut value znacheniya
       model.addAttribute("appName","Car-Sharing");
       model.addAttribute("healthUrl","/api/health");
       return "index"; // imya shablona bez rashireniya
        //Это имя HTML-шаблона, который Spring отдаёт браузеру.
        //Spring ищет файл:src/main/resources/templates/index.html
   }

}
