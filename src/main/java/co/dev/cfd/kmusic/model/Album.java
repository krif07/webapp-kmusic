package co.dev.cfd.kmusic.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Album {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(nullable=false)
    private Long id;
    
    private String titulo;
    private String genero;
    private LocalDate fechaEstreno;

    @ManyToOne
    private Artista artista;
    
    @OneToMany
    private List<Cancion> canciones = new ArrayList<>();
}
