package site.easy.to.build.crm.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import site.easy.to.build.crm.entity.Lead;
import site.easy.to.build.crm.service.lead.LeadService;

@Controller
@RequestMapping("api/leads")
public class LeadRestController {
    @Autowired
    private LeadService leadService;

    @GetMapping("/text")
    public String getText() {
        return "i love you";
    }

    @GetMapping("/all")
    public List<Lead> getAllLeads() {
        return leadService.findAll();
    }

    @PutMapping("/update/{leadId}")
    public ResponseEntity<Lead> updateLead(@PathVariable int leadId, @RequestBody Lead leadDetails) {
        Lead lead = leadService.findByLeadId(leadId);
        if (lead == null) {
            return ResponseEntity.notFound().build();
        }
        lead.setName(leadDetails.getName());
        lead.setStatus(leadDetails.getStatus());
        lead.setPhone(leadDetails.getPhone());
        lead.setMeetingId(leadDetails.getMeetingId());
        lead.setGoogleDrive(leadDetails.getGoogleDrive());
        lead.setGoogleDriveFolderId(leadDetails.getGoogleDriveFolderId());
        lead.setLeadActions(leadDetails.getLeadActions());
        lead.setFiles(leadDetails.getFiles());
        lead.setGoogleDriveFiles(leadDetails.getGoogleDriveFiles());
        lead.setManager(leadDetails.getManager());
        lead.setEmployee(leadDetails.getEmployee());
        lead.setCustomer(leadDetails.getCustomer());
        lead.setCreatedAt(leadDetails.getCreatedAt());

        Lead updatedLead = leadService.save(lead);
        return ResponseEntity.ok(updatedLead);
    }

    @DeleteMapping("/delete/{leadId}")
    public ResponseEntity<Void> deleteLead(@PathVariable int leadId) {
        Lead lead = leadService.findByLeadId(leadId);
        if (lead == null) {
            return ResponseEntity.notFound().build();
        }
        leadService.delete(lead);
        return ResponseEntity.noContent().build();
    }
}