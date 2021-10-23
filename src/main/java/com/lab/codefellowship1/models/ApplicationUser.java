package com.lab.codefellowship1.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class ApplicationUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String username;
    String password;
    String firstName;
    String lastName;
    Date dateOfBirth;
    String bio;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "Uusers")
    List<Post> posts;


    public ApplicationUser(){}

    public ApplicationUser(String username, String password, String firstName, String lastName, Date dateOfBirth,
                           String bio){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
    }

    public List<Post> getPosts() {
        return posts;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }

    public String getBio() {
        return this.bio;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "follower_follower", joinColumns = @JoinColumn(name = "from_id"), inverseJoinColumns = @JoinColumn(name = "to_id"))
    List<ApplicationUser> followers = new ArrayList<>();
    @ManyToMany(mappedBy = "followers")
    List<ApplicationUser> following = new ArrayList<>();

    public List<ApplicationUser> getFollowers() {
        return followers;
    }

    public List<ApplicationUser> getFollowing() {
        return following;
    }

    public void setFollowers(List<ApplicationUser> followers) {
        this.followers = followers;
    }


    public void setFollowing(List<ApplicationUser> following) {
        this.following = following;
    }
}
