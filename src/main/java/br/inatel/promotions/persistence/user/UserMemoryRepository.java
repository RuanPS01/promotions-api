package br.inatel.promotions.persistence.user;

import br.inatel.promotions.persistence.user.User;
import br.inatel.promotions.persistence.user.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

//@Component
public class UserMemoryRepository implements UserRepository {

    private Set<User> db = new HashSet<>();

    @Override
    public void save(User user) {
        db.add(user);
    }

    @Override
    public List<User> findAll() {
        return db.stream().toList();
    }

    @Override
    public Optional<User> findById(String id) {
        return db.stream()
                .filter(spl -> spl.getId().equals(id))
                .findFirst();
    }

    @Override
    public void delete(String id) {
        db.removeIf(spl -> spl.getId().equals(id));
    }

    @Override
    public void update(User user) {
        delete(user.getId());
        save(user);
    }
}
