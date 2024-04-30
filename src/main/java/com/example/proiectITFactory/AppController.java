package com.example.proiectITFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AppController {
    @Autowired //se creeaza o instanta si se injecteaza direct in clasa
    private UserRepository repo;
    @GetMapping("")
    public String homePage() {
        return "index";
    }

    @GetMapping("/inregistrare")
    public String formularInregistrare(Model model) {
        model.addAttribute("user", new User());
        return "formular_inregistrare";
    }

    @PostMapping("/proces_inregistrare")
    public String procesInregistrare(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getParola());
        user.setParola(encodedPassword);
        repo.save(user);
    return "succes_la_inregistrare";
    }
    @GetMapping("/list_users")
    public String vizualizareUtilizatori(Model model) {
        List<User> listaUtilizatori = repo.findAll();
        model.addAttribute("listaUtilizatori", listaUtilizatori);
        return "utilizatori";
    }

    @GetMapping("/delete_user/{id}")
    public String stergeUtilizator(@PathVariable("id") Long id) {
        repo.deleteById(id);
        return "redirect:/list_users"; // Redirectăm utilizatorul către pagina cu lista actualizată de utilizatori
    }

    // Metoda pentru afișarea formularului de modificare a numelui
    @GetMapping("/edit_name/{id}")
    public String formularModificareNume(@PathVariable("id") Long id, Model model) {
        User user = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Utilizatorul nu a fost găsit"));
        model.addAttribute("user", user);
        return "formular_modificare_nume";
    }

    // Metoda pentru procesarea modificării numelui
    @PostMapping("/edit_name/{id}")
    public String procesModificareNume(@PathVariable("id") Long id, @RequestParam("nume") String nume) {
        User user = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Utilizatorul nu a fost găsit"));
        user.setNume(nume);
        repo.save(user);
        return "redirect:/list_users";
    }

    // Metoda pentru afișarea formularului de modificare a emailului
    @GetMapping("/edit_email/{id}")
    public String formularModificareEmail(@PathVariable("id") Long id, Model model) {
        User user = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Utilizatorul nu a fost găsit"));
        model.addAttribute("user", user);
        return "formular_modificare_email";
    }

    // Metoda pentru procesarea modificării emailului
    @PostMapping("/edit_email/{id}")
    public String procesModificareEmail(@PathVariable("id") Long id, @RequestParam("email") String email) {
        User user = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Utilizatorul nu a fost găsit"));
        user.setEmail(email);
        repo.save(user);
        return "redirect:/list_users";
    }


}
