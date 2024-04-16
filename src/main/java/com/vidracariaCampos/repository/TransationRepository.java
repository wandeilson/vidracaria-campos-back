package com.vidracariaCampos.repository;
import com.vidracariaCampos.model.entity.StockTransation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
@Repository
public interface TransationRepository extends JpaRepository<StockTransation, UUID> {
}
