/*
 *       Project: FundFlow
 *       Team: 10
 *       @Authors: Abdulrahman Al Qaiwani, Ameen Ahmed, Ahmad Ahmadi, Yousuf Majwal
 *
 */

package org.team10.fundflow.Services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.team10.fundflow.Configs.Campaign;
import org.team10.fundflow.Repositories.CampaignRepository;
import java.util.List;
import java.util.Optional;

@Service
public class CampaignService {

    @Autowired
    private CampaignRepository campaignRepository;

    public List<Campaign> getAllCampaigns() {
        return campaignRepository.findAll();
    }

    // New method to find campaigns by username
    public List<Campaign> getCampaignsByUsername(String username) {
        return campaignRepository.findByUsername(username);
    }

    public Optional<Campaign> getCampaignById(int id) {
        return campaignRepository.findById(id);
    }

    public Campaign saveCampaign(Campaign campaign) {
        return campaignRepository.save(campaign);
    }

    public Campaign updateCampaign(int id, Campaign campaignDetails) {
        Campaign campaign = campaignRepository.findById(id).orElse(null);
        if (campaign != null) {
            if (campaignDetails.getTitle() != null) {
                campaign.setTitle(campaignDetails.getTitle());
            }

            if (campaignDetails.getAmountRaised() >= 0) {
                campaign.setAmountRaised(campaignDetails.getAmountRaised());
            }

            if (campaignDetails.getGoal() > 0) {
                campaign.setGoal(campaignDetails.getGoal());
            }

            if (campaignDetails.getName() != null) {
                campaign.setName(campaignDetails.getName());
            }

            if (campaignDetails.getDescription() != null) {
                campaign.setDescription(campaignDetails.getDescription());
            }

            if (campaignDetails.getEndDate() != null) {
                campaign.setEndDate(campaignDetails.getEndDate());
            }

            if (campaignDetails.getSponsor() != null) {
                campaign.setSponsor(campaignDetails.getSponsor());
            }

            if (campaignDetails.getCategory() != null) {
                campaign.setCategory(campaignDetails.getCategory());
            }

            if (campaignDetails.getUsername() != null) {
                campaign.setUsername(campaignDetails.getUsername());
            }

            return campaignRepository.save(campaign);
        }
        return null;
    }

    public void deleteCampaign(int id) {
        campaignRepository.deleteById(id);
    }

    private void deleteAllCampaigns() {
        campaignRepository.deleteAll();
    }

    @Transactional
    public void deleteAllAndResetSequence() {
        deleteAllCampaigns();
        campaignRepository.resetIdSequence();
    }
}