package site.easy.to.build.crm.service.depense;

import java.util.List;
import java.util.Optional;

import site.easy.to.build.crm.entity.Depense;
import site.easy.to.build.crm.entity.Lead;

public interface DepenseService {
    List<Depense> getAllDepenses();
    Optional<Depense> getDepenseById(Integer id);
    Depense saveDepense(Depense depense);
    void deleteDepense(Integer id);
    double getTotalDepenseByCustomerId(int customerId);
    double getTotalDepenseLeads(int month, int year);
    public List<Depense> getDepensesWithTickets();
    public List<Object[]> findTotalDepenseByCustomer();
    public List<Object[]> findTotalDepenseLeadsByCustomer();
    List<Depense> findDepenseByTicketId(int ticketId);
    List<Depense> findDepenseByLeadId(int leadId);

    void updateDepenseEtat(int depenseId, int newEtat);
    
}
