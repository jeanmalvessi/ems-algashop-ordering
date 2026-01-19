package com.algaworks.algashop.ordering.application;

import com.algaworks.algashop.ordering.utils.TestcontainerPostgreSQLConfig;
import jakarta.transaction.Transactional;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestcontainerPostgreSQLConfig.class)
public abstract class AbstractApplicationIT {
}
