package com.cms.test.config.listener;

import com.cms.test.jpa.entity.AuditModel;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

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
        }
    }


}
