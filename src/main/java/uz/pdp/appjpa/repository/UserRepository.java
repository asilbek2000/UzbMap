package uz.pdp.appjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appjpa.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {
}
