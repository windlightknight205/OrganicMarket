package com.project.shopapp.repositories;

import com.project.shopapp.models.Favourite;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavouriteRepository extends JpaRepository<Favourite, Long> {
    Optional<Favourite> findByUserIdAndProductId(Long userId, Long productId);
    @Transactional
    void deleteByUserIdAndProductId(Long userId, Long productId);
}
