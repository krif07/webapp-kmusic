package co.dev.cfd.kmusic.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Album {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(nullable=false)
    private Long id;
    
    private String titulo;
    private String genero;
    private LocalDate fechaEstreno;

    @ManyToOne
    @JoinColumn(name="artista_id")
    private Artista artista;
    
    @OneToMany(
        mappedBy="album",
        cascade=CascadeType.ALL,
        orphanRemoval=true
    )
    private List<Cancion> canciones = new ArrayList<>();
}
