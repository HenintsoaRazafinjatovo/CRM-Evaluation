package site.easy.to.build.crm.service.budget;

import java.util.List;

import org.springframework.stereotype.Service;

import site.easy.to.build.crm.entity.BudgetTotal;
import site.easy.to.build.crm.repository.BudgetTotalRepository;
@Service
public class BudgetTotalService {
    
    private final BudgetTotalRepository budgetTotalRepository;

    public BudgetTotalService(BudgetTotalRepository budgetTotalRepository) {
        this.budgetTotalRepository = budgetTotalRepository;
    }

    public List<BudgetTotal> findAll() {
        return budgetTotalRepository.findAll();
    }

    public BudgetTotal getTotalBudgetByCustomerId(int customerId) {
        return budgetTotalRepository.findByCustomerId(customerId).orElse(null);
    }
}