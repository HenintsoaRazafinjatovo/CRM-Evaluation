package site.easy.to.build.crm.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import site.easy.to.build.crm.entity.BudgetTotal;

import java.util.List;
import java.util.Optional;

@Repository
public interface BudgetTotalRepository extends JpaRepository<BudgetTotal, Integer> {
    
    Optional<BudgetTotal> findByCustomerId(int customerId);

    List<BudgetTotal> findAll();

    @Query("SELECT COALESCE(SUM(valeur), 0) FROM Budget")
    double getTotalBudget();
}
