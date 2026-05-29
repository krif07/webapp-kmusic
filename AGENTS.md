# KMusic — Agent Guide

## Stack

- **Spring Boot 4.0.6** / **Java 25** / WAR packaging
- **Maven Wrapper** (`mvnw.cmd` on Windows, `mvnw` on Unix) — do not assume `mvn` is on PATH
- **H2 in-memory DB** (`jdbc:h2:mem:kmusicdb`) — schema drops on every restart (`ddl-auto=create-drop`)
- **JPA / Hibernate**, **Lombok**, **Datafaker 2.5.4**, **Hibernate Validator 8.0.3**

## Key commands

```bash
mvnw.cmd spring-boot:run          # start dev server
mvnw.cmd test                      # run all tests
mvnw.cmd test -Dtest=CancionServiceTest  # single test class
mvnw.cmd clean package             # build WAR
```

All from repo root. No flags needed. No `install` step required first.

## Project structure

```
co.dev.cfd.kmusic
├── KMusicApplication.java          # entrypoint
├── ServletInitializer.java         # WAR support
├── model/                          # JPA entities (Artista, Album, Cancion)
├── repository/                     # Spring Data JPA
└── service/                        # business logic (no controllers yet)
```

Relationships: `Artista 1──* Album 1──* Cancion`

## Quirks & gotchas

- **No controllers exist yet** — app is service-layer only. New features likely need a REST layer.
- `spring.jpa.open-in-view=false` — lazy loading outside `@Transactional` will throw `LazyInitializationException`
- `data.sql` runs on every startup (seeds 10 artists, 10 albums, 10 songs). Disable by setting `spring.sql.init.mode=never`.
- H2 Console enabled at `/h2-console` (JDBC URL: `jdbc:h2:mem:kmusicdb`)
- Service methods named in Spanish: `obtenerXxxPorId()`, `guardarXxx()`, `listarXxx()`, `eliminarXxx()`
- `@Modifying` is used on `CancionService.eliminarCancion()` (works but atypical — normally on `@Query` in repositories)
- `Artista.nacionalidad` is `@NotBlank` — tests/seed must always provide it; other `Artista` fields are optional
- `Album.canciones` has `cascade=CascadeType.ALL` + `orphanRemoval=true` — deleting an album deletes its songs
- `EntityNotFoundException` comes from `jakarta.persistence` (not javax)

## Tests

- All tests are `@SpringBootTest` integration tests (no `@WebMvcTest`, no Mockito unit tests)
- `CancionServiceTest` sets up an `Artista` in `@BeforeEach` — adding more tests should follow same pattern
- Test class naming: `{Class}Test` (not `{Class}Tests`)

## Default

```bash
mvnw.cmd clean test
```
