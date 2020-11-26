package com.codecool.accomodation.repository;

import com.codecool.accomodation.model.entity.Host;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class HostRepositoryTest {

    @Autowired
    private HostRepository repository;

    @Test
    public void test_saveNewHost_hasSizeOne() {
        Host host = Host.builder()
            .email("host@earth.com")
            .phone("0690666999")
            .build();

        repository.save(host);

        List<Host> hosts = repository.findAll();
        assertThat(hosts).hasSize(1);
    }
}