package org.neobook.userservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.neobook.userservice.model.Student;
import org.neobook.userservice.repository.StudentRepository;
import org.neobook.userservice.service.KeycloakService;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Testcontainers
@SpringBootTest(properties = "spring.profiles.active=test", webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@org.springframework.test.context.ActiveProfiles("test")
class StudentControllerIntegrationTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:14")
            .withDatabaseName("user_service")
            .withUsername("postgres")
            .withPassword("")
            .withInitScript("schema.sql");

    @DynamicPropertySource
    static void datasourceProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentRepository studentRepository;

    @org.springframework.boot.test.mock.mockito.MockBean
    private KeycloakService keycloakService;

    private UUID kcId;

    @BeforeEach
    void setup() {
        studentRepository.deleteAll();
        Student s = new Student();
        kcId = UUID.randomUUID();
        s.setKeycloakUserId(kcId);
        s.setPhone("123");
        studentRepository.save(s);
        UserRepresentation user = new UserRepresentation();
        user.setFirstName("Test");
        user.setLastName("Student");
        org.mockito.Mockito.when(keycloakService.getUser(kcId)).thenReturn(user);
    }

    @Test
    @WithMockUser(roles = "TEACHER")
    void getStudentsReturnsData() throws Exception {
        mockMvc.perform(get("/api/students"))
                .andDo(org.springframework.test.web.servlet.result.MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].keycloakUserId").value(kcId.toString()));
    }
}
