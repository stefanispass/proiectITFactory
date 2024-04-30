package com.example.proiectITFactory;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest //clasa de test va rula ca datajpatest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE) // folosim baza de date reala
@Rollback(value = false)
public class UserRepoTest {

@Autowired
    private UserRepository repo;

@Autowired
    private TestEntityManager entityManager;
//@Test
//    public void testCreateUser() {
//    User user = new User();
//    user.setEmail("mihai@gmail.com");
//    user.setParola("123Mihai.,");
//    user.setNume("Mircea");
//    user.setPrenume("Mihai");
//    user.setVarsta(29);
//    User savedUser = repo.save(user);
//
//    User existUser = entityManager.find(User.class, savedUser.getId());
//
//    assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
//}
@Test
    public void testFindUserByEmail() {
    String email = "asd@gmail.com";
    User user = repo.findByEmail(email);
    assertThat(user).isNotNull();
}

@Test
    public void testGetUserById() {
        Long userId = 1L; // ID-ul utilizatorului de test
        User user = repo.getUserById(userId);
        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo(userId); // Verificăm dacă ID-ul utilizatorului returnat este cel așteptat
    System.out.println(user.getNume());
    }

}
