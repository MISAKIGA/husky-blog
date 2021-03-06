package com.misakiga.husky.uc.model;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author MISAKIGA
 */
public class Authorize {

    private Collection<String> resources = new ArrayList<>();
    private Collection<String> roles = new ArrayList<>();

    public Collection<String> getResources(){return resources;}

    public void setResources(Collection<String> resources) {
        this.resources = resources;
    }

    public Collection<String> getRoles() {
        return roles;
    }

    public void setRoles(Collection<String> roles) {
        this.roles = roles;
    }
}
