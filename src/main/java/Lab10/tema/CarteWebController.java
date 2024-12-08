package Lab10.tema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CarteWebController {

    private final CarteRepository repository;

    // Constructor pentru injectarea dependenței CarteRepository
    @Autowired
    public CarteWebController(CarteRepository repository) {
        this.repository = repository;
    }

    // Răspunde cererii GET pentru URL-ul /lista-carti
    @GetMapping("/lista-carti")
    public String listaCarti(Model model) {
        // Adăugăm toate cărțile din repository în model
        model.addAttribute("carti", repository.findAll());
        model.addAttribute("mesaj", "Lista cărților preluate prin repository");
        return "carti";  // Trimitem pagina carti.html
    }

    // Operații POST pentru adăugare, modificare, ștergere
    @PostMapping("/operatii")
    public String operatii(@RequestParam String isbn, @RequestParam String titlu,
                           @RequestParam String autor, Model model,
                           @RequestParam(required = false) String adauga,
                           @RequestParam(required = false) String modifica,
                           @RequestParam(required = false) String sterge,
                           @RequestParam(required = false) String autorFiltru) {

        if (adauga != null) {
            // Adăugare carte
            if (isbn.isEmpty() || titlu.isEmpty() || autor.isEmpty()) {
                model.addAttribute("mesaj", "Adaugarea nu se realizează dacă nu completaţi toate caracteristicile!");
            } else {
                Carte carte = new Carte(isbn, titlu, autor);  // Creează obiectul Carte
                repository.save(carte);  // Salvează cartea în baza de date
                model.addAttribute("mesaj", "Adaugare realizata cu succes!");
            }
        } else if (modifica != null) {
            // Modificare carte
            Carte carteDeModificat = repository.findById(isbn).orElse(null);
            if (carteDeModificat != null) {
                carteDeModificat.setTitlu(titlu);
                carteDeModificat.setAutor(autor);
                repository.save(carteDeModificat);
                model.addAttribute("mesaj", "Cartea cu ISBN-ul " + isbn + " a fost modificata!");
            } else {
                model.addAttribute("mesaj", "Nu se gaseste nici o carte cu isbn-ul introdus.");
            }
        } else if (sterge != null) {
            // Ștergere carte
            Carte carteDeSters = repository.findById(isbn).orElse(null);
            if (carteDeSters != null) {
                repository.delete(carteDeSters);
                model.addAttribute("mesaj", "Cartea cu ISBN-ul " + isbn + " a fost stearsa!");
            } else {
                model.addAttribute("mesaj", "Nu se gaseste nici o carte cu isbn-ul introdus.");
            }
        } else if (autorFiltru != null && !autorFiltru.isEmpty()) {
            // Filtrare după autor
            model.addAttribute("carti", repository.findByAutor(autorFiltru));
            model.addAttribute("mesaj", "Cărțile următoare aparțin autorului " + autorFiltru);
        } else {
            // Dacă nu este selectată o acțiune, afișează toate cărțile
            model.addAttribute("carti", repository.findAll());
            model.addAttribute("mesaj", "Lista cărților preluate prin repository");
        }

        return "carti";  // Returnează pagina HTML carti.html actualizată
    }
}
