//package com.codecool.accommodation.repository;
//
//import com.codecool.accommodation.model.entity.Address;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//@ActiveProfiles("test")
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//public class AddressRepositoryTest {
//
//    @Autowired
//    private AddressRepository addressRepository;
//
//    @Test
//    public void smokeTest() {
//        assertThat(addressRepository).isNotNull();
//    }
//
//    @Test
//    public void test_saveNewAddress_hasSizeIncreasesWithOne() {
//        List<Address> originalData = addressRepository.findAll();
//        Integer originalDataSize = originalData.size();
//
//        Address address = Address.builder()
//            .city("Budapest")
//            .street("Érc utca")
//            .houseNumber(3)
//            .zipCode("1032")
//            .build();
//
//        addressRepository.save(address);
//        List<Address> addresses = addressRepository.findAll();
//        assertThat(addresses).hasSize(originalDataSize + 1);
//    }
//
//    @Test
//    public void test_addressHouseNumberShouldBeNotNull_ThrowsException() {
//        Address address = Address.builder()
//            .city("Budapest")
//            .street("Érc utca")
//            .zipCode("1032")
//            .build();
//
//        assertThrows(DataIntegrityViolationException.class, () ->
//            addressRepository.saveAndFlush(address));
//    }
//
//    @Test
//    public void test_addressCityShouldBeNotNull_ThrowsException() {
//        Address address = Address.builder()
//            .street("Érc utca")
//            .houseNumber(3)
//            .zipCode("1032")
//            .build();
//
//        assertThrows(DataIntegrityViolationException.class, () ->
//            addressRepository.saveAndFlush(address));
//    }
//
//    @Test
//    public void test_addressStreetShouldBeNotNull_ThrowsException() {
//        Address address = Address.builder()
//            .city("Budapest")
//            .houseNumber(3)
//            .zipCode("1032")
//            .build();
//
//        assertThrows(DataIntegrityViolationException.class, () ->
//            addressRepository.saveAndFlush(address));
//    }
//
//    @Test
//    public void test_addressZipCodeShouldBeNotNull_ThrowsException() {
//        Address address = Address.builder()
//            .city("Budapest")
//            .street("Érc utca")
//            .houseNumber(3)
//            .build();
//
//        assertThrows(DataIntegrityViolationException.class, () ->
//            addressRepository.saveAndFlush(address));
//    }
//
//    @Test
//    public void test_saveSeveralAddresses_persistAll() {
//        List<Address> originalData = addressRepository.findAll();
//        Integer originalDataSize = originalData.size();
//
//        Address address1 = Address.builder()
//            .city("Budapest")
//            .street("Érc utca")
//            .houseNumber(3)
//            .zipCode("1032")
//            .build();
//
//        Address address2 = Address.builder()
//            .city("Budapest")
//            .street("Viador utca")
//            .houseNumber(3)
//            .zipCode("1034")
//            .build();
//
//        List<Address> newAddresses = Arrays.asList(address1, address2);
//        addressRepository.saveAll(newAddresses);
//
//        List<Address> addresses = addressRepository.findAll();
//        assertThat(addresses).hasSize(originalDataSize + newAddresses.size());
//    }
//}