/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utcluj.sd.assign1.backend.business;

import edu.utcluj.sd.assign1.backend.model.User;
import edu.utcluj.sd.assign1.backend.repository.UserDAO;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;


/**
 *
 * @author Eniko
 */
@ManagedBean
@RequestScoped
public class UserData {
    @EJB 
    private UserDAO userDAO;

    private int id;
    private String name;
    private String username;
    private String password;
    private String address;
    private float latitude;
    private float longitude;
    private Date birthDate;
    private String role;
    private ArrayList<User> users;

  
 
 public UserData() {
     
 }
    
    public String insert() {
    if(userDAO.insertUser(new User(id,name,username,password,address,latitude,longitude,birthDate,role))) {
                return "adminView";
            } else {
                return "insertFailed";
                
            }           
        
    }
    
     public List<User> getUsers() {
        this.users = (ArrayList<User>) userDAO.getAllUsers();
        return users;
        
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
  
     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

     public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
  
     public String delete(User user) {
         if(userDAO.deleteUser(user)) {
        
                return "adminView";
            } else {
                return "insertFailed";
                
            }        
       
    }
      public String update() {
          if(userDAO.updateUser(new User(id,name,username,password,address,latitude,longitude,birthDate,role))) {
     
                return "adminView";
            } else {
                return "insertFailed";
                
            }        
       
    }
    public String updateView(User user){
        
       
        this.id=user.getId();
        this.name=user.getName();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.address=user.getAddress();
        this.birthDate=user.getBirthDate();
        this.latitude=user.getLatitude();
        this.longitude=user.getLongitude();
        this.role=user.getRole();
        return "updateView";
    }
    
    public String viewUser(User user){
        
        this.id=user.getId();
        this.name=user.getName();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.address=user.getAddress();
        this.birthDate=user.getBirthDate();
        this.latitude=user.getLatitude();
        this.longitude=user.getLongitude();
        this.role=user.getRole();
        return "viewUser";
    }
     
}
