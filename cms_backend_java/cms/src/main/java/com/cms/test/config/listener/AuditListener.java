package com.cms.test.config.listener;

import com.cms.test.jpa.entity.AuditModel;
import com.cms.test.security.UserModel;
import com.cms.test.utils.ServiceUtils;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

/**
 * Listener Class to Entity Audits
 */
public class AuditListener {

    /**
     *
     * @param object
     */
    @PreUpdate
    public void preUpdate(Object object){
        if(object instanceof AuditModel){
            AuditModel auditModel = (AuditModel) object;
            auditModel.setUpdatedAt(new Date());
            UserModel userModel = ServiceUtils.getUserDetailsService().getLoggedInUser();
            auditModel.setUpdatedBy(userModel.getEmail());
        }
    }

    /**
     * pre Presist
     * @param object
     */
    @PrePersist
    public void perPersist(Object object){
        if(object instanceof AuditModel){
            AuditModel auditModel = (AuditModel) object;
            auditModel.setUpdatedAt(new Date());
            UserModel userModel = ServiceUtils.getUserDetailsService().getLoggedInUser();
            auditModel.setUpdatedBy(userModel.getEmail());
        }
    }


}
