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


Source generated by CrudMaker version 1.0.0.201410152247

*/

package co.edu.uniandes.csw.RoyalSystem.facultaduniandes.logic.mock;
import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.RoyalSystem.facultaduniandes.logic.dto.FacultadUniandesDTO;
import co.edu.uniandes.csw.RoyalSystem.facultaduniandes.logic.dto.FacultadUniandesPageDTO;
import co.edu.uniandes.csw.RoyalSystem.facultaduniandes.logic.api._IFacultadUniandesLogicService;

public abstract class _FacultadUniandesMockLogicService implements _IFacultadUniandesLogicService {

	private Long id= new Long(1);
	protected List<FacultadUniandesDTO> data=new ArrayList<FacultadUniandesDTO>();

	public FacultadUniandesDTO createFacultadUniandes(FacultadUniandesDTO facultadUniandes){
		id++;
		facultadUniandes.setId(id);
		data.add(facultadUniandes);
		return facultadUniandes;
    }
    
    public List<FacultadUniandesDTO> getFacultadUniandess(){
		return data; 
	}

	public FacultadUniandesPageDTO getFacultadUniandess(Integer page, Integer maxRecords){
		FacultadUniandesPageDTO response = new FacultadUniandesPageDTO();
		response.setTotalRecords(Long.parseLong(data.size()+""));
		response.setRecords(data);
		return response;
	}

	public FacultadUniandesDTO getFacultadUniandes(Long id){
		for(FacultadUniandesDTO data1:data){
			if(data1.getId().equals(id)){
				return data1;
			}
		}
		return null;
	}

	public void deleteFacultadUniandes(Long id){
	    FacultadUniandesDTO delete=null;
		for(FacultadUniandesDTO data1:data){
			if(data1.getId().equals(id)){
				delete=data1;
			}
		}
		if(delete!=null){
			data.remove(delete);
		} 
	}

	public void updateFacultadUniandes(FacultadUniandesDTO facultadUniandes){
	    FacultadUniandesDTO delete=null;
		for(FacultadUniandesDTO data1:data){
			if(data1.getId().equals(id)){
				delete=data1;
			}
		}
		if(delete!=null){
			data.remove(delete);
			data.add(facultadUniandes);
		} 
	}	
}