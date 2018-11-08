package com.dateTracker.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * The type User.
 */
@Entity(name = "User")
@Table(name = "user")
public class User {

    /**
     * Instance variables
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "skill_level")
    private int skillLevel;

    @Column(name = "primary_phone_number")
    private Integer primaryPhoneNumber;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_password")
    private String userPassword;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<TestScore> testScores = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    /**
     * Instantiates a new User.
     */
    public User() {
    }

    public User(String emailAddress, String firstName, String lastName, int skillLevel, int primaryPhoneNumber, String userName, String userPassword ) {
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.lastName = lastName;
        this.skillLevel = skillLevel;
        this.primaryPhoneNumber = primaryPhoneNumber;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets email address.
     *
     * @return the email address
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Sets email address.
     *
     * @param emailAddress the email address
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets skill level.
     *
     * @return the skill level
     */
    public int getSkillLevel() {
        return skillLevel;
    }

    /**
     * Sets skill level.
     *
     * @param skillLevel the skill level
     */
    public void setSkillLevel(int skillLevel) {
        this.skillLevel = skillLevel;
    }


    /**
     * Gets primary phone number.
     *
     * @return the primary phone number
     */
    public Integer getPrimaryPhoneNumber() {
        return primaryPhoneNumber;
    }

    /**
     * Sets primary phone number.
     *
     * @param primaryPhoneNumber the primary phone number
     */
    public void setPrimaryPhoneNumber(Integer primaryPhoneNumber) {
        this.primaryPhoneNumber = primaryPhoneNumber;
    }

    /**
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets user name.
     *
     * @param userName the user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets user password.
     *
     * @return the user password
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * Sets user password.
     *
     * @param userPassword the user password
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * Gets test scores.
     *
     * @return the test scores
     */
    public Set<TestScore> getTestScores() {
        return testScores;
    }

    /**
     * Sets test scores.
     *
     * @param testScores the test scores
     */
    public void setTestScores(Set<TestScore> testScores) {
        this.testScores = testScores;
    }

    /**
     * Add Test Score.
     *
     * @param testScore the test score
     */
    public void addTestScore(TestScore testScore) {
        testScores.add(testScore);
        testScore.setUser(this);
    }

    /**
     * Remove Test Score.
     *
     * @param testScore the test score
     */
    public void removeTestScore(TestScore testScore) {
        testScores.remove(testScore);
        testScore.setUser(null);
    }

    /**
     * Gets roles.
     *
     * @return the roles
     */
    public Set<Role> getRoles() {
        return roles;
    }

    /**
     * Sets roles.
     *
     * @param roles the roles
     */
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    /**
     * Add role.
     *
     * @param role the role
     */
    public void addRole(Role role) {
        roles.add(role);
        role.setUser(this);
    }

    /**
     * Remove role.
     *
     * @param role the role
     */
    public void removeRole(Role role) {
        roles.remove(role);
        role.setUser(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (skillLevel != user.skillLevel) return false;
        if (emailAddress != null ? !emailAddress.equals(user.emailAddress) : user.emailAddress != null) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (primaryPhoneNumber != null ? !primaryPhoneNumber.equals(user.primaryPhoneNumber) : user.primaryPhoneNumber != null)
            return false;
        if (userName != null ? !userName.equals(user.userName) : user.userName != null) return false;
        return userPassword != null ? userPassword.equals(user.userPassword) : user.userPassword == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (emailAddress != null ? emailAddress.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + skillLevel;
        result = 31 * result + (primaryPhoneNumber != null ? primaryPhoneNumber.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (userPassword != null ? userPassword.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", emailAddress='" + emailAddress + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", skillLevel=" + skillLevel +
                ", primaryPhoneNumber=" + primaryPhoneNumber +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", testScores=" + testScores +
                ", roles=" + roles +
                '}';
    }
}
