/* ========================================================================
 * Copyright 2014 RoyalSystem
 *
 * Licensed under the MIT, The MIT License (MIT)
 * Copyright (c) 2014 RoyalSystem

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
 * ========================================================================


Source generated by CrudMaker version 1.0.0.201411201032

*/

package co.edu.uniandes.csw.RoyalSystem.experiencia.master.logic.dto;

import co.edu.uniandes.csw.RoyalSystem.archivo.logic.dto.ArchivoDTO;
import co.edu.uniandes.csw.RoyalSystem.comentario.logic.dto.ComentarioDTO;
import co.edu.uniandes.csw.RoyalSystem.experiencia.logic.dto.ExperienciaDTO;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public abstract class _ExperienciaMasterDTO {

 
    protected ExperienciaDTO experienciaEntity;
    protected Long id;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public ExperienciaDTO getExperienciaEntity() {
        return experienciaEntity;
    }

    public void setExperienciaEntity(ExperienciaDTO experienciaEntity) {
        this.experienciaEntity = experienciaEntity;
    }
    
    public List<ArchivoDTO> createarchivo;
    public List<ArchivoDTO> updatearchivo;
    public List<ArchivoDTO> deletearchivo;
    public List<ArchivoDTO> listarchivo;	
    public List<ComentarioDTO> createcomentario;
    public List<ComentarioDTO> updatecomentario;
    public List<ComentarioDTO> deletecomentario;
    public List<ComentarioDTO> listcomentario;	
	
	
	
    public List<ArchivoDTO> getCreatearchivo(){ return createarchivo; };
    public void setCreatearchivo(List<ArchivoDTO> createarchivo){ this.createarchivo=createarchivo; };
    public List<ArchivoDTO> getUpdatearchivo(){ return updatearchivo; };
    public void setUpdatearchivo(List<ArchivoDTO> updatearchivo){ this.updatearchivo=updatearchivo; };
    public List<ArchivoDTO> getDeletearchivo(){ return deletearchivo; };
    public void setDeletearchivo(List<ArchivoDTO> deletearchivo){ this.deletearchivo=deletearchivo; };
    public List<ArchivoDTO> getListarchivo(){ return listarchivo; };
    public void setListarchivo(List<ArchivoDTO> listarchivo){ this.listarchivo=listarchivo; };	
    public List<ComentarioDTO> getCreatecomentario(){ return createcomentario; };
    public void setCreatecomentario(List<ComentarioDTO> createcomentario){ this.createcomentario=createcomentario; };
    public List<ComentarioDTO> getUpdatecomentario(){ return updatecomentario; };
    public void setUpdatecomentario(List<ComentarioDTO> updatecomentario){ this.updatecomentario=updatecomentario; };
    public List<ComentarioDTO> getDeletecomentario(){ return deletecomentario; };
    public void setDeletecomentario(List<ComentarioDTO> deletecomentario){ this.deletecomentario=deletecomentario; };
    public List<ComentarioDTO> getListcomentario(){ return listcomentario; };
    public void setListcomentario(List<ComentarioDTO> listcomentario){ this.listcomentario=listcomentario; };	
	
	
}

