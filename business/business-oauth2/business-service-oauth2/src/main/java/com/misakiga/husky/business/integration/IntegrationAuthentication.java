package com.misakiga.husky.business.integration;

import lombok.Data;

import java.util.Map;

/**
 * @author MISAKIGA
 */
@Data
public class IntegrationAuthentication {

    private String authType;
    private String username;
    private Map<String,String[]> authParameters;

    public String getAuthParameter(String parameter){
        String[] values = this.authParameters.get(parameter);
        if (values != null && values.length > 0){
            return values[0];
        }
        return null;
    }

}
