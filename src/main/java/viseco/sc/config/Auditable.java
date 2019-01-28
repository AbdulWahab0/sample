package viseco.sc.config;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.event.AuditingEventListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@MappedSuperclass



@EntityListeners(AuditingEventListener.class)

public abstract class Auditable<U> {



    @CreatedBy

    protected U createdBy;


    @CreatedDate

    @Temporal(TemporalType.TIMESTAMP)

    protected Date creationDate;

    @LastModifiedBy

    protected U lastModifiedBy;

    @LastModifiedDate

    @Temporal(TemporalType.TIMESTAMP)

    protected Date lastModifiedDate;

}