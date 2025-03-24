package site.easy.to.build.crm.controller.api;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.easy.to.build.crm.entity.Seuil;
import site.easy.to.build.crm.service.seuil.SeuilService;

@RestController
@RequestMapping("api/seuil")
public class SeuilRestController {

    private final SeuilService seuilService;

    @Autowired
    public SeuilRestController(SeuilService seuilService) {
        this.seuilService = seuilService;
    }

    @GetMapping("/get")
    public BigDecimal getSeuilTauxAlerte() {
        Seuil seuilActuel = seuilService.getSeuilActuel();
        return seuilActuel != null ? seuilActuel.getTaux() : BigDecimal.ZERO;
    }

    @PostMapping("/set")
    public void setSeuilTauxAlerte(@RequestParam BigDecimal seuil) {
        Seuil newSeuil = new Seuil(seuil, LocalDateTime.now());
        seuilService.addSeuil(newSeuil);
    }
}
