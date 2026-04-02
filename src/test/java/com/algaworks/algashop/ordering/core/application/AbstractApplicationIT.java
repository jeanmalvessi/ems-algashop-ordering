package com.algaworks.algashop.ordering.core.application;

import com.algaworks.algashop.ordering.utils.TestcontainerPostgreSQLConfig;
import jakarta.transaction.Transactional;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestcontainerPostgreSQLConfig.class)
public abstract class AbstractApplicationIT {
}
