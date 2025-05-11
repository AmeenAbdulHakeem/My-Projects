/*
*       Project: FundFlow
*       Team: 10
*       @Authors: Abdulrahman Al Qaiwani, Ameen Ahmed, Ahmad Ahmadi, Yousuf Majwal
*
*/

package org.team10.fundflow.Configs;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Campaign")
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title", length = 255, nullable = false)
    private String title;

    @Column(name = "amountRaised")
    private double amountRaised;

    @Column(name = "goal", nullable = false)
    private double goal;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "description", length = 2000, nullable = false)
    private String description;

    @Column(name = "category", length = 255, nullable = false)
    private String category;

    @Column(name = "sponsor", length = 255)
    private String sponsor;

    @Column(name = "start_date")
    private LocalDate startDate = LocalDate.now();

    @Column(name = "end_date")
    private LocalDate endDate;

    // creator of campaign
    @Column(name = "username")
    private String username;

    // constructors
    public Campaign() {}

    public Campaign(String title, double amountRaised, double fundraisingGoal, String name, String description, String sponsor, LocalDate endDate, String category, String username) {
        this.title = title;
        this.amountRaised = amountRaised;
        this.goal = fundraisingGoal;
        this.name = name;
        this.description = description;
        this.sponsor = sponsor;
        this.category = category;
        this.startDate = LocalDate.now();
        this.endDate = endDate;
        this.username = username;
    }

    // getters and setters
    public int getId() {
        return this.id;
    }
    public String getTitle() {
        return this.title;
    }
    public double getAmountRaised() {
        return this.amountRaised;
    }
    public double getGoal() {
        return this.goal;
    }
    public String getName() {
        return this.name;
    }
    public String getDescription() {
        return this.description;
    }
    public String getSponsor() {
        return this.sponsor;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setAmountRaised(double amountRaised) {
        this.amountRaised = amountRaised;
    }
    public void setGoal(double fundraisingGoal) {
        this.goal = fundraisingGoal;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }
    public LocalDate getStartDate() {
        return this.startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
