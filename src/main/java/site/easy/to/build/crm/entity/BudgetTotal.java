package site.easy.to.build.crm.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "total_budget_par_client") // Nom de la vue SQL
public class BudgetTotal {

    @Id
    @Column(name = "customer_id")
    private int customerId;

    @Column(name = "total_budget")
    private double totalBudget;

    public BudgetTotal() {
    }

    // Getters et Setters
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(double totalBudget) {
        this.totalBudget = totalBudget;
    }
}
