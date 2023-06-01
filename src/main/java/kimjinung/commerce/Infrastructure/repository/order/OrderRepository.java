package kimjinung.commerce.Infrastructure.repository.order;

import kimjinung.commerce.domain.Member;
import kimjinung.commerce.domain.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {

    Optional<Order> save(Order order);
    Optional<Order> findById(UUID id);
    Optional<List<Order>> findByMember(Member member);
}
