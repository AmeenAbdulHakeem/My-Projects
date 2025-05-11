/*
 *       Project: FundFlow
 *       Team: 10
 *       @Authors: Abdulrahman Al Qaiwani, Ameen Ahmed, Ahmad Ahmadi, Yousuf Majwal
 *
 */

package org.team10.fundflow.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.team10.fundflow.Configs.Campaign;
import java.util.List;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Integer> {
    List<Campaign> findByUsername(String username);

    @Modifying
    @Query(value = "ALTER TABLE Campaign AUTO_INCREMENT = 1", nativeQuery = true)
    void resetIdSequence();
}