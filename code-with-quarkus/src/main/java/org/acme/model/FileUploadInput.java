// src/main/java/org/acme/model/FileUploadInput.java

package org.acme.model;

import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.PartType;
import org.jboss.resteasy.reactive.multipart.FileUpload;

public class FileUploadInput {

    @FormParam("filename")
    @PartType(MediaType.TEXT_PLAIN)
    public String filename;

    @FormParam("file")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    public FileUpload file;
}