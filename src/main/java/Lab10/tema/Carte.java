package Lab10.tema;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Carte {
    @Id
    private String isbn;
    private String titlu;
    private String autor;

    // Constructor fără argumente (pentru JPA)
    public Carte() {
    }

    // Constructor cu argumente
    public Carte(String isbn, String titlu, String autor) {
        this.isbn = isbn;
        this.titlu = titlu;
        this.autor = autor;
    }

    // Getters și Setters
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
