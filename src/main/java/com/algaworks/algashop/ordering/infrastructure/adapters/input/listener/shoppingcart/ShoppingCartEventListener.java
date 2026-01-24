package com.algaworks.algashop.ordering.infrastructure.adapters.input.listener.shoppingcart;

import com.algaworks.algashop.ordering.core.domain.model.shoppingcart.ShoppingCartCreatedEvent;
import com.algaworks.algashop.ordering.core.domain.model.shoppingcart.ShoppingCartEmptiedEvent;
import com.algaworks.algashop.ordering.core.domain.model.shoppingcart.ShoppingCartItemAddedEvent;
import com.algaworks.algashop.ordering.core.domain.model.shoppingcart.ShoppingCartItemRemovedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ShoppingCartEventListener {

    @EventListener
    public void listen(ShoppingCartCreatedEvent event) {
        log.info("ShoppingCartCreatedEvent listener");
    }

    @EventListener
    public void listen(ShoppingCartEmptiedEvent event) {
        log.info("ShoppingCartEmptiedEvent listener");
    }

    @EventListener
    public void listen(ShoppingCartItemAddedEvent event) {
        log.info("ShoppingCartItemAddedEvent listener");
    }

    @EventListener
    public void listen(ShoppingCartItemRemovedEvent event) {
        log.info("ShoppingCartItemRemovedEvent listener");
    }
}
