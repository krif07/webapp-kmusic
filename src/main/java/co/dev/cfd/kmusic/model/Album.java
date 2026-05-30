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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Album {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(nullable=false)
    private Long id;
    
    @NotBlank(message = "El título no puede estar en blanco")
    @Size(min = 1, max = 255, message = "El título debe tener entre 1 y 255 caracteres")
    private String titulo;

    @NotBlank(message = "El género no puede estar en blanco")
    @Size(min = 2, max = 100, message = "El género debe tener entre 2 y 100 caracteres")
    private String genero;

    @NotNull(message = "La fecha de estreno no puede ser nula")
    private LocalDate fechaEstreno;

    @ManyToOne
    @JoinColumn(name="artista_id")
    @NotNull(message = "El artista no puede ser nulo")
    private Artista artista;
    
    @OneToMany(
        mappedBy="album",
        cascade=CascadeType.ALL,
        orphanRemoval=true
    )
    @ToString.Exclude
    private List<Cancion> canciones = new ArrayList<>();
}
