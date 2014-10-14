/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utcluj.sd.assign1.backend.business;

import edu.utcluj.sd.assign1.backend.model.User;
import edu.utcluj.sd.assign1.backend.repository.UserDAO;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Eniko
 */
@ManagedBean
@RequestScoped
public class LoginData {

   
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
    private String timezone;
 
  public LoginData() {
    }
  
 public String checkValidUser()
    { 
        User user = userDAO.getUserByUsername(username);
        this.id=user.getId();
        this.name=user.getName();
        this.address=user.getAddress();
        this.birthDate=user.getBirthDate();
        this.latitude=user.getLatitude();
        this.longitude=user.getLongitude();
        this.role=user.getRole();
        
        if(username.equalsIgnoreCase(user.getUsername()))
        {
 
            if(password.equals(user.getPassword())) {
                if (user.getRole().equals("admin")) {
                return "adminView";
                } else { 
                return "simpleView"; 
                }
                
            } else {
                return "index";
            }
        }
        else
        {
            return "index";
        }
    }
 
   public String getTimezone() {
          
	this.timezone=getTimezoneFromRequest();
        
        return timezone;
    }
   private String getTimezoneFromRequest() {
     try{
                String urlString = "http://www.earthtools.org/timezone-1.1/"+this.latitude+"/"+this.longitude;
                URL url = new URL(urlString);
               
        HttpURLConnection request1 = (HttpURLConnection) url.openConnection();
         request1.setRequestMethod("GET");
       
        request1.connect();


  	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
         dbf.setValidating(false);
      dbf.setIgnoringComments(false);
      dbf.setIgnoringElementContentWhitespace(true);
      dbf.setNamespaceAware(true);
		DocumentBuilder db = dbf.newDocumentBuilder();


       Document document;
                try {
                    document = db.parse(request1.getInputStream());
                
                    NodeList nodes = document.getElementsByTagName("timezone");
                    for (int itr = 0; itr < nodes.getLength(); itr++) {
                    Node node = nodes.item(itr);
                    Element eElement = (Element) node;
                    System.out.println("\nNode Name :" + node.getNodeName());
                    return eElement.getElementsByTagName("offset").item(0).getTextContent() + " " + eElement.getElementsByTagName("suffix").item(0).getTextContent();
                    }

                
                } catch (IOException ex) {
                    Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SAXException ex) {
                    Logger.getLogger(LoginData.class.getName()).log(Level.SEVERE, null, ex);
                }
                } catch (ParserConfigurationException | IOException pce) {
                        Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, pce);
		}
     return "";
   
   }
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
       public String getUsername() {
        return username;
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
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
}
