package com.example.my;



public class retrieveimagemodel
{
    String id, name, model,specs,ehrate,location, image,email,phonenumber;

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public retrieveimagemodel(String id, String name, String model, String specs, String ehrate, String location, String image, String email, String phonenumber) {
        this.id = id;
        this.name = name;
        this.model = model;
        this.specs = specs;
        this.ehrate = ehrate;
        this.location = location;
        this.image = image;
        this.email = email;
        this.phonenumber = phonenumber;
    }

    public retrieveimagemodel() {
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }

    public String getEhrate() {
        return ehrate;
    }

    public void setEhrate(String ehrate) {
        this.ehrate = ehrate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
