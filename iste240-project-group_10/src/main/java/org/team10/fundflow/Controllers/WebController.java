/*
 *       Project: FundFlow
 *       Team: 10
 *       @Authors: Abdulrahman Al Qaiwani, Ameen Ahmed, Ahmad Ahmadi, Yousuf Majwal
 *
 */

package org.team10.fundflow.Controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.team10.fundflow.Configs.Campaign;
import org.team10.fundflow.Services.CampaignService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WebController {

    @Autowired
    private CampaignService campaignService;

    @GetMapping("/")
    public String homepage() {
        return "index.html";
    }

    @GetMapping("/about")
    public String about() {
        return "about.html";
    }

    @GetMapping("/create")
    public String create() {
        return "create.html";
    }

    @GetMapping("/sponsors")
    public String sponsors(){
        return "sponsors.html";
    }

    @GetMapping("/campaigns")
    public String campaigns(){
        return "campaigns";
    }

    @GetMapping("/campaign")
    public String campaign(){
        return "campaign";
    }

    @GetMapping("/login")
    public String login(){
        return "login.html";
    }

    @GetMapping("/signup")
    public String signup(){
        return "signup.html";
    }

    @GetMapping("/mycampaigns")
    public String mycampaigns(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");

        if (username == null || username.isEmpty()) {
            username = "ANON";
        }

        List<Campaign> userCampaigns = campaignService.getCampaignsByUsername(username);

        List<Map<String, Object>> enhancedCampaigns = new ArrayList<>();

        for (Campaign campaign : userCampaigns) {
            Map<String, Object> campaignMap = new HashMap<>();

            campaignMap.put("id", campaign.getId());
            campaignMap.put("title", campaign.getTitle());
            campaignMap.put("name", campaign.getName());
            campaignMap.put("description", campaign.getDescription());
            campaignMap.put("category", campaign.getCategory());
            campaignMap.put("goal", campaign.getGoal());
            campaignMap.put("amountRaised", campaign.getAmountRaised());
            campaignMap.put("sponsor", campaign.getSponsor());
            campaignMap.put("username", campaign.getUsername());

            if (campaign.getEndDate() != null) {
                campaignMap.put("endDate", campaign.getEndDate().toString());
            } else {
                campaignMap.put("endDate", "");
            }

            // Calculate progress percentage
            double progressPercentage = (campaign.getAmountRaised() / campaign.getGoal()) * 100;
            campaignMap.put("progressPercentage", (int) Math.min(100, Math.round(progressPercentage)));

            enhancedCampaigns.add(campaignMap);
        }

        model.addAttribute("campaigns", enhancedCampaigns);
        model.addAttribute("username", username);

        model.addAttribute("equals", new Object() {
            public boolean equals(Object first, Object second) {
                return first != null && first.equals(second);
            }
        });

        return "userCampaigns";
    }

}
