package co.dev.cfd.kmusic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.dev.cfd.kmusic.service.AlbumService;

@SpringBootTest
public class AlbumServiceTest {

    @Autowired
    private AlbumService albumService;

    @BeforeEach
    void setup() {
        
    }

    @Test
    void test() {

    }
}
