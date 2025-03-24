package site.easy.to.build.crm.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.easy.to.build.crm.entity.Budget;
import site.easy.to.build.crm.entity.BudgetTotal;
import site.easy.to.build.crm.service.budget.BudgetTotalService;

import java.util.List;

@RestController
@RequestMapping("api/budgets")
public class BudgetTotalRestController {

    private final BudgetTotalService budgetService;

    @Autowired
    public BudgetTotalRestController(BudgetTotalService budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping("/all")
    public List<BudgetTotal> getAllBudgets() {
        return budgetService.findAll();
    }
    @GetMapping("/total")
    public double getTotalBudget() {
        return budgetService.getTotalBudget();
    }
}
