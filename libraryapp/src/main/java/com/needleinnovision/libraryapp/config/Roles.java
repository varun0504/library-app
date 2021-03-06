package com.needleinnovision.libraryapp.config;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/*CREATE TABLE library_schema.ROLES
(
    ROLE_ID serial NOT NULL,
    NAME character varying(20) NOT NULL,
	STATUS character varying(1) NOT NULL DEFAULT 'A',
    CREATION_DATE timestamp without time zone,
    CREATED_BY character varying(40),
    MODIFICATION_DATE timestamp without time zone,
    MODIFIED_BY character varying(40),
    PRIMARY KEY (ROLE_ID),
	CONSTRAINT STATUS_TYPE CHECK (STATUS IN ('A','D'))
);*/


@Entity
@Table(name = "roles")
public class Roles {
	@Id
    private long roleId;
	
	@Column
    private String name;

    @Column
    private String status;
    
    @Column
    private Date creationDate; 

    @Column
    private String createdBy;
    
    @Column
    private Date modificationDate; 

    @Column
    private String modifiedBy;

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
}
