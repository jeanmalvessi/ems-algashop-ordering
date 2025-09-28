package com.algaworks.algashop.ordering.domain.model.order;

import com.algaworks.algashop.ordering.domain.model.commons.Document;
import com.algaworks.algashop.ordering.domain.model.commons.FullName;
import com.algaworks.algashop.ordering.domain.model.commons.Phone;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

class RecipientTest {

    @Test
    void shouldCreateRecipientWithValidData() {
        Recipient recipient = Recipient.builder()
                .fullName(new FullName("John", "Doe"))
                .document(new Document("12345678900"))
                .phone(new Phone("1234567890"))
                .build();

        assertThat(recipient).isNotNull();
        assertThat(recipient.fullName()).isEqualTo(new FullName("John", "Doe"));
        assertThat(recipient.document()).isEqualTo(new Document("12345678900"));
        assertThat(recipient.phone()).isEqualTo(new Phone("1234567890"));
    }

    @Test
    void shouldNotCreateRecipientWithNullFullName() {
        assertThatNullPointerException().isThrownBy(() ->
            Recipient.builder()
                .fullName(null)
                .document(new Document("12345678900"))
                .phone(new Phone("1234567890"))
                .build()
        );
    }

    @Test
    void shouldNotCreateRecipientWithNullDocument() {
        assertThatNullPointerException().isThrownBy(() ->
            Recipient.builder()
                .fullName(new FullName("John", "Doe"))
                .document(null)
                .phone(new Phone("1234567890"))
                .build()
        );
    }

    @Test
    void shouldNotCreateRecipientWithNullPhone() {
        assertThatNullPointerException().isThrownBy(() ->
            Recipient.builder()
                .fullName(new FullName("John", "Doe"))
                .document(new Document("12345678900"))
                .phone(null)
                .build()
        );
    }
}
