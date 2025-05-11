/*
 *       Project: FundFlow
 *       Team: 10
 *       @Authors: Abdulrahman Al Qaiwani, Ameen Ahmed, Ahmad Ahmadi, Yousuf Majwal
 *
 */

package org.team10.fundflow.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.team10.fundflow.Configs.Campaign;
import org.team10.fundflow.Services.CampaignService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/campaign")
public class CampaignController {

    @Autowired
    private CampaignService campaignService;

    @Autowired
    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @GetMapping
    public List<Campaign> getAllCampaigns() {
        return campaignService.getAllCampaigns();
    }

    @GetMapping("/username")
    public Optional<List<Campaign>> getCampaignByUsername(@RequestParam String username) {
        return Optional.ofNullable(campaignService.getCampaignsByUsername(username));
    }

    @GetMapping("/{id}")
    public Optional<Campaign> getCampaignById(@PathVariable int id) {
        return campaignService.getCampaignById(id);
    }

    @PostMapping
    public Campaign createCampaign(@RequestBody Campaign campaign) {
        return campaignService.saveCampaign(campaign);
    }

    @PutMapping("/{id}")
    public Campaign updateCampaign(@PathVariable int id, @RequestBody Campaign campaign) {
        return campaignService.updateCampaign(id, campaign);
    }

    @DeleteMapping("/{id}")
    public void deleteCampaign(@PathVariable int id) {
        campaignService.deleteCampaign(id);
    }

    @DeleteMapping("/deleteall")
    public void deleteAllCampaigns() {
        campaignService.deleteAllAndResetSequence();
    }
}
