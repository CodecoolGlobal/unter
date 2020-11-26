package com.codecool.accomodation.repository;

import com.codecool.accomodation.model.entity.Host;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class HostRepositoryTest {

    @Autowired
    private HostRepository repository;

    @Test
    public void test_saveNewHost_hasSizeOne() {
        Host host = Host.builder()
            .email("host1@earth.com")
            .phone("0690666999")
            .build();

        repository.save(host);

        List<Host> hosts = repository.findAll();
        assertThat(hosts).hasSize(1);
    }

    @Test
    public void test_saveHostsWithSameEmail_ThrowsException() {
        Host host = Host.builder()
            .email("host2@earth.com")
            .phone("0690666999")
            .build();

        repository.saveAndFlush(host);

        Host host2 = Host.builder()
            .email("host2@earth.com")
            .phone("0690666888")
            .build();

        assertThrows(DataIntegrityViolationException.class, () ->
            repository.saveAndFlush(host2));
    }

    @Test
    public void test_hostEmailShouldBeNotNull_ThrowsException() {
        Host host = Host.builder()
            .phone("0690666999")
            .build();

        assertThrows(DataIntegrityViolationException.class, () ->
            repository.saveAndFlush(host));
    }

    @Test
    public void test_hostPhoneShouldBeNotNull_ThrowsException() {
        Host host = Host.builder()
            .email("host@earth.com")
            .build();

        assertThrows(DataIntegrityViolationException.class, () ->
            repository.saveAndFlush(host));
    }
}