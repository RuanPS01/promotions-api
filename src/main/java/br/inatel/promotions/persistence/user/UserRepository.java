package br.inatel.promotions.persistence.user;

import br.inatel.promotions.persistence.user.User;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public interface UserRepository {
    void save(User user);

    List<User> findAll() throws ExecutionException, InterruptedException;

    Optional<User> findById(String id) throws ExecutionException, InterruptedException;

    void delete(String id) throws ExecutionException, InterruptedException;

    void update(User user);
}
