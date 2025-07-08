package org.neobook.notebookservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.neobook.notebookservice.model.Grade;
import org.neobook.notebookservice.repository.GradeRepository;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;
import java.util.UUID;


@Testcontainers
@SpringBootTest(properties = "spring.profiles.active=test", webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@org.springframework.test.context.ActiveProfiles("test")
class GradeControllerIntegrationTest {

    @Container
    static MongoDBContainer mongo = new MongoDBContainer("mongo:6");

    @DynamicPropertySource
    static void mongoProps(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongo::getReplicaSetUrl);
    }

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private GradeRepository gradeRepository;

    private UUID studentId;

    @BeforeEach
    void setup() {
        gradeRepository.deleteAll().block();
        studentId = UUID.randomUUID();
        Grade g = new Grade();
        g.setStudentId(studentId);
        g.setTeacherId(UUID.randomUUID());
        g.setSubjectId(1L);
        g.setValue(5);
        g.setDate(LocalDate.now());
        gradeRepository.save(g).block();
    }

    @Test
    void getGradesReturnsData() {
        webTestClient.mutateWith(SecurityMockServerConfigurers.mockJwt().jwt(jwt -> jwt.claim("roles", java.util.List.of("ROLE_STUDENT"))))
                .get().uri(uriBuilder -> uriBuilder.path("/api/grades").queryParam("studentId", studentId).build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].studentId").isEqualTo(studentId.toString());
    }
}
