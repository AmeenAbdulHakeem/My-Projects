/*
 *       Project: FundFlow
 *       Team: 10
 *       @Authors: Abdulrahman Al Qaiwani, Ameen Ahmed, Ahmad Ahmadi, Yousuf Majwal
 *
 */

package org.team10.fundflow.Configs;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "username", nullable = false, unique = true, length = 255)
    private String username;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "balance", nullable = false)
    private Double balance;

    // constructors
    public User() {}

    public User(String name, String username, String password, Double balance) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.balance = balance;
    }

    // getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Double getBalance() { return balance; }
    public void setBalance(Double balance) { this.balance = balance; }
}
